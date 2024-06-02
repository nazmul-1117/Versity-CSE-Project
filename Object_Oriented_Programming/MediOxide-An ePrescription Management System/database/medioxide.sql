-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2024 at 11:30 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medioxide`
--
CREATE DATABASE IF NOT EXISTS `medioxide` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `medioxide`;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_personal_info`
--

DROP TABLE IF EXISTS `doctor_personal_info`;
CREATE TABLE IF NOT EXISTS `doctor_personal_info` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_name` varchar(255) NOT NULL,
  `doctor_gender` enum('Male','Female','Other') DEFAULT 'Male',
  `doctor_phone` varchar(20) DEFAULT '+880xxxx-xxxxxx',
  `doctor_email` varchar(255) DEFAULT 'example@medioxide.com',
  `doctor_nid` varchar(20) NOT NULL,
  `doctor_license_number` varchar(50) NOT NULL,
  `doctor_current_hospital` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `doctor_nid` (`doctor_nid`),
  UNIQUE KEY `doctor_license_number` (`doctor_license_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4000 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_professional_info`
--

DROP TABLE IF EXISTS `doctor_professional_info`;
CREATE TABLE IF NOT EXISTS `doctor_professional_info` (
  `doctor_id` int(11) NOT NULL,
  `doctor_department` varchar(255) DEFAULT 'Neurology',
  `doctor_specialty` varchar(255) DEFAULT NULL,
  `doctor_room_number` int(11) DEFAULT NULL,
  `doctor_degree` varchar(255) DEFAULT 'MBBS.',
  KEY `doctor_id` (`doctor_id`),
  KEY `doctor_department` (`doctor_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medical_department`
--

DROP TABLE IF EXISTS `medical_department`;
CREATE TABLE IF NOT EXISTS `medical_department` (
  `departmental_id` int(11) NOT NULL,
  `departmental_name` varchar(255) NOT NULL,
  `department_description` text DEFAULT NULL,
  `department_head` varchar(255) DEFAULT 'Prof. Dr. Nazmul Hossain',
  `department_location` varchar(255) DEFAULT NULL,
  `department_type` enum('Clinical','Non-Clinical') DEFAULT 'Clinical',
  PRIMARY KEY (`departmental_name`),
  UNIQUE KEY `departmental_id` (`departmental_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medical_problems`
--

DROP TABLE IF EXISTS `medical_problems`;
CREATE TABLE IF NOT EXISTS `medical_problems` (
  `medical_problem_id` int(11) NOT NULL AUTO_INCREMENT,
  `medical_problem_name` varchar(255) DEFAULT NULL,
  `medical_department` varchar(255) DEFAULT NULL,
  `medical_problem_description` text DEFAULT NULL,
  `medical_problem_symptoms` text DEFAULT NULL,
  `medical_problem_treatment` text DEFAULT NULL,
  PRIMARY KEY (`medical_problem_id`),
  KEY `medical_department` (`medical_department`)
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medical_tests`
--

DROP TABLE IF EXISTS `medical_tests`;
CREATE TABLE IF NOT EXISTS `medical_tests` (
  `medical_test_id` int(11) NOT NULL AUTO_INCREMENT,
  `medical_test_name` varchar(255) DEFAULT NULL,
  `medical_test_description` text DEFAULT NULL,
  `medical_test_normal_range` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`medical_test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
CREATE TABLE IF NOT EXISTS `medicine` (
  `medicine_id` int(11) NOT NULL AUTO_INCREMENT,
  `medicine_name` varchar(255) NOT NULL,
  `medicine_types` varchar(50) DEFAULT 'Tablet',
  `medicine_generic` varchar(255) DEFAULT NULL,
  `medicine_brands` varchar(255) DEFAULT NULL,
  `medicine_description` text DEFAULT NULL,
  PRIMARY KEY (`medicine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6000 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patients_medical_info`
--

DROP TABLE IF EXISTS `patients_medical_info`;
CREATE TABLE IF NOT EXISTS `patients_medical_info` (
  `patients_id` int(11) DEFAULT NULL,
  `patients_visiting_department` varchar(255) DEFAULT NULL,
  `patients_diseases` varchar(255) DEFAULT NULL,
  `patients_weight` decimal(5,2) DEFAULT NULL,
  `patients_systolic_bp` int(11) DEFAULT NULL,
  `patients_diastolic_bp` int(11) DEFAULT NULL,
  `patients_body_temp` decimal(4,2) DEFAULT NULL,
  `patients_previous_problem` varchar(255) DEFAULT NULL,
  `patients_family_problem` text DEFAULT NULL,
  KEY `patients_id` (`patients_id`),
  KEY `patients_visiting_department` (`patients_visiting_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patients_personal_info`
--

DROP TABLE IF EXISTS `patients_personal_info`;
CREATE TABLE IF NOT EXISTS `patients_personal_info` (
  `patients_id` int(11) NOT NULL AUTO_INCREMENT,
  `patients_name` varchar(255) NOT NULL,
  `patients_surname` varchar(255) DEFAULT NULL,
  `patients_age` int(11) NOT NULL,
  `patients_gender` enum('Male','Female','Other') DEFAULT 'Other',
  `patients_mobile` varchar(20) DEFAULT '+880xxxx-xxxxxx',
  `patients_email` varchar(255) DEFAULT 'example@medioxide.com',
  `patients_address` text DEFAULT NULL,
  PRIMARY KEY (`patients_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3000 DEFAULT CHARSET=utf8mb4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `doctor_professional_info`
--
ALTER TABLE `doctor_professional_info`
  ADD CONSTRAINT `doctor_professional_info_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_personal_info` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctor_professional_info_ibfk_2` FOREIGN KEY (`doctor_department`) REFERENCES `medical_department` (`departmental_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `medical_problems`
--
ALTER TABLE `medical_problems`
  ADD CONSTRAINT `medical_problems_ibfk_1` FOREIGN KEY (`medical_department`) REFERENCES `medical_department` (`departmental_name`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patients_medical_info`
--
ALTER TABLE `patients_medical_info`
  ADD CONSTRAINT `patients_medical_info_ibfk_1` FOREIGN KEY (`patients_id`) REFERENCES `patients_personal_info` (`patients_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patients_medical_info_ibfk_2` FOREIGN KEY (`patients_visiting_department`) REFERENCES `medical_department` (`departmental_name`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
