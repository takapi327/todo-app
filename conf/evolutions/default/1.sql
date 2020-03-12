# ---!Ups
create table user (
  id int auto_increment primary key,
  name varchar(255) not null,
  mail varchar(255) not null
);
insert into user values (default, 'test??', 'test@mail');

# ---!Downs
drop table user;