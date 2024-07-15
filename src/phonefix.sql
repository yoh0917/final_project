-- phonefix


truncate table F00001_PhoneFixs

INSERT INTO F00001_PhoneFixs(FixName, FixDate, FixCost, FixPort, FixState, userId)
VALUES
('leo', '2024-07-20', '3900', '電池損壞無法充電需要更換', '維修中', '2406140002'),
('leo', '2024-07-25', '5500', '麥克風無法使用', '已完成', '2406140002'),
('leo', '2024-07-21', '4400', '麥克風無法使用', '未接單', '2406140002'),
('gary', '2024-07-06', '5500', '相機無法對焦需要檢查', '維修中', '2406140002'),
('gary', '2024-07-01', '3700', '充電口接觸不良', '已完成', '2406140001'),
('gary', '2024-07-20', '6700', '手機掉水需要更換零件', '未接單', '2406140001'),
('gary', '2024-07-15', '5500', '手機掉水需要更換零件', '維修中', '2406140001'),
('gary', '2024-07-12', '4400', '電池損壞無法充電需要更換', '已完成', '2406140001');



INSERT INTO F00001_PhoneFixs(FixName, FixDate, FixCost, FixPort, FixState)
VALUES
('Oliver Smith', '2023-10-15', '2300', '音量按鈕卡住無法使用', '維修中'),
('Amelia Johnson', '2024-01-20', '3100', '相機無法對焦需要檢查', '已完成'),
('William Brown', '2023-08-30', '2700', '手機掉水需要更換零件', '未接單'),
('Sophia Williams', '2023-12-25', '1500', '充電口接觸不良', '已完成'),
('James Jones', '2024-03-14', '3300', '無法開機可能是主板問題', '維修中'),
('Charlotte Garcia', '2023-09-22', '2500', '藍牙功能異常需要檢查', '未接單'),
('Benjamin Miller', '2024-04-02', '2100', 'WiFi無法連接', '已完成'),
('Mia Davis', '2023-07-19', '1700', '耳機插孔壞了', '未接單'),
('Lucas Martinez', '2024-05-16', '2900', '麥克風無法使用', '維修中'),
('Harper Rodriguez', '2023-11-30', '3500', '手機反應遲鈍需要重置', '已完成'),
('Henry Martinez', '2024-06-08', '3800', '屏幕碎裂', '未接單'),
('Ella Lee', '2023-10-05', '4000', '手機自動重啟', '維修中'),
('Alexander Perez', '2023-12-17', '4200', 'GPS無法定位', '已完成'),
('Emily White', '2024-01-09', '1500', '手機無法充電', '未接單'),
('Michael Harris', '2023-09-12', '3300', '無法開機', '維修中'),
('Ava Clark', '2024-02-24', '2700', '手機螢幕閃爍', '已完成'),
('Ethan Lewis', '2023-08-07', '2300', '相機無法使用', '未接單'),
('Isabella Walker', '2023-12-05', '2100', '音量過低', '維修中'),
('Daniel Hall', '2024-03-21', '2600', '觸控失靈', '已完成'),
('Grace Allen', '2023-11-03', '3200', '手機發熱嚴重', '未接單'),
('Logan Young', '2024-04-12', '2900', '無法連接網絡', '維修中'),
('Chloe King', '2023-09-18', '3100', '手機震動無反應', '已完成'),
('Matthew Wright', '2024-05-27', '2800', '手機無法通話', '未接單'),
('Avery Scott', '2023-07-25', '3400', '相機無法拍照', '維修中'),
('Sebastian Green', '2023-12-29', '3700', '指紋識別無法使用', '已完成'),
('Evelyn Adams', '2024-06-15', '3900', '無法接收短信', '未接單'),
('Jack Baker', '2023-08-11', '4100', '手機無法解鎖', '維修中'),
('Scarlett Nelson', '2024-01-30', '4300', '無法連接藍牙', '已完成'),
('Owen Carter', '2023-10-28', '4500', '手機無法同步資料', '未接單'),
('Lily Mitchell', '2024-03-05', '4700', '手機電池耗電快', '維修中'),
('Samuel Roberts', '2023-11-07', '4900', '手機屏幕無顯示', '已完成'),
('Hannah Turner', '2024-04-25', '5100', '無法開機可能是電池問題', '未接單'),
('David Phillips', '2023-09-29', '5300', '手機自動重啟', '維修中'),
('Zoe Campbell', '2024-05-13', '5500', '相機無法啟動', '已完成'),
('Joseph Parker', '2023-08-22', '5700', '手機信號差', '未接單'),
('Victoria Evans', '2024-01-18', '5900', '無法連接WiFi', '維修中'),
('John Edwards', '2023-10-02', '6100', '手機螢幕裂了', '已完成'),
('Layla Collins', '2024-02-12', '6300', '充電速度很慢', '未接單'),
('Levi Stewart', '2023-12-20', '6500', '無法使用手機支付', '維修中'),
('Riley Morris', '2024-03-28', '6700', '手機麥克風沒聲音', '已完成'),
('Dylan Cook', '2023-11-15', '6900', '手機反應遲緩', '未接單'),
('Aria Bell', '2024-04-07', '7100', '無法連接手機網絡', '維修中'),
('Caleb Murphy', '2023-09-05', '7300', '手機無法關機', '已完成'),
('Aubrey Sanchez', '2024-05-23', '7500', '相機拍照模糊', '未接單'),
('Mason Rivera', '2023-08-26', '7700', '無法播放音樂', '維修中'),
('Aurora Ward', '2024-01-04', '7900', '手機無法開機', '已完成'),
('Elijah Cooper', '2023-10-10', '8100', '無法使用指紋解鎖', '未接單'),
('Natalie Torres', '2024-02-21', '8300', '手機無法接打電話', '維修中'),
('Joshua Peterson', '2023-12-12', '8500', '無法連接手機數據', '已完成'),
('Luna Gray', '2024-03-12', '8700', '無法使用WiFi功能', '未接單'),
('Ethan Ramirez', '2024-05-05', '8900', '手機過熱需要冷卻', '已完成'),
('Liam Anderson', '2023-11-22', '9100', '手機無法充電', '維修中'),
('Mason Lee', '2024-01-14', '9300', '手機無法同步資料', '未接單'),
('Oliver Scott', '2023-09-09', '9500', '無法使用手機數據', '已完成'),
('Sophia Martinez', '2024-03-19', '9700', '手機屏幕不靈敏', '維修中'),
('Emma Taylor', '2023-12-30', '9900', '手機震動異常', '未接單');

select * from F00001_PhoneFixs