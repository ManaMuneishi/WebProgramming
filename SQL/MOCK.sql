CREATE TABLE user(
id SERIAL,
login_id varchar(255) UNIQUE NOT NULL,
name varchar(255) NOT NULL,
birth_date DATE NOT NULL,
password varchar(255) NOT NULL,
create_date DATETIME NOT NULL,
update_date DATETIME NOT NULL)

INSERT INTO user VALUES(null,'admin','管理者','1642/5/5','ppp','2015-12-08 12:00:00.317','2015-12-08 12:00:00.317');
INSERT INTO user VALUES(null,'mm','山田太郎','1999/5/5','ppa','2015-12-08 12:00:00.317','2015-12-08 12:00:00.317');
