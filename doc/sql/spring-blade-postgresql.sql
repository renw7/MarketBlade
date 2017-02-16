/*
Navicat PGSQL Data Transfer

Source Server         : postgresql_local
Source Server Version : 90601
Source Host           : localhost:5432
Source Database       : blade
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90601
File Encoding         : 65001

Date: 2017-01-12 16:16:16
*/


-- ----------------------------
-- Sequence structure for blade_notice_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_notice_id_seq";
CREATE SEQUENCE "blade_notice_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."blade_notice_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for blade_attach_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_attach_id_seq";
CREATE SEQUENCE "blade_attach_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."blade_attach_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for blade_dept_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_dept_id_seq";
CREATE SEQUENCE "blade_dept_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;
SELECT setval('"public"."blade_dept_id_seq"', 14, true);

-- ----------------------------
-- Sequence structure for blade_dict_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_dict_id_seq";
CREATE SEQUENCE "blade_dict_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 46
 CACHE 1;
SELECT setval('"public"."blade_dict_id_seq"', 46, true);

-- ----------------------------
-- Sequence structure for blade_generate_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_generate_id_seq";
CREATE SEQUENCE "blade_generate_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."blade_generate_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for blade_login_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_login_log_id_seq";
CREATE SEQUENCE "blade_login_log_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;
SELECT setval('"public"."blade_login_log_id_seq"', 3, true);

-- ----------------------------
-- Sequence structure for blade_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_menu_id_seq";
CREATE SEQUENCE "blade_menu_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 105
 CACHE 1;
SELECT setval('"public"."blade_menu_id_seq"', 105, true);

-- ----------------------------
-- Sequence structure for blade_operation_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_operation_log_id_seq";
CREATE SEQUENCE "blade_operation_log_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."blade_operation_log_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for blade_parameter_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_parameter_id_seq";
CREATE SEQUENCE "blade_parameter_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2
 CACHE 1;
SELECT setval('"public"."blade_parameter_id_seq"', 2, true);

-- ----------------------------
-- Sequence structure for blade_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_relation_id_seq";
CREATE SEQUENCE "blade_relation_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 2100
 CACHE 1;
SELECT setval('"public"."blade_relation_id_seq"', 2100, true);

-- ----------------------------
-- Sequence structure for blade_role_ext_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_role_ext_id_seq";
CREATE SEQUENCE "blade_role_ext_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 11
 CACHE 1;
SELECT setval('"public"."blade_role_ext_id_seq"', 11, true);

-- ----------------------------
-- Sequence structure for blade_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_role_id_seq";
CREATE SEQUENCE "blade_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 7
 CACHE 1;
SELECT setval('"public"."blade_role_id_seq"', 7, true);

-- ----------------------------
-- Sequence structure for blade_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "blade_user_id_seq";
CREATE SEQUENCE "blade_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 5
 CACHE 1;
SELECT setval('"public"."blade_user_id_seq"', 5, true);

