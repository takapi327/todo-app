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
class BuyController @Inject()(repository: BuyRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc){

  def index() = Action.async { implicit request =>
    repository.list().map { buy =>
      Ok(views.html.buy.index(buy, Buy.buyForm))
    }
  }

  def create() = Action.async { implicit request =>
    Buy.buyForm.bindFromRequest.fold(
      buyerror => {
        Future.successful(Ok(views.html.buy.add(buyerror)))
      },
      buy => {
        repository.create(buy.product, buy.quantity, buy.price).map { _ =>
          Redirect(routes.BuyController.index)
        }
      }
    )
  }
}