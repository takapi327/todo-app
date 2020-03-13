package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

@Singleton
// dbConfigProviderデータベースアクセスのための設定、ExecutionContextは非同期処理に必要必ず記述
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class UserTable(tag: Tag) extends Table[User](tag, "user"){
    // columnは列情報などのインスタンスを返す
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def mail = column[String]("mail")
    def pass = column[String]("pass")

    // <>は双方向マッピング、インスタンスに変換しあう
    def * = (id, name, mail, pass) <> ((User.apply _).tupled, User.unapply)
  }

  def list(): Future[Seq[User]] = db.run{
      user.result
  }

  def create(name: String, mail: String, pass: String):Future[Int] = db.run(
    user += User(0, name, mail, pass)
  )
  // 下記の記述からuserテーブルにあるレコードを取り出す
  private val user = TableQuery[UserTable]
}