package repositories

import edu.knoldus.connection.H2DBComp
import edu.knoldus.repositories.{Dependent, DependentRepo}
import org.scalatest.AsyncFunSuite

class DependentSpec extends AsyncFunSuite with DependentRepo with H2DBComp{

  test ("getall dependents") {
    getAll.map(res => assert(res.size === 3))
  }

  test("get dependents with employee"){
    getDependentWithEmployeeName.map(res =>
      assert(res === List(("Anuj1","Anuj Saxena"),("Anuj2","Anuj"),("Anuj3","Anuj")))
    )
  }

  test ("Insert one dependent") {
    insert(Dependent(4,1,"Anuj4","brother",Some(26))).map(res=> assert(res === 1))
  }

  test("Delete one dependent") {
    delete(1).map(res => assert(res == 1))
  }

  test ("Update name of dependent") {
    updateName(2, "ashish saxena").map(res => assert(res == 1))
  }

  test ("upsert a dependent") {
    upsert(Dependent(1,1,"swati","sister",Some(28))).map(res=> assert(res == 1))
  }

  test ("insert then update") {
    insertThenUpdate(Dependent(5,2,"Agam","bro",Some(29)),"Agam Bhardwaj")
      .map(res => assert(res == 1))
  }

  /*test("get dependents with employee again"){
    getDependentWithEmployeeName.map(res =>
      assert(res === List(("Anuj1","Anuj Saxena"),("Anuj2","Anuj"),("Anuj3","Anuj")))
    )
  }*/

  test("insert dependent using PSQL "){
    insertPSQL(Dependent(9,2,"Agam","bro",Some(29))).map(res => assert(res === 1))
  }

  /*test("get max age from dependents "){

  }*/

}
