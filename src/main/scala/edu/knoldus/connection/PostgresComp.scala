package edu.knoldus.connection

import slick.jdbc.PostgresProfile

trait PostgresComp extends DBComp {

  import driver.api._

  val driver = PostgresProfile

  val db = Database.forConfig("myPostgresDB")

}
