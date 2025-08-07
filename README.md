Hospital Management System:
This is a Spring Boot-based backend application for managing hospital operations in a Hospital Management System.

Technologies Used
Java 21
MySQL
Spring Boot
Spring Security(JWT token)
RBAC
REST API



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

DoctorController:
Doctor operations such as managing patient medical histories.

Endpoint                      	   Method	      Description
/doctor/appointments                GET       	Get list of all appointments
/doctor/add-medical-history	        POST	      Add medical history for a patient
/doctor/medical-history/{patientId} GET	        Get medical history by patient ID


NurseController:
Nurse operations such as updating health records of patients.

Endpoint	                        Method	    Description
/nurse/health-records	             POST	      Add new health record for a patient
/nurse/health-records/{patientId}	 GET	        Get health records by patient ID
/nurse/health-records           	 GET   	    Get all health records

PatientController
Patient access to view their complete health information.

Endpoint            	 Method	  Description
/patient/health-info  	GET	   Get full health information by email



