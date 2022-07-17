use master
drop database coffee
create database coffee
use coffee
create table category(
 id int identity(1,1) primary key,
 name nvarchar(100) not null,
 mausac varchar(100) 
)
go
UPDATE category SET name ='capuchino' , mausac='#ffffff' WHERE id=5
select * from category
create table product(
 id int identity(1,1) primary key,
 name nvarchar(100) not null,
cat_id int foreign key references category(id),
price float not null,
create_at DateTime DEFAULT GETDATE(),
[status] int DEFAULT 1
UPDATE product SET name ='trhtrh' ,cat_id='2', price=22,status=0,create_at=GETDATE() WHERE id=1
SELECT *,FORMAT( create_at, 'dd-MM-yyyy hh:mm:ss tt') AS 'create' from product
SELECT * FROM category WHERE name LIKE '%cafe%'
INSERT INTO product(name,cat_id,price,status) VALUES('tggt',11,30,1)
go
create table coffee_table(
id int identity(1,1) primary key,
name nvarchar(100) not null,
[status] int
)
go
select * from coffee_table
UPDATE coffee_table SET name = 'bàn10',status='0' where id =1
create table [admin](
id int identity(1,1) primary key,
username varchar(30),
[password] varchar(30)
)
go
select * from [admin]
drop table employee
create table employee(
id int identity(1,1) primary key,
name nvarchar(255) not null,
username varchar(30) not null,
[password] varchar(30) not null,
gender bit not null,
[card] bigint not null,
birthday date,
phone varchar(30),
email varchar(100),
[address] ntext

)
go
select * from employee
INSERT INTO employee VALUES('chien','nhanvien1','123456',1,98765432123454,'2000-01-01','0988005510','chien123@gamil.com',N'hà nội')
UPDATE employee SET name = ?,username=?,password=?,gender=?,card=?,birthday=?,phone=?,email=?,address=? where id =1
create table [order](
id int identity(1,1) primary key,
discount float,
table_id int foreign key references coffee_table(id),
emp_id int Foreign key references employee(id),
create_at datetime,
total_price float,
[status] int
)
go
SELECT
     coffee_table."id" AS coffee_table_id,
     coffee_table."name" AS coffee_table_name,
     [order]."id" AS order_id,
     [order]."discount" AS order_discount,
     [order]."create_at" AS order_create_at,
     [order]."total_price" AS order_total_price,
     order_details."id" AS order_details_id,
     order_details."order_id" AS order_details_order_id,
     order_details."pro_id" AS order_details_pro_id,
     order_details."quantity" AS order_details_quantity,
     order_details."price" AS order_details_price,
     product."name" AS product_name,
     product."id" AS product_id
FROM
     "dbo"."coffee_table" coffee_table INNER JOIN "dbo"."[order]" [order]ON coffee_table."id" = [order]."table_id"
     INNER JOIN "dbo"."order_details" order_details ON [order]."id" = order_details."order_id"
     INNER JOIN "dbo"."product" product ON order_details."pro_id" = product."id"
WHERE
     [order]."status" =1 and  [order]."id" =$P{[order].id}
select *,FORMAT( create_at, 'dd-MM-yyyy hh:mm:ss tt') AS 'create' from [order]
insert into [order](table_id,create_at,status) values(1,'2000-10-10',0)
create table order_details(
id int identity(1,1) primary key,
order_id int foreign key references [order](id),
pro_id int foreign key references [product](id),
quantity int ,
price float
)
go
select * from order_details 
delete [order]
delete order_details
Select ct.pro_id,order_id, quantity,name, ct.price From order_details AS ct INNER JOIN product AS td ON ct.pro_id = td.id  INNER JOIN [order] AS hd ON hd.id = ct.order_id Where  hd.create_at >='2020-01-08' AND hd.create_at <='2020-01-9 +'
Select * From [order] AS hd INNER JOIN  order_details AS ct ON ct.order_id = hd.id Where table_id = 1 AND ct.order_id = 1 AND status = 0
create table customer(
id int identity(1,1) primary key,
name nvarchar(255) not null,
table_id int Foreign key references coffee_table(id),
phone varchar(30),
email varchar(100),
create_at datetime default getdate(),
quantity int
[status] int 
)

select * from customer
DELETE FROM customer WHERE id =15 and status =0 or status =2
SELECT name, id FROM product where id in (Select pro_id From order_details)
SELECT *,FORMAT(create_at, 'HH') AS 'gio',FORMAT(create_at, 'm') AS 'phut' FROM customer
select * from coffee_table where status =0 union select * from coffee_table where id=1
Select ct.price, ct.quantity, td.name From order_details AS ct INNER JOIN [order] AS hd ON ct.order_id = hd.id INNER JOIN product AS td ON td.id = ct.pro_id Where hd.status = 1 AND ct.pro_id = 1