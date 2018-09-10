-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for payment_system
CREATE DATABASE IF NOT EXISTS `payment_system` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `payment_system`;

-- Dumping structure for table payment_system.addresses
CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.addresses: 0 rows
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table payment_system.bills
CREATE TABLE IF NOT EXISTS `bills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `currency_id` int(11) DEFAULT NULL,
  `service_id` bigint(20) DEFAULT NULL,
  `subscriber_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKyfik8bx7lgy1995gbqsd6tnq` (`currency_id`),
  KEY `FKtcqk28u48nsf09eaxwuo6v2an` (`service_id`),
  KEY `FK85l0oawldwk1vsji4ndl4n8v0` (`subscriber_id`)
) ENGINE=MyISAM AUTO_INCREMENT=272 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.bills: 120 rows
/*!40000 ALTER TABLE `bills` DISABLE KEYS */;
INSERT INTO `bills` (`id`, `amount`, `end_date`, `payment_date`, `start_date`, `currency_id`, `service_id`, `subscriber_id`) VALUES
	(152, 45, '2018-09-01', NULL, '2018-08-01', 1, 2, 1),
	(153, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 1),
	(154, 20, '2018-09-01', NULL, '2018-08-01', 1, 3, 1),
	(155, 20, '2018-08-01', NULL, '2018-07-01', 1, 2, 1),
	(156, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 1),
	(157, 45, '2018-08-01', NULL, '2018-07-01', 1, 3, 1),
	(158, 65, '2018-08-01', NULL, '2018-07-01', 1, 2, 2),
	(159, 35, '2018-08-01', NULL, '2018-07-01', 1, 1, 2),
	(160, 48, '2018-08-01', NULL, '2018-07-01', 1, 3, 2),
	(161, 56, '2018-09-01', NULL, '2018-08-01', 1, 3, 2),
	(162, 35, '2018-09-01', NULL, '2018-08-01', 1, 1, 2),
	(163, 45, '2018-09-01', NULL, '2018-08-01', 1, 2, 2),
	(164, 45, '2018-09-01', NULL, '2018-08-01', 1, 2, 3),
	(165, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 3),
	(166, 80, '2018-09-01', NULL, '2018-08-01', 1, 3, 3),
	(167, 62, '2018-08-01', NULL, '2018-07-01', 1, 3, 3),
	(168, 20, '2018-08-01', NULL, '2018-07-01', 1, 1, 3),
	(169, 45, '2018-08-01', NULL, '2018-07-01', 1, 2, 3),
	(170, 45, '2018-08-01', NULL, '2018-07-01', 1, 2, 4),
	(171, 40, '2018-08-01', NULL, '2018-07-01', 1, 1, 4),
	(172, 40, '2018-08-01', NULL, '2018-07-01', 1, 3, 4),
	(173, 40, '2018-09-01', NULL, '2018-08-01', 1, 3, 4),
	(174, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 4),
	(175, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 4),
	(176, 60, '2018-09-01', NULL, '2018-08-01', 1, 2, 5),
	(177, 40, '2018-09-01', NULL, '2018-08-01', 1, 1, 5),
	(178, 100, '2018-09-01', NULL, '2018-08-01', 1, 3, 5),
	(179, 100, '2018-08-01', NULL, '2018-07-01', 1, 3, 5),
	(180, 40, '2018-08-01', NULL, '2018-07-01', 1, 1, 5),
	(181, 80, '2018-08-01', NULL, '2018-07-01', 1, 2, 5),
	(182, 42, '2018-08-01', NULL, '2018-07-01', 1, 2, 6),
	(183, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 6),
	(184, 60, '2018-08-01', NULL, '2018-07-01', 1, 3, 6),
	(185, 62.8, '2018-09-01', NULL, '2018-08-01', 1, 3, 6),
	(186, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 6),
	(187, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 6),
	(188, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 7),
	(189, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 7),
	(190, 45.8, '2018-09-01', NULL, '2018-08-01', 1, 3, 7),
	(191, 68.2, '2018-08-01', NULL, '2018-07-01', 1, 3, 7),
	(192, 30, '2018-08-01', NULL, '2018-07-01', 1, 1, 7),
	(193, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 7),
	(194, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 8),
	(195, 20, '2018-08-01', NULL, '2018-07-01', 1, 1, 8),
	(196, 25.6, '2018-08-01', NULL, '2018-07-01', 1, 3, 8),
	(197, 38.4, '2018-09-01', NULL, '2018-08-01', 1, 3, 8),
	(198, 30, '2018-09-01', NULL, '2018-08-01', 1, 2, 8),
	(199, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 8),
	(200, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 9),
	(201, 45, '2018-09-01', NULL, '2018-08-01', 1, 2, 9),
	(202, 85.8, '2018-09-01', NULL, '2018-08-01', 1, 3, 9),
	(203, 85.8, '2018-08-01', NULL, '2018-07-01', 1, 3, 9),
	(204, 30, '2018-08-01', NULL, '2018-07-01', 1, 1, 9),
	(205, 45, '2018-08-01', NULL, '2018-07-01', 1, 2, 9),
	(206, 45, '2018-08-01', NULL, '2018-07-01', 1, 2, 10),
	(207, 20, '2018-08-01', NULL, '2018-07-01', 1, 1, 10),
	(208, 74.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 10),
	(209, 63.2, '2018-09-01', NULL, '2018-08-01', 1, 3, 10),
	(210, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 10),
	(211, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 10),
	(212, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 11),
	(213, 30, '2018-09-01', NULL, '2018-08-01', 1, 1, 11),
	(214, 56.6, '2018-09-01', NULL, '2018-08-01', 1, 3, 11),
	(215, 78.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 11),
	(216, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 11),
	(217, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 11),
	(218, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 12),
	(219, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 12),
	(220, 78.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 12),
	(221, 56.1, '2018-09-01', NULL, '2018-08-01', 1, 3, 12),
	(222, 25, '2018-09-01', NULL, '2018-08-01', 1, 2, 12),
	(223, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 12),
	(224, 20, '2018-09-01', NULL, '2018-08-01', 1, 1, 13),
	(225, 35, '2018-09-01', NULL, '2018-08-01', 1, 2, 13),
	(226, 78.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 13),
	(227, 45.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 13),
	(228, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 13),
	(229, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 13),
	(230, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 14),
	(231, 30, '2018-08-01', NULL, '2018-07-01', 1, 1, 14),
	(232, 126.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 14),
	(233, 98.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 14),
	(234, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 14),
	(235, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 14),
	(236, 30, '2018-09-01', NULL, '2018-08-01', 1, 2, 15),
	(237, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 15),
	(238, 32.8, '2018-09-01', NULL, '2018-08-01', 1, 3, 15),
	(239, 45.2, '2018-08-01', NULL, '2018-07-01', 1, 3, 15),
	(240, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 15),
	(241, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 15),
	(242, 40, '2018-08-01', NULL, '2018-07-01', 1, 2, 16),
	(243, 30, '2018-08-01', NULL, '2018-07-01', 1, 1, 16),
	(244, 86.2, '2018-08-01', NULL, '2018-07-01', 1, 3, 16),
	(245, 56.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 16),
	(246, 25, '2018-09-01', NULL, '2018-08-01', 1, 2, 16),
	(247, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 16),
	(248, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 17),
	(249, 35, '2018-09-01', NULL, '2018-08-01', 1, 2, 17),
	(250, 56.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 17),
	(251, 65.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 17),
	(252, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 17),
	(253, 30, '2018-08-01', NULL, '2018-07-01', 1, 2, 17),
	(254, 30, '2018-08-01', NULL, '2018-07-01', 1, 2, 18),
	(255, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 18),
	(256, 86.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 18),
	(257, 74.2, '2018-09-01', NULL, '2018-08-01', 1, 3, 18),
	(258, 30, '2018-09-01', NULL, '2018-08-01', 1, 1, 18),
	(259, 50, '2018-09-01', NULL, '2018-08-01', 1, 2, 18),
	(260, 40, '2018-09-01', NULL, '2018-08-01', 1, 2, 19),
	(261, 30, '2018-09-01', NULL, '2018-08-01', 1, 1, 19),
	(262, 75.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 19),
	(263, 45.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 19),
	(264, 25, '2018-08-01', NULL, '2018-07-01', 1, 1, 19),
	(265, 45, '2018-08-01', NULL, '2018-07-01', 1, 2, 19),
	(266, 50, '2018-08-01', NULL, '2018-07-01', 1, 2, 20),
	(267, 30, '2018-08-01', NULL, '2018-07-01', 1, 1, 20),
	(268, 67.3, '2018-08-01', NULL, '2018-07-01', 1, 3, 20),
	(269, 45.3, '2018-09-01', NULL, '2018-08-01', 1, 3, 20),
	(270, 25, '2018-09-01', NULL, '2018-08-01', 1, 1, 20),
	(271, 45, '2018-09-01', NULL, '2018-08-01', 1, 2, 20);
/*!40000 ALTER TABLE `bills` ENABLE KEYS */;

-- Dumping structure for table payment_system.currencies
CREATE TABLE IF NOT EXISTS `currencies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(255) DEFAULT NULL,
  `exchange_rate` double DEFAULT NULL,
  `currency_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.currencies: 3 rows
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` (`id`, `currency`, `exchange_rate`, `currency_name`) VALUES
	(1, 'bgn', 1, 'bgn'),
	(2, 'usd', 1, 'usd'),
	(3, 'eur', 1, 'eur');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;

