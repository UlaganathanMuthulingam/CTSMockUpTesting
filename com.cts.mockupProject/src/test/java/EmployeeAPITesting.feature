Feature: Emmployee API Testing

@LoginSuccess
Scenario: Test LoginEmployee API call with valid details
Given Pass employee parameter "emp3" password paramter "confidential"
When hit GET employee service endPoint resource as "/employee/login"
Then should return response code as 200
And successMessage should be displayed as "successful operation"


@LoginBadRequest
Scenario: Test LoginEmployee API call with invalid emp and password
Given Pass employee parameter "invalidemp" password paramter "confidential"
When hit GET employee service endPoint resource as "/employee/login"
Then should return response code as 400
And successMessage should be displayed as "Invalid employeename/password supplied"

@GetEmployee
Scenario: Get employee details based on employeeName
Given Pass employee parameter as "emp3"
When hit GET employee service endPoint resource as "/employee"
Then should return response code as 200
And print the response
And verify "emp3" with reponse field "employeename"

@PostEmployee
Scenario: Create new record
Given Pass new record json body
When hit Post employee service endPoint resource as "/employee"
Then should return response code as 200
And successMessage should be displayed as "successful operation" 


@PutEmployee
Scenario: Update ExistingRecord
Given Pass new record json body with parameter employee name as "emp4"
When hit put employee service endPoint resource as "/employee"
Then should return response code as 200
And successMessage should be displayed as "Given employee details updated successfully"


@DeleteRecord
Scenario: Delete ExistingRecord
Given Pass new record json body with parameter employee name as "emp2"
When hit delete employee service endPoint resource as "/employee"
Then should return response code as 200
And successMessage should be displayed as "Record deleted Successfully"


 
