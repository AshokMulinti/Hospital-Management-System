Hospital Management System:
This is a Spring Boot-based backend application for managing hospital operations in a Hospital Management System.


AuthController:
Handles user authentication

Endpoint        Method          Description
/auth/login	     POST	          User login (returns JWT token)


AdminController:
Admin operations for managing users and roles

Endpoint	         Method     	Description
/admin/users	      GET	        Get all users
/admin/users/{id}	  GET       	Get user by ID
/admin/register	    POST	      Register a new user
/admin/users/{id}	  PUT	        Update an existing user
/admin/users/{id}	  DELETE    	Delete a user
/admin/roles	      GET	        Get all roles


ReceptionistController:
Receptionist operations like patient registration and appointment management

Endpoint                          	Method	             Description
/receptionist/register-patient    	POST	            Register a new patient
/receptionist/schedule-appointment	POST	            Schedule an appointment
/receptionist/update-appointment  	PUT	              Update an existing appointment
/receptionist/appointments	        GET	              Get list of all appointments
