package models.scala.models.com.models

import java.sql.Timestamp

import org.joda.time.DateTime

/**
 * Created by zhangxingjie on 3/19/15.
 */
trait Implicits {
  implicit def dateTime2Timestamp(datetime: DateTime): Timestamp = new Timestamp(datetime.getMillis)
  implicit def timestamp2Datetime(timestamp: Timestamp): DateTime = new DateTime(timestamp)
  implicit def dateTime2TimestampOpt(datetime: Option[DateTime]): Option[Timestamp] = datetime.map(dt => new Timestamp(dt.getMillis))
  implicit def timestamp2DatetimeOpt(timestamp: Option[Timestamp]): Option[DateTime] = timestamp.map(new DateTime(_))
}
object Implicits extends Implicits