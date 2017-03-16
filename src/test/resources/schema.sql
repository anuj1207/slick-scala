DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee(id int PRIMARY KEY ,name varchar(200),age int);

DROP TABLE IF EXISTS project;

CREATE TABLE IF NOT EXISTS project (
  id int PRIMARY KEY,
  name varchar(25),
  emp_id INT NOT NULL references employee(id),
  totalMembers int,
  lead varchar(30)
  );

DROP TABLE IF EXISTS Dependent;

CREATE TABLE IF NOT EXISTS dependent (
  dependentId int PRIMARY KEY,
  empId int NOT NULL references employee(id),
  name varchar(50),
  relation varchar(20),
  age int
  );