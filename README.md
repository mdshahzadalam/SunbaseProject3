# SunbaseProject3


# CRUD Application for Customer

## Description
This is a CRUD application for managing customers using Java JSP Servlets. It includes functionalities to create, read, update, and delete customer information. Additionally, it includes JWT authentication and a synchronization feature to fetch and update customer data from a remote API.

## Requirements
- Java 8+
- Apache Tomcat 8+
- MySQL



## Functionality
- *Login Screen*: Allows user authentication using JWT.
- *Customer List Screen*: Displays a list of customers with options to add, edit, delete, and sync customer data.
- *Add Customer Screen*: Form to add or update customer information.

## Authentication
- Use the following credentials for login:
  - *Login ID*: test@sunbasedata.com
  - *Password*: Test@123

## Sync Feature
- Syncs customer data from the remote API: https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=customer_list
- Authenticate using the following endpoint:
  - *Path*: https://qa.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp
  - *Method*: POST
  - *Body*: { "login_id": "test@sunbasedata.com", "password": "Test@123" }
  - *Header*: Authorization: Bearer token_received_in_authentication_API_call

## Notes
- Ensure the sync servlet is mapped to /sync in web.xml.
- Do not hardcode login credentials in the code.
- Add comments in your code for readability.

## License
This project is licensed under the MIT License.