-- Dumping structure for table payment_system.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r261muslviw4d89p3xlvagqof` (`authority`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.roles: 3 rows
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `authority`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_USER'),
	(3, 'ROLE_CHANGEPASSWORD');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table payment_system.services
CREATE TABLE IF NOT EXISTS `services` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `service` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.services: 3 rows
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` (`id`, `service`, `service_name`) VALUES
	(1, 'internet\r\n', 'INTERNET'),
	(2, 'television', 'TV'),
	(3, 'telephone', 'PHONE');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;

-- Dumping structure for table payment_system.subscribers
CREATE TABLE IF NOT EXISTS `subscribers` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `egn` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `total_amount_payed` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqumsxotdjx442bkjej7iq1qf5` (`bank_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.subscribers: 20 rows
/*!40000 ALTER TABLE `subscribers` DISABLE KEYS */;
INSERT INTO `subscribers` (`id`, `egn`, `first_name`, `last_name`, `phone_number`, `bank_id`, `total_amount_payed`) VALUES
	(1, '9107153399  ', 'Tate', 'Moschell', '0889123456', 3, 0),
	(2, '8711104943 ', 'Brianna', 'Turner', '0889123654', 4, 0),
	(3, '8606177807 ', 'Stacy', 'Miller', '0899456123', 5, 0),
	(4, '8401049920 ', 'Elise', 'Hill', '089978456', 3, 0),
	(5, '8106105603 ', 'Korbin', 'Sullivan', '0899789123', 4, 0),
	(6, '1912213157 ', 'Kyleigh', 'Wood', '0899111111', 5, 0),
	(7, '2408311270 ', 'Samson', 'Carson', '0888222222', 3, 0),
	(8, '3905224608 ', 'Lyla', 'Reiter', '0898333333', 4, 0),
	(9, '8909084355 ', 'Brandon', 'Valdez', '0888123456', 5, 0),
	(10, '9006200810 ', 'Damion', 'Salavaria', '0897123456', 3, 0),
	(11, '9503081715 ', 'Arnav', 'Turner', '0888555555', 4, 0),
	(12, '6208089902 ', 'Andre', 'Diaz', '0888666666', 5, 0),
	(13, '7106089331 ', 'Kaelyn', 'Richins', '0888987654', 3, 0),
	(14, '7401163864 ', 'Sofia', 'Miller', '0899987654', 4, 0),
	(15, '7904150576 ', 'Jillian', 'Hill', '0898654321', 5, 0),
	(16, '8303180555 ', 'Ashlee', 'Sullivan', '0878123456', 3, 0),
	(17, '8403037810 ', 'Lainey', 'Wood', '0898777777', 4, 0),
	(18, '9110036741 ', 'Hayden', 'Carson', '0899888888', 5, 0),
	(19, '9303196580 ', 'Malcolm', 'Reiter', '0878444444', 3, 0),
	(20, '9704097570 ', 'Sonny', 'Valdez', '0888333333', 4, 0);
