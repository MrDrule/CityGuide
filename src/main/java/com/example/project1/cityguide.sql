-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: 127.0.0.1
-- Χρόνος δημιουργίας: 09 Ιαν 2023 στις 22:39:11
-- Έκδοση διακομιστή: 10.4.25-MariaDB
-- Έκδοση PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `cityguide`
--
CREATE DATABASE IF NOT EXISTS `cityguide` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `cityguide`;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `favourite`
--

CREATE TABLE `favourite` (
  `username` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `vicinity` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `rating` double NOT NULL,
  `town_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `places`
--

CREATE TABLE `places` (
  `place_id` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `town_id` int(11) NOT NULL,
  `name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `open_now` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rating` double NOT NULL,
  `vicinity` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `price_level` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `ratings`
--

CREATE TABLE `ratings` (
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ratingtxt` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ratingvalue` double NOT NULL,
  `place_id` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `town`
--

CREATE TABLE `town` (
  `town_id` int(11) NOT NULL,
  `town_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`place_id`),
  ADD KEY `FK_2` (`town_id`);

--
-- Ευρετήρια για πίνακα `town`
--
ALTER TABLE `town`
  ADD PRIMARY KEY (`town_id`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `places`
--
ALTER TABLE `places`
  ADD CONSTRAINT `FK_1` FOREIGN KEY (`town_id`) REFERENCES `town` (`town_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
