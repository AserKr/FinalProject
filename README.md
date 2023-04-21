# Final Project
This Project is Simulation of a library System in which the user can interact with it via the provided textual interface, the library system allows the user to use many features. All the steps to run and install the program are provided below 

---
# Installation and Build
This project is also a Maven project, i.e. it uses the standard Maven project structure that your IDE hopefully understands when you `git clone` it. The provided Maven POM includes the JUnit4 and the algs4 dependency.



Maven:
- `mvn compile` compiles all implementation classes.
- `mvn run` executes the main method, "Main"
- 'mvn site'
- `mvn test` runs all test cases (i.e. all classes with a name that either starts with `Test` or ends with `Test`, `Tests`, or `TestCase`).
- `mvn site` generates a documentation site for the project information.



Jar:
- Navigate to the FinalProject directory.
- In your terminal run the following command
`sh createjar.cmd` to generate a compressed jar file and place it in the current directory.
- In your terminal use the following command `sh runjar.cmd` to run the jar file



---
# Usage
**In the library System you can do the following as a Faculty member:**

- Add books
- Add Omnibuses

**In the library System you can do the following as a user:**

- Borrow a book
- Return a book
- Extend Lending (with the presence of a valid faculty member)






---
# License
License: [MIT](LICENSE.txt)




---
# Design
This Program implements the Composistion design pattern between the Omnibuses and Books, where an omnibus contains many books and when an action is performed on an omnibus all of its children books undergo the same action
[Class Diagram](src/site/markdown/DESIGN.md)



---
# Contributions

- Aser Kroma -- Villborg Erlendsdóttir


