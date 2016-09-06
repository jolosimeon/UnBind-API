CREATE DATABASE  IF NOT EXISTS `unbind_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `unbind_db`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: unbind_db
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `edges_end`
--

DROP TABLE IF EXISTS `edges_end`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edges_end` (
  `startnode` bigint(20) NOT NULL,
  `endnode` bigint(20) NOT NULL,
  `way_id` bigint(20) DEFAULT NULL,
  `speedMax` int(11) DEFAULT NULL,
  `length` double DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `isOneWay` int(11) DEFAULT NULL,
  `normalweight` double DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`endnode`,`startnode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `edges_start`
--

DROP TABLE IF EXISTS `edges_start`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edges_start` (
  `startnode` bigint(20) NOT NULL,
  `endnode` bigint(20) NOT NULL,
  `way_id` bigint(20) DEFAULT NULL,
  `speedMax` int(11) DEFAULT NULL,
  `length` double DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `isOneWay` int(11) DEFAULT NULL,
  `normalweight` double DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`startnode`,`endnode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nodes`
--

DROP TABLE IF EXISTS `nodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nodes` (
  `id` bigint(20) NOT NULL,
  `lon` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `traffc`
--

DROP TABLE IF EXISTS `traffc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `traffc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat` varchar(45) DEFAULT NULL,
  `longstart` varchar(45) DEFAULT NULL,
  `latend` varchar(45) DEFAULT NULL,
  `longend` varchar(45) DEFAULT NULL,
  `traffic_status` varchar(45) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  `day` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `weather`
--

DROP TABLE IF EXISTS `weather`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weather` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  `rainfall` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-06 19:05:00
