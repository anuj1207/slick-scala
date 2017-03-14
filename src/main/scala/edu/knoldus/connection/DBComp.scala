package edu.knoldus.connection

import slick.jdbc.JdbcProfile

trait DBComp {

  val driver: JdbcProfile

  import driver.api._

  val db: Database
}
