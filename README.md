# MachmakingSystem
This system uses the [ELO rating algorithm](https://en.wikipedia.org/wiki/Elo_rating_system)
to find balanced match between two players, based on their rating.

## Database
I used the JDBC API to connect to MySQL.
You can easily create a connection to the database in the following way:

```java
java.sql.Connection connection = DriverManager.getConnection("jdbc:subprotocol://url:port/schema", "username", "password");
```

## Credentials
If you are going to share a project on GitHub like i did, make sure to hide your MySQL credentials by setting up 
at least 2 environment variables: 1 for the username and 1 for the password.
You can use your credentials with the System library:

```java
String yourEnvironmentVariable = System.getenv("yourVariableName");
```
