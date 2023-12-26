CREATE TABLE `routers` (
  `name` varchar(100) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `vendor` varchar(100) DEFAULT NULL,
  `siteId` varchar(50) DEFAULT NULL,
  `ospf` varchar(255) DEFAULT NULL,
  `ping` tinyint(1) DEFAULT NULL,
  `provinceId` varchar(2) DEFAULT NULL,
  `nodeType` varchar(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
