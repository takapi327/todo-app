package controllers

import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.{ExecutionContext, Future}

@Singleton
// TaskRepositoryの記述でデータベースアクセス、MessagesControllerComponentsFormを扱えるようにする、ExecutionContextは非同期の処理、MessagesAbstractController(cc)エラーメッセージに対応
class TaskController @Inject()(repository: TaskRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

   // トップページ表示
  def index() = Action.async { implicit request => 
    repository.list().map { task =>
      Ok(views.html.task.index(task))
    }
  }

  // 新規投稿画面に推移
  def add() = Action { implicit request =>
    Ok(views.html.task.add(Task.taskForm))
  }

  def create() = Action.async { implicit request =>
    Task.taskForm.bindFromRequest.fold(
      taskerror => {
        Future.successful(Ok(views.html.task.add(taskerror)))
      },
      task => {
        repository.create(task.tittle, task.text, task.day).map { _ =>
          Redirect(routes.TaskController.index)
        }
      }
    )
  }
}
