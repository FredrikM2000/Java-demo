# DAT250 Assignment 5 Short Report

1. Execution

For this assignment, I implemented Redis caching for aggregated vote counts using a single test class, RedisTest.  
The class sets up an in-memory H2 database, populates it with polls and votes, and uses Redis (via UnifiedJedis) to cache query results as JSON.  
The test verifies that the first call retrieves data from the database, while subsequent calls return cached results.

2. Technical Issues and Learning Experience

- Jedis JSON Type Error
  - jedis.jsonGet() returned an Object instead of a String. Fixed by using jsonGetAsPlainString() to correctly retrieve JSON data.

- Empty Database
  - No data was available for caching at first. Solved by copying the populate() method from PollsTest to insert sample data.

3. Pending Issues
To the best of my knowledge, there are no pending issues and the assignment has been fully completed.
