-- 文章表
CREATE TABLE `articles` (
  `id` varchar(30) NOT NULL COMMENT '主键',
  `author` varchar(20) NOT NULL COMMENT '作者',
  `category` varchar(50) NOT NULL COMMENT '文章分类',
  `tabloid` varchar(255) NOT NULL COMMENT '文章简介',
  `content` text NOT NULL COMMENT '文章内容',
  `tags` varchar(50) NOT NULL COMMENT '文章标签',
  `title` varchar(100) NOT NULL COMMENT '文章标题',
  `type` int(2) DEFAULT NULL COMMENT '文章类型：1：原创，2：转载',
  `views` int(11) DEFAULT 0 COMMENT '文章访问量',
  `gmt_create` bigint(20) NOT NULL COMMENT '文章创建时间',
  `gmt_update` bigint(20) DEFAULT NULL COMMENT '文章更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 留言表
CREATE TABLE `messages` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(20) NOT NULL COMMENT '留言者自定义昵称',
  `email` varchar(50) NOT NULL COMMENT '留言者邮箱',
  `content` varchar(255) NOT NULL COMMENT '留言内容',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间（时间戳）',
  `status` int(2) DEFAULT 0 COMMENT '留言状态：0：未读，1：已读',
  `reply_content` varchar(255) DEFAULT NULL COMMENT '留言回复的内容',
  `gmt_reply` bigint(20) DEFAULT NULL COMMENT '回复留言的时间（时间戳）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;