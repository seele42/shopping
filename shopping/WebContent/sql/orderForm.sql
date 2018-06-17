-- orderForm¶©µ¥±í --

create table orderForm 
(
     id int(10) primary key auto_increment,
     username varchar(255) not null,
     orderDate timestamp not null default current_timestamp,
     commodity_name varchar(255) not null,
     commodity_price float(10,2) not null,
     sum int(10) 
);


