package controllers

import play.api.mvc.Security._
import play.api.mvc._
import play.api.mvc.Results._
import helpers.SessionServer

trait Secured {
  private def username(request: RequestHeader): Option[Long] = {
    SessionServer.getLoginedUserId(request)
  }
  private def onUnauthorized(request: RequestHeader) = {
    Redirect(routes.LoginCtrl.login())
  }

  def Auth(f: => Request[AnyContent] => Result) = Authenticated(username, onUnauthorized){ implicit userId =>
      Action(implicit request => f(request))
  }
}
