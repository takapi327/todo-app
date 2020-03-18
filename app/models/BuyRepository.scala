package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

@Singleton
class BuyRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class BuyTable(tag: Tag) extends Table[Buy](tag, "buy"){
    def id       = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def product  = column[String]("product")
    def quantity = column[String]("quantity")
    def price    = column[String]("price")

    def * = (id, product, quantity, price) <> ((Buy.apply _).tupled, Buy.unapply)
  }

  def list(): Future[Seq[Buy]] = db.run {
    buy.result
  }

  def create(product: String, quantity: String, price: String):Future[Int] = db.run(
    buy += Buy(0, product, quantity, price)
  )

  private val buy = TableQuery[BuyTable]
}