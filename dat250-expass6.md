DAT250 Assignment 3 Short Report
1. Technical Issues and Learning Experience

- Dynamic poll retrieval in React
	- Implemented fetching the correct poll based on the URL by using React Router’s useParams() hook to extract the poll ID. This allowed the frontend to display the correct poll question and its options dynamically.

- Voting system implementation
	- While implementing vote submission, initially votes did not register correctly in the backend due to mismatches between the frontend payload and the backend expectations.
	- Adjusted the vote payload to include the poll ID and the selected option’s presentationOrder.

- Real-time vote counter update
	- After submitting a vote, the displayed vote counts did not update immediately.
	- Solved by updating the local state in React immediately after a successful vote submission.

- Frontend integration with Spring Boot
	- When serving the React application through Spring Boot, accessing React routes directly caused 404 errors.
	- Solved by adding a controller to forward all React routes (e.g., /createpoll and /vote/{id}) to index.html, allowing the SPA to handle routing internally.

2. Pending/Optional Tasks
Extensions (Step 6): I did not add any of these extensions.
