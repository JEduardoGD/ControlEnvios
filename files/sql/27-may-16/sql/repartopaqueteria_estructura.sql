-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 27, 2016 at 11:20 AM
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=dec8 AUTO_INCREMENT=71 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=31 ;

-- --------------------------------------------------------

--
-- Table structure for table `mensajeria`
--

CREATE TABLE IF NOT EXISTS `mensajeria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Table structure for table `origen`
--

CREATE TABLE IF NOT EXISTS `origen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=43 ;

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
  KEY `f_tiposusuario` (`f_tiposusuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `destinatario`
--
ALTER TABLE `destinatario`
  ADD CONSTRAINT `fk_destinatario_departamento1` FOREIGN KEY (`f_departamento`) REFERENCES `departamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `guia`
--
ALTER TABLE `guia`
  ADD CONSTRAINT `fk_guia_destinatario1` FOREIGN KEY (`f_destinatario`) REFERENCES `destinatario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_mensajeria1` FOREIGN KEY (`f_mensajeria`) REFERENCES `mensajeria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_origen1` FOREIGN KEY (`f_origen`) REFERENCES `origen` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guia_usuario1` FOREIGN KEY (`f_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`f_tiposusuario`) REFERENCES `tiposusuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
