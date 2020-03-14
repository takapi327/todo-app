package models

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import scala.concurrent.{Future, ExecutionContext}

@Singleton
// dbConfigProviderデータベースアクセスのための設定、ExecutionContextは非同期処理に必要必ず記述
class TaskRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class TaskTable(tag: Tag) extends Table[Task](tag, "task"){
    // columnは列情報などのインスタンスを返す
    def id     = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def tittle = column[String]("tittle")
    def text   = column[String]("text")
    def day    = column[String]("day")

    // <>は双方向マッピング、インスタンスに変換しあう
    def * = (id, tittle, text, day) <> ((Task.apply _).tupled, Task.unapply)
  }

  def list(): Future[Seq[Task]] = db.run{
    task.result
  }

  def create(tittle: String, text: String, day: String):Future[Int] = db.run(
    task += Task(0, tittle, text, day)
  )

  private val task = TableQuery[TaskTable]
}