package controllers

import java.sql.Timestamp
import play.api.mvc._
import com.typesafe.scalalogging.LazyLogging
/**
 * Created by zhangxingjie on 3/19/15.
 */
private [controllers] trait BaseController extends Controller with LazyLogging with JsonResponse  {

  protected def now = new Timestamp(System.currentTimeMillis())

//  case class AppUser(val id: Long, val nickName: String, status: String)

//  private def Auth(f: => AppUser => Request[AnyContent] => Result) = {
//
//
//  }
}
