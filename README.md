
# Department Event Portal

This mini project is part of curriculum activity using Java, MySQL

To run this project you required some basic softwares

    1. Java 8.0+
    2. MySQL 5.1+
## Run Locally

Clone the project

```bash
  git clone /https://github.com/hashanant/Mini-Project
```

Go to the project directory

```bash
  cd Mini-Project
```

Start MySQL local server with default port ```3306```

```bash
  net start <DATABASE USERNAME>
```

Create and configure tables in database
```bash
  CREATE DATABASE miniproject;

  CREATE TABLE signup (ID INT(10) AUTO_INCREMENT, fName TEXT, lName TEXT, userName VARCHAR(10), pwd VARCHAR(15), PRIMARY KEY (ID));

  CREATE TABLE admin (id INT(10) AUTO_INCREMENT, userName VARCHAR(10), pwd VARCHAR(15), PRIMARY KEY (id));

  INSERT INTO admin VALUES (1, admin, hash);

  CREATE TABLE events (id INT(4), title VARCHAR(255), description LONGTEXT, PRIMARY KEY (id));

  CREATE TABLE applied (id INT(10) AUTO_INCREMENT, userName VARCHAR(10), title VARCHAR(255), PRIMARY KEY (id));
```

Compile Java files

```bash
  javac *.java
```

Connect MySQL Connector to Java  

```bash
  java -cp .;mysql-connector-java.jar MainWindow
```

  
## Documentation

[Java Documentation](https://docs.oracle.com/en/java/)

[MySQL Documentation](https://dev.mysql.com/doc/)

  
## 🛠 Skills
Java, Python, MySQL, HTML5, CSS, JavaScript...

  
## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/hashanant)

[![twitter](https://img.shields.io/badge/twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/hashanant)

  
## License

[MIT](https://choosealicense.com/licenses/mit/)
