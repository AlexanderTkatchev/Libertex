DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` identity NOT NULL PRIMARY KEY,
  `name` varchar(50) DEFAULT NULL,
  `wallet` decimal(20,2) NOT NULL DEFAULT 0
);
