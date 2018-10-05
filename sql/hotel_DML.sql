use hotel;
INSERT INTO client (client_id, client_name) VALUES 
	(1, 'Customer One'),
	(2, 'John'),
	(3, 'Mike'),
	(4, 'Tim'),
	(5, 'Alex'),
	(6, 'Sasha'),
	(7, 'Polina'),
	(8, 'Nata'),
	(9, 'Toma');

INSERT INTO room (room_id, room_number, room_capacity, room_roomstar, room_roomstatus, room_price) VALUES 
('8', '88', '8', 'THREE', 'AVAILABLE', '8'),
('3', '33', '1', 'TWO', 'OCCUPIED', '10'),
('1', '11', '2', 'ONE', 'AVAILABLE', '10'),
('2', '22', '4', 'ONE', 'AVAILABLE', '10'),
('4', '66', '1', 'THREE', 'AVAILABLE', '30'),
('5', '77', '2', 'THREE', 'AVAILABLE', '35'),
('6', '44', '6', 'FOUR', 'AVAILABLE', '50'),
('7', '55', '2', 'FIVE', 'OCCUPIED', '80');

INSERT INTO service (service_id, service_code, service_name, service_price) VALUES 
 (1, '3', 'Ironing', '3'),
 (2, '1', 'Cleaning', '5'),
 (3, '2', 'Delivery', '10'),
 (4, '4', 'Music', '15'),
 (5, '5', 'Key', '20');

INSERT INTO `order` (order_id, order_num, order_client_id, order_room_id, order_start_date, order_finish_date) VALUES 
(1, '1', '1', '1', '2018-07-10', '2018-07-14'),
(2, '2', '2', '2', '2018-07-11', '2018-07-12'),
(3, '3', '3', '3', '2018-07-08', '2018-07-10'),
(4, '4', '4', '6', '2018-07-14', '2018-07-16'),
(5, '5', '1', '6', '2018-07-10', '2018-07-12'),
(6, '6', '5', '7', '2018-07-25', '2018-07-25'),
(7, '7', '6', '7', '2018-07-26', null),
(8, '10', '3', '3', '2018-08-06', null),
(9, '11', '2', '2', '2018-07-29', '2018-07-31'),
(10, '10', '3', '8', '2018-08-01', '2018-08-08'),
(11, '11', '3', '8', '2018-07-01', '2018-07-08');

INSERT INTO service_order (sc_id, sc_service_id, sc_order_id) VALUES 
 (1, 1, 1),
 (2, 2, 1),
 (3, 3, 1),
 (4, 2, 2),
 (5, 2, 3),
 (6, 2, 9);