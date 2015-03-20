package controllers

import play.api.data.Form
import play.api.i18n.Messages
import play.api.libs.json.{JsValue, Json, Writes}
/**
 * Created by zhangxingjie on 3/19/15.
 */
trait JsonResponse {

  val statusKey = "status"

  val msgKey = "msg"

  protected def jsonOk = jsonMsg("ok", "ok")

  protected def jsonMsg(status: String, msg: String) = Json.obj(
    statusKey -> status,
    msgKey -> Messages(msg)
  )

  protected def jsonMsg(formWithErrors: Form[_]): JsValue = {
    jsonMsg("bad_request", formWithErrors.errors.headOption.fold("提交的数据有误")( err => {
      Messages(err.message) + s" (${err.key})"
    }))
  }

  protected def jsonOk[T](data: T, msg: String = "ok")(implicit tjs: Writes[T]) = jsonMsg("ok", msg, data)

  protected def jsonMsg[T](status: String, msg: String, data: T)(implicit tjs: Writes[T]) = Json.obj(
    statusKey -> status,
    msgKey -> Messages(msg),
    "data" -> tjs.writes(data)
  )

}
