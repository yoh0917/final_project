--admin--------------------
drop table if exists U00003_Admin
create table U00003_Admin
(
	adminId  int identity(1,1) primary key,
	adminName nvarchar(20) not null,
	adminAccount nvarchar(20) not null,
	adminPassword nvarchar(50) not null,
	role nvarchar(20)
)

Truncate Table U00001_Users;

insert into U00003_Admin(adminName, adminAccount,adminPassword, role)
	values('admin','admin','admin','dbo')
select * from  U00003_Admin

-- User Table ----------------------------------------------------
drop table if exists U00002_UserStatus
create table U00002_UserStatus(
	statusId int primary key,
	statusDescrib nvarchar(10)
)

insert into U00002_UserStatus( statusId, statusDescrib )
values (-2, '刪除'), 
	   (-1,'封鎖'),
	   (0,'未驗證'),
	   (1,'正常')

select * from U00002_UserStatus

drop table if exists U00001_Users;
create table U00001_Users(
	userId nvarchar(50) not null primary key,
	username nvarchar(20),
	useraccount nvarchar(50) unique,
	password nvarchar(50) not null,
	Email nvarchar(50) not null,
	contactNum nvarchar(10) not null unique constraint CK_contactNum check ( LEFT(contactNum, 2) = '09' AND LEN(contactNum) = 10), 
	birthday Date not null,
	status int default 0 not null,
	createTime Datetime2(0) not null default Getdate(),
	prevlogTime Datetime2(0),
	CONSTRAINT CK_EmailAddress_Format
	CHECK (
        Email LIKE '%_@_%._%' AND
        CHARINDEX(' ', Email) = 0 AND
        Email NOT LIKE '%@%@%' AND
        Email NOT LIKE '%..%' AND
        LEFT(Email, 1) NOT LIKE '%[^a-zA-Z0-9]%' AND
        RIGHT(Email, 1) NOT LIKE '%[^a-zA-Z0-9]%'
    ),
	foreign key(status) references U00002_userStatus(statusID) 
);

insert into U00001_Users(userId, userName, useraccount, password, email, status , contactNum, birthday)
values('2406140001','Gary','Gary1234' , '123456', 'leo312654@gmail.com',1 ,'0900000000','1984-06-06'),
	('2406140002', 'leo','leo1234', '123456', 'leo312654@gmail.com',1 ,'0987705857','1984-04-02'),
	('2406140003', 'Ben','Ben1234', '123456', 'Ben@test.com',1 ,'0987654321','1970-01-05'),
	('2406140004', 'Ken','Ken2145', '123456', 'Ken@test.com',1 , '0912345678','1997-10-06'),
	('2406140005', 'David','David4589', '123456', 'David@test.com',1 , '0923456781','1988-08-06'),
	('2406140006', 'Yaris','Yaris1456', '123456', 'Yaris@test.com',1 , '0934567812','2002-05-04'),
	('2406140007', 'Susan','Susan2442', '123456', 'Susan@test.com',1 , '0945678123','1990-02-24'),
	('2406140008', 'Mary1','Mary456', '123456', 'Mary@test.com',1 , '0956781234','1995-12-14'),
	('2407030009', 'Amber','Amber1456', '123456', 'Amber@test.com',1 , '0967812345','1975-09-15'),
	('2406140010', 'Josh','Josh1456', '123456', 'Josh@test.com',1 , '0978123456','1993-10-02'),
	('2407030001', 'admin','admin', '123456', 'admin@test.com',-2 , '0912345671','1993-10-02')

select * from U00001_users
select * from U00002_UserStatus
select * from  U00003_Admin

Create View U00001_Users_V AS
 Select 
		u.userId,
		u.userName, 
		u.useraccount,
		u.email,
		u.contactNum,
		s.statusDescrib,
		u.createTime,
		u.prevlogTime
	from U00001_Users u JOIN U00002_UserStatus s ON u.status = s.statusId
	Where s.statusId <> -2

select * from U00001_Users_V



insert into U00004_UserPhonePlanList(agreementDate, phoneNunber, planID, userId)
values
	('2024-07-15','0912345678',2,'2406140002'),
	('2024-07-21','0923456789',5,'2406140002'),
	('2024-07-08','0945678912',3,'2406140002'),
	('2024-07-01','0956789123',23,'2406140002'),
	('2024-06-25','0978912345',40,'2406140002'),
	('2024-07-15','0912345678',2,'2406140002'),
	('2024-07-21','0923456789',5,'2406140002'),
	('2024-07-08','0945678912',3,'2406140002'),
	('2024-07-01','0956789123',23,'2406140002'),
	('2024-06-25','0978912345',40,'2406140002'),



select * from U00001_users
select * from U00002_UserStatus
select * from  U00003_Admin