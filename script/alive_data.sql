/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : alive

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 21/03/2024 00:42:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `product_id` bigint NOT NULL COMMENT '商品 ID',
  `spec` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '规格',
  `sku_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 编码',
  `sales_volume` int NOT NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `market_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存',
  `album_pics` varchar(3000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'SKU 图片数组',
  `weight` decimal(10, 4) NULL DEFAULT NULL COMMENT '商品重量，千克',
  `volume` decimal(10, 4) NULL DEFAULT NULL COMMENT '商品体积，立方米',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_productId`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品 SKU 信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_sku
-- ----------------------------
INSERT INTO `product_sku` VALUES (1, 1, '[{\"attributeId\":12,\"attributeName\":\"颜色\",\"valueId\":5,\"value\":\"白色\"}]', '1234000', 0, 0.00, 0.00, 0, '[\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/Snipaste_2023-12-25_18-30-46.png\"]', 0.0000, 0.0000, 0, 1, '2024-03-20 18:19:31', 1, '2024-03-20 18:19:31');
INSERT INTO `product_sku` VALUES (2, 2, '[{\"attributeId\":12,\"attributeName\":\"颜色\",\"valueId\":5,\"value\":\"白色\"}]', '1234000', 0, 0.00, 0.00, 0, '[\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo-white.png\",\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png\",\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/Snipaste_2023-12-25_18-31-08.png\",\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/Snipaste_2023-12-25_18-31-08.png\",\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/Snipaste_2023-12-25_18-31-02.png\"]', 0.0000, 0.0000, 0, 1, '2024-03-20 19:59:38', 1, '2024-03-20 19:59:38');
INSERT INTO `product_sku` VALUES (3, 3, '[{\"attributeId\":14,\"attributeName\":\"1\",\"valueId\":11,\"value\":\"2\"}]', '122000', 0, 1.00, 1.00, 2, '[\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png\",\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo-white.png\"]', 0.0020, 0.0040, 0, 1, '2024-03-20 20:16:07', 1, '2024-03-21 00:30:00');
INSERT INTO `product_sku` VALUES (4, 4, '[{\"attributeId\":15,\"attributeName\":\"12\",\"valueId\":13,\"value\":\"2\"}]', '12000', 0, 0.00, 0.00, 0, '[\"https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png\"]', 0.0000, 0.0000, 0, 1, '2024-03-20 20:21:02', 1, '2024-03-20 20:21:02');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父级分类 ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '图标',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0停用 1正常',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 0, '图书、音像、电子书刊', 0, NULL, 1, 0, 1, '2024-01-31 16:53:27', 1, '2024-03-11 22:27:12');
INSERT INTO `product_category` VALUES (2, 0, '手机', 0, NULL, 1, 0, 1, '2024-01-31 17:04:18', 1, '2024-03-07 21:41:33');
INSERT INTO `product_category` VALUES (3, 0, '家用电器', 0, NULL, 1, 0, 1, '2024-02-27 21:29:38', 1, '2024-02-27 21:29:38');
INSERT INTO `product_category` VALUES (4, 0, '数码', 0, NULL, 1, 0, 1, '2024-03-11 22:27:22', 1, '2024-03-11 22:27:22');
INSERT INTO `product_category` VALUES (5, 0, '家居家装', 0, NULL, 1, 0, 1, '2024-03-11 22:27:32', 1, '2024-03-11 22:27:32');
INSERT INTO `product_category` VALUES (6, 0, '电脑、办公', 0, NULL, 1, 0, 1, '2024-03-11 22:27:33', 1, '2024-03-11 22:27:33');
INSERT INTO `product_category` VALUES (7, 0, '厨具', 0, NULL, 1, 0, 1, '2024-03-11 22:27:34', 1, '2024-03-11 22:27:34');
INSERT INTO `product_category` VALUES (8, 0, '个护化妆', 0, NULL, 1, 0, 1, '2024-03-11 22:27:35', 1, '2024-03-11 22:27:35');
INSERT INTO `product_category` VALUES (9, 0, '服饰内衣', 0, NULL, 1, 0, 1, '2024-03-11 22:27:36', 1, '2024-03-11 22:27:36');
INSERT INTO `product_category` VALUES (10, 0, '钟表', 0, NULL, 1, 0, 1, '2024-03-11 22:27:37', 1, '2024-03-11 22:27:37');
INSERT INTO `product_category` VALUES (11, 0, '鞋靴', 0, NULL, 1, 0, 1, '2024-03-11 22:27:38', 1, '2024-03-11 22:27:38');
INSERT INTO `product_category` VALUES (12, 0, '母婴', 0, NULL, 1, 0, 1, '2024-03-11 22:27:39', 1, '2024-03-11 22:27:39');
INSERT INTO `product_category` VALUES (13, 0, '礼品箱包', 0, NULL, 1, 0, 1, '2024-03-11 22:27:40', 1, '2024-03-11 22:27:40');
INSERT INTO `product_category` VALUES (14, 0, '食品饮料、保健食品', 0, NULL, 1, 0, 1, '2024-03-11 22:27:41', 1, '2024-03-11 22:27:41');
INSERT INTO `product_category` VALUES (15, 0, '珠宝', 0, NULL, 1, 0, 1, '2024-03-11 22:27:42', 1, '2024-03-11 22:27:42');
INSERT INTO `product_category` VALUES (16, 0, '汽车用品', 0, NULL, 1, 0, 1, '2024-03-11 22:27:43', 1, '2024-03-11 22:27:43');
INSERT INTO `product_category` VALUES (17, 0, '运动健康', 0, NULL, 1, 0, 1, '2024-03-11 22:27:44', 1, '2024-03-11 22:27:44');
INSERT INTO `product_category` VALUES (18, 0, '玩具乐器', 0, NULL, 1, 0, 1, '2024-03-11 22:27:45', 1, '2024-03-11 22:27:45');
INSERT INTO `product_category` VALUES (19, 0, '彩票、旅行、充值、票务', 0, NULL, 1, 0, 1, '2024-03-11 22:27:46', 1, '2024-03-11 22:27:46');
INSERT INTO `product_category` VALUES (20, 0, '生鲜', 0, NULL, 1, 0, 1, '2024-03-11 22:27:47', 1, '2024-03-11 22:27:47');
INSERT INTO `product_category` VALUES (21, 0, '整车', 0, NULL, 1, 0, 1, '2024-03-11 22:27:48', 1, '2024-03-11 22:27:48');
INSERT INTO `product_category` VALUES (33, 1, '电子书刊', 0, NULL, 1, 0, 1, '2024-03-11 22:27:49', 1, '2024-03-11 22:27:49');
INSERT INTO `product_category` VALUES (34, 1, '音像', 0, NULL, 1, 0, 1, '2024-03-11 22:27:50', 1, '2024-03-11 22:27:50');
INSERT INTO `product_category` VALUES (35, 1, '英文原版', 0, NULL, 1, 0, 1, '2024-03-11 22:27:51', 1, '2024-03-11 22:27:51');
INSERT INTO `product_category` VALUES (36, 1, '文艺', 0, NULL, 1, 0, 1, '2024-03-11 22:27:52', 1, '2024-03-11 22:27:52');
INSERT INTO `product_category` VALUES (37, 1, '少儿', 0, NULL, 1, 0, 1, '2024-03-11 22:27:53', 1, '2024-03-11 22:27:53');
INSERT INTO `product_category` VALUES (38, 1, '人文社科', 0, NULL, 1, 0, 1, '2024-03-11 22:27:54', 1, '2024-03-11 22:27:54');
INSERT INTO `product_category` VALUES (39, 1, '经管励志', 0, NULL, 1, 0, 1, '2024-03-11 22:27:55', 1, '2024-03-11 22:27:55');
INSERT INTO `product_category` VALUES (40, 1, '生活', 0, NULL, 1, 0, 1, '2024-03-11 22:27:56', 1, '2024-03-11 22:27:56');
INSERT INTO `product_category` VALUES (41, 1, '科技', 0, NULL, 1, 0, 1, '2024-03-11 22:27:57', 1, '2024-03-11 22:27:57');
INSERT INTO `product_category` VALUES (42, 1, '教育', 0, NULL, 1, 0, 1, '2024-03-11 22:27:58', 1, '2024-03-11 22:27:58');
INSERT INTO `product_category` VALUES (43, 1, '港台图书', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 1, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (44, 1, '其他', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 1, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (45, 2, '手机', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 2, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (46, 2, '运营商', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 3, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (47, 2, '手机配件', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 4, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (48, 2, '对讲机', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 5, '2024-03-11 22:27:59');
INSERT INTO `product_category` VALUES (49, 2, '手机服务', 0, NULL, 1, 0, 1, '2024-03-11 22:27:59', 6, '2024-03-11 22:27:59');

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '品牌 logo',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌描述',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态，0停用 1正常',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品品牌' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_brand
-- ----------------------------
INSERT INTO `product_brand` VALUES (1, '华为', 0, '1', NULL, 1, 0, NULL, '2024-02-29 21:54:48', 1, '2024-03-12 20:04:49');
INSERT INTO `product_brand` VALUES (2, 'OPPO', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:08', 1, '2024-03-12 20:05:08');
INSERT INTO `product_brand` VALUES (3, 'VIVO', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:15', 1, '2024-03-12 20:05:15');
INSERT INTO `product_brand` VALUES (4, '真我', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:22', 1, '2024-03-12 20:05:22');
INSERT INTO `product_brand` VALUES (5, '一加', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:28', 1, '2024-03-12 20:05:28');
INSERT INTO `product_brand` VALUES (6, '小米', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:36', 1, '2024-03-12 20:05:36');
INSERT INTO `product_brand` VALUES (7, 'Apple', 0, '1', NULL, 1, 0, 1, '2024-03-12 20:05:41', 1, '2024-03-12 20:05:41');

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute`;
CREATE TABLE `product_attribute`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint NULL DEFAULT NULL COMMENT '分组 ID',
  `type` int NOT NULL DEFAULT 0 COMMENT '属性类型，0规格 1参数',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性名称',
  `select_type` tinyint NOT NULL DEFAULT 0 COMMENT '属性选择类型，0唯一 1单选 2多选',
  `input_type` tinyint NOT NULL DEFAULT 0 COMMENT '属性录入方式，0手工录入 1从列表中选取',
  `input_list` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '可选值列表，以逗号隔开',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `filter_type` tinyint NOT NULL DEFAULT 0 COMMENT '分类筛选样式，1普通 1颜色',
  `search_type` tinyint NOT NULL DEFAULT 0 COMMENT '检索类型，0不需要进行检索 1关键字检索 2范围检索',
  `related_status` tinyint NOT NULL DEFAULT 0 COMMENT '相同属性产品是否关联，0不关联 1关联',
  `addition` tinyint NOT NULL DEFAULT 0 COMMENT '支持新增',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attribute
-- ----------------------------
INSERT INTO `product_attribute` VALUES (1, 1, 0, '颜色', 1, 0, '', 0, 0, 1, 0, 0, 0, 1, '2024-03-07 23:14:40', 1, '2024-03-12 21:17:53');
INSERT INTO `product_attribute` VALUES (2, 1, 1, 'CCC强制性认证', 1, 0, '是,否', 0, 0, 0, 0, 0, 0, 1, '2024-03-12 20:42:57', 1, '2024-03-12 20:47:43');
INSERT INTO `product_attribute` VALUES (3, 1, 1, '机型', 0, 0, NULL, 1, 0, 1, 1, 0, 0, 1, '2024-03-12 21:14:20', 1, '2024-03-12 21:15:07');
INSERT INTO `product_attribute` VALUES (4, 1, 1, '上市日期', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-12 21:15:54', 1, '2024-03-12 21:15:54');
INSERT INTO `product_attribute` VALUES (5, 1, 1, '电信设备进网许可证', 1, 0, '是,否', 0, 0, 1, 0, 0, 0, 1, '2024-03-12 21:16:22', 1, '2024-03-12 21:16:22');
INSERT INTO `product_attribute` VALUES (6, 1, 1, '入网型号', 0, 0, NULL, 0, 0, 1, 0, 0, 0, 1, '2024-03-12 21:16:43', 1, '2024-03-12 21:16:43');
INSERT INTO `product_attribute` VALUES (7, 1, 0, '版本', 0, 0, '', 0, 0, 1, 0, 1, 0, 1, '2024-03-12 21:24:34', 1, '2024-03-12 21:24:34');
INSERT INTO `product_attribute` VALUES (8, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 18:04:19', 1, '2024-03-20 18:04:19');
INSERT INTO `product_attribute` VALUES (9, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 18:05:18', 1, '2024-03-20 18:05:18');
INSERT INTO `product_attribute` VALUES (10, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 18:07:10', 1, '2024-03-20 18:07:10');
INSERT INTO `product_attribute` VALUES (11, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 18:08:44', 1, '2024-03-20 18:08:44');
INSERT INTO `product_attribute` VALUES (12, NULL, 0, '颜色', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 18:19:16', 1, '2024-03-20 18:19:16');
INSERT INTO `product_attribute` VALUES (13, NULL, 0, '啊', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 20:09:56', 1, '2024-03-20 20:09:56');
INSERT INTO `product_attribute` VALUES (14, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 20:15:54', 1, '2024-03-20 20:15:54');
INSERT INTO `product_attribute` VALUES (15, NULL, 0, '12', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 20:20:44', 1, '2024-03-20 20:20:44');
INSERT INTO `product_attribute` VALUES (16, NULL, 0, '1', 0, 0, NULL, 0, 0, 0, 0, 0, 0, 1, '2024-03-20 21:12:29', 1, '2024-03-20 21:12:29');

-- ----------------------------
-- Table structure for product_attribute_group
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_group`;
CREATE TABLE `product_attribute_group`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '分类 ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性分组名称',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attribute_group
-- ----------------------------
INSERT INTO `product_attribute_group` VALUES (1, 45, '主体', 0, 0, 1, '2024-03-07 21:56:55', 1, '2024-03-12 20:32:00');

-- ----------------------------
-- Table structure for product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_value`;
CREATE TABLE `product_attribute_value`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attribute_id` bigint NOT NULL COMMENT '属性 ID',
  `product_id` bigint NULL DEFAULT NULL COMMENT '商品编号',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '属性值',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品属性值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_attribute_value
-- ----------------------------
INSERT INTO `product_attribute_value` VALUES (1, 8, NULL, '1', 0, 0, 1, '2024-03-20 18:04:21', 1, '2024-03-20 18:04:21');
INSERT INTO `product_attribute_value` VALUES (2, 9, NULL, '1', 0, 0, 1, '2024-03-20 18:05:19', 1, '2024-03-20 18:05:19');
INSERT INTO `product_attribute_value` VALUES (3, 10, NULL, '2', 0, 0, 1, '2024-03-20 18:07:11', 1, '2024-03-20 18:07:11');
INSERT INTO `product_attribute_value` VALUES (4, 11, NULL, '2', 0, 0, 1, '2024-03-20 18:08:46', 1, '2024-03-20 18:08:46');
INSERT INTO `product_attribute_value` VALUES (5, 12, NULL, '白色', 0, 0, 1, '2024-03-20 18:19:20', 1, '2024-03-20 18:19:20');
INSERT INTO `product_attribute_value` VALUES (6, 2, 1, '制性认证', 0, 0, 1, '2024-03-20 18:19:31', 1, '2024-03-20 18:19:31');
INSERT INTO `product_attribute_value` VALUES (7, 4, 1, '110100', 0, 0, 1, '2024-03-20 18:19:31', 1, '2024-03-20 18:19:31');
INSERT INTO `product_attribute_value` VALUES (8, 2, 2, '制性认证', 0, 0, 1, '2024-03-20 19:59:38', 1, '2024-03-20 19:59:38');
INSERT INTO `product_attribute_value` VALUES (9, 4, 2, '110100', 0, 0, 1, '2024-03-20 19:59:38', 1, '2024-03-20 19:59:38');
INSERT INTO `product_attribute_value` VALUES (10, 13, NULL, '1', 0, 0, 1, '2024-03-20 20:09:58', 1, '2024-03-20 20:09:58');
INSERT INTO `product_attribute_value` VALUES (11, 14, NULL, '2', 0, 0, 1, '2024-03-20 20:15:56', 1, '2024-03-20 20:15:56');
INSERT INTO `product_attribute_value` VALUES (12, 2, 3, '12', 0, 0, 1, '2024-03-20 20:16:08', 1, '2024-03-20 20:16:08');
INSERT INTO `product_attribute_value` VALUES (13, 15, NULL, '2', 0, 0, 1, '2024-03-20 20:20:45', 1, '2024-03-20 20:20:45');
INSERT INTO `product_attribute_value` VALUES (14, 16, NULL, '1', 0, 0, 1, '2024-03-20 21:12:30', 1, '2024-03-20 21:12:30');
INSERT INTO `product_attribute_value` VALUES (15, 14, NULL, '3', 0, 0, 1, '2024-03-21 00:20:43', 1, '2024-03-21 00:20:43');
INSERT INTO `product_attribute_value` VALUES (16, 14, NULL, '1', 0, 0, 1, '2024-03-21 00:39:55', 1, '2024-03-21 00:39:55');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` bigint NOT NULL COMMENT '所属分类',
  `brand_id` bigint NOT NULL COMMENT '所属品牌',
  `sn_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品封面图片',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态，0下架 1上架',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `sales_volume` int NOT NULL DEFAULT 0 COMMENT '销量',
  `price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `market_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `stock` int NOT NULL DEFAULT 0 COMMENT '库存',
  `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '单位',
  `detail_html` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品详情',
  `detail_mobile_html` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '移动端商品详情',
  `gift_point` int NOT NULL DEFAULT 0 COMMENT '赠送积分',
  `gift_growth` int NOT NULL DEFAULT 0 COMMENT '赠送成长值',
  `use_point_limit` int NOT NULL DEFAULT 0 COMMENT '限制使用的积分数',
  `new_status` tinyint NOT NULL DEFAULT 0 COMMENT '是否新品',
  `recommend_status` tinyint NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `service_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '服务保障',
  `sub_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '副标题',
  `keyword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '关键字',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '简介',
  `is_del` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  `creator` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_categoryId`(`category_id` ASC) USING BTREE,
  INDEX `idx_brandId`(`brand_id` ASC) USING BTREE,
  INDEX `idx_snCode`(`sn_code` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, 45, 1, '1234', '测试', 'https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png', 0, 0, 0, 0.00, 0.00, 0, '看', '', '', 0, 0, 0, 0, 0, NULL, '副标题', '', '', 0, 1, '2024-03-20 18:19:31', 1, '2024-03-20 18:19:31');
INSERT INTO `product` VALUES (2, 45, 1, '1234', '测试', 'https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png', 0, 0, 0, 0.00, 0.00, 0, '看', '', '', 0, 0, 0, 0, 0, NULL, '副标题', '', '', 0, 1, '2024-03-20 19:59:38', 1, '2024-03-20 19:59:38');
INSERT INTO `product` VALUES (3, 45, 1, '122', '12', 'https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png', 0, 0, 0, 0.00, 0.00, 0, '12', '', '', 0, 0, 0, 0, 0, NULL, '12', '', '', 0, 1, '2024-03-20 20:16:07', 1, '2024-03-20 20:16:07');
INSERT INTO `product` VALUES (4, 45, 1, '12', '12', 'https://alive-1318219312.cos.ap-chengdu.myqcloud.com/product/logo.png', 0, 0, 0, 0.00, 0.00, 0, '12', '', '', 0, 0, 0, 0, 0, NULL, '12', '', '', 1, 1, '2024-03-20 20:21:02', 1, '2024-03-20 21:26:30');

SET FOREIGN_KEY_CHECKS = 1;
