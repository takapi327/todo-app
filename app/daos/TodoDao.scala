// DAOクラスを使用 DAOに関しては右記参照(https://techacademy.jp/magazine/19443)
package daos

import javax.inject.Inject
import models.Todo
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TodoDao @Inject() (protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContent: ExecutionContext) extends HasDatabaseConfigProvider[JdbcProfile]{
  import driver.api._

  // 検索
  private all(): Future[Seq[Todo]] = db.run(Todo.result)

  // 登録
  def insert(todo: Todo): Future[Unit] = {
    val todos = Todos returning Todos.map(_.id) into ((todo, id) => todo.copy(id = id)) += todo
    db.run(todos.transactionaly).map(_ =>())
  }

  // 更新
  def update(todo: Todo): Future[Unit] = {
    db.run(Todo.filter(_.id === todo.id).map(_.content).update(todo.content)).map(_ => ())
  }

  // 削除
  def delete(todo: Todo): Future[Unit] = {
    deb.run(Todo.filter(_.id === todo.id).delete).map(_ => ())
  }

  // マッピング
  private class TodoTable(tag: Tag)extends Table[Todo](tag, "TODO"){
    def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
    def content = column[String]("CONTENT")
    def * = (id, content) <> (Todo.tupled, Todo.unapply _)
  }
  
}