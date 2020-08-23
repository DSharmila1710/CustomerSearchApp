# CustomerSearchApp

Project : API Automation
Framework : TestNG
Support : RestAssured Java Libraries
Authentication : bearer token

Project Overview : 
Customers search application enables the operations team of an organization to search for existing
customer(s). To search customer(s), the user needs to authenticate themselves using their credentials.
Once authentication is successful then the user can either search all customers or search a customer by
their phone number.

APIs involved :
1. AuthenticateAPI is the test case that is run to fetch the Oauth2.0 token
2. GetUsersAPI is the test case which displays the list of all the user details present in the app
3. GetParticularUserDetail is the test case which searches a particualr user with his phone number
