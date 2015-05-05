from yoyo import step, transaction

transaction(
  step(
    """
    CREATE TABLE app_users (
      id bigint not null auto_increment,
      nickname varchar(20) not null,
      email varchar(50) not null,
      phone varchar(20),
      password varchar(100) not null,
      status varchar(20) not null default 'CREATED',
      birthday DATETIME  not null,
      sex INT  not null,
      location varchar(100) not null,

      updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id),
      unique key(nickname)
    );
    """,
    """
    DROP TABLE app_users;
    """
  )
)
