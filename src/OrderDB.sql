DELETE FROM O0002_ORDERDETAIL;
DELETE FROM O0001_ORDER;

SELECT * FROM p0001_products
SELECT * FROM O0001_ORDER
SELECT * FROM O0002_ORDERDETAIL


/*
SELECT * FROM S1001_SHOPPINGCART_V

WITH RankedPhotos AS (
    SELECT
        photoid,
        photoFile,
        productid,
        ROW_NUMBER() OVER (PARTITION BY productid ORDER BY photoid) AS rn
    FROM
        photo
)
SELECT
    S0001.USERID,
    S0001.PRODUCTID,
    S0001.COLOR,
    S0001.STORAGE,
    S0001.QUANTITY,
    S0001.PRICE,
    P0001.PRODUCTNAME,
	CONCAT(P0001.PRODUCTNAME,P0001.color,P0001.capacity) NEWPRODUCTNAME,
    P.photoFile
FROM
    S0001_SHOPPINGCART S0001
LEFT JOIN p0001_products P0001
    ON S0001.PRODUCTID = P0001.productid
LEFT JOIN RankedPhotos P
    ON S0001.PRODUCTID = P.productid AND P.rn = 1;


CREATE TABLE O0001_ORDER (
    ORDERID varchar(17) NOT NULL PRIMARY KEY,
    STATUS varchar(1) NULL,
    CREATEDATE datetime NULL,
    TOTALAMOUNT int NULL,
    PAYDATE datetime NULL,
    PAYSTATUS varchar(1) NULL,
    USERID varchar(10) NULL,
    USERNAME varchar(50) NOT NULL,
    NAME nvarchar(50) NULL,
    EMAIL varchar(50) NULL,
    PHONE varchar(15) NULL,
    CITY nvarchar(10) NULL,
    DISTRICT nvarchar(10) NULL,
    ADDRESS nvarchar(100) NULL,
    DETAILADDRESS nvarchar(255) NULL
);

CREATE TABLE O0002_ORDERDETAIL (
    DETAILID varchar(10) NOT NULL PRIMARY KEY,
    ORDERID varchar(17) NOT NULL,
    PRODUCTID varchar(10) NULL,
    PRODUCTNAME nvarchar(100) NULL,
    COLOR nvarchar(30) NULL,
    STORAGE nvarchar(30) NULL,
    QUANTITY int NULL,
    PRICE int NULL,
    TOTAL int NULL,

);

CREATE TABLE S0001_SHOPPINGCART (
                                    USERID VARCHAR(50),
                                    PRODUCTID INT,
                                    COLOR  VARCHAR(20),
                                    STORAGE  VARCHAR(20),
                                    QUANTITY INT,
                                    PRICE INT
                                        CONSTRAINT PK_SHOPPINGCART PRIMARY KEY (USERID, PRODUCTID),
);

INSERT INTO S0001_SHOPPINGCART (USERID, PRODUCTID, COLOR, STORAGE, QUANTITY, PRICE)
VALUES
    ('admin', 1, '����', '1000TB', 1, 50000),
    ('admin', 2, '�Ȧ�', '512TB', 1, 40000);
*/

/*
ALTER TABLE O0002_ORDERDETAIL ALTER COLUMN PRODUCTID INT
-- ��s O0001_ORDER ���
ALTER TABLE O0001_ORDER
ADD NAME nvarchar(50) NULL;

-- ��s O0002_ORDERDETAIL ���
ALTER TABLE O0002_ORDERDETAIL
ADD PRODUCTNAME nvarchar(100) NULL;
*/


DECLARE @i INT = 1;
DECLARE @seq INT = 1; -- 訂單序號
DECLARE @detailSeq INT = 1; -- DETAILID序號

-- 定義產品資料
DECLARE @Products TABLE (
                            PRODUCTID INT,
                            DESCRIPTION NVARCHAR(255),
                            PRICE INT,
                            PRODUCTNAME NVARCHAR(50),
                            STOCKQUANTITY INT,
                            PRODUCTBRAND NVARCHAR(50),
                            PRODUCTSTATUS INT,
                            capacity NVARCHAR(10),
                            color NVARCHAR(10)
                        );

