package controllers

import play.api.mvc.Action
/**
 * Created by zhangxingjie on 3/19/15.
 */
object LoginCtrl extends BaseController {

  def show = Action { request =>
    Ok(jsonOk)
  }

  def login = Action { request =>
    Ok(jsonOk)
  }
}
