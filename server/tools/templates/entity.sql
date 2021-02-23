create database {database};

use {database};

drop table if exists {names};

create table {names} (
    id int PRIMARY KEY AUTO_INCREMENT,
    {body}
)

insert into {names} ({cols}) values ({values});

select * from {names};
