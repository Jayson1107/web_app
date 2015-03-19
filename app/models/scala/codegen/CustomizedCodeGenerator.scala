package  codegen

import codegen.Config._
import scala.reflect.runtime.currentMirror
import scala.slick.driver.JdbcProfile
import scala.slick.jdbc.meta.createModel
import scala.slick.{model => m}
import scala.slick.codegen.SourceCodeGenerator

/**
 * This customizes the Slick code generator. We only do simple name mappings.
 * For a more advanced example see https://github.com/cvogt/slick-presentation/tree/scala-exchange-2013
 */
object CustomizedCodeGenerator {
  def main(args: Array[String]) = {
    codegen.writeToFile(
      "scala.slick.driver.MySQLDriver",
      outputFolder,
      pkg,
      "Tables",
      "Tables.scala"
    )
  }

  val driver: JdbcProfile = {
    val module = currentMirror.staticModule(slickDriver)
    val reflectedModule = currentMirror.reflectModule(module)
    val driver = reflectedModule.instance.asInstanceOf[JdbcProfile]
    driver
  }
  val db = driver.simple.Database.forURL(url, user, password, driver = jdbcDriver)
  // filter out desired tables
  val tablePattern = """^[^_]+.*"""
  val model = db.withSession { implicit session =>
    val tables = driver.getTables.list.filter(t => t.name.name.matches(tablePattern))
    createModel(tables, driver)
  }

  val codegen = new SourceCodeGenerator(model) {
    // customize Scala entity name (case class, etc.)
    // dbTableName should be in the format of "xxxs"
    override def entityName = dbTableName => {
      val word = {
        if (dbTableName.endsWith("ses")) dbTableName.dropRight(2)                 //
        //        else if (dbTableName.endsWith("ies")) dbTableName.dropRight(3) + "y"      // cookies?
        else dbTableName.dropRight(1)
      }
      word.toLowerCase.toCamelCase
    }

    // customize Scala table name (table class, table values, .. )
    override def tableName = dbTableName => dbTableName.toLowerCase.toCamelCase

    override def Table = new Table(_) {
      table =>
      override def autoIncLastAsOption = true
      override def TableValue = new TableValue {
        override def rawName = super.rawName.uncapitalize
      }
    }

  }
}