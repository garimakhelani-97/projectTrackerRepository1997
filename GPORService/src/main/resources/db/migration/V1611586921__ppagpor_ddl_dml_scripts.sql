-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: ppagpor
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buhead`
--

DROP TABLE IF EXISTS `buhead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buhead` (
  `buHeadId` int NOT NULL,
  `buHead` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`buHeadId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buhead`
--

LOCK TABLES `buhead` WRITE;
/*!40000 ALTER TABLE `buhead` DISABLE KEYS */;
/*!40000 ALTER TABLE `buhead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `ipAddress` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdBy` int DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedBy` int DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Calsonic Kansei','Calsonic Kansei',1,NULL,NULL,NULL,NULL,NULL),(2,'Chrystler','Chrystler',1,NULL,NULL,NULL,NULL,NULL),(3,'Daimler','Daimler',1,NULL,NULL,NULL,NULL,NULL),(4,'Ford','Ford',1,NULL,NULL,NULL,NULL,NULL),(5,'Toyota','Toyota',1,NULL,NULL,NULL,NULL,NULL),(6,'General Motors','General Motors',1,NULL,NULL,NULL,NULL,NULL),(7,'Honda','Honda',1,NULL,NULL,NULL,NULL,NULL),(8,'Hyundai','Hyundai',1,NULL,NULL,NULL,NULL,NULL),(9,'JAC','JAC',1,NULL,NULL,NULL,NULL,NULL),(10,'JLR','JLR',1,NULL,NULL,NULL,NULL,NULL),(11,'Force Motors','Force Motors',1,NULL,NULL,NULL,NULL,NULL),(12,'Kia','Kia',1,NULL,NULL,NULL,NULL,NULL),(13,'Land Rover','Land Rover',1,NULL,NULL,NULL,NULL,NULL),(14,'Mah Sing','Mah Sing',1,NULL,NULL,NULL,NULL,NULL),(15,'Mahindra (India)','Mahindra (India)',1,NULL,NULL,NULL,NULL,NULL),(16,'Mazda','Mazda',1,NULL,NULL,NULL,NULL,NULL),(17,'Mitsubishi','Mitsubishi',1,NULL,NULL,NULL,NULL,NULL),(18,'Mobis','Mobis',1,NULL,NULL,NULL,NULL,NULL),(19,'Nissan','Nissan',1,NULL,NULL,NULL,NULL,NULL),(20,'PSA','PSA',1,NULL,NULL,NULL,NULL,NULL),(21,'Renault','Renault',1,NULL,NULL,NULL,NULL,NULL),(22,'RSM','RSM',1,NULL,NULL,NULL,NULL,NULL),(23,'SAIC','SAIC',1,NULL,NULL,NULL,NULL,NULL),(24,'Ssangyong','Ssangyong',1,NULL,NULL,NULL,NULL,NULL),(25,'Suzuki','Suzuki',1,NULL,NULL,NULL,NULL,NULL),(26,'Tata','Tata',1,NULL,NULL,NULL,NULL,NULL),(27,'Volvo','Volvo',1,NULL,NULL,NULL,NULL,NULL),(28,'Volvo Truck','Volvo Truck',1,NULL,NULL,NULL,NULL,NULL),(29,'VW Group','VW Group',1,NULL,NULL,NULL,NULL,NULL),(30,'Others','Others',1,NULL,NULL,NULL,NULL,NULL),(31,'Faurecia','Faurecia',1,NULL,NULL,NULL,NULL,NULL),(32,'Antolin','Antolin',1,NULL,NULL,NULL,NULL,NULL),(33,'BMW','BMW',1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customergroup`
--

