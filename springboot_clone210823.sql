CREATE DATABASE  IF NOT EXISTS `springboot_clone` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `springboot_clone`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: springboot_clone
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (2,'admin','2021-06-14 12:32:44','admin','2021-06-14 12:32:44','the-gioi','Thế giới'),(5,'admin','2021-06-14 12:41:25','admin','2021-06-14 12:41:25','giai-tri','Giải trí'),(8,'admin','2021-06-14 12:42:07','admin','2021-06-14 12:42:07','khoa-hoc','Khoa học'),(9,'admin','2021-06-24 00:41:40','admin','2021-06-24 00:41:40','xa-hoi','Xã hội'),(12,'admin','2023-04-17 20:04:16','admin','2023-04-17 20:04:16','mobile','Mobile'),(13,'admin','2023-04-17 20:05:18','admin','2023-04-17 20:05:18','thu-thuat','Thủ thuật'),(14,NULL,NULL,NULL,NULL,'gracate1','gracatet1');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `newid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_new_comment_idx` (`newid`),
  CONSTRAINT `FK_new_comment` FOREIGN KEY (`newid`) REFERENCES `new` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (34,'user01','2023-04-09 15:09:46','user01','2023-04-09 15:09:46','comment1',5),(35,'user01','2023-04-09 15:09:49','user01','2023-04-09 15:09:49','comment2',5),(36,'user01','2023-04-09 15:09:51','user01','2023-04-09 15:09:51','comment3',5),(39,'admin','2023-04-11 00:09:51','admin','2023-04-11 00:09:51','comment01 by Admin',5);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new`
--

DROP TABLE IF EXISTS `new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `content` text,
  `shortdescription` text,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_n64e68925ltftar1nwdaqhaa` (`category_id`),
  CONSTRAINT `FK_n64e68925ltftar1nwdaqhaa` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new`
--

