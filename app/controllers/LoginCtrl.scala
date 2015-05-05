package controllers

import play.api.mvc.Action
import play.api.data.Forms._
import play.api.db.slick._
import play.api.Play.current
import play.api.data.Form
import models.myapp.Tables._
import models.myapp.Tables.profile.simple._
import com.lambdaworks.crypto.SCryptUtil
import helpers.{SessionClient, SessionServer}
/**
 * Created by zhangxingjie on 3/19/15.
 */
object LoginCtrl extends BaseController  with Secured {

  def index = Auth { implicit request=>
    Ok(views.html.index())
  }

  def show() = Action { implicit request =>
    Ok(views.html.login(""))
  }

  val loginForm  = Form(
    tuple(
      "account" -> text,
      "password" -> text
    )
  )
  def login = Action { implicit request =>
    loginForm.bindFromRequest().fold(
      error => BadRequest(views.html.login("服务器正在睡觉~~~")),
      req => {
        val userName = req._1
        val password = req._2
        DB.withSession { implicit session =>
          appUsers.filter(_.nickname === userName).firstOption.fold(
            BadRequest(views.html.login("账号不存在，亲要先注册哦"))
          ){ user =>
            if(!SCryptUtil.check(password, user.password)){
              BadRequest(views.html.login("账号或者密码不正确"))
            } else {
              val loginRequest = SessionClient.defineUUID(request, user.id.get)
              Redirect(routes.LoginCtrl.index()).withSession(loginRequest)
            }
          }
        }
      }
    )
  }

  val registerForm  = Form(
    tuple(
      "nick_name" -> text,
      "password_1" -> text,
      "password_2" -> text,
      "email" -> text,
      "gender" -> number,
      "birthday" -> date
    )
  )
  def registerIndex = Action { implicit request =>
    Ok(views.html.register())
  }

  def register = Action { implicit request =>
    registerForm.bindFromRequest().fold(
      error => Ok(jsonMsg(error)),
      req => {
        // 检查用户名是否存在
        DB.withSession { implicit session =>
          appUsers.filter(_.nickname === req._1).firstOption.fold {
            if(1 == appUsers.insert(AppUser(nickname = req._1,
              email = req._4,
              phone = None,
              password = req._2,
              birthday = now,
              sex = req._5,
              location = req._1,
              createdAt = now,
              updatedAt = now))){
              Ok(jsonOk)
            } else {
              Ok(jsonMsg("Fail", "注册失败，请重新注册"))
            }
          }{ re =>
            Ok(jsonMsg("Fail", "用户已经存在"))
          }
        }
      }
    )
  }
}
