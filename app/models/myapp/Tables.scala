package models.myapp
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}
  
  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = appUsers.ddl
  
  /** Entity class storing rows of table appUsers
   *  @param nickname Database column nickname DBType(VARCHAR), Length(20,true)
   *  @param email Database column email DBType(VARCHAR), Length(50,true)
   *  @param phone Database column phone DBType(VARCHAR), Length(20,true), Default(None)
   *  @param password Database column password DBType(VARCHAR), Length(100,true)
   *  @param status Database column status DBType(VARCHAR), Length(20,true), Default(CREATED)
   *  @param birthday Database column birthday DBType(DATETIME)
   *  @param sex Database column sex DBType(INT)
   *  @param location Database column location DBType(VARCHAR), Length(100,true)
   *  @param updatedAt Database column updated_at DBType(DATETIME)
   *  @param createdAt Database column created_at DBType(DATETIME)
   *  @param id Database column id DBType(BIGINT), AutoInc, PrimaryKey */
  case class AppUser(nickname: String, email: String, phone: Option[String] = None, password: String, status: String = "CREATED", birthday: java.sql.Timestamp, sex: Int, location: String, updatedAt: java.sql.Timestamp, createdAt: java.sql.Timestamp, id: Option[Long] = None)
  /** GetResult implicit for fetching AppUser objects using plain SQL queries */
  implicit def GetResultAppUser(implicit e0: GR[String], e1: GR[Option[String]], e2: GR[java.sql.Timestamp], e3: GR[Int], e4: GR[Option[Long]]): GR[AppUser] = GR{
    prs => import prs._
    val r = (<<?[Long], <<[String], <<[String], <<?[String], <<[String], <<[String], <<[java.sql.Timestamp], <<[Int], <<[String], <<[java.sql.Timestamp], <<[java.sql.Timestamp])
    import r._
    AppUser.tupled((_2, _3, _4, _5, _6, _7, _8, _9, _10, _11, _1)) // putting AutoInc last
  }
  /** Table description of table app_users. Objects of this class serve as prototypes for rows in queries. */
  class AppUsers(_tableTag: Tag) extends Table[AppUser](_tableTag, "app_users") {
    def * = (nickname, email, phone, password, status, birthday, sex, location, updatedAt, createdAt, id.?) <> (AppUser.tupled, AppUser.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (nickname.?, email.?, phone, password.?, status.?, birthday.?, sex.?, location.?, updatedAt.?, createdAt.?, id.?).shaped.<>({r=>import r._; _1.map(_=> AppUser.tupled((_1.get, _2.get, _3, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get, _10.get, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))
    
    /** Database column nickname DBType(VARCHAR), Length(20,true) */
    val nickname: Column[String] = column[String]("nickname", O.Length(20,varying=true))
    /** Database column email DBType(VARCHAR), Length(50,true) */
    val email: Column[String] = column[String]("email", O.Length(50,varying=true))
    /** Database column phone DBType(VARCHAR), Length(20,true), Default(None) */
    val phone: Column[Option[String]] = column[Option[String]]("phone", O.Length(20,varying=true), O.Default(None))
    /** Database column password DBType(VARCHAR), Length(100,true) */
    val password: Column[String] = column[String]("password", O.Length(100,varying=true))
    /** Database column status DBType(VARCHAR), Length(20,true), Default(CREATED) */
    val status: Column[String] = column[String]("status", O.Length(20,varying=true), O.Default("CREATED"))
    /** Database column birthday DBType(DATETIME) */
    val birthday: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("birthday")
    /** Database column sex DBType(INT) */
    val sex: Column[Int] = column[Int]("sex")
    /** Database column location DBType(VARCHAR), Length(100,true) */
    val location: Column[String] = column[String]("location", O.Length(100,varying=true))
    /** Database column updated_at DBType(DATETIME) */
    val updatedAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_at")
    /** Database column created_at DBType(DATETIME) */
    val createdAt: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("created_at")
    /** Database column id DBType(BIGINT), AutoInc, PrimaryKey */
    val id: Column[Long] = column[Long]("id", O.AutoInc, O.PrimaryKey)
    
    /** Uniqueness Index over (nickname) (database name nickname) */
    val index1 = index("nickname", nickname, unique=true)
  }
  /** Collection-like TableQuery object for table appUsers */
  lazy val appUsers = new TableQuery(tag => new AppUsers(tag))
}