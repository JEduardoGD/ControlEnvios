-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 27, 2016 at 04:27 PM
-- Server version: 5.5.49
-- PHP Version: 5.4.45-3+donate.sury.org~precise+3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `repartopaqueteria`
--

--
-- Dumping data for table `departamento`
--

INSERT INTO `departamento` (`id`, `nombre`) VALUES
(14, 'americana de Inmuebles'),
(3, 'contabilidad'),
(9, 'control de casas'),
(5, 'crédito casas'),
(6, 'crédito senior'),
(15, 'dirección general'),
(8, 'editorial'),
(13, 'jolinllin'),
(7, 'la viga'),
(10, 'marketing'),
(4, 'personal'),
(2, 'producción'),
(12, 'publicidad'),
(11, 'regalias'),
(1, 'sistemas');

--
-- Dumping data for table `destinatario`
--

INSERT INTO `destinatario` (`id`, `nombre`, `f_departamento`) VALUES
(1, 'Jonathan Castellanos', 3),
(2, 'Antonio Leal', 3),
(3, 'Raúl Cassani', 3),
(4, 'Georgina González', 3),
(5, 'Armando Saavedra', 3),
(6, 'Roberto Olivares', 3),
(7, 'Esther Ruedas', 3),
(8, 'Carmen Velazquez', 3),
(9, 'Alfonso Perea', 3),
(10, 'Lic. Miguel Angel Hdez', 1),
(11, 'Norma León', 1),
(12, 'José Molina', 1),
(13, 'Felipe Amezquita', 1),
(14, 'Ing. Lander Trillas', 2),
(15, 'Mercedes García', 2),
(16, 'Lic. Galera', 7),
(17, 'Rosario Barrera', 7),
(18, 'Irene Yepez', 7),
(19, 'Francisco Bermejo', 7),
(20, 'Ma. Del Carmen Gtez.', 5),
(21, 'Natividad Nicolas', 5),
(22, 'Esmeralda Vazquez', 5),
(23, 'Velia Valdez', 5),
(24, 'Maru Ruedas', 5),
(25, 'Elizabeth Rivera', 5),
(26, 'Ricardo Rodriguez', 5),
(27, 'Guadalupe Alvarado', 6),
(28, 'Carlos Rodríguez', 6),
(29, 'Norma Yepez', 6),
(30, 'Cristian Cruz', 6),
(31, 'Carolina Ramírez', 6),
(32, 'Carlos Trillas', 8),
(33, 'Nora Fuentes', 8),
(34, 'Nadia Alvarez', 8),
(35, 'Laura Ignacio', 8),
(36, 'Verónica Lamas', 8),
(37, 'Ruben Oliver', 8),
(38, 'Lucia Ramírez', 8),
(39, 'Fernando Flores', 8),
(40, 'Jorge Durán', 8),
(41, 'Alma Loera', 8),
(42, 'Lourdes López', 8),
(43, 'Cyntia Domínguez', 8),
(44, 'Priscila Harfusch', 8),
(45, 'Jessy Perez', 8),
(46, 'Alvaro Espinosa', 8),
(47, 'Juan Felipe O', 9),
(48, 'Verónica Peña', 9),
(49, 'Rosy Silva', 9),
(50, 'Hugo', 9),
(51, 'Lety Villacetin', 4),
(52, 'Martha', 4),
(53, 'Estela Bazan', 4),
(54, 'Adriana Rico', 4),
(55, 'Armando Ramírez', 4),
(56, 'Abel Hinojosa', 4),
(57, 'Carmen Pedrero', 4),
(58, 'Yascara Mondragón', 10),
(59, 'Isabel Marquez', 10),
(60, 'Erika Campos', 10),
(61, 'Marcela Torres', 11),
(62, 'Jonathan', 11),
(63, 'Sergio Shinji', 12),
(64, 'Celina Reyes', 12),
(65, 'Lic. Fernando Trillas', 15),
(66, 'Victoria Carrazco', 15),
(67, 'Monica Aparicio', 15),
(68, 'Magdalena Vivar', 13),
(69, 'Thania Guillen', 13),
(70, 'Isabel Mendez', 14);

--
-- Dumping data for table `guia`
--

INSERT INTO `guia` (`id`, `numero`, `observacion`, `fecha`, `otroorigen`, `otrodestinatario`, `otrodepartamento`, `f_origen`, `f_mensajeria`, `f_destinatario`, `f_usuario`) VALUES
(31, '1013393383891133537600809602826011', NULL, '2016-05-27 12:06:34', NULL, NULL, NULL, 2, 8, 20, 3);

--
-- Dumping data for table `mensajeria`
--

INSERT INTO `mensajeria` (`id`, `nombre`) VALUES
(13, 'Aeroflash'),
(11, 'Correos de México'),
(7, 'DHL'),
(4, 'Estafeta'),
(9, 'Estrella blanca'),
(8, 'FEDEX'),
(15, 'MexPost'),
(12, 'ODM'),
(10, 'Red Pack'),
(3, 'UPS');

--
-- Dumping data for table `origen`
--

INSERT INTO `origen` (`id`, `nombre`) VALUES
(2, 'Acapulco'),
(52, 'Administracion P.L. Molinos'),
(19, 'Aguascalientes'),
(51, 'Alm. Gral. Otros Fondos'),
(35, 'Cancún'),
(36, 'Chihuahua'),
(12, 'Ciudad Juárez (Las Torres)'),
(47, 'Ciudad Juárez (Río grande)'),
(43, 'Ciudad Juárez (Sendero)'),
(48, 'Coacalco'),
(32, 'Colombia'),
(7, 'Cuauhtémoc'),
(1, 'Cuernavaca'),
(46, 'Durango'),
(13, 'Guadalajara'),
(42, 'Guadalajara Kiosco'),
(37, 'Interlomas'),
(17, 'Ixtapaluca'),
(44, 'León (Campestre)'),
(38, 'León (Centro)'),
(21, 'Lerma'),
(24, 'Mérida'),
(41, 'Metepec'),
(6, 'Monterrey'),
(8, 'Morelia'),
(15, 'Oaxaca'),
(25, 'Orizaba'),
(16, 'Pachuca'),
(22, 'Politécnico (Santo Tomás)'),
(23, 'Politécnico (Zacatenco)'),
(39, 'Puebla (Centro)'),
(9, 'Puebla (Dorada)'),
(26, 'Querétaro'),
(27, 'Reynosa'),
(28, 'Saltillo'),
(10, 'San luis potosí'),
(20, 'Tampico'),
(11, 'Tijuana'),
(18, 'Toluca (Centro)'),
(29, 'Torreón'),
(30, 'Tuxtla Gutierrez'),
(50, 'U.A.N.L'),
(49, 'Vallarta Bodega'),
(34, 'Vallarta puerto'),
(33, 'Venezuela'),
(4, 'Veracruz'),
(40, 'Villahermosa'),
(31, 'Xalapa');

--
-- Dumping data for table `tiposusuario`
--

INSERT INTO `tiposusuario` (`id`, `tipo`) VALUES
(2, 'administrador'),
(1, 'capturista');

--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id`, `name`, `password`, `username`, `f_tiposusuario`) VALUES
(3, 'Juanita', '7a859b0e224b9e8ffab84c07541d9672d846aa568e4a728782e9c925bc5cd7f8', 'juanita', 1),
(5, 'javier', 'da52a0582288ca37437c646d2f9ad1b607c4d671eeae234585fb71488a5aa2bd', 'gjavier', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
