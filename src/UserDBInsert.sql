insert into U00002_UserStatus( statusId, statusDescrib )
values (-2, '刪除'), 
	   (-1,'封鎖'),
	   (0,'未驗證'),
	   (1,'正常')

insert into U00001_Users(userId, username, useraccount, password, Email, status , contactNum, birthday)
values('2406141234','Gary','Gary1234' , '123456', 'leo312654@gmail.com',1 ,'0900000000','1984-06-06'),
	('2406144578', 'leo','leo1234', '123456', 'leo312654@gmail.com',1 ,'0987705857','1984-04-02'),
	('2406145678', 'Ben','Ben1234', '123456', 'Ben@test.com',1 ,'0987654321','1970-01-05'),
	('2406144567', 'Ken','Ken2145', '123456', 'Ken@test.com',1 , '0912345678','1997-10-06'),
	('2406141111', 'David','David4589', '123456', 'David@test.com',1 , '0923456781','1988-08-06'),
	('2406148971', 'Yaris','Yaris1456', '123456', 'Yaris@test.com',1 , '0934567812','2002-05-04'),
	('2406147615', 'Susan','Susan2442', '123456', 'Susan@test.com',1 , '0945678123','1990-02-24'),
	('2406141236', 'Mary1','Mary456', '123456', 'Mary@test.com',1 , '0956781234','1995-12-14'),
	('2407031245', 'Amber','Amber1456', '123456', 'Amber@test.com',1 , '0967812345','1975-09-15'),
	('2406143458', 'Josh','Josh1456', '123456', 'Josh@test.com',1 , '0978123456','1993-10-02'),
	('2407033414', 'admin','admin', '123456', 'admin@test.com',-2 , '0912345671','1993-10-02')


Create View U00001_Users_V AS
 Select 
		u.userId,
		u.userName, 
		u.userAccount,
		u.Email,
		u.contactNum,
		s.statusDescrib,
		u.createTime,
		u.prevlogTime
	from U00001_Users u JOIN U00002_UserStatus s ON u.status = s.statusId
	Where s.statusId <> -2