-- 插入產品資料
INSERT INTO @Products (PRODUCTID, DESCRIPTION, PRICE, PRODUCTNAME, STOCKQUANTITY, PRODUCTBRAND, PRODUCTSTATUS, capacity, color)
VALUES
    (1, 'A15仿生晶片，優秀的性能與效能', 18000, 'iPhone 13', 44, 'Apple', 1, '128GB', '紅色'),
    (2, '6.7吋Super Retina XDR顯示屏，耐用設計，增強防摔', 44000, 'iPhone 14 Pro Max', 10, 'Apple', 1, '256GB', '白色'),
    (3, '6.1吋6.7吋Super Retina XDR顯示屏，強化玻璃', 26000, 'iPhone 15', 28, 'Apple', 1, '512GB', '黑色'),
    (4, '6.1吋Super Retina XDR顯示屏，性能提升', 28500, 'iPhone 15 Plus', 37, 'Apple', 1, '256GB', '金色'),
    (5, '6.1吋Super Retina XDR顯示屏，強化設計', 32000, 'iPhone 15 Pro', 8, 'Apple', 1, '256GB', '白色'),
    (6, '6.7吋Super Retina XDR顯示屏，效能優化', 39000, 'iPhone 15 Pro Max', 22, 'Apple', 1, '256GB', '黑色'),
    (7, 'Galaxy M14 5G 6.6吋', 4490, 'Samsung M14', 55, 'Samsung', 1, '128GB', '白色'),
    (8, '6.5吋FHD+ Super AMOLED 120Hz顯示螢幕', 6390, 'Samsung M34', 33, 'Samsung', 1, '256GB', '藍色'),
    (9, '搭載Vision Booster的6.5吋Super AMOLED螢幕', 4790, 'Samsung A15', 3, 'Samsung', 1, '128GB', '白色'),
    (10, '擁有5000高容量電池的A35', 7590, 'Samsung A35', 5, 'Samsung', 1, '256GB', '霧釉藍'),
    (11, '配備5000高容量電池的A55', 9790, 'Samsung A55', 36, 'Samsung', 1, '128GB', '冰藍雪');

