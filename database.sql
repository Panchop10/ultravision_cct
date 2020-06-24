-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: ultravision
-- ------------------------------------------------------
-- Server version	5.7.25-google-log

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
-- Current Database: `ultravision`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ultravision` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ultravision`;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `is_active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Jennine','McCool','jmccool0@squarespace.com','8217339201','9780 Garrison Center',1),(2,'Noble','Burwell','nburwell1@merriam-webster.com','8442191189','480 Village Trail',1),(3,'Robyn','Benning','rbenning2@yelp.com','6589986612','13158 Melby Trail',1),(4,'Janelle','Carverhill','jcarverhill3@sciencedaily.com','7144345403','15 Tomscot Circle',1),(5,'Silvie','Livingstone','slivingstone4@goodreads.com','9502165384','0 Debra Place',1),(6,'Tootsie','Dobing','tdobing5@cargocollective.com','5919630046','823 Jenifer Avenue',1),(7,'Brunhilde','Mathe','bmathe6@shinystat.com','8821836214','6009 Harper Alley',1),(8,'Karylin','Fashion','kfashion7@wired.com','3156111492','17 Buena Vista Parkway',1),(9,'Zerk','Gumbley','zgumbley8@flickr.com','2221371482','13 Autumn Leaf Point',1),(10,'Jeannette','Cluley','jcluley9@dot.gov','6006109837','9 Lukken Street',1),(11,'Bartel','Pavinese','bpavinesea@surveymonkey.com','2915206003','92461 Grim Drive',1),(12,'Isabella','Brissard','ibrissardb@ocn.ne.jp','3015777533','85357 Donald Street',1),(13,'Wilmar','Klinck','wklinckc@eventbrite.com','2072237059','7742 Hanover Point',1),(14,'Gerome','Treble','gtreble@over-blog.com','8589275927','43 Warner Hill',1),(15,'Oriana','Titford','otitforde@ucoz.com','7163007435','08187 Farwell Court',1),(16,'Avivah','Duffree','aduffreef@gmpg.org','3625414307','02392 Springs Hill',1),(17,'Yance','Wind','ywindg@acquirethisname.com','1312087983','34 Mariners Cove Trail',1),(18,'Dov','Felton','dfeltonh@businessinsider.com','1771047768','75012 Clyde Gallagher Parkway',1),(19,'Nikos','Fernando','nfernandoi@sakura.ne.jp','2624382180','6 5th Park',1),(20,'Arabela','Kief','akiefj@google.nl','9914635052','90 Anhalt Plaza',1),(21,'Catlee','Daton','cdatonk@constantcontact.com','3528917388','15 Kings Center',1),(22,'Burke','Hiddersley','bhiddersleyl@chronoengine.com','7759444886','1 Crest Line Place',1),(23,'Hanan','Bollis','hbollism@mtv.com','6484043119','8 Anderson Junction',1),(24,'Jaymee','Clampe','jclampen@youtube.com','5477725797','425 Monument Avenue',1),(25,'Shina','Gentiry','sgentiryo@wunderground.com','1648182372','480 Lunder Park',1),(26,'Modestia','McAuslene','mmcauslenep@sogou.com','7693789572','1 Union Junction',1),(27,'Leontyne','Dacombe','ldacombeq@economist.com','6728173520','50 Tennyson Alley',1),(28,'Ivar','Ripping','irippingr@about.me','5506471703','1703 Badeau Way',1),(29,'Erny','Towler','etowlers@msu.edu','6132747595','669 Derek Crossing',1),(30,'Chrissy','Ferenczy','cferenczyt@furl.net','9449202772','44 Vermont Parkway',1),(36,'Frederick','Smith','fsmith@gmail.com','5557372819',NULL,1),(37,'Martin','Walcott','martinw@msu.edu','9874612822',NULL,1),(38,'Amilcar','Aponte','amilcar@cct.ie','0989998888',NULL,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `product_uid` varchar(128) NOT NULL,
  `store_id` int(11) NOT NULL,
  `rental_price` int(11) NOT NULL,
  `year_released` int(11) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `disk_type` varchar(45) NOT NULL,
  `allmusic_link` varchar(255) DEFAULT NULL,
  `imdb_link` varchar(255) DEFAULT NULL,
  `director_or_band` varchar(255) DEFAULT NULL,
  `item_type` int(11) NOT NULL,
  `item_status` int(11) DEFAULT NULL,
  `is_active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `store_fk_idx` (`store_id`),
  KEY `item_type_fk_idx` (`item_type`),
  KEY `item_status_fk_idx` (`item_status`),
  CONSTRAINT `item_status_fk` FOREIGN KEY (`item_status`) REFERENCES `item_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item_type_fk` FOREIGN KEY (`item_type`) REFERENCES `item_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `store_item_fk` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'Weekend (a.k.a. Le Week-end) (Week End)','dce6ce82-9fa7-4fad-9db9-884c80ff94b2',1,12,1998,'Drama','DVD',NULL,NULL,'Leo Kohrt',3,1,1),(2,'Dead Air','dc4a3bc0-e3e3-4fbe-8afe-cd608917b8f0',1,11,1987,'Horror|Sci-Fi|Thriller','BLURAY',NULL,NULL,'Ulrika Pentin',4,2,1),(3,'Oh, God! You Devil','f03b5ee7-a945-44e7-8333-dc72002fcfde',1,13,1995,'Comedy','DVD',NULL,NULL,'Agnella Penhearow',3,2,1),(4,'South Shaolin Master, The (Nan quan wang)','59aabf18-aef1-4dab-9699-f96aa055f436',1,11,2002,'Action|Adventure|Drama','DVD',NULL,NULL,'Nikkie Buttriss',4,2,1),(5,'Spring in a Small Town (Xiao cheng zhi chun)','d358204b-c5b5-449f-8e6a-17224b8a5889',1,14,1988,'Drama|Romance','DVD',NULL,NULL,'Launce Shaefer',3,1,1),(6,'Jesus\' Son','10c97375-821d-45f4-89c5-3c5f80b29d19',1,10,1995,'Drama','CD',NULL,NULL,'Saundra Lineker',3,2,1),(7,'Ballast','77523424-9a1a-4fa4-8dc8-92f8d123aee9',1,14,2002,'Drama','CD',NULL,NULL,'Eustacia Cleaton',4,2,1),(8,'Just a Sigh','2fcad9dc-333e-4bad-bdad-e3bfddae4e24',1,13,2008,'Drama|Romance','DVD',NULL,NULL,'Forbes Syversen',3,1,1),(9,'The Army','0cc49074-1159-4501-b5f4-3ea0414ee18a',1,11,1993,'Drama','DVD',NULL,NULL,'Frieda Spalton',3,2,1),(10,'No Looking Back','889f8733-7b02-4010-aa14-83d5fbc40cdf',1,13,1995,'Drama|Romance','CD',NULL,NULL,'Ivor Anderl',3,1,1),(11,'Nanking','f384f0c3-044f-49d9-9d42-04a7212d20c2',1,10,2009,'Documentary|War','BLURAY',NULL,NULL,'Leila McCorkell',4,2,1),(12,'Don Quixote','086b2846-1983-4730-80fe-8f0340fc0aaf',1,10,1968,'Adventure|Comedy|Drama','DVD',NULL,NULL,'Ringo Glas',4,2,1),(13,'Battlestar Galactica','c035310d-4725-4a4e-9457-110bd7972111',1,13,2000,'Drama|Sci-Fi|War','DVD',NULL,NULL,'Cyndy Tremlett',3,2,1),(14,'Games','fa6108d8-10be-4ae4-8ec7-0a6a8af308b0',1,11,1986,'Thriller','DVD',NULL,NULL,'Nickie Baiyle',3,1,1),(15,'The Kick','9911268e-7808-4247-8c20-0d201759448b',1,10,2012,'Action','BLURAY',NULL,NULL,'Cozmo Beauchop',4,2,1),(16,'Falling Awake','49c37836-3b95-476e-83c2-2e07c7e2a4d3',1,11,2002,'Romance|Thriller','BLURAY',NULL,NULL,'Alix Roz',4,2,1),(17,'Oath, The','3ee8bd74-87d5-442b-8808-c202e99b88f6',1,10,1991,'Documentary','DVD',NULL,NULL,'Willdon Marvelley',4,2,1),(18,'Philadelphia Experiment, The','2460e9c2-7e39-4744-bb45-36860713e425',1,14,2005,'Adventure|Drama|Sci-Fi','CD',NULL,NULL,'Pepi Bigley',3,2,1),(19,'Day the Earth Stood Still, The','9d0a5cae-0364-4347-92fb-77d5f5abe4fd',1,12,2010,'Drama|Sci-Fi|Thriller|IMAX','DVD',NULL,NULL,'Farleigh Hellwich',4,1,1),(20,'Without Bias (a.k.a. Len Bias)','ee71a2fa-337a-466c-804a-00bd412cbb4e',1,12,2001,'Documentary','DVD',NULL,NULL,'Abbey Trayte',3,2,1),(21,'Torremolinos 73','f36cdb1a-9444-44dc-8041-722b23d3b94c',1,12,2003,'Comedy|Drama','BLURAY',NULL,NULL,'Myrtle Pomfret',3,2,1),(22,'St. Valentine\'s Day Massacre, The','fff5177e-e354-4df2-80ad-d544ad631027',1,10,1997,'Crime|Drama','BLURAY',NULL,NULL,'Magdalene Knibbs',4,2,1),(23,'Paperhouse','a0d061e1-8766-4369-a2c9-d212f77a58e3',1,12,1992,'Fantasy|Horror|Thriller','CD',NULL,NULL,'Bethany Geraldini',4,2,1),(24,'Casino Jack','94569169-2c09-4799-8835-929233745e84',1,10,2005,'Comedy|Crime','CD',NULL,NULL,'Queenie GiacobbiniJacob',4,2,1),(25,'Edward II','0ef1d5a1-1cf6-4070-80ac-54a2ba4cfdd3',1,11,1986,'Drama','DVD',NULL,NULL,'Jo Humfrey',4,1,1),(26,'Minecraft: The Story of Mojang','bbff7f81-1f44-4341-b8f6-86e116b072c5',1,15,2006,'Documentary','DVD',NULL,NULL,'Kelly O\' Neligan',4,2,1),(27,'Charles Bradley: Soul of America','c82f6023-9c26-4577-8fc8-01f879935ed5',1,12,1999,'Documentary','DVD',NULL,NULL,'Danny Starr',3,2,1),(28,'Playing from the Plate (Grajacy z talerza)','5cb3fdd3-ae9a-42f2-b9b8-b4b55793957b',1,10,1992,'Drama|Fantasy|Mystery','CD',NULL,NULL,'Zebedee Haugh',4,1,1),(29,'Ghost of Frankenstein, The','982dbcf2-d593-40ad-a563-65fd81d6e0d0',1,14,2002,'Horror','CD',NULL,NULL,'Hoebart Lygoe',4,2,1),(30,'Crisis: Behind a Presidential Commitment','3f6d4bff-8bd2-49fe-b215-6cec6be46e1c',1,14,1996,'Documentary','CD',NULL,NULL,'Penrod Micah',4,1,1),(31,'Kaibab Pussytoes','7936b844-419b-4d68-9c88-c65729714a6c',1,10,1992,'Dance','BLURAY',NULL,NULL,'Hurlee Redpath',1,1,1),(32,'Polianthes','056013ea-9864-4d97-8072-c0cb68921558',1,8,1992,'RnB Soul','BLURAY',NULL,NULL,'Lorens Sherer',2,2,1),(33,'Wideleaf Stegonia Moss','b8f09c22-6944-4cb1-a533-bab90558a176',1,5,1998,'Rock Contemporary','DVD',NULL,NULL,'Timmi Tomaszynski',1,2,1),(34,'Xylographa Lichen','705f5cf0-b6b5-43dc-8ccb-fefd376c2532',1,10,2013,'Hip Hop Rap','CD',NULL,NULL,'Malinda Stannis',1,2,1),(35,'Nevada Cryptantha','3a3f0f03-fb1e-4d5a-b4ee-8647460bbc49',1,6,1998,'Metal Death','CD',NULL,NULL,'Petunia Buzek',2,2,1),(36,'American Strigula Lichen','c584902b-4d88-46da-b522-9aeaf6f6d8ad',1,5,2005,'Electronica','BLURAY',NULL,NULL,'Fawn Aronowicz',1,1,1),(37,'Eastern Marsh Fern','f7b25a42-d030-4610-9259-22bd26de3f3f',1,10,2003,'Folk International','CD',NULL,NULL,'Shalom Ingyon',2,2,1),(38,'Purple Witchweed','0e2f8be1-3f2e-4b91-9da9-ca53ef0f721c',1,7,2006,'Hip Hop Rap','CD',NULL,NULL,'Ronnie Lots',1,2,1),(39,'Fringed Twinevine','334e407b-ba14-4efd-a290-6275f4800c7c',1,9,2008,'Metal Death','CD',NULL,NULL,'Cad McNeely',2,1,1),(40,'Cracked Lichen','45b5dc6c-c59a-4148-bfee-3e66e327679d',1,9,2008,'Hip Hop Rap','DVD',NULL,NULL,'Greg de Merida',1,1,1),(41,'Looseflower Milkvetch','00f7a0e2-4d93-4abb-b810-f1ead6f0ed78',1,7,1990,'Blues Contemporary','BLURAY',NULL,NULL,'Hanson Malin',1,1,1),(42,'Ballhead Sandwort','272481ea-078c-4cf7-ab41-614ed5eaf854',1,6,2010,'Dance','CD',NULL,NULL,'Vyky Arnaudin',1,1,1),(43,'Aglaonema','69f94972-c36d-41a2-b142-6da90b67cb99',1,6,1993,'Electronica','DVD',NULL,NULL,'Dorthy Crim',1,1,1),(44,'Japanese Yew','4a3b01fc-b065-4c0b-8792-d75c8c91afa2',1,7,1987,'Pop Indie','BLURAY',NULL,NULL,'Ky Baleine',1,1,1),(45,'Bearded Grasspink','cf905c66-8539-4244-94e5-b84de5465362',1,7,1991,'Metal Heavy','CD',NULL,NULL,'Cross Sibary',2,1,1),(46,'Lobed Fleabane','8e3021e8-a144-4c29-b31f-7fd25b7cb03e',1,8,1998,'RnB Soul','DVD',NULL,NULL,'Goddart McElrea',2,1,1),(47,'Ridgetop Naupaka','6e0ab4be-a84f-4188-a522-b5c89d9bf3b3',1,9,1986,'Folk International','DVD',NULL,NULL,'Gracie Hymus',1,2,1),(48,'Pebble Pincushion','5dbe195c-f6d8-44fb-ad29-6e981ebe80b2',1,8,2001,'Metal Death','DVD',NULL,NULL,'Othella Hadcock',1,2,1),(49,'Southwestern Pricklypoppy','d0c74aa8-b5a9-4150-a296-92ec9a4cd70e',1,9,1993,'Jazz Classic','DVD',NULL,NULL,'Ravid Roskelley',2,2,1),(50,'Largespore Map Lichen','c25501dc-8ed7-4782-a476-c510d4124c1e',1,9,1992,'RnB Soul','BLURAY',NULL,NULL,'Shelia Guidera',1,2,1),(51,'Prostrate Speedwell','dbc5621c-dbb3-4960-b2dd-aaca74467e92',1,6,2002,'Dance','CD',NULL,NULL,'Isaiah Challenor',2,1,1),(52,'Drummond\'s Plagiomnium Moss','948b7165-8030-4b7a-8ff3-8d24174db24b',1,10,2007,'Blues Contemporary','BLURAY',NULL,NULL,'Domenico Lipscombe',2,2,1),(53,'Forbes\' Pipturus','aeabecd4-b9b1-4c08-a2b5-f6c3829a73bb',1,6,2009,'Gospel','DVD',NULL,NULL,'Dania Roddick',2,1,1),(54,'Chaparral Honeysuckle','32109932-2e1e-42c7-95a3-c552aafe9597',1,10,1996,'Dance','DVD',NULL,NULL,'Farris Boscott',1,1,1),(55,'Cyrtandra','7ebe5a27-07f9-4d70-9a54-181797570070',1,6,2001,'Rock Neo Psychedelia','BLURAY',NULL,NULL,'Alix Pallasch',2,2,1),(56,'Chaenothecopsis Lichen','ba325c8c-8a11-4a8e-931d-57338bf47991',1,5,2009,'Metal Alternative','CD',NULL,NULL,'Kristal Veldens',2,1,1),(57,'Miraculous Berry','26e49d25-9adb-497a-a58f-d8a762b1eb27',1,5,2003,'Pop Contemporary','DVD',NULL,NULL,'Angie Lanfere',1,2,1),(58,'Raup\'s Indian Paintbrush','c911f82b-b3ef-47d9-9855-9cdf04f65075',1,5,1997,'Grunge Emo','CD',NULL,NULL,'Ninette Clegg',1,1,1),(59,'Grundlach\'s Attorney','e4a24a06-9f75-4c70-a80d-4271c8ebc64c',1,8,2007,'Pop Latin','BLURAY',NULL,NULL,'Lilly Dyka',2,1,1),(60,'Field Pumpkin','8532c171-b15a-4f0c-93e3-ad8f8cd0e5d0',1,10,2002,'Reggae','BLURAY',NULL,NULL,'Hanson Clayal',1,1,1),(62,'Titanic','d6f584ba-ff90-467c-b163-1005d764b7e9',1,10,1990,'Drama','DVD',NULL,NULL,'Spilberg',3,1,1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_status`