DROP TABLE IF EXISTS `customergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customergroup` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `ipAddress` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdBy` int DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedBy` int DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customergroup`
--

LOCK TABLES `customergroup` WRITE;
/*!40000 ALTER TABLE `customergroup` DISABLE KEYS */;
INSERT INTO `customergroup` VALUES (1,'French CG','French CG',1,NULL,NULL,NULL,NULL,NULL),(2,'German CG','German CG',1,NULL,NULL,NULL,NULL,NULL),(3,'Asian CG','Asian CG',1,NULL,NULL,NULL,NULL,NULL),(4,'American CG','American CG',1,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `customergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gate`
--

DROP TABLE IF EXISTS `gate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `order` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gate`
--

LOCK TABLES `gate` WRITE;
/*!40000 ALTER TABLE `gate` DISABLE KEYS */;
INSERT INTO `gate` VALUES (1,'Gate 1','Gate 1',1,1),(2,'Gate 2','Gate 2',1,2),(3,'Gate 3','Gate 3',1,3),(4,'Gate 4','Gate 4',1,4),(5,'Gate 5','Gate 5',1,5),(6,'Gate 6','Gate 6',1,6);
/*!40000 ALTER TABLE `gate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1),(1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metrics`
--

DROP TABLE IF EXISTS `metrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metrics` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `recordStatus` int DEFAULT NULL,
  `order` int DEFAULT NULL,
  `record` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metrics`
--

LOCK TABLES `metrics` WRITE;
/*!40000 ALTER TABLE `metrics` DISABLE KEYS */;
INSERT INTO `metrics` VALUES (1,'Planning','Planning',1,2,NULL),(2,'Product',NULL,1,3,NULL),(3,'Quality',NULL,1,4,NULL),(4,'Process/ Indus',NULL,1,5,NULL),(5,'Purchasing',NULL,1,6,NULL),(6,'Sales',NULL,1,7,NULL),(7,'Financials',NULL,1,8,NULL),(8,'Overall',NULL,1,1,NULL);
/*!40000 ALTER TABLE `metrics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plant`
--

DROP TABLE IF EXISTS `plant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plant` (
  `plantId` int NOT NULL,
  `plant` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`plantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plant`
--

LOCK TABLES `plant` WRITE;
/*!40000 ALTER TABLE `plant` DISABLE KEYS */;
/*!40000 ALTER TABLE `plant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ispId` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(56) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(56) COLLATE utf8mb4_general_ci NOT NULL,
  `customerGroupId` int NOT NULL,
  `customerId` int NOT NULL,
  `unitId` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sopQuarter` varchar(3) COLLATE utf8mb4_general_ci NOT NULL,
  `sopYear` int NOT NULL,
  `FIELD10` varchar(8) COLLATE utf8mb4_general_ci NOT NULL,
  `eopQuarter` varchar(2) COLLATE utf8mb4_general_ci NOT NULL,
  `eopYear` int NOT NULL,
  `Project_Life` int NOT NULL,
  `Record_Status_ID` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `AwardDate` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `currencyId` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdBy` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdDate` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updatedBy` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `updatedDate` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `projectStatusId` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `projectController` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (101,'S1055_250220','Renault X52 (2) Logan / Sandero IP Brownfield Algeria','Renault X52 (2) Logan / Sandero IP Brownfield Algeria',1,1,'1','Jan',2018,'Jan-18','Q1',2029,11,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(102,'S1091_250220','Mitsubishi MMPC EL Mirage/Attrage Bumper OTH Philippines','Mitsubishi MMPC EL Mirage/Attrage Bumper OTH Philippines',2,2,'2','Feb',2018,'Feb-18','Q1',2030,12,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(103,'S970_250220','Mahindra Bolero Bolero IP Pune','Mahindra Bolero Bolero IP Pune',3,3,'3','Mar',2018,'Mar-18','Q1',2031,13,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(104,'S1034_250220','RSM XFD (2) Talisman & SM6 (airvent) TR Chennai','RSM XFD (2) Talisman & SM6 (airvent) TR Chennai',4,4,'4','Apr',2018,'Apr-18','Q1',2032,14,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(105,'S1035_250220','SYMC 1 Rexton & Rexton Sports TR Chennai','SYMC 1 Rexton & Rexton Sports TR Chennai',1,5,'5','May',2018,'May-18','Q1',2033,15,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(106,'S816_250220','Mobis TBD New IP Chennai','Mobis TBD New IP Chennai',2,6,'6','Jun',2018,'Jun-18','Q1',2034,16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(107,'S1036_250220','Nissan W32V Serena OTH Chennai','Nissan W32V Serena OTH Chennai',3,7,'7','Jul',2018,'Jul-18','Q1',2035,17,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(108,'S564_250220','VW VW546 Touareg DP Nitra','VW VW546 Touareg DP Nitra',4,8,'8','Aug',2018,'Aug-18','Q1',2036,18,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(109,'S855_250220','Audi AU336 Audi Q3 DP Nitra','Audi AU336 Audi Q3 DP Nitra',1,9,'9','Sep',2018,'Sep-18','Q1',2037,19,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(110,'S1076_250220','JLR X574 E-Pace CSLE Nitra','JLR X574 E-Pace CSLE Nitra',2,10,'10','Oct',2018,'Oct-18','Q1',2038,20,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectgatedetail`
--

DROP TABLE IF EXISTS `projectgatedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectgatedetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `projectGateAssociationId` int DEFAULT NULL,
  `projectId` int NOT NULL,
  `gateId` int NOT NULL,
  `initialDate` datetime DEFAULT NULL,
  `effectiveDate` datetime DEFAULT NULL,
  `percentageLRR` int DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `reportedMonth` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectgatedetail`
--

LOCK TABLES `projectgatedetail` WRITE;
/*!40000 ALTER TABLE `projectgatedetail` DISABLE KEYS */;
INSERT INTO `projectgatedetail` VALUES (1,1,101,1,NULL,NULL,10,4,'21-Jan',NULL,NULL),(2,2,101,2,NULL,NULL,10,4,'21-Jan',NULL,NULL),(3,NULL,101,3,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(4,NULL,101,4,NULL,NULL,NULL,5,'21-Jan',NULL,NULL),(5,NULL,101,5,NULL,NULL,NULL,6,'21-Jan',NULL,NULL),(6,NULL,101,6,NULL,NULL,NULL,6,'21-Jan',NULL,NULL),(7,NULL,102,1,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(8,NULL,102,2,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(9,NULL,102,3,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(10,NULL,102,4,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(11,NULL,102,5,NULL,NULL,NULL,5,'21-Jan',NULL,NULL),(12,NULL,102,6,NULL,NULL,NULL,6,'21-Jan',NULL,NULL),(13,NULL,103,1,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(14,NULL,103,2,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(15,NULL,103,3,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(16,NULL,103,4,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(17,NULL,103,5,NULL,NULL,NULL,4,'21-Jan',NULL,NULL),(18,NULL,103,6,NULL,NULL,NULL,5,'21-Jan',NULL,NULL),(19,NULL,101,1,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(20,NULL,101,2,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(21,NULL,101,3,NULL,NULL,NULL,5,'20-Dec',NULL,NULL),(22,NULL,101,4,NULL,NULL,NULL,6,'20-Dec',NULL,NULL),(23,NULL,101,5,NULL,NULL,NULL,6,'20-Dec',NULL,NULL),(24,NULL,101,6,NULL,NULL,NULL,6,'20-Dec',NULL,NULL),(25,NULL,102,1,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(26,NULL,102,2,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(27,NULL,102,3,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(28,NULL,102,4,NULL,NULL,NULL,5,'20-Dec',NULL,NULL),(29,NULL,102,5,NULL,NULL,NULL,6,'20-Dec',NULL,NULL),(30,NULL,102,6,NULL,NULL,NULL,6,'20-Dec',NULL,NULL),(31,NULL,103,1,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(32,NULL,103,2,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(33,NULL,103,3,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(34,NULL,103,4,NULL,NULL,NULL,4,'20-Dec',NULL,NULL),(35,NULL,103,5,NULL,NULL,NULL,5,'20-Dec',NULL,NULL),(36,NULL,103,6,NULL,NULL,NULL,6,'20-Dec',NULL,NULL);
/*!40000 ALTER TABLE `projectgatedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectinfo`
--

DROP TABLE IF EXISTS `projectinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectinfo` (
  `projectId` int NOT NULL,
  `projectName` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectinfo`
--

LOCK TABLES `projectinfo` WRITE;
/*!40000 ALTER TABLE `projectinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectmetricsdetail`
--

DROP TABLE IF EXISTS `projectmetricsdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectmetricsdetail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `projectId` int DEFAULT NULL,
  `metricsId` int NOT NULL,
  `statusId` int DEFAULT NULL,
  `comment` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reportedMonth` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdOn` datetime DEFAULT NULL,
  `updatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectmetricsdetail`
--

LOCK TABLES `projectmetricsdetail` WRITE;
/*!40000 ALTER TABLE `projectmetricsdetail` DISABLE KEYS */;
INSERT INTO `projectmetricsdetail` VALUES (2,101,8,7,NULL,'21-Jan',NULL,NULL),(3,101,1,8,NULL,'21-Jan',NULL,NULL),(4,102,8,8,NULL,'21-Jan',NULL,NULL),(5,103,8,9,NULL,'21-Jan',NULL,NULL),(6,101,8,9,NULL,'20-Dec',NULL,NULL),(7,101,1,7,NULL,'20-Dec',NULL,NULL),(8,102,8,9,NULL,'20-Dec',NULL,NULL),(9,103,8,8,NULL,'20-Dec',NULL,NULL);
/*!40000 ALTER TABLE `projectmetricsdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectprocessassociation`
--

DROP TABLE IF EXISTS `projectprocessassociation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectprocessassociation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `processId` int DEFAULT NULL,
  `projectId` int DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectprocessassociation`
--

LOCK TABLES `projectprocessassociation` WRITE;
/*!40000 ALTER TABLE `projectprocessassociation` DISABLE KEYS */;
INSERT INTO `projectprocessassociation` VALUES (12,1,101,10),(13,1,102,10),(14,1,103,10),(15,1,104,10),(16,1,105,10),(17,1,106,10),(18,1,107,10),(19,2,108,10),(20,2,109,10),(21,2,110,10);
/*!40000 ALTER TABLE `projectprocessassociation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projectstatus`
--

DROP TABLE IF EXISTS `projectstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projectstatus` (
  `statusId` int NOT NULL,
  `approved` int NOT NULL,
  `inWorkFlow` int NOT NULL,
  `pending` int NOT NULL,
  `projectId` int DEFAULT NULL,
  `returnValue` int NOT NULL,
  PRIMARY KEY (`statusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projectstatus`
--

LOCK TABLES `projectstatus` WRITE;
/*!40000 ALTER TABLE `projectstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `projectstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` int DEFAULT NULL,
  `description` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Record Status',1,'Active'),(2,'Record Status',2,'De-active'),(3,'Record Status',3,'Deleted'),(4,'Gate Status',1,'In Progress'),(5,'Gate Status',2,'Completed'),(6,'Gate Status',3,'Unknown'),(7,'Metrics Status',1,'green'),(8,'Metrics Status',2,'yellow'),(9,'Metrics Status',3,'red'),(10,'Project Status',1,'Opportunity'),(11,'Project Status',2,'Awarded'),(12,'Project Status',3,'Cancelled'),(13,'Project Status',4,'Potential'),(14,'Workflow Status',1,'Pending'),(15,'Workflow Status',2,'Submitted'),(16,'Workflow Status',3,'Returned'),(17,'Workflow Status',4,'Reviewed'),(18,'Workflow Status',5,'Approved');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unitId` int NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `regionId` int DEFAULT NULL,
  `countryId` int DEFAULT NULL,
  `stateId` int DEFAULT NULL,
  `cityId` int DEFAULT NULL,
  `regionalOfficeId` int DEFAULT NULL,
  `clusterId` int DEFAULT NULL,
  `subClusterId` int DEFAULT NULL,
  `companyId` int DEFAULT NULL,
  `pUnitId` int DEFAULT NULL,
  `qUnitId` text COLLATE utf8mb4_general_ci,
  `qcdStartDate` datetime DEFAULT NULL,
  `misStartDate` datetime DEFAULT NULL,
  `mapStartDate` datetime DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  `ipAddress` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `createdBy` int DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT NULL,
  `updatedBy` int DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `consolidateFlag` int DEFAULT NULL,
  `reportingBec` int DEFAULT NULL,
  `isOnlyBs` int DEFAULT NULL,
  `isOnlyPl` int DEFAULT NULL,
  `parentId` int DEFAULT NULL,
  `isOpUnit` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,10711,'SMRC-France-Gondecourt','Gondecourt',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,10712,'SMRC-France-Rougegoutte','Rougegoutte',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,10713,'SMRC-Spain-Igualada','Igualada',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,10714,'SMRC-Spain-Salceda','Salceda',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,10715,'SMRC-Spain-Medina','Medina',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,10716,'SMRC-Croatia- Zagreb','Zagreb',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,10717,'SMRC-Morocco-Tetouan','Tetouan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,10718,'SMRC-Slovakia-Nitra','SMRC-Slovakia-Nitra',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,10719,'SMRC-Russia-Kaluga','SMRC-Russia-Kaluga',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,10720,'SMRC-Brazil-Arbor','SMRC-Brazil-Arbor',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,11717,'SMRC-Brazil- Gravatai','SMRC-Brazil- Gravatai',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,11718,'SMRC-Argentina- Otto Krause','SMRC-Argentina- Otto Krause',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,12717,'SMRC-Korea- Asan Yesan Busan','SMRC-Korea- Asan Yesan Busan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,12718,'SMRC-Thailand- Rayong','SMRC-Thailand- Rayong',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,12719,'SMRC-Pune-India','SMRC-Pune-India',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,12720,'SMRC-India-Chennai','SMRC-India-Chennai',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,12721,'SMRC-Indonesia-Jakarta','SMRC-Indonesia-Jakarta',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,12722,'SMRC-Philippines- Santa Rosa','SMRC-Philippines- Santa Rosa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,29754,'SMRC-China- Shanghai','SMRC-China- Shanghai',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,29755,'SMRC-France-Harnes','SMRC-France-Harnes',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,29756,'SMRC France – le Plessis','SMRC France – le Plessis',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,29757,'SMRC Germany – Isenbuettel','SMRC Germany – Isenbuettel',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,29758,'SMRC-Germany-Teltow','SMRC-Germany-Teltow',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,29759,'SMRC-Spain-Mas Blau','SMRC-Spain-Mas Blau',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,29764,'SMRCAHN BV','SMRCAHN BV',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,29765,'SMRC-Poland- Poznan','SMRC-Poland- Poznan',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflow`
--

DROP TABLE IF EXISTS `workflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `processId` int NOT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_processId_idx` (`processId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflow`
--

LOCK TABLES `workflow` WRITE;
/*!40000 ALTER TABLE `workflow` DISABLE KEYS */;
INSERT INTO `workflow` VALUES (2,1,10);
/*!40000 ALTER TABLE `workflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflowapprover`
--

DROP TABLE IF EXISTS `workflowapprover`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflowapprover` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unitId` int NOT NULL,
  `projectId` int NOT NULL,
  `roleId` int NOT NULL,
  `order` int NOT NULL,
  `approverId` int NOT NULL,
  `approveStatus` int NOT NULL,
  `workflowDetailId` int NOT NULL,
  `workflowSubmissionId` int NOT NULL,
  `approvedDate` datetime DEFAULT NULL,
  `receivedDate` datetime DEFAULT NULL,
  `comments` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `reminderCount` int DEFAULT NULL,
  `lastReminder` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`unitId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflowapprover`
--

LOCK TABLES `workflowapprover` WRITE;
/*!40000 ALTER TABLE `workflowapprover` DISABLE KEYS */;
INSERT INTO `workflowapprover` VALUES (1,1,1,2,1,1,10,1,1,'2021-01-12 00:00:00','2021-01-12 00:00:00','well done ',1,'2021-01-11 00:00:00');
/*!40000 ALTER TABLE `workflowapprover` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflowsubmission`
--

DROP TABLE IF EXISTS `workflowsubmission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workflowsubmission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `unitId` int DEFAULT NULL,
  `projectId` int DEFAULT NULL,
  `processId` int DEFAULT NULL,
  `reportedMonth` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `submitBy` int DEFAULT NULL,
  `submitOn` datetime DEFAULT NULL,
  `dueDte` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflowsubmission`
--

LOCK TABLES `workflowsubmission` WRITE;
/*!40000 ALTER TABLE `workflowsubmission` DISABLE KEYS */;
INSERT INTO `workflowsubmission` VALUES (1,2745,101,1,'21-Jan',NULL,'2016-01-21 00:00:00',NULL),(2,NULL,102,1,'21-Jan',NULL,'2017-01-21 00:00:00',NULL),(3,NULL,103,1,'21-Jan',NULL,'2010-01-21 00:00:00',NULL),(4,NULL,103,1,'21-Jan',NULL,'2012-01-21 00:00:00',NULL);
/*!40000 ALTER TABLE `workflowsubmission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-25 20:30:47
