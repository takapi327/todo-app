# ---!Ups

create table User(
  id into auto_increment primary key,
  name varchar(255) NOT NULL,
  mail varchar(255) NOT NULL
);

# ---!Downs

drop table User;