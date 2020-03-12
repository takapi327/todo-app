package models

import play.api.data.Form
import play.api.data.Forms._

object User {
  val userForm: Form[UserForm] = Form {
    mapping(
      "name" -> text,
      "mail" -> text
    )(UserForm.apply)(UserForm.unapply)
  }
}

case class User(id: Int, name: String, mail: String)
case class UserForm(name: String, mail: String)