CREATE TABLE F00001_PhonePlans 
(
	PlanID INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	PlanName NVARCHAR(10) NOT NULL,
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


select *from U00004_UserPhonePlanList


Drop TABLE U00001_Users



SELECT * FROM F00001_PhonePlans
Drop TABLE  F00001_PhonePlans



INSERT INTO F00001_PhonePlans (PlanName,TelCompany,ContractType,ContractDuration,Generation,DataUsage,DataTransferRate,IntraNetCall,InterNetCall,LocalCall,discount) VALUES 
(388,'台灣大哥大','攜碼','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','攜碼','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','攜碼','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','攜碼','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','攜碼','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','攜碼','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'台灣大哥大','攜碼','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'台灣大哥大','攜碼','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'台灣大哥大','攜碼','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','新申辦','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','新申辦','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','新申辦','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','新申辦','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','新申辦','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','新申辦','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'台灣大哥大','新申辦','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'台灣大哥大','新申辦','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'台灣大哥大','新申辦','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','續約','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','續約','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','續約','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','續約','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','續約','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','續約','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'台灣大哥大','續約','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'台灣大哥大','續約','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'台灣大哥大','續約','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','攜碼','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','攜碼','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','攜碼','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'台灣大哥大','攜碼','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'台灣大哥大','攜碼','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'台灣大哥大','攜碼','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'台灣大哥大','攜碼','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'台灣大哥大','攜碼','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'台灣大哥大','攜碼','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','新申辦','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','新申辦','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','新申辦','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','新申辦','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','新申辦','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','新申辦','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'中華電信','新申辦','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'中華電信','新申辦','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'中華電信','新申辦','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','攜碼','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','攜碼','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','攜碼','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','攜碼','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','攜碼','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','攜碼','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'中華電信','攜碼','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'中華電信','攜碼','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'中華電信','攜碼','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','續約','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','續約','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','續約','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'中華電信','續約','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'中華電信','續約','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'中華電信','續約','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'中華電信','續約','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'中華電信','續約','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'中華電信','續約','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','新申辦','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','新申辦','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','新申辦','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','新申辦','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','新申辦','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','新申辦','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'遠傳','新申辦','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'遠傳','新申辦','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'遠傳','新申辦','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','攜碼','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','攜碼','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','攜碼','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','攜碼','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','攜碼','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','攜碼','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'遠傳','攜碼','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'遠傳','攜碼','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'遠傳','攜碼','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','續約','30個月','4G','6GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','續約','30個月','4G','12GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','續約','30個月','4G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(388,'遠傳','續約','30個月','5G','5GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(499,'遠傳','續約','30個月','5G','10GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(599,'遠傳','續約','30個月','5G','20GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(799,'遠傳','續約','30個月','5G','40GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(999,'遠傳','續約','30個月','5G','60GB','量到降速','網內免費','網外30分鐘','每分鐘1元','3000'),
(1399,'遠傳','續約','30個月','5G','無限制','無線飆網','網內免費','網外30分鐘','每分鐘1元','3000')





