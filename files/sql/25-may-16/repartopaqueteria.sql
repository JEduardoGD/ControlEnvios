-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 25, 2016 at 03:26 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `repartopaqueteria`
--

-- --------------------------------------------------------

--
-- Table structure for table `departamento`
--

CREATE TABLE IF NOT EXISTS `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `departamento`
--

INSERT INTO `departamento` (`id`, `nombre`) VALUES
(2, 'Abogados'),
(7, 'Autores'),
(3, 'Contaduria'),
(5, 'Directivos'),
(6, 'Mantenimiento'),
(4, 'Personal'),
(1, 'Sistemas');

-- --------------------------------------------------------

--
-- Table structure for table `destinatario`
--

CREATE TABLE IF NOT EXISTS `destinatario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `f_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  KEY `fk_destinatario_Departamento1_idx` (`f_departamento`)
) ENGINE=InnoDB  DEFAULT CHARSET=dec8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `destinatario`
--

INSERT INTO `destinatario` (`id`, `nombre`, `f_departamento`) VALUES
(1, 'Miguel Mtz', 2),
(2, 'Jose Otero', 2),
(3, 'Luis Gtz', 3),
(4, 'Federico Mtz', 7),
(5, 'Maria Gomez', 1);

-- --------------------------------------------------------

--
-- Table structure for table `guia`
--

CREATE TABLE IF NOT EXISTS `guia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(45) DEFAULT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `otroorigen` varchar(45) DEFAULT NULL,
  `otrodestinatario` varchar(45) DEFAULT NULL,
  `otrodepartamento` varchar(45) DEFAULT NULL,
  `f_origen` int(11) DEFAULT NULL,
  `f_mensajeria` int(11) DEFAULT NULL,
  `f_destinatario` int(11) DEFAULT NULL,
  `f_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Guia_origen1_idx` (`f_origen`),
  KEY `fk_Guia_mensajeria1_idx` (`f_mensajeria`),
  KEY `fk_Guia_destinatario1_idx` (`f_destinatario`),
  KEY `fk_guia_usuario1_idx` (`f_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=86 ;

--
-- Dumping data for table `guia`
--

INSERT INTO `guia` (`id`, `numero`, `observacion`, `fecha`, `otroorigen`, `otrodestinatario`, `otrodepartamento`, `f_origen`, `f_mensajeria`, `f_destinatario`, `f_usuario`) VALUES
(1, 'c234234234324234234', NULL, '2016-05-02 16:32:42', 'Griffin', NULL, NULL, NULL, 4, 3, 1),
(2, 'v4747547645646456', NULL, '2016-05-02 17:22:42', 'Xempoala', NULL, NULL, NULL, 4, 3, 1),
(3, 'x3333', NULL, '2016-05-03 10:40:29', NULL, NULL, NULL, 2, 5, 2, 1),
(4, 'x111111', NULL, '2016-05-03 11:00:28', NULL, NULL, NULL, 2, 8, 2, 1),
(5, 'z2222', NULL, '2016-05-03 11:11:18', NULL, NULL, NULL, 2, 4, 2, 1),
(6, 'v3333', NULL, '2016-05-03 11:12:56', NULL, 'Fio perez', 'inventario', 2, 3, NULL, 1),
(7, 'z4444', NULL, '2016-05-03 11:14:58', 'Copala', 'Carla Gutierrez', 'inventario', NULL, 5, NULL, 1),
(8, 'b5555', NULL, '2016-05-03 11:23:30', NULL, 'Uriel Avila', 'inventario', 1, 3, NULL, 1),
(9, 'g44444', NULL, '2016-05-03 11:56:02', NULL, NULL, NULL, 1, 4, 1, 1),
(10, 'd4444', NULL, '2016-05-03 11:57:02', NULL, 'Ernesto Gil', 'inventario', 2, 7, NULL, 1),
(11, 'c3333', NULL, '2016-05-03 12:38:32', NULL, 'Ernestina Gtr', 'inventario', 1, 3, NULL, 1),
(12, 'v4444', NULL, '2016-05-04 09:51:32', NULL, NULL, NULL, 1, 3, 1, 1),
(13, 'd3333', NULL, '2016-05-04 09:53:02', NULL, NULL, NULL, 1, 3, 1, 1),
(14, 'f4444', NULL, '2016-05-04 09:54:29', NULL, NULL, NULL, 2, 3, 1, 1),
(17, 'c333', NULL, '2016-05-04 10:04:53', NULL, 'Ernesto Gil', 'inventario', 1, 4, NULL, 1),
(18, 't70003', NULL, '2016-05-05 16:52:08', NULL, NULL, NULL, 1, 4, 2, 1),
(19, 'l1238621375', NULL, '2016-05-06 13:01:01', 'Xalapa', NULL, NULL, NULL, 13, 4, 1),
(20, 'H72735453', NULL, '2016-05-06 13:01:41', 'Chiapas', 'Perez', 'cocina', NULL, 3, NULL, 1),
(21, 'f33333', NULL, '2016-05-06 13:26:17', NULL, 'Ramon Velazco', 'cocina', 2, 4, NULL, 1),
(22, 'f333332', NULL, '2016-05-06 13:26:35', NULL, 'Gil Marquez', 'cocina', 2, 4, NULL, 1),
(23, 'd63663', NULL, '2016-05-06 13:39:02', NULL, 'Carla Moreno', 'cocina', 3, 11, NULL, 1),
(24, 'd333311', 'Retardo', '2016-05-06 14:01:54', NULL, NULL, NULL, 6, 11, 2, 1),
(25, 'h666', '', '2016-05-06 14:02:14', NULL, NULL, NULL, 1, 11, 2, 1),
(26, 'b72727272', '', '2016-05-09 13:39:54', NULL, NULL, NULL, 1, 7, 3, 1),
(27, 'l132746', 'Incluido', '2016-05-11 13:14:24', NULL, NULL, NULL, 3, 4, 3, 1),
(28, 'K82826541132', NULL, '2016-05-13 13:49:07', NULL, 'Creed', 'escritores', 7, NULL, NULL, 1),
(29, 'F111221002', '', '2016-05-14 09:46:52', NULL, NULL, NULL, 2, 7, 1, 1),
(30, 'E9997321002', 'Retraso', '2016-05-15 09:47:23', NULL, NULL, NULL, 1, 7, 4, 1),
(31, 'Y666397321002', '', '2016-05-15 09:47:38', NULL, NULL, NULL, 6, 4, 4, 1),
(32, 'T333397321002', '', '2016-05-16 09:47:58', NULL, NULL, NULL, 6, 9, 3, 1),
(33, 'GH33397321002', '', '2016-05-17 09:48:10', NULL, NULL, NULL, 7, 11, 3, 1),
(34, 'NNH33397321002', NULL, '2016-05-16 09:48:29', NULL, 'Jaime Luis', 'inventario', 7, 11, NULL, 1),
(35, 'K3397321002', NULL, '2016-05-16 09:48:36', NULL, 'Jaime Luis', 'inventario', 7, 11, NULL, 1),
(36, 'K999297321002', NULL, '2016-05-16 09:48:49', NULL, 'Luis David', 'inventario', 7, 11, NULL, 1),
(37, 'L8873uwwg', '', '2016-05-18 09:52:20', NULL, NULL, NULL, 6, 3, 1, 1),
(38, 'O88228873uwwg', '', '2016-05-16 09:52:27', NULL, NULL, NULL, 6, 3, 2, 1),
(39, 'P8228873uwwg', '', '2016-05-25 09:52:50', NULL, NULL, NULL, 6, 3, 2, 1),
(40, 'H7171763222', '', '2016-05-20 09:57:54', NULL, NULL, NULL, 2, 12, 1, 1),
(41, 'E111111111', '', '2016-05-24 09:59:50', NULL, NULL, NULL, 2, 12, 1, 1),
(42, 'Er22222222', '', '2016-05-22 10:07:25', NULL, NULL, NULL, 2, 12, 2, 1),
(43, 'J88882522122', '', '2016-05-17 13:39:45', NULL, NULL, NULL, 2, NULL, 2, 1),
(44, 'F888821122231', '', '2016-05-20 09:36:59', NULL, NULL, NULL, 6, 12, 3, 1),
(45, 'F865121122231', '', '2016-05-20 09:37:12', NULL, NULL, NULL, 6, 12, 3, 1),
(46, 'K999221122231', '', '2016-05-20 09:37:19', NULL, NULL, NULL, 6, 12, 3, 1),
(47, '000221122231', '', '2016-05-20 09:37:25', NULL, NULL, NULL, 6, 12, 3, 1),
(48, 'O000211122231', '', '2016-05-20 09:37:36', NULL, NULL, NULL, 6, 11, 3, 1),
(49, 'O22211122231', '', '2016-05-21 09:37:41', NULL, NULL, NULL, 6, 11, 3, 1),
(50, 'Pqqq12211122231', '', '2016-05-22 09:37:46', NULL, NULL, NULL, 6, 11, 3, 1),
(51, 'P012211122231', '', '2016-05-23 09:37:51', NULL, NULL, NULL, 6, 11, 3, 1),
(52, 'P00198211122231', '', '2016-05-24 09:37:58', NULL, NULL, NULL, 6, 11, 3, 1),
(53, 'K88828211122231', '', '2016-05-25 09:38:04', NULL, NULL, NULL, 6, 11, 3, 1),
(54, '9211122231', '', '2016-05-26 09:38:18', NULL, NULL, NULL, 7, 11, 2, 1),
(55, 'Lll211122231', '', '2016-05-22 09:38:25', NULL, NULL, NULL, 7, 11, 2, 1),
(56, 'Q001122231', '', '2016-05-23 09:38:40', NULL, NULL, NULL, 7, 11, 2, 1),
(57, 'Q011122231', '', '2016-05-24 09:38:45', NULL, NULL, NULL, 7, 11, 2, 1),
(58, 'Q0211122231', '', '2016-05-25 09:38:50', NULL, NULL, NULL, 7, 11, 2, 1),
(59, 'Q3111122231', '', '2016-05-20 09:38:57', NULL, NULL, NULL, 7, 11, 1, 1),
(60, 'Q43111122231', '', '2016-05-20 09:39:01', NULL, NULL, NULL, 7, 11, 1, 1),
(61, 'Q5111122231', '', '2016-05-23 09:39:05', NULL, NULL, NULL, 7, 11, 1, 1),
(62, 'Q65111122231', '', '2016-05-24 09:39:10', NULL, NULL, NULL, 7, 11, 1, 1),
(63, 'Q77111122231', '', '2016-05-20 09:39:17', NULL, NULL, NULL, 7, 11, 1, 1),
(64, 'Q977111122231', '', '2016-05-18 09:39:22', NULL, NULL, NULL, 7, 11, 1, 1),
(65, 'Q80977111122231', '', '2016-05-16 09:39:26', NULL, NULL, NULL, 7, 11, 1, 1),
(66, 'O000028811', '', '2016-05-21 09:43:08', NULL, NULL, NULL, 4, 9, 3, 1),
(67, 'Q200028811', '', '2016-05-20 09:43:14', NULL, NULL, NULL, 4, 9, 3, 1),
(68, 'Q1100028811', '', '2016-05-21 09:43:18', NULL, NULL, NULL, 4, 9, 3, 1),
(69, 'A22100028811', '', '2016-05-22 09:43:23', NULL, NULL, NULL, 4, 9, 3, 1),
(70, 'U8822100028811', '', '2016-05-23 09:43:30', NULL, NULL, NULL, 4, 9, 3, 1),
(71, 'E11122100028811', '', '2016-05-24 09:43:34', NULL, NULL, NULL, 4, 9, 3, 1),
(72, 'S222221', '', '2016-05-23 10:04:09', NULL, NULL, NULL, 1, NULL, 1, 1),
(73, 'J8383827', '', '2016-05-24 13:08:32', NULL, NULL, NULL, 2, 3, 1, 1),
(74, 'ddddee333', '', '2016-05-24 13:30:42', NULL, NULL, NULL, 2, 3, 2, 1),
(78, 'S22221434', '', '2016-05-24 14:13:17', NULL, NULL, NULL, 2, 7, 1, 1),
(79, NULL, '', '2016-05-24 14:13:40', NULL, NULL, NULL, 2, NULL, 1, 1),
(80, NULL, '', '2016-05-24 14:13:44', NULL, NULL, NULL, 2, NULL, 1, 1),
(81, '', '', '2016-05-24 14:14:36', NULL, NULL, NULL, 2, NULL, 3, 1),
(82, NULL, '', '2016-05-24 14:15:23', NULL, NULL, NULL, 2, NULL, 3, 1),
(83, 'D1112001', '', '2016-05-24 14:29:59', NULL, NULL, NULL, 2, 12, 1, 1),
(84, NULL, 'asdsadad', '2016-05-24 14:30:31', NULL, NULL, NULL, 2, NULL, 1, 1),
(85, 'O00002122', '', '2016-05-25 13:02:49', NULL, NULL, NULL, 2, 7, 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mensajeria`
--

CREATE TABLE IF NOT EXISTS `mensajeria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `mensajeria`
--

INSERT INTO `mensajeria` (`id`, `nombre`) VALUES
(3, 'Columbia Pack'),
(12, 'Cotsco'),
(7, 'DHL'),
(4, 'Estafeta'),
(9, 'Estrella blanca'),
(11, 'Estrella de oro'),
(8, 'fedex'),
(13, 'Ntp Envios'),
(10, 'packet quick'),
(5, 'Quick learning'),
(6, 'Redex'),
(14, 'x envios');

-- --------------------------------------------------------

--
-- Table structure for table `origen`
--

CREATE TABLE IF NOT EXISTS `origen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `origen`
--

INSERT INTO `origen` (`id`, `nombre`) VALUES
(2, 'Acapulco'),
(1, 'Cuernavaca'),
(5, 'Culhuacan'),
(6, 'Monterrey'),
(7, 'Sampoala'),
(3, 'Uxmal'),
(4, 'Xochiltepec');

-- --------------------------------------------------------

--
-- Table structure for table `tiposusuario`
--

CREATE TABLE IF NOT EXISTS `tiposusuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tipo_UNIQUE` (`tipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tiposusuario`
--

INSERT INTO `tiposusuario` (`id`, `tipo`) VALUES
(2, 'administrador'),
(1, 'capturista');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `username` varchar(45) NOT NULL,
  `f_tiposusuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`name`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_usuario_tiposusuario1_idx` (`f_tiposusuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `name`, `password`, `username`, `f_tiposusuario`) VALUES
(1, 'david javier', '07d046d5fac12b3f82daf5035b9aae86db5adc8275ebfbf05ec83005a4a8ba3e', 'david', 1),
(2, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `guia`
--
ALTER TABLE `guia`
  ADD CONSTRAINT `fk_guia_destinatario1` FOREIGN KEY (`f_destinatario`) REFERENCES `destinatario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_mensajeria1` FOREIGN KEY (`f_mensajeria`) REFERENCES `mensajeria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_origen1` FOREIGN KEY (`f_origen`) REFERENCES `origen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_usuario1` FOREIGN KEY (`f_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
