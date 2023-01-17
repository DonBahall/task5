DROP DATABASE IF EXISTS task5;
create database task5;
use task5;

DROP TABLE IF EXISTS client;
create table client(
    client_id INTEGER primary key auto_increment ,
    firstname varchar(40)not null ,
    lastname varchar(40)not null ,
    password varchar(20)not null
);
DROP TABLE IF EXISTS tovar;
create table tovar(
    tovar_id INTEGER primary key auto_increment,
    client_id INTEGER null,
    name varchar(30) not null,
    price int not null,
    foreign key (client_id) references client(client_id)
);
INSERT INTO tovar(client_id, name, price) VALUES (null,'phone',30);
INSERT INTO tovar(client_id, name, price) VALUES (null,'desk',15);