use SenlaDbLesson10;

INSERT INTO `product` (`maker`, `model`, `type`) VALUES ('Sony', 'vaio', 'laptop');
INSERT INTO `product` (maker, model, type) VALUES ('lenovo', 'yoga 900', 'laptop');
INSERT INTO `product` (maker, model, type) VALUES ('lenovo', 'x250', 'laptop');
INSERT INTO `product` (maker, model, type) VALUES ('hp', 'EliteBook 2560p', 'laptop');
INSERT INTO `product` (maker, model, type) VALUES ('Dell', 'Inspiron 15', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('corrino', 'M55V', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('Acer', 'Aspire 5349', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('HP', '635', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('Toshiba', 'Satellite P755', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('Asus', 'X555', 'laptop');
INSERT INTO product (maker, model, type) VALUES ('Asus', 'X751', 'laptop');

INSERT INTO product (maker, model, type) VALUES 
('ASUS', 'C302CA-DHM4 Chromebook','laptop'),
('ASUS','ZenBook','laptop'),
('Eluktronics', 'N850HK1 Pro Gaming', 'laptop'),
('HP', '17','laptop'),
('CUK', 'Probook 450 G5', 'laptop'),
('Dell', 'Latitude 14 5000 5414','laptop'),
('Lenovo', 'Thinkpad Yoga 260','laptop'),
('Samsung', 'Notebook 9 Pro','laptop'),
('Samsung', 'Notebook Odyssey', 'laptop'),
('Lenovo','Miix 630','laptop');

INSERT INTO product (maker, model, type) VALUES ('hp', 'dc5800', 'pc');
INSERT INTO product (maker, model, type) VALUES ('hp', '24-r028ur','pc');
INSERT INTO product (maker, model, type) VALUES ('compaq', 'desktop','pc');
INSERT INTO product (maker, model, type) VALUES ('compaq', 'Compaq CQ5210F','pc');
INSERT INTO product (maker, model, type) VALUES ('compaq', 'presario CQ5300','pc');
INSERT INTO product (maker, model, type) VALUES ('hp', '8300 Elite','pc');
INSERT INTO product (maker, model, type) VALUES ('Acer', 'Aspire Desktop','pc');
INSERT INTO product (maker, model, type) VALUES ('hp', '2017 HP Pavilion Slimline 400 Flagship Premium','pc');
INSERT INTO product (maker, model, type) VALUES ('Dell', 'Optiplex 780 Minitower', 'pc');

INSERT INTO product (maker, model, type) VALUES ('HP', 'LaserJet 1320', 'printer');
INSERT INTO product (maker, model, type) VALUES ('HP', 'LaserJet Pro MFP M125rnw', 'printer');
INSERT INTO product (maker, model, type) VALUES ('HP', 'Laserjet M1132', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'ML-1210 ', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'ML-2010P', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'M2026W', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'CLP-310', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'SCX-3400', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'SCX-4600', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'CLP-315', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Samsung', 'ML1610', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Xerox', 'Phaser 3119', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Xerox', 'phaser 3140', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Xerox', 'WorkCentre Pro 420', 'printer');
INSERT INTO product (maker, model, type) VALUES ('Xerox', 'DC 260', 'printer');
INSERT INTO product (maker, model, type) VALUES
 ('Xerox', 'Workcentre 5020', 'printer'),
('HP', 'DeskJet 2130', 'printer'),
('Canon', 'PIXMA G3411', 'printer'),
('HP', 'DeskJet Ink Advantage 4535', 'printer'),
('HP', 'DesignJet T520', 'printer'),
('Epson', 'SureColor SC-P600', 'printer');

INSERT INTO `printer` (`model`, `color`, `type`, `price`) VALUES 
('CLP-310', 'y', 'Laser', '220'),
('CLP-315', 'y', 'Laser', '200'),
('DC 260', 'n', 'Laser', '8650'),
('Laserjet M1132', 'n', 'Laser', '350'),
('M2026W', 'n', 'Laser', '280'),
('SCX-3400', 'n', 'Laser', '300'),
('DeskJet 2130', 'y', 'Jet', '60'),
('PIXMA G3411', 'y', 'Jet', '400'),
('Phaser 3119', 'n', 'Laser', '60'),
('DeskJet Ink Advantage 4535', 'y', 'Jet', '330'),
('DesignJet T520', 'y', 'Jet', '2700'),
('SureColor SC-P600', 'y', 'Jet', '1860'); 

INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) 
  VALUES (NULL, 'yoga 900', '2000', '8000', '500', '2000', '13');
INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (NULL, 'x250', '1500', '4000', '256', '600', '12');
INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (NULL, 'EliteBook 2560p', '3000', '4000', '2128', '400', '12');

INSERT INTO laptop (model, speed, ram, hd, price, screen) VALUES 
  ('C302CA-DHM4 Chromebook', '2200', '4000', '64', '950', '12'),
  ('ZenBook', '1600', '8000', '256', '1446', '13'),
  ('N850HK1 Pro Gaming', '2800', '16000', '512', '2000', '15'),
  ('17', '2500', '8000', '1000', '960', '17'),
  ('Probook 450 G5', '1800', '32000', '2500', '2540', '15'),
  ('Latitude 14 5000 5414', '2400', '8000', '128', '1760', '14'),
  ('Inspiron 15', '2500', '8000', '1000', '1040', '15'),
  ('Thinkpad Yoga 260', '2300', '8000', '500', '1900', '12'),
  ('Notebook 9 Pro', '4000', '8000', '2000', '3800', '13'),
  ('Notebook Odyssey', '3800', '16000', '500', '3200', '15'),
  ('vaio', '2000', '4000', '250', '710', '16'),
  ('Miix 630', '1900', '4000', '250', '1680', '12');

INSERT INTO product (maker, model, type) VALUES
 ('HP', 'Envy 750-000ur', 'pc'),
 ('Dell', 'Alienware X51 R2', 'pc'),
 ('HP', 'EliteDesk 800 G1', 'pc'),
 ('Dell', 'Optiplex', 'pc'),
 ('HP', 'Pavilion 570', 'pc'),
 ('HP', 'ProDesk 600 G1', 'pc'),
 ('HP', '290 G1', 'pc');

INSERT INTO pc (model, speed, ram, hd, cd, price) VALUES 
('Envy 750-000ur', '3600', '16000', '1128', '42', '2500'),
('Alienware X51 R2', '3600', '16000', '2000', '42', '2900'),
('EliteDesk 800 G1', '3600', '16000', '256', '42', '3000'),
('Optiplex', '3000', '4000', '500', '42', '1200'),
('Pavilion 570', '3000', '8000', '2000', '42', '1860'),
('ProDesk 600 G1', '3000', '4000', '500', '42', '630'),
('ProDesk 600 G1', '3200', '8000', '1128', '42', '1340'),
('290 G1', '3000', '4000', '500', '42', '1610'),
('Optiplex', '3200', '8000', '256', '42', '1680'),
('Optiplex', '3600', '8000', '500', '42', '1960');

INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, '8300 Elite', '3200', '8000', '500', '0', '184');
INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, 'Aspire Desktop', '2400', '8000', '1000', '8', '700');
INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, 'Compaq CQ5210F', '4000', '3000', '500', '0', '400');

INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES
(NULL, '2017 HP Pavilion Slimline 400 Flagship Premium', '1400', '4000', '500', '24', '380'),
(NULL, '24-r028ur', '2900', '4000', '1000', '24', '1100'),
(NULL, 'Aspire Desktop', '3500', '12000', '2000', '8', '940'),
(NULL, 'Optiplex 780 Minitower', '3000', '4000', '160', '12', '220'),
(NULL, 'dc5800', '3000', '2000', '160', '48', '2400'),
(NULL, 'presario CQ5300', '2300', '2000', '320', '24', '150');
