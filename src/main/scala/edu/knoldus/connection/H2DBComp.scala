package edu.knoldus.connection

import java.util.UUID

import slick.jdbc.H2Profile

trait H2DBComp extends DBComp{

  val driver = H2Profile

  import driver.api.Database

  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~                  ~~~~~~~~~~~~~~~~~~~~~~~\n\n")
  val randomDB: String = "jdbc:h2:mem:test" + UUID.randomUUID().toString + ";"
  val h2Url: String = randomDB + "MODE=MySql;DATABASE_TO_UPPER=false;INIT=RUNSCRIPT FROM 'src/test/resources/schema.sql'\\;RUNSCRIPT FROM 'src/test/resources/initialData.sql'"
  println(s"\n\n~~~~~~~~~~~~~~~~~~~~~             $h2Url         ~~~~~~~~~~~~~~~~~~~~~~~\n\n")
  val db: Database = Database.forURL(url = h2Url, driver = "org.h2.Driver")

}
