package edu.knoldus.connection

import slick.jdbc.MySQLProfile

trait MySqlComp extends DBComp {

  val driver = MySQLProfile

  import driver.api._

  val db = Database.forConfig("mySqlDB")

}
