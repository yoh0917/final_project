-- product
create table p0001_products (
	productid int identity(1,1) primary key not null,	
	productName nvarchar(100)not null ,
	price nvarchar(100) not null,
	description nvarchar(max)not null,
	stockQuantity nvarchar(100)not null
);

INSERT INTO p0001_products (ProductName, price, description, stockquantity)
VALUES
('Apple iPhone 13', 18000, N'A15仿生芯片，優秀的性能與能效', 50),
('Apple iPhone 15', 26000, N'6.1/6.7英寸Super Retina XDR顯示屏，邊框更窄，機身更輕薄', 30),
('Apple iPhone 15 Plus', 28500, N'6.7英寸Super Retina XDR顯示屏，精緻設計，輕薄機身', 15),
('Apple iPhone 15 Pro', 32000, N'6.1英寸Super Retina XDR顯示屏，鈦金屬框架，輕量化設計', 8),
('Apple iPhone 15 Pro Max', 39000, N'6.7英寸Super Retina XDR顯示屏，鈦金屬框架，輕量化設計', 0),
('Apple iPhone 14 Pro Max', 44000, N'6.7英寸Super Retina XDR顯示屏，耐用設計，陶瓷盾玻璃', 0),
('Samsung M14', 4490, N'Galaxy M14 5G 的 6.6吋 V 極限全螢幕，給你更寬廣的視野。採用 FHD+ 技術並支援 90Hz 螢幕高更新率，讓你瀏覽資訊更為流暢、清晰', 15),
('Samsung M34', 6390, N'6.5吋 FHD+ Super AMOLED 120Hz 螢幕深入感受每一個細節。支援 1,000-nit 亮度，讓內容清晰顯示。採用 Vision Booster 技術，即使在陽光下依舊完美呈現。藍光防護能讓你舒適地觀看精彩內容', 30),
('Samsung A15', 4790, N'搭載 Vision Booster 的 6.5吋 Super AMOLED 顯示螢幕，以最高可達 800 nits 的亮度、90Hz 更新頻率及護眼低藍光螢幕，呈現流暢、明亮且生動的觀賞體驗', 20),
('Samsung MA25', 6490, N'絕美無比。Galaxy A25 5G 配備 6.5吋 Super AMOLED 顯示螢幕，呈現最高 1,000 nits 的豐富色彩和細節，同時搭載 Vision Booster 技術，提供清晰的戶外可見性。此外，Eye Comfort Shield 能減少藍光刺激，不分晝夜守護你的雙眼', 10),
('Samsung MA35', 8490, N'絕美無比。Galaxy A25 5G 配備 6.5吋 Super AMOLED 顯示螢幕，呈現最高 1,000 nits 的豐富色彩和細節，同時搭載 Vision Booster 技術，提供清晰的戶外可見性。此外，Eye Comfort Shield 能減少藍光刺激，不分晝夜守護你的雙眼', 150),
('Samsung A55', 10290, N'Galaxy A55 5G 高質感金屬邊框搭配玻璃背蓋，3 鏡頭垂直排列，打造出 Galaxy A 系列超高顏值外觀，一出手就很潮。此外，螢幕採用 Corning® Gorilla® Glass Victus®+，讓手機更加堅固耐用', 23);