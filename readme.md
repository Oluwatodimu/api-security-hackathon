## API Security Hackathon Submission Documentation

This documentation is divided into two major parts:
1. Instructions on how to run and get familiar with the application locally.
2. Steps taken to make the APIs and the application in general secure.

### 1. Instructions on how to run and get familiar with the application locally

For the aim of setting up this application properly, the team has hosted the major dependencies
using cloud services and containerized the application. The dependencies hosted on the cloud are the `mysql`
database and the `redis` cluster.

The only `requirement` to run this application locally is to have docker installed on your machine.
After that is set up, you should follow the following instructions:

1. Clone the project from Git Hub (main branch).
2. Ensure you have good internet and a powerful machine because the performance of  docker depends on these.
3. Navigate to the directory containing the cloned project and run the following command 
`docker build -t api-security.jar .`. Ensure your `docker` is running in the background before you do this.
The purpose of this step is to build a docker image of the application.
4. After doing this, the next thing is to run the docker image in a container and you can do this by using the
the command below `docker run -p 8080:8080 api-security.jar`, which will run the docker container on the localhost, and
enable you to access the APIs at port 8080.

You can ensure the application is running by running the `app health check` request located in the application's Postman API collection
which can be found here for your reference `https://www.postman.com/lively-firefly-891824/workspace/my-public-workspace/collection/18629385-4d291df2-63ea-448a-85f1-c66f5813ab34?action=share&creator=18629385`.

To access the APIs locally, you should change the Postman collection environment to `localhost`. You can also access the APIs of the cloud
hosted application by changing the environment to `cloudhost` in the postman collection.

#### Getting familiar
There are 3 user roles in the application; admin, teacher and student roles. Only an admin can create a teacher user. 
We created an admin user with the email `admin@gmail.com` and password `password123$` so you can use this for authentication
and explore the functionality of the application. With this, you can create a teacher user.

For more clarification, we made a
table listing all the endpoints in the application, a brief description of what they do, and who can access them. This is shown below
![Screen Shot 2023-07-05 at 10 54 46 PM](https://github.com/Oluwatodimu/api-security-hackathon/assets/81085914/a85293a0-5670-4b8d-be94-f88c1979a8db)

I hope this will be clear enough instructions for the judges to navigate through the endpoints with ease, 
and once again thank your for the opportunity to participate in this hackathon.

### 2. Steps Taken to Secure the APIs and Endpoints

This section contains a detailed explanation of the steps our team took in
securing the application and its endpoints.

The mandatory requirements for the hackathon completed are explained below:
1. Authentication & Authorization: This was achieved in the project with the use of
JWTs (JSON web tokens). Users are authenticated with the use of their emails and passwords, with
a `rememberMe` parameter to manage the expiration time of the generated jwt token. User authorities
are also embedded into the JWTs for the purpose of authorization.


2. Input validation: This was implemented by creating a 3-layer input validation approach. The
3. first layer of validation occurs by validating the request objects that are parsed from the API request body;
the second layer of validation occurs in the transition from the service layer to the
repository layer and the third one occurs on the entities themselves before they are saved into the database.
We also created Java filters to validate the request headers to ensure the required headers
are present, e.g the presence of the `Accept` header and its mandatory value of `application/JSON


4. Rate Limiting: This was achieved using filters also, and we set a value of a maximum of 100 requests every 
2 minutes. We also made these values configurable, in order for tweaking and giving freedom when testing locally.


5. Logging and monitoring with treblle.


6. Use UUIDs not IDs: The team ensured the use of UUIDs when saving entities into the database, and other operations.


7. Method limiting: This was also achieved to ensure that no malicious were made on the APIs, by trying 
different HTTP verbs.


8. Minimum of 6 endpoints: The team created a total of 17 endpoints for this application.


9. Other mandatory requirements were made, and the team ensured that they passed the API call
audit on the Treblle dashboard.

The other measures the team took to ensure the security of the project APIs include:
1. Executing authorization using Role-based access control and method level security: Since three kind of
user roles exist in the application, we implemented the authorization at the method level, the controller
methods in the controller layer of the application. So now, there are some endpoints that require only authentication,
and some that require authentication, and also a specific user role.


2. Using DB audit columns: Db audit columns are not directly part of application security but play a crucial role in 
enhancing the overall security of an application. Audit columns can also help identify errors and anomalies within the 
application. By capturing and logging important events, such as unexpected system behavior, failed operations, 
or exceptions, developers and administrators can analyze the logs to identify and resolve potential security 
vulnerabilities or system weaknesses.


3. Using DTOs: We implemented the DTOs design pattern to provide a structured and controlled way to handle data in our 
application, which we leveraged to enforce security practices and mitigate security risks. By utilizing DTOs 
effectively, we improved the overall security posture of our application by controlling data access, validating 
inputs, and enforcing data protection formats. We also used DTOs to execute our second layer of data validation
(as explained earlier). We did this by enforcing fields to conform to specific requirements and then ensured that
the transfer of data from the service layer to the controller layer was done by DTOs alone.


4. User activation with OTP: This is used in our application after the successful registration
of a student. This process helps ensure that the account being created is associated with a legitimate user and not a 
malicious actor attempting to create fraudulent or unauthorized accounts. OTPs provide an additional layer of security 
by making it difficult for automated attacks. We just made the OTP (activation key) be returned as part of the response
and didn't do anything fancy like sending actual emails, since it is not a major requirement.


5. Uniform API and error responses: This was one of the highest-priority security features
we implemented in our hackathon submission. The goal of this is to prevent hackers from 
gaining insights into the application's vulnerabilities or sensitive data. This also helped prevent the exposure of 
sensitive information, such as database details or system internals, in error responses.


6. Cors origin resource sharing (CORS) handling - We were not sure what sort of applications
would be used to test our application, so the team decided to handle CORS configuration, in order to avoid such
related CORS errors in the testing process.


I also took some pointers from the 10 rest commandments posted earlier this week.
