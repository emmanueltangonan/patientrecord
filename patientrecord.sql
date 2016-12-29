-- MySQL dump 10.16  Distrib 10.1.19-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.19-MariaDB

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
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `pid` int(10) unsigned NOT NULL,
  `doc_id` int(11) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `dob` date NOT NULL,
  `address` varchar(30) NOT NULL,
  `diagnosis` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `doc_id` (`doc_id`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`doc_id`) REFERENCES `physician` (`doc_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (11,1,'Maktol','Boy','m','1990-02-02','Cavite',''),(16,1,'Baboy','Boy','m','1998-05-05','Tondo',''),(17,1,'Malakas','Magand','m','1988-06-06','QC',''),(19,1,'asfs','dfsa','m','1881-05-06','ads',''),(24,1,'Hello','World','m','0111-11-11','Bulacan','CVD'),(26,1,'Hey','Sexy','f','1993-02-21','Bulacan','dasfsad'),(28,1,'Java','MAn','m','1992-02-02','Tokyo','SSSSSSs'),(29,1,'Darwin','Charles','f','1876-03-31','Manila','Baliw'),(30,2,'sadas','asda','m','4234-12-21','sasfsd',''),(31,1,'Blob','bob','m','1990-02-21','Patikul, Sulu',''),(32,1,'Boom','Baby','m','1990-11-01','Basilan',''),(33,1,'sdafsad','sdfas','m','1997-11-01','sdfsgdsd',''),(35,1,'asdfasd','dsfasd','m','0033-03-31','dsgsdg',''),(36,1,'dsfgsd','sdfgfsd','m','0002-02-22','dfgdsf',''),(37,1,'dfsgs','dfgsd','m','0444-04-04','fddfbx',''),(38,2,'DelaCruz','Allan','m','1998-02-22','Aparri, Cagayan','');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_workups`
--

DROP TABLE IF EXISTS `patient_workups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_workups` (
  `pid` int(10) unsigned NOT NULL,
  `wid` int(10) unsigned NOT NULL,
  `frequency` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pid`,`wid`),
  KEY `pw_ibfk_2` (`wid`),
  CONSTRAINT `pw_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `patient` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_workups`
--

LOCK TABLES `patient_workups` WRITE;
/*!40000 ALTER TABLE `patient_workups` DISABLE KEYS */;
INSERT INTO `patient_workups` VALUES (16,1,6),(16,3,2),(16,4,1),(16,5,1),(16,6,1),(16,7,1),(16,9,4),(16,10,1),(16,11,6),(17,7,1),(28,1,1),(28,2,1),(28,3,1),(28,4,1),(29,2,3),(29,4,4),(30,2,5),(30,4,5),(32,4,3),(32,6,4),(32,9,2),(37,1,1),(37,2,1),(37,3,1),(37,4,1),(37,5,1),(37,6,1),(37,7,1),(37,8,1),(37,9,1),(37,10,1),(37,11,1),(38,1,1),(38,6,3),(38,7,5),(38,9,1);
/*!40000 ALTER TABLE `patient_workups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `physician`
--

DROP TABLE IF EXISTS `physician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `physician` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `department` varchar(20) NOT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `physician`
--

LOCK TABLES `physician` WRITE;
/*!40000 ALTER TABLE `physician` DISABLE KEYS */;
INSERT INTO `physician` VALUES (1,'pepe','1234','Jose','Rizal','Neurology'),(2,'hayden','1234','Hayden','Kho','Dermatology');
/*!40000 ALTER TABLE `physician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workup`
--

DROP TABLE IF EXISTS `workup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workup` (
  `wid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workup`
--

LOCK TABLES `workup` WRITE;
/*!40000 ALTER TABLE `workup` DISABLE KEYS */;
INSERT INTO `workup` VALUES (1,'X-ray','Diagnostic x rays are useful in detecting abnormalities within the body. They are a painless, non-invasive way to help diagnose problems such as broken bones, tumors, dental decay, and the presence of foreign bodies.',500.5),(2,'CT Scan','A computerized tomography (CT) scan combines a series of X-ray images taken from different angles and uses computer processing to create cross-sectional images, or slices, of the bones, blood vessels and soft tissues inside your body. CT scan images provide more detailed information than plain X-rays do.',2100.45),(3,'Complete Blood Count','A complete blood count (CBC) is a blood test used to evaluate your overall health and detect a wide range of disorders, including anemia, infection and leukemia. A complete blood count test measures several components and features of your blood, including: red blood cells, which carry oxygen; white blood cells, which fight infection; hemoglobin, the oxygen-carrying protein in red blood cells; hematocrit, the proportion of red blood cells to the fluid component, or plasma, in your blood;\r\nplatelets, which help with blood clotting',400),(4,'Blood Electrolytes','Analysis of blood chemistry can provide important information about the function of the kidneys and other organs. This common panel of blood tests measures levels of important electrolytes and other chemicals.',700.15),(5,'Urinalysis','This standard test is usually performed on admission to a hospital or as part of an annual physical. It may also be done if you have symptoms relating to abdominal pain or blood in the urine. One to two ounces of urine are required.',400.35),(6,'Urine Culture','If you experience symptoms of a urinary tract infection (UTI), your doctor may test a sample of your urine to make the diagnosis. A urine sample is required.',1100.78),(7,'Semen Analysis','Can be used to learn about the health of your reproductive organs, or after a vasectomy to determine if the operation was successful. A semen sample is collected in a sterile, wide-mouth container provided by the lab.',500.25),(8,'Pap Smear','Should be performed annually for women who are over the age of 18 and/or sexually active, to screen for cervical cancer and certain vaginal or uterine infections. Cells are removed from the cervical area for testing.',700),(9,'HIV Antibody','This test to determine if you are infected with HIV should be performed three to six months after you think you may have been exposed to the virus.',1799.99),(10,'Stool Culture','Used to determine whether you have pathogenic bacteria in your gastrointestinal tract. Test requires a fresh stool sample or one that has been placed in a specialized collection container.',450),(11,'Lipid Profile','This group of tests can determine risk of coronary heart disease, and may be a good indicator of whether someone is likely to have a heart attack or stroke, as caused by blockage of blood vessels.',350.33);
/*!40000 ALTER TABLE `workup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-29 16:53:56
