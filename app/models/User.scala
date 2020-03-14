package models

import play.api.data.Form
import play.api.data.Forms._

object User {
  val userForm: Form[UserForm] = Form {
    mapping(
      "name" -> nonEmptyText.verifying(error="3文字以上に。", constraint=_.length >= 3).verifying(error="10文字以内に。", constraint=_.length <= 10),
      "mail" -> text.verifying(error="メールアドレスを入力。", constraint=_.matches("""([a-zA-Z0-9\.\_-]+)@([a-zA-Z0-9\.\_-]+)""")),
      "pass" -> nonEmptyText.verifying(error="半角の数値とハイフンのみ入力可。", constraint=_.matches("""[1-9-]+"""))
    )(UserForm.apply)(UserForm.unapply)
  }

  val loginUserForm: Form[LoginUser] = Form( 
    mapping(
      "name" -> text.verifying("ユーザNAMEを入力してください" , {!_.isEmpty()}),
      "pass" -> text.verifying("パスワードを入力してください" , {!_.isEmpty()})
    )(LoginUser.apply)(LoginUser.unapply) 
  )
}

case class User(id: Int, name: String, mail: String, pass: String)
case class UserForm(name: String, mail: String, pass: String)
case class LoginUser(name: String, pass: String)