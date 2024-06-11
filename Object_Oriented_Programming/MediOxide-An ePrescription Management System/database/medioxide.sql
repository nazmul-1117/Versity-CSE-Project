-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2024 at 03:39 PM
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
  `doctor_surname` varchar(255) DEFAULT NULL,
  `doctor_gender` enum('Male','Female','Other') DEFAULT 'Male',
  `doctor_phone` varchar(20) DEFAULT '+880xxxx-xxxxxx',
  `doctor_email` varchar(255) DEFAULT 'example@medioxide.com',
  `doctor_nid` int(20) NOT NULL,
  `doctor_license_number` varchar(50) NOT NULL,
  `doctor_current_hospital` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `doctor_nid` (`doctor_nid`),
  UNIQUE KEY `doctor_license_number` (`doctor_license_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4006 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor_personal_info`
--

INSERT INTO `doctor_personal_info` (`doctor_id`, `doctor_name`, `doctor_surname`, `doctor_gender`, `doctor_phone`, `doctor_email`, `doctor_nid`, `doctor_license_number`, `doctor_current_hospital`) VALUES
(4000, 'Md. Nazmul', 'Hossain', 'Male', '+0123-456-7890', 'nazmul.doctor@medioxide.com', 123456789, 'MD12345', 'General Hospital'),
(4001, 'bbb', 'aaa', 'Female', '+880534455', '54', 542124554, 'addLicenceTextField', 'hdfh'),
(4004, '5555', 'ty', 'Female', '555', '555', 4546, '4564', 'ghdb'),
(4005, 'ahad', 'hasan', 'Male', '+880', 'gf', 0, 'g', 'gd');

-- --------------------------------------------------------

--
-- Table structure for table `doctor_professional_info`
--

DROP TABLE IF EXISTS `doctor_professional_info`;
CREATE TABLE IF NOT EXISTS `doctor_professional_info` (
  `doctor_id` int(11) NOT NULL,
  `doctor_department` varchar(255) DEFAULT 'Neurology',
  `doctor_specialty` varchar(255) DEFAULT NULL,
  `doctor_room_number` varchar(11) DEFAULT NULL,
  `doctor_degree` varchar(255) DEFAULT 'MBBS.',
  `doctor_experience` int(11) DEFAULT 0,
  KEY `doctor_id` (`doctor_id`),
  KEY `doctor_department` (`doctor_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor_professional_info`
--

INSERT INTO `doctor_professional_info` (`doctor_id`, `doctor_department`, `doctor_specialty`, `doctor_room_number`, `doctor_degree`, `doctor_experience`) VALUES
(4000, 'Cardiology & CCU', 'Interventional Cardiologist', 'Room 301', 'MD', 10),
(4001, 'Medicine', 'fh', 'h15', 'fhdfhdhdfjhj', 10),
(4004, 'Gynae & Obstetrics', '65', '12', 'fd', 5),
(4005, 'Cardiology & CCU', 'd', 'a-502', 'M', 10);

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

--
-- Dumping data for table `medical_department`
--

INSERT INTO `medical_department` (`departmental_id`, `departmental_name`, `department_description`, `department_head`, `department_location`, `department_type`) VALUES
(108, 'Burn Plastic Surgery', 'Plastic surgery procedures for burns', 'Prof. Dr. Burming Award', 'Building A, Floor 3, Room 302', 'Clinical'),
(101, 'Cardiology & CCU', 'Specializes in heart-related diseases.', 'Prof. Dr. Nazmul Hossain', 'Building B, Floor 2, Room-203', 'Clinical'),
(109, 'Gastroenterology', 'Gastroenterologists deal with gastritis, ulcers, and irritable bowel syndrome.', 'Prof. Dr. Boota Singh', 'Building B, Floor 5, Room 507', 'Clinical'),
(110, 'Gynae & Obstetrics', 'Specializes for women during every stage of their reproductive health journey', 'Prof. Dr. Nazmul Hossain', 'Building D, Floor 1, Room 302', 'Non-Clinical'),
(104, 'Medicine', 'Internists specialize in adult healthcare, managing complex medical conditions, preventive care, and health promotion.', 'Prof. Dr. Ashik Mia', 'Building A, Floor 2, Room 202', 'Clinical'),
(107, 'Neurology', 'Specializes in nervous system comprises the brain.', 'Prof. Dr. Nazmul Hossain', 'Building D, Floor 1, Room 109', 'Clinical'),
(105, 'Orthopedic Surgery', 'Orthopedics, is a specialized branch of medicine that focuses on the care of the musculoskeletal system.', 'Prof. Dr. Sobuj Talukdar', 'Building C, Floor 5, Room 513', 'Clinical'),
(102, 'Pediatrics', 'Cares for children and adolescents.', 'Prof. Dr. Abdul Azad', 'Building A, Floor 1, Room-105', 'Clinical'),
(106, 'Psychiatry', 'Specializes in treatment for mental conditions', 'Prof. Dr. Sajib Hasan', 'Building C, Floor 3, Room 302', 'Clinical'),
(103, 'Radiology & Imaging', 'Performs diagnostic imaging (X-rays, MRI, CT scans, etc.).', 'Prof. Dr. Forhad Reza', 'Building C, Floor 3, Room 302', 'Clinical');

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
) ENGINE=InnoDB AUTO_INCREMENT=7004 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medical_problems`
--

INSERT INTO `medical_problems` (`medical_problem_id`, `medical_problem_name`, `medical_department`, `medical_problem_description`, `medical_problem_symptoms`, `medical_problem_treatment`) VALUES
(7000, 'Hypertension', 'Cardiology & CCU', 'High blood pressure', 'Headaches, dizziness, fatigue', 'Prescription medications, lifestyle changes'),
(7001, 'zad', 'Burn Plastic Surgery', 'ad', 'hh', 'ad'),
(7003, 'ad', 'Medicine', 'desrtip', 'rew', 'asr');

-- --------------------------------------------------------

--
-- Table structure for table `medical_tests`
--

DROP TABLE IF EXISTS `medical_tests`;
CREATE TABLE IF NOT EXISTS `medical_tests` (
  `medical_test_id` int(11) NOT NULL AUTO_INCREMENT,
  `medical_test_name` varchar(255) DEFAULT NULL,
  `medical_test_category` varchar(255) DEFAULT NULL,
  `medical_test_description` text DEFAULT NULL,
  `medical_test_normal_range` varchar(50) DEFAULT NULL,
  `medical_test_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`medical_test_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medical_tests`
--

INSERT INTO `medical_tests` (`medical_test_id`, `medical_test_name`, `medical_test_category`, `medical_test_description`, `medical_test_normal_range`, `medical_test_price`) VALUES
(5000, '2D Echo', 'Echo', 'An echocardiogram (echo) is a noninvasive medical procedure that uses ultrasound waves to create a picture of the heart.', '25.50', '1600.00'),
(5001, 'bbb bbb', 'Medicine', 'now nne', '8.9', '2500.50');

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
) ENGINE=InnoDB AUTO_INCREMENT=6004 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`medicine_id`, `medicine_name`, `medicine_types`, `medicine_generic`, `medicine_brands`, `medicine_description`) VALUES
(6000, 'Paracetamol', 'Tablet', 'Acetaminophen', 'Tylenol', 'Used for pain and fever relief'),
(6001, 'bbb aaa', 'ccc kkk', 'ggg', 'ddd', 'eee'),
(6003, 'maxpro', 'tablet', 'hypotension', 'jjj', 'mmmmmmm');

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

