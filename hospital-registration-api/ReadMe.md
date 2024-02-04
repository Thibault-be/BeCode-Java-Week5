Endpoints
Register a Visitor

URL: POST /api/visits
Request Body: JSON object with the following fields:
firstName (String): First name of the visitor.
lastName (String): Last name of the visitor.
doctorId (Optional, Integer): ID of the consulting doctor, if applicable.
timestamp (String): Timestamp of the visit in a valid date-time format.
Response: 201 Created on successful registration, along with the visitor's unique id.
Description: This endpoint allows you to register a visitor, providing their details and the optional consulting doctor's ID. The registration includes a timestamp for the visit.
Get Visits for a Day

URL: GET /api/visits
Query Parameters:
date (String): Date for which to retrieve visits (in a valid date format).
Response: 200 OK with a list of visits made on the specified day.
Description: Use this endpoint to get a list of all visits that occurred on a specific date.
Get Visits for a Period of Time

URL: GET /api/visits
Query Parameters:
startDate (String): Start date of the period (in a valid date format).
endDate (String): End date of the period (in a valid date format).
Response: 200 OK with a list of visits made during the specified period.
Description: Use this endpoint to retrieve a list of visits that occurred within a specified time period.
Manage Doctors

URL: POST /api/doctors
Request Body: JSON object with the following fields:
name (String): Name of the doctor.
specialization (String): Specialization of the doctor.
Response: 201 Created on successful addition, along with the doctor's unique id.
Description: This endpoint allows hospital management to add a new doctor along with their specialization.
Update a Doctor

URL: PUT /api/doctors/{id}
Path Parameters:
id (Integer): ID of the doctor to update.
Request Body: JSON object with the following fields:
name (String): Updated name of the doctor.
specialization (String): Updated specialization of the doctor.
Response: 200 OK on successful update.
Description: Use this endpoint to update the details of a specific doctor.
Delete a Doctor

URL: DELETE /api/doctors/{id}
Path Parameters:
id (Integer): ID of the doctor to delete.
Response: 204 No Content on successful deletion.
Description: This endpoint allows hospital management to remove a doctor from the system.
Get All Doctors

URL: GET /api/doctors
Response: 200 OK with a list of all registered doctors.
Description: Use this endpoint to retrieve a list of all doctors registered in the system.
Get Doctor by ID

URL: GET /api/doctors/{id}
Path Parameters:
id (Integer): ID of the doctor to retrieve.
Response: 200 OK with the details of the specified doctor.
Description: This endpoint allows you to fetch the details of a specific doctor by providing their ID.