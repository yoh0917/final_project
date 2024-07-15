-- DELETE FROM O0002_ORDERDETAIL;
-- DELETE FROM O0001_ORDER;
--
-- SELECT * FROM p0001_products
-- SELECT * FROM O0001_ORDER
-- SELECT * FROM O0002_ORDERDETAIL
--
-- ALTER TABLE O0002_ORDERDETAIL ALTER COLUMN PRODUCTID INT

DECLARE @i INT = 1;
DECLARE @seq INT = 1; -- �ߤ@�����W�ǦC
DECLARE @detailSeq INT = 1; -- DETAILID���ߤ@���W�ǦC

WHILE @i <= 500
    BEGIN
        -- �ͦ��H���� CREATEDATE�A�d��q 2022-01-01 �� 2024-12-31�A�åh���@����
        DECLARE @RandomDateTime DATETIME2(0) = DATEADD(DAY, ABS(CHECKSUM(NEWID())) % (365 * 3), '20220101')
            + DATEADD(SECOND, ABS(CHECKSUM(NEWID())) % (24 * 60 * 60), 0);
        DECLARE @CREATEDATE DATETIME = CAST(CONCAT(
                YEAR(@RandomDateTime), '-',
                RIGHT('0' + CAST(MONTH(@RandomDateTime) AS VARCHAR(2)), 2), '-',
                RIGHT('0' + CAST(DAY(@RandomDateTime) AS VARCHAR(2)), 2), ' ',
                RIGHT('0' + CAST(DATEPART(HOUR, @RandomDateTime) AS VARCHAR(2)), 2), ':',
                RIGHT('0' + CAST(DATEPART(MINUTE, @RandomDateTime) AS VARCHAR(2)), 2), ':',
                RIGHT('0' + CAST(DATEPART(SECOND, @RandomDateTime) AS VARCHAR(2)), 2)
                                            ) AS DATETIME);

        -- �ͦ�ORDERID�A�榡��S0YYMMDDHHMMSS + �ߤ@�����W�ǦC
        DECLARE @ORDERID VARCHAR(15) = 'S0' + RIGHT('0' + CAST(YEAR(@CREATEDATE) % 100 AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(MONTH(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DAY(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(HOUR, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(MINUTE, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(SECOND, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('000' + CAST(@seq AS VARCHAR(3)), 3);

        -- �W�[�ǦC�H�T�O�ߤ@��
        SET @seq = @seq + 1;

        DECLARE @USERID VARCHAR(10) = 'P' + RIGHT('0000000' + CAST(@i AS VARCHAR(9)), 8);
        DECLARE @USERNAME VARCHAR(50) = 'User' + CAST(@i AS VARCHAR(10));

        -- �ͦ�STATUS�A1�e70%
        DECLARE @STATUS VARCHAR(1) = CASE
                                         WHEN ABS(CHECKSUM(NEWID())) % 10 < 7 THEN '1'
                                         ELSE CAST(ABS(CHECKSUM(NEWID())) % 3 + 2 AS VARCHAR(1))
            END;

        -- �ͦ�TOTALAMOUNT�A�d��10000��50000
        DECLARE @TOTALAMOUNT INT = ABS(CHECKSUM(NEWID())) % 40001 + 10000;

        -- �ͦ�PAYSTATUS�A90%��P�A10%��U
        DECLARE @PAYSTATUS VARCHAR(1) = CASE
                                            WHEN ABS(CHECKSUM(NEWID())) % 10 < 9 THEN 'P'
                                            ELSE 'U'
            END;

        -- �ͦ�PAYDATE�A�p�GPAYSTATUS��P�h�ͦ�����A�_�h��NULL�A�åh���@����
        DECLARE @RandomPayDateTime DATETIME2(0) = DATEADD(DAY, ABS(CHECKSUM(NEWID())) % (365 * 3), '20220101')
            + DATEADD(SECOND, ABS(CHECKSUM(NEWID())) % (24 * 60 * 60), 0);
        DECLARE @PAYDATE DATETIME = CASE
                                        WHEN @PAYSTATUS = 'P' THEN CAST(CONCAT(
                                                YEAR(@RandomPayDateTime), '-',
                                                RIGHT('0' + CAST(MONTH(@RandomPayDateTime) AS VARCHAR(2)), 2), '-',
                                                RIGHT('0' + CAST(DAY(@RandomPayDateTime) AS VARCHAR(2)), 2), ' ',
                                                RIGHT('0' + CAST(DATEPART(HOUR, @RandomPayDateTime) AS VARCHAR(2)), 2), ':',
                                                RIGHT('0' + CAST(DATEPART(MINUTE, @RandomPayDateTime) AS VARCHAR(2)), 2), ':',
                                                RIGHT('0' + CAST(DATEPART(SECOND, @RandomPayDateTime) AS VARCHAR(2)), 2)
                                                                        ) AS DATETIME)
                                        ELSE NULL
            END;

        -- ���J��O0001_ORDER��
        INSERT INTO dbo.O0001_ORDER (ORDERID, CREATEDATE, PAYDATE, PAYSTATUS, STATUS, TOTALAMOUNT, USERID, USERNAME)
        VALUES (@ORDERID, @CREATEDATE, @PAYDATE, @PAYSTATUS, @STATUS, @TOTALAMOUNT, @USERID, @USERNAME);

        -- �p��C���q�檺���~�ƶq�A�H����1��3
        DECLARE @productCount INT = ABS(CHECKSUM(NEWID())) % 3 + 1;
        DECLARE @j INT = 1;
        DECLARE @totalDetailAmount INT = 0;

        WHILE @j <= @productCount
            BEGIN
                -- �ͦ��ߤ@��DETAILID
                DECLARE @DETAILID VARCHAR(10) = 'D' + RIGHT('0000000' + CAST(@detailSeq AS VARCHAR(7)), 7);
                -- �W�[DETAILID���ǦC�H�T�O�ߤ@��
                SET @detailSeq = @detailSeq + 1;

                -- �H�����PRODUCTID�A�d��1��50
                DECLARE @PRODUCTID INT = ABS(CHECKSUM(NEWID())) % 50 + 1;
                DECLARE @COUNT INT = 1; -- �ƶq�T�w��1
                -- �ͦ�PRICE�A�d��5000��50000
                DECLARE @PRICE INT = ABS(CHECKSUM(NEWID())) % 45001 + 5000;
                DECLARE @TOTAL INT = @COUNT * @PRICE;

                -- ���J��O0002_ORDERDETAIL��A�ϥΨӦ�O0001��ORDERID
                INSERT INTO dbo.O0002_ORDERDETAIL (DETAILID, COUNT, ORDERID, PRICE, PRODUCTID, TOTAL)
                VALUES (@DETAILID, @COUNT, @ORDERID, @PRICE, @PRODUCTID, @TOTAL);

                SET @totalDetailAmount = @totalDetailAmount + @TOTAL;
                SET @j = @j + 1;
            END;

        -- ��sO0001��TOTALAMOUNT�Ϩ䵥��O0002��TOTAL���[�`
        UPDATE dbo.O0001_ORDER
        SET TOTALAMOUNT = @totalDetailAmount
        WHERE ORDERID = @ORDERID;

        SET @i = @i + 1;
    END;
