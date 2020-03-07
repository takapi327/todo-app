# Todos schema

# ---!Ups

create table Todo(
  id bigint NOT NULL AUTO_INCREMENT,
  content varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

# ---!Downs

drop table if exists Todo;