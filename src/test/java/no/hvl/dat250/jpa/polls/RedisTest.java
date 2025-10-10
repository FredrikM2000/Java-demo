package no.hvl.dat250.jpa.polls;

import com.example.demo.Poll;
import com.example.demo.User;
import com.example.demo.Vote;
import com.example.demo.VoteOption;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import net.bytebuddy.description.method.MethodDescription;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.json.Path;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RedisTest {
    private UnifiedJedis jedis;
    private EntityManagerFactory emf;
    private Gson gson =  new Gson();

    @BeforeEach
    public void setUp() {
        EntityManagerFactory emf = new PersistenceConfiguration("polls")
                .managedClass(Poll.class)
                .managedClass(User.class)
                .managedClass(Vote.class)
                .managedClass(VoteOption.class)
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:polls")
                .property(PersistenceConfiguration.SCHEMAGEN_DATABASE_ACTION, "drop-and-create")
                .property(PersistenceConfiguration.JDBC_USER, "sa")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                .createEntityManagerFactory();
        emf.runInTransaction(this::populate);
        this.emf = emf;

        jedis = new UnifiedJedis("redis://localhost:6379");
    }

    private void populate(EntityManager em) {
        User alice = new User("alice", "alice@online.com");
        User bob = new User("bob", "bob@bob.home");
        User eve = new User("eve", "eve@mail.org");
        em.persist(alice);
        em.persist(bob);
        em.persist(eve);
        Poll poll = alice.createPoll("Vim or Emacs?");
        VoteOption vim = poll.addVoteOption("Vim");
        VoteOption emacs = poll.addVoteOption("Emacs");
        Poll poll2 = eve.createPoll("Pineapple on Pizza");
        VoteOption yes = poll2.addVoteOption("Yes! Yammy!");
        VoteOption no = poll2.addVoteOption("Mamma mia: Nooooo!");
        em.persist(poll);
        em.persist(poll2);
        em.persist(alice.voteFor(vim));
        em.persist(bob.voteFor(vim));
        em.persist(eve.voteFor(emacs));
        em.persist(eve.voteFor(yes));
    }


    @Test
    public void testRedis() {
        UnifiedJedis jedis = new UnifiedJedis("redis://localhost:6379");

//        System.out.println("Keys in Redis:");
//        for (String key : jedis.keys("*")) {
//            System.out.println(key);
//        }

        // Get the JSON value for your poll
//        String pollJson = jedis.jsonGetAsPlainString("poll:03ebcb7b", Path.ROOT_PATH);
//        System.out.println("Poll JSON: " + pollJson);
        System.out.println("Keys in Redis:");
        for (String key : jedis.keys("*")) {
            System.out.println(" - " + key);
        }



//        System.out.println("Keys in Redis:");
//        for (String key : jedis.keys("*")) {
//            String type = jedis.type(key);
//            System.out.println("Key: " + key + " (type: " + type + ")");
//
//            switch (type) {
//                case "string":
//                    System.out.println("  Value: " + jedis.get(key));
//                    break;
//                case "hash":
//                    System.out.println("  Hash values: " + jedis.hgetAll(key));
//                    break;
//                case "list":
//                    System.out.println("  List values: " + jedis.lrange(key, 0, -1));
//                    break;
//                case "set":
//                    System.out.println("  Set members: " + jedis.smembers(key));
//                    break;
//                case "zset":
//                    System.out.println("  Sorted set: " + jedis.zrangeWithScores(key, 0, -1));
//                    break;
//                default:
//                    System.out.println("  (Unhandled type: " + type + ")");
//                    break;
//            }
//        }

        jedis.close();
    }


    public Map<String, Integer> retrieveVotesPerOption(Long pollId) {
        // Check cache
        String cacheKey = "poll:votes:" + pollId;
        String cached = jedis.jsonGetAsPlainString(cacheKey, Path.ROOT_PATH);
        if(cached != null) {
            System.out.println("Cache hit for poll " + pollId);
            Type type = new TypeToken<Map<String, Integer>>() {}.getType();
            // parse cached JSON back into Map<String, Integer> and return
            return gson.fromJson(cached, type);
        }

        System.out.println("Cache miss for poll " + pollId + ", fetching from DB...");
        Map<String, Integer> votes = fetchVotesFromDatabase(pollId);

        jedis.jsonSet(cacheKey, Path.ROOT_PATH, votes);
        jedis.expire(cacheKey, 60);

        return votes;
    }

    private Map<String, Integer> fetchVotesFromDatabase(Long pollId) {
        Map<String, Integer> voteCounts = new HashMap<>();

        emf.runInTransaction(em -> {
            List<Object[]> results = em.createQuery(
                            "SELECT o.caption, COUNT(v.id) " +
                                    "FROM VoteOption o LEFT JOIN Vote v ON v.votesOn = o " +
                                    "WHERE o.poll.id = :pollId " +
                                    "GROUP BY o.caption", Object[].class)
                    .setParameter("pollId", pollId)
                    .getResultList();

            for (Object[] row : results) {
                voteCounts.put((String) row[0], ((Long) row[1]).intValue());
            }
        });

        return voteCounts;
    }

    @Test
    public void testCacheImplementation() {
        Long pollId = 1L;

        emf.runInTransaction(em -> {
            System.out.println("=== USERS ===");
            em.createNativeQuery("SELECT * FROM users")
                    .getResultList()
                    .forEach(row -> System.out.println(Arrays.toString((Object[]) row)));

            System.out.println("=== POLLS ===");
            em.createNativeQuery("SELECT * FROM polls")
                    .getResultList()
                    .forEach(row -> System.out.println(Arrays.toString((Object[]) row)));

            System.out.println("=== VOTEOPTIONS ===");
            em.createNativeQuery("SELECT * FROM voteoptions")
                    .getResultList()
                    .forEach(row -> System.out.println(Arrays.toString((Object[]) row)));

            System.out.println("=== VOTES ===");
            em.createNativeQuery("SELECT * FROM votes")
                    .getResultList()
                    .forEach(row -> System.out.println(Arrays.toString((Object[]) row)));
        });

        Map<String, Integer> firstFetch = retrieveVotesPerOption(pollId); // First call -> should be a cache miss and hit DB
        System.out.println("DB result: " + firstFetch);


        String pollJson = jedis.jsonGetAsPlainString("poll:03ebcb7b", Path.ROOT_PATH);
        System.out.println("Poll JSON: " + pollJson);

        Map<String, Integer> secondFetch = retrieveVotesPerOption(pollId); // Second call -> should be a cache hit
        System.out.println("Cache result: " + secondFetch);

        assertNotNull(secondFetch);
    }

    @AfterEach
    public void tearDown() {
        if (jedis != null) {jedis.close();}
        if (emf != null) {emf.close();}
    }

}
