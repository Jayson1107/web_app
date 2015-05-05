package helpers

import play.api.cache.Cache
import play.api.Play.current
import play.api.mvc.RequestHeader
/**
 * Created by zhangxingjie on 4/16/15.
 */
object SessionServer {

  private val LoginUserId                   = "MYAPP_LOGINED_"

  private val seconds_1day                  = 60*60*24
  private val ExpireLoginUserId             = seconds_1day        // 登录有效期24小时

  def setLoginedUserId(uuid: String, userId: Long) = {
    Cache.set(LoginUserId + uuid, userId, ExpireLoginUserId)
  }
  def delLoginedUserId(uuid: String) = {
    Cache.remove(LoginUserId + uuid)
  }
  def getLoginedUserId(uuid: String):Option[Long] = {
    Cache.getAs[Long](LoginUserId + uuid)
  }
  def getLoginedUserId(request: RequestHeader):Option[Long] = {
    SessionClient.getUUID(request) match {
      case Some(uuid) => getLoginedUserId(uuid)
      case None => None
    }
    //    Some(1)
  }
}

object SessionClient {
  val UUID = "APP_UUIID"

  def getUUID(request: RequestHeader) = {
    request.session.get(UUID)
  }

  def defineUUID(request: RequestHeader, userId: Long) = {
    val uuId = scala.util.Random.alphanumeric.take(10).mkString

    SessionServer.setLoginedUserId(uuId, userId)
    request.session + (UUID -> uuId)

  }

  def destroyUUID(request: RequestHeader) = {
    getUUID(request).fold(){ uuid=>
      SessionServer.delLoginedUserId(uuid)
    }
    request.session - UUID
  }
}
