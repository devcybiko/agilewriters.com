create database agilewritersdb;

use agilewritersdb;

drop table if exists members;

create table members (
    id int PRIMARY KEY AUTO_INCREMENT,
    fname varchar(32),
    lname varchar(48),
    email varchar(128),
    password varchar(128),
    UNIQUE(email)
)

insert into members (fname, lname, email, password) values ("greg", "smith", "email", "password");

-- select * from members;
