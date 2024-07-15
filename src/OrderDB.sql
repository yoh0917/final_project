-- DELETE FROM O0002_ORDERDETAIL;
-- DELETE FROM O0001_ORDER;
--
-- SELECT * FROM p0001_products
-- SELECT * FROM O0001_ORDER
-- SELECT * FROM O0002_ORDERDETAIL
--
-- ALTER TABLE O0002_ORDERDETAIL ALTER COLUMN PRODUCTID INT

DECLARE @i INT = 1;
DECLARE @seq INT = 1; -- 唯一的遞增序列
DECLARE @detailSeq INT = 1; -- DETAILID的唯一遞增序列

WHILE @i <= 500
    BEGIN
        -- 生成隨機的 CREATEDATE，範圍從 2022-01-01 到 2024-12-31，並去除毫秒部分
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

        -- 生成ORDERID，格式為S0YYMMDDHHMMSS + 唯一的遞增序列
        DECLARE @ORDERID VARCHAR(15) = 'S0' + RIGHT('0' + CAST(YEAR(@CREATEDATE) % 100 AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(MONTH(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DAY(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(HOUR, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(MINUTE, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(SECOND, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('000' + CAST(@seq AS VARCHAR(3)), 3);

        -- 增加序列以確保唯一性
        SET @seq = @seq + 1;

        DECLARE @USERID VARCHAR(10) = 'P' + RIGHT('0000000' + CAST(@i AS VARCHAR(9)), 8);
        DECLARE @USERNAME VARCHAR(50) = 'User' + CAST(@i AS VARCHAR(10));

        -- 生成STATUS，1占70%
        DECLARE @STATUS VARCHAR(1) = CASE
                                         WHEN ABS(CHECKSUM(NEWID())) % 10 < 7 THEN '1'
                                         ELSE CAST(ABS(CHECKSUM(NEWID())) % 3 + 2 AS VARCHAR(1))
            END;

        -- 生成TOTALAMOUNT，範圍10000到50000
        DECLARE @TOTALAMOUNT INT = ABS(CHECKSUM(NEWID())) % 40001 + 10000;

        -- 生成PAYSTATUS，90%為P，10%為U
        DECLARE @PAYSTATUS VARCHAR(1) = CASE
                                            WHEN ABS(CHECKSUM(NEWID())) % 10 < 9 THEN 'P'
                                            ELSE 'U'
            END;

        -- 生成PAYDATE，如果PAYSTATUS為P則生成日期，否則為NULL，並去除毫秒部分
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

        -- 插入到O0001_ORDER表
        INSERT INTO dbo.O0001_ORDER (ORDERID, CREATEDATE, PAYDATE, PAYSTATUS, STATUS, TOTALAMOUNT, USERID, USERNAME)
        VALUES (@ORDERID, @CREATEDATE, @PAYDATE, @PAYSTATUS, @STATUS, @TOTALAMOUNT, @USERID, @USERNAME);

        -- 計算每筆訂單的產品數量，隨機為1到3
        DECLARE @productCount INT = ABS(CHECKSUM(NEWID())) % 3 + 1;
        DECLARE @j INT = 1;
        DECLARE @totalDetailAmount INT = 0;

        WHILE @j <= @productCount
            BEGIN
                -- 生成唯一的DETAILID
                DECLARE @DETAILID VARCHAR(10) = 'D' + RIGHT('0000000' + CAST(@detailSeq AS VARCHAR(7)), 7);
                -- 增加DETAILID的序列以確保唯一性
                SET @detailSeq = @detailSeq + 1;

                -- 隨機選擇PRODUCTID，範圍1到50
                DECLARE @PRODUCTID INT = ABS(CHECKSUM(NEWID())) % 50 + 1;
                DECLARE @COUNT INT = 1; -- 數量固定為1
                -- 生成PRICE，範圍5000到50000
                DECLARE @PRICE INT = ABS(CHECKSUM(NEWID())) % 45001 + 5000;
                DECLARE @TOTAL INT = @COUNT * @PRICE;

                -- 插入到O0002_ORDERDETAIL表，使用來自O0001的ORDERID
                INSERT INTO dbo.O0002_ORDERDETAIL (DETAILID, COUNT, ORDERID, PRICE, PRODUCTID, TOTAL)
                VALUES (@DETAILID, @COUNT, @ORDERID, @PRICE, @PRODUCTID, @TOTAL);

                SET @totalDetailAmount = @totalDetailAmount + @TOTAL;
                SET @j = @j + 1;
            END;

        -- 更新O0001的TOTALAMOUNT使其等於O0002的TOTAL的加總
        UPDATE dbo.O0001_ORDER
        SET TOTALAMOUNT = @totalDetailAmount
        WHERE ORDERID = @ORDERID;

        SET @i = @i + 1;
    END;
