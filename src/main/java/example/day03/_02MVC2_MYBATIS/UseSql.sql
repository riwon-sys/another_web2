drop database if exists springweb2;
     create database springweb2;
            use springweb2;
                create table user(
                    id int auto_increment,
                    name varchar(100),
                    age int,
                    constraint primary key(id)
                );