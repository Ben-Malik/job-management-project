# job-management-project

The milestone of this very project is to allow managing jobs
to be run at any wished time.

You could schedule a job to be executed at a particular time or
execute it immediately.

Each of the jobs are in one of the States: QUEUED, RUNNING, DONE,or FAILED.

For testing purpose the in-memory database H2 has been utilised.

# To run the project on your local
<code>$ git clone https://github.com/Ben-Malik/job-management-project <code/>


<code> $cd job-management-project </code>
<br>

 **Now Run the Project** 


<code>$ mvn clean install spring-boot:run </code>



# Future Plans on the Project

1. Add a new html page allowing users to add their jobs via a simple form.
2. Allow jobs to run via shell or bash scripts.
3. Ensure the jobs are saved to the database after their run in the schedule mode.
4. Deploy the project to a remote host.

**Got any ideas or suggestions on the project?** Drop me an email at [benmaliktchamalam@gmail.com](mailto:benmaliktchamalam@gmail.com) to discuss!