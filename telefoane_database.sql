-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: telefoane
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'Plastor');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ci_sessions`
--

DROP TABLE IF EXISTS `ci_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ci_sessions` (
  `id` varchar(128) COLLATE utf8_bin NOT NULL,
  `ip_address` varchar(45) COLLATE utf8_bin NOT NULL,
  `timestamp` int(10) unsigned NOT NULL DEFAULT '0',
  `data` blob NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ci_sessions_timestamp` (`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ci_sessions`
--

LOCK TABLES `ci_sessions` WRITE;
/*!40000 ALTER TABLE `ci_sessions` DISABLE KEYS */;
INSERT INTO `ci_sessions` VALUES ('3lq63vk5h4tv5mdim3imkolouejl8oiq','::1',1622221877,'__ci_last_regenerate|i:1622221865;');
/*!40000 ALTER TABLE `ci_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `migration`
--

DROP TABLE IF EXISTS `migration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `migration` (
  `version` varchar(180) COLLATE utf8_bin NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `migration`
--

LOCK TABLES `migration` WRITE;
/*!40000 ALTER TABLE `migration` DISABLE KEYS */;
INSERT INTO `migration` VALUES ('m000000_000000_base',1554366368),('m190404_082240_create_branch_table',1554366378);
/*!40000 ALTER TABLE `migration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `office` (
  `idoffice` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isbranch` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL,
  `parent` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idoffice`)
) ENGINE=MyISAM AUTO_INCREMENT=114 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` VALUES (6,1,'Plastor',0),(7,1,'Plastor Trading',0),(8,0,'Resurse Umane',6),(9,0,'Tehnologia Informatiei',6),(10,0,'Serv. Calitate',6),(11,0,'Pompieri',6),(12,0,'Clapari',6),(13,0,'Marketing',6),(14,0,'Contabilitate',6),(15,0,'Secretariat',6),(16,0,'Oficiu primire',6),(17,0,'Paza Protectie Administrativ',6),(18,0,'Clapari Logistica',6),(19,0,'Clapari Injectie',6),(20,0,'Clapari Marcaj',6),(21,0,'Clapari Montaj',6),(22,0,'Clapari Matriterie',6),(23,0,'Clapari Intretinere',6),(24,0,'Clapari Sef depozit',6),(25,0,'Clapari Fix-fond (Casti)',6),(26,0,'Secretariat 2',6),(27,0,'Sindicat',6),(28,0,'Poarta auto Dispeceri',6),(29,0,'PP-A  Perimetru (gunoi)',6),(30,0,'Cantina',6),(31,0,'PP-A -Dispeceri',6),(32,0,'PP-A- Femei de servici',6),(33,0,'Revizor contabil',6),(34,0,'Financiar',6),(35,0,'Consilieri',6),(36,0,'Birou Masurari 3D',6),(37,0,'Inginerie',6),(38,0,'Asimilari Microproductie',6),(39,0,'Mentenanta Investitii',6),(40,0,'Intretinere Mentenanta',6),(41,0,'A.M.C.',6),(42,0,'Mentenanta Matrite',6),(43,0,'Magazia Matrite',6),(44,0,'Matriterie Clapari',6),(46,0,'Departament Productie',6),(47,0,'Serviciul Productie',6),(48,0,'Injectie Plastor',6),(99,0,'CTC Injectie Plastor',6),(50,0,'Asociatia SAFIR',6),(51,0,'Repere Tehnice',6),(52,0,'Asamblare',6),(53,0,'Corpuri Cave',6),(54,0,'Cumparari',6),(55,0,'Magazia Materiale',6),(56,0,'Vanzari Distributie',6),(57,0,'Facturare',6),(58,0,'Electronisti',6),(59,0,'Clapari Atomic Reisec',6),(60,1,'Pergo',0),(61,0,'Pergo',60),(62,0,'Clapari Montaj J',6),(63,0,'Clapari CTC',6),(64,0,'Clapari Linie montaj',6),(65,0,'Clapari Montaj Atomic H,F',6),(66,0,'Clapari Metode',6),(67,0,'Clapari Magazia Sosoni',6),(68,0,'Plastor Trading',7),(69,1,'Monita',0),(70,0,'Monita',69),(71,1,'Promotic',0),(72,0,'Promotic',71),(73,1,'Recolo',0),(74,0,'Recolo',73),(75,1,'Livaro',0),(76,0,'Livaro',75),(77,1,'Lesto',0),(78,0,'Lesto',77),(79,1,'Pereno',0),(80,0,'Pereno',79),(81,1,'Plastis',0),(82,0,'Plastis',81),(83,0,'Pereno Pompieri',79),(84,0,'Controlling',6),(85,0,'Magazia Mostre',6),(87,0,'Clapari facturare',6),(88,0,'Clapari Mentenanta Electronisti',6),(89,0,'Injectie I- sefi schimb',6),(90,1,'Matriterie Noua (M9)',0),(91,0,'Receptie',90),(92,0,'Sef Atelier',90),(93,0,'Magazie Oteluri',90),(94,0,'Atelier (Hala)',90),(95,0,'Inginer Sef',90),(96,0,'Sef Proiectare',90),(97,0,'Proiectanti CAD',90),(98,0,'Proiectanti CAM',90),(100,0,'Dept. Economic',6),(101,0,'Serv. Calitate - Laborator',6),(102,0,'Laborator Masurari 3D',6),(113,0,'1123',77),(112,0,'aaaa',77);
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offices_person`
--

DROP TABLE IF EXISTS `offices_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offices_person` (
  `idoffice` int(10) unsigned NOT NULL,
  `idperson` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idoffice`,`idperson`),
  KEY `fk_office_has_person_person1` (`idperson`),
  KEY `fk_office_has_person_office` (`idoffice`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offices_person`
--

LOCK TABLES `offices_person` WRITE;
/*!40000 ALTER TABLE `offices_person` DISABLE KEYS */;
INSERT INTO `offices_person` VALUES (8,67),(8,69),(8,226),(9,87),(9,88),(9,89),(9,270),(9,296),(10,45),(10,46),(10,47),(10,49),(11,44),(11,50),(11,51),(11,52),(11,53),(11,157),(12,27),(12,256),(13,70),(13,71),(13,264),(13,273),(15,37),(15,74),(15,94),(17,40),(17,157),(18,28),(18,242),(18,243),(18,244),(18,247),(18,248),(18,249),(19,29),(19,209),(19,210),(19,211),(19,212),(19,213),(19,214),(19,291),(20,31),(21,32),(22,33),(22,132),(22,133),(22,254),(23,35),(24,34),(24,206),(24,207),(24,257),(25,203),(25,204),(25,205),(25,287),(25,288),(26,38),(27,157),(28,54),(28,55),(28,289),(29,42),(29,59),(29,60),(29,61),(30,57),(30,296),(32,63),(32,64),(32,65),(32,66),(35,95),(35,96),(36,196),(37,99),(37,101),(37,102),(37,104),(37,105),(37,276),(37,293),(38,107),(38,108),(39,109),(39,110),(40,112),(40,113),(40,114),(40,118),(40,119),(40,292),(40,295),(41,121),(41,122),(42,129),(42,130),(42,154),(46,144),(47,145),(47,282),(48,147),(48,152),(48,153),(48,265),(48,266),(48,279),(50,145),(51,158),(51,272),(51,274),(53,166),(54,100),(54,168),(54,169),(54,170),(54,171),(54,172),(54,241),(55,176),(55,177),(55,178),(55,179),(55,180),(55,181),(55,182),(55,278),(56,183),(56,184),(56,185),(56,188),(56,189),(56,290),(56,294),(57,191),(57,193),(57,194),(58,115),(58,195),(59,198),(61,197),(61,261),(62,201),(63,200),(63,260),(64,202),(65,202),(66,208),(66,298),(67,215),(67,216),(68,217),(68,218),(68,219),(68,220),(68,221),(68,222),(70,223),(70,224),(70,252),(72,227),(72,228),(74,229),(74,230),(74,231),(74,232),(76,75),(76,233),(78,234),(78,235),(80,80),(80,237),(82,144),(82,238),(83,239),(87,251),(88,116),(88,117),(88,258),(89,150),(91,262),(92,253),(93,131),(95,134),(96,136),(97,135),(97,137),(97,138),(97,140),(97,141),(98,124),(98,125),(98,126),(98,271),(99,151),(99,277),(100,75),(100,77),(100,78),(100,79),(100,80),(100,81),(100,84),(100,86),(100,280),(101,48),(102,284),(102,299);
/*!40000 ALTER TABLE `offices_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `idperson` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `nickname` varchar(45) DEFAULT NULL,
  `telint_id` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idperson`)
) ENGINE=MyISAM AUTO_INCREMENT=310 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (295,'Avram','Petrisor','',0),(27,'Dediu','Daniel','Tedi',66),(28,'Takacs','Janos','',68),(29,'Ilie','Sorin','',74),(31,'Buibas','Calin','',79),(32,'Pek','Liliana','Lili',0),(33,'Borodi','Adrian','Ady',81),(34,'Rosu','Viorica','Vio',0),(35,'Gavrila','Ioan','Nelu',0),(37,'Naghiu','Florica','',52),(38,'Hodorog','Dora','',0),(44,'Pop','Gheorghe','Ghita',0),(40,'Lupse','Ana','Ani',0),(42,'Fulop','Vasile Dan','Vasile',97),(284,'Bulzan','Ionut','',0),(45,'Semes','Aurora Daiana','Daiana',0),(46,'Visoiu','Vasile','',65),(47,'Seres','Alina','',0),(48,'Moga','Cornelia','Neli',729),(49,'Buzdugan','Dan','',0),(50,'Boar','Petru','',63),(51,'Restea','Aurel','',0),(52,'Turcutiu','Teodor','Dele',0),(53,'Pascu','Pavel','',0),(54,'Balaian','Gabriel','',95),(55,'Oprea','Ioan','Ionica',0),(57,'Popa','Maria','Mariuta',99),(196,'Kelemen','Istvan','Pisti',412),(59,'Marinau','Adrian','',97),(60,'Craciun','Aurel','',0),(61,'Nagy','Istvan','Pista',0),(74,'Mariscas','Sorin Virgil','',0),(63,'Ur','Terezia','Teri',0),(64,'Cohut','Titiana','',0),(65,'Farcas','Niculina','',0),(66,'Filipcic','Maria','',0),(67,'Milas','Fabiola','Lia',131),(69,'Sas','Felicia','',132),(70,'Popus','Lavinia','',136),(71,'Cociuba','Eugenia','Jeni',137),(75,'Coroban','Adrian','Adi',720),(77,'Kiss','Maria','',724),(78,'Veres','Marta','',719),(79,'Bacioiu','Corina','',724),(80,'Bococi','Andreea','',723),(81,'Csiszer','Violeta','',719),(84,'Cristea','Simona','',722),(86,'Cazan','Ileana','Lenuta',721),(87,'Negrau','Florian','',159),(88,'Xasant','Floarea','Mariana',159),(89,'Rosu','Marius','',62),(287,'Dovin','Cristian','',0),(94,'Cionca','Felicia','',132),(95,'Ganea','Sebastian','Sebi',168),(96,'Lazau','Florentina','',168),(257,'Sabau','Edit','',0),(291,'Hora','Mircea','',0),(99,'Bercea','Ioan','Puiu',176),(100,'Farcasiu','Simona','',299),(101,'Kovacs','Ildiko','',177),(102,'Mihaies','Adrian','Adi',575),(104,'Enache','Dan','',575),(105,'Puscas','Aurel','',575),(107,'Ruge','Viorel','',185),(108,'Maghiar','Cristian','',185),(109,'Bodog','Emil','',0),(110,'Foica','Nicolae','Nicu',190),(292,'Chilba','Gheorghe','',0),(112,'Chilba','Marian','',193),(113,'Zifceac','Iosif','',0),(114,'Mate','Alexandru','',0),(115,'Szabo','Csaba','',395),(116,'Bradea','Adrian','',0),(117,'Buha','Florin','',0),(118,'Ghilba','George','',0),(119,'Mariscas','Tatiana','',0),(290,'Orboi','Dorina','',446),(121,'Pokornyi','Iuliu','Gyuszi',0),(122,'Barta','Stefan','Istvan',0),(253,'Ogrean','Milu','',605),(124,'Lazar','Florin','',0),(125,'Florut','Ioan','',0),(126,'Hering','Adrian','',0),(129,'Borzan','Vasile','',208),(130,'Rozik','Iosif','',0),(131,'Buescu','Tudor','',0),(132,'Saiti','Zoltan','',0),(133,'Beke','Stelian','',0),(134,'Prada','Adrian','',610),(135,'Ilie','Eva','',0),(136,'Baciu','Nicolae','Nicu',222),(137,'Perei','Zoltan','Zoli',610),(138,'Purcarin','Viorica','',610),(140,'Major','Laszlo','Laci',610),(141,'Pop','Adrian','Adi',222),(144,'Hodisan','Sorin','',0),(145,'Sabau','Elena','',0),(147,'Ungur','Horia','',0),(300,'test','test','testnick',0),(150,'Radu','Viorel','',265),(151,'Buta','Gheorghe','',0),(152,'Vant','Lucia','',248),(153,'Morgovan','Florica','',248),(154,'Ardelean','Petru','',208),(157,'Pascu','Florin','',0),(158,'Luncan','Lucian','',270),(272,'Negrau','Ramona','',270),(166,'David','Florentin','',280),(294,'Trifan','Dana','',0),(168,'Fazekas','Gabriela','',298),(169,'Haragos','Romeo','Romi',297),(170,'Marina','Calin','',298),(171,'Mihail','Lucian','',301),(172,'Prada','Florina','',300),(176,'Cosman','Ileana','Icu',313),(177,'Pop','Angela','Angi',313),(178,'Papp','Zorita','Hajni',536),(179,'Borodan','Florin','',0),(180,'Kupe','Stefan','Stefi',0),(181,'Susman','Virgil','',0),(182,'Fazecas','Vasile','',313),(183,'Bacter','Cristian','Cristi',447),(184,'Ardelean','Cornelia','',449),(185,'Crisan','Vasile','',446),(293,'Hajdu','Gergely','',0),(188,'Sotoc','Florica','',449),(189,'Dediu','Viviana','Vivi',448),(191,'Oprean','Mariana','',341),(193,'Furtos','Mariana','',342),(194,'Fodor','Angela','',0),(195,'Chilba','George','',0),(197,'Torjoc','Ramona','',0),(198,'Lupu Zaharia','Adriana','',0),(200,'Foiut','Magda','',0),(201,'Bochis','Terezia','',0),(202,'Tepele','Rodica','',415),(203,'Stanescu','Ioana','',85),(204,'Tocai','Ioana','',85),(205,'Pop','Rodica','',85),(206,'Szabo','Zoltan','',0),(207,'Kun','Victor','',0),(208,'Balajti','Alexandru','Sanyi',0),(209,'Tripon','Ionel','',0),(210,'Erdeli','Florian','',74),(211,'Csilik','Lucian','',0),(212,'Hora','Marioara','',0),(213,'Maghiar','Alin','',0),(214,'Andras','Monica Maria','',74),(215,'Podila','Monica','',0),(216,'Julai','Aurica','',0),(217,'Popus','Dan','',504),(218,'Seres','Radu','',0),(219,'Koteles','Francisc','Feri',0),(220,'Sana','Dinu','',504),(221,'Stoian','Adrian','Adi',504),(222,'Croitoru','Diana','',0),(223,'Negrut','Gheorghe','Ghita',0),(224,'Naghiu','Florian','',0),(226,'Buda','Rodica','',131),(227,'Omut','Irma','',563),(228,'Mitra','Gabriela','Gabi',563),(229,'Covaciu','Silviu','',0),(230,'Chis','Mihai','',0),(231,'Kovacs','Eugen','',0),(232,'Oros','Dana','',0),(233,'Porumb','Ovidiu','Ovi',0),(234,'Mocanu','Tudor','',0),(235,'Balint','Vasile','',0),(237,'Ciaca','Ioan','Nelu',0),(238,'Luca','Florin','',0),(239,'Mateas','Florin','',0),(241,'Szekely','Lajos','Lali',299),(242,'Bughiu','Petru','',559),(243,'Herman','Otilia','',73),(244,'Radulescu','Daniela','',68),(247,'Verdesi','Mihaela','',0),(248,'Neagu','Viorica','Vio',73),(249,'Baciu','Camelia','',73),(279,'Oprea','Costel','',248),(254,'Milas','Mihai','',74),(289,'Bar','Sorin Florin','',0),(270,'Berdie','Dorel','Doru',159),(251,'Jurjut','Ana','Ani',0),(258,'Haidu','Remus','',0),(271,'Szakall','Imre','',0),(252,'Chiscoci','Gheorghe','George',0),(256,'Onaca','Maria','',77),(282,'Campan','Valeria','Vali',581),(278,'Capilnean','Eva','',536),(261,'Iftimiciuc','Dan','',0),(260,'Panzaru','Teodora','',413),(262,'Ivasca','Dacian','',605),(299,'Marian','Flaviu','',0),(264,'David','Nicoleta','',138),(265,'Balaian','Lucian','',248),(266,'Maris','Madalin','',0),(280,'Neaga','Andreea Dana','Dana',767),(273,'Osvat','Catalin','',137),(274,'Bulzan','Florin','',270),(298,'Kulpinszki','Stefan','',0),(276,'Radu','Popovici','',0),(288,'Chilba','Marinela','',0),(277,'Florea','Ioana','',0),(296,'Santau','Octavian','Tavi',62),(301,'test','test','testnick',0),(302,'test','test','testnick',0),(303,'test','test','testnick',0),(304,'test','test','testnick',0),(305,'test','test','testnick',0),(306,'test','test','testnick',0),(307,'test','test','testnick',0),(308,'244','444','444',0);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `idphone` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(20) NOT NULL,
  `fix` tinyint(4) NOT NULL DEFAULT '0',
  `interior` tinyint(4) NOT NULL DEFAULT '0',
  `mobil` tinyint(4) NOT NULL DEFAULT '0',
  `serv` tinyint(4) NOT NULL DEFAULT '0',
  `idoffice` int(10) NOT NULL DEFAULT '0',
  `idperson` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idphone`)
) ENGINE=MyISAM AUTO_INCREMENT=786 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (55,'111',0,1,0,0,16,0),(59,'166',0,1,0,0,17,0),(26,'0259323158',1,0,0,0,3,0),(60,'211',0,1,0,0,17,0),(56,'131',0,1,0,0,16,0),(57,'0259207111',1,0,0,0,16,0),(766,'111',0,1,0,0,15,0),(91,'103',0,1,0,0,26,0),(62,'154',0,1,0,0,9,0),(63,'138',0,1,0,0,11,0),(64,'0751291806',0,0,0,1,11,0),(65,'150',0,1,0,0,10,0),(66,'106',0,1,0,0,12,0),(86,'0744791277',0,0,0,1,0,27),(68,'206',0,1,0,0,18,0),(569,'0746629168',0,0,1,0,0,40),(70,'0359411489',1,0,0,0,0,28),(71,'0359174104',1,0,0,0,0,27),(73,'205',0,1,0,0,18,0),(74,'126',0,1,0,0,19,0),(77,'210',0,1,0,0,12,0),(583,'189',0,1,0,0,88,0),(79,'197',0,1,0,0,20,0),(80,'160',0,1,0,0,21,0),(81,'132',0,1,0,0,22,0),(82,'137',0,1,0,0,22,0),(83,'153',0,1,0,0,23,0),(84,'108',0,1,0,0,24,0),(85,'173',0,1,0,0,25,0),(89,'0726241382',0,0,1,0,0,32),(90,'0745304534',0,0,0,1,0,37),(92,'0741569036',0,0,1,0,0,38),(104,'0726286236',0,0,1,0,0,33),(95,'121',0,1,0,0,28,0),(769,'0744225370',0,0,1,0,0,298),(97,'185',0,1,0,0,29,0),(98,'0752484533',0,0,1,0,0,42),(99,'152',0,1,0,0,30,0),(730,'199',0,1,0,0,102,0),(102,'179',0,1,0,0,27,0),(105,'0743006045',0,0,1,0,0,45),(106,'0733751951',0,0,1,0,0,47),(107,'0743965417',0,0,1,0,0,48),(108,'0745529162',0,0,1,0,0,46),(756,'0743342374',1,0,0,0,0,295),(110,'0742211223',0,0,1,0,0,50),(111,'0747256157',0,0,1,0,0,51),(112,'0771358173',0,0,1,0,0,52),(113,'0770104115',0,0,1,0,0,53),(114,'0749241308',0,0,1,0,0,54),(115,'0743083040',0,0,1,0,0,55),(116,'0747340045',0,0,1,0,0,57),(400,'0359412254',1,0,0,0,0,33),(118,'0770540153',0,0,1,0,0,59),(119,'0743360819',0,0,1,0,0,60),(120,'0742600007',0,0,1,0,0,61),(143,'0753054671',0,0,1,0,0,75),(731,'167',0,1,0,0,10,0),(127,'0746865099',0,0,1,0,0,63),(128,'0742151728',0,0,1,0,0,64),(129,'0752765313',0,0,1,0,0,65),(130,'0752495337',0,0,1,0,0,66),(131,'120',0,1,0,0,8,0),(132,'140',0,1,0,0,8,0),(133,'0744512141',0,0,1,0,0,67),(135,'0748708483',0,0,1,0,0,69),(136,'148',0,1,0,0,13,0),(137,'109',0,1,0,0,13,0),(138,'165',0,1,0,0,13,0),(139,'0740244472',0,0,0,1,0,70),(140,'0744510722',0,0,1,0,0,71),(599,'0259469408',1,0,0,0,61,0),(581,'182',0,1,0,0,47,0),(145,'0744782970',0,0,1,0,0,77),(146,'0749184201',0,0,1,0,0,78),(147,'0745665905',0,0,1,0,0,79),(362,'0755127130',0,0,1,0,0,80),(149,'0727827469',0,0,1,0,0,81),(587,'0731318534',0,0,1,0,0,261),(722,'162',0,1,0,0,100,0),(152,'134',0,1,0,0,33,0),(575,'157',0,1,0,0,37,0),(728,'0773 795359',1,0,0,0,0,195),(158,'0742407522',0,0,1,0,0,86),(159,'118',0,1,0,0,9,0),(511,'0770153277',0,0,1,0,0,87),(161,'0770113314',0,0,1,0,0,88),(684,'0746209062',0,0,1,0,0,89),(663,'0359437610',1,0,0,0,90,0),(759,'147',0,1,0,0,35,0),(760,'0758068121',0,0,0,1,0,49),(170,'0744767774',0,0,0,1,0,94),(171,'0728059366',0,0,1,0,0,95),(533,'0749073114',0,0,1,0,0,96),(173,'212',0,1,0,0,36,0),(582,'108',0,1,0,0,87,0),(745,'0771328612',0,0,1,0,0,102),(176,'218',0,1,0,0,37,0),(177,'144',0,1,0,0,37,0),(531,'0740252562',0,0,0,1,0,99),(179,'0723448164',0,0,1,0,0,100),(180,'0770113546',0,0,1,0,0,101),(746,'0740 052 272',1,0,0,0,0,290),(183,'0771524218',0,0,1,0,0,104),(184,'0731832481',0,0,1,0,0,105),(185,'190',0,1,0,0,38,0),(723,'193',0,1,0,0,100,0),(187,'0730104884',0,0,1,0,0,107),(188,'0748837431',0,0,1,0,0,108),(189,'143',0,1,0,0,39,0),(190,'107',0,1,0,0,39,0),(191,'0740167257',0,0,0,1,0,109),(192,'0744773258',0,0,0,1,0,110),(193,'174',0,1,0,0,40,0),(729,'209',0,1,0,0,101,0),(195,'0742356360',0,0,0,1,0,112),(514,'0359454471',1,0,0,0,0,113),(197,'0770273194',0,0,1,0,0,114),(738,'0740764908',1,0,0,0,0,287),(517,'0771667663',0,0,0,1,0,115),(200,'0744792105',0,0,1,0,0,116),(201,'0746140973',0,0,1,0,0,117),(202,'0771767518',0,0,0,1,0,117),(203,'0744935614',0,0,1,0,0,118),(204,'0740792150',0,0,1,0,0,119),(205,'155',0,1,0,0,41,0),(206,'0740549845',0,0,1,0,0,121),(207,'0770107270',0,0,1,0,0,122),(208,'186',0,1,0,0,42,0),(211,'0745401512',0,0,1,0,0,124),(212,'0743688123',0,0,1,0,0,125),(213,'0726688925',0,0,1,0,0,126),(763,'0770122676',0,0,0,1,0,129),(217,'0740961836',0,0,1,0,0,130),(219,'0730021746',0,0,1,0,0,131),(220,'0743285647',0,0,1,0,0,132),(221,'0745964468',0,0,1,0,0,133),(223,'0746046399',0,0,0,1,0,134),(224,'0259414171',1,0,0,0,0,134),(225,'0742128410',0,0,1,0,0,135),(226,'0359439704',1,0,0,0,0,135),(227,'0723133816',0,0,1,0,0,136),(228,'0359419945',1,0,0,0,0,136),(229,'0744684389',0,0,1,0,0,137),(230,'0771077612',0,0,1,0,0,137),(232,'0359442755',1,0,0,0,0,138),(233,'0744868882',0,0,1,0,0,138),(600,'0359401332',1,0,0,0,61,0),(590,'0722628846',0,0,1,0,0,196),(236,'0359413260',1,0,0,0,0,140),(237,'0742833796',0,0,1,0,0,140),(667,'0359437610/115',0,1,0,0,95,0),(622,'0771562145',0,0,1,0,0,141),(242,'110',0,1,0,0,46,0),(243,'0744773267',0,0,0,1,0,144),(696,'0753680113',0,0,1,0,0,280),(690,'0770456362',0,0,0,1,0,278),(246,'0359436716',1,0,0,0,0,145),(270,'149',0,1,0,0,51,0),(248,'171',0,1,0,0,48,0),(249,'0359405729',1,0,0,0,0,147),(250,'0752318549',0,0,0,1,0,147),(740,'0753054676',1,0,0,0,0,288),(739,'173',1,0,0,0,0,288),(253,'0359444952',1,0,0,0,0,149),(254,'0743396669',0,0,1,0,0,149),(255,'0359438047',1,0,0,0,0,150),(256,'0770101412',0,0,1,0,0,150),(257,'0359431020',1,0,0,0,0,151),(258,'0770135387',0,0,1,0,0,151),(259,'0359303280',1,0,0,0,0,152),(260,'0742287963',0,0,1,0,0,152),(261,'0359805207',1,0,0,0,0,153),(262,'0771296449',0,0,0,1,0,153),(263,'0359195988',1,0,0,0,0,154),(733,'0758031002',0,0,1,0,0,154),(688,'168',0,1,0,0,48,0),(269,'0745627639',0,0,1,0,0,157),(271,'0359431101',1,0,0,0,0,158),(272,'0742019261',0,0,1,0,0,158),(692,'0772064211',0,0,1,0,0,279),(275,'104',0,1,0,0,52,0),(724,'195',0,1,0,0,100,0),(721,'161',0,1,0,0,100,0),(280,'181',0,1,0,0,53,0),(689,'183',0,1,0,0,99,0),(283,'0744456885',0,0,1,0,0,166),(749,'0359418518',1,0,0,0,0,38),(286,'0259416353',1,0,0,0,0,74),(287,'0359808352',1,0,0,0,0,157),(288,'0770131501',0,0,1,0,0,40),(289,'0359175104',1,0,0,0,0,40),(290,'0259433620',1,0,0,0,0,42),(291,'0359415328',1,0,0,0,0,59),(293,'0359410883',1,0,0,0,0,53),(294,'0359436981',1,0,0,0,0,52),(295,'0359435862',1,0,0,0,0,50),(296,'0259433973',1,0,0,0,0,63),(297,'129',0,1,0,0,54,0),(298,'196',0,1,0,0,54,0),(299,'163',0,1,0,0,54,0),(300,'113',0,1,0,0,54,0),(301,'135',0,1,0,0,54,0),(302,'0746637463',0,0,1,0,0,168),(303,'0359404203',1,0,0,0,0,169),(304,'0728738805',0,0,1,0,0,169),(305,'0748928936',0,0,1,0,0,170),(306,'0359177131',1,0,0,0,0,171),(307,'0744517128',0,0,1,0,0,171),(308,'0749241346',0,0,1,0,0,172),(589,'0745605706',0,0,0,1,0,253),(586,'0745506141',0,0,1,0,0,260),(720,'146',0,1,0,0,100,0),(313,'124',0,1,0,0,55,0),(536,'219',0,1,0,0,55,0),(317,'0359402784',1,0,0,0,0,176),(318,'0751043560',0,0,1,0,0,176),(319,'0359436722',1,0,0,0,0,177),(320,'0749074159',0,0,1,0,0,177),(321,'0259441173',1,0,0,0,0,178),(322,'0744636327',0,0,1,0,0,178),(323,'0259436217',1,0,0,0,0,179),(324,'0359438602',1,0,0,0,0,180),(325,'0748953382',0,0,1,0,0,180),(326,'0788464260',0,0,1,0,0,181),(327,'0752412163',0,0,1,0,0,182),(328,'0359802349',1,0,0,0,0,65),(331,'0752318530',0,0,0,1,0,183),(332,'0259478099',1,0,0,0,0,184),(333,'0752318553',0,0,0,1,0,184),(334,'0752318552',0,0,0,1,0,185),(753,'0756340118',1,0,0,0,0,293),(337,'0359425280',1,0,0,0,0,188),(338,'0742684322',0,0,1,0,0,188),(339,'0740134244',0,0,1,0,0,189),(736,'0751197249',0,0,1,0,0,170),(341,'136',0,1,0,0,57,0),(342,'114',0,1,0,0,57,0),(343,'0359425016',1,0,0,0,0,191),(344,'0746280656',0,0,1,0,0,191),(347,'0359455412',1,0,0,0,0,193),(348,'0744535538',0,0,1,0,0,193),(349,'0359418651',1,0,0,0,0,194),(350,'0744771403',0,0,1,0,0,194),(741,'131',1,0,0,0,0,74),(353,'0359415528',1,0,0,0,0,168),(354,'0359466134',1,0,0,0,0,67),(356,'0259467453',1,0,0,0,0,69),(357,'0259411203',1,0,0,0,0,70),(358,'0359423832',1,0,0,0,0,71),(360,'0359174460',1,0,0,0,0,86),(361,'0359801005',1,0,0,0,0,79),(719,'127',0,1,0,0,100,0),(529,'0359401704',1,0,0,0,0,87),(365,'0359801087',1,0,0,0,0,88),(686,'0741248309',0,0,1,0,0,136),(601,'0359401333',1,0,0,0,61,0),(368,'0259416393',1,0,0,0,0,94),(369,'0359411180',1,0,0,0,0,95),(371,'0359438744',1,0,0,0,0,45),(372,'0359801477',1,0,0,0,0,48),(373,'0259418881',1,0,0,0,0,49),(374,'0745450889',0,0,1,0,0,49),(744,'0743341393',1,0,0,0,0,289),(743,'121',1,0,0,0,0,289),(378,'0259417528',1,0,0,0,0,99),(379,'0359466486',1,0,0,0,0,100),(380,'0359418424',1,0,0,0,0,101),(381,'0359446641',1,0,0,0,0,102),(382,'0359806148',1,0,0,0,0,104),(383,'0721631422',0,0,1,0,0,104),(384,'0359425435',1,0,0,0,0,107),(385,'0259351215',1,0,0,0,0,108),(386,'0259410112',1,0,0,0,0,110),(387,'0259477277',1,0,0,0,0,109),(751,'0359427769',1,0,0,0,0,292),(389,'0359423664',1,0,0,0,0,112),(390,'0359416940',1,0,0,0,0,114),(391,'0359417179',1,0,0,0,0,115),(392,'0359809575',1,0,0,0,0,117),(393,'0359427769',1,0,0,0,0,195),(752,'0747043008',0,0,1,0,0,292),(395,'139',0,1,0,0,58,0),(396,'0259416353',1,0,0,0,0,119),(398,'0359428307',1,0,0,0,0,124),(399,'0359407896',1,0,0,0,0,126),(401,'0359422685',1,0,0,0,0,132),(402,'0359439704',1,0,0,0,0,29),(403,'0771523660',0,0,1,0,0,29),(404,'0744299990',0,0,1,0,0,29),(405,'110',0,1,0,0,50,0),(406,'0755094739',0,0,1,0,0,145),(574,'0772225980',0,0,1,0,0,243),(592,'0749161214',0,0,1,0,0,265),(409,'0728284691',0,0,1,0,0,197),(410,'188',0,1,0,0,59,0),(411,'116',0,1,0,0,62,0),(413,'151',0,1,0,0,63,0),(414,'119',0,1,0,0,64,0),(415,'105',0,1,0,0,64,0),(416,'0740113619',0,0,1,0,0,203),(417,'0740476615',0,0,1,0,0,204),(418,'0741424561',0,0,1,0,0,205),(419,'153',0,1,0,0,65,0),(420,'153',1,0,0,0,0,202),(421,'0770151367',0,0,1,0,0,202),(422,'0742003232',0,0,1,0,0,200),(423,'0741380606',0,0,1,0,0,206),(424,'0745224910',0,0,1,0,0,207),(425,'108',0,1,0,0,66,0),(426,'153',0,1,0,0,66,0),(427,'0770124906',0,0,1,0,0,208),(428,'0771298492',0,0,0,1,0,209),(429,'0727748832',0,0,1,0,0,209),(430,'0771299130',0,0,0,1,0,210),(431,'0752261040',0,0,1,0,0,210),(432,'0359429089',1,0,0,0,0,210),(433,'0745704779',0,0,1,0,0,211),(434,'0770120775',0,0,1,0,0,211),(435,'0771520453',0,0,1,0,0,212),(436,'0744352487',0,0,1,0,0,213),(767,'117',0,1,0,0,100,0),(438,'0359432413',1,0,0,0,0,214),(439,'0770121912',0,0,1,0,0,214),(538,'0771667680',0,0,1,0,0,28),(441,'176',0,1,0,0,67,0),(754,'0745 627637',1,0,0,0,0,294),(443,'0745112986',0,0,0,1,0,74),(444,'0771492256',0,0,1,0,0,44),(445,'166',0,1,0,0,32,0),(446,'128',0,1,0,0,56,0),(447,'130',0,1,0,0,56,0),(448,'156',0,1,0,0,56,0),(449,'164',0,1,0,0,56,0),(568,'0744774919',0,0,0,1,0,252),(451,'0722673791',0,0,1,0,0,217),(452,'0731290323',0,0,1,0,0,218),(453,'0722673809',0,0,1,0,0,219),(454,'0727133305',0,0,1,0,0,220),(455,'0731318544',0,0,1,0,0,221),(456,'0731290320',0,0,1,0,0,222),(572,'0259467711',1,0,0,0,70,0),(594,'0751246813',0,0,1,0,0,223),(459,'0744535706',0,0,1,0,0,224),(576,'0771622646',0,0,1,0,0,241),(563,'123',0,1,0,0,72,0),(562,'216',0,1,0,0,72,0),(755,'0770 117731',1,0,0,0,0,226),(464,'0770154011',0,0,1,0,0,227),(465,'0770370712',0,0,1,0,0,228),(560,'142',0,1,0,0,51,0),(468,'0745506613',0,0,1,0,0,229),(469,'0745524260',0,0,1,0,0,230),(470,'0745506612',0,0,1,0,0,231),(471,'0744180760',0,0,1,0,0,232),(472,'0259433843',1,0,0,0,74,0),(571,'141',0,1,0,0,70,0),(474,'0259467723',1,0,0,0,60,0),(475,'0359311991',1,0,0,0,60,0),(476,'0359195123',1,0,0,0,7,0),(477,'0259411594',1,0,0,0,7,0),(478,'0259410328',1,0,0,0,71,0),(479,'0259467722',1,0,0,0,71,0),(480,'0259433843',1,0,0,0,73,0),(701,'0733973659',0,0,1,0,0,273),(482,'0259415393',1,0,0,0,75,0),(483,'0259415393',1,0,0,0,76,0),(484,'0744783989',0,0,1,0,0,233),(770,'143',0,1,0,0,77,0),(492,'0259207143',1,0,0,0,77,0),(487,'143',0,1,0,0,78,0),(488,'0758068316',0,1,0,0,78,0),(489,'0758068316',0,0,1,0,0,234),(490,'0749150492',0,0,1,0,0,235),(494,'0359199311',1,0,0,0,79,0),(495,'0259412223',1,0,0,0,79,0),(496,'200',0,1,0,0,80,0),(497,'0359199311',1,0,0,0,80,0),(498,'0259412223',1,0,0,0,80,0),(567,'0744396266',0,0,0,1,0,237),(501,'0733106331',0,0,1,0,81,0),(502,'0733106331',1,0,0,0,82,0),(503,'0731038257',0,0,1,0,0,238),(504,'215',0,1,0,0,68,0),(505,'0259408335',1,0,0,0,0,219),(506,'0259408340',1,0,0,0,0,220),(507,'0259408330',1,0,0,0,0,222),(508,'0259408346',1,0,0,0,0,221),(509,'0751291807',0,0,1,0,83,0),(510,'0751291807',0,0,1,0,0,239),(580,'110',0,1,0,0,47,0),(516,'0772018702',0,0,0,1,0,113),(519,'0720405460',0,0,1,0,0,115),(521,'0745264856',0,0,0,1,0,34),(522,'0746601833',0,0,0,1,0,31),(596,'0744512788',0,0,1,0,0,270),(524,'0771667671',0,0,0,1,0,157),(539,'0744292622',0,0,0,1,0,28),(526,'0771667682',0,0,0,1,0,152),(527,'0771291259',0,0,0,1,0,200),(785,'259323222',1,0,0,0,0,34),(747,'0742223284',0,0,1,0,0,291),(557,'0722219558',0,0,0,1,0,244),(564,'0741300327',0,0,1,0,0,251),(550,'0771116234',0,0,0,1,0,247),(559,'204',0,1,0,0,18,0),(554,'0772233262',0,0,0,1,0,242),(704,'0742924565',1,0,0,0,0,264),(677,'0359437633',1,0,0,0,95,0),(678,'0359437610/117',1,0,0,0,97,0),(682,'123',0,1,0,0,71,0),(671,'0359437610/118',0,1,0,0,98,0),(673,'0359437610/112',0,1,0,0,92,0),(674,'0359437633',1,0,0,0,92,0),(665,'0359437610/114',0,1,0,0,94,0),(675,'0359437610/116',0,1,0,0,96,0),(676,'0359437579',1,0,0,0,96,0),(681,'0742861562',0,0,1,0,0,94),(640,'0359437610-118',0,0,0,1,0,271),(672,'0359437610/111',0,1,0,0,91,0),(683,'169',0,1,0,0,43,0),(669,'0359437610/113',0,1,0,0,93,0),(737,'173',1,0,0,0,0,287),(757,'0771763082',0,0,1,0,0,296),(782,'1233',1,0,0,0,0,0),(781,'3214',0,1,0,0,113,0),(780,'233',0,1,0,0,112,0),(783,'7895',0,0,0,0,0,0);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `search`
--

DROP TABLE IF EXISTS `search`;
/*!50001 DROP VIEW IF EXISTS `search`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `search` AS SELECT 
 1 AS `fname`,
 1 AS `lname`,
 1 AS `name`,
 1 AS `branch`,
 1 AS `idoffice`,
 1 AS `number`,
 1 AS `telserv`,
 1 AS `telfix`,
 1 AS `telmobil`,
 1 AS `idperson`,
 1 AS `nickname`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `telserv`
--

DROP TABLE IF EXISTS `telserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telserv` (
  `tel_serv` varchar(45) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telserv`
--

LOCK TABLES `telserv` WRITE;
/*!40000 ALTER TABLE `telserv` DISABLE KEYS */;
INSERT INTO `telserv` VALUES ('0740167257'),('0758068121'),('0740236426'),('0740252562'),('0742081634'),('0742356360'),('0744767774'),('0744773258'),('0744773267'),('0752318549'),('0745112986'),('0745264854'),('0745304534'),('0752260527'),('0746159785'),('0747043008'),('0747498415'),('0751291806'),('0740244472'),('0744791277'),('0740150312'),('0746601833'),('0745264856'),('0745605706'),('0746046399'),('0752318530'),('0752318551'),('0752318552'),('0752318553'),('0771667671'),('0771667674'),('0771667680'),('0771667663'),('0771667668'),('0771767518'),('0770455497'),('0772018507'),('0772018702'),('0771667682'),('0771297270'),('0771296449'),('0771291259'),('0771299130'),('0771298492'),('0770456362'),('0771116234'),('0772206101'),('0772212016'),('0772219558'),('0772225980'),('0772233262'),('0772206696'),('0772204664'),('0772250927'),('0772251248'),('0771632956');
/*!40000 ALTER TABLE `telserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL DEFAULT '',
  `email` varchar(100) NOT NULL DEFAULT '',
  `fname` varchar(25) NOT NULL DEFAULT '',
  `lname` varchar(25) NOT NULL DEFAULT '',
  `isadmin` tinyint(1) NOT NULL DEFAULT '0',
  `password` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (3,'admin','it@plastor.ro','Administrator','Administrator',1,'YWRtaW4='),(9,'dcovaliu','office2@plastor.ro','Covaliu','Dora',0,'YWRtaW4=');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'telefoane'
--

--
-- Final view structure for view `search`
--

/*!50001 DROP VIEW IF EXISTS `search`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `search` AS select `person`.`fname` AS `fname`,`person`.`lname` AS `lname`,`office`.`name` AS `name`,`op`.`name` AS `branch`,`office`.`idoffice` AS `idoffice`,`phone`.`number` AS `number`,`tel`.`number` AS `telserv`,`tfix`.`number` AS `telfix`,`tmob`.`number` AS `telmobil`,`person`.`idperson` AS `idperson`,`person`.`nickname` AS `nickname` from (((((((`person` join `offices_person` on((`person`.`idperson` = `offices_person`.`idperson`))) join `office` on((`office`.`idoffice` = `offices_person`.`idoffice`))) join `phone` on((`phone`.`idoffice` = `office`.`idoffice`))) join `office` `op` on((`office`.`parent` = `op`.`idoffice`))) left join `phone` `tel` on(((`person`.`idperson` = `tel`.`idperson`) and (`tel`.`serv` = 1)))) left join `phone` `tfix` on(((`person`.`idperson` = `tfix`.`idperson`) and (`tfix`.`fix` = 1)))) left join `phone` `tmob` on(((`person`.`idperson` = `tmob`.`idperson`) and (`tmob`.`mobil` = 1)))) order by `person`.`fname`,`person`.`lname` */;
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

-- Dump completed on 2021-05-31 19:16:20