LOCK TABLES `new` WRITE;
/*!40000 ALTER TABLE `new` DISABLE KEYS */;
INSERT INTO `new` VALUES (5,'admin','2021-06-14 12:50:47','editor01','2023-03-07 14:11:59','<p>L&otilde;i trong của Tr&aacute;i Đất ph&aacute;t triển nhanh hơn ở b&ecirc;n dưới biển Banda của Indonesia v&agrave; chậm hơn ở ph&iacute;a dưới Brazil.</p>\n\n<p>Hiện tượng được c&aacute;c nh&agrave; khoa học ph&aacute;t hiện khi nghi&ecirc;n cứu s&oacute;ng địa chấn (rung động dưới l&ograve;ng đất do động đất g&acirc;y ra) truyền qua l&otilde;i trong của Tr&aacute;i Đất, v&igrave; một số l&yacute; do, s&oacute;ng di chuyển qua l&otilde;i giữa cực bắc v&agrave; cực nam nhanh hơn nhiều so với di chuyển qua x&iacute;ch đạo.</p>\n\n<p>Giới nghi&ecirc;n cứu đ&atilde; biết về sự ch&ecirc;nh lệch mang t&ecirc;n dị hướng địa chấn trong nhiều thập kỷ, nhưng kh&ocirc;ng thể t&igrave;m ra c&aacute;ch giải th&iacute;ch ph&ugrave; hợp với dữ liệu sẵn c&oacute;. Giờ đ&acirc;y, sử dụng m&ocirc; phỏng m&aacute;y t&iacute;nh về sự ph&aacute;t triển của l&otilde;i Tr&aacute;i Đất trong h&agrave;ng tỷ năm qua, nghi&ecirc;n cứu mới c&ocirc;ng bố h&ocirc;m 3/6 tr&ecirc;n tạp ch&iacute; <em>Nature Geoscience</em> cung cấp c&acirc;u trả lời hợp l&yacute;.</p>\n\n<p>Mỗi năm, l&otilde;i trong của Tr&aacute;i Đất ph&aacute;t triển theo chiều hướng kh&ocirc;ng đều với tinh thể sắt h&igrave;nh th&agrave;nh nhanh hơn ở nửa ph&iacute;a đ&ocirc;ng của l&otilde;i so với nửa ph&iacute;a t&acirc;y. Điều n&agrave;y c&oacute; nghĩa l&otilde;i ngo&agrave;i lấy nhiều nhiệt từ nửa đ&ocirc;ng (b&ecirc;n dưới Indonesia) hơn nửa t&acirc;y (b&ecirc;n dưới Brazil).</p>\n\n<p>&quot;Để hiển thị sự ph&aacute;t triển kh&ocirc;ng đều ở l&otilde;i, h&atilde;y h&igrave;nh dung một gốc c&acirc;y với những v&ograve;ng c&acirc;y tản dần ra từ điểm trung t&acirc;m, nhưng t&acirc;m của v&ograve;ng c&acirc;y lệch với t&acirc;m của c&aacute;i c&acirc;y, v&igrave; vậy c&aacute;c v&ograve;ng c&aacute;ch xa nhau ở nửa ph&iacute;a đ&ocirc;ng v&agrave; gần nhau hơn ở nửa ph&iacute;a t&acirc;y. Mặt cắt của l&otilde;i trong Tr&aacute;i Đất c&oacute; thể cũng tương tự như vậy&quot;, Frost giải th&iacute;ch.</p>\n\n<p>Trung b&igrave;nh, b&aacute;n k&iacute;nh l&otilde;i trong tăng th&ecirc;m khoảng một milimet mỗi năm. Trọng lực b&ugrave; đắp cho sự ph&aacute;t triển kh&ocirc;ng đều ở ph&iacute;a t&acirc;y bằng c&aacute;ch đẩy những tinh thể mới về hướng t&acirc;y. Tại đ&oacute;, c&aacute;c tinh thể dồn tụ lại th&agrave;nh cấu tr&uacute;c dạng lưới trải d&agrave;i dọc theo trục bắc - nam của l&otilde;i. Cấu tr&uacute;c tinh thể nằm song song với cực Tr&aacute;i Đất, cho ph&eacute;p s&oacute;ng địa chấn truyền nhanh hơn theo hướng đ&oacute;.</p>\n\n<p>C&aacute;c nh&agrave; nghi&ecirc;n cứu cho biết rất kh&oacute; t&igrave;m ra nguy&ecirc;n nh&acirc;n dẫn tới sự ph&aacute;t triển mất c&acirc;n bằng ở l&otilde;i trong m&agrave; kh&ocirc;ng xem x&eacute;t những lớp kh&aacute;c của Tr&aacute;i Đất. Mỗi lớp được kiểm so&aacute;t bởi lớp ph&iacute;a tr&ecirc;n v&agrave; ảnh hưởng tới lớp b&ecirc;n dưới. L&otilde;i trong chậm r&atilde;i cứng lại từ l&otilde;i lỏng b&ecirc;n ngo&agrave;i, giống như quả cầu tuyết th&ecirc;m dần từng lớp. L&otilde;i ngo&agrave;i lại nguội dần nhờ lớp phủ b&ecirc;n tr&ecirc;n.</p>\n\n<p>Mảng kiến tạo cũng c&oacute; thể l&agrave; một phần nguy&ecirc;n nh&acirc;n. Khi mảng kiến tạo lạnh ch&igrave;m xuống s&acirc;u b&ecirc;n dưới bề mặt Tr&aacute;i Đất ở đới h&uacute;t ch&igrave;m, ch&uacute;ng l&agrave;m nguội lớp phủ b&ecirc;n dưới. Tuy nhi&ecirc;n, liệu sự nguội đi của lớp phủ c&oacute; t&aacute;c động tới l&otilde;i trong hay kh&ocirc;ng vẫn l&agrave; chủ đề g&acirc;y tranh c&atilde;i.</p>\n\n<p>Nh&oacute;m nghi&ecirc;n cứu cũng kh&ocirc;ng biết chắc hiện tượng mất nhiệt kh&ocirc;ng đều ở lớp l&otilde;i c&oacute; ảnh hưởng tới từ trường Tr&aacute;i Đất hay kh&ocirc;ng. Từ trường hiện nay tồn tại nhờ sự dịch chuyển của sắt lỏng ở l&otilde;i ngo&agrave;i. Qu&aacute; tr&igrave;nh n&agrave;y chịu ảnh hưởng từ sự mất nhiệt ở l&otilde;i trong. Nếu l&otilde;i trong mất nhiều nhiệt ở nửa ph&iacute;a đ&ocirc;ng hơn ph&iacute;a t&acirc;y, l&otilde;i ngo&agrave;i cũng c&oacute; thể dịch chuyển nhanh hơn ở ph&iacute;a đ&ocirc;ng.</p>\n','Lõi trong của Trái Đất phát triển nhanh hơn ở bên dưới biển Banda của Indonesia và chậm hơn ở phía dưới Brazil.','loi trai dat dang meo dan-1626803126801.jpg','Lõi Trái Đất đang méo dần phát triển nhanh hơn ở bên dưới biển Thái Bình Dương',1,8,NULL),(32,'admin','2023-04-17 20:10:39','admin','2023-04-17 20:18:55','<p>C&aacute;ch xử l&yacute; khi điện thoại bị rơi v&agrave;o nước đơn giản dưới đ&acirc;y sẽ gi&uacute;p điện thoại của bạn kh&ocirc;ng bị hư hỏng nặng.</p><p>Điện thoại bị rơi xuống nước l&agrave; t&igrave;nh trạng kh&ocirc;ng ai muốn gặp phải. Nếu kh&ocirc;ng may điện thoại của bạn bị rơi xuống nước h&atilde;y &aacute;p dụng ngay c&aacute;ch xử l&yacute; đơn giản dưới đ&acirc;y.</p><p>C&aacute;ch xử l&yacute; khi điện thoại bị rơi v&agrave;o nước</p><p>Đ&ocirc;i khi trong qu&aacute; tr&igrave;nh sử dụng điện thoại, bạn v&ocirc; t&igrave;nh đ&aacute;nh rơi điện thoại xuống nước. Nếu kh&ocirc;ng may gặp phải t&igrave;nh trạng n&agrave;y, bạn h&atilde;y b&igrave;nh tĩnh v&agrave; xử l&yacute; theo c&aacute;c bước dưới đ&acirc;y:</p><p><em><strong>Lấy điện thoại ra khỏi nước v&agrave; tắt nguồn điện thoại </strong></em></p><p>Khi điện thoại bị rơi v&agrave;o nước, điều đầu ti&ecirc;n bạn cần l&agrave;m l&agrave; lấy điện thoại ra khỏi nước ngay lập tức. Tuy nhi&ecirc;n, bạn cần lưu &yacute; để điện thoại theo chiều thẳng đứng, hướng chiều c&oacute; c&aacute;c cổng kết nối xuống ph&iacute;a dưới để nước c&oacute; thể tho&aacute;t ra ngo&agrave;i, tr&aacute;nh t&igrave;nh trạng nước chảy ngược v&agrave;o b&ecirc;n trong.</p><p>Sau đ&oacute;, bạn cần tắt nguồn. Việc l&agrave;m n&agrave;y sẽ tr&aacute;nh được trường hợp nước len lỏi v&agrave;o những bo mạch g&acirc;y đứt mạch điện nếu như điện thoại đang hoạt động. Trường hợp bạn đang sạc điện thoại th&igrave; h&atilde;y b&igrave;nh tĩnh r&uacute;t sạc ra trước, rồi lấy điện thoại ra sau để tr&aacute;nh bị điện giật.</p><p><strong><em>Th&aacute;o rời điện thoại </em></strong></p><p>Th&aacute;o nắp lưng v&agrave; lấy pin ra khỏi m&aacute;y ngay lập tức, d&ugrave;ng khăn mềm kh&ocirc; để lau pin, sau đ&oacute; để ở nơi tho&aacute;ng m&aacute;t cho pin nhanh kh&ocirc; (nếu m&aacute;y pin rời).</p><p>Th&aacute;o SIM, thẻ nhớ ra khỏi m&aacute;y. Do thẻ nhớ v&agrave; SIM nhỏ n&ecirc;n việc lau kh&ocirc; sẽ dễ d&agrave;ng hơn.</p><p><strong><em>Lau kh&ocirc; b&ecirc;n ngo&agrave;i điện thoại </em></strong></p><p>Đối với nước b&igrave;nh thường: Bạn sử dụng khăn vải mềm lau kh&ocirc; sạch to&agrave;n bộ bề mặt điện thoại. Sau đ&oacute; h&atilde;y sử dụng tăm b&ocirc;ng để lau cổng sạc, cổng tai nghe, tất cả c&aacute;c cổng kết nối với phụ kiện.</p><p>Đối với với chất lỏng kh&ocirc;ng phải nước thường (nước ngọt, nước muối,&hellip;): Những loại nước n&agrave;y khả năng g&acirc;y b&agrave;o m&ograve;n điện thoại, v&igrave; vậy bạn cần nhanh ch&oacute;ng lau bằng điện thoại bằng khăn ẩm để loại bỏ nước b&aacute;m v&agrave;o v&agrave; giảm bớt khả năng b&agrave;o m&ograve;n g&acirc;y ra. Sau đ&oacute;, bạn h&atilde;y sử dụng khăn kh&ocirc;, mềm để lau ch&ugrave;i lại điện thoại.</p><p><strong><em>H&uacute;t ẩm cho điện thoại </em></strong></p><p>Sau khi đ&atilde; lau kh&ocirc; điện thoại v&agrave; c&aacute;c bộ phận kh&aacute;c, bạn h&atilde;y h&uacute;t ẩm cho điện thoại. C&oacute; nhiều c&aacute;ch h&uacute;t ẩm. Thứ nhất, bạn c&oacute; thể sử dụng gạo để h&uacute;t ẩm bằng c&aacute;ch phủ gạo l&ecirc;n mọi bề mặt của điện thoại từ 1-2 ng&agrave;y. Ch&uacute; &yacute; kh&ocirc;ng để c&aacute;c hạt gạo nhỏ bị mắc kẹt v&agrave;o c&aacute;c cổng kết nối</p><p>Thứ hai, đựng điện thoại v&agrave;o g&oacute;i h&uacute;t ẩm silica gel từ 1-2 ng&agrave;y. Thứ ba, d&ugrave;ng m&aacute;y sấy hoặc m&aacute;y h&uacute;t bụi để h&uacute;t bớt nước ở trong từng r&atilde;nh kẽ của điện thoại.</p><p>Lưu &yacute; bạn kh&ocirc;ng n&ecirc;n phơi điện thoại ra ngo&agrave;i trời nắng, v&igrave; ở nhiệt độ cao, sẽ l&agrave;m hỏng c&aacute;c linh kiện b&ecirc;n trong điện thoại</p><p><strong><em>Ki&ecirc;n nhẫn chờ đợi </em></strong></p><p>Sau khi thực hiện giai đoạn l&agrave;m kh&ocirc;, bạn n&ecirc;n ki&ecirc;n nhẫn chờ đợi v&agrave; sử dụng một điện thoại kh&aacute;c để thay thế. Nếu bạn để điện thoại v&agrave;o trong bao gạo, bạn n&ecirc;n chờ từ 1 đến 2 ng&agrave;y.</p><p><strong><em>Khởi động lại m&aacute;y v&agrave; sử dụng thử </em></strong></p><p>Sau khi thực hiện xong c&aacute;c bước tr&ecirc;n, bạn lắp pin (nếu pin rời) hoặc cắm sạc rồi một l&uacute;c rồi bật n&uacute;t nguồn xem m&aacute;y c&oacute; hoạt động b&igrave;nh thường kh&ocirc;ng.</p><p><strong><em>Lưu &yacute; khi sử dụng tăm b&ocirc;ng, khăn b&ocirc;ng lau điện thoại </em></strong></p><p>Việc l&agrave;m kh&ocirc; điện thoại v&agrave; c&aacute;c cổng kết nối bằng khăn b&ocirc;ng v&agrave; tăm b&ocirc;ng được nhiều người sử dụng. Tuy nhi&ecirc;n, c&aacute;c mảnh b&ocirc;ng vụn khi bị thấm nước c&oacute; thể bị mắc kẹt v&agrave;o trong điện thoại g&acirc;y hư hại c&aacute;c bộ phận b&ecirc;n trong. V&igrave; thế, bạn hết sức thận trọng khi xử l&yacute; t&igrave;nh huống n&agrave;y.</p><p>Tr&ecirc;n đ&acirc;y l&agrave; c&aacute;ch xử l&yacute; khi điện thoại bị rơi v&agrave;o nước. Nếu điện thoại của bạn kh&ocirc;ng may bị rơi xuống nước h&atilde;y b&igrave;nh tĩnh v&agrave; xử l&yacute; theo c&aacute;c bước hướng dẫn nh&eacute;.</p>','Cách xử lý khi điện thoại bị rơi vào nước đơn giản dưới đây sẽ giúp điện thoại của bạn không bị hư hỏng nặng. Điện thoại bị rơi xuống nước là tình trạng không ai muốn gặp phải','ipiccyimage-fixed-1681737535226.jpg','Cách xử lý khi điện thoại bị rơi vào nước',1,13,''),(33,'admin','2023-04-17 20:25:51','admin','2023-04-17 20:25:51','<p>Đầu ti&ecirc;n l&agrave; JerryRigEverything, YouTuber t&ecirc;n thật l&agrave; Zack Nelson hiện c&oacute; 8 triệu người theo d&otilde;i v&agrave; cũng l&agrave; người đ&atilde; t&igrave;m hiểu s&acirc;u về phần cứng của v&ocirc; số điện thoại:</p><p><em>&quot;Do mỗi năm Apple đều tập trung v&agrave;o việc tạo ra một d&ograve;ng điện thoại mới - n&ecirc;n kh&aacute; dễ hiểu rằng mọi phi&ecirc;n bản điện thoại của họ đều c&oacute; khả năng tương t&aacute;c liền mạch với những chiếc iPhone kh&aacute;c trong hệ sinh th&aacute;i của h&atilde;ng.</em></p><p><em>Đ&oacute; cũng l&agrave; l&yacute; do tại sao việc gửi tệp từ thiết bị Apple n&agrave;y sang thiết bị Apple kh&aacute;c th&ocirc;ng qua AirDrop lại dễ d&agrave;ng như vậy.</em></p><p><em>Một kỳ t&iacute;ch đ&aacute;ng n&oacute;i kh&aacute;c l&agrave; mạng lưới AirTag to&agrave;n cầu</em><em>. Mọi chiếc iPhone đang hoạt động tr&ecirc;n thế giới đều c&oacute; thể được li&ecirc;n kết với nhau để tạo th&agrave;nh một &#39;lưới&#39; gi&uacute;p theo d&otilde;i những chiếc AirTag.</em></p><p><em>C&oacute; lẽ việc cho tới nay chỉ c&oacute; 1 c&ocirc;ng ty duy nhất l&agrave;m được điều đ&oacute; l&agrave; đ&aacute;ng quan ngại - l&yacute; do l&agrave; nhiều nh&agrave; sản xuất điện thoại Android khiến ch&uacute;ng kh&aacute;c biệt.</em></p><p><em>V&agrave; c&oacute; thể thấy mạng lưới AirTag l&agrave; t&iacute;nh năng m&agrave; người d&ugrave;ng Android chỉ c&oacute; thể mơ ước&quot;.</em></p><p>Bi&ecirc;n tập vi&ecirc;n người Anh Jason England, người nổi tiếng tr&ecirc;n k&ecirc;nh YouTube của trang tin c&ocirc;ng nghệ Laptop Mag nhấn mạnh:</p><p><em>&quot;Chất lượng video vượt trội v&agrave; khả năng chỉnh sửa dễ d&agrave;ng l&agrave; hai điều khiến t&ocirc;i gắn b&oacute; với điện thoại th&ocirc;ng minh của Apple.</em></p><p><em>C&ocirc;ng việc của t&ocirc;i dựa v&agrave;o phần mềm Final Cut Pro để chỉnh sửa v&agrave; trước đ&acirc;y t&ocirc;i sử dụng m&aacute;y ảnh Fujifilm XA-7 để quay phim, nhưng sau một v&agrave;i thử nghiệm, t&ocirc;i đ&atilde; chuyển sang iPhone 13 Pro.</em></p><p><em>N&oacute;i một c&aacute;ch đơn giản, chất lượng video từ chiếc iPhone l&agrave; đủ tốt cho video YouTube - v&agrave; việc chuyển v&agrave; chỉnh sửa dễ d&agrave;ng hơn rất nhiều&quot;.</em></p><p>Theo &ocirc;ng England, c&aacute;c thiết bị Android đơn giản l&agrave; kh&ocirc;ng đo lường được chất lượng video như iPhone v&agrave; nhờ c&oacute; Airdrop, việc chuyển video trở n&ecirc;n đơn giản.</p><p>Tr&ecirc;n hết, chiếc MacBook Pro được c&agrave;i đặt sẵn Final Cut Pro của &ocirc;ng lu&ocirc;n sẵn s&agrave;ng để xử l&yacute; c&aacute;c cảnh quay c&oacute; độ trung thực cao m&agrave; iPhone cung cấp.</p><p>Trợ l&yacute; Tổng bi&ecirc;n tập của Laptop Mag, Sean Riley th&igrave; nhấn mạnh:</p><p><em>&quot;Những người h&acirc;m mộ Android sẽ chế nhạo c&aacute;ch tiếp cận kiểu &#39;walled garden&#39; </em>(<em>khu vườn bị tường bao bọc) của Apple v&igrave; ph&aacute; vỡ một số quyền tự do m&agrave; bạn được hưởng tr&ecirc;n Android v&agrave; Windows, nhưng mặt kh&aacute;c, b&ecirc;n trong khu vườn đ&oacute; cũng kh&aacute; đẹp...</em></p><p><em>Tương tự như vậy, quyền kiểm so&aacute;t của Apple đối với c&aacute;c bản cập nhật phần mềm của họ l&agrave; kh&ocirc;ng ai s&aacute;nh kịp...</em></p><p><em>Đ&ocirc;i khi, bạn sẽ thấy c&aacute;c nh&agrave; sản xuất điện thoại Android tung ra c&aacute;c bản cập nhật phần mềm nhưng ch&uacute;ng lại kh&ocirc;ng khả dụng cho những người sử dụng một nh&agrave; mạng cụ thể. Đ&oacute; l&agrave; vấn đề bạn sẽ kh&ocirc;ng bao giờ gặp phải tr&ecirc;n iPhone&quot;.</em></p><p>MrMobile, một YouTuber c&oacute; t&ecirc;n thật l&agrave; Michael Fisher với hơn 1 triệu người follow cho rằng riPhone thường bị ch&ecirc; l&agrave; qu&aacute; cứng nhắc v&agrave; dễ đo&aacute;n định, nhưng điều đ&oacute; đi k&egrave;m với c&aacute;c t&iacute;nh năng vững chắc:</p><p><em>&quot;C&oacute; thể nhắc lại rằng đ&acirc;y l&agrave; lập luận về c&aacute;i gọi l&agrave; &#39;hệ sinh th&aacute;i&#39;. </em><em>Nếu bạn c&oacute; 1 chiếc iPhone, bạn c&oacute; thể chia sẻ tệp kh&ocirc;ng d&acirc;y với chiếc iPhone kh&aacute;c, MacBook v&agrave; iPad kh&aacute;c một c&aacute;ch nhanh ch&oacute;ng v&agrave; dễ d&agrave;ng bằng AirDrop.</em></p><p><em>Tương tự như vậy, x&aacute;c thực hai yếu tố rất đơn giản v&agrave; nhất qu&aacute;n tr&ecirc;n c&aacute;c thiết bị của Apple. </em><em>N&oacute; tự động giữ cho bạn an to&agrave;n hơn việc x&aacute;c thực dựa tr&ecirc;n SMS (tin nhắn) v&agrave; đ&oacute; l&agrave; trải nghiệm nhất qu&aacute;n hơn so với ứng dụng x&aacute;c thực của b&ecirc;n thứ ba.</em></p><p><em>V&agrave; với tư c&aacute;ch l&agrave; một người đ&atilde;ng tr&iacute;, đối với t&ocirc;i AirTags l&agrave; một m&oacute;n qu&agrave; trời cho&quot;.</em></p><p>L&agrave; một người y&ecirc;u th&iacute;ch điện thoại Android, MrMobile cũng mong muốn rằng ch&uacute;ng c&oacute; thể sớm &quot;copy&quot; c&ocirc;ng nghệ sạc MagSafe của Apple với những ưu điểm như c&oacute; thể gắn v&agrave;o bằng từ t&iacute;nh, sạc nhanh v&agrave; dễ d&agrave;ng hơn.</p><p>Zac Hall, Bi&ecirc;n tập vi&ecirc;n cao cấp của trang tin c&ocirc;ng nghệ 9to5Mac Zac Hall cho rằng việc iPhone gh&eacute;p nối liền mạch c&aacute;c thiết bị kh&aacute;c do Apple sản xuất đ&atilde; gi&uacute;p trải nghiệm trở n&ecirc;n tuyệt vời:</p><p><em>&quot;Tai nghe AirPods Pro v&agrave; AirPods Max của Apple rất đặc biệt.</em></p><p><em>Mặc d&ugrave; bạn c&oacute; thể gh&eacute;p nối AirPods với điện thoại Android qua Bluetooth - nhưng việc thiết lập v&agrave; chuyển đổi nhanh giữa iPhone, iPad v&agrave; Mac rất tiện lợi&quot;.</em></p>','Câu hỏi \"điều gì iPhone làm được mà Android thì không?\" đã được đặt ra cho các chuyên gia điện thoại.','photo-6-1681737950546.jpg','Bất ngờ những điều iPhone có thể làm còn điện thoạ',1,12,''),(34,'admin','2023-05-04 20:42:19','admin','2023-05-04 20:42:19','<p>Quy tr&igrave;nh sửa chữa lỗi xanh m&agrave;n h&igrave;nh của iPhone 13 Pro Max khiến nhiều người bất ngờ về mức độ đơn giản.</p>\n\n<p>Tại Việt Nam, iPhone 13 Pro v&agrave; iPhone 13 Pro Max l&agrave; d&ograve;ng m&aacute;y khiến nhiều người &aacute;m ảnh. C&oacute; xuất ph&aacute;t điểm thuận lợi với mức doanh số cao nhờ những n&acirc;ng cấp gi&aacute; trị về m&agrave;n h&igrave;nh, thời lượng pin v&agrave; camera, tuy nhi&ecirc;n danh tiếng của d&ograve;ng m&aacute;y n&agrave;y bị ph&aacute; vỡ sau b&ecirc; bối về lỗi m&agrave;n h&igrave;nh.</p>\n\n<p>Cụ thể, sau một thời gian sử dụng, một tỷ lệ người d&ugrave;ng iPhone 13 Pro Max cho biết chiếc m&aacute;y của họ gặp t&igrave;nh trạng trắng/xanh m&agrave;n h&igrave;nh, dẫn đến kh&ocirc;ng thể sử dụng được. T&igrave;nh trạng n&agrave;y xảy ra ngay cả với những chiếc m&aacute;y được sử dụng giữ g&igrave;n, kh&ocirc;ng va đập hay v&agrave;o nước.&nbsp;</p>\n\n<p>iPhone 13 Pro v&agrave; iPhone 13 Pro Max bị lỗi m&agrave;n h&igrave;nh</p>\n\n<p>Nếu m&aacute;y gặp lỗi trong hạn bảo h&agrave;nh, Apple sẽ sửa chữa v&agrave; thay thế miễn ph&iacute;. Tuy nhi&ecirc;n, nếu đen đủi gặp lỗi khi đ&atilde; qu&aacute; hạn bảo h&agrave;nh, giải ph&aacute;p duy nhất để khắc phục t&igrave;nh trạng n&agrave;y l&agrave; thay m&agrave;n h&igrave;nh kh&aacute;c với mức gi&aacute; tới 9 triệu đồng.</p>\n\n<p>Đến đ&acirc;y, kh&ocirc;ng &iacute;t người lựa chọn phương &aacute;n &quot;b&aacute;n x&aacute;c&quot; chiếc m&aacute;y bởi chi ph&iacute; sửa chữa qu&aacute; cao, bằng 1/2 gi&aacute; trị của một chiếc iPhone 13 Pro Max &quot;l&agrave;nh lặn&quot; ở thời điểm hiện tại. Quan trọng hơn, kh&ocirc;ng c&oacute; bất cứ một bảo đảm n&agrave;o rằng m&agrave;n h&igrave;nh thay thế sẽ kh&ocirc;ng gặp lỗi tương tự. Lo sợ m&aacute;y sẽ lại gặp lỗi, rất &iacute;t người d&ugrave;ng mặn m&agrave; với việc thay thế m&agrave;n h&igrave;nh.</p>\n\n<p>Tuy nhi&ecirc;n, gần đ&acirc;y tr&ecirc;n c&aacute;c hội nh&oacute;m chia sẻ kỹ thuật sửa chữa điện thoại đ&atilde; rộ l&ecirc;n phương ph&aacute;p khắc phục lỗi m&agrave;n h&igrave;nh của iPhone 13 Pro Max cực kỳ đơn giản. Đơn giản đến độ anh Tuấn, một kỹ thuật vi&ecirc;n tại Th&aacute;i B&igrave;nh, đ&atilde; chia sẻ đoạn video sửa lỗi m&agrave;n h&igrave;nh chỉ trong... 1 ph&uacute;t.</p>\n\n<p>Sửa lỗi m&agrave;n h&igrave;nh của iPhone 13 Pro Max chỉ trong 1 ph&uacute;t (Video: Tuấn Vũ)</p>\n\n<p>Quả thực, kỹ thuật sửa chữa lỗi m&agrave;n h&igrave;nh iPhone 13 Pro Max kh&ocirc;ng c&oacute; g&igrave; phức tạp, nếu kh&ocirc;ng muốn n&oacute;i l&agrave; đơn giản tới kh&ocirc;ng ngờ. Tất cả những g&igrave; kỹ thuật vi&ecirc;n cần l&agrave;m l&agrave; c&acirc;u một sợi d&acirc;y đồng để cấp &aacute;p từ một con IC n&agrave;y sang một con IC kh&aacute;c.&nbsp;</p>\n\n<p>Kỹ thuật c&acirc;u &aacute;p để sửa m&agrave;n h&igrave;nh iPhone 13 Pro Max (ảnh: Nh&oacute;m ICFix)</p>\n\n<p>Tuy nhi&ecirc;n, cũng cần khẳng định rằng phương ph&aacute;p sửa chữa n&oacute;i tr&ecirc;n vẫn c&ograve;n tồn tại nhiều rủi ro. Trrong đ&oacute;, vấn đề lớn nhất l&agrave; việc m&agrave;n h&igrave;nh của m&aacute;y sẽ kh&ocirc;ng thể hiển thị ở tần số qu&eacute;t 120Hz được nữa, m&agrave; thay v&agrave;o đ&oacute; chỉ l&agrave; 60Hz. Một số người cũng phản &aacute;nh t&igrave;nh trạng m&aacute;y hao pin hơn sau khi sửa chữa. Ngo&agrave;i ra, một số kỹ thuật vi&ecirc;n c&ograve;n ho&agrave;i nghi về độ bền của phương ph&aacute;p n&agrave;y.</p>\n\n<p>V&igrave; vậy ở thời điểm hiện tại, c&oacute; thể coi giải ph&aacute;p &quot;c&acirc;u d&acirc;y&quot; n&oacute;i tr&ecirc;n chỉ mang t&iacute;nh chất tạm thời v&agrave; sẽ tiếp tục được c&aacute;c kỹ thuật vi&ecirc;n nghi&ecirc;n cứu để đưa ra kết quả tối ưu hơn. Tuy vậy, đ&acirc;y l&agrave; một t&iacute;n hiệu tốt cho người d&ugrave;ng iPhone 13 Pro Max tại Việt Nam, bởi họ sẽ c&oacute; th&ecirc;m một phương &aacute;n sửa chữa với chi ph&iacute; hợp l&yacute; hơn, thay v&igrave; phải chịu mất một số tiền lớn như trước đ&acirc;y.</p>\n','Quy trình sửa chữa lỗi xanh màn hình của iPhone 13 Pro Max khiến nhiều người bất ngờ về mức độ đơn giản.\n','photo1667611026500-1683207738869.jpg','Lỗi phần cứng nghiêm trọng của iPhone được kỹ thuật viên Việt xử lý trong 1 phút',3,12,''),(35,NULL,NULL,NULL,NULL,'graphqlt1content','graphqlt1des',NULL,'graphqlt1',0,12,NULL);
/*!40000 ALTER TABLE `new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_rating`
--

DROP TABLE IF EXISTS `post_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_rating` (
  `post_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`post_id`,`user_id`),
  KEY `FK_postrating_user_idx` (`user_id`),
  CONSTRAINT `Fk_postrating_news` FOREIGN KEY (`post_id`) REFERENCES `new` (`id`),
  CONSTRAINT `FK_postrating_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_rating`
--

LOCK TABLES `post_rating` WRITE;
/*!40000 ALTER TABLE `post_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilege`
--

DROP TABLE IF EXISTS `privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `privilege` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `resourceid` bigint DEFAULT NULL,
  `url_api` varchar(255) DEFAULT NULL,
  `method_api` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_privilege_resource_idx` (`resourceid`),
  CONSTRAINT `FK_privilege_resource` FOREIGN KEY (`resourceid`) REFERENCES `resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilege`
--

LOCK TABLES `privilege` WRITE;
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
INSERT INTO `privilege` VALUES (2,'admin','2021-06-14 13:30:44','admin','2021-06-14 13:30:44','Xem danh mục','VIEW_CATEGORY',1,'/api/category','get','action',1),(4,'admin','2023-03-05 20:06:41','admin','2023-03-05 21:55:33','Thêm bài viết','CREATE_POST',NULL,NULL,NULL,NULL,NULL),(5,'admin','2023-03-05 20:07:19','admin','2023-03-08 13:51:10','Cập nhập mọi bài viết','UPDATE_POST',NULL,NULL,NULL,NULL,NULL),(7,'admin','2023-03-05 21:28:24','admin','2023-03-07 13:13:02','Xem mọi bài viết','VIEW_POST',NULL,NULL,NULL,NULL,NULL),(8,'admin','2023-03-05 21:45:15','admin','2023-03-07 13:13:12','Xóa mọi bài viết','DELETE_POST',NULL,NULL,NULL,NULL,NULL),(9,'admin','2023-03-06 13:56:26','admin','2023-03-06 13:56:26','Duyệt bài viết','PUBLISH_POST',NULL,NULL,NULL,NULL,NULL),(10,'admin','2023-03-07 10:39:30','admin','2023-03-07 10:39:30','Truy cập trang chủ admin','ACCESS_HOMEADMIN',NULL,NULL,NULL,NULL,NULL),(11,'admin','2023-03-07 13:13:59','admin','2023-03-07 13:13:59','Cập nhập bài viết của bản thân','UPDATE_SELF_POST',NULL,NULL,NULL,NULL,NULL),(12,'admin','2023-03-07 13:36:13','admin','2023-03-07 13:36:13','Xem bài viết của bản thân','VIEW_SELF_POST',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'admin','2021-06-14 12:30:44','admin','2023-03-07 10:39:40','ROLE_ADMIN','Quản trị'),(2,'admin','2021-06-14 13:30:44','admin','2023-02-28 19:32:54','ROLE_USER','Người dùng'),(3,'admin','2021-06-14 14:30:44','admin','2023-03-08 14:03:53','ROLE_AUTHOR','Tác giả'),(5,'admin','2023-03-07 10:01:36','admin','2023-03-07 10:39:52','ROLE_EDITOR','Người kiểm duyệt'),(6,'admin','2023-03-07 10:32:55','admin','2023-03-07 22:16:47','ROLE_EDITOR_2','Người kiểm duyệt 2');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_privilege` (
  `roleid` bigint NOT NULL,
  `privilegeid` bigint NOT NULL,
  KEY `FK3vthan6r8q7lt2tarrsahy3t6` (`privilegeid`),
  KEY `FKbd488lgu6vudlm8gw453slro4` (`roleid`),
  CONSTRAINT `FK3vthan6r8q7lt2tarrsahy3t6` FOREIGN KEY (`privilegeid`) REFERENCES `privilege` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKbd488lgu6vudlm8gw453slro4` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
INSERT INTO `role_privilege` VALUES (1,2),(1,4),(1,5),(1,7),(1,8),(1,9),(1,10),(5,9),(5,10),(6,4),(6,10),(3,4),(3,10),(3,11),(3,12);
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `interest` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKuser_category_idx` (`interest`),
  CONSTRAINT `FKuser_category` FOREIGN KEY (`interest`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','2021-06-13 22:39:18','admin','2021-06-13 22:39:20','adminadmin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'admin',12),(2,'admin','2021-06-13 22:39:19','admin','2021-06-13 22:39:19','test001test001','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'test001',NULL),(5,'admin','2021-06-13 22:39:20','admin','2021-06-13 23:25:05','test004test004','$2a$10$AlWzTWmgnYICgTACd5mMR.i4Kdt8XH.vcHWmkO.OFQqI2LoA9ZcZS',1,'test004',NULL),(6,'admin','2021-06-13 22:48:35','admin','2021-06-13 22:48:35','test005test005','$2a$10$sC64HlAqTeDlaWHfxkmntefAOGp8CguWQIsispPmodVbUJgps.kmu',1,'test005',NULL),(7,'anonymousUser','2021-07-26 20:18:21','anonymousUser','2021-07-26 20:18:21','useruser01','$2a$10$YjrUAn.08bYjbGpB.d.2Y.7DXMbo5WfUSJ9naUMJUHS3EgefqKQ56',1,'user01',13),(8,'anonymousUser','2021-07-27 07:38:32','anonymousUser','2021-07-27 07:38:32','useruser02','$2a$10$y2MpOiBtHb/4f9q/p1noOeTpV6mzcPRCQLW0CbuYhtx.rQ3cE0fK.',1,'user02',NULL),(9,'anonymousUser','2021-07-27 09:26:28','anonymousUser','2021-07-27 09:26:28','useruser03','$2a$10$i2yZUsaK.V5h3yin7klVUuKx4IzdU6v3TTPWD7Hdk.MxswLMFmlJy',1,'user03',NULL),(10,'anonymousUser','2021-07-27 16:44:19','anonymousUser','2021-07-27 16:44:19','user04user04','$2a$10$QqLKCXo5dl2.s2XA6Ekhk.fk5lab2g0l03oh6Qv5wtP/Xg1NNe87W',1,'user04',NULL),(12,'admin','2021-07-30 14:05:01','admin','2021-07-30 14:05:01','user05user05','$2a$10$Zd9WvqBrQaJLL3jAxU6woOg231FXrtHChrnnOIokQK0u2oPlMlW1G',1,'user05',NULL),(13,'admin','2023-03-01 11:26:14','author01','2023-03-01 11:30:17','author01author01','$2a$10$JVaaT6WR5rhu2vhrRFXXV.YQtfCSK.wIzPuZGcYMGybclUiY9mTCq',1,'author01',NULL),(14,'admin','2023-03-07 10:02:26','admin','2023-03-07 10:02:26','Editor FullName','$2a$10$8G3EUJovfRL/3/iCtcqDaOUtFJGU4CEDpVHP43w/gHYbj2nC4jwui',1,'editor01',NULL),(15,'admin','2023-03-07 10:33:30','admin','2023-03-07 10:33:30','Editor2 FullName','$2a$10$coWhQhid8MxwUIsp4lSGHejVksRg9ToYF7/iqMZ5Pe1TKmRX0DeXC',1,'editor02',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `userid` bigint NOT NULL,
  `roleid` bigint NOT NULL,
  KEY `FK_jpkvqqgndeonhr2alguthv64e` (`roleid`),
  KEY `FK_jwv62hhuqojjk7pot7kaex3e1` (`userid`),
  CONSTRAINT `FK_jpkvqqgndeonhr2alguthv64e` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_jwv62hhuqojjk7pot7kaex3e1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(2,3),(5,3),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(12,2),(12,3),(13,3),(14,5),(15,6);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-21 13:48:48
