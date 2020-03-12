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
// UserRepositoryの記述でデータベースアクセス、MessagesControllerComponentsFormを扱えるようにする、ExecutionContextは非同期の処理、MessagesAbstractController(cc)エラーメッセージに対応
class HomeController @Inject()(repository: UserRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {

  def index() = Action.async { implicit request => 
    repository.list().map {user =>
      Ok(views.html.index(user))
    }
  }
}