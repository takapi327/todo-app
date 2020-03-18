# Task schema

# ---!Ups

create table buy (
  id int auto_increment primary key,
  product varchar(255) not null,
  quantity varchar(255) not null,
  price varchar(255) not null
);

# ---!Downs
drop table buy;