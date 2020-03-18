package models

import play.api.data.Form
import play.api.data.Forms._

object Buy{
  val buyForm: Form[BuyForm] = Form{
    mapping(
      "product" -> nonEmptyText,
      "quantity" -> nonEmptyText,
      "price" -> nonEmptyText
    )(BuyForm.apply)(BuyForm.unapply)
  }
}

case class Buy(id: Int, product: String, quantity: String, price: String)
case class BuyForm(product: String, quantity: String, price: String)