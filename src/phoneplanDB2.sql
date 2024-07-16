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
(388,'�x�W�j���j','��X','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','��X','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','��X','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','��X','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','��X','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','��X','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'�x�W�j���j','��X','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'�x�W�j���j','��X','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'�x�W�j���j','��X','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','�s�ӿ�','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','�s�ӿ�','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','�s�ӿ�','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'�x�W�j���j','�s�ӿ�','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','���','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','���','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','���','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','���','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','���','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','���','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'�x�W�j���j','���','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'�x�W�j���j','���','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'�x�W�j���j','���','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','��X','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','��X','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','��X','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'�x�W�j���j','��X','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'�x�W�j���j','��X','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'�x�W�j���j','��X','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'�x�W�j���j','��X','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'�x�W�j���j','��X','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'�x�W�j���j','��X','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','�s�ӿ�','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','�s�ӿ�','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','�s�ӿ�','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','�s�ӿ�','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','�s�ӿ�','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','�s�ӿ�','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'���عq�H','�s�ӿ�','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'���عq�H','�s�ӿ�','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'���عq�H','�s�ӿ�','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','��X','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','��X','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','��X','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','��X','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','��X','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','��X','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'���عq�H','��X','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'���عq�H','��X','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'���عq�H','��X','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','���','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','���','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','���','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'���عq�H','���','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'���عq�H','���','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'���عq�H','���','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'���عq�H','���','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'���عq�H','���','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'���عq�H','���','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'����','�s�ӿ�','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','�s�ӿ�','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','�s�ӿ�','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'����','�s�ӿ�','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','�s�ӿ�','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','�s�ӿ�','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'����','�s�ӿ�','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'����','�s�ӿ�','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'����','�s�ӿ�','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'����','��X','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','��X','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','��X','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'����','��X','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','��X','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','��X','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'����','��X','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'����','��X','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'����','��X','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000'),
(388,'����','���','30�Ӥ�','4G','6GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','���','30�Ӥ�','4G','12GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','���','30�Ӥ�','4G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(388,'����','���','30�Ӥ�','5G','5GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(499,'����','���','30�Ӥ�','5G','10GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(599,'����','���','30�Ӥ�','5G','20GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(799,'����','���','30�Ӥ�','5G','40GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(999,'����','���','30�Ӥ�','5G','60GB','�q�쭰�t','�����K�O','���~30����','�C����1��','3000'),
(1399,'����','���','30�Ӥ�','5G','�L����','�L�u�t��','�����K�O','���~30����','�C����1��','3000')




