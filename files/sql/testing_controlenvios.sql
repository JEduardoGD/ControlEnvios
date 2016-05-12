-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 12, 2016 at 04:56 PM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `controlenvios`
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
(2, 'abogacia'),
(3, 'contaduria'),
(6, 'Joyeria'),
(7, 'Limon'),
(4, 'personal'),
(1, 'sistemas'),
(5, 'ururururur');

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
(1, 'davide tge', 2),
(2, 'jose', 2),
(3, 'asdasd', 3),
(4, 'Federico', 7),
(5, 'FulanodeTal Gomez', 1);

-- --------------------------------------------------------

--
-- Table structure for table `guia`
--

CREATE TABLE IF NOT EXISTS `guia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(45) NOT NULL,
  `observacion` varchar(45) DEFAULT NULL,
  `fecha` datetime NOT NULL,
  `otroorigen` varchar(45) DEFAULT NULL,
  `otrodestinatario` varchar(45) DEFAULT NULL,
  `otrodepartamento` varchar(45) DEFAULT NULL,
  `f_origen` int(11) DEFAULT NULL,
  `f_mensajeria` int(11) NOT NULL,
  `f_destinatario` int(11) DEFAULT NULL,
  `f_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero_UNIQUE` (`numero`),
  KEY `fk_Guia_origen1_idx` (`f_origen`),
  KEY `fk_Guia_mensajeria1_idx` (`f_mensajeria`),
  KEY `fk_Guia_destinatario1_idx` (`f_destinatario`),
  KEY `fk_guia_usuario1_idx` (`f_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `guia`
--

INSERT INTO `guia` (`id`, `numero`, `observacion`, `fecha`, `otroorigen`, `otrodestinatario`, `otrodepartamento`, `f_origen`, `f_mensajeria`, `f_destinatario`, `f_usuario`) VALUES
(1, 'c234234234324234234', NULL, '2016-05-02 16:32:42', 'Griffin', NULL, NULL, NULL, 4, 3, 1),
(2, 'v4747547645646456', NULL, '2016-05-02 17:22:42', 'Xempoala', NULL, NULL, NULL, 4, 3, 1),
(3, 'x3333', NULL, '2016-05-03 10:40:29', NULL, NULL, NULL, 2, 5, 2, 1),
(4, 'x111111', NULL, '2016-05-03 11:00:28', NULL, NULL, NULL, 2, 8, 2, 1),
(5, 'z2222', NULL, '2016-05-03 11:11:18', NULL, NULL, NULL, 2, 4, 2, 1),
(6, 'v3333', NULL, '2016-05-03 11:12:56', NULL, 'Fio perez', 'Inventario', 2, 3, NULL, 1),
(7, 'z4444', NULL, '2016-05-03 11:14:58', 'Copala', 'Carla Gutierrez', 'Inventario', NULL, 5, NULL, 1),
(8, 'b5555', NULL, '2016-05-03 11:23:30', NULL, 'Uriel Avila', 'Inventario', 1, 3, NULL, 1),
(9, 'g44444', NULL, '2016-05-03 11:56:02', NULL, NULL, NULL, 1, 4, 1, 1),
(10, 'd4444', NULL, '2016-05-03 11:57:02', NULL, 'Ernesto Gil', 'Inventario', 2, 7, NULL, 1),
(11, 'c3333', NULL, '2016-05-03 12:38:32', NULL, 'Ernestina Gtr', 'Inventario', 1, 3, NULL, 1),
(12, 'v4444', NULL, '2016-05-04 09:51:32', NULL, NULL, NULL, 1, 3, 1, 1),
(13, 'd3333', NULL, '2016-05-04 09:53:02', NULL, NULL, NULL, 1, 3, 1, 1),
(14, 'f4444', NULL, '2016-05-04 09:54:29', NULL, NULL, NULL, 2, 3, 1, 1),
(17, 'c333', NULL, '2016-05-04 10:04:53', NULL, 'Ernesto Gil', 'Inventario', 1, 4, NULL, 1),
(18, 't70003', NULL, '2016-05-05 16:52:08', NULL, NULL, NULL, 1, 4, 2, 1),
(19, 'l1238621375', NULL, '2016-05-06 13:01:01', 'Xalapa', NULL, NULL, NULL, 13, 4, 1),
(20, 'H72735453', NULL, '2016-05-06 13:01:41', 'Chiapas', 'Perez', 'Cocina', NULL, 3, NULL, 1),
(21, 'f33333', NULL, '2016-05-06 13:26:17', NULL, 'Ramon Velazco', 'Cocina', 2, 4, NULL, 1),
(22, 'f333332', NULL, '2016-05-06 13:26:35', NULL, 'Gil Marquez', 'Cocina', 2, 4, NULL, 1),
(23, 'd63663', NULL, '2016-05-06 13:39:02', NULL, 'Carla Moreno', 'Cocina', 3, 11, NULL, 1),
(24, 'd333311', 'Retardo', '2016-05-06 14:01:54', NULL, NULL, NULL, 6, 11, 2, 1),
(25, 'h666', '', '2016-05-06 14:02:14', NULL, NULL, NULL, 1, 11, 2, 1),
(26, 'b72727272', '', '2016-05-09 13:39:54', NULL, NULL, NULL, 1, 7, 3, 1),
(27, 'l132746', 'Incluido', '2016-05-11 13:14:24', NULL, NULL, NULL, 3, 4, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mensajeria`
--

CREATE TABLE IF NOT EXISTS `mensajeria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `mensajeria`
--

INSERT INTO `mensajeria` (`id`, `nombre`) VALUES
(3, 'Columbia Pack'),
(7, 'DHL'),
(4, 'Estafeta'),
(11, 'estrella de oro'),
(8, 'fedex'),
(6, 'frech chicken'),
(9, 'La competencia'),
(12, 'la que sea'),
(13, 'Ntp Envios'),
(10, 'packet quick'),
(5, 'Quick learning');

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
(6, 'Monterrey'),
(5, 'polala'),
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
  `password` varchar(45) NOT NULL,
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
(1, 'david javier', 'david', 'david', 1),
(2, 'admin', 'admin', 'admin', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
