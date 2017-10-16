GRANT ALL PRIVILEGES ON devcamp.* TO javauser@localhost
 IDENTIFIED BY 'javadude' WITH GRANT OPTION;
drop database devcamp;
create database devcamp;

create table devcamp.photogallery (
  id int not null auto_increment primary key,
  writer varchar(50),
  title varchar(100),
  content text,
  filename VARCHAR (255),
  saveName VARCHAR (20),
  fileSize int);

insert into devcamp.photogallery (writer, title, content) values ('kenu', 'kenu.heo@gmail.com', 'content');


GRANT ALL PRIVILEGES ON javatest.* TO javauser@localhost
 IDENTIFIED BY 'javadude' WITH GRANT OPTION;

drop database javatest;

create database javatest;

create table javatest.photogallery (
  id int not null auto_increment primary key,
  writer varchar(50),
  title varchar(100),
  content text,
  filename VARCHAR (255),
  saveName VARCHAR (20),
  fileSize int);

insert into javatest.photogallery (writer, title, content) values ('kenu', 'kenu.heo@gmail.com', 'content');
