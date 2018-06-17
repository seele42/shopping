--vip表 用于储存用户信息--

create table vip
(
       username varchar(255) primary key,
       userpass varchar(255) not null,
       phone    varchar(255),
       address  varchar(255),
       realname varchar(255) 
);
