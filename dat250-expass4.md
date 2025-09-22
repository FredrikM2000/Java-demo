DAT250 Assignment 4 Short Report

1. Technical Issues and Learning Experience

- Test failure due to presentationOrder
  - A test initially failed because it expected 2 votes for an option, but got 0. The issue was that presentationOrder on VoteOption wasn’t set correctly. After fixing the logic to assign it when adding options to a poll, the test passed.
- Inspecting the database
  - I struggled to figure out how to view the contents of the in-memory H2 database. Since the database only exists while the program is running, I could not just open it in an external tool. My solution was to run SQL queries directly in the test class (`PollsTest`) and print out the results with `System.out.println`. This way, I could confirm the tables were created and data was inserted.

2. Managing the Database Tables

I inspected the tables by running queries during the test execution and printing the results.  
Here is an example screenshot of the output showing that the four tables were created and populated:

<img width="528" height="374" alt="image" src="https://github.com/user-attachments/assets/c76f3692-0b7b-46d5-9983-57245e59d158" />
This confirms that the tables `USERS`, `POLLS`, `VOTEOPTIONS`, and `VOTES` were successfully created and populated by the JPA entities.

3. Pending Issues
 
- The raw output from printing query results shows object references (like `[B@588614f9]`) for binary data, which is not very readable. It works for inspection, but could be improved with better formatting.
- I was not able to find a better way to inspect the database tables in time than printing them from within the test code. The output works for verification but is not as clear as using a dedicated database viewer.  