--

DROP TABLE IF EXISTS `item_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_status` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_status`
--

LOCK TABLES `item_status` WRITE;
/*!40000 ALTER TABLE `item_status` DISABLE KEYS */;
INSERT INTO `item_status` VALUES (1,'Perfect'),(2,'Good'),(3,'OK'),(4,'Damaged');
/*!40000 ALTER TABLE `item_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_type`
--

DROP TABLE IF EXISTS `item_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_type` (
  `id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_type`
--

LOCK TABLES `item_type` WRITE;
/*!40000 ALTER TABLE `item_type` DISABLE KEYS */;
INSERT INTO `item_type` VALUES (1,'Music'),(2,'Music Videos'),(3,'Movies'),(4,'TV Box Set');
/*!40000 ALTER TABLE `item_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `loyalty_system`
--

DROP TABLE IF EXISTS `loyalty_system`;
/*!50001 DROP VIEW IF EXISTS `loyalty_system`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `loyalty_system` AS SELECT 
 1 AS `customer_id`,
 1 AS `loyalty_points`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `loyalty_transaction` int(11) NOT NULL,
  `rental_date` datetime NOT NULL,
  `return_date` datetime DEFAULT NULL,
  `status_rented` int(11) NOT NULL,
  `status_returned` int(11) DEFAULT NULL,
  `fine` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rented_idx` (`status_rented`),
  KEY `returned_idx` (`status_returned`),
  KEY `customer_idx` (`customer_id`),
  KEY `item_idx` (`item_id`),
  CONSTRAINT `customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rented` FOREIGN KEY (`status_rented`) REFERENCES `rental_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `returned` FOREIGN KEY (`status_returned`) REFERENCES `rental_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
INSERT INTO `rental` VALUES (1,2,6,10,'2020-02-03 00:00:00','2020-02-06 00:00:00',1,2,10,11),(2,58,19,10,'2020-02-10 00:00:00','2020-02-13 00:00:00',1,3,20,5),(3,8,20,10,'2020-04-08 00:00:00','2020-04-11 00:00:00',1,2,10,13),(4,29,11,10,'2020-04-11 00:00:00','2020-04-14 00:00:00',1,3,20,14),(5,30,26,10,'2020-04-16 00:00:00','2020-04-19 00:00:00',1,3,20,14),(6,32,18,10,'2020-01-03 00:00:00','2020-05-11 04:34:44',1,2,5,8),(7,32,1,10,'2020-01-05 00:00:00','2020-01-08 00:00:00',1,2,10,8),(8,29,14,10,'2020-01-30 00:00:00','2020-02-02 00:00:00',1,1,0,14),(9,21,4,10,'2020-02-27 00:00:00','2020-03-01 00:00:00',1,1,0,12),(10,9,2,10,'2020-04-27 00:00:00','2020-04-30 00:00:00',1,3,20,11),(11,52,1,10,'2020-03-23 00:00:00','2020-03-26 00:00:00',1,3,20,10),(12,35,14,10,'2020-03-29 00:00:00','2020-04-01 00:00:00',1,2,10,6),(13,56,16,10,'2020-02-15 00:00:00','2020-02-18 00:00:00',1,2,10,5),(14,5,4,10,'2020-01-16 00:00:00','2020-01-19 00:00:00',1,2,10,14),(15,40,22,10,'2020-01-28 00:00:00','2020-01-31 00:00:00',1,1,0,9),(16,1,16,10,'2020-04-27 00:00:00','2020-04-30 00:00:00',1,3,20,12),(17,29,16,10,'2020-02-06 00:00:00','2020-02-09 00:00:00',1,1,0,14),(18,3,29,10,'2020-02-18 00:00:00','2020-02-21 00:00:00',1,2,10,13),(19,30,19,10,'2020-02-25 00:00:00','2020-02-28 00:00:00',1,2,10,14),(20,29,30,10,'2020-03-08 00:00:00','2020-03-11 00:00:00',1,3,20,14),(21,38,23,10,'2020-01-08 00:00:00','2020-01-11 00:00:00',1,3,20,7),(22,60,12,10,'2020-04-24 00:00:00','2020-04-27 00:00:00',1,3,20,6),(23,53,20,10,'2020-02-08 00:00:00','2020-02-11 00:00:00',1,1,0,6),(24,55,24,10,'2020-03-01 00:00:00','2020-03-04 00:00:00',1,1,0,6),(25,5,15,10,'2020-04-02 00:00:00','2020-04-05 00:00:00',1,1,0,14),(26,11,19,10,'2020-04-12 00:00:00','2020-04-15 00:00:00',1,1,0,10),(27,26,6,10,'2020-04-07 00:00:00','2020-04-10 00:00:00',1,2,10,15),(28,10,21,10,'2020-01-19 00:00:00','2020-01-22 00:00:00',1,1,0,13),(29,40,18,10,'2020-03-02 00:00:00','2020-03-05 00:00:00',1,1,0,9),(30,8,12,10,'2020-02-23 00:00:00','2020-02-26 00:00:00',1,2,10,13),(31,60,7,10,'2020-01-09 00:00:00','2020-01-12 00:00:00',1,2,10,6),(32,5,29,10,'2020-03-17 00:00:00','2020-03-20 00:00:00',1,3,20,14),(33,14,24,10,'2020-04-05 00:00:00','2020-04-08 00:00:00',1,1,0,11),(34,39,29,10,'2020-04-26 00:00:00','2020-04-29 00:00:00',1,3,20,9),(35,30,25,10,'2020-02-24 00:00:00','2020-02-27 00:00:00',1,1,0,14),(36,15,3,10,'2020-01-04 00:00:00','2020-01-07 00:00:00',1,1,0,10),(37,47,5,10,'2020-01-05 00:00:00','2020-01-08 00:00:00',1,1,0,9),(38,6,14,10,'2020-04-19 00:00:00','2020-04-22 00:00:00',1,2,10,10),(39,59,3,10,'2020-03-02 00:00:00','2020-03-05 00:00:00',1,2,10,8),(40,55,7,10,'2020-02-27 00:00:00','2020-03-01 00:00:00',1,1,0,6),(41,60,8,10,'2020-01-07 00:00:00','2020-01-10 00:00:00',1,2,10,6),(42,10,14,10,'2020-04-28 00:00:00','2020-05-01 00:00:00',1,3,20,13),(43,38,20,10,'2020-02-09 00:00:00','2020-02-12 00:00:00',1,1,0,7),(44,41,17,10,'2020-03-17 00:00:00','2020-03-20 00:00:00',1,1,0,7),(45,45,30,10,'2020-04-17 00:00:00','2020-04-20 00:00:00',1,1,0,7),(46,48,19,10,'2020-04-27 00:00:00','2020-04-30 00:00:00',1,1,0,8),(47,36,4,10,'2020-04-21 00:00:00','2020-04-24 00:00:00',1,1,0,5),(48,53,9,10,'2020-03-15 00:00:00','2020-03-18 00:00:00',1,1,0,6),(49,33,7,10,'2020-01-15 00:00:00','2020-01-18 00:00:00',1,1,0,5),(50,3,29,10,'2020-02-16 00:00:00','2020-02-19 00:00:00',1,1,0,13),(53,60,1,10,'2020-05-11 02:38:54','2020-05-11 04:36:09',1,2,0,10),(54,60,1,10,'2020-05-11 04:35:49','2020-05-11 04:38:06',1,2,5,10),(55,60,1,10,'2020-05-11 04:39:49','2020-05-11 04:40:03',1,4,15,10),(56,57,1,10,'2020-05-11 04:40:21',NULL,2,NULL,0,5),(57,49,20,10,'2020-05-11 04:40:35','2020-06-19 14:39:15',2,2,10,9),(58,8,30,10,'2020-05-11 04:40:46',NULL,1,NULL,0,13),(59,52,37,10,'2020-05-11 04:40:55',NULL,2,NULL,0,10),(60,48,38,10,'2020-06-19 14:38:39',NULL,2,NULL,0,8),(61,43,38,10,'2020-06-19 14:38:47','2020-06-19 14:39:07',1,1,0,6);
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_status`
--

DROP TABLE IF EXISTS `rental_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental_status` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_status`
--

LOCK TABLES `rental_status` WRITE;
/*!40000 ALTER TABLE `rental_status` DISABLE KEYS */;
INSERT INTO `rental_status` VALUES (1,'Perfect'),(2,'Good'),(3,'OK'),(4,'Damaged');
/*!40000 ALTER TABLE `rental_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `rented_per_customer`
--

DROP TABLE IF EXISTS `rented_per_customer`;
/*!50001 DROP VIEW IF EXISTS `rented_per_customer`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `rented_per_customer` AS SELECT 
 1 AS `customer_id`,
 1 AS `items_rented`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_admin` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `store_fk_idx` (`store_id`),
  CONSTRAINT `store_fk` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'jbullivant0','$2y$12$RGDBefx7tkRP.h0k9.DjZbL291xwnhg',0,1),(2,'tsenchenko1','$2y$12$CUeBYrm9nlYZ.e6Q4.oAVZn814rvjha',0,1),(3,'etompsett2','$2y$12$tGWwMEf6Oodj.x0c1.Xadkl319lQUot',0,1),(4,'lfloodgate3','$2y$12$xwrnEaY9OyQv.M3G0.FbINy103tnwcF',0,1),(5,'gmander4','$2y$12$TYpzunU9udNG.M6g0.gthKb723YUNqC',0,1),(6,'fstimson5','$2y$12$liSHdFq4CqDe.a0u1.vlbHJ460fsNRn',0,1),(7,'cendecott6','$2y$12$lMpTXKv1lVZO.U1v3.GoFQD571blXHR',0,1),(8,'teisak7','$2y$12$ZkWSJVy5roSi.X1k2.rQbMw492jubKV',0,1),(9,'gismail8','$2y$12$SmHAXft3hubN.h4k8.NJGTl253HGQJO',0,1),(10,'pyakushkin9','$2y$12$UIjRMYh5Ygoq.v0Q1.dvRIG140aVJBd',0,1);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES (1,'Head Office','30-34 Westmoreland St, Dublin 2, D02 HK35');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `finish_date` date NOT NULL,
  `amount_paid` int(11) NOT NULL,
  `card_number` varchar(20) NOT NULL,
  `card_type` varchar(45) NOT NULL,
  `customer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_idx` (`type`),
  KEY `customer_fk_idx` (`customer_id`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `type_fk` FOREIGN KEY (`type`) REFERENCES `subscription_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (2,3,'2020-04-14','2020-05-14',20,'5250-3038-4114-8054','visa',2),(3,2,'2020-04-09','2020-05-09',30,'5188-0469-1996-6710','jcb',3),(4,4,'2020-03-04','2020-04-03',50,'5173-0930-7598-8825','jcb',4),(5,2,'2020-01-19','2020-02-18',30,'5134-6122-4597-2683','visa',5),(6,2,'2020-02-18','2020-03-19',30,'5147-8828-9537-7147','jcb',6),(7,3,'2020-05-05','2020-06-04',20,'5152-5127-6777-3110','jcb',7),(8,1,'2020-03-18','2020-04-17',20,'5143-0326-8046-7399','jcb',8),(9,1,'2020-02-03','2020-03-04',20,'5153-6678-8748-7854','mastercard',9),(10,2,'2020-03-18','2020-04-17',30,'5156-7581-2010-0393','jcb',10),(11,4,'2020-03-28','2020-04-27',50,'5156-7581-2010-0393','jcb',11),(12,2,'2020-03-11','2020-04-10',30,'5152-8073-2049-7939','china-unionpay',12),(13,4,'2020-03-09','2020-04-08',50,'5189-0734-1179-7686','diners-club-enroute',13),(14,4,'2020-01-15','2020-02-14',50,'5105-7644-0362-9912','diners-club-enroute',14),(15,3,'2020-02-20','2020-03-21',20,'5184-8748-4590-1695','jcb',15),(16,1,'2020-02-05','2020-03-06',20,'5133-3027-3951-0626','jcb',16),(17,4,'2020-02-15','2020-03-16',50,'5104-2898-2637-0491','china-unionpay',17),(18,3,'2020-02-11','2020-03-12',20,'5139-5571-9825-5835','diners-club-carte-blanche',18),(19,4,'2020-01-20','2020-02-19',50,'5185-3123-0755-9078','jcb',19),(20,4,'2020-04-24','2020-05-24',50,'5138-9059-6294-8984','china-unionpay',20),(21,2,'2020-02-17','2020-03-18',30,'5141-4186-2829-1072','switch',21),(22,4,'2020-03-31','2020-04-30',50,'5138-5805-4215-1486','visa-electron',22),(23,2,'2020-02-06','2020-03-07',30,'5183-4117-3632-2320','mastercard',23),(24,1,'2020-04-04','2020-05-04',20,'5115-3602-8176-5455','maestro',24),(25,1,'2020-03-05','2020-04-04',20,'5194-8820-6906-8408','china-unionpay',25),(26,4,'2020-01-10','2020-02-09',50,'5125-9069-7855-2198','jcb',26),(27,1,'2020-04-25','2020-05-25',20,'5123-9694-4162-6779','americanexpress',27),(28,3,'2020-04-16','2020-05-16',20,'5109-5823-9097-6436','switch',28),(29,4,'2020-05-02','2020-06-01',50,'5185-0899-4997-2666','jcb',29),(30,2,'2020-04-23','2020-05-23',30,'5180-2810-4036-4672','jcb',30),(33,2,'2020-05-10','2020-06-10',30,'5188-0469-1996-6710','jcb',3),(34,4,'2020-05-10','2020-06-10',50,'5125-9069-7855-2198','jcb',26),(35,2,'2020-05-10','2020-06-10',30,'5194-8820-6906-8408','china-unionpay',25),(36,1,'2020-05-10','2020-06-10',20,'5115-3602-8176-5455','maestro',24),(37,2,'2020-05-10','2020-06-10',30,'5183-4117-3632-2320','mastercard',23),(38,1,'2020-05-10','2020-06-10',20,'5123-2134-1234-1234','Master Card',1),(40,2,'2020-05-10','2020-06-10',30,'5156-7581-2010-0393','jcb',10),(41,4,'2020-05-10','2020-06-10',50,'5156-7581-2010-0393','jcb',11),(42,2,'2020-05-10','2020-06-10',30,'5141-4186-2829-1072','switch',21),(43,4,'2020-05-10','2020-06-10',50,'5138-5805-4215-1486','visa-electron',22),(44,4,'2020-05-10','2020-06-10',50,'5334-3403-3401-1234','Master Card',36),(45,1,'2020-05-10','2020-06-10',20,'5312-8562-2138-4836','Master Card',37),(46,1,'2020-06-19','2020-07-19',20,'5169-6918-2345-9865','Master Card',38),(47,4,'2020-06-19','2020-07-19',50,'5169-6918-2345-9865','Master Card',38);
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription_type`
--

DROP TABLE IF EXISTS `subscription_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription_type` (
  `id` int(11) NOT NULL,
  `short_name` varchar(45) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `item_limit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription_type`
--

LOCK TABLES `subscription_type` WRITE;
/*!40000 ALTER TABLE `subscription_type` DISABLE KEYS */;
INSERT INTO `subscription_type` VALUES (1,'ML','Music Lovers',20,4),(2,'VL','Video Lovers',30,4),(3,'TV','TV Lovers',20,4),(4,'PR','Premium',50,4);
/*!40000 ALTER TABLE `subscription_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `ultravision`
--

USE `ultravision`;

--
-- Final view structure for view `loyalty_system`
--

/*!50001 DROP VIEW IF EXISTS `loyalty_system`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `loyalty_system` AS select `rental`.`customer_id` AS `customer_id`,sum(`rental`.`loyalty_transaction`) AS `loyalty_points` from `rental` group by `rental`.`customer_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `rented_per_customer`
--

/*!50001 DROP VIEW IF EXISTS `rented_per_customer`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `rented_per_customer` AS select `rental`.`customer_id` AS `customer_id`,count(0) AS `items_rented` from `rental` where isnull(`rental`.`return_date`) group by `rental`.`customer_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 15:39:06
