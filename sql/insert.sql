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
INSERT INTO product (maker, model, type) VALUES ('Xerox', 'Workcentre 5020', 'printer');
 
INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) 
  VALUES (NULL, 'yoga 900', '2000', '8000', '500', '2000', '13');
INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (NULL, 'x250', '1500', '4000', '256', '600', '12');
INSERT INTO `laptop` (`code`, `model`, `speed`, `ram`, `hd`, `price`, `screen`) VALUES (NULL, 'EliteBook 2560p', '3000', '4000', '2128', '400', '12');


INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, '8300 Elite', '3200', '8000', '500', '0', '184');
INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, 'Aspire Desktop', '2400', '8000', '1000', '8', '700');
INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES (NULL, 'Compaq CQ5210F', '4000', '3000', '500', '0', '400');

INSERT INTO `pc` (`code`, `model`, `speed`, `ram`, `hd`, `cd`, `price`) VALUES
(NULL, '2017 HP Pavilion Slimline 400 Flagship Premium', '1400', '4', '500', '24', '380'),
(NULL, '24-r028ur', '2900', '4000', '1000', '24', '1100'),
(NULL, 'Aspire Desktop', '3500', '12000', '2000', '8', '940'),
(NULL, 'Optiplex 780 Minitower', '3000', '4000', '160', '12', '220'),
(NULL, 'dc5800', '3000', '2000', '160', '48', '2400'),
(NULL, 'presario CQ5300', '2300', '2000', '320', '24', '150');