--
-- Dumping data for table `patients_medical_info`
--

INSERT INTO `patients_medical_info` (`patients_id`, `patients_visiting_department`, `patients_diseases`, `patients_weight`, `patients_systolic_bp`, `patients_diastolic_bp`, `patients_body_temp`, `patients_previous_problem`, `patients_family_problem`) VALUES
(3000, 'Neurology', 'brain stoke', '62.00', 86, 125, '98.00', 'Allergy, ', 'Azms'),
(3001, 'Radiology & Imaging', 'fdb', '56.00', 56, 56, '56.00', 'Allergy, Allergy, Diabetes, ', 'fdbs'),
(3006, 'Neurology', 'fdhh', '654.00', 4, 45, '85.00', 'Diabetes, ', '56dsg'),
(3011, 'Neurology', 'dfsdf', '98.00', 544, 89, '98.00', 'Allergy, ', 'gjf'),
(3013, 'Neurology', 'asdf', '56.00', 89, 12, '98.00', 'Allergy, ', 'azva'),
(3014, 'Cardiology & CCU', 'haeart attact', '60.00', 76, 130, '56.00', 'Allergy, ', ''),
(3015, 'Neurology', 'hgfhff', '0.00', 0, 0, '0.00', 'Allergy, ', '');

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
) ENGINE=InnoDB AUTO_INCREMENT=3016 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patients_personal_info`
--

INSERT INTO `patients_personal_info` (`patients_id`, `patients_name`, `patients_surname`, `patients_age`, `patients_gender`, `patients_mobile`, `patients_email`, `patients_address`) VALUES
(3000, 'sifjsvvs', 'ereeev', 1265, 'Female', '+8806562468678', 'afdsfa', 'fada'),
(3001, 'bbb bbb', 'bbb', 12, 'Female', '+88078956', 'bfb', 'bffb'),
(3006, 'nazmul', 'hossain', 22, 'Male', '+880564456456', 'hgfh', 'dsgd'),
(3011, 'sifjsvvs', 'ijsdfj', 1252, 'Other', '+880656246', 'afdsfa', 'fada'),
(3013, 'ada', 'ada', 12, 'Female', '+8801232564', 'dasaf', 'afda'),
(3014, 'Masum ', 'Khan', 12, 'Male', '+88012345', 'nbcvdf@fmail.com', 'fdghfh'),
(3015, 'aaa', 'aaa', 12, 'Male', '+880124555', 'hjfgjh', 'hgjg');

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
