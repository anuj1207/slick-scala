package edu.knoldus

import edu.knoldus.repositories._

import scala.concurrent.ExecutionContext.Implicits.global

object SlickApplication {

  def main(args: Array[String]): Unit = {
    println("Hello, world!")

    /*val insertRes0 = EmployeeRepo.insert(Employee(2022,"anuj",24))
    val res0 = insertRes0.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res0.map(println(_))
    Thread.sleep(1000)*/

    val insertRes1 = DependentRepo.insertThenUpdate(Dependent(51,2022,"anuj","bro",Some(24)),Dependent(52,2022,"anuj'brother","bro",Some(24)))
    val res1 = insertRes1.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res1.map(println(_))
    Thread.sleep(1000)
    /*EmployeeRepo.create

    Thread.sleep(10000)

    val insertRes0 = EmployeeRepo.insert(Employee(2022,"anuj",24))
    val res0 = insertRes0.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res0.map(println(_))
    Thread.sleep(5000)

    val insertRes = EmployeeRepo.insert(Employee(2021,"raman",24))
    val res01 = insertRes.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res01.map(println(_))
    Thread.sleep(5000)

    val insertRes1 = EmployeeRepo.insert(Employee(2023,"aman",24))
    val res02 = insertRes1.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res02.map(println(_))
    Thread.sleep(5000)

    val insertRes2 = EmployeeRepo.insert(Employee(2024,"chaman",24))
    val res03 = insertRes2.map{res => s"$res row inserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res03.map(println(_))
    Thread.sleep(5000)


    ProjectRepo.create
    Thread.sleep(10000)

    val insertResPro0 = ProjectRepo.insert(Project(3,2022,"anujProject"))
    val res10 = insertResPro0.map{res => s"$res row inserted into project"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResPro = ProjectRepo.insert(Project(4,2021,"ramanProject"))
    val res11 = insertResPro.map{res => s"$res row inserted into project"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResPro1 = ProjectRepo.insert(Project(5,2023,"amanProject"))
    val res12 = insertResPro1.map{res => s"$res row inserted into project"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResPro2 = ProjectRepo.insert(Project(6,2024,"chamanProject"))
    val res13 = insertResPro2.map{res => s"$res row inserted into project"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res10.map(println(_))
    Thread.sleep(5000)
    res11.map(println(_))
    Thread.sleep(5000)
    res12.map(println(_))
    Thread.sleep(5000)
    res13.map(println(_))
    Thread.sleep(5000)

    DependentRepo.create
    Thread.sleep(10000)

    val insertResDep0 = DependentRepo.insert(Dependent(1,2022,"Some Relative","relation",Some(45)))
    val res20 = insertResDep0.map{res => s"$res row inserted into Dependent"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResDep1 = DependentRepo.insert(Dependent(2,2021,"Some Relative","relation",Some(45)))
    val res21 = insertResDep1.map{res => s"$res row inserted into Dependent"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResDep2 = DependentRepo.insert(Dependent(3,2023,"Some Relative","relation",Some(45)))
    val res22 = insertResDep2.map{res => s"$res row inserted into Dependent"}.recover{
      case ex:Throwable => ex.getMessage
    }
    val insertResDep3 = DependentRepo.insert(Dependent(4, 2024,"Some Relative","relation",Some(45)))
    val res23 = insertResDep3.map{res => s"$res row inserted into Dependent"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res20.map(println(_))
    Thread.sleep(5000)
    res21.map(println(_))
    Thread.sleep(5000)
    res22.map(println(_))
    Thread.sleep(5000)
    res23.map(println(_))
    Thread.sleep(5000)

    val deleteResDep = DependentRepo.delete(1)
    val res30 = deleteResDep.map{res => s"$res row deleted from dependent"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res30.map(println(_))
    Thread.sleep(5000)

    val deleteResPro = ProjectRepo.delete("anujProject")
    val res40 = deleteResPro.map{res => s"$res row deleted from project"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res40.map(println(_))
    Thread.sleep(5000)

    val deleteResEmp = EmployeeRepo.delete("raman")
    val res50 = deleteResEmp.map{res => s"$res row deleted from employee"}.recover{
      case ex:Throwable => ex.getMessage
    }

    res50.map(println(_))
    Thread.sleep(5000)

    val upsertResEmp = EmployeeRepo.upsert(Employee(2050,"Agraj",28))
    val res60 = upsertResEmp.map{res => s"$res row upserted into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res60.map(println(_))
    Thread.sleep(5000)

    val upsertResPro = ProjectRepo.upsert(Project(10,2050,"AgrajProject"))
    val res61 = upsertResPro.map{res => s"$res row upserted into project"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res61.map(println(_))
    Thread.sleep(5000)

    val upsertResDep = DependentRepo.upsert(Dependent(101,2050,"Dep Name","some relation",Some(35)))
    val res62 = upsertResDep.map{res => s"$res row upserted into department"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res62.map(println(_))
    Thread.sleep(5000)

    val updateResEmp = EmployeeRepo.update(2022,"Anuj Saxena")
    val res70 = updateResEmp.map{res => s"$res row updated into employee"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res70.map(println(_))
    Thread.sleep(5000)

    val updateResPro = ProjectRepo.update(4,"chamanlalProject")
    val res71 = updateResPro.map{res => s"$res row updated into Project"}.recover{
      case ex:Throwable => ex.getMessage
    }
    res71.map(println(_))
    Thread.sleep(5000)*/

  }

}
