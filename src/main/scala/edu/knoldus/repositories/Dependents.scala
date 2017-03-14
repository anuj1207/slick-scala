package edu.knoldus.repositories

import edu.knoldus.connection.{DBComp, MySqlComp}

import scala.concurrent.Future

case class Dependent(id: Int, empId: Int, name: String, relation: String, age: Option[Int])

trait DependentTable extends EmployeeTable {

  this: DBComp =>

  import driver.api._

  private[repositories] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {

    val id = column[Int]("dependentid", O.PrimaryKey, O.AutoInc)
    val empId = column[Int]("empid")
    val name = column[String]("name")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age", O.Default(None))

    def employeeDependentFK = foreignKey("employee-dependent_fk", empId, employeeTableQuery)(_.id)

    def * = (id, empId, name, relation, age) <> (Dependent.tupled, Dependent.unapply)

  }

  protected val dependentTableQuery = TableQuery[DependentTable]

  protected def dependentTableAutoInc = dependentTableQuery returning dependentTableQuery.map(_.id)

}

trait DependentRepo extends DependentTable {

  this: DBComp =>

  import driver.api._

  def create(): Future[Unit] = db.run(dependentTableQuery.schema.create)

  def insert(dependent: Dependent): Future[Int] = db.run {
    dependentTableQuery += dependent
  }

  def delete(empid: Int): Future[Int] = {
    val query = dependentTableQuery.filter(d => d.empId === empid)
    val action = query.delete
    db.run(action)
  }

  def updateName(id: Int, name: String): Future[Int] = {
    val query = dependentTableQuery.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  def upsert(dependent: Dependent): Future[Int] = {
    val query = dependentTableQuery.insertOrUpdate(dependent)
    dependentTableQuery += dependent
    db.run(query)
  }

  def getAll: Future[List[Dependent]] = db.run{
    dependentTableQuery.to[List].result
  }

  def getDependentWithEmployee: Future[List[(Employee, Dependent)]] = db.run {
    (for {
      record <- dependentTableQuery
      employee <- record.employeeDependentFK
    }yield (employee, record)).to[List].result
  }

}

object DependentRepo extends DependentRepo with MySqlComp
