-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: datadinner
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (9,'Burger'),(8,'Carne'),(7,'Cervezas'),(10,'Ensaladas'),(5,'Para Picar'),(2,'Platos Principales'),(3,'Postres'),(4,'Refrescos');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cierre_caja`
--

DROP TABLE IF EXISTS `cierre_caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cierre_caja` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total_pedidos` int NOT NULL,
  `total_facturado` decimal(38,2) NOT NULL,
  `resumen_productos` json DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fecha` (`fecha`),
  KEY `idx_cierre_fecha` (`fecha`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_caja`
--

LOCK TABLES `cierre_caja` WRITE;
/*!40000 ALTER TABLE `cierre_caja` DISABLE KEYS */;
INSERT INTO `cierre_caja` VALUES (1,'2025-09-18',3,55.25,'[{\"total\": 7.50, \"cantidad\": 3, \"producto\": \"Coca Cola\"}, {\"total\": 18.00, \"cantidad\": 2, \"producto\": \"Hamburguesa\"}, {\"total\": 5.50, \"cantidad\": 1, \"producto\": \"Ensalada\"}, {\"total\": 24.25, \"cantidad\": 1, \"producto\": \"Pizza\"}]');
/*!40000 ALTER TABLE `cierre_caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cierre_caja_detalle`
--

DROP TABLE IF EXISTS `cierre_caja_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cierre_caja_detalle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cierre_caja_id` int NOT NULL,
  `producto_id` int NOT NULL,
  `cantidad` int NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cierre_caja_id` (`cierre_caja_id`),
  KEY `producto_id` (`producto_id`),
  CONSTRAINT `cierre_caja_detalle_ibfk_1` FOREIGN KEY (`cierre_caja_id`) REFERENCES `cierre_caja` (`id`),
  CONSTRAINT `cierre_caja_detalle_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cierre_caja_detalle`
--

LOCK TABLES `cierre_caja_detalle` WRITE;
/*!40000 ALTER TABLE `cierre_caja_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `cierre_caja_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cerrada` bit(1) NOT NULL,
  `fecha` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total` decimal(38,2) NOT NULL,
  `pedido_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_factura_pedido` (`pedido_id`),
  CONSTRAINT `FK66aqtgi8w5t46g7cytr2259w4` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (2,_binary '\0','2025-10-20 15:41:53',7.00,6);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesas`
--

DROP TABLE IF EXISTS `mesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mesas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` int NOT NULL,
  `estado` enum('LIBRE','OCUPADA','RESERVADA','CUENTA') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'LIBRE',
  `capacidad` int NOT NULL,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numero` (`numero`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesas`
--

LOCK TABLES `mesas` WRITE;
/*!40000 ALTER TABLE `mesas` DISABLE KEYS */;
INSERT INTO `mesas` VALUES (1,1,'OCUPADA',4,50,100),(2,2,'OCUPADA',8,260,100),(3,3,'OCUPADA',4,470,100),(4,4,'LIBRE',4,680,100),(5,5,'LIBRE',4,890,100),(6,6,'LIBRE',4,50,300),(7,7,'LIBRE',4,260,300),(8,8,'LIBRE',4,470,300),(9,9,'RESERVADA',4,680,300),(10,10,'CUENTA',4,890,300);
/*!40000 ALTER TABLE `mesas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_productos`
--

DROP TABLE IF EXISTS `pedido_productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pedido_id` int NOT NULL,
  `producto_id` int NOT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `producto_id` (`producto_id`),
  KEY `idx_pedido_producto` (`pedido_id`,`producto_id`),
  CONSTRAINT `pedido_productos_ibfk_1` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `pedido_productos_ibfk_2` FOREIGN KEY (`producto_id`) REFERENCES `productos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_productos`
--

LOCK TABLES `pedido_productos` WRITE;
/*!40000 ALTER TABLE `pedido_productos` DISABLE KEYS */;
INSERT INTO `pedido_productos` VALUES (8,6,6,2,0.00),(13,8,6,3,3.50),(15,9,6,2,3.50),(16,9,5,1,6.00),(17,10,11,1,2.50),(18,10,9,1,3.00),(19,10,8,1,2.50),(20,11,11,1,2.50),(21,11,9,1,3.00),(22,11,8,3,2.50);
/*!40000 ALTER TABLE `pedido_productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_hora` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` enum('EN_PREPARACION','SERVIDO','CERRADO','PENDIENTE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'EN_PREPARACION',
  `mesa_id` int NOT NULL,
  `usuario_id` int NOT NULL,
  `total` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mesa_id` (`mesa_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`mesa_id`) REFERENCES `mesas` (`id`) ON DELETE CASCADE,
  CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (3,'2025-09-18 09:19:51','EN_PREPARACION',1,4,0.00),(6,'2025-09-18 09:22:39','SERVIDO',2,5,0.00),(8,'2025-12-05 10:07:23','PENDIENTE',2,2,0.00),(9,'2025-12-05 10:20:00','PENDIENTE',2,2,13.00),(10,'2025-12-12 08:09:23','PENDIENTE',3,2,8.00),(11,'2025-12-12 08:14:38','PENDIENTE',3,2,13.00);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `precio` decimal(38,2) NOT NULL,
  `categoria_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_id` (`categoria_id`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`categoria_id`) REFERENCES `categorias` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (5,'CheeseBurger',8.50,2),(6,'Tarta de queso',3.50,3),(7,'Patatas Bravas',4.50,5),(8,'Doble',2.50,7),(9,'Tercio',3.00,7),(11,'CocoColaZero',2.50,4),(12,'Clásica Sencilla',7.50,9),(13,'Doble Queso XL',12.00,9),(14,'Vegana de Lentejas',9.50,9),(15,'Bacon BBQ',10.50,9),(16,'Mexicana Picante',11.50,9),(17,'Pollo Crispy',8.00,9),(18,'Gourmet Trufa',14.50,9),(19,'Smash',10.50,9),(20,'Doble Smash',13.50,9),(21,'Burger del Mes',13.00,9),(22,'Entrecot (300g)',18.00,8),(23,'Solomillo de Ternera',24.00,8),(24,'Costillas BBQ',15.00,8),(25,'Brocheta de Pollo',10.00,8),(26,'Chuletas de Cordero',20.00,8),(27,'Secreto Ibérico',17.50,8),(28,'Chuletón (1kg)',35.00,8),(29,'Steak Tartar',22.00,8),(30,'Albóndigas Caseras',9.00,8),(31,'Lomo Bajo',16.50,8),(32,'Cerveza Rubia Nacional',3.00,7),(33,'Cerveza Negra (Importación)',4.50,7),(34,'IPA Artesana',5.50,7),(35,'Pilsner Ligera',3.50,7),(36,'Cerveza de Trigo',4.00,7),(37,'Lager Sin Alcohol',3.00,7),(38,'Cerveza Tostada',3.80,7),(39,'Radler con Limón',3.20,7),(40,'Stout de Malta',5.00,7),(41,'Pack Degustación',12.00,7),(42,'Ensalada Caprese',7.50,10),(43,'Ensalada de Pollo y Aguacate',10.00,10),(44,'Ensalada de Quinoa',9.50,10),(45,'Ensaladilla Rusa',6.00,10),(46,'Ensalada Griega',8.50,10),(47,'Ensalada de Pasta',7.00,10),(48,'Ensalada de Atún',8.00,10),(49,'Ensalada de Cabra y Frutos Secos',11.00,10),(50,'Ensalada de Marisco',12.00,10),(51,'Ensalada de la Huerta',6.50,10),(52,'Croquetas de Jamón',6.50,5),(53,'Aros de Cebolla',4.50,5),(54,'Nachos con Guacamole',8.00,5),(55,'Patatas Bravas XL',5.50,5),(56,'Queso Curado',9.00,5),(57,'Tabla de Embutidos',15.00,5),(58,'Hummus con crudités',7.00,5),(59,'Calamares Rebozados',10.50,5),(60,'Alitas de Pollo',7.50,5),(61,'Aceitunas Aliñadas',3.00,5),(62,'Lasaña de Carne',9.50,2),(63,'Salmón a la Plancha',13.00,2),(64,'Arroz con Verduras',8.50,2),(65,'Bacalao al Pil Pil',16.00,2),(66,'Wok de Pollo y Fideos',11.00,2),(67,'Pizza Margarita Grande',10.50,2),(68,'Canelones de Espinacas',9.00,2),(69,'Revuelto de Setas',10.00,2),(70,'Pollo al Curry',12.50,2),(71,'Taco Vegetariano (3 und.)',8.00,2),(72,'Flan Casero',3.50,3),(73,'Brownie con Helado',5.00,3),(74,'Mousse de Limón',4.00,3),(75,'Tiramisú',5.50,3),(76,'Fruta de Temporada',3.00,3),(77,'Helado de Vainilla',3.50,3),(78,'Crema Catalana',4.50,3),(79,'Coulant de Chocolate',6.00,3),(80,'Batido de Fresa',4.00,3),(81,'Torrijas (Especial)',5.50,3),(82,'Coca-Cola (Lata)',2.50,4),(83,'Fanta Naranja',2.50,4),(84,'Agua con Gas (Botella)',2.00,4),(85,'Zumo de Melocotón',3.00,4),(86,'Tónica',2.80,4),(87,'Limonada Casera',3.50,4),(88,'Té Frío Limón',2.50,4),(89,'Refresco de Cola Zero',2.50,4),(90,'Zumo de Piña',3.00,4),(91,'Agua Grande (1L)',3.50,4);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_pedido`
--

DROP TABLE IF EXISTS `ticket_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket_pedido` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pedido_id` int NOT NULL,
  `fecha_hora` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total` decimal(38,2) NOT NULL,
  `estado` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pedido` (`pedido_id`),
  CONSTRAINT `fk_pedido` FOREIGN KEY (`pedido_id`) REFERENCES `pedidos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_pedido`
--

LOCK TABLES `ticket_pedido` WRITE;
/*!40000 ALTER TABLE `ticket_pedido` DISABLE KEYS */;
INSERT INTO `ticket_pedido` VALUES (2,3,'2025-09-18 10:04:48',15.50,'PENDIENTE');
/*!40000 ALTER TABLE `ticket_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pass` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rol` enum('ADMIN','CAMARERO') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_usuario` (`nombre_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (2,'Pepe','02','CAMARERO'),(4,'Juan','03','CAMARERO'),(5,'María','04','CAMARERO'),(6,'Adrian','10','ADMIN'),(7,'ElJefe','0008','ADMIN');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-12 10:44:11
