Scenario: User is trying to login to admin panel with wrong password

Given user is on login form page
When user inserts login: Adrian in input with name: user
Then user inserts password: test in input with name: haslo
When user clicks on submit button with id: submit
Then site displays failed login text in div with id: błąd


Scenario: User is trying to login to admin panel with good password and he managed to do it

Given user is on login form page
When user inserts login: Adrian in input with name: user
Then user inserts password: projekt in input with name: haslo
When user clicks on submit button with id: submit
Then site contains link with text: Wyloguj


Scenario: User is trying to login to admin panel with good password and he managed to do it. And then he goes back to homepage and back to administrator panel with session turned on.

Given user is on login form page
Then site contains link with text: Wyloguj
When user clicks on link with text: Strona główna
Then user clicks on link with text: Panel administracyjny
Then session is restored and session id's length is greater than 0: true