/*!40000 ALTER TABLE `subscribers` ENABLE KEYS */;

-- Dumping structure for table payment_system.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `eik` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.users: 5 rows
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `eik`, `email`, `password`, `username`, `enabled`) VALUES
	(1, '123', '2@test', '$2a$10$kTdBslDXzfyS7NfC4F9iO.zyYL9SLh5TBMjFkqfLSPYkDe81xs0Ha', 'admin2', b'1'),
	(2, '123', 'admin1@test', '$2a$10$6hXoEwCVCUaChg8a7UdmbuSaApBdI8G21SGRbhuv8Kb1XFqynjnAu', 'admin1', b'1'),
	(3, '1234567891011', 'dsk@dsk.bg', '$2a$10$nLCmKTQ.fRDxvZO3.eEsFetNsPliKyxZo6btCqpEoIaN09j6OFp3O', 'dsk-bank', b'1'),
	(4, '1234567891012', 'ubb@obb.bg', '$2a$10$WtZ8120VZkbA.hJGHUzCo.xEA5lpNyCg3fPiGVhoGg3uGOU.RJva2', 'ubb-bank', b'1'),
	(5, '1234567891013', 'rbb@rbb.bg', '$2a$10$57bnWo/Jss0ltcrB9jjoXuUnT98lb3ZydaBnY.khWPwob.FIk30lu', 'rbb-bank', b'1');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table payment_system.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  KEY `FK2o0jvgh89lemvvo17cbqvdxaa` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table payment_system.users_roles: 5 rows
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 2),
	(4, 2),
	(5, 2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
