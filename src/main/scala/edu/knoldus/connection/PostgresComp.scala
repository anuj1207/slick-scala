package edu.knoldus.connection

import slick.jdbc.PostgresProfile

trait PostgresComp extends DBComp {

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
