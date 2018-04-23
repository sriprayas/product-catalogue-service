create table PRODUCT
(
   product_id INT not null IDENTITY(1,1),
   product_name varchar(255) not null,
   product_type varchar(255) not null,
   product_rate FLOAT(8) not null,
   product_description varchar(255) not null,
   product_date DATE,
   product_available BOOLEAN not null,
   primary key(product_id)
);