WHILE @i <= 500
    BEGIN
        -- 產生隨機 CREATEDATE，範圍從 2023-01-01 到 2024-08-06，並加上一個隨機時間
        DECLARE @RandomDateTime DATETIME2(0) = DATEADD(DAY, ABS(CHECKSUM(NEWID())) % (365 + 217), '20230101')
            + DATEADD(SECOND, ABS(CHECKSUM(NEWID())) % (24 * 60 * 60), 0);
        DECLARE @CREATEDATE DATETIME = CAST(CONCAT(
                YEAR(@RandomDateTime), '-',
                RIGHT('0' + CAST(MONTH(@RandomDateTime) AS VARCHAR(2)), 2), '-',
                RIGHT('0' + CAST(DAY(@RandomDateTime) AS VARCHAR(2)), 2), ' ',
                RIGHT('0' + CAST(DATEPART(HOUR, @RandomDateTime) AS VARCHAR(2)), 2), ':',
                RIGHT('0' + CAST(DATEPART(MINUTE, @RandomDateTime) AS VARCHAR(2)), 2), ':',
                RIGHT('0' + CAST(DATEPART(SECOND, @RandomDateTime) AS VARCHAR(2)), 2)
                                            ) AS DATETIME);

        -- 產生ORDERID，格式為S0YYMMDDHHMMSS + 序號 + 隨機數
        DECLARE @ORDERID VARCHAR(18) = 'S0' + RIGHT('0' + CAST(YEAR(@CREATEDATE) % 100 AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(MONTH(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DAY(@CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(HOUR, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(MINUTE, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('0' + CAST(DATEPART(SECOND, @CREATEDATE) AS VARCHAR(2)), 2)
            + RIGHT('000' + CAST(@seq AS VARCHAR(3)), 3)
            + CAST(ABS(CHECKSUM(NEWID())) % 1000 AS VARCHAR(3));

        -- 增加序號以確保唯一
        SET @seq = @seq + 1;

        DECLARE @USERID VARCHAR(10) = 'P' + RIGHT('0000000' + CAST(@i AS VARCHAR(9)), 8);
        DECLARE @USERNAME VARCHAR(50) = 'User' + CAST(@i AS VARCHAR(10));

        -- 產生STATUS，70%為1
        DECLARE @STATUS VARCHAR(1) = CASE
                                         WHEN ABS(CHECKSUM(NEWID())) % 10 < 7 THEN '1'
                                         ELSE CAST(ABS(CHECKSUM(NEWID())) % 3 + 2 AS VARCHAR(1))
            END;

        -- 產生TOTALAMOUNT，範圍從10000到50000
        DECLARE @TOTALAMOUNT INT = ABS(CHECKSUM(NEWID())) % 40001 + 10000;

        -- 產生PAYSTATUS，90%為P，10%為U
        DECLARE @PAYSTATUS VARCHAR(1) = CASE
                                            WHEN ABS(CHECKSUM(NEWID())) % 10 < 9 THEN 'P'
                                            ELSE 'U'
            END;

        -- 產生PAYDATE，若PAYSTATUS為P則生成日期時間，否則為NULL
        DECLARE @RandomPayDateTime DATETIME2(0) = DATEADD(DAY, ABS(CHECKSUM(NEWID())) % (365 + 217), '20230101')
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

        -- 插入到 O0001_ORDER 表
        INSERT INTO dbo.O0001_ORDER (ORDERID, CREATEDATE, PAYDATE, PAYSTATUS, STATUS, TOTALAMOUNT, USERID, USERNAME)
        VALUES (@ORDERID, @CREATEDATE, @PAYDATE, @PAYSTATUS, @STATUS, @TOTALAMOUNT, @USERID, @USERNAME);

        -- 計算當前訂單的產品數量，範圍為1到3
        DECLARE @productCount INT = ABS(CHECKSUM(NEWID())) % 3 + 1;
        DECLARE @j INT = 1;
        DECLARE @totalDetailAmount INT = 0;

        WHILE @j <= @productCount
            BEGIN
                -- 生成唯一的 DETAILID，格式為D + 日期時間 + 隨機數
                DECLARE @DETAILID VARCHAR(17) = 'D' + FORMAT(@CREATEDATE, 'yyyyMMddHHmmss') + RIGHT('000' + CAST(ABS(CHECKSUM(NEWID())) % 1000 AS VARCHAR(3)), 3);

                -- 隨機選擇一個產品
                DECLARE @SelectedProduct TABLE (
                                                   PRODUCTID INT,
                                                   DESCRIPTION NVARCHAR(255),
                                                   PRICE INT,
                                                   PRODUCTNAME NVARCHAR(50),
                                                   STOCKQUANTITY INT,
                                                   PRODUCTBRAND NVARCHAR(50),
                                                   PRODUCTSTATUS INT,
                                                   capacity NVARCHAR(10),
                                                   color NVARCHAR(10)
                                               );
                INSERT INTO @SelectedProduct
                SELECT TOP 1 * FROM @Products
                ORDER BY NEWID();

                DECLARE @PRODUCTID INT;
                DECLARE @PRICE INT;
                DECLARE @PRODUCTNAME NVARCHAR(50);
                DECLARE @CAPACITY NVARCHAR(10);
                DECLARE @COLOR NVARCHAR(10);
                DECLARE @QUANTITY INT = 1; -- 數量設定為1
                DECLARE @TOTAL INT;

                SELECT @PRODUCTID = PRODUCTID,
                       @PRICE = PRICE,
                       @PRODUCTNAME = PRODUCTNAME,
                       @CAPACITY = capacity,
                       @COLOR = color
                FROM @SelectedProduct;

                SET @TOTAL = @QUANTITY * @PRICE;

                -- 插入到 O0002_ORDERDETAIL 表
                INSERT INTO dbo.O0002_ORDERDETAIL (DETAILID, QUANTITY, ORDERID, PRICE, PRODUCTID, TOTAL, PRODUCTNAME, STORAGE, COLOR)
                VALUES (@DETAILID, @QUANTITY, @ORDERID, @PRICE, @PRODUCTID, @TOTAL, @PRODUCTNAME, @CAPACITY, @COLOR);

                SET @totalDetailAmount = @totalDetailAmount + @TOTAL;
                SET @j = @j + 1;
            END;

        -- 更新 O0001_ORDER 的 TOTALAMOUNT 為 O0002_ORDERDETAIL 的 TOTAL 合計
        UPDATE dbo.O0001_ORDER
        SET TOTALAMOUNT = @totalDetailAmount
        WHERE ORDERID = @ORDERID;

        SET @i = @i + 1;
    END;
