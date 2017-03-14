
package edu.knoldus.repositories

import edu.knoldus.connection.{DBComp, MySqlComp}

import scala.concurrent.Future
/*not needed now
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag
*/


case class Project(id: Int, empId: Int, name: String)

trait ProjectTable extends EmployeeTable{

  this: DBComp =>

  import driver.api._


  class ProjectTable(tag: Tag) extends Table[Project](tag, "project"){
    val id = column[Int]("id", O.PrimaryKey)
    val empId = column[Int]("emp_id")
    val name = column[String]("name")

    def employeeProjectFK = foreignKey("employee_project_fk", empId, employeeTableQuery)(_.id)

    def * = (id, empId, name) <> (Project.tupled, Project.unapply)
  }

  val projectTableQuery = TableQuery[ProjectTable]

}


trait ProjectRepo extends ProjectTable {

  this: DBComp =>

  import driver.api._

//  val db = Database.forConfig("myPostgresDB")

  def create: Future[Unit] = db.run(projectTableQuery.schema.create)

  def insert(project: Project): Future[Int] = db.run{
    projectTableQuery+=project
  }

  def delete(name: String): Future[Int] = {
    val query = projectTableQuery.filter(x=> x.name === name)
    val action = query.delete
    db.run(action)
  }

  def update(id:Int ,name: String): Future[Int] = {
    val query = projectTableQuery.filter(x=> x.id === id)
      .map(_.name)
    val action = query.update(name)
    db.run(action)
  }

  def upsert(project: Project): Future[Int] = {
    val query = projectTableQuery.insertOrUpdate(project)
    projectTableQuery+=project
    db.run(query)
  }

  def getAll: Future[List[Project]] = {
    val query = projectTableQuery.to[List]
    db.run(query.result)
  }

}

object ProjectRepo extends ProjectRepo with MySqlComp
