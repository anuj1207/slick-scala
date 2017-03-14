package edu.knoldus

import edu.knoldus.repositories.{Employee, EmployeeRepo, Project, ProjectRepo}

import scala.concurrent.ExecutionContext.Implicits.global

object SlickApplication {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    //EmployeeRepo.create
    val insertRes = EmployeeRepo.insert(Employee(2021,"raman",24))
    val res = insertRes.map{res => s"$res row inserted"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res.map(println(_))
    Thread.sleep(5000)
    ProjectRepo.create
    val insertResPro = ProjectRepo.insert(Project(3,2050,"anujProject"))
    val res1 = insertResPro.map{res => s"$res row inserted"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res1.map(println(_))
    Thread.sleep(5000)
  }
}
