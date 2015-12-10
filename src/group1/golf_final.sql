-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2015 at 07:09 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `golf_final`
--
CREATE DATABASE IF NOT EXISTS `golf_final` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `golf_final`;

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE IF NOT EXISTS `players` (
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `handicap` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `average` int(11) DEFAULT NULL,
  `times_played` int(11) DEFAULT NULL,
  `team` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`first_name`, `last_name`, `handicap`, `score`, `rank`, `average`, `times_played`, `team`) VALUES
('Big', 'Jimmy', 18, 0, NULL, 0, 0, 'TeamTwo'),
('John', 'Adams', 22, 32, NULL, 0, 1, 'TeamTwo'),
('Thomas', 'Jefferson', 19, 54, NULL, 0, 1, 'TeamTwo'),
('James', 'Madison', 24, 12, NULL, 0, 1, 'TeamTwo'),
('James', 'Monroe', 16, 142, NULL, 0, 2, 'TeamOne'),
('JohnQuincy', 'Adams', 25, 43, NULL, 0, 1, 'TeamOne'),
('Joey', 'Calzone', 12, 0, NULL, 0, 0, 'TeamOne'),
('Martin', 'VanBuren', 20, 3, NULL, 0, 1, 'TeamOne'),
('WilliamHenry', 'Harrison', 24, 24, NULL, 0, 1, 'TeamThree'),
('John', 'Tyler', 9, 22, NULL, 0, 1, 'TeamThree'),
('JamesKnox', 'Polk', 11, 76, NULL, 0, 1, 'TeamThree'),
('Zachary', 'Taylor', 27, 66, NULL, 0, 1, 'TeamThree'),
('Millard', 'Fillmore', 24, 87, NULL, 0, 1, 'TeamFour'),
('Franklin', 'Pierce', 10, 99, NULL, 0, 1, 'TeamFour'),
('James', 'Buchanan', 30, 39, NULL, 0, 1, 'TeamFour'),
('Abraham', 'Lincoln', 20, 57, NULL, 0, 1, 'TeamFour'),
('Andrew', 'Johnson', 26, 11, NULL, 0, 1, 'TeamFive'),
('Ulysses', 'Grant', 14, 19, NULL, 0, 1, 'TeamFive'),
('Rutherford', 'Hayes', 17, 44, NULL, 0, 1, 'TeamFive'),
('James', 'Garfield', 28, 10, NULL, 0, 1, 'TeamFive'),
('Chester', 'Arthur', 16, 45, NULL, 0, 1, 'TeamSix'),
('Grover', 'Cleveland', 24, 36, NULL, 0, 1, 'TeamSix'),
('Benjamin', 'Harrison', 23, 81, NULL, 0, 1, 'TeamSix'),
('William', 'McKinley', 19, 7, NULL, 0, 1, 'TeamSix');

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE IF NOT EXISTS `teams` (
  `team_name` varchar(40) NOT NULL,
  `team_score` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`team_name`, `team_score`) VALUES
('TeamTwo', 130),
('TeamOne', 221),
('TeamThree', 188),
('TeamFour', 282),
('TeamFive', 84),
('TeamSix', 169);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
