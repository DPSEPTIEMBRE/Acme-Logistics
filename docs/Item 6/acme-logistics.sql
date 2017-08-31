start transaction;

drop database if exists `Acme-Logistics`;
create database `Acme-Logistics`;

use `Acme-Logistics`;

grant select, insert, update, delete on `Acme-Logistics`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter,
	create temporary tables, lock tables, create view, create routine,
	alter routine, execute, trigger, show view on `Acme-Logistics`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Logistics
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  KEY `FK_5w0f1bikb2adecvdifuxbep9d` (`creditCard_id`),
  KEY `FK_e53x697sry6pe5r9q8mf5gvjq` (`wall_id`),
  CONSTRAINT `FK_e53x697sry6pe5r9q8mf5gvjq` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_5w0f1bikb2adecvdifuxbep9d` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_actor`
--

DROP TABLE IF EXISTS `actor_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_actor` (
  `follower_id` int(11) NOT NULL,
  `followed_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_actor`
--

LOCK TABLES `actor_actor` WRITE;
/*!40000 ALTER TABLE `actor_actor` DISABLE KEYS */;
INSERT INTO `actor_actor` VALUES (348,358),(348,349),(349,358),(349,350),(350,358),(350,350),(351,358),(351,350),(355,358),(355,350),(356,358),(356,350),(357,358),(357,350),(358,349),(358,350),(359,349),(359,350),(360,349),(360,350),(377,349),(377,350),(378,349),(378,350),(379,349),(379,350);
/*!40000 ALTER TABLE `actor_actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_folder`
--

DROP TABLE IF EXISTS `actor_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_folder` (
  `Actor_id` int(11) NOT NULL,
  `folders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_dp573h40udupcm5m1kgn2bc2r` (`folders_id`),
  CONSTRAINT `FK_dp573h40udupcm5m1kgn2bc2r` FOREIGN KEY (`folders_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_folder`
--

LOCK TABLES `actor_folder` WRITE;
/*!40000 ALTER TABLE `actor_folder` DISABLE KEYS */;
INSERT INTO `actor_folder` VALUES (348,384),(349,385),(350,386),(351,387),(355,388),(356,389),(357,390),(358,391),(359,392),(360,393),(377,394),(378,395),(379,396),(348,397),(349,398),(350,399),(351,400),(355,401),(356,402),(357,403),(358,404),(359,405),(360,406),(377,407),(378,408),(379,409),(348,410),(349,411),(350,412),(351,413),(355,414),(356,415),(357,416),(358,417),(359,418),(360,419),(377,420),(378,421),(379,422),(348,423),(349,424),(350,425),(351,426),(355,427),(356,428),(357,429),(358,430),(359,431),(360,432),(377,433),(378,434),(379,435);
/*!40000 ALTER TABLE `actor_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  KEY `FK_gl4ryvfr1pd7798c3kuo22hvb` (`creditCard_id`),
  KEY `FK_5nvlf2he3hg5x1k90o6jc45kp` (`wall_id`),
  CONSTRAINT `FK_5nvlf2he3hg5x1k90o6jc45kp` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_gl4ryvfr1pd7798c3kuo22hvb` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (348,1,'Juan Carlos','Sevilla','juancarlos@hotmail.com','44689767K','+34 (88) 8788','41100','lopez Muñoz',436,335,466);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessman`
--

DROP TABLE IF EXISTS `businessman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessman` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  `store_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hkksdm75bbah7a6x3hjthwes8` (`userAccount_id`),
  KEY `FK_plxuu5adx8i4frtokcxi54890` (`store_id`),
  KEY `FK_owrh6sm5ktnqjkfa78m0nk7py` (`creditCard_id`),
  KEY `FK_m4a4bkkuw17vwar0shb895xa8` (`wall_id`),
  CONSTRAINT `FK_m4a4bkkuw17vwar0shb895xa8` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_hkksdm75bbah7a6x3hjthwes8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_owrh6sm5ktnqjkfa78m0nk7py` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_plxuu5adx8i4frtokcxi54890` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessman`
--

LOCK TABLES `businessman` WRITE;
/*!40000 ALTER TABLE `businessman` DISABLE KEYS */;
INSERT INTO `businessman` VALUES (358,1,'Felix','Sevilla','felix@hotmail.com','42689267K','+34 (88) 2223','41100','Rodriguez',443,342,473,364),(359,1,'jose','Sevilla','jose@hotmail.com','42289267K','+34 (28) 2223','41100','Rodriguez',444,343,474,365),(360,1,'luis','Sevilla','luis@hotmail.com','42389267K','+34 (28) 2223','41100','Rodriguez',445,344,475,366);
/*!40000 ALTER TABLE `businessman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessman_businessorder`
--

DROP TABLE IF EXISTS `businessman_businessorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessman_businessorder` (
  `Businessman_id` int(11) NOT NULL,
  `orders_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ijtc7ja1uwswn6f225x80dchs` (`orders_id`),
  KEY `FK_amjqcxqaogwem06e5f0ljk27p` (`Businessman_id`),
  CONSTRAINT `FK_amjqcxqaogwem06e5f0ljk27p` FOREIGN KEY (`Businessman_id`) REFERENCES `businessman` (`id`),
  CONSTRAINT `FK_ijtc7ja1uwswn6f225x80dchs` FOREIGN KEY (`orders_id`) REFERENCES `businessorder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessman_businessorder`
--

LOCK TABLES `businessman_businessorder` WRITE;
/*!40000 ALTER TABLE `businessman_businessorder` DISABLE KEYS */;
INSERT INTO `businessman_businessorder` VALUES (358,361),(358,362),(359,363);
/*!40000 ALTER TABLE `businessman_businessorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessorder`
--

DROP TABLE IF EXISTS `businessorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessorder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `day` datetime DEFAULT NULL,
  `devilered` bit(1) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `storeIdentif` int(11) DEFAULT NULL,
  `vat` varchar(255) DEFAULT NULL,
  `offerTransport_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8ut6nplerwxbdrlqfab8uvysp` (`offerTransport_id`),
  CONSTRAINT `FK_8ut6nplerwxbdrlqfab8uvysp` FOREIGN KEY (`offerTransport_id`) REFERENCES `offertransport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessorder`
--

LOCK TABLES `businessorder` WRITE;
/*!40000 ALTER TABLE `businessorder` DISABLE KEYS */;
INSERT INTO `businessorder` VALUES (361,1,'2019-03-08 00:00:00','','KG',22.22,22.22,2,'2322',371),(362,1,'2019-03-08 00:00:00','','KG',22.22,22.22,2,'2322',372),(363,1,'2019-02-08 00:00:00','','KG',22.22,22.22,2,'2322',373);
/*!40000 ALTER TABLE `businessorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `businessorder_fish`
--

DROP TABLE IF EXISTS `businessorder_fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `businessorder_fish` (
  `BusinessOrder_id` int(11) NOT NULL,
  `fish_id` int(11) NOT NULL,
  KEY `FK_6eykghq2078o0g37jr5nq31t4` (`fish_id`),
  KEY `FK_c3n44gtxs79simvo3taq2ogtj` (`BusinessOrder_id`),
  CONSTRAINT `FK_c3n44gtxs79simvo3taq2ogtj` FOREIGN KEY (`BusinessOrder_id`) REFERENCES `businessorder` (`id`),
  CONSTRAINT `FK_6eykghq2078o0g37jr5nq31t4` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `businessorder_fish`
--

LOCK TABLES `businessorder_fish` WRITE;
/*!40000 ALTER TABLE `businessorder_fish` DISABLE KEYS */;
INSERT INTO `businessorder_fish` VALUES (361,463),(362,463),(363,463),(363,464);
/*!40000 ALTER TABLE `businessorder_fish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer`
--

DROP TABLE IF EXISTS `buyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n1u0aujikrqor6qt4sc7dxi6y` (`userAccount_id`),
  KEY `FK_jujan5shk59hjt1sigq27ug6i` (`creditCard_id`),
  KEY `FK_ashbm7kjo7h91575jids0ry38` (`wall_id`),
  CONSTRAINT `FK_ashbm7kjo7h91575jids0ry38` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_jujan5shk59hjt1sigq27ug6i` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_n1u0aujikrqor6qt4sc7dxi6y` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer`
--

LOCK TABLES `buyer` WRITE;
/*!40000 ALTER TABLE `buyer` DISABLE KEYS */;
INSERT INTO `buyer` VALUES (355,1,'Adrian','Sevilla','adrian@hotmail.com','42289267K','+34 (88) 2488','41100','Sanchez',440,339,470),(356,1,'Jesus','Sevilla','jesus@hotmail.com','42289267K','+34 (88) 2428','41100','Escobar',441,340,471),(357,1,'Pablo','Sevilla','pablo@hotmail.com','42689267K','+34 (88) 2228','41100','Escobar',442,341,472);
/*!40000 ALTER TABLE `buyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buyer_ticker`
--

DROP TABLE IF EXISTS `buyer_ticker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `buyer_ticker` (
  `Buyer_id` int(11) NOT NULL,
  `tickers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_iq8pnl95jbktgb9agd7rv7fa0` (`tickers_id`),
  KEY `FK_m4l84n063s5fo8041jchuanjm` (`Buyer_id`),
  CONSTRAINT `FK_m4l84n063s5fo8041jchuanjm` FOREIGN KEY (`Buyer_id`) REFERENCES `buyer` (`id`),
  CONSTRAINT `FK_iq8pnl95jbktgb9agd7rv7fa0` FOREIGN KEY (`tickers_id`) REFERENCES `ticker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buyer_ticker`
--

LOCK TABLES `buyer_ticker` WRITE;
/*!40000 ALTER TABLE `buyer_ticker` DISABLE KEYS */;
INSERT INTO `buyer_ticker` VALUES (355,352),(356,353),(356,354);
/*!40000 ALTER TABLE `buyer_ticker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (367,0,'hola mundo','2018-11-08 00:00:00'),(368,0,'hola mundo2','2018-11-08 00:00:00'),(369,0,'hola mundo3','2018-11-08 00:00:00'),(370,0,'hola mundo4','2018-11-08 00:00:00');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `cardNumber` varchar(255) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `expMonth` int(11) DEFAULT NULL,
  `expYear` int(11) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (436,0,'AMEX','4620252787040202',123,12,2020,'holder name 1'),(437,0,'AMEX','4620252787040202',123,12,2020,'holder name 2'),(438,0,'AMEX','4620252787040202',123,12,2020,'holder name 3'),(439,0,'AMEX','4620252787040202',123,12,2020,'holder name 4'),(440,0,'AMEX','4620252787040202',123,12,2020,'holder name 5'),(441,0,'AMEX','4620252787040202',123,12,2020,'holder name 6'),(442,0,'AMEX','4620252787040202',123,12,2020,'holder name 7'),(443,0,'AMEX','4620252787040202',123,12,2020,'holder name 8'),(444,0,'AMEX','4620252787040202',123,12,2020,'holder name 9'),(445,0,'AMEX','4620252787040202',123,12,2020,'holder name 10'),(446,0,'AMEX','4620252787040202',123,12,2020,'holder name 11'),(447,0,'AMEX','4620252787040202',123,12,2020,'holder name 12'),(448,0,'AMEX','4620252787040202',123,12,2020,'holder name 13');
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailycatch`
--

DROP TABLE IF EXISTS `dailycatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailycatch` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `fish_id` int(11) DEFAULT NULL,
  `fisherman_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5vdaxobfdlq8x8yaoqeagxsaa` (`fish_id`),
  KEY `FK_5sue36brp7ohi2bch6n4xohdc` (`fisherman_id`),
  CONSTRAINT `FK_5sue36brp7ohi2bch6n4xohdc` FOREIGN KEY (`fisherman_id`) REFERENCES `fisherman` (`id`),
  CONSTRAINT `FK_5vdaxobfdlq8x8yaoqeagxsaa` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailycatch`
--

LOCK TABLES `dailycatch` WRITE;
/*!40000 ALTER TABLE `dailycatch` DISABLE KEYS */;
INSERT INTO `dailycatch` VALUES (479,0,'KG',5,463,349),(480,0,'KG',7,464,349),(481,0,'KG',5,464,350),(482,0,'KG',5,464,351);
/*!40000 ALTER TABLE `dailycatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fish`
--

DROP TABLE IF EXISTS `fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fish` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fish`
--

LOCK TABLES `fish` WRITE;
/*!40000 ALTER TABLE `fish` DISABLE KEYS */;
INSERT INTO `fish` VALUES (463,0,'magikarp'),(464,0,'gambas'),(465,0,'boquerones');
/*!40000 ALTER TABLE `fish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fish_property`
--

DROP TABLE IF EXISTS `fish_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fish_property` (
  `Fish_id` int(11) NOT NULL,
  `listProperties_id` int(11) NOT NULL,
  UNIQUE KEY `UK_kq6kuugjj82s4byj5pvi2kruh` (`listProperties_id`),
  KEY `FK_hsrn8shkq3xqx1t9i3p9heu8w` (`Fish_id`),
  CONSTRAINT `FK_hsrn8shkq3xqx1t9i3p9heu8w` FOREIGN KEY (`Fish_id`) REFERENCES `fish` (`id`),
  CONSTRAINT `FK_kq6kuugjj82s4byj5pvi2kruh` FOREIGN KEY (`listProperties_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fish_property`
--

LOCK TABLES `fish_property` WRITE;
/*!40000 ALTER TABLE `fish_property` DISABLE KEYS */;
INSERT INTO `fish_property` VALUES (463,458),(463,462),(464,459),(465,460),(465,461);
/*!40000 ALTER TABLE `fish_property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fisherman`
--

DROP TABLE IF EXISTS `fisherman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fisherman` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8s8f83296pxtcvi5t7i6i03bs` (`userAccount_id`),
  KEY `FK_9gr8fj7sx9apxymliib34o0h7` (`creditCard_id`),
  KEY `FK_5no150ed4wrvsg9th0wsai4v9` (`wall_id`),
  CONSTRAINT `FK_5no150ed4wrvsg9th0wsai4v9` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_8s8f83296pxtcvi5t7i6i03bs` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_9gr8fj7sx9apxymliib34o0h7` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisherman`
--

LOCK TABLES `fisherman` WRITE;
/*!40000 ALTER TABLE `fisherman` DISABLE KEYS */;
INSERT INTO `fisherman` VALUES (349,1,'Juan','Sevilla','jj@hotmail.com','44689267K','+34 (88) 3788','41100','Valle',437,336,467),(350,1,'Juan','Sevilla','jj@hotmail.com','44689267K','+34 (88) 3788','41100','Valle',438,337,468),(351,1,'Laura','Sevilla','laura@hotmail.com','44289267K','+34 (88) 3488','41100','Padial',439,338,469);
/*!40000 ALTER TABLE `fisherman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fisherman_dailycatch`
--

DROP TABLE IF EXISTS `fisherman_dailycatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fisherman_dailycatch` (
  `Fisherman_id` int(11) NOT NULL,
  `dailyCatchs_id` int(11) NOT NULL,
  UNIQUE KEY `UK_7ssciki46euv4gnqwwn5jchet` (`dailyCatchs_id`),
  KEY `FK_qnpgdc5w9poc5y2st5jqgxc1q` (`Fisherman_id`),
  CONSTRAINT `FK_qnpgdc5w9poc5y2st5jqgxc1q` FOREIGN KEY (`Fisherman_id`) REFERENCES `fisherman` (`id`),
  CONSTRAINT `FK_7ssciki46euv4gnqwwn5jchet` FOREIGN KEY (`dailyCatchs_id`) REFERENCES `dailycatch` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisherman_dailycatch`
--

LOCK TABLES `fisherman_dailycatch` WRITE;
/*!40000 ALTER TABLE `fisherman_dailycatch` DISABLE KEYS */;
INSERT INTO `fisherman_dailycatch` VALUES (349,479),(349,480),(350,481),(351,482);
/*!40000 ALTER TABLE `fisherman_dailycatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fisherman_offermarket`
--

DROP TABLE IF EXISTS `fisherman_offermarket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fisherman_offermarket` (
  `Fisherman_id` int(11) NOT NULL,
  `offerMarkets_id` int(11) NOT NULL,
  UNIQUE KEY `UK_ahflu40wtuf24a03j6pmnewo0` (`offerMarkets_id`),
  KEY `FK_lgj1ink39bbi7akorxhpe6bnj` (`Fisherman_id`),
  CONSTRAINT `FK_lgj1ink39bbi7akorxhpe6bnj` FOREIGN KEY (`Fisherman_id`) REFERENCES `fisherman` (`id`),
  CONSTRAINT `FK_ahflu40wtuf24a03j6pmnewo0` FOREIGN KEY (`offerMarkets_id`) REFERENCES `offermarket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fisherman_offermarket`
--

LOCK TABLES `fisherman_offermarket` WRITE;
/*!40000 ALTER TABLE `fisherman_offermarket` DISABLE KEYS */;
INSERT INTO `fisherman_offermarket` VALUES (349,455),(350,456),(351,457);
/*!40000 ALTER TABLE `fisherman_offermarket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `folderName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (384,0,'inbox'),(385,0,'inbox'),(386,0,'inbox'),(387,0,'inbox'),(388,0,'inbox'),(389,0,'inbox'),(390,0,'inbox'),(391,0,'inbox'),(392,0,'inbox'),(393,0,'inbox'),(394,0,'inbox'),(395,0,'inbox'),(396,0,'inbox'),(397,0,'outbox'),(398,0,'outbox'),(399,0,'outbox'),(400,0,'outbox'),(401,0,'outbox'),(402,0,'outbox'),(403,0,'outbox'),(404,0,'outbox'),(405,0,'outbox'),(406,0,'outbox'),(407,0,'outbox'),(408,0,'outbox'),(409,0,'outbox'),(410,0,'trashbox'),(411,0,'trashbox'),(412,0,'trashbox'),(413,0,'trashbox'),(414,0,'trashbox'),(415,0,'trashbox'),(416,0,'trashbox'),(417,0,'trashbox'),(418,0,'trashbox'),(419,0,'trashbox'),(420,0,'trashbox'),(421,0,'trashbox'),(422,0,'trashbox'),(423,0,'spambox'),(424,0,'spambox'),(425,0,'spambox'),(426,0,'spambox'),(427,0,'spambox'),(428,0,'spambox'),(429,0,'spambox'),(430,0,'spambox'),(431,0,'spambox'),(432,0,'spambox'),(433,0,'spambox'),(434,0,'spambox'),(435,0,'spambox');
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_mailmessage`
--

DROP TABLE IF EXISTS `folder_mailmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_mailmessage` (
  `Folder_id` int(11) NOT NULL,
  `messages_id` int(11) NOT NULL,
  UNIQUE KEY `UK_t0swyuobr7225xnposfuu8ixm` (`messages_id`),
  KEY `FK_rys3vi5853dyhptvldu6nn09l` (`Folder_id`),
  CONSTRAINT `FK_rys3vi5853dyhptvldu6nn09l` FOREIGN KEY (`Folder_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_t0swyuobr7225xnposfuu8ixm` FOREIGN KEY (`messages_id`) REFERENCES `mailmessage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_mailmessage`
--

LOCK TABLES `folder_mailmessage` WRITE;
/*!40000 ALTER TABLE `folder_mailmessage` DISABLE KEYS */;
INSERT INTO `folder_mailmessage` VALUES (385,449),(386,450),(388,451),(401,452),(401,453),(401,454);
/*!40000 ALTER TABLE `folder_mailmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mailmessage`
--

DROP TABLE IF EXISTS `mailmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mailmessage` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `sent` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) NOT NULL,
  `sender_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mailmessage`
--

LOCK TABLES `mailmessage` WRITE;
/*!40000 ALTER TABLE `mailmessage` DISABLE KEYS */;
INSERT INTO `mailmessage` VALUES (449,0,'it is a mesage important','HIGH','2017-11-07 00:00:00','urgent',350,349),(450,0,'it is a mesage important','NEUTRAL','2017-11-07 00:00:00','hello',351,350),(451,0,'it is a mesage important','LOW','2017-11-07 00:00:00','hello world',356,355),(452,0,'it is a mesage important','LOW','2017-11-07 00:00:00','hello world',355,356),(453,0,'it is a mesage important','LOW','2017-11-07 00:00:00','hello world',355,356),(454,0,'it is a mesage important','LOW','2017-11-07 00:00:00','hello world',355,356);
/*!40000 ALTER TABLE `mailmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offermarket`
--

DROP TABLE IF EXISTS `offermarket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offermarket` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `isEstatic` bit(1) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `fisherman_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kcvyv414pgtdp3fjgtfi9tfx9` (`fisherman_id`),
  CONSTRAINT `FK_kcvyv414pgtdp3fjgtfi9tfx9` FOREIGN KEY (`fisherman_id`) REFERENCES `fisherman` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offermarket`
--

LOCK TABLES `offermarket` WRITE;
/*!40000 ALTER TABLE `offermarket` DISABLE KEYS */;
INSERT INTO `offermarket` VALUES (455,0,'2018-10-08 00:00:00',NULL,'','KG','Sevilla',22.22,22.22,349),(456,0,'2018-10-08 00:00:00',NULL,'\0','KG','Sevilla',22.22,22.22,350),(457,0,'2018-10-08 00:00:00',NULL,'','KG','Sevilla',22.22,22.22,351);
/*!40000 ALTER TABLE `offermarket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offermarket_fish`
--

DROP TABLE IF EXISTS `offermarket_fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offermarket_fish` (
  `OfferMarket_id` int(11) NOT NULL,
  `fishers_id` int(11) NOT NULL,
  KEY `FK_f6snwin5b2sedovdg2jmqsjh7` (`fishers_id`),
  KEY `FK_k554qi6lm4c74qr67v6qdxpbg` (`OfferMarket_id`),
  CONSTRAINT `FK_k554qi6lm4c74qr67v6qdxpbg` FOREIGN KEY (`OfferMarket_id`) REFERENCES `offermarket` (`id`),
  CONSTRAINT `FK_f6snwin5b2sedovdg2jmqsjh7` FOREIGN KEY (`fishers_id`) REFERENCES `fish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offermarket_fish`
--

LOCK TABLES `offermarket_fish` WRITE;
/*!40000 ALTER TABLE `offermarket_fish` DISABLE KEYS */;
INSERT INTO `offermarket_fish` VALUES (455,463),(455,464),(456,465);
/*!40000 ALTER TABLE `offermarket_fish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offertransport`
--

DROP TABLE IF EXISTS `offertransport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offertransport` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `chargeMax` double DEFAULT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `periodeEnd` datetime DEFAULT NULL,
  `periodeStart` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `transporterIdentif` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offertransport`
--

LOCK TABLES `offertransport` WRITE;
/*!40000 ALTER TABLE `offertransport` DISABLE KEYS */;
INSERT INTO `offertransport` VALUES (371,0,'\0',2,'Madrid','KG','Sevilla','2020-04-09 00:00:00','2019-10-09 00:00:00',2,'4444-22-AA'),(372,0,'\0',2,'Madrid','KG','Sevilla','2020-04-09 00:00:00','2019-10-09 00:00:00',2,'4444-22-AB'),(373,0,'\0',2,'Madrid','KG','Sevilla','2020-04-09 00:00:00','2019-10-09 00:00:00',2,'4444-22-AC'),(374,0,'',2,'Madrid','KG','Sevilla','2020-06-09 00:00:00','2019-10-09 00:00:00',2,'4894-22-AA'),(375,0,'',10,'Madrid','KG','Granada','2020-04-10 00:00:00','2019-10-09 00:00:00',120,'4444-22-AA'),(376,0,'',20,'Andromeda','KG','Sevilla','2052-04-09 00:00:00','2019-10-09 00:00:00',2000,'4444-22-AA');
/*!40000 ALTER TABLE `offertransport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `fish_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e7risd3js16wxiws0lfer8yt3` (`fish_id`),
  CONSTRAINT `FK_e7risd3js16wxiws0lfer8yt3` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (483,0,'KG',2,2,463),(484,0,'KG',2,2,464),(485,0,'KG',2,2,465);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_comment`
--

DROP TABLE IF EXISTS `product_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_comment` (
  `Product_id` int(11) NOT NULL,
  `comments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_qrqgx0q2mucoen8gw2s5uwrqf` (`comments_id`),
  KEY `FK_nomglegv2oroe3vliqlqmgoxk` (`Product_id`),
  CONSTRAINT `FK_nomglegv2oroe3vliqlqmgoxk` FOREIGN KEY (`Product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FK_qrqgx0q2mucoen8gw2s5uwrqf` FOREIGN KEY (`comments_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comment`
--

LOCK TABLES `product_comment` WRITE;
/*!40000 ALTER TABLE `product_comment` DISABLE KEYS */;
INSERT INTO `product_comment` VALUES (483,367),(483,368),(484,369),(484,370);
/*!40000 ALTER TABLE `product_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (458,0,'its a good fish','Fish of water'),(459,0,'its a good fish','Fish of water'),(460,0,'its a good fish','Fish of water'),(461,0,'its a good fish water','Fish of water 2'),(462,0,'its a good fish fire','Fish of fire 5');
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spamword`
--

DROP TABLE IF EXISTS `spamword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spamword` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spamword`
--

LOCK TABLES `spamword` WRITE;
/*!40000 ALTER TABLE `spamword` DISABLE KEYS */;
INSERT INTO `spamword` VALUES (380,0,'viagra'),(381,0,'cialis'),(382,0,'sex'),(383,0,'love');
/*!40000 ALTER TABLE `spamword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `VAT` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (364,0,'3333','Sevilla','Marisco recio','40100'),(365,0,'3333','Sevilla','Marisco alfonso','40100'),(366,0,'3333','Sevilla','Marisco elene','40100');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_comment`
--

DROP TABLE IF EXISTS `store_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_comment` (
  `Store_id` int(11) NOT NULL,
  `comments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_6n4jhqefosbrf5os6wls58rdl` (`comments_id`),
  KEY `FK_mf5occ5uho7h7rurcnwgpg1rh` (`Store_id`),
  CONSTRAINT `FK_mf5occ5uho7h7rurcnwgpg1rh` FOREIGN KEY (`Store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FK_6n4jhqefosbrf5os6wls58rdl` FOREIGN KEY (`comments_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_comment`
--

LOCK TABLES `store_comment` WRITE;
/*!40000 ALTER TABLE `store_comment` DISABLE KEYS */;
INSERT INTO `store_comment` VALUES (364,367),(364,368),(365,369),(366,370);
/*!40000 ALTER TABLE `store_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_product`
--

DROP TABLE IF EXISTS `store_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_product` (
  `Store_id` int(11) NOT NULL,
  `products_id` int(11) NOT NULL,
  UNIQUE KEY `UK_o4oybdvh2l3vy1qqoluiwu0nh` (`products_id`),
  KEY `FK_b7wtxnu8qwkbyfg638532g4ri` (`Store_id`),
  CONSTRAINT `FK_b7wtxnu8qwkbyfg638532g4ri` FOREIGN KEY (`Store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FK_o4oybdvh2l3vy1qqoluiwu0nh` FOREIGN KEY (`products_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_product`
--

LOCK TABLES `store_product` WRITE;
/*!40000 ALTER TABLE `store_product` DISABLE KEYS */;
INSERT INTO `store_product` VALUES (364,483),(364,484),(365,485);
/*!40000 ALTER TABLE `store_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticker`
--

DROP TABLE IF EXISTS `ticker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticker`
--

LOCK TABLES `ticker` WRITE;
/*!40000 ALTER TABLE `ticker` DISABLE KEYS */;
INSERT INTO `ticker` VALUES (352,0,'KG',2,2),(353,0,'KG',2,2),(354,0,'KG',2,2);
/*!40000 ALTER TABLE `ticker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticker_fish`
--

DROP TABLE IF EXISTS `ticker_fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticker_fish` (
  `Ticker_id` int(11) NOT NULL,
  `fish_id` int(11) NOT NULL,
  KEY `FK_crawcw1ncc4wchqqhs0tnnduq` (`fish_id`),
  KEY `FK_49q43rtr1r7eau2tabqa36x2a` (`Ticker_id`),
  CONSTRAINT `FK_49q43rtr1r7eau2tabqa36x2a` FOREIGN KEY (`Ticker_id`) REFERENCES `ticker` (`id`),
  CONSTRAINT `FK_crawcw1ncc4wchqqhs0tnnduq` FOREIGN KEY (`fish_id`) REFERENCES `fish` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticker_fish`
--

LOCK TABLES `ticker_fish` WRITE;
/*!40000 ALTER TABLE `ticker_fish` DISABLE KEYS */;
INSERT INTO `ticker_fish` VALUES (352,463),(353,464),(354,464);
/*!40000 ALTER TABLE `ticker_fish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporter`
--

DROP TABLE IF EXISTS `transporter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporter` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numberidentif` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `wall_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qywrsv8uxytpwgtf5tl44qurc` (`userAccount_id`),
  KEY `FK_lvnq0gj3nnwxh59qprusaxhmo` (`creditCard_id`),
  KEY `FK_ged14e9c6u55wau3oaus4seb3` (`wall_id`),
  CONSTRAINT `FK_ged14e9c6u55wau3oaus4seb3` FOREIGN KEY (`wall_id`) REFERENCES `wall` (`id`),
  CONSTRAINT `FK_lvnq0gj3nnwxh59qprusaxhmo` FOREIGN KEY (`creditCard_id`) REFERENCES `creditcard` (`id`),
  CONSTRAINT `FK_qywrsv8uxytpwgtf5tl44qurc` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporter`
--

LOCK TABLES `transporter` WRITE;
/*!40000 ALTER TABLE `transporter` DISABLE KEYS */;
INSERT INTO `transporter` VALUES (377,1,'luis','Sevilla','luis@hotmail.com','42389267K','+34 (28) 2223','41100','Rodriguez',446,345,476),(378,1,'pepe','Sevilla','pepe@hotmail.com','42389367K','+34 (68) 2223','41400','Rodriguez',447,346,477),(379,1,'Manuel','Sevilla','manuel@hotmail.com','42389237K','+34 (28) 2233','41100','Rodriguez',448,347,478);
/*!40000 ALTER TABLE `transporter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transporter_offertransport`
--

DROP TABLE IF EXISTS `transporter_offertransport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transporter_offertransport` (
  `Transporter_id` int(11) NOT NULL,
  `offerTransport_id` int(11) NOT NULL,
  UNIQUE KEY `UK_altm5g8u0q0op2cs9t1maoelc` (`offerTransport_id`),
  KEY `FK_441m4208ehv9a996yecqdt1mv` (`Transporter_id`),
  CONSTRAINT `FK_441m4208ehv9a996yecqdt1mv` FOREIGN KEY (`Transporter_id`) REFERENCES `transporter` (`id`),
  CONSTRAINT `FK_altm5g8u0q0op2cs9t1maoelc` FOREIGN KEY (`offerTransport_id`) REFERENCES `offertransport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transporter_offertransport`
--

LOCK TABLES `transporter_offertransport` WRITE;
/*!40000 ALTER TABLE `transporter_offertransport` DISABLE KEYS */;
INSERT INTO `transporter_offertransport` VALUES (377,371),(377,372),(377,374),(377,375),(377,376),(378,373);
/*!40000 ALTER TABLE `transporter_offertransport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (335,0,'21232f297a57a5a743894a0e4a801fc3','admin'),(336,0,'882aee9871aa64cabe53aa56ed8a5b1d','fisherman1'),(337,0,'36e5595b7d02333b5169b4b85fc03e95','fisherman2'),(338,0,'48d8e31636708c6206d81d80648056a2','fisherman3'),(339,0,'5cbd9d629096842872fdc665d2d03ba3','buyer1'),(340,0,'ba71d29d4efdd8753c516db594fab6d8','buyer2'),(341,0,'3cb52c98f366dad959eb21181107c7a7','buyer3'),(342,0,'632c2797159252f221ee9b85c059610c','businessman1'),(343,0,'94d7bf4bc7fd209c5b6f10b701bda752','businessman2'),(344,0,'248a99052712131bb838cf189f73d350','businessman3'),(345,0,'f317f2b7bf598eeea034ba7601a79103','transporter1'),(346,0,'110940609dd9168c4fc67b141ae4f055','transporter2'),(347,0,'4385f0854c6b975a9b5c4a354d680a3f','transporter3');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (335,'ADMINISTRATOR'),(336,'FISHERMAN'),(337,'FISHERMAN'),(338,'FISHERMAN'),(339,'BUYER'),(340,'BUYER'),(341,'BUYER'),(342,'BUSINESSMAN'),(343,'BUSINESSMAN'),(344,'BUSINESSMAN'),(345,'TRANSPORTER'),(346,'TRANSPORTER'),(347,'TRANSPORTER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wall`
--

DROP TABLE IF EXISTS `wall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wall` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `offer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wall`
--

LOCK TABLES `wall` WRITE;
/*!40000 ALTER TABLE `wall` DISABLE KEYS */;
INSERT INTO `wall` VALUES (466,0,'wall 1',1),(467,0,'wall 2',2),(468,0,'wall 3',3),(469,0,'wall 4',4),(470,0,'wall 5',5),(471,0,'wall 6',6),(472,0,'wall 7',7),(473,0,'wall 8',8),(474,0,'wall 9',9),(475,0,'wall 10',10),(476,0,'wall 11',11),(477,0,'wall 12',12),(478,0,'wall 13',13);
/*!40000 ALTER TABLE `wall` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-31 11:04:18

commit;
