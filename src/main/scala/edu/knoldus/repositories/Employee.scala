package edu.knoldus.repositories

import edu.knoldus.connection.{DBComp, MySqlComp}

import scala.concurrent.Future
/*not needed now
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag*/


case class Employee(id: Int, name: String, age: Int)

trait EmployeeTable {

  this: DBComp =>

  import driver.api._

  class EmployeeTable(tag: Tag) extends Table[Employee](tag, "employee"){
    val id = column[Int]("id", O.PrimaryKey)
    val name = column[String]("name")
    val age = column[Int]("age")

    def * = (id, name, age) <> (Employee.tupled, Employee.unapply)
  }

  val employeeTableQuery = TableQuery[EmployeeTable]

}

trait EmployeeRepo extends EmployeeTable {

  this: DBComp =>

  import driver.api._

  //val db = Database.forConfig("myPostgresDB")

  def create: Future[Unit] = db.run(employeeTableQuery.schema.create)

  def insert(emp: Employee): Future[Int] = db.run{
    employeeTableQuery+=emp
  }

  def delete(name: String): Future[Int] = {
    val query = employeeTableQuery.filter(x=> x.name === "anuj")
    val action = query.delete
    db.run(action)
  }

  def update(id:Int ,name: String): Future[Int] = {
    val query = employeeTableQuery.filter(x=> x.id === id)
      .map(_.name)
    val action = query.update(name)
    db.run(action)
  }

  def upsert(employee: Employee): Future[Int] = {
    val query = employeeTableQuery.insertOrUpdate(employee)
    employeeTableQuery+=employee
    db.run(query)
  }

  def getAll: Future[List[Employee]] = {
    val query = employeeTableQuery.to[List]
    db.run(query.result)
  }

  def getSeniorEmployee(age: Int): Future[List[Employee]] = {
    val query = employeeTableQuery.filter(_.age>=age).to[List]
    db.run(query.result)
  }

}

object EmployeeRepo extends EmployeeRepo with MySqlComp