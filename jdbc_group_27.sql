-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2020 at 08:09 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.3.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jdbc_group_27`
--

-- --------------------------------------------------------

--
-- Table structure for table `hotels`
--

CREATE TABLE `hotels` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `stars` int(1) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `added_date` timestamp NULL DEFAULT NULL,
  `price` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hotels`
--

INSERT INTO `hotels` (`id`, `name`, `author_id`, `stars`, `description`, `added_date`, `price`) VALUES
(1, 'Casetta Campo de Fiori', 1, 4, '<div id=\"summary\" class=\"\" style=\"color: #333333; font-family: BlinkMacSystemFont, -apple-system, \'Segoe UI\', Roboto, Helvetica, Arial, sans-serif;\" data-et-mouseenter=\"customGoal:BUeeZaTaTaBEKMPXLae:1\r\ncustomGoal:BUeeHNSPMWdMOdUBMHT:1\r\ncustomGoal:BUeeHNSPdQEeWFWZTDWFC:1\r\ncustomGoal:BUeeHNSPMWdMOdUEXXO:1\r\ncustomGoal:BUeeHNSPdQEeXXaNSPRXXe:1\r\ncustomGoal:BUeePHOOSINEGO:1\r\ncustomGoal:BUeeHNSPVadIaCKe:1\r\ncustomGoal:BUeeHNSPCaASBaRDSGEXO:1 customGoal:BUeeZdbaBMRbBYWKOBLHXT:1\" data-et-click=\"customGoal:BUeeZaTaTaBEKMPXLae:2\r\ncustomGoal:BUeeHNSPMWdMOdUBMHT:2\r\ncustomGoal:BUeeHNSPdQEeWFWZTDWFC:2\r\ncustomGoal:BUeeHNSPMWdMOdUEXXO:2\r\ncustomGoal:BUeeHNSPdQEeXXaNSPRXXe:2\r\ncustomGoal:BUeePHOOSINEGO:2\r\ncustomGoal:BUeeHNSPVadIaCKe:2\r\ncustomGoal:BUeeHNSPCaASBaRDSGEXO:2 customGoal:BUeeZdbaBMRbBYWKOBLHXT:2\">\r\n<div id=\"property_description_content\">\r\n<p>Located 92 m from Campo de\' Fiori, Casetta Campo de Fiori has accommodations and free WiFi in Rome.</p>\r\n<p>The units come with air-conditioned bedrooms and a kitchen with an oven, microwave and fridge. A stovetop and electric tea pot are also available.</p>\r\n<p>A bicycle rental service is available at the apartment.</p>\r\n<p>Piazza Navona is a 4-minute walk from Casetta Campo de Fiori. Ciampino Airport is 14.5 km away.</p>\r\n</div>\r\n</div>\r\n<p class=\"geo_information\" style=\"color: #333333; font-family: BlinkMacSystemFont, -apple-system, \'Segoe UI\', Roboto, Helvetica, Arial, sans-serif;\">This is our guests\' favorite part of Rome, according to independent reviews.</p>\r\n<p class=\"hp-desc-review-highlight hp-desc-traveller-type--ph-v0\" style=\"color: #333333; font-family: BlinkMacSystemFont, -apple-system, \'Segoe UI\', Roboto, Helvetica, Arial, sans-serif;\">Couples in particular like the location &ndash; they rated it&nbsp;<strong>9.8</strong>&nbsp;for a two-person trip.</p>\r\n<p class=\"hp-desc-we-speak\" style=\"color: #333333; font-family: BlinkMacSystemFont, -apple-system, \'Segoe UI\', Roboto, Helvetica, Arial, sans-serif;\">We speak your language!</p>\r\n<p class=\"hp-desc-we-speak\" style=\"color: #333333; font-family: BlinkMacSystemFont, -apple-system, \'Segoe UI\', Roboto, Helvetica, Arial, sans-serif;\"><img src=\"https://cf.bstatic.com/images/hotel/max1280x900/265/265766932.jpg\" alt=\"Gallery image of this property\" /></p>', '2020-10-08 13:42:25', 80),
(2, 'Rixos Almaty', 1, 5, '<p><img src=\"https://cf.bstatic.com/images/hotel/max1280x900/265/265764392.jpg\" alt=\"Gallery image of this property\" /></p>\r\n<p>THIS IS <strong>RIXOS</strong> in <strong>ALMATY</strong> CITY very comfortable!</p>', '2020-10-08 13:56:33', 100);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `full_name`, `picture`) VALUES
(1, 'ilyas@gmail.com', 'qweqwe', 'Ilyas Zhuanyshev', '/images/default_user.png'),
(2, 'serik@gmail.com', 'qweqwe', 'Serikbek Almasbekovic Serikov', 'https://picsum.photos/id/237/300/300'),
(5, 'ronaldo@gmail.com', 'qweqwe', 'Cristiano Ronaldo', '/images/default_user.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`id`),
  ADD KEY `hotels_users__fk` (`author_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_uindex` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hotels`
--
ALTER TABLE `hotels`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hotels`
--
ALTER TABLE `hotels`
  ADD CONSTRAINT `hotels_users__fk` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
