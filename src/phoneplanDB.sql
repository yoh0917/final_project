
CREATE TABLE F00001_PhonePlans 
(
    PlanID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    PlanName NVARCHAR(10) NOT NULL,
	PhoneNumber NVARCHAR(20) ,
    TelCompany NVARCHAR(25) NOT NULL,
    ContractType NVARCHAR(25) NOT NULL,
    ContractDuration NVARCHAR(15) NOT NULL,
    Generation NVARCHAR(10) NOT NULL,
    DataUsage NVARCHAR(10) NOT NULL,
    DataTransferRate NVARCHAR(50) NOT NULL,
    IntraNetCall NVARCHAR(50) NOT NULL,
    InterNetCall NVARCHAR(50) NOT NULL,
    LocalCall NVARCHAR(50) NOT NULL,
    Discount NVARCHAR(MAX),
    Gift NVARCHAR(MAX)
);

select *from F00001_PhonePlans 


select *from U00001_Users


Drop TABLE U00001_Users



SELECT * FROM F00001_PhonePlans
WHERE PlanID=1
Drop TABLE  F00001_PhonePlans

ALTER DATABASE [FORUM]  SET SINGLE_USER;
ALTER DATABASE [FORUM]  COLLATE Chinese_Taiwan_Stroke_CS_AS
ALTER DATABASE [FORUM]  SET MULTI_USER;
--CREATE FUNCTION SetPlanID



INSERT INTO F00001_PhonePlans (PlanName, PhoneNumber, TelCompany, ContractType, ContractDuration, Generation, DataUsage, DataTransferRate, IntraNetCall, InterNetCall, LocalCall, Discount)
VALUES 
('388', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '新申辦', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '新申辦', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '新申辦', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '台灣大哥大', '新申辦', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '續約', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '續約', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '續約', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '續約', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '續約', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '續約', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '台灣大哥大', '續約', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '台灣大哥大', '續約', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '台灣大哥大', '續約', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '攜碼', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '台灣大哥大', '攜碼', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '新申辦', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '新申辦', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '新申辦', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '新申辦', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '新申辦', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '新申辦', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '中華電信', '新申辦', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '中華電信', '新申辦', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '中華電信', '新申辦', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '攜碼', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '攜碼', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '攜碼', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '攜碼', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '攜碼', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '攜碼', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '中華電信', '攜碼', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '中華電信', '攜碼', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '中華電信', '攜碼', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '續約', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '續約', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '續約', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '中華電信', '續約', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '中華電信', '續約', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '中華電信', '續約', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '中華電信', '續約', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('999', NULL, '中華電信', '續約', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('1399', NULL, '中華電信', '續約', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '遠傳', '新申辦', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '遠傳', '新申辦', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '遠傳', '新申辦', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('388', NULL, '遠傳', '新申辦', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('499', NULL, '遠傳', '新申辦', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('599', NULL, '遠傳', '新申辦', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
('799', NULL, '遠傳', '新申辦', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(999, NULL, '遠傳', '新申辦', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(1399, NULL, '遠傳', '新申辦', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(388, NULL, '遠傳', '攜碼', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(499, NULL, '遠傳', '攜碼', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(599, NULL, '遠傳', '攜碼', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(388, NULL, '遠傳', '攜碼', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(499, NULL, '遠傳', '攜碼', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(599, NULL, '遠傳', '攜碼', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(799, NULL, '遠傳', '攜碼', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(999, NULL, '遠傳', '攜碼', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(1399, NULL, '遠傳', '攜碼', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(388, NULL, '遠傳', '續約', '30個月', '4G', '6GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(499, NULL, '遠傳', '續約', '30個月', '4G', '12GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(599, NULL, '遠傳', '續約', '30個月', '4G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(388, NULL, '遠傳', '續約', '30個月', '5G', '5GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(499, NULL, '遠傳', '續約', '30個月', '5G', '10GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(599, NULL, '遠傳', '續約', '30個月', '5G', '20GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(799, NULL, '遠傳', '續約', '30個月', '5G', '40GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(999, NULL, '遠傳', '續約', '30個月', '5G', '60GB', '量到降速', '網內免費', '網外30分鐘', '每分鐘1元', '3000'),
(1399, NULL, '遠傳', '續約', '30個月', '5G', '無限制', '無線飆網', '網內免費', '網外30分鐘', '每分鐘1元', '3000');




ALTER TABLE F00001_PhonePlans
DROP COLUMN userID;

ALTER TABLE F00001_PhonePlans
ADD userID INT IDENTITY(1,1) PRIMARY KEY;

INSERT INTO U00001_Users (userID, birthday, contactNum, createTime, Email, password, prevlogTime, status, useraccount, username)
VALUES ('1', '1998-01-01', '0973', '2023-07-04 12:00:00', 'test@example.com', 'password', '2023-07-04 12:00:00', 1, 'testuser', 'Test User');