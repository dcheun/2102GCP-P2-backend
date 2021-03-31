# 2102GCP-P2-backend - Online Education

This is the backend for Revature's 2102 Java GCP curriculum - Project 2. It is built to the specifications outlined below. 

Online Education is an online platform that allows instructors to create courses and students to search for and enroll in courses.

Only registered users can login and create or enroll in courses. Users can sign up via the sign up page and select if they want to be an instructor or a student. Once the user logs in they are redirected to the main dashboard. From the main dashboard users can view their courses and list of all other available courses are presented in a bottom section. There is a search bar at the top where they can search for courses.

For instructors, they have a button that will allow them to create new courses or edit an existing one. Each course can have many course materials. Once this is saved, the course will be added to their MY COURSES section.

For students, they can enroll in a course by viewing it in the details page and clicking on Enroll. Students can start a course to begin viewing course materials. In this view they can also rate and leave comments for the courses they take.

Non-registered users are free to browse available courses on the platform and view it's details.

## Technical Specifications

The backend is written in the Spring Framework utilizing the following technologies:

- Spring WEB
- Spring DATA
- Spring AOP

The frontend is written in Angular framework. There is a separate repository for the frontend.

## Testing and Logging Specifications

This project was developed with test-driven development (TDD) process in mind. 

The testing suite includes:

### DAO and Service methods are tested accordingly

- Mocking is performed on all applicable service tests and where necessary
- Postman tests are configured for every endpoint

### All applicable errors must be logged to a file

- Any other important information should be logged as well

## API Specifications

- The API follow RESTful conventions
- JWTs are used to authenticate users and protect routes
- Appropriate error codes are returned for every endpoint

## Continuous Integration and Deployment Specifications

### CI/CD Tools

- All user stories are listed
- Frontend and Backend have their own repositories
- SonarCloud set up on github for at least the backend
- Jenkins set up for automatic building and testing of the backend

### Deployment - GCP and Firebase

- Backend is deployed on GCP
- Frontend is deployed via firebase hosting

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
