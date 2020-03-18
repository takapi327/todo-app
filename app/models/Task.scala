package models

import play.api.data.Form
import play.api.data.Forms._

object Task {
  val taskForm: Form[TaskForm] = Form {
    mapping(
      "tittle" -> nonEmptyText,
      "text" -> nonEmptyText,
      "day" -> nonEmptyText
    )(TaskForm.apply)(TaskForm.unapply)
  }
}

case class Task(id: Int, tittle: String, text: String, day: String)
case class TaskForm(tittle: String, text: String, day: String)