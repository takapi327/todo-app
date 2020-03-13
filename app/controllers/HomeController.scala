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


 val loginUserForm: Form[LoginUser] = Form( 
      mapping(
        "name" -> text.verifying("ユーザNAMEを入力してください" , {!_.isEmpty()}),
        "pass" -> text.verifying("パスワードを入力してください" , {!_.isEmpty()})
      )(LoginUser.apply)(LoginUser.unapply) 
    )
  // トップページ表示
  def index() = Action.async { implicit request => 
    repository.list().map {user =>
      Ok(views.html.index(user))
    }
  }

  // 新規登録画面に推移
  def sign() = Action { implicit request =>
    Ok(views.html.sign(User.userForm))
  }

  // 新規user登録
  def create() = Action.async { implicit request =>
    User.userForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.sign(errorForm)))
      },
      user => {
        repository.create(user.name, user.mail, user.pass).map { _ =>
          Redirect(routes.HomeController.index)
        }
      }
    )
  }

  // ログイン画面に推移
   def log() = Action { implicit request =>
    Ok(views.html.login(loginUserForm))
  }

  def login() = Action.async {implicit request: Request[AnyContent] =>
    loginUserForm.bindFromRequest.fold(
      errors => {
        // Future.successful(Ok(views.html.login(errors)))
        // BadRequest(views.html.login(errors))
        Ok(views.html.login(errors))
      },
      success => {
        val loginUser = loginUserForm.bindFromRequest.get
        Ok(views.html.loginSuccess())
      }
    )
  }
}