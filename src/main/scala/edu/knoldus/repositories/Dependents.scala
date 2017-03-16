package edu.knoldus.repositories

import edu.knoldus.connection.{DBComp, MySqlComp}

import scala.concurrent.Future

case class Dependent(id: Int, empId: Int, name: String, relation: String, age: Option[Int])

trait DependentTable extends EmployeeTable {

  this: DBComp =>

  import driver.api._

  private[repositories] class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {

    val id = column[Int]("dependentId", O.PrimaryKey, O.AutoInc)
    val empId = column[Int]("empId")
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

  def delete(id: Int): Future[Int] = {
    val query = dependentTableQuery.filter(d => d.id === id)
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

  def getDependentWithEmployeeName: Future[List[(String,String)]] = {
    val abc = for{
      (dep,emp) <- dependentTableQuery join employeeTableQuery on (_.empId === _.id)
    }yield (dep.name,emp.name)
    db.run(abc.to[List].result)
  }

  def insertThenUpdate(dependent: Dependent, name: String): Future[Int] = {
    val q1 = dependentTableQuery += dependent
    val q2 = dependentTableQuery.filter(_.id === dependent.id).map(_.name).update(name)
    val query = q1.andThen(q2).transactionally
    db.run(query)
  }

  def insertPSQL(dependent: Dependent): Future[Int] ={
    val q = sqlu"insert into dependent values (${dependent.id}, ${dependent.empId}, ${dependent.name}, ${dependent.relation}, ${dependent.age})"
    db.run(q)
  }

  /*def getMaxAge = {
    val query = dependentTableQuery.map(_.age).max
    db.run(query)
  }*/

}

object DependentRepo extends DependentRepo with MySqlComp
