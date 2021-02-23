create database {database};

use {database};

drop table if exists members;

create table members (
    id int PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR (64),
    lname VARCHAR (64),
    email VARCHAR (128),
    password VARCHAR (128),
    UNIQUE(email)
)

insert into members (fname,lname,email,password) values ({values});

select * from members;