-- ----------------------------
-- Table structure for blade_attach
-- ----------------------------
DROP TABLE IF EXISTS "blade_attach";
CREATE TABLE "blade_attach" (
"id" int4 DEFAULT nextval('blade_attach_id_seq'::regclass) NOT NULL,
"code" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
"url" text COLLATE "default",
"status" int4,
"creater" int4,
"createtime" timestamp(0)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_attach
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blade_dept
-- ----------------------------
DROP TABLE IF EXISTS "blade_dept";
CREATE TABLE "blade_dept" (
"id" int4 DEFAULT nextval('blade_dept_id_seq'::regclass) NOT NULL,
"num" int4,
"pid" int4,
"simplename" varchar(45) COLLATE "default",
"fullname" varchar(255) COLLATE "default",
"tips" varchar(255) COLLATE "default",
"version" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_dept
-- ----------------------------
BEGIN;
INSERT INTO "blade_dept" VALUES ('1', '0', '0', 'company', 'company', null, '1');
INSERT INTO "blade_dept" VALUES ('2', '1', '1', '江苏XX', '江苏XX信息技术有限公司', null, '1');
INSERT INTO "blade_dept" VALUES ('3', '1', '2', '技服部', '技术服务部', null, '2');
INSERT INTO "blade_dept" VALUES ('4', '2', '2', '客服部', '客户服务部', null, '1');
INSERT INTO "blade_dept" VALUES ('5', '3', '2', '销售部', '销售部', null, '1');
INSERT INTO "blade_dept" VALUES ('6', '4', '2', '综合管理部', '综合管理部', null, '1');
INSERT INTO "blade_dept" VALUES ('7', '2', '1', '常州XX', '常州XX软件技术有限公司', null, '1');
INSERT INTO "blade_dept" VALUES ('8', '1', '7', '产品部', '产品部', null, '1');
INSERT INTO "blade_dept" VALUES ('9', '2', '7', '研发部', '研发部', null, '1');
INSERT INTO "blade_dept" VALUES ('10', '3', '7', '项目部', '项目部', null, '1');
INSERT INTO "blade_dept" VALUES ('11', '4', '7', '运维部', '运维部', null, '1');
INSERT INTO "blade_dept" VALUES ('12', '5', '7', '销售部', '销售部', null, '1');
INSERT INTO "blade_dept" VALUES ('13', '6', '7', '行政部', '行政部', null, '1');
COMMIT;

-- ----------------------------
-- Table structure for blade_dict
-- ----------------------------
DROP TABLE IF EXISTS "blade_dict";
CREATE TABLE "blade_dict" (
"id" int4 DEFAULT nextval('blade_dict_id_seq'::regclass) NOT NULL,
"code" varchar(255) COLLATE "default",
"num" int4,
"pid" int4,
"name" varchar(255) COLLATE "default",
"tips" varchar(255) COLLATE "default",
"version" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_dict
-- ----------------------------
BEGIN;
INSERT INTO "blade_dict" VALUES ('1', '101', '0', '0', '性别', null, '0');
INSERT INTO "blade_dict" VALUES ('2', '101', '1', '1', '男', null, '5');
INSERT INTO "blade_dict" VALUES ('3', '101', '2', '1', '女', null, '5');
INSERT INTO "blade_dict" VALUES ('4', '101', '3', '1', '未知', ' ', null);
INSERT INTO "blade_dict" VALUES ('5', '901', '0', '0', '账号状态', null, '0');
INSERT INTO "blade_dict" VALUES ('6', '901', '1', '5', '启用', null, '0');
INSERT INTO "blade_dict" VALUES ('7', '901', '2', '5', '冻结', null, '0');
INSERT INTO "blade_dict" VALUES ('8', '901', '3', '5', '待审核', null, '2');
INSERT INTO "blade_dict" VALUES ('9', '901', '4', '5', '审核拒绝', null, '0');
INSERT INTO "blade_dict" VALUES ('10', '901', '5', '5', '已删除', null, '3');
INSERT INTO "blade_dict" VALUES ('11', '902', '0', '0', '状态', null, '0');
INSERT INTO "blade_dict" VALUES ('12', '902', '1', '11', '启用', null, '0');
INSERT INTO "blade_dict" VALUES ('13', '902', '2', '11', '禁用', null, '0');
INSERT INTO "blade_dict" VALUES ('14', '102', '0', '0', '公告类型', null, '0');
INSERT INTO "blade_dict" VALUES ('15', '102', '10', '14', '通知公告', null, '0');
INSERT INTO "blade_dict" VALUES ('16', '102', '9', '14', '发布计划', null, '0');
INSERT INTO "blade_dict" VALUES ('17', '903', '0', '0', '审核状态', null, '0');
INSERT INTO "blade_dict" VALUES ('18', '903', '1', '17', '待审核', null, '0');
INSERT INTO "blade_dict" VALUES ('19', '903', '2', '17', '审核拒绝', null, '0');
INSERT INTO "blade_dict" VALUES ('20', '903', '3', '17', '审核通过', null, '0');
INSERT INTO "blade_dict" VALUES ('41', '102', '6', '16', '测试', null, '0');
INSERT INTO "blade_dict" VALUES ('44', '102', '1', '14', '发布测试', null, '0');
INSERT INTO "blade_dict" VALUES ('45', '102', '2', '16', '测试222', null, '2');
COMMIT;

-- ----------------------------
-- Table structure for blade_generate
-- ----------------------------
DROP TABLE IF EXISTS "blade_generate";
CREATE TABLE "blade_generate" (
"id" int4 DEFAULT nextval('blade_generate_id_seq'::regclass) NOT NULL,
"name" varchar(255) COLLATE "default",
"realpath" varchar(255) COLLATE "default",
"packagename" varchar(255) COLLATE "default",
"modelname" varchar(255) COLLATE "default",
"tablename" varchar(255) COLLATE "default",
"pkname" varchar(255) COLLATE "default",
"tips" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_generate
-- ----------------------------
BEGIN;
INSERT INTO "blade_generate" VALUES ('1', '测试', 'E:\Workspaces\blade\SpringBlade', 'com.smallchill.gen', 'Notice', 'blade_notice', 'id', null);
COMMIT;

-- ----------------------------
-- Table structure for blade_login_log
-- ----------------------------
DROP TABLE IF EXISTS "blade_login_log";
CREATE TABLE "blade_login_log" (
"id" int4 DEFAULT nextval('blade_login_log_id_seq'::regclass) NOT NULL,
"logname" varchar(255) COLLATE "default",
"userid" varchar(255) COLLATE "default",
"classname" varchar(255) COLLATE "default",
"method" text COLLATE "default",
"createtime" timestamp(0),
"succeed" varchar(255) COLLATE "default",
"message" text COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_login_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for blade_menu
-- ----------------------------
DROP TABLE IF EXISTS "blade_menu";
CREATE TABLE "blade_menu" (
"id" int4 DEFAULT nextval('blade_menu_id_seq'::regclass) NOT NULL,
"code" varchar(255) COLLATE "default",
"pcode" varchar(255) COLLATE "default",
"alias" varchar(255) COLLATE "default",
"name" varchar(255) COLLATE "default",
"icon" varchar(255) COLLATE "default",
"url" varchar(255) COLLATE "default",
"num" int4,
"levels" int4,
"source" text COLLATE "default",
"path" varchar(255) COLLATE "default",
"tips" varchar(255) COLLATE "default",
"status" int4,
"isopen" varchar(255) COLLATE "default",
"istemplate" varchar(255) COLLATE "default",
"version" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "blade_menu"."code" IS '菜单编号';

-- ----------------------------
-- Records of blade_menu
-- ----------------------------
BEGIN;
INSERT INTO "blade_menu" VALUES ('1', 'system', '0', null, '系统管理', 'fa-cog', null, '9', '1', null, null, null, '1', '1', '0', '3');
INSERT INTO "blade_menu" VALUES ('2', 'role', 'system', null, '角色管理', 'fa-key', '/role/', '2', '2', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('3', 'role_add', 'role', 'addex', '角色新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/role/add', '1', '3', null, 'role_add.html', '800*340', '1', '0', null, '3');
INSERT INTO "blade_menu" VALUES ('4', 'role_edit', 'role', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/role/edit', '2', '3', null, 'role_edit.html', '800*340', '1', '0', '0', '3');
INSERT INTO "blade_menu" VALUES ('5', 'role_remove', 'role', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/role/remove', '3', '3', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('6', 'role_view', 'role', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/role/view', '4', '3', null, 'role_view.html', '800*340', '1', null, null, '1');
INSERT INTO "blade_menu" VALUES ('7', 'role_authority', 'role', 'authority', '权限配置', 'btn btn-xs btn-white | fa fa-wrench  bigger-120', '/role/authority', '5', '3', null, 'role_authority.html', '350*500', '1', '0', null, '2');
INSERT INTO "blade_menu" VALUES ('8', 'user', 'system', null, '用户管理', 'fa-user', '/user/', '1', '2', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('9', 'user_add', 'user', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/user/add', '1', '3', null, 'user_add.html', '800*430', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('10', 'user_edit', 'user', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/user/edit', '2', '3', null, 'user_edit.html', '800*430', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('11', 'user_del', 'user', 'remove', '删除', 'btn btn-xs btn-white | fa fa fa-times bigger-120', '/user/del', '3', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('12', 'user_view', 'user', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/user/view', '4', '3', null, 'user_view.html', '800*390', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('13', 'user_audit', 'user', 'audit', '审核', 'btn btn-xs btn-white | fa fa-user  bigger-120', '{"toint_status_equal":3}', '5', '3', null, null, null, '1', '0', null, '2');
INSERT INTO "blade_menu" VALUES ('14', 'user_audit_ok', 'user_audit', 'ok', '通过', 'btn btn-xs btn-white | fa fa-check  bigger-120', '/user/auditOk', '1', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('15', 'user_audit_refuse', 'user_audit', 'refuse', '拒绝', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/user/auditRefuse', '2', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('16', 'user_audit_back', 'user_audit', 'back', '返回', 'btn btn-xs btn-white | fa fa-undo  bigger-120', null, '3', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('17', 'user_reset', 'user', 'reset', '重置密码', 'btn btn-xs btn-white | fa fa-key  bigger-120', '/user/reset', '6', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('18', 'user_ban', 'user', 'frozen', '冻结', 'btn btn-xs btn-white | fa fa-ban  bigger-120', '/user/ban', '7', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('19', 'user_recycle', 'user', 'recycle', '回收站', 'btn btn-xs btn-white | fa fa-recycle  bigger-120', '{"toint_status_equal":"5"}', '8', '3', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('20', 'user_recycle_restore', 'user_recycle', 'restore', '还原', 'btn btn-xs btn-white | fa fa-refresh  bigger-120', '/user/restore', '1', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('21', 'user_recycle_remove', 'user_recycle', 'remove', '彻底删除', 'btn btn-xs btn-white  btn-danger | fa fa fa-times bigger-120', '/user/remove', '2', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('22', 'user_recycle_back', 'user_recycle', 'back', '返回', 'btn btn-xs btn-white | fa fa-undo  bigger-120', null, '3', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('23', 'user_roleAssign', 'user', 'assign', '角色分配', 'btn btn-xs btn-white | fa fa-users bigger-120', '/user/roleAssign', '9', '3', null, 'user_roleAssign.html', '350*500', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('24', 'user_extrole', 'user', 'agent', '权限代理', 'btn btn-xs btn-white | fa fa-wrench  bigger-120', '/user/extrole', '10', '3', null, 'user_extrole.html', null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('25', 'menu', 'system', null, '菜单管理', 'fa-tasks', '/menu/', '3', '2', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('26', 'menu_add', 'menu', 'addex', '菜单新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/menu/add', '1', '3', null, 'menu_add.html', '800*430', '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('27', 'menu_edit', 'menu', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/menu/edit', '2', '3', null, 'menu_edit.html', '800*430', '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('28', 'menu_del', 'menu', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/menu/del', '3', '3', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('29', 'menu_view', 'menu', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/menu/view', '4', '3', null, 'menu_view.html', '800*430', '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('30', 'menu_recycle', 'menu', 'recycle', '回收站', 'btn btn-xs btn-white | fa fa-recycle  bigger-120', '{"toint_status_equal":"2"}', '5', '3', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('31', 'menu_recycle_restore', 'menu_recycle', 'restore', '还原', 'btn btn-xs btn-white | fa fa-refresh  bigger-120', '/menu/restore', '1', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('32', 'menu_recycle_remove', 'menu_recycle', 'remove', '彻底删除', 'btn btn-xs btn-white  btn-danger | fa fa fa-times bigger-120', '/menu/remove', '2', '4', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('33', 'menu_recycle_back', 'menu_recycle', 'back', '返回', 'btn btn-xs btn-white | fa fa-undo  bigger-120', null, '3', '4', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('34', 'dict', 'system', null, '字典管理', 'fa fa-book', '/dict/', '4', '2', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('35', 'dict_add', 'dict', 'addex', '字典新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/dict/add', '1', '3', null, 'dict_add.html', '800*390', '1', '0', null, '2');
INSERT INTO "blade_menu" VALUES ('36', 'dict_edit', 'dict', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/dict/edit', '2', '3', null, 'dict_edit.html', '800*390', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('37', 'dict_remove', 'dict', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/dict/remove', '3', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('38', 'dict_view', 'dict', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/dict/view', '4', '3', null, 'dict_view.html', '800*390', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('39', 'dept', 'system', null, '部门管理', 'fa fa-users', '/dept/', '5', '2', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('40', 'dept_add', 'dept', 'addex', '部门新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/dept/add', '1', '3', null, 'dept_add.html', '800*340', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('41', 'dept_edit', 'dept', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/dept/edit', '2', '3', null, 'dept_edit.html', '800*340', '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('42', 'dept_remove', 'dept', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/dept/remove', '3', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('43', 'dept_view', 'dept', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/dept/view', '4', '3', null, 'dept_view.html', '800*340', '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('44', 'attach', 'system', null, '附件管理', 'fa fa-paperclip', '/attach/', '6', '2', null, 'attach.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('45', 'attach_add', 'attach', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/attach/add', '1', '3', null, 'attach_add.html', '800*450', '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('46', 'attach_edit', 'attach', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/attach/edit', '2', '3', null, 'attach_edit.html', '800*290', '1', '0', null, '0');
INSERT INTO "blade_menu" VALUES ('47', 'attach_remove', 'attach', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/attach/remove', '3', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('48', 'attach_view', 'attach', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/attach/view', '4', '3', null, 'attach_view.html', '800*450', '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('49', 'attach_download', 'attach', 'download', '下载', 'btn btn-xs btn-white | fa fa-paperclip bigger-120', '/attach/download', '5', '3', null, null, null, '1', null, null, '0');
INSERT INTO "blade_menu" VALUES ('56', 'parameter', 'system', null, '参数化管理', 'fa-tags', '/parameter/', '9', '2', null, 'parameter.html', null, '1', '0', '1', '0');
INSERT INTO "blade_menu" VALUES ('57', 'parameter_add', 'parameter', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/parameter/add', '1', '3', null, 'parameter_add.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('58', 'parameter_edit', 'parameter', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/parameter/edit', '2', '3', null, 'parameter_edit.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('59', 'parameter_del', 'parameter', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/parameter/del', '3', '3', null, null, null, '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('60', 'parameter_view', 'parameter', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/parameter/view', '4', '3', null, 'parameter_view.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('61', 'parameter_recycle', 'parameter', 'recycle', '回收站', 'btn btn-xs btn-white | fa fa-recycle  bigger-120', '{"toint_status_equal":"5"}', '5', '3', null, 'parameter_recycle.html', null, '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('62', 'parameter_recycle_restore', 'parameter_recycle', 'restore', '还原', 'btn btn-xs btn-white | fa fa-refresh  bigger-120', '/parameter/restore', '1', '4', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('63', 'parameter_recycle_remove', 'parameter_recycle', 'remove', '彻底删除', 'btn btn-xs btn-white  btn-danger | fa fa fa-times bigger-120', '/parameter/remove', '2', '4', null, null, null, '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('64', 'parameter_recycle_back', 'parameter_recycle', 'back', '返回', 'btn btn-xs btn-white | fa fa-undo  bigger-120', null, '3', '4', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('65', 'druid', 'system', null, '连接池监视', 'fa-arrows-v', '/druid', '10', '2', null, null, null, '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('81', 'log', 'system', null, '日志管理', 'fa-tasks', null, '11', '2', null, null, null, '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('82', 'olog', 'log', null, '操作日志', 'fa-database', '/olog/', '1', '3', null, 'olog.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('83', 'llog', 'log', null, '登录日志', 'fa-sign-in', '/llog/', '2', '3', null, 'llog.html', null, '1', '0', '1', '0');
INSERT INTO "blade_menu" VALUES ('84', 'olog_add', 'olog', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/olog/add', '1', '4', null, 'olog_add.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('85', 'olog_edit', 'olog', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/olog/edit', '2', '4', null, 'olog_edit.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('86', 'olog_remove', 'olog', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/olog/remove', '3', '4', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('87', 'olog_view', 'olog', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/olog/view', '4', '4', null, 'olog_view.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('88', 'llog_add', 'llog', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/llog/add', '1', '4', null, 'llog_add.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('89', 'llog_edit', 'llog', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/llog/edit', '2', '4', null, 'llog_edit.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('90', 'llog_remove', 'llog', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/llog/remove', '3', '4', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('91', 'llog_view', 'llog', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/llog/view', '4', '4', null, 'llog_view.html', null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('92', 'office', '0', null, '工作台', 'fa fa-desktop', null, '1', '1', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('93', 'notice', 'office', null, '通知公告', 'fa fa-bell', '/notice/', '1', '2', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('94', 'notice_add', 'notice', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/notice/add', '1', '3', null, null, '800*450', '1', '0', '0', '1');
INSERT INTO "blade_menu" VALUES ('95', 'notice_edit', 'notice', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/notice/edit', '2', '3', null, null, '800*450', '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('96', 'notice_remove', 'notice', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/notice/remove', '3', '3', null, null, null, '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('97', 'notice_view', 'notice', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/notice/view', '4', '3', null, null, '800*450', '1', '0', '0', '0');
INSERT INTO "blade_menu" VALUES ('98', 'online', 'system', '', '在线开发', 'fa-rocket', null, '12', '2', null, null, '800*520', '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('99', 'generate', 'online', null, '代码生成', 'fa-gavel', '/generate/', '1', '3', null, null, '800*520', '1', '0', null, '1');
INSERT INTO "blade_menu" VALUES ('100', 'generate_add', 'generate', 'add', '新增', 'btn btn-xs btn-white | fa fa-floppy-o bigger-120', '/generate/add', '1', '4', null, null, '800*420', '1', '0', null, '3');
INSERT INTO "blade_menu" VALUES ('101', 'generate_edit', 'generate', 'edit', '修改', 'btn btn-xs btn-white | fa fa-pencil  bigger-120', '/generate/edit', '2', '4', null, null, '800*420', '1', '0', null, '3');
INSERT INTO "blade_menu" VALUES ('102', 'generate_remove', 'generate', 'remove', '删除', 'btn btn-xs btn-white | fa fa-times  bigger-120', '/generate/remove', '3', '4', null, null, '800*520', '1', '0', null, null);
INSERT INTO "blade_menu" VALUES ('103', 'generate_view', 'generate', 'view', '查看', 'btn btn-xs btn-white | fa fa-eye bigger-120', '/generate/view', '4', '4', null, null, '800*420', '1', '0', null, '3');
INSERT INTO "blade_menu" VALUES ('104', 'generate_gencode', 'generate', 'gencode', '代码生成', 'btn btn-xs btn-white | fa fa-gavel bigger-120', '/generate/gencode', '5', '4', null, null, '800*520', '1', '0', null, '1');
COMMIT;

-- ----------------------------
-- Table structure for blade_notice
-- ----------------------------
DROP TABLE IF EXISTS "blade_notice";
CREATE TABLE "blade_notice" (
"id" int4 DEFAULT nextval('blade_notice_id_seq'::regclass) NOT NULL,
"title" varchar(255) COLLATE "default",
"type" int4,
"content" text COLLATE "default",
"publishtime" timestamp(0),
"createtime" timestamp(0),
"creater" int4,
"pic" int4,
"version" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "blade_notice"."id" IS '序列';
COMMENT ON COLUMN "blade_notice"."title" IS '标题';
COMMENT ON COLUMN "blade_notice"."type" IS '类型';
COMMENT ON COLUMN "blade_notice"."content" IS '内容';
COMMENT ON COLUMN "blade_notice"."publishtime" IS '发布时间';
COMMENT ON COLUMN "blade_notice"."createtime" IS '创建时间';
COMMENT ON COLUMN "blade_notice"."creater" IS '创建人';
COMMENT ON COLUMN "blade_notice"."pic" IS '图片';

-- ----------------------------
-- Records of blade_notice
-- ----------------------------
BEGIN;
INSERT INTO "blade_notice" VALUES ('1', '标题2', '10', '123123', '2017-01-22 00:00:00', '2017-01-11 08:43:00', '1', null, '1');
INSERT INTO "blade_notice" VALUES ('2', '标题1', '9', '123', '2017-01-15 00:00:00', '2017-01-11 16:25:50', '1', null, '3');
INSERT INTO "blade_notice" VALUES ('3', '标题3', '1', '123', '2017-01-31 00:00:00', '2017-01-12 16:15:29', '1', null, null);
COMMIT;

-- ----------------------------
-- Table structure for blade_operation_log
-- ----------------------------
DROP TABLE IF EXISTS "blade_operation_log";
CREATE TABLE "blade_operation_log" (
"id" int4 DEFAULT nextval('blade_operation_log_id_seq'::regclass) NOT NULL,
"logname" varchar(255) COLLATE "default",
"userid" varchar(255) COLLATE "default",
"classname" varchar(255) COLLATE "default",
"method" text COLLATE "default",
"createtime" timestamp(0),
"succeed" varchar(255) COLLATE "default",
"message" text COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_operation_log
-- ----------------------------
BEGIN;
INSERT INTO "blade_operation_log" VALUES ('1', '异常日志', '1', null, 'Missing URI template variable ''tablePath'' for method parameter of type String', '2016-11-01 09:29:09', '0', null);
COMMIT;

-- ----------------------------
-- Table structure for blade_parameter
-- ----------------------------
DROP TABLE IF EXISTS "blade_parameter";
CREATE TABLE "blade_parameter" (
"id" int4 DEFAULT nextval('blade_parameter_id_seq'::regclass) NOT NULL,
"code" varchar(255) COLLATE "default",
"num" int4,
"name" varchar(255) COLLATE "default",
"para" text COLLATE "default",
"tips" varchar(255) COLLATE "default",
"status" int4,
"version" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_parameter
-- ----------------------------
BEGIN;
INSERT INTO "blade_parameter" VALUES ('1', '101', '100', '是否开启记录日志', '2', '1:是  2:否', '1', '9');
INSERT INTO "blade_parameter" VALUES ('2', '123', '100', '测试', '1', '测试参数', '5', '1');
COMMIT;

-- ----------------------------
-- Table structure for blade_relation
-- ----------------------------
DROP TABLE IF EXISTS "blade_relation";
CREATE TABLE "blade_relation" (
"id" int4 DEFAULT nextval('blade_relation_id_seq'::regclass) NOT NULL,
"menuid" int4,
"roleid" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_relation
-- ----------------------------
BEGIN;
INSERT INTO "blade_relation" VALUES ('1', '1', '5');
INSERT INTO "blade_relation" VALUES ('2', '39', '5');
INSERT INTO "blade_relation" VALUES ('3', '40', '5');
INSERT INTO "blade_relation" VALUES ('4', '41', '5');
INSERT INTO "blade_relation" VALUES ('5', '42', '5');
INSERT INTO "blade_relation" VALUES ('6', '43', '5');
INSERT INTO "blade_relation" VALUES ('7', '73', '2');
INSERT INTO "blade_relation" VALUES ('8', '74', '2');
INSERT INTO "blade_relation" VALUES ('9', '75', '2');
INSERT INTO "blade_relation" VALUES ('10', '76', '2');
INSERT INTO "blade_relation" VALUES ('11', '77', '2');
INSERT INTO "blade_relation" VALUES ('12', '78', '2');
INSERT INTO "blade_relation" VALUES ('13', '79', '2');
INSERT INTO "blade_relation" VALUES ('14', '80', '2');
INSERT INTO "blade_relation" VALUES ('15', '81', '2');
INSERT INTO "blade_relation" VALUES ('16', '82', '2');
INSERT INTO "blade_relation" VALUES ('17', '83', '2');
INSERT INTO "blade_relation" VALUES ('18', '84', '2');
INSERT INTO "blade_relation" VALUES ('19', '85', '2');
INSERT INTO "blade_relation" VALUES ('20', '86', '2');
INSERT INTO "blade_relation" VALUES ('21', '87', '2');
INSERT INTO "blade_relation" VALUES ('22', '88', '2');
INSERT INTO "blade_relation" VALUES ('23', '89', '2');
INSERT INTO "blade_relation" VALUES ('24', '90', '2');
INSERT INTO "blade_relation" VALUES ('25', '91', '2');
INSERT INTO "blade_relation" VALUES ('26', '92', '2');
INSERT INTO "blade_relation" VALUES ('27', '93', '2');
INSERT INTO "blade_relation" VALUES ('28', '94', '2');
INSERT INTO "blade_relation" VALUES ('29', '95', '2');
INSERT INTO "blade_relation" VALUES ('30', '96', '2');
INSERT INTO "blade_relation" VALUES ('31', '97', '2');
INSERT INTO "blade_relation" VALUES ('32', '98', '2');
INSERT INTO "blade_relation" VALUES ('33', '99', '2');
INSERT INTO "blade_relation" VALUES ('34', '100', '2');
INSERT INTO "blade_relation" VALUES ('35', '101', '2');
INSERT INTO "blade_relation" VALUES ('36', '102', '2');
INSERT INTO "blade_relation" VALUES ('37', '1', '2');
INSERT INTO "blade_relation" VALUES ('38', '8', '2');
INSERT INTO "blade_relation" VALUES ('39', '9', '2');
INSERT INTO "blade_relation" VALUES ('40', '10', '2');
INSERT INTO "blade_relation" VALUES ('41', '11', '2');
INSERT INTO "blade_relation" VALUES ('42', '12', '2');
INSERT INTO "blade_relation" VALUES ('43', '13', '2');
INSERT INTO "blade_relation" VALUES ('44', '14', '2');
INSERT INTO "blade_relation" VALUES ('45', '15', '2');
INSERT INTO "blade_relation" VALUES ('46', '16', '2');
INSERT INTO "blade_relation" VALUES ('47', '17', '2');
INSERT INTO "blade_relation" VALUES ('48', '18', '2');
INSERT INTO "blade_relation" VALUES ('49', '19', '2');
INSERT INTO "blade_relation" VALUES ('50', '20', '2');
INSERT INTO "blade_relation" VALUES ('51', '21', '2');
INSERT INTO "blade_relation" VALUES ('52', '22', '2');
INSERT INTO "blade_relation" VALUES ('53', '23', '2');
INSERT INTO "blade_relation" VALUES ('54', '24', '2');
INSERT INTO "blade_relation" VALUES ('55', '2', '2');
INSERT INTO "blade_relation" VALUES ('56', '3', '2');
INSERT INTO "blade_relation" VALUES ('57', '4', '2');
INSERT INTO "blade_relation" VALUES ('58', '5', '2');
INSERT INTO "blade_relation" VALUES ('59', '6', '2');
INSERT INTO "blade_relation" VALUES ('60', '7', '2');
INSERT INTO "blade_relation" VALUES ('61', '25', '2');
INSERT INTO "blade_relation" VALUES ('62', '26', '2');
INSERT INTO "blade_relation" VALUES ('63', '27', '2');
INSERT INTO "blade_relation" VALUES ('64', '28', '2');
INSERT INTO "blade_relation" VALUES ('65', '29', '2');
INSERT INTO "blade_relation" VALUES ('66', '30', '2');
INSERT INTO "blade_relation" VALUES ('67', '31', '2');
INSERT INTO "blade_relation" VALUES ('68', '32', '2');
INSERT INTO "blade_relation" VALUES ('69', '33', '2');
INSERT INTO "blade_relation" VALUES ('70', '34', '2');
INSERT INTO "blade_relation" VALUES ('71', '35', '2');
INSERT INTO "blade_relation" VALUES ('72', '36', '2');
INSERT INTO "blade_relation" VALUES ('73', '37', '2');
INSERT INTO "blade_relation" VALUES ('74', '38', '2');
INSERT INTO "blade_relation" VALUES ('75', '39', '2');
INSERT INTO "blade_relation" VALUES ('76', '40', '2');
INSERT INTO "blade_relation" VALUES ('77', '41', '2');
INSERT INTO "blade_relation" VALUES ('78', '42', '2');
INSERT INTO "blade_relation" VALUES ('79', '43', '2');
INSERT INTO "blade_relation" VALUES ('80', '44', '2');
INSERT INTO "blade_relation" VALUES ('81', '45', '2');
INSERT INTO "blade_relation" VALUES ('82', '46', '2');
INSERT INTO "blade_relation" VALUES ('83', '47', '2');
INSERT INTO "blade_relation" VALUES ('84', '48', '2');
INSERT INTO "blade_relation" VALUES ('85', '49', '2');
INSERT INTO "blade_relation" VALUES ('86', '1', '25');
INSERT INTO "blade_relation" VALUES ('87', '62', '25');
INSERT INTO "blade_relation" VALUES ('88', '64', '25');
INSERT INTO "blade_relation" VALUES ('89', '72', '25');
INSERT INTO "blade_relation" VALUES ('90', '73', '25');
INSERT INTO "blade_relation" VALUES ('91', '74', '25');
INSERT INTO "blade_relation" VALUES ('92', '75', '25');
INSERT INTO "blade_relation" VALUES ('93', '76', '25');
INSERT INTO "blade_relation" VALUES ('94', '77', '25');
INSERT INTO "blade_relation" VALUES ('95', '78', '25');
INSERT INTO "blade_relation" VALUES ('96', '79', '25');
INSERT INTO "blade_relation" VALUES ('97', '80', '25');
INSERT INTO "blade_relation" VALUES ('98', '92', '1');
INSERT INTO "blade_relation" VALUES ('99', '93', '1');
INSERT INTO "blade_relation" VALUES ('100', '94', '1');
INSERT INTO "blade_relation" VALUES ('101', '95', '1');
INSERT INTO "blade_relation" VALUES ('102', '96', '1');
INSERT INTO "blade_relation" VALUES ('103', '97', '1');
INSERT INTO "blade_relation" VALUES ('104', '98', '1');
INSERT INTO "blade_relation" VALUES ('105', '99', '1');
INSERT INTO "blade_relation" VALUES ('106', '100', '1');
INSERT INTO "blade_relation" VALUES ('107', '101', '1');
INSERT INTO "blade_relation" VALUES ('108', '102', '1');
INSERT INTO "blade_relation" VALUES ('109', '103', '1');
INSERT INTO "blade_relation" VALUES ('110', '104', '1');
INSERT INTO "blade_relation" VALUES ('1906', '105', '1');
INSERT INTO "blade_relation" VALUES ('1907', '106', '1');
INSERT INTO "blade_relation" VALUES ('1908', '107', '1');
INSERT INTO "blade_relation" VALUES ('1909', '225', '1');
INSERT INTO "blade_relation" VALUES ('1910', '1', '1');
INSERT INTO "blade_relation" VALUES ('1911', '8', '1');
INSERT INTO "blade_relation" VALUES ('1912', '9', '1');
INSERT INTO "blade_relation" VALUES ('1913', '10', '1');
INSERT INTO "blade_relation" VALUES ('1914', '11', '1');
INSERT INTO "blade_relation" VALUES ('1915', '12', '1');
INSERT INTO "blade_relation" VALUES ('1916', '13', '1');
INSERT INTO "blade_relation" VALUES ('1917', '14', '1');
INSERT INTO "blade_relation" VALUES ('1918', '15', '1');
INSERT INTO "blade_relation" VALUES ('1919', '16', '1');
INSERT INTO "blade_relation" VALUES ('1920', '17', '1');
INSERT INTO "blade_relation" VALUES ('1921', '18', '1');
INSERT INTO "blade_relation" VALUES ('1922', '19', '1');
INSERT INTO "blade_relation" VALUES ('1923', '20', '1');
INSERT INTO "blade_relation" VALUES ('1924', '21', '1');
INSERT INTO "blade_relation" VALUES ('1925', '22', '1');
INSERT INTO "blade_relation" VALUES ('1926', '23', '1');
INSERT INTO "blade_relation" VALUES ('1927', '24', '1');
INSERT INTO "blade_relation" VALUES ('1928', '2', '1');
INSERT INTO "blade_relation" VALUES ('1929', '3', '1');
INSERT INTO "blade_relation" VALUES ('1930', '4', '1');
INSERT INTO "blade_relation" VALUES ('1931', '5', '1');
INSERT INTO "blade_relation" VALUES ('1932', '6', '1');
INSERT INTO "blade_relation" VALUES ('1933', '7', '1');
INSERT INTO "blade_relation" VALUES ('1934', '25', '1');
INSERT INTO "blade_relation" VALUES ('1935', '26', '1');
INSERT INTO "blade_relation" VALUES ('1936', '27', '1');
INSERT INTO "blade_relation" VALUES ('1937', '28', '1');
INSERT INTO "blade_relation" VALUES ('1938', '29', '1');
INSERT INTO "blade_relation" VALUES ('1939', '30', '1');
INSERT INTO "blade_relation" VALUES ('1940', '31', '1');
INSERT INTO "blade_relation" VALUES ('1941', '32', '1');
INSERT INTO "blade_relation" VALUES ('1942', '33', '1');
INSERT INTO "blade_relation" VALUES ('1943', '34', '1');
INSERT INTO "blade_relation" VALUES ('1944', '35', '1');
INSERT INTO "blade_relation" VALUES ('1945', '36', '1');
INSERT INTO "blade_relation" VALUES ('1946', '37', '1');
INSERT INTO "blade_relation" VALUES ('1947', '38', '1');
INSERT INTO "blade_relation" VALUES ('1948', '39', '1');
INSERT INTO "blade_relation" VALUES ('1949', '40', '1');
INSERT INTO "blade_relation" VALUES ('1950', '41', '1');
INSERT INTO "blade_relation" VALUES ('1951', '42', '1');
INSERT INTO "blade_relation" VALUES ('1952', '43', '1');
INSERT INTO "blade_relation" VALUES ('1953', '44', '1');
INSERT INTO "blade_relation" VALUES ('1954', '45', '1');
INSERT INTO "blade_relation" VALUES ('1955', '46', '1');
INSERT INTO "blade_relation" VALUES ('1956', '47', '1');
INSERT INTO "blade_relation" VALUES ('1957', '48', '1');
INSERT INTO "blade_relation" VALUES ('1958', '49', '1');
INSERT INTO "blade_relation" VALUES ('1959', '56', '1');
INSERT INTO "blade_relation" VALUES ('1960', '57', '1');
INSERT INTO "blade_relation" VALUES ('1961', '58', '1');
INSERT INTO "blade_relation" VALUES ('1962', '59', '1');
INSERT INTO "blade_relation" VALUES ('1963', '60', '1');
INSERT INTO "blade_relation" VALUES ('1964', '61', '1');
INSERT INTO "blade_relation" VALUES ('1965', '62', '1');
INSERT INTO "blade_relation" VALUES ('1966', '63', '1');
INSERT INTO "blade_relation" VALUES ('1967', '64', '1');
INSERT INTO "blade_relation" VALUES ('1968', '65', '1');
INSERT INTO "blade_relation" VALUES ('1969', '81', '1');
INSERT INTO "blade_relation" VALUES ('1970', '82', '1');
INSERT INTO "blade_relation" VALUES ('1971', '84', '1');
INSERT INTO "blade_relation" VALUES ('1972', '85', '1');
INSERT INTO "blade_relation" VALUES ('1973', '86', '1');
INSERT INTO "blade_relation" VALUES ('1974', '87', '1');
INSERT INTO "blade_relation" VALUES ('1975', '83', '1');
INSERT INTO "blade_relation" VALUES ('1976', '88', '1');
INSERT INTO "blade_relation" VALUES ('1977', '89', '1');
INSERT INTO "blade_relation" VALUES ('1978', '90', '1');
INSERT INTO "blade_relation" VALUES ('1979', '91', '1');
INSERT INTO "blade_relation" VALUES ('1998', '1', '4');
INSERT INTO "blade_relation" VALUES ('1999', '2', '4');
INSERT INTO "blade_relation" VALUES ('2000', '3', '4');
INSERT INTO "blade_relation" VALUES ('2001', '4', '4');
INSERT INTO "blade_relation" VALUES ('2002', '5', '4');
INSERT INTO "blade_relation" VALUES ('2003', '6', '4');
INSERT INTO "blade_relation" VALUES ('2004', '7', '4');
INSERT INTO "blade_relation" VALUES ('2005', '39', '4');
INSERT INTO "blade_relation" VALUES ('2006', '40', '4');
INSERT INTO "blade_relation" VALUES ('2007', '41', '4');
INSERT INTO "blade_relation" VALUES ('2008', '42', '4');
INSERT INTO "blade_relation" VALUES ('2009', '43', '4');
INSERT INTO "blade_relation" VALUES ('2010', '81', '4');
INSERT INTO "blade_relation" VALUES ('2011', '82', '4');
INSERT INTO "blade_relation" VALUES ('2012', '84', '4');
INSERT INTO "blade_relation" VALUES ('2013', '85', '4');
INSERT INTO "blade_relation" VALUES ('2014', '86', '4');
INSERT INTO "blade_relation" VALUES ('2015', '87', '4');
INSERT INTO "blade_relation" VALUES ('2016', '83', '4');
INSERT INTO "blade_relation" VALUES ('2017', '88', '4');
INSERT INTO "blade_relation" VALUES ('2018', '89', '4');
INSERT INTO "blade_relation" VALUES ('2019', '90', '4');
INSERT INTO "blade_relation" VALUES ('2020', '91', '4');
INSERT INTO "blade_relation" VALUES ('2075', '1', '6');
INSERT INTO "blade_relation" VALUES ('2076', '81', '6');
INSERT INTO "blade_relation" VALUES ('2077', '82', '6');
INSERT INTO "blade_relation" VALUES ('2078', '84', '6');
INSERT INTO "blade_relation" VALUES ('2079', '85', '6');
INSERT INTO "blade_relation" VALUES ('2080', '86', '6');
INSERT INTO "blade_relation" VALUES ('2081', '87', '6');
INSERT INTO "blade_relation" VALUES ('2082', '83', '6');
INSERT INTO "blade_relation" VALUES ('2083', '88', '6');
INSERT INTO "blade_relation" VALUES ('2084', '89', '6');
INSERT INTO "blade_relation" VALUES ('2085', '90', '6');
INSERT INTO "blade_relation" VALUES ('2086', '91', '6');
INSERT INTO "blade_relation" VALUES ('2090', '1', '3');
INSERT INTO "blade_relation" VALUES ('2091', '56', '3');
INSERT INTO "blade_relation" VALUES ('2092', '61', '3');
INSERT INTO "blade_relation" VALUES ('2093', '62', '3');
INSERT INTO "blade_relation" VALUES ('2094', '64', '3');
COMMIT;

-- ----------------------------
-- Table structure for blade_role
-- ----------------------------
DROP TABLE IF EXISTS "blade_role";
CREATE TABLE "blade_role" (
"id" int4 DEFAULT nextval('blade_role_id_seq'::regclass) NOT NULL,
"num" int4,
"pid" int4,
"name" varchar(255) COLLATE "default",
"deptid" int4,
"tips" varchar(255) COLLATE "default",
"version" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_role
-- ----------------------------
BEGIN;
INSERT INTO "blade_role" VALUES ('1', '1', '0', '超级管理员', '1', 'administrator', '1');
INSERT INTO "blade_role" VALUES ('2', '1', '1', '管理员', '7', 'admin', '5');
INSERT INTO "blade_role" VALUES ('3', '2', '1', '管理员1', '10', 'admin', '2');
INSERT INTO "blade_role" VALUES ('4', '2', '0', '测试', '10', 'test', '1');
INSERT INTO "blade_role" VALUES ('5', '1', '4', '测试1', '3', 'test', '4');
INSERT INTO "blade_role" VALUES ('6', '2', '4', '测试2', '10', 'test', '1');
INSERT INTO "blade_role" VALUES ('7', '3', '4', '测试3', '10', 'test', null);
COMMIT;

-- ----------------------------
-- Table structure for blade_role_ext
-- ----------------------------
DROP TABLE IF EXISTS "blade_role_ext";
CREATE TABLE "blade_role_ext" (
"id" int4 DEFAULT nextval('blade_role_ext_id_seq'::regclass) NOT NULL,
"userid" int4,
"rolein" text COLLATE "default",
"roleout" text COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_role_ext
-- ----------------------------
BEGIN;
INSERT INTO "blade_role_ext" VALUES ('1', '22', '92,93,94,95,96,97', '2,3,4,5,6,7');
INSERT INTO "blade_role_ext" VALUES ('2', '66', '1,44,49', '45');
INSERT INTO "blade_role_ext" VALUES ('3', '2', '1,34,35,36,37,38', '8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,44,45,46,47,48,49');
INSERT INTO "blade_role_ext" VALUES ('4', '63', '0', '0');
INSERT INTO "blade_role_ext" VALUES ('5', '72', '0', '0');
INSERT INTO "blade_role_ext" VALUES ('6', '74', '0', '0');
INSERT INTO "blade_role_ext" VALUES ('7', '1', '0', '0');
INSERT INTO "blade_role_ext" VALUES ('8', '168', '92,103,104,105,106,107', '109,110,111,112,113,114,115,116,117,118,119,120,121,122');
INSERT INTO "blade_role_ext" VALUES ('9', '189', '108,109,110,111,112,113,114,115,116,117,118,119,120,121,122', '0');
INSERT INTO "blade_role_ext" VALUES ('10', '21', '92,98,99,100,101,102,1,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,39,40,41,42,43', '0');
COMMIT;

-- ----------------------------
-- Table structure for blade_user
-- ----------------------------
DROP TABLE IF EXISTS "blade_user";
CREATE TABLE "blade_user" (
"id" int4 DEFAULT nextval('blade_user_id_seq'::regclass) NOT NULL,
"account" varchar(45) COLLATE "default",
"password" varchar(45) COLLATE "default",
"salt" varchar(45) COLLATE "default",
"name" varchar(45) COLLATE "default",
"birthday" timestamp(0),
"sex" int4,
"email" varchar(45) COLLATE "default",
"phone" varchar(45) COLLATE "default",
"roleid" varchar(255) COLLATE "default",
"deptid" int4,
"status" int4,
"createtime" timestamp(0),
"version" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of blade_user
-- ----------------------------
BEGIN;
INSERT INTO "blade_user" VALUES ('1', 'admin', '4779e4a9903dfb08f9f877791c516a73', 'admin', '管理员', '2015-09-08 00:00:00', '1', 'admin@tonbusoft.com.cn', '22233322', '1', '9', '1', '2016-01-29 08:49:53', '27');
INSERT INTO "blade_user" VALUES ('2', '1231233', '0e6d70b68d86446e9d2464d13a85aff3', 'd51t4', '123123', '2017-01-16 00:00:00', '1', ' ', ' ', '3', '2', '5', '2017-01-11 15:12:11', '1');
INSERT INTO "blade_user" VALUES ('3', 'test001', 'e334680512284cac2f57701abe03af96', 'r4i90', 'test', '2016-02-19 14:00:13', '1', ' ', '123123', '5', '3', '1', '2016-02-19 14:00:19', '27');
INSERT INTO "blade_user" VALUES ('4', '123123', '653f21c93acdd4f03c95876824f440a7', '048wh', '213123', '2016-05-03 00:00:00', '1', '1232', '1232', '4,5', '1', '1', '2016-05-17 18:50:15', '3');
COMMIT;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "blade_notice_id_seq" OWNED BY "blade_notice"."id";
ALTER SEQUENCE "blade_attach_id_seq" OWNED BY "blade_attach"."id";
ALTER SEQUENCE "blade_dept_id_seq" OWNED BY "blade_dept"."id";
ALTER SEQUENCE "blade_dict_id_seq" OWNED BY "blade_dict"."id";
ALTER SEQUENCE "blade_generate_id_seq" OWNED BY "blade_generate"."id";
ALTER SEQUENCE "blade_login_log_id_seq" OWNED BY "blade_login_log"."id";
ALTER SEQUENCE "blade_menu_id_seq" OWNED BY "blade_menu"."id";
ALTER SEQUENCE "blade_operation_log_id_seq" OWNED BY "blade_operation_log"."id";
ALTER SEQUENCE "blade_parameter_id_seq" OWNED BY "blade_parameter"."id";
ALTER SEQUENCE "blade_relation_id_seq" OWNED BY "blade_relation"."id";
ALTER SEQUENCE "blade_role_ext_id_seq" OWNED BY "blade_role_ext"."id";
ALTER SEQUENCE "blade_role_id_seq" OWNED BY "blade_role"."id";
ALTER SEQUENCE "blade_user_id_seq" OWNED BY "blade_user"."id";

-- ----------------------------
-- Primary Key structure for table blade_attach
-- ----------------------------
ALTER TABLE "blade_attach" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_dept
-- ----------------------------
ALTER TABLE "blade_dept" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_dict
-- ----------------------------
ALTER TABLE "blade_dict" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_generate
-- ----------------------------
ALTER TABLE "blade_generate" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_login_log
-- ----------------------------
ALTER TABLE "blade_login_log" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_menu
-- ----------------------------
ALTER TABLE "blade_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_notice
-- ----------------------------
ALTER TABLE "blade_notice" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_operation_log
-- ----------------------------
ALTER TABLE "blade_operation_log" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_parameter
-- ----------------------------
ALTER TABLE "blade_parameter" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_relation
-- ----------------------------
ALTER TABLE "blade_relation" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_role
-- ----------------------------
ALTER TABLE "blade_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_role_ext
-- ----------------------------
ALTER TABLE "blade_role_ext" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table blade_user
-- ----------------------------
ALTER TABLE "blade_user" ADD PRIMARY KEY ("id");
