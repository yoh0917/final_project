-- phonefix


truncate table F00001_PhoneFixs

INSERT INTO F00001_PhoneFixs(FixName, FixDate, FixCost, FixPort,userId)
VALUES
('leo', '2024/07/20', '39000', 'Processor','2406140002'),
('leo', '2024/07/25', '55000', 'Microphone','2406140002'),
('leo', '2024/07/21', '44000', 'Microphone','2406140002'),
('gary', '2024/07/06', '55000', 'Front Camera','2406140002'),
('gary', '2024/07/01', '37000', 'USB Port','2406140001'),
('gary', '2024/07/20', '67000', 'Memory','2406140001'),
('gary', '2024/07/15', '55000', 'Memory','2406140001'),
('gary', '2024/07/12', '44000', 'Processor','2406140001')

INSERT INTO F00001_PhoneFixs(FixName, FixDate, FixCost, FixPort)
VALUES
('Brian Hamilton', '2011/04/04', '44000', 'Microphone'),
('Susan Levy', '2000/05/16', '67000', 'USB Port'),
('Sean Green', '2009/09/11', '44000', 'Memory'),
('Kimberly Smith', '2013/12/25', '55000', 'Microphone'),
('Jennifer Summers', '2009/09/11', '55000', 'USB Port'),
('April Snyder', '2011/04/04', '55000', 'Processor'),
('Dana Nguyen', '2011/04/04', '39000', 'Processor'),
('Cheryl Bradley', '2000/05/16', '39000', 'USB Port'),
('Walter Pratt', '2013/12/25', '44000', 'Processor'),
('Bobby Flores', '2013/12/25', '55000', 'Front Camera'),
('Tasha Rodriguez', '2013/12/25', '37000', 'Memory'),
('Michelle Kelley', '2009/09/11', '67000', 'Processor'),
('Kimberly Maynard', '2007/07/06', '55000', 'Microphone'),
('Laurie Wallace', '2011/04/04', '44000', 'Memory'),
('Janice Johnston', '2007/07/06', '67000', 'Memory'),
('Collin Lopez', '2007/07/06', '55000', 'Front Camera'),
('Mary Alvarez', '2000/05/16', '55000', 'Memory'),
('Peter Mcdowell', '2007/07/06', '39000', 'Processor'),
('Sarah Villanueva', '2000/05/16', '39000', 'USB Port'),
('Kimberly Myers', '2000/05/16', '37000', 'USB Port'),
('Desiree Cain', '2013/12/25', '37000', 'Front Camera'),
('Stephanie Lawrence', '2011/04/04', '55000', 'USB Port'),
('Lauren Hayes', '2009/09/11', '67000', 'Processor'),
('Whitney Stark', '2013/12/25', '37000', 'Processor'),
('Angela Salazar', '2011/04/04', '55000', 'Microphone'),
('Mr. Ryan Sanchez', '2000/05/16', '44000', 'Memory'),
('Autumn Robinson', '2013/12/25', '37000', 'Memory'),
('Faith Cabrera', '2009/09/11', '55000', 'USB Port'),
('Charles Wolfe', '2009/09/11', '39000', 'USB Port'),
('Kenneth Kent', '2011/04/04', '44000', 'Memory'),
('Melanie Johnson', '2011/04/04', '39000', 'Memory'),
('Lisa Johnston', '2007/07/06', '37000', 'Front Camera'),
('Jacob Hooper', '2011/04/04', '44000', 'Processor'),
('Alex Woodward', '2013/12/25', '44000', 'Front Camera'),
('Caleb Clark', '2007/07/06', '39000', 'Processor'),
('Taylor Johnson', '2011/04/04', '37000', 'Front Camera'),
('Brian Green', '2011/04/04', '55000', 'Memory'),
('Matthew Bell', '2000/05/16', '67000', 'Processor'),
('Jonathan Williams', '2011/04/04', '37000', 'Memory'),
('William Gonzalez', '2000/05/16', '67000', 'Microphone'),
('Nicholas Massey', '2011/04/04', '44000', 'Processor'),
('Caroline Chambers', '2011/04/04', '55000', 'Processor');

select * from F00001_PhoneFixs