## Film Query Command Line App

### Skill Distillery Week 7 Project

### Description
Film Query App is a command-line application that retrieves data from a mySQL database using JDBC API. It has a switch based user menu that allows a user to search for a film by id, or search for a film by keyword. All JDBC code is contained in the DatabaseAccessorObject class. For my user inputs I implemented an InputHelper class to display messages and parse input. To display the language, I used an ENUM that corresponded to the language ID and called it in my toStringDetails() method.


### Lessons Learned
Lessons learned include relational databases, SQL, and JDBC API. I learned the distinction between prepared statements and statements. A prepared statement is compiled one time, even if a bind variable changes, it will use the statement again without compiling. Additionally, you can’t use a bind variable with statements, and they are vulnerable to SQL Injection.


### Technologies Used
Java, Eclipse, vi, atom, git/gitHub, Maven Repository, SQL, mySql, JDBC
