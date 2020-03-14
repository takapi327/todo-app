# Task schema

# ---!Ups

create table task (
  id int auto_increment primary key,
  tittle varchar(255) not null,
  text varchar(255) not null,
  day varchar(255) not null
);

# ---!Downs
drop table task;