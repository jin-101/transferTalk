-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: transfer_talk
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `league`
--

DROP TABLE IF EXISTS `league`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `league` (
  `league_id` int NOT NULL AUTO_INCREMENT,
  `league_name` varchar(255) DEFAULT NULL,
  `country_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`league_id`),
  UNIQUE KEY `league_unique` (`league_name`,`country_name`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league`
--

LOCK TABLES `league` WRITE;
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
INSERT INTO `league` VALUES (16,'-_league','-'),(7,'2. Bundesliga','Germany'),(10,'3. Liga','Germany'),(104,'Albania_league','Albania'),(21,'Algeria_league','Algeria'),(137,'Andorra_league','Andorra'),(119,'Angola_league','Angola'),(68,'Armenia_league','Armenia'),(52,'Australia_league','Australia'),(32,'Austria_league','Austria'),(116,'Azerbaijan_league','Azerbaijan'),(105,'Belarus_league','Belarus'),(24,'Belgium_league','Belgium'),(83,'Bolivia_league','Bolivia'),(80,'Bosnia-Herzegovina_league','Bosnia-Herzegovina'),(17,'Breasileirao','Brazil'),(35,'Bulgaria_league','Bulgaria'),(4,'Bundesliga','Germany'),(114,'Burkina Faso_league','Burkina Faso'),(74,'Cameroon_league','Cameroon'),(67,'Canada_league','Canada'),(139,'Career break','-'),(5,'Championnat National','France'),(11,'Championship','England'),(40,'Chile_league','Chile'),(81,'China_league','China'),(60,'Colombia_league','Colombia'),(94,'Congo_league','Congo'),(71,'Costa Rica_league','Costa Rica'),(63,'Cote d\'Ivoire_league','Cote d\'Ivoire'),(28,'Croatia_league','Croatia'),(86,'Cyprus_league','Cyprus'),(26,'Czech Republic_league','Czech Republic'),(29,'Denmark_league','Denmark'),(131,'Dominican Republic_league','Dominican Republic'),(130,'DR Congo_league','DR Congo'),(102,'Ecuador_league','Ecuador'),(76,'Egypt_league','Egypt'),(33,'England_league','England'),(46,'Eredivisie','Netherlands'),(133,'Estonia_league','Estonia'),(117,'Faroe Islands_league','Faroe Islands'),(92,'Finland_league','Finland'),(15,'France_league','France'),(82,'French Guiana_league','French Guiana'),(128,'Gabon_league','Gabon'),(72,'Georgia_league','Georgia'),(19,'Germany_league','Germany'),(61,'Ghana_league','Ghana'),(49,'Greece_league','Greece'),(99,'Guatemala_league','Guatemala'),(127,'Guinea_league','Guinea'),(79,'Honduras_league','Honduras'),(69,'Hongkong_league','Hongkong'),(25,'Hungary_league','Hungary'),(70,'Iceland_league','Iceland'),(132,'India_league','India'),(90,'Indonesia_league','Indonesia'),(95,'Iran_league','Iran'),(135,'Iraq_league','Iraq'),(57,'Ireland_league','Ireland'),(64,'Israel_league','Israel'),(22,'Italy_league','Italy'),(108,'Jamaica_league','Jamaica'),(47,'Japan_league','Japan'),(112,'Jordan_league','Jordan'),(136,'Kazakhstan_league','Kazakhstan'),(89,'Korea, South_league','Korea, South'),(123,'Kuwait_league','Kuwait'),(1,'LaLiga','Spain'),(3,'LaLiga2','Spain'),(101,'Latvia_league','Latvia'),(14,'League One','England'),(13,'League Two','England'),(107,'Lebanon_league','Lebanon'),(109,'Libya_league','Libya'),(55,'Liechtenstein_league','Liechtenstein'),(2,'Ligue 1','France'),(8,'Ligue 2','France'),(111,'Lithuania_league','Lithuania'),(73,'Luxembourg_league','Luxembourg'),(78,'Malaysia_league','Malaysia'),(103,'Mali_league','Mali'),(113,'Malta_league','Malta'),(115,'Martinique_league','Martinique'),(43,'Mexico_league','Mexico'),(50,'MLS','United States'),(96,'Moldova_league','Moldova'),(65,'Monaco_league','Monaco'),(106,'Montenegro_league','Montenegro'),(62,'Morocco_league','Morocco'),(126,'Neukaledonien_league','Neukaledonien'),(93,'New Zealand_league','New Zealand'),(66,'Nigeria_league','Nigeria'),(84,'North Macedonia_league','North Macedonia'),(59,'Northern Ireland_league','Northern Ireland'),(56,'Norway_league','Norway'),(118,'Panama_league','Panama'),(87,'Paraguay_league','Paraguay'),(48,'Peru_league','Peru'),(23,'Poland_league','Poland'),(12,'Premier League','England'),(27,'Primeira_Liga','Portugal'),(34,'Qatar_league','Qatar'),(138,'Retired','-'),(37,'Réunion_league','Réunion'),(51,'Romania_league','Romania'),(54,'Russia_league','Russia'),(134,'San Marino_league','San Marino'),(75,'Saudi Arabia_league','Saudi Arabia'),(20,'Scotland Premiership','Scotland'),(97,'Senegal_league','Senegal'),(45,'Serbia_league','Serbia'),(9,'Serie A','Italy'),(6,'Serie B','Italy'),(100,'Sierra Leone_league','Sierra Leone'),(88,'Singapore_league','Singapore'),(30,'Slovakia_league','Slovakia'),(44,'Slovenia_league','Slovenia'),(77,'South Africa_league','South Africa'),(31,'Spain_league','Spain'),(125,'Sudan_league','Sudan'),(36,'Superliga_Argentina','Argentina'),(53,'Sweden_league','Sweden'),(18,'Switzerland_league','Switzerland'),(129,'Thailand_league','Thailand'),(120,'The Gambia_league','The Gambia'),(91,'Togo_league','Togo'),(110,'Trinidad and Tobago_league','Trinidad and Tobago'),(38,'Tunisia_league','Tunisia'),(42,'Turkey_league','Turkey'),(41,'Ukraine_league','Ukraine'),(98,'United Arab Emirates_league','United Arab Emirates'),(39,'Uruguay_league','Uruguay'),(124,'Uzbekistan_league','Uzbekistan'),(85,'Venezuela_league','Venezuela'),(58,'Wales_league','Wales'),(122,'Zambia_league','Zambia'),(121,'Zimbabwe_league','Zimbabwe');
/*!40000 ALTER TABLE `league` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-02  9:12:18
