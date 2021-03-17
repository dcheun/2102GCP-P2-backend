# 2102GCP-P2-backend - Education Marketplace

This is the backend for Revature's 2102 Java GCP curriculum - Project 2. It is built to the specifications outlined below. 

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

- Backend is deployed on GCP App Engine
- Frontend is deployed via firebase hosting

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
