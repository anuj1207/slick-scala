package repositories
import edu.knoldus.connection.H2DBComp
import edu.knoldus.repositories.{Employee, EmployeeRepo}
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite with EmployeeRepo with H2DBComp{

  test("Insert a record in employee 1") {
    insert(Employee(3,"unknown",24)).map(res => assert(res === 1))
  }

}
