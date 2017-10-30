-- ----------------------------
-- 
--
--
--创建数据库
--
--
--
-- ----------------------------
USE master
IF EXISTS (
	SELECT
		*
	FROM
		sysdatabases
	WHERE
		name = 'hrp-sms'
) 

DROP DATABASE [hrp-sms]

GO

CREATE DATABASE [hrp-sms]
ON
(
    NAME = hrpsms,
    FILENAME = 'D:\hrp-sms_data.mdf',
	SIZE = 20MB,
	MAXSIZE = UNLIMITED,
	FILEGROWTH = 10%
)
LOG ON 
(
    NAME = hrpsms_data,
    FILENAME = 'D:\hrp-sms_log.ldf',
	SIZE = 20MB,
	MAXSIZE = UNLIMITED,
	FILEGROWTH = 10%
);

GO
-- ----------------------------
-- 
--
--
--创建表结构和表字段
--
--
--
-- ----------------------------
USE [hrp-sms]

-- ----------------------------
-- Table structure for t_sms_accessControl
-- ----------------------------
if object_id(N't_sms_accessControl',N'U') is not null
DROP TABLE [t_sms_accessControl]
GO
CREATE TABLE [t_sms_accessControl] (
[objectType] int NOT NULL ,
[objectId] int NOT NULL ,
[roleId] varchar(50) NOT NULL ,
[accessMask] int NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_accessControl', 
'COLUMN', N'objectType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'objectType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'objectType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_accessControl', 
'COLUMN', N'objectId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'objectId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'objectId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_accessControl', 
'COLUMN', N'roleId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'roleId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'roleId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_accessControl', 
'COLUMN', N'accessMask')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限掩码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'accessMask'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限掩码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_accessControl'
, @level2type = 'COLUMN', @level2name = N'accessMask'
GO

-- ----------------------------
-- Table structure for t_sms_approved_supplier
-- ----------------------------
if object_id(N't_sms_approved_supplier',N'U') is not null
DROP TABLE [t_sms_approved_supplier]
GO
CREATE TABLE [t_sms_approved_supplier] (
[id] varchar(50) NOT NULL ,
[supplier] varchar(50) NULL ,
[materialItem] varchar(50) NULL ,
[isStopped] tinyint NULL ,
[measureUnit] varchar(44) NULL ,
[supplierRate] decimal(20,10) NULL ,
[taxPrice] decimal(20,10) NULL ,
[effectualDate] date NULL ,
[uneffectualDate] date NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[review] tinyint NULL DEFAULT ((0)) ,
[purMeasureUnit] varchar(44) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'materialItem')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联物料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'materialItem'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联物料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'materialItem'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'isStopped')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否停用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'isStopped'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否停用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'isStopped'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'measureUnit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'基本计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'measureUnit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'基本计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'measureUnit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'supplierRate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供货比例'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'supplierRate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供货比例'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'supplierRate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'taxPrice')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'含税价格'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'taxPrice'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'含税价格'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'taxPrice'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'effectualDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生效日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'effectualDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生效日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'effectualDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'uneffectualDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'失效日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'uneffectualDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'失效日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'uneffectualDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（1.未编辑，2.新增，3.已编辑）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（1.未编辑，2.新增，3.已编辑）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'review'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_approved_supplier', 
'COLUMN', N'purMeasureUnit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'purMeasureUnit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_approved_supplier'
, @level2type = 'COLUMN', @level2name = N'purMeasureUnit'
GO

-- ----------------------------
-- Table structure for t_sms_base_status
-- ----------------------------
if object_id(N't_sms_base_status',N'U') is not null
DROP TABLE [t_sms_base_status]
GO
CREATE TABLE [t_sms_base_status] (
[id] int NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_category
-- ----------------------------
if object_id(N't_sms_category',N'U') is not null
DROP TABLE [t_sms_category]
GO
CREATE TABLE [t_sms_category] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_category', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分类'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分类'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_category', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分类ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分类ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_category', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分类名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分类名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_category'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_certificate
-- ----------------------------
if object_id(N't_sms_certificate',N'U') is not null
DROP TABLE [t_sms_certificate]
GO
CREATE TABLE [t_sms_certificate] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_certificate', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_certificate', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_certificate', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证书名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证书名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_certificate'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_city
-- ----------------------------
if object_id(N't_sms_city',N'U') is not null
DROP TABLE [t_sms_city]
GO
CREATE TABLE [t_sms_city] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_country
-- ----------------------------
if object_id(N't_sms_country',N'U') is not null
DROP TABLE [t_sms_country]
GO
CREATE TABLE [t_sms_country] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_county
-- ----------------------------
if object_id(N't_sms_county',N'U') is not null
DROP TABLE [t_sms_county]
GO
CREATE TABLE [t_sms_county] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_currency
-- ----------------------------
if object_id(N't_sms_currency',N'U') is not null
DROP TABLE [t_sms_currency]
GO
CREATE TABLE [t_sms_currency] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_currency', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'币别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'币别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_currency', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'币种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'币种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_currency', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'币种名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'币种名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_currency'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_dataFlowSubClass
-- ----------------------------
if object_id(N't_sms_dataFlowSubClass',N'U') is not null
DROP TABLE [t_sms_dataFlowSubClass]
GO
CREATE TABLE [t_sms_dataFlowSubClass] (
[topClassId] int NOT NULL ,
[subSysId] int NOT NULL ,
[name] varchar(255) NOT NULL ,
[index] int NOT NULL ,
[visible] tinyint NULL ,
[url] varchar(500) NULL ,
[icon] varchar(100) NULL ,
[ownerType] int NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'topClassId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上级模块ID(关联t_DataFlowTopClass中topClassId)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'topClassId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上级模块ID(关联t_DataFlowTopClass中topClassId)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'topClassId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'subSysId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'子模块ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'subSysId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'子模块ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'subSysId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'子模块名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'子模块名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'子模块排序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'子模块排序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'index'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'visible')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'明细菜单是否可见：
设置为0：系统用户不可见,供应商用户不可见
设置为1：系统用户可见,供应商用户不可见
设置为2：系统用户不可见,供应商用户可见
设置为3：系统用户可见,供应商用户可见
'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'visible'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'明细菜单是否可见：
设置为0：系统用户不可见,供应商用户不可见
设置为1：系统用户可见,供应商用户不可见
设置为2：系统用户不可见,供应商用户可见
设置为3：系统用户可见,供应商用户可见
'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'visible'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'url')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'子系统网页链接地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'url'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'子系统网页链接地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'url'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'icon')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'title图标地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'icon'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'title图标地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'icon'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowSubClass', 
'COLUMN', N'ownerType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户类别，使用该功能的用户(平台用户，供应链用户，两者都可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'ownerType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户类别，使用该功能的用户(平台用户，供应链用户，两者都可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowSubClass'
, @level2type = 'COLUMN', @level2name = N'ownerType'
GO

-- ----------------------------
-- Table structure for t_sms_dataFlowTopClass
-- ----------------------------
if object_id(N't_sms_dataFlowTopClass',N'U') is not null
DROP TABLE [t_sms_dataFlowTopClass]
GO
CREATE TABLE [t_sms_dataFlowTopClass] (
[topClassId] int NOT NULL ,
[topClassName] varchar(50) NOT NULL ,
[index] int NOT NULL ,
[visible] bit NOT NULL ,
[icon] varchar(100) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowTopClass', 
'COLUMN', N'topClassId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'模块ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'topClassId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'模块ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'topClassId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowTopClass', 
'COLUMN', N'topClassName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'模块名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'topClassName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'模块名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'topClassName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowTopClass', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'排序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'排序号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'index'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowTopClass', 
'COLUMN', N'visible')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否可见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'visible'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否可见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'visible'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_dataFlowTopClass', 
'COLUMN', N'icon')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'菜单图标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'icon'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'菜单图标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_dataFlowTopClass'
, @level2type = 'COLUMN', @level2name = N'icon'
GO

-- ----------------------------
-- Table structure for t_sms_employee
-- ----------------------------
if object_id(N't_sms_employee',N'U') is not null
DROP TABLE [t_sms_employee]
GO
CREATE TABLE [t_sms_employee] (
[id] varchar(255) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(30) NULL ,
[mobile] varchar(20) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_employee', 
'COLUMN', N'mobile')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'职员手机号码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_employee'
, @level2type = 'COLUMN', @level2name = N'mobile'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'职员手机号码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_employee'
, @level2type = 'COLUMN', @level2name = N'mobile'
GO

-- ----------------------------
-- Table structure for t_sms_formClass
-- ----------------------------
if object_id(N't_sms_formClass',N'U') is not null
DROP TABLE [t_sms_formClass]
GO
CREATE TABLE [t_sms_formClass] (
[classId] int NOT NULL ,
[name] varchar(50) NOT NULL ,
[tableName] varchar(50) NOT NULL ,
[primaryKey] varchar(20) NOT NULL ,
[bosType] varchar(8) NOT NULL DEFAULT '' 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formClass', 
'COLUMN', N'classId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'业务类别-唯一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'classId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'业务类别-唯一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'classId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formClass', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'业务类别名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'业务类别名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formClass', 
'COLUMN', N'tableName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物理存储表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'tableName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物理存储表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'tableName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formClass', 
'COLUMN', N'primaryKey')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'primaryKey'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'primaryKey'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formClass', 
'COLUMN', N'bosType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'唯一标识单据类别(参考EAS设计)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'bosType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'唯一标识单据类别(参考EAS设计)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formClass'
, @level2type = 'COLUMN', @level2name = N'bosType'
GO

-- ----------------------------
-- Table structure for t_sms_formEntries
-- ----------------------------
if object_id(N't_sms_formEntries',N'U') is not null
DROP TABLE [t_sms_formEntries]
GO
CREATE TABLE [t_sms_formEntries] (
[classId] int NOT NULL ,
[entryIndex] int NOT NULL ,
[tableName] varchar(50) NOT NULL ,
[foreignKey] varchar(20) NOT NULL ,
[primaryKey] varchar(20) NOT NULL ,
[bosType] varchar(8) NOT NULL DEFAULT '' ,
[joinType] varchar(20) NOT NULL DEFAULT '' 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'classId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'与formClass主表classId一致'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'classId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'与formClass主表classId一致'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'classId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'entryIndex')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'子表序号(从1开始)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'entryIndex'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'子表序号(从1开始)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'entryIndex'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'tableName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'表名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'tableName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'表名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'tableName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'foreignKey')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联主表字段'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'foreignKey'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联主表字段'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'foreignKey'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'primaryKey')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'primaryKey'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'primaryKey'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'bosType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'唯一标识单据类别(参考EAS设计)设置成与主表bosType一致'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'bosType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'唯一标识单据类别(参考EAS设计)设置成与主表bosType一致'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'bosType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formEntries', 
'COLUMN', N'joinType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'与主表的连接关系(默认 INNER JOIN)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'joinType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'与主表的连接关系(默认 INNER JOIN)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formEntries'
, @level2type = 'COLUMN', @level2name = N'joinType'
GO

-- ----------------------------
-- Table structure for t_sms_formFields
-- ----------------------------
if object_id(N't_sms_formFields',N'U') is not null
DROP TABLE [t_sms_formFields]
GO
CREATE TABLE [t_sms_formFields] (
[classId] int NOT NULL ,
[page] int NOT NULL DEFAULT ((0)) ,
[name] varchar(50) NOT NULL ,
[sqlColumnName] varchar(30) NULL ,
[key] varchar(30) NOT NULL DEFAULT '' ,
[dataType] int NULL DEFAULT NULL ,
[ctrlType] int NULL DEFAULT NULL ,
[ctlIndex] int NULL DEFAULT NULL ,
[index] int NULL DEFAULT ((0)) ,
[display] int NULL DEFAULT ((1)) ,
[showWidth] int NULL DEFAULT ((80)) ,
[lookUpType] int NULL DEFAULT ((0)) ,
[lookUpClassID] int NULL DEFAULT ((0)) ,
[srcTable] varchar(50) NULL DEFAULT '' ,
[srcTableAlisAs] varchar(30) NULL DEFAULT NULL ,
[srcField] varchar(30) NULL DEFAULT '' ,
[disPlayField] varchar(30) NULL DEFAULT NULL ,
[disPlayNum] varchar(30) NULL DEFAULT NULL ,
[joinType] varchar(20) NULL DEFAULT '' ,
[filter] varchar(500) NULL DEFAULT '' ,
[defaultValue] varchar(255) NULL DEFAULT NULL ,
[maxValue] numeric(10,2) NULL ,
[minValue] numeric(10,2) NULL ,
[mustInput] int NULL DEFAULT ((0)) ,
[length] int NULL DEFAULT NULL ,
[lock] int NULL DEFAULT ((0)) ,
[isCondition] int NULL DEFAULT ((0)) ,
[isCount] int NULL DEFAULT ((0)) ,
[needSave] int NULL DEFAULT ((1)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'classId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'业务类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'classId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'业务类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'classId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'page')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'标示模板字段所在的页面（0是表头，1是第一个子表，2是第二个子表，以此类推...）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'page'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'标示模板字段所在的页面（0是表头，1是第一个子表，2是第二个子表，以此类推...）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'page'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段名(显示的字段名)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段名(显示的字段名)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'sqlColumnName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物理表中的字段名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'sqlColumnName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物理表中的字段名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'sqlColumnName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'key')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段唯一标示(用于关联表显示字段名与本表字段名同名的情况，在一个formClass中FKey是唯一的)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'key'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段唯一标示(用于关联表显示字段名与本表字段名同名的情况，在一个formClass中FKey是唯一的)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'key'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'dataType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段类型(数字，文本等)1:数字2:文本3:日期4:布尔'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'dataType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段类型(数字，文本等)1:数字2:文本3:日期4:布尔'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'dataType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'ctrlType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'控件类型，指示前端展示时的特殊类型，比如多选框，基础资料选择框等,3:多选按钮6:基础资料选择框'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'ctrlType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'控件类型，指示前端展示时的特殊类型，比如多选框，基础资料选择框等,3:多选按钮6:基础资料选择框'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'ctrlType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'ctlIndex')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段的后台查询顺序，FIndex是用于前端界面展示时的tab顺序\r\nFCtlIndex用于后端查询构建查询脚本时模板的遍历顺序，处理因为可能涉及关联其他表查询，连接表时的顺序不可随意改变时产生的错误'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'ctlIndex'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段的后台查询顺序，FIndex是用于前端界面展示时的tab顺序\r\nFCtlIndex用于后端查询构建查询脚本时模板的遍历顺序，处理因为可能涉及关联其他表查询，连接表时的顺序不可随意改变时产生的错误'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'ctlIndex'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段的显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段的显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'index'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'display')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段显示性:用于根据用户类别控制字段取值。\r\n采用二进制方式配置\r\n如3表示平台用户物业用户都显示\r\n参考t_SubSysEnum,typeID=3'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'display'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段显示性:用于根据用户类别控制字段取值。\r\n采用二进制方式配置\r\n如3表示平台用户物业用户都显示\r\n参考t_SubSysEnum,typeID=3'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'display'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'showWidth')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段在前端页面显示的宽度(单位px)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'showWidth'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段在前端页面显示的宽度(单位px)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'showWidth'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'lookUpType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'标示是否是引用基础资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lookUpType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'标示是否是引用基础资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lookUpType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'lookUpClassID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'引用基础资料的类别ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lookUpClassID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'引用基础资料的类别ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lookUpClassID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'srcTable')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'引用的表名(表示此字段是要关联表查询)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcTable'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'引用的表名(表示此字段是要关联表查询)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcTable'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'srcTableAlisAs')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联表别名(用于关联多次同一张表时)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcTableAlisAs'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联表别名(用于关联多次同一张表时)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcTableAlisAs'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'srcField')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联表的字段名,当FSrcTable有值时，此字段为必填'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcField'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联表的字段名,当FSrcTable有值时，此字段为必填'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'srcField'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'disPlayField')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联表显示的字段名,当FSrcTable有值时，此字段为必填,即显示FSrcTable表的FDisPlayField字段'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'disPlayField'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联表显示的字段名,当FSrcTable有值时，此字段为必填,即显示FSrcTable表的FDisPlayField字段'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'disPlayField'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'disPlayNum')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联表显示的代码字段,当FSrcTable有值时，此字段为必填,，一般显示关联标的FNumber'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'disPlayNum'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联表显示的代码字段,当FSrcTable有值时，此字段为必填,，一般显示关联标的FNumber'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'disPlayNum'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'joinType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'表链接类型(默认 INNER JOIN)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'joinType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'表链接类型(默认 INNER JOIN)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'joinType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'filter')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'过滤条件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'filter'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'过滤条件'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'filter'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'defaultValue')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'默认值(控件初始化时候的默认值)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'defaultValue'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'默认值(控件初始化时候的默认值)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'defaultValue'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'maxValue')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'最大值(数值类型字段使用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'maxValue'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'最大值(数值类型字段使用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'maxValue'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'minValue')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'最小值(数值类型字段使用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'minValue'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'最小值(数值类型字段使用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'minValue'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'mustInput')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否必录： 采用二进制方式配置, 参考t_SubSysEnum,typeID=4'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'mustInput'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否必录： 采用二进制方式配置, 参考t_SubSysEnum,typeID=4'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'mustInput'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'length')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段长度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'length'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段长度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'length'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'lock')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'字段锁定性:表示字段页面展现的控制可编辑形式。\r\n采用二进制方式配置\r\n如3表示新增修改时都锁定\r\n参考t_SubSysEnum,typeID=2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lock'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'字段锁定性:表示字段页面展现的控制可编辑形式。\r\n采用二进制方式配置\r\n如3表示新增修改时都锁定\r\n参考t_SubSysEnum,typeID=2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'lock'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'isCondition')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否可作为过滤条件，0：否，1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'isCondition'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否可作为过滤条件，0：否，1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'isCondition'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'isCount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否统计项，0：否，1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'isCount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否统计项，0：否，1：是'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'isCount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_formFields', 
'COLUMN', N'needSave')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否需要保存到数据库(即物理表是否有字段与之对应)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'needSave'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否需要保存到数据库(即物理表是否有字段与之对应)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_formFields'
, @level2type = 'COLUMN', @level2name = N'needSave'
GO

-- ----------------------------
-- Table structure for t_sms_industry
-- ----------------------------
if object_id(N't_sms_industry',N'U') is not null
DROP TABLE [t_sms_industry]
GO
CREATE TABLE [t_sms_industry] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_industry', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行业'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行业'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_industry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行业ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行业ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_industry', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行业名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行业名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_industry'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_item
-- ----------------------------
if object_id(N't_sms_item',N'U') is not null
DROP TABLE [t_sms_item]
GO
CREATE TABLE [t_sms_item] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL ,
[specification] varchar(255) NULL ,
[highConsumable] tinyint NULL ,
[isLotNumber] tinyint NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
'COLUMN', N'specification')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'规格型号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'specification'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'规格型号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'specification'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
'COLUMN', N'highConsumable')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否高值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'highConsumable'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否高值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'highConsumable'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item', 
'COLUMN', N'isLotNumber')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'isLotNumber'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item'
, @level2type = 'COLUMN', @level2name = N'isLotNumber'
GO

-- ----------------------------
-- Table structure for t_sms_item_license_type
-- ----------------------------
if object_id(N't_sms_item_license_type',N'U') is not null
DROP TABLE [t_sms_item_license_type]
GO
CREATE TABLE [t_sms_item_license_type] (
[id] varchar(50) NOT NULL ,
[number] varchar(50) NULL ,
[name] varchar(50) NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[isMust] tinyint NULL ,
[isControl] tinyint NULL ,
[review] tinyint NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料证件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料证件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（1.未编辑，2.新增，3.已编辑）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（1.未编辑，2.新增，3.已编辑）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'isMust')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'isMust'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'isMust'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'isControl')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'isControl'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'isControl'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_item_license_type', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_item_license_type'
, @level2type = 'COLUMN', @level2name = N'review'
GO

-- ----------------------------
-- Table structure for t_sms_log
-- ----------------------------
if object_id(N't_sms_log',N'U') is not null
DROP TABLE [t_sms_log]
GO
CREATE TABLE [t_sms_log] (
[userName] varchar(50) NULL ,
[ip] varchar(20) NULL ,
[message] varchar(255) NULL ,
[operateTime] datetime NULL ,
[clazz] varchar(500) NULL ,
[method] varchar(50) NULL ,
[id] int NOT NULL IDENTITY(1,1) ,
[params] varchar(1000) NULL 
)


GO
DBCC CHECKIDENT(N'[t_sms_log]', RESEED, 12986)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'userName')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'userName'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'userName'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'ip')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作用户ip'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'ip'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作用户ip'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'ip'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'message')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'message'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'message'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'operateTime')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'operateTime'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'operateTime'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'clazz')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作类路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'clazz'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作类路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'clazz'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'method')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'方法名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'method'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'方法名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'method'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_log', 
'COLUMN', N'params')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'方法参数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'params'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'方法参数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_log'
, @level2type = 'COLUMN', @level2name = N'params'
GO

-- ----------------------------
-- Table structure for t_sms_msglog
-- ----------------------------
if object_id(N't_sms_msglog',N'U') is not null
DROP TABLE [t_sms_msglog]
GO
CREATE TABLE [t_sms_msglog] (
[seqid] varchar(50) NOT NULL ,
[mobiles] varchar(1000) NULL ,
[smsContent] varchar(1000) NULL ,
[restr] varchar(100) NULL ,
[sendtime] datetime NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_objectAccessType
-- ----------------------------
if object_id(N't_sms_objectAccessType',N'U') is not null
DROP TABLE [t_sms_objectAccessType]
GO
CREATE TABLE [t_sms_objectAccessType] (
[objectType] int NOT NULL ,
[objectId] int NOT NULL ,
[index] int NOT NULL ,
[name] varchar(50) NULL ,
[accessMask] int NULL ,
[accessUse] int NULL ,
[ownerType] int NULL ,
[description] varchar(100) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'objectType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'objectType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'objectType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'objectId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'objectId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'objectId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'index'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'accessMask')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限掩码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'accessMask'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限掩码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'accessMask'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'accessUse')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联权限项'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'accessUse'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联权限项'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'accessUse'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'ownerType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限用户者类别，使用该权限的用户(1平台用户，2供应商用户，3两者都可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'ownerType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限用户者类别，使用该权限的用户(1平台用户，2供应商用户，3两者都可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'ownerType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectAccessType', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectAccessType'
, @level2type = 'COLUMN', @level2name = N'description'
GO

-- ----------------------------
-- Table structure for t_sms_objectType
-- ----------------------------
if object_id(N't_sms_objectType',N'U') is not null
DROP TABLE [t_sms_objectType]
GO
CREATE TABLE [t_sms_objectType] (
[topClassId] int NOT NULL ,
[subSysId] int NOT NULL ,
[objectType] int NOT NULL ,
[objectId] int NOT NULL ,
[name] varchar(50) NULL ,
[description] varchar(100) NOT NULL ,
[classId] int NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectType', 
'COLUMN', N'objectType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'objectType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限类别(用于区分模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'objectType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectType', 
'COLUMN', N'objectId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'objectId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限ID(用于区分子模块)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'objectId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectType', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'权限名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'权限名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_objectType', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_objectType'
, @level2type = 'COLUMN', @level2name = N'description'
GO

-- ----------------------------
-- Table structure for t_sms_pay
-- ----------------------------
if object_id(N't_sms_pay',N'U') is not null
DROP TABLE [t_sms_pay]
GO
CREATE TABLE [t_sms_pay] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_pay', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_pay', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款方式ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款方式ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_pay', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款方式名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款方式名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_pay'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_paymentType
-- ----------------------------
if object_id(N't_sms_paymentType',N'U') is not null
DROP TABLE [t_sms_paymentType]
GO
CREATE TABLE [t_sms_paymentType] (
[id] varchar(255) NOT NULL ,
[name] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_plugins
-- ----------------------------
if object_id(N't_sms_plugins',N'U') is not null
DROP TABLE [t_sms_plugins]
GO
CREATE TABLE [t_sms_plugins] (
[id] int NOT NULL ,
[classId] int NOT NULL ,
[plugName] varchar(255) NOT NULL ,
[index] int NOT NULL DEFAULT ((0)) ,
[desc] varchar(255) NOT NULL DEFAULT '' 
)


GO

-- ----------------------------
-- Table structure for t_sms_province
-- ----------------------------
if object_id(N't_sms_province',N'U') is not null
DROP TABLE [t_sms_province]
GO
CREATE TABLE [t_sms_province] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_purchase_order
-- ----------------------------
if object_id(N't_sms_purchase_order',N'U') is not null
DROP TABLE [t_sms_purchase_order]
GO
CREATE TABLE [t_sms_purchase_order] (
[id] varchar(50) NOT NULL ,
[supplier] varchar(255) NULL ,
[bizDate] date NULL ,
[purchasePerson] varchar(255) NULL ,
[saleProxy] tinyint NULL ,
[isInTax] tinyint NULL ,
[tickDate] datetime NULL ,
[confirmTickDate] datetime NULL ,
[tickType] tinyint NULL ,
[confirmTick] tinyint NULL ,
[number] varchar(255) NULL ,
[isQuicken] tinyint NULL ,
[currency] varchar(255) NULL ,
[totalAmount] money NULL ,
[totalTax] money NULL ,
[totalTaxAmount] money NULL ,
[createTime] datetime NULL ,
[baseStatus] tinyint NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'订单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'订单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'bizDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'订单日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'bizDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'订单日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'bizDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'purchasePerson')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'purchasePerson'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'purchasePerson'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'saleProxy')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购模式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'saleProxy'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购模式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'saleProxy'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'isInTax')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否含税'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'isInTax'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否含税'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'isInTax'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'tickDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'HRP确认接单时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'tickDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'HRP确认接单时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'tickDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'confirmTickDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商接单时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'confirmTickDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商接单时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'confirmTickDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'tickType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'HRP确认是否接单'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'tickType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'HRP确认是否接单'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'tickType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'confirmTick')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商是否接单'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'confirmTick'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商是否接单'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'confirmTick'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'isQuicken')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否加急'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'isQuicken'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否加急'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'isQuicken'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'currency')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'币别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'currency'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'币别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'currency'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'totalAmount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalAmount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalAmount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'totalTax')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalTax'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalTax'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'totalTaxAmount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'价税合计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalTaxAmount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'价税合计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'totalTaxAmount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'createTime')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'制单日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'createTime'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'制单日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'createTime'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order', 
'COLUMN', N'baseStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
GO

-- ----------------------------
-- Table structure for t_sms_purchase_order_entry
-- ----------------------------
if object_id(N't_sms_purchase_order_entry',N'U') is not null
DROP TABLE [t_sms_purchase_order_entry]
GO
CREATE TABLE [t_sms_purchase_order_entry] (
[material] varchar(255) NULL ,
[parent] varchar(255) NULL ,
[price] money NULL ,
[qty] numeric(10,2) NULL ,
[deliveryDate] datetime NULL ,
[discountRate] float(53) NULL ,
[taxRate] float(53) NULL ,
[taxPrice] money NULL ,
[actualTaxPrice] money NULL ,
[discountAmount] money NULL ,
[tax] money NULL ,
[localAmount] money NULL ,
[seq] int NULL ,
[confirmDate] datetime NULL ,
[confirmQty] numeric(10,2) NULL ,
[unit] varchar(255) NULL ,
[id] varchar(255) NOT NULL ,
[amount] money NULL ,
[invoiceQty] numeric(10,2) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'订单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'订单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'price')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'price'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'price'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'qty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'deliveryDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'交货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'deliveryDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'交货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'deliveryDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'discountRate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'折扣率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'discountRate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'折扣率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'discountRate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'taxRate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'taxRate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'taxRate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'taxPrice')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'含税单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'taxPrice'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'含税单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'taxPrice'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'actualTaxPrice')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实际含税单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'actualTaxPrice'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实际含税单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'actualTaxPrice'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'discountAmount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'折扣额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'discountAmount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'折扣额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'discountAmount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'tax')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'tax'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'tax'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'localAmount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'本位币金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'localAmount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'本位币金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'localAmount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'seq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'confirmDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'确认时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'confirmDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'确认时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'confirmDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'confirmQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'确认数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'confirmQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'确认数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'confirmQty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'unit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'基本计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'基本计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'amount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purchase_order_entry', 
'COLUMN', N'invoiceQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发货数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'invoiceQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发货数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purchase_order_entry'
, @level2type = 'COLUMN', @level2name = N'invoiceQty'
GO

-- ----------------------------
-- Table structure for t_sms_purinwarehs
-- ----------------------------
if object_id(N't_sms_purinwarehs',N'U') is not null
DROP TABLE [t_sms_purinwarehs]
GO
CREATE TABLE [t_sms_purinwarehs] (
[id] varchar(255) NOT NULL ,
[number] varchar(255) NULL ,
[bizDate] datetime NULL ,
[baseStatus] tinyint NULL ,
[sourceBillType] varchar(255) NULL ,
[supplier] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'入库单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'入库单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'入库单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'入库单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'bizDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'入库日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'bizDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'入库日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'bizDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'baseStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'sourceBillType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO

-- ----------------------------
-- Table structure for t_sms_purinwarehs_entry
-- ----------------------------
if object_id(N't_sms_purinwarehs_entry',N'U') is not null
DROP TABLE [t_sms_purinwarehs_entry]
GO
CREATE TABLE [t_sms_purinwarehs_entry] (
[id] varchar(255) NOT NULL ,
[parent] varchar(255) NULL ,
[seq] int NULL ,
[orderId] varchar(255) NULL ,
[orderSeq] varchar(255) NULL ,
[material] varchar(255) NULL ,
[lot] varchar(255) NULL ,
[innercode] varchar(255) NULL ,
[unit] varchar(255) NULL ,
[price] money NULL ,
[actualQty] numeric(18,2) NULL ,
[dyProDate] datetime NULL ,
[dyManufacturer] varchar(255) NULL ,
[registrationNo] varchar(255) NULL ,
[amount] money NULL ,
[effectiveDate] datetime NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'入库单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'入库单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'入库单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'入库单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'seq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'orderId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'orderSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'lot')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'innercode')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'innercode'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'innercode'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'unit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'price')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'price'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'price'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'actualQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'dyProDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'dyManufacturer')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'registrationNo')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'amount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purinwarehs_entry', 
'COLUMN', N'effectiveDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purinwarehs_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
GO

-- ----------------------------
-- Table structure for t_sms_purreceival
-- ----------------------------
if object_id(N't_sms_purreceival',N'U') is not null
DROP TABLE [t_sms_purreceival]
GO
CREATE TABLE [t_sms_purreceival] (
[id] varchar(255) NOT NULL ,
[number] varchar(255) NULL ,
[bizDate] datetime NULL ,
[baseStatus] tinyint NULL ,
[sourceBillType] varchar(255) NULL ,
[supplier] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'bizDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'bizDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'bizDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'baseStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'sourceBillType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO

-- ----------------------------
-- Table structure for t_sms_purreceival_entry
-- ----------------------------
if object_id(N't_sms_purreceival_entry',N'U') is not null
DROP TABLE [t_sms_purreceival_entry]
GO
CREATE TABLE [t_sms_purreceival_entry] (
[id] varchar(255) NOT NULL ,
[parent] varchar(255) NULL ,
[seq] int NULL ,
[orderId] varchar(255) NULL ,
[orderSeq] varchar(255) NULL ,
[material] varchar(255) NULL ,
[lot] varchar(255) NULL ,
[innercode] varchar(255) NULL ,
[unit] varchar(255) NULL ,
[price] money NULL ,
[qty] numeric(18,2) NULL ,
[actualQty] numeric(18,2) NULL ,
[dyProDate] datetime NULL ,
[dyManufacturer] varchar(255) NULL ,
[registrationNo] varchar(255) NULL ,
[amount] money NULL ,
[effectiveDate] datetime NULL ,
[qualifiedQty] numeric(18,2) NULL ,
[unqualifiedQty] numeric(18,2) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收货单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收货单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'seq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'orderId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'orderSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'lot')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'innercode')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'innercode'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'innercode'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'unit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'price')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'price'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'price'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'qty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'应收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'应收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'actualQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'dyProDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'dyManufacturer')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'registrationNo')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'amount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'effectiveDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'qualifiedQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合格数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'qualifiedQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合格数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'qualifiedQty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreceival_entry', 
'COLUMN', N'unqualifiedQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'不合格数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'unqualifiedQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'不合格数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreceival_entry'
, @level2type = 'COLUMN', @level2name = N'unqualifiedQty'
GO

-- ----------------------------
-- Table structure for t_sms_purreturns
-- ----------------------------
if object_id(N't_sms_purreturns',N'U') is not null
DROP TABLE [t_sms_purreturns]
GO
CREATE TABLE [t_sms_purreturns] (
[id] varchar(255) NOT NULL ,
[number] varchar(255) NULL ,
[bizDate] datetime NULL ,
[baseStatus] tinyint NULL ,
[sourceBillType] varchar(255) NULL ,
[supplier] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'退货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'退货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns', 
'COLUMN', N'bizDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'退货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'bizDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'退货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'bizDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns', 
'COLUMN', N'baseStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'baseStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns', 
'COLUMN', N'sourceBillType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'源单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'sourceBillType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO

-- ----------------------------
-- Table structure for t_sms_purreturns_entry
-- ----------------------------
if object_id(N't_sms_purreturns_entry',N'U') is not null
DROP TABLE [t_sms_purreturns_entry]
GO
CREATE TABLE [t_sms_purreturns_entry] (
[id] varchar(255) NOT NULL ,
[parent] varchar(255) NULL ,
[orderId] varchar(255) NULL ,
[orderSeq] varchar(255) NULL ,
[material] varchar(255) NULL ,
[unit] varchar(255) NULL ,
[returnQty] numeric(18,2) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'退货单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'退货单子表内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'退货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'退货单内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'orderId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'orderSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'unit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_purreturns_entry', 
'COLUMN', N'returnQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'returnQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实收数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_purreturns_entry'
, @level2type = 'COLUMN', @level2name = N'returnQty'
GO

-- ----------------------------
-- Table structure for t_sms_role
-- ----------------------------
if object_id(N't_sms_role',N'U') is not null
DROP TABLE [t_sms_role]
GO
CREATE TABLE [t_sms_role] (
[roleId] varchar(50) NOT NULL ,
[name] varchar(20) NOT NULL ,
[number] varchar(20) NOT NULL ,
[type] varchar(50) NOT NULL ,
[status] tinyint NOT NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_role', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_role', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_role', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色类别(1:平台角色2:供应商角色)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色类别(1:平台角色2:供应商角色)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_role', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否可用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否可用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_role'
, @level2type = 'COLUMN', @level2name = N'status'
GO

-- ----------------------------
-- Table structure for t_sms_roleType
-- ----------------------------
if object_id(N't_sms_roleType',N'U') is not null
DROP TABLE [t_sms_roleType]
GO
CREATE TABLE [t_sms_roleType] (
[typeId] nvarchar(50) NOT NULL ,
[number] varchar(20) NOT NULL ,
[name] varchar(20) NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_roleType', 
'COLUMN', N'typeId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'typeId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'typeId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_roleType', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_roleType', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_roleType'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_sale_proxy
-- ----------------------------
if object_id(N't_sms_sale_proxy',N'U') is not null
DROP TABLE [t_sms_sale_proxy]
GO
CREATE TABLE [t_sms_sale_proxy] (
[id] int NOT NULL ,
[name] varchar(50) NULL ,
[number] varchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sale_proxy', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购模式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sale_proxy'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购模式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sale_proxy'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_sendcargo
-- ----------------------------
if object_id(N't_sms_sendcargo',N'U') is not null
DROP TABLE [t_sms_sendcargo]
GO
CREATE TABLE [t_sms_sendcargo] (
[id] varchar(50) NOT NULL ,
[number] varchar(255) NULL ,
[Date] datetime NULL ,
[supplier] varchar(255) NULL ,
[logistics] varchar(255) NULL ,
[logisticsNo] varchar(255) NULL ,
[saleProxy] tinyint NULL ,
[type] tinyint NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发货单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'Date')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'Date'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发货日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'Date'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'logistics')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物流公司'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'logistics'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物流公司'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'logistics'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'logisticsNo')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物流单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'logisticsNo'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物流单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'logisticsNo'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'saleProxy')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'saleProxy'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单据状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'saleProxy'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发货状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发货状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo'
, @level2type = 'COLUMN', @level2name = N'type'
GO

-- ----------------------------
-- Table structure for t_sms_sendcargo_entry
-- ----------------------------
if object_id(N't_sms_sendcargo_entry',N'U') is not null
DROP TABLE [t_sms_sendcargo_entry]
GO
CREATE TABLE [t_sms_sendcargo_entry] (
[id] varchar(255) NOT NULL ,
[parent] varchar(255) NULL ,
[orderId] varchar(255) NULL ,
[seq] int NULL ,
[material] varchar(255) NULL ,
[lot] varchar(255) NULL ,
[dyBatchNum] varchar(255) NULL ,
[code] varchar(255) NULL ,
[unit] varchar(255) NULL ,
[price] money NULL ,
[qty] numeric(18,2) NULL ,
[dyProDate] datetime NULL ,
[dyManufacturer] varchar(255) NULL ,
[registrationNo] varchar(255) NULL ,
[amount] money NULL ,
[effectiveDate] datetime NULL ,
[orderSeq] varchar(255) NULL ,
[actualQty] numeric(18,2) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发货单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发货单ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'orderId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'采购订单号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'orderId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'seq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'seq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'lot')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'批次'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'lot'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'dyBatchNum')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'批号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyBatchNum'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'批号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyBatchNum'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'code')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'code'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'个体码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'code'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'unit')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计量单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'price')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'price'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'price'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'qty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'应发数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'应发数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'qty'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'dyProDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyProDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'dyManufacturer')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'生产厂家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'dyManufacturer'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'registrationNo')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'产品注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'registrationNo'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'amount')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'amount'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'effectiveDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'有效期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'effectiveDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'orderSeq')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'订单行号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'orderSeq'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sendcargo_entry', 
'COLUMN', N'actualQty')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实发数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实发数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sendcargo_entry'
, @level2type = 'COLUMN', @level2name = N'actualQty'
GO

-- ----------------------------
-- Table structure for t_sms_serial_number
-- ----------------------------
if object_id(N't_sms_serial_number',N'U') is not null
DROP TABLE [t_sms_serial_number]
GO
CREATE TABLE [t_sms_serial_number] (
[id] int NOT NULL IDENTITY(1,1) ,
[classId] int NULL ,
[year] int NULL ,
[number] int NULL 
)


GO
DBCC CHECKIDENT(N'[t_sms_serial_number]', RESEED, 3)
GO

-- ----------------------------
-- Table structure for t_sms_settlement
-- ----------------------------
if object_id(N't_sms_settlement',N'U') is not null
DROP TABLE [t_sms_settlement]
GO
CREATE TABLE [t_sms_settlement] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_settlement', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'结算方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'结算方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_settlement', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'结算方式ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'结算方式ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_settlement', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'结算方式名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'结算方式名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_settlement'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_sourceBillType
-- ----------------------------
if object_id(N't_sms_sourceBillType',N'U') is not null
DROP TABLE [t_sms_sourceBillType]
GO
CREATE TABLE [t_sms_sourceBillType] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sourceBillType', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'原单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sourceBillType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'原单据类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sourceBillType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sourceBillType', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'原单据类型ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sourceBillType'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'原单据类型ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sourceBillType'
, @level2type = 'COLUMN', @level2name = N'id'
GO

-- ----------------------------
-- Table structure for t_sms_status
-- ----------------------------
if object_id(N't_sms_status',N'U') is not null
DROP TABLE [t_sms_status]
GO
CREATE TABLE [t_sms_status] (
[id] int NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_subMessage
-- ----------------------------
if object_id(N't_sms_subMessage',N'U') is not null
DROP TABLE [t_sms_subMessage]
GO
CREATE TABLE [t_sms_subMessage] (
[detailId] int NOT NULL ,
[typeId] int NOT NULL ,
[number] varchar(20) NOT NULL DEFAULT '' ,
[name] varchar(200) NULL ,
[enable] tinyint NOT NULL DEFAULT ((1)) ,
[index] int NOT NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性表
存储系统中枚举变量值，如币别有：人民币，美元，欧元等类似的可以只用代码名称两个属性描述的资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性表
存储系统中枚举变量值，如币别有：人民币，美元，欧元等类似的可以只用代码名称两个属性描述的资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'detailId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'明细属性内码（1--1000保留做系统属性，1001以上用作用户属性）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'detailId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'明细属性内码（1--1000保留做系统属性，1001以上用作用户属性）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'detailId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'typeId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性类别ID(内码)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'typeId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性类别ID(内码)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'typeId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性明细名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性明细名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'enable')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否有效(0无效1:有效，默认有效)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'enable'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否有效(0无效1:有效，默认有效)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'enable'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMessage', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'显示顺序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMessage'
, @level2type = 'COLUMN', @level2name = N'index'
GO

-- ----------------------------
-- Table structure for t_sms_subMesType
-- ----------------------------
if object_id(N't_sms_subMesType',N'U') is not null
DROP TABLE [t_sms_subMesType]
GO
CREATE TABLE [t_sms_subMesType] (
[typeId] int NOT NULL ,
[name] varchar(20) NOT NULL ,
[desc] varchar(100) NOT NULL ,
[systemType] tinyint NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMesType', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性类别表
存储系统中枚举类型名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性类别表
存储系统中枚举类型名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMesType', 
'COLUMN', N'typeId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'辅助属性ID(内码)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'typeId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'辅助属性ID(内码)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'typeId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMesType', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMesType', 
'COLUMN', N'desc')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N' 描述信息'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'desc'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N' 描述信息'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'desc'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_subMesType', 
'COLUMN', N'systemType')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'标示系统类型或用户类型(0系统类型1:用户类型)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'systemType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'标示系统类型或用户类型(0系统类型1:用户类型)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_subMesType'
, @level2type = 'COLUMN', @level2name = N'systemType'
GO

-- ----------------------------
-- Table structure for t_sms_supplier
-- ----------------------------
if object_id(N't_sms_supplier',N'U') is not null
DROP TABLE [t_sms_supplier]
GO
CREATE TABLE [t_sms_supplier] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[taxId] varchar(100) NULL ,
[corp] varchar(255) NULL ,
[brno] varchar(255) NULL ,
[taxCategoryId] varchar(50) NULL ,
[taxRate] decimal(28,10) NULL ,
[country] varchar(50) NULL ,
[city] varchar(50) NULL ,
[province] varchar(50) NULL ,
[county] varchar(50) NULL ,
[address] varchar(255) NULL ,
[industryId] varchar(50) NULL ,
[categoryId] varchar(50) NULL ,
[status] tinyint NULL ,
[number] varchar(50) NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[review] tinyint NULL DEFAULT ((0)) ,
[mobile] varchar(20) NULL ,
[contactPerson] varchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'简称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'简称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'taxId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税务登记号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税务登记号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'corp')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'法人代表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'corp'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'法人代表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'corp'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'brno')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'工商注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'brno'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'工商注册号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'brno'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'taxCategoryId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxCategoryId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxCategoryId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'taxRate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxRate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税率'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'taxRate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'country')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'国家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'country'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'国家'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'country'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'city')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'city'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'city'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'province')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'省份'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'province'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'省份'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'province'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'county')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'区县'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'county'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'区县'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'county'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'address')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'address'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'address'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'industryId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'行业ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'industryId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'行业ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'industryId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'categoryId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'分类ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'categoryId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'分类ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'categoryId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'review'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'mobile')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商手机号码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'mobile'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商手机号码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'mobile'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier', 
'COLUMN', N'contactPerson')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'contactPerson'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier'
, @level2type = 'COLUMN', @level2name = N'contactPerson'
GO

-- ----------------------------
-- Table structure for t_sms_supplier_item_license
-- ----------------------------
if object_id(N't_sms_supplier_item_license',N'U') is not null
DROP TABLE [t_sms_supplier_item_license]
GO
CREATE TABLE [t_sms_supplier_item_license] (
[id] varchar(50) NOT NULL ,
[type] varchar(50) NULL ,
[supplier] varchar(50) NULL ,
[authOrg] varchar(100) NULL ,
[description] varchar(255) NULL ,
[beginDate] datetime NULL ,
[endDate] datetime NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[review] tinyint NULL DEFAULT ((0)) ,
[material] varchar(50) NULL ,
[isMust] tinyint NULL ,
[isControl] tinyint NULL ,
[prohibited] tinyint NULL ,
[name] varchar(80) NULL ,
[number] nvarchar(80) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料证件类型(关联t_sms_material_license_type表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料证件类型(关联t_sms_material_license_type表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商(关联t_sms_supplier表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商(关联t_sms_supplier表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'authOrg')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发证机关'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'authOrg'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发证机关'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'authOrg'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'beginDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'起始日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'beginDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'起始日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'beginDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'endDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'结束日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'endDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'结束日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'endDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'review'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'material')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物料（关联表t_sms_item主键id）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'material'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物料（关联表t_sms_item主键id）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'material'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'isMust')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'isMust'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'isMust'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license', 
'COLUMN', N'isControl')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'isControl'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license'
, @level2type = 'COLUMN', @level2name = N'isControl'
GO

-- ----------------------------
-- Table structure for t_sms_supplier_item_license_entry
-- ----------------------------
if object_id(N't_sms_supplier_item_license_entry',N'U') is not null
DROP TABLE [t_sms_supplier_item_license_entry]
GO
CREATE TABLE [t_sms_supplier_item_license_entry] (
[id] varchar(50) NOT NULL ,
[parent] varchar(50) NULL ,
[url] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license_entry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'外键，关联主表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'外键，关联主表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_item_license_entry', 
'COLUMN', N'url')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'附件存放路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'url'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'附件存放路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_item_license_entry'
, @level2type = 'COLUMN', @level2name = N'url'
GO

-- ----------------------------
-- Table structure for t_sms_supplier_license
-- ----------------------------
if object_id(N't_sms_supplier_license',N'U') is not null
DROP TABLE [t_sms_supplier_license]
GO
CREATE TABLE [t_sms_supplier_license] (
[id] varchar(50) NOT NULL ,
[type] varchar(50) NULL ,
[supplier] varchar(50) NULL ,
[authOrg] varchar(100) NULL ,
[description] varchar(255) NULL DEFAULT '' ,
[beginDate] datetime NULL ,
[endDate] datetime NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[review] tinyint NULL DEFAULT ((0)) ,
[isMust] tinyint NULL ,
[isControl] tinyint NULL ,
[prohibited] tinyint NULL ,
[name] varchar(80) NULL ,
[number] nvarchar(80) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证件类型(关联t_sms_supplier_license_type表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证件类型(关联t_sms_supplier_license_type表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商(关联t_sms_supplier表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商(关联t_sms_supplier表主键id)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'authOrg')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'发证机关'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'authOrg'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'发证机关'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'authOrg'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'description')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'description'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'description'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'beginDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'起始日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'beginDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'起始日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'beginDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'endDate')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'结束日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'endDate'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'结束日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'endDate'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'review'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'isMust')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'isMust'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'isMust'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'isControl')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'isControl'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'isControl'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license', 
'COLUMN', N'prohibited')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否停用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'prohibited'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否停用'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license'
, @level2type = 'COLUMN', @level2name = N'prohibited'
GO

-- ----------------------------
-- Table structure for t_sms_supplier_license_entry
-- ----------------------------
if object_id(N't_sms_supplier_license_entry',N'U') is not null
DROP TABLE [t_sms_supplier_license_entry]
GO
CREATE TABLE [t_sms_supplier_license_entry] (
[id] varchar(50) NOT NULL ,
[parent] varchar(50) NULL ,
[url] varchar(255) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_entry', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_entry', 
'COLUMN', N'parent')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'外键，关联主表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'外键，关联主表主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'parent'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_entry', 
'COLUMN', N'url')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'附件存放路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'url'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'附件存放路径'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_entry'
, @level2type = 'COLUMN', @level2name = N'url'
GO

-- ----------------------------
-- Table structure for t_sms_supplier_license_type
-- ----------------------------
if object_id(N't_sms_supplier_license_type',N'U') is not null
DROP TABLE [t_sms_supplier_license_type]
GO
CREATE TABLE [t_sms_supplier_license_type] (
[id] varchar(50) NOT NULL ,
[number] varchar(20) NULL ,
[name] varchar(50) NULL ,
[isMust] tinyint NULL ,
[isControl] tinyint NULL ,
[syncStatus] tinyint NULL DEFAULT ((0)) ,
[review] tinyint NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'供应商证件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'供应商证件类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'证件内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'证件内码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'isMust')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'isMust'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否必须'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'isMust'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'isControl')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'isControl'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否控制'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'isControl'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'syncStatus')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'同步状态（0.未同步，1.已同步）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'syncStatus'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_supplier_license_type', 
'COLUMN', N'review')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'review'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审核状态：0.未审核，1.已审核'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_supplier_license_type'
, @level2type = 'COLUMN', @level2name = N'review'
GO

-- ----------------------------
-- Table structure for t_sms_sys_profile
-- ----------------------------
if object_id(N't_sms_sys_profile',N'U') is not null
DROP TABLE [t_sms_sys_profile]
GO
CREATE TABLE [t_sms_sys_profile] (
[category] varchar(255) NOT NULL DEFAULT '' ,
[key] varchar(255) NOT NULL DEFAULT '' ,
[name] varchar(255) NOT NULL DEFAULT '' ,
[desc] varchar(255) NULL DEFAULT '' ,
[value] varchar(255) NOT NULL ,
[index] int NOT NULL DEFAULT ((0)) ,
[explanation] varchar(255) NOT NULL DEFAULT '' ,
[readOnly] tinyint NOT NULL DEFAULT ((0)) 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'category')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数类别(通常按模块来分参数，记录模块标示)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'category'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数类别(通常按模块来分参数，记录模块标示)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'category'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'key')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'key'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'key'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数名称(尽量简短，不要太长)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数名称(尽量简短，不要太长)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'desc')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'desc'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'desc'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'value')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'value'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'value'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'index')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数排序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'index'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数排序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'index'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'explanation')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'参数选项,配置参数的类型及类型明细
{ctlType:checkBox,}
{ctlType:selector,list:[{0:不启用},{1:启用}]}
{ctlType:text,}
{ctlType:number,}'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'explanation'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'参数选项,配置参数的类型及类型明细
{ctlType:checkBox,}
{ctlType:selector,list:[{0:不启用},{1:启用}]}
{ctlType:text,}
{ctlType:number,}'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'explanation'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_sys_profile', 
'COLUMN', N'readOnly')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否只读参数(只读参数不可修改)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'readOnly'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否只读参数(只读参数不可修改)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_sys_profile'
, @level2type = 'COLUMN', @level2name = N'readOnly'
GO

-- ----------------------------
-- Table structure for t_sms_taxCategory
-- ----------------------------
if object_id(N't_sms_taxCategory',N'U') is not null
DROP TABLE [t_sms_taxCategory]
GO
CREATE TABLE [t_sms_taxCategory] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(20) NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_taxCategory', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税种'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税种'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_taxCategory', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税种ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_taxCategory', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税种名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税种名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_taxCategory'
, @level2type = 'COLUMN', @level2name = N'name'
GO

-- ----------------------------
-- Table structure for t_sms_unit
-- ----------------------------
if object_id(N't_sms_unit',N'U') is not null
DROP TABLE [t_sms_unit]
GO
CREATE TABLE [t_sms_unit] (
[id] varchar(50) NOT NULL ,
[name] varchar(255) NULL ,
[number] varchar(20) NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_unit', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_unit', 
'COLUMN', N'id')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'id'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'id'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_unit', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_unit', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'编码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_unit'
, @level2type = 'COLUMN', @level2name = N'number'
GO

-- ----------------------------
-- Table structure for t_sms_user
-- ----------------------------
if object_id(N't_sms_user',N'U') is not null
DROP TABLE [t_sms_user]
GO
CREATE TABLE [t_sms_user] (
[userId] varchar(50) NOT NULL ,
[number] varchar(20) NOT NULL ,
[name] varchar(20) NOT NULL ,
[password] varchar(50) NOT NULL ,
[type] varchar(50) NOT NULL ,
[status] tinyint NOT NULL DEFAULT ((0)) ,
[role] varchar(50) NOT NULL ,
[supplier] varchar(50) NULL DEFAULT '' ,
[token] varchar(255) NOT NULL DEFAULT '' ,
[phone] varchar(20) NOT NULL DEFAULT '' 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'userId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'userId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'userId'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'number')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'number'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户代码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'number'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'name')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'name'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'name'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'password')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'password'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'password'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'type')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户类别(基础资料1002)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'type'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户类别(基础资料1002)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'type'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'status')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否禁用(0可用1禁用，默认0可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'status'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否禁用(0可用1禁用，默认0可用)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'status'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'role')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'引用角色基础资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'role'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'引用角色基础资料'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'role'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'supplier')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'引用供应商基础资料，当type为供应商用户时必须有'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'supplier'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'引用供应商基础资料，当type为供应商用户时必须有'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'supplier'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_user', 
'COLUMN', N'token')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'hrp端与sms''交互数据通过token校验'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'token'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'hrp端与sms''交互数据通过token校验'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_user'
, @level2type = 'COLUMN', @level2name = N'token'
GO

-- ----------------------------
-- Table structure for t_sms_user_entry
-- ----------------------------
if object_id(N't_sms_user_entry',N'U') is not null
DROP TABLE [t_sms_user_entry]
GO
CREATE TABLE [t_sms_user_entry] (
[id] varchar(50) NOT NULL ,
[parent] varchar(50) NULL ,
[mobile] varchar(11) NULL ,
[pic] varchar(255) NULL 
)


GO

-- ----------------------------
-- Table structure for t_sms_userType
-- ----------------------------
if object_id(N't_sms_userType',N'U') is not null
DROP TABLE [t_sms_userType]
GO
CREATE TABLE [t_sms_userType] (
[typeId] varchar(50) NOT NULL ,
[number] varchar(20) NOT NULL ,
[name] varchar(20) NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_userType', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_userType'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_userType'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N't_sms_userType', 
'COLUMN', N'typeId')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_userType'
, @level2type = 'COLUMN', @level2name = N'typeId'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N't_sms_userType'
, @level2type = 'COLUMN', @level2name = N'typeId'
GO

-- ----------------------------
-- Table structure for t_sms_validation
-- ----------------------------
if object_id(N't_sms_validation',N'U') is not null
DROP TABLE [t_sms_validation]
GO
CREATE TABLE [t_sms_validation] (
[a] char(1) NULL 
)


GO

-- ----------------------------
-- View structure for vw_newid
-- ----------------------------
IF  EXISTS(SELECT 1 FROM sys.views WHERE name='vw_newid')
DROP VIEW [vw_newid]
GO
CREATE VIEW [vw_newid] AS 
SELECT NEWID() [FNEWID]
GO



-- ----------------------------
-- Function structure for base64encode
-- ----------------------------
if object_id(N'base64encode',N'U') is not null
DROP FUNCTION [base64encode]
GO

CREATE FUNCTION [base64encode]
(@source VARBINARY(6000))
RETURNS VARCHAR(8000)
AS
BEGIN
DECLARE @g_base64 AS VARCHAR(64)
DECLARE @l_result AS VARCHAR(8000)
DECLARE @l_length as int
DECLARE @l BINARY(3)
DECLARE @r1 BINARY(1)
DECLARE @r2 BINARY(1)
DECLARE @r3 BINARY(1)
DECLARE @b1 TINYINT
DECLARE @b2 TINYINT
DECLARE @b3 TINYINT
DECLARE @b4 TINYINT
DECLARE @i AS INT
SET @g_base64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/';
SET @l_length = DATALENGTH(@source)
SET @i = 0;
SET @l_result =''
WHILE FLOOR(@l_length/3) >@i
BEGIN
SET @l = SUBSTRING(@source,(@i*3)+1,3)
SET @r1=  SUBSTRING(@l,1,1)
SET @r2=  SUBSTRING(@l,2,1)
SET @r3=  SUBSTRING(@l,3,1)
SET @b1 = FLOOR(@r1/4)
SET @b2 = ((@r1 & 3) *16 )+ FLOOR(@r2/16)
SET @b3 = ((@r2 & 15) * 4)+ FLOOR(@r3/64)
SET @b4 = (@r3 & 63)
SET @l_result = @l_result + SUBSTRING(@g_base64,@b1+1,1) + SUBSTRING(@g_base64,@b2+1,1) +SUBSTRING(@g_base64,@b3+1,1) +SUBSTRING(@g_base64,@b4+1,1)
SET @i = @i+1
END
IF @l_length % 3 = 1
BEGIN
SET @l = SUBSTRING(@source,@l_length,1)
SET @r1 = @l
SET @b1 = FLOOR(@r1/4)
SET @b2 = (@r1 & 3) * 16
SET @l_result = @l_result + SUBSTRING(@g_base64,@b1+1,1) + SUBSTRING(@g_base64,@b2+1,1) +'=='
END
ELSE IF @l_length % 3 = 2
BEGIN
SET @l = SUBSTRING(@source,@l_length-1,2)
SET @r1=  SUBSTRING(@l,1,1)
SET @r2=  SUBSTRING(@l,2,1)
SET @b1 = FLOOR(@r1/4)
SET @b2 = ((@r1 & 3) *16 )+ FLOOR(@r2/16)
SET @b3 = ((@r2 & 15) * 4)
SET @l_result = @l_result + SUBSTRING(@g_base64,@b1+1,1) + SUBSTRING(@g_base64,@b2+1,1) +SUBSTRING(@g_base64,@b3+1,1) +'='
END
RETURN  @l_result
END




GO

-- ----------------------------
-- Function structure for newbosid
-- ----------------------------
if object_id(N'newbosid',N'U') is not null
DROP FUNCTION [newbosid]
GO

CREATE FUNCTION [newbosid]
(@typeString VARCHAR(8))
RETURNS VARCHAR(44)
AS
BEGIN
DECLARE @newuuid AS BINARY(16)
DECLARE @sbosid AS VARCHAR(20)
DECLARE @bosid AS BINARY(20)
DECLARE @typenumber AS BIGINT
DECLARE @l_result AS VARCHAR(44)
DECLARE @UUID AS UNIQUEIDENTIFIER
SELECT @UUID = [FNEWID]  FROM VW_NEWID
IF (LEN(@typeString) = 4) -- ?????4
BEGIN
SELECT  @l_result =   CAST(@UUID AS VARCHAR(40))+ @typeString
END
ELSE IF LEN(@typeString) = 8 --?????8
BEGIN
DECLARE @hex AS CHAR(16)
DECLARE @n AS INT
DECLARE @c AS CHAR(1)
DECLARE @pos AS BIGINT
DECLARE @bint AS BIGINT
DECLARE @l AS INT
DECLARE @b_num AS BIT
SET @hex = '0123456789ABCDEF'
SET @n = 8
SET @b_num = 1
SET @bint = 0
WHILE @n >0
BEGIN
SET @c = SUBSTRING(@typeString,@n,1)
SET @pos = PATINDEX('%'+@c+'%',@hex)
IF @pos = 0
BEGIN
SET @b_num = 0
BREAK
END
SET @bint = @bint+ POWER(16,8-@n)*(@pos-1)
SET @n = @n-1
END
IF @b_num = 0 -- ??????
BEGIN
SELECT  @l_result =   CAST(@UUID AS VARCHAR(40))+ @typeString
END
ELSE -- ??????
BEGIN
SELECT @newuuid = CAST(@UUID AS BINARY(16))
SELECT @bosid = @newuuid + CAST(@bint AS BINARY(4))
SET @l_result = dbo.base64encode(@bosid)
END
END
ELSE
BEGIN
SELECT  @l_result = NULL
END
RETURN @l_result
END


GO


-- ----------------------------
-- 
--
--
--添加表索引
--
--
-- ----------------------------

-- ----------------------------
-- Indexes structure for table t_sms_accessControl
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_accessControl
-- ----------------------------
ALTER TABLE [t_sms_accessControl] ADD PRIMARY KEY ([objectType], [objectId], [roleId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_approved_supplier
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_approved_supplier
-- ----------------------------
ALTER TABLE [t_sms_approved_supplier] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_base_status
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_base_status
-- ----------------------------
ALTER TABLE [t_sms_base_status] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_category
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_category
-- ----------------------------
ALTER TABLE [t_sms_category] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_certificate
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_certificate
-- ----------------------------
ALTER TABLE [t_sms_certificate] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_city
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_city
-- ----------------------------
ALTER TABLE [t_sms_city] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_country
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_country
-- ----------------------------
ALTER TABLE [t_sms_country] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_county
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_county
-- ----------------------------
ALTER TABLE [t_sms_county] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_currency
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_currency
-- ----------------------------
ALTER TABLE [t_sms_currency] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_dataFlowSubClass
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_dataFlowSubClass
-- ----------------------------
ALTER TABLE [t_sms_dataFlowSubClass] ADD PRIMARY KEY ([subSysId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_dataFlowTopClass
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_dataFlowTopClass
-- ----------------------------
ALTER TABLE [t_sms_dataFlowTopClass] ADD PRIMARY KEY ([topClassId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_employee
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_employee
-- ----------------------------
ALTER TABLE [t_sms_employee] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_formClass
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_formClass
-- ----------------------------
ALTER TABLE [t_sms_formClass] ADD PRIMARY KEY ([classId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_formEntries
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_formEntries
-- ----------------------------
ALTER TABLE [t_sms_formEntries] ADD PRIMARY KEY ([classId], [entryIndex])
GO

-- ----------------------------
-- Indexes structure for table t_sms_formFields
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_formFields
-- ----------------------------
ALTER TABLE [t_sms_formFields] ADD PRIMARY KEY ([classId], [key])
GO

-- ----------------------------
-- Indexes structure for table t_sms_industry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_industry
-- ----------------------------
ALTER TABLE [t_sms_industry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_item
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_item
-- ----------------------------
ALTER TABLE [t_sms_item] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_item_license_type
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_item_license_type
-- ----------------------------
ALTER TABLE [t_sms_item_license_type] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_log
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_log
-- ----------------------------
ALTER TABLE [t_sms_log] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_msglog
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_msglog
-- ----------------------------
ALTER TABLE [t_sms_msglog] ADD PRIMARY KEY ([seqid])
GO

-- ----------------------------
-- Indexes structure for table t_sms_objectAccessType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_objectAccessType
-- ----------------------------
ALTER TABLE [t_sms_objectAccessType] ADD PRIMARY KEY ([objectType], [objectId], [index])
GO

-- ----------------------------
-- Indexes structure for table t_sms_objectType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_objectType
-- ----------------------------
ALTER TABLE [t_sms_objectType] ADD PRIMARY KEY ([topClassId], [subSysId], [objectType], [objectId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_pay
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_pay
-- ----------------------------
ALTER TABLE [t_sms_pay] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_paymentType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_paymentType
-- ----------------------------
ALTER TABLE [t_sms_paymentType] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_plugins
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_plugins
-- ----------------------------
ALTER TABLE [t_sms_plugins] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_province
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_province
-- ----------------------------
ALTER TABLE [t_sms_province] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purchase_order
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purchase_order
-- ----------------------------
ALTER TABLE [t_sms_purchase_order] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purchase_order_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purchase_order_entry
-- ----------------------------
ALTER TABLE [t_sms_purchase_order_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purinwarehs
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purinwarehs
-- ----------------------------
ALTER TABLE [t_sms_purinwarehs] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purinwarehs_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purinwarehs_entry
-- ----------------------------
ALTER TABLE [t_sms_purinwarehs_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purreceival
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purreceival
-- ----------------------------
ALTER TABLE [t_sms_purreceival] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purreceival_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purreceival_entry
-- ----------------------------
ALTER TABLE [t_sms_purreceival_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purreturns
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purreturns
-- ----------------------------
ALTER TABLE [t_sms_purreturns] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_purreturns_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_purreturns_entry
-- ----------------------------
ALTER TABLE [t_sms_purreturns_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_role
-- ----------------------------
ALTER TABLE [t_sms_role] ADD PRIMARY KEY ([roleId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_roleType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_roleType
-- ----------------------------
ALTER TABLE [t_sms_roleType] ADD PRIMARY KEY ([typeId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_sale_proxy
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_sale_proxy
-- ----------------------------
ALTER TABLE [t_sms_sale_proxy] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_sendcargo
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_sendcargo
-- ----------------------------
ALTER TABLE [t_sms_sendcargo] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_sendcargo_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_sendcargo_entry
-- ----------------------------
ALTER TABLE [t_sms_sendcargo_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_serial_number
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_serial_number
-- ----------------------------
ALTER TABLE [t_sms_serial_number] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_settlement
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_settlement
-- ----------------------------
ALTER TABLE [t_sms_settlement] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_sourceBillType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_sourceBillType
-- ----------------------------
ALTER TABLE [t_sms_sourceBillType] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_status
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_status
-- ----------------------------
ALTER TABLE [t_sms_status] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_subMessage
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_subMessage
-- ----------------------------
ALTER TABLE [t_sms_subMessage] ADD PRIMARY KEY ([detailId], [typeId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_subMesType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_subMesType
-- ----------------------------
ALTER TABLE [t_sms_subMesType] ADD PRIMARY KEY ([typeId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier
-- ----------------------------
ALTER TABLE [t_sms_supplier] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier_item_license
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier_item_license
-- ----------------------------
ALTER TABLE [t_sms_supplier_item_license] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier_item_license_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier_item_license_entry
-- ----------------------------
ALTER TABLE [t_sms_supplier_item_license_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier_license
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier_license
-- ----------------------------
ALTER TABLE [t_sms_supplier_license] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier_license_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier_license_entry
-- ----------------------------
ALTER TABLE [t_sms_supplier_license_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_supplier_license_type
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_supplier_license_type
-- ----------------------------
ALTER TABLE [t_sms_supplier_license_type] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_sys_profile
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_sys_profile
-- ----------------------------
ALTER TABLE [t_sms_sys_profile] ADD PRIMARY KEY ([category], [key])
GO

-- ----------------------------
-- Indexes structure for table t_sms_taxCategory
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_taxCategory
-- ----------------------------
ALTER TABLE [t_sms_taxCategory] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_unit
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_unit
-- ----------------------------
ALTER TABLE [t_sms_unit] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_user
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_user
-- ----------------------------
ALTER TABLE [t_sms_user] ADD PRIMARY KEY ([userId])
GO

-- ----------------------------
-- Indexes structure for table t_sms_user_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_user_entry
-- ----------------------------
ALTER TABLE [t_sms_user_entry] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table t_sms_userType
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table t_sms_userType
-- ----------------------------
ALTER TABLE [t_sms_userType] ADD PRIMARY KEY ([typeId])
GO



-- ----------------------------
-- 
--
--初始化表数据
--
--
-- ----------------------------




-- ----------------------------
-- Records of t_sms_accessControl
-- ----------------------------
BEGIN TRANSACTION
GO
INSERT INTO [t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (N'10', N'1005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'207'), (N'10', N'1010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1015', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'15'), (N'10', N'1020', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1025', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'5'), (N'10', N'1030', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'10', N'1030', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'293'), (N'10', N'1035', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1035', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1045', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1045', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1050', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1050', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1055', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1055', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1060', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1060', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1065', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1065', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1070', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1070', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1075', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1075', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1080', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1080', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1085', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1085', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'20', N'2005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'3'), (N'20', N'2005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'3'), (N'20', N'2010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'20', N'2010', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'30', N'3005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'30', N'3005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'30', N'3010', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3015', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'30', N'3015', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'30', N'3020', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'319'), (N'30', N'3020', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3025', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'319'), (N'30', N'3025', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'40', N'4005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'271'), (N'40', N'4005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'40', N'4010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'40', N'4015', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'40', N'4020', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'60', N'6005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'60', N'6005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'140', N'14005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'140', N'14010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'1000', N'100005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1')

GO

-- ----------------------------
-- Records of t_sms_dataFlowSubClass
-- ----------------------------

INSERT INTO [t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (N'10', N'1005', N'用户', N'1', N'1', N'html/base/index.html?classId=1001', N'account.png', N'1'), (N'10', N'1010', N'用户类别', N'2', N'1', N'html/base/index.html?classId=1002', N'category.png', N'1'), (N'10', N'1015', N'角色', N'3', N'1', N'html/base/index.html?classId=1003', N'role.png', N'1'), (N'10', N'1020', N'角色类别', N'4', N'1', N'html/base/index.html?classId=1004', N'category.png', N'1'), (N'10', N'1025', N'角色权限', N'5', N'1', N'html/rolePerm/index.html', N'permision.png', N'1'), (N'10', N'1030', N'供应商', N'6', N'3', N'html/base/index.html?classId=1005', N'supplier-features.png', N'3'), (N'10', N'1035', N'供应商类别', N'7', N'1', N'html/base/index.html?classId=1006', N'category.png', N'3'), (N'10', N'1045', N'行业', N'9', N'1', N'html/base/index.html?classId=1012', N'industry.png', N'3'), (N'10', N'1050', N'结算方式', N'10', N'1', N'html/base/index.html?classId=1016', N'settlement.png', N'3'), (N'10', N'1055', N'付款方式', N'11', N'1', N'html/base/index.html?classId=1014', N'payment.png', N'3'), (N'10', N'1060', N'物料', N'12', N'1', N'html/base/index.html?classId=1013', N'goods.png', N'3'), (N'10', N'1065', N'税种', N'13', N'1', N'html/base/index.html?classId=1017', N'category.png', N'3'), (N'10', N'1070', N'城市', N'14', N'1', N'html/base/index.html?classId=1008', N'country.png', N'3'), (N'10', N'1075', N'国家', N'15', N'1', N'html/base/index.html?classId=1009', N'country.png', N'3'), (N'10', N'1080', N'区县', N'16', N'1', N'html/base/index.html?classId=1010', N'country.png', N'3'), (N'10', N'1085', N'省份', N'17', N'1', N'html/base/index.html?classId=1015', N'country.png', N'3'), (N'20', N'2005', N'采购订单', N'1', N'3', N'html/list/index.html?classId=2019', N'purchase.png', N'3'), (N'20', N'2010', N'订单追踪', N'1', N'3', N'html/report/order-track/index.html', N'purchase.png', N'3'), (N'30', N'3005', N'供应商资质维护', N'1', N'3', N'html/supplier/license/index.html?classId=3010', N'file.png', N'3'), (N'30', N'3010', N'物料证件维护', N'1', N'3', N'html/supplier/license/index.html?classId=3020', N'file.png', N'3'), (N'30', N'3015', N'供应商物料查询', N'2', N'3', N'html/base/index.html?classId=3030', N'search.png', N'3'), (N'30', N'3020', N'供应商证件类别', N'2', N'3', N'html/base/index.html?classId=1007', N'category.png', N'3'), (N'30', N'3025', N'物料证件类别', N'2', N'3', N'html/base/index.html?classId=1023', N'category.png', N'3'), (N'40', N'4005', N'发货单', N'2', N'3', N'html/list/index.html?classId=2020', N'deliver.png', N'3'), (N'60', N'6005', N'订单统计', N'2', N'3', N'html/report/order-count/index.html', N'calculator.png', N'3'), (N'140', N'14005', N'参数设置', N'1', N'1', N'html/sys/parameter/index.html', N'category.png', N'3'), (N'140', N'14010', N'系统日志', N'1', N'1', N'html/sys/log/index.html', N'calendar.png', N'3'), (N'1000', N'100005', N'API测试', N'1', N'0', N'html/api-tester/index.html', N'icon_order_normal.png', N'0')


GO


-- ----------------------------
-- Records of t_sms_dataFlowTopClass
-- ----------------------------
INSERT INTO [t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (N'10', N'基础资料', N'6', N'1', N'text.png'), (N'20', N'订单管理', N'1', N'1', N'manage-order.png'), (N'30', N'证件管理', N'4', N'1', N'supplier-features.png'), (N'40', N'发货管理', N'2', N'1', N'deliver.png'), (N'60', N'统计查询', N'5', N'1', N'search.png'), (N'140', N'系统管理', N'7', N'1', N'set.png'), (N'1000', N'Test', N'100', N'0', N'all.png')

GO


-- ----------------------------
-- Records of t_sms_formClass
-- ----------------------------

INSERT INTO [t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (N'1001', N'用户', N't_sms_user', N'userId', N'10011001'), (N'1002', N'用户类别', N't_sms_userType', N'typeId', N'10021002'), (N'1003', N'角色', N't_sms_role', N'roleId', N'10031003'), (N'1004', N'角色类别', N't_sms_roleType', N'typeId', N'10041004'), (N'1005', N'供应商', N't_sms_supplier', N'id', N'37C67DFC'), (N'1006', N'供应商类别', N't_sms_category', N'id', N'7A2569A2'), (N'1007', N'供应商证件类别', N't_sms_supplier_license_type', N'id', N'9D1A92CC'), (N'1008', N'城市', N't_sms_city', N'id', N'0C5DD6B6'), (N'1009', N'国家', N't_sms_country', N'id', N'2665126B'), (N'1010', N'区县', N't_sms_county', N'id', N'859D3D7F'), (N'1011', N'币别', N't_sms_currency', N'id', N'DEB58FDC'), (N'1012', N'行业', N't_sms_industry', N'id', N'C3FDE1A9'), (N'1013', N'物料', N't_sms_item', N'id', N'4409E7F0'), (N'1014', N'付款方式', N't_sms_pay', N'id', N'6BCA0AB5'), (N'1015', N'省份', N't_sms_province', N'id', N'818DCAFB'), (N'1016', N'结算方式', N't_sms_settlement', N'id', N'E96B2B8E'), (N'1017', N'税种', N't_sms_taxCategory', N'id', N'91E210CA'), (N'1018', N'单位', N't_sms_unit', N'id', N'5B825C57'), (N'1021', N'采购模式', N't_sms_sale_proxy', N'id', N''), (N'1023', N'物料证件类型', N't_sms_item_license_type', N'id', N'2F32B746'), (N'1024', N'职员', N't_sms_employee', N'id', N''), (N'1025', N'单据状态', N't_sms_base_status', N'id', N''), (N'1026', N'原单据类型', N't_sms_sourceBillType', N'id', N''), (N'1027', N'状态', N't_sms_status', N'id', N''), (N'2019', N'采购订单', N't_sms_purchase_order', N'id', N''), (N'2020', N'发货单', N't_sms_sendcargo', N'id', N'15F2BD83'), (N'2021', N'收货单', N't_sms_purreceival', N'id', N''), (N'2022', N'入库单', N't_sms_purinwarehs', N'id', N''), (N'2023', N'退货单', N't_sms_purreturns', N'id', N''), (N'3010', N'供应商资质', N't_sms_supplier_license', N'id', N'8D759F89'), (N'3020', N'物料证件', N't_sms_supplier_item_license', N'id', N'24631D4E'), (N'3030', N'中标库', N't_sms_approved_supplier', N'id', N'ssssssss'), (N'14001', N'系统日志', N't_sms_log', N'id', N'')

GO


-- ----------------------------
-- Records of t_sms_formEntries
-- ----------------------------

INSERT INTO [t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (N'1001', N'1', N't_sms_user_entry', N'parent', N'entryId', N'10011001', N'left join'), (N'2019', N'1', N't_sms_purchase_order_entry', N'parent', N'entryId', N'', N''), (N'2020', N'1', N't_sms_sendcargo_entry', N'parent', N'entryId', N'15F2BD83', N''), (N'2021', N'1', N't_sms_purreceival_entry', N'parent', N'entryId', N'', N''), (N'2022', N'1', N't_sms_purinwarehs_entry', N'parent', N'entryId', N'', N''), (N'2023', N'1', N't_sms_purreturns_entry', N'parent', N'entryId', N'', N''), (N'3010', N'1', N't_sms_supplier_license_entry', N'parent', N'entryId', N'10011001', N'left join'), (N'3020', N'1', N't_sms_supplier_item_license_entry', N'parent', N'entryId', N'10011001', N'left join')

GO


-- ----------------------------
-- Records of t_sms_formFields
-- ----------------------------

INSERT INTO [t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (N'1001', N'1', N'电话', N'mobile', N'mobile', N'2', N'8', N'7', N'7', N'127', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'15', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'账号', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'10', N'0', N'0', N'1'), (N'1001', N'0', N'姓名', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'密码', N'password', N'password', N'99', N'99', N'4', N'4', N'60', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'手机', N'phone', N'phone', N'2', N'8', N'7', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'15', N'0', N'0', N'0', N'1'), (N'1001', N'1', N'pic', N'pic', N'pic', N'2', N'8', N'7', N'7', N'127', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'15', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'角色', N'role', N'role', N'2', N'6', N'6', N'6', N'63', N'80', N'1', N'1003', N't_sms_Role', N'', N'roleId', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'11', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'是否禁用', N'status', N'status', N'4', N'3', N'9', N'9', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'关联供应商', N'supplier', N'supplier', N'2', N'6', N'8', N'8', N'63', N'150', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'1001', N'0', N'用户类别', N'type', N'type', N'2', N'6', N'5', N'5', N'61', N'80', N'1', N'1002', N't_sms_userType', N'', N'typeId', N'name', N'number', N'INNER JOIN', N'', N'', null, null, N'15', N'8', N'0', N'0', N'0', N'1'), (N'1001', N'0', N'内码', N'userId', N'userId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1002', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1002', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1002', N'0', N'内码', N'typeId', N'typeId', N'1', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1003', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1003', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1003', N'0', N'内码', N'roleId', N'roleId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1003', N'0', N'是否禁用', N'status', N'status', N'4', N'3', N'13', N'13', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1003', N'0', N'角色类别', N'type', N'type', N'2', N'6', N'5', N'5', N'61', N'80', N'1', N'1004', N't_sms_roleType', N'', N'typeId', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'8', N'12', N'0', N'0', N'1'), (N'1004', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1004', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1004', N'0', N'内码', N'typeId', N'typeId', N'1', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'地址', N'address', N'address', N'2', null, N'15', N'15', N'63', N'300', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'80', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'工商注册号', N'brno', N'brno', N'2', null, N'6', N'6', N'0', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'分类', N'categoryId', N'categoryId', N'2', N'6', N'4', N'4', N'63', N'60', N'1', N'1006', N't_sms_category', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'城市', N'city', N'city', N'2', N'6', N'12', N'12', N'63', N'80', N'1', N'1008', N't_sms_city', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'联系人', N'contactPerson', N'contactPerson', N'2', null, N'16', N'16', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'法人代表', N'corp', N'corp', N'2', null, N'5', N'5', N'0', N'70', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'国家', N'country', N'country', N'2', N'6', N'11', N'11', N'63', N'80', N'1', N'1009', N't_sms_country', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'区县', N'county', N'county', N'2', N'6', N'14', N'14', N'63', N'80', N'1', N'1010', N't_sms_county', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'40', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'行业', N'industryId', N'industryId', N'2', N'6', N'10', N'10', N'63', N'120', N'1', N'1012', N't_sms_industry', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'手机号码', N'mobile', N'mobile', N'2', N'8', N'17', N'17', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'200', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'省份', N'province', N'province', N'2', N'6', N'13', N'13', N'63', N'80', N'1', N'1015', N't_sms_province', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'18', N'18', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'1005', N'0', N'状态', N'status', N'status', N'2', N'6', N'18', N'18', N'63', N'80', N'1', N'1026', N't_sms_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'1005', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'99', N'99', N'63', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'15', N'1', N'0', N'1'), (N'1005', N'0', N'税种', N'taxCategoryId', N'taxCategoryId', N'2', N'6', N'7', N'7', N'63', N'70', N'1', N'1017', N't_sms_taxCategory', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1005', N'0', N'税务登记号', N'taxId', N'taxId', N'2', null, N'9', N'9', N'63', N'120', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'1', N'0', N'1'), (N'1005', N'0', N'税率', N'taxRate', N'taxRate', N'1', N'1', N'8', N'8', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1006', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1006', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1006', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'是否控制', N'isControl', N'isControl', N'4', N'3', N'4', N'4', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'是否必须', N'isMust', N'isMust', N'4', N'3', N'5', N'5', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1007', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'6', N'6', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'1007', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'99', N'99', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'1008', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1008', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1008', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1009', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1009', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1009', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1010', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1010', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1010', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1011', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1011', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1011', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1012', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1012', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1012', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'是否高值', N'highConsumable', N'highConsumable', N'4', N'3', N'5', N'5', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'是否批次', N'isLotNumber', N'isLotNumber', N'4', N'3', N'6', N'6', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1013', N'0', N'规格型号', N'specification', N'specification', N'2', null, N'4', N'4', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'1014', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1014', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1014', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1015', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1015', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1015', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1016', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1016', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1016', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1017', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1017', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1017', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1018', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1018', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1018', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1021', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1021', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1021', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'是否控制', N'isControl', N'isControl', N'4', N'3', N'4', N'4', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'是否必须', N'isMust', N'isMust', N'4', N'3', N'4', N'4', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1023', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'6', N'6', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'1023', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'99', N'99', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'1024', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1024', N'0', N'手机号码', N'mobile', N'mobile', N'2', null, N'15', N'15', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1')
GO
INSERT INTO [t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (N'1024', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1024', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1025', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'1025', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1025', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1026', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'1026', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'1026', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'实际含税单价', N'actualTaxPrice', N'actualTaxPrice', N'1', null, N'10', N'10', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'单据状态', N'baseStatus', N'baseStatus', N'2', N'6', N'13', N'13', N'447', N'150', N'1', N'1021', N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'0', N'订单日期', N'bizDate', N'bizDate', N'3', N'12', N'2', N'2', N'447', N'120', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'确认交货日期', N'confirmDate', N'confirmDate', N'3', N'12', N'15', N'15', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'确认数量', N'confirmQty', N'confirmQty', N'1', null, N'14', N'14', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'供应商是否接单', N'confirmTick', N'confirmTick', N'4', N'3', N'17', N'17', N'447', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'供应商接单时间', N'confirmTickDate', N'confirmTickDate', N'3', N'12', N'12', N'12', N'447', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'制单日期', N'createTime', N'createTime', N'3', N'12', N'10', N'10', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'币别', N'currency', N'currency', N'2', N'6', N'7', N'7', N'447', N'150', N'1', N'1011', N't_sms_currency', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'1', N'交货日期', N'deliveryDate', N'deliveryDate', N'3', N'12', N'13', N'13', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'折扣额', N'discountAmount', N'discountAmount', N'1', null, N'11', N'11', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'折扣率', N'discountRate', N'discountRate', N'1', null, N'7', N'7', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'是否高值', N'', N'highConsumable', N'4', N'3', N'3', N'2', N'0', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'0'), (N'2019', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'发货数量', N'invoiceQty', N'invoiceQty', N'1', null, N'5', N'5', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'是否含税', N'isInTax', N'isInTax', N'4', N'3', N'14', N'14', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'是否批次', N'', N'isLotNumber', N'4', N'3', N'3', N'2', N'0', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'0'), (N'2019', N'0', N'是否加急', N'isQuicken', N'isQuicken', N'4', N'3', N'15', N'15', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'本位币金额', N'localAmount', N'localAmount', N'1', null, N'12', N'12', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'物料名称', N'material', N'material', N'2', N'6', N'2', N'2', N'447', N'80', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'1', N'物料编码', N'', N'materialNumber', N'2', null, N'3', N'2', N'447', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'2019', N'0', N'单据编号', N'number', N'number', N'2', null, N'1', N'1', N'447', N'120', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'订单内码', N'parent', N'parent', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'价格', N'price', N'price', N'1', null, N'6', N'6', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'采购员', N'purchasePerson', N'purchasePerson', N'2', N'6', N'4', N'4', N'447', N'150', N'1', N'1024', N't_sms_employee', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'1', N'数量', N'qty', N'qty', N'1', null, N'4', N'4', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'采购模式', N'saleProxy', N'saleProxy', N'2', N'6', N'3', N'3', N'447', N'60', N'1', N'1021', N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'1', N'行号', N'seq', N'seq', N'2', null, N'1', N'1', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'规格型号', N'', N'specification', N'2', null, N'3', N'2', N'447', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'2019', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'5', N'5', N'447', N'200', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2019', N'1', N'税额', N'tax', N'tax', N'1', null, N'11', N'11', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'含税单价', N'taxPrice', N'taxPrice', N'1', null, N'9', N'9', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'税率', N'taxRate', N'taxRate', N'1', null, N'8', N'8', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'HRP确认接单时间', N'tickDate', N'tickDate', N'3', N'12', N'11', N'11', N'447', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'HRP确认是否接单', N'tickType', N'tickType', N'4', N'3', N'16', N'16', N'447', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'金额', N'totalAmount', N'totalAmount', N'2', null, N'9', N'9', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'税额', N'totalTax', N'totalTax', N'2', null, N'6', N'6', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'0', N'价税合计', N'totalTaxAmount', N'totalTaxAmount', N'2', null, N'8', N'8', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2019', N'1', N'基本计量单位', N'unit', N'unit', N'2', N'6', N'3', N'3', N'447', N'60', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2020', N'1', N'实发数量', N'actualQty', N'actualQty', N'1', null, N'12', N'12', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'金额', N'amount', N'amount', N'1', null, N'15', N'15', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'个体码', N'code', N'code', N'2', null, N'8', N'8', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2020', N'0', N'发货日期', N'Date', N'Date', N'3', N'12', N'2', N'7', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'批号', N'dyBatchNum', N'dyBatchNum', N'2', null, N'7', N'7', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'生产厂家', N'dyManufacturer', N'dyManufacturer', N'2', null, N'14', N'14', N'447', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'11', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'生产日期', N'dyProDate', N'dyProDate', N'3', N'12', N'13', N'13', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'100', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'有效期', N'effectiveDate', N'effectiveDate', N'3', N'12', N'16', N'16', N'447', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'是否高值', N'', N'highConsumable', N'4', N'3', N'5', N'5', N'0', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'highConsumable', N'', N'LFET JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'2020', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'是否批次', N'', N'isLotNumber', N'4', N'3', N'5', N'5', N'0', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'isLotNumber', N'', N'LFET JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'2020', N'0', N'物流公司', N'logistics', N'logistics', N'2', null, N'1', N'5', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'2020', N'0', N'物流单号', N'logisticsNo', N'logisticsNo', N'2', null, N'1', N'6', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'0', N'0', N'0', N'1'), (N'2020', N'1', N'批次', N'lot', N'lot', N'1', null, N'6', N'6', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'物料名称', N'material', N'material', N'2', N'6', N'3', N'4', N'447', N'120', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'11', N'15', N'0', N'0', N'1'), (N'2020', N'0', N'单据编号', N'number', N'number', N'2', null, N'1', N'2', N'447', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'采购订单内码', N'orderId', N'orderId', N'2', null, N'2', N'2', N'0', N'100', N'4', N'0', N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'采购订单号', N'', N'orderNumber', N'2', null, N'2', N'2', N'447', N'100', N'5', N'0', N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'0'), (N'2020', N'1', N'采购订单行号', N'orderSeq', N'orderSeq', N'2', null, N'3', N'3', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'发货单内码', N'parent', N'parent', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'价格', N'price', N'price', N'1', null, N'9', N'9', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'应发数量', N'qty', N'qty', N'1', null, N'11', N'11', N'48', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'15', N'0', N'0', N'0'), (N'2020', N'1', N'产品注册号', N'registrationNo', N'registrationNo', N'2', null, N'15', N'15', N'447', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2020', N'0', N'订单类型', N'saleProxy', N'saleProxy', N'2', N'6', N'3', N'3', N'447', N'60', N'1', N'1021', N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'行号', N'seq', N'seq', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'规格型号', N'', N'specification', N'2', null, N'5', N'5', N'447', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'2020', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'7', N'3', N'447', N'100', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'2020', N'0', N'是否发送到医院', N'type', N'type', N'4', N'3', N'5', N'5', N'3', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'15', N'0', N'0', N'1'), (N'2020', N'1', N'单位', N'unit', N'unit', N'2', N'6', N'10', N'10', N'447', N'80', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'实收数量', N'actualQty', N'actualQty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'金额', N'amount', N'amount', N'1', null, N'15', N'15', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'5', N'0', N'0', N'1'), (N'2021', N'0', N'单据状态', N'baseStatus', N'baseStatus', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1021', N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2021', N'0', N'收货日期', N'bizDate', N'bizDate', N'3', N'12', N'2', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'生产厂家', N'dyManufacturer', N'dyManufacturer', N'2', null, N'13', N'13', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'11', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'生产日期', N'dyProDate', N'dyProDate', N'3', N'12', N'12', N'12', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'100', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'有效期', N'effectiveDate', N'effectiveDate', N'3', N'12', N'16', N'16', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2021', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'个体码', N'innercode', N'innercode', N'2', null, N'8', N'8', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'批次', N'lot', N'lot', N'1', null, N'6', N'6', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'物料名称', N'material', N'material', N'2', N'6', N'3', N'4', N'63', N'120', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'11', N'15', N'0', N'0', N'1'), (N'2021', N'0', N'收货单号', N'number', N'number', N'2', null, N'1', N'2', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'采购订单内码', N'orderId', N'orderId', N'2', null, N'2', N'2', N'0', N'100', N'4', N'0', N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'采购订单号', N'', N'orderNumber', N'2', null, N'2', N'2', N'63', N'100', N'5', N'0', N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'0'), (N'2021', N'1', N'采购订单行号', N'orderSeq', N'orderSeq', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'收货单内码', N'parent', N'parent', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'价格', N'price', N'price', N'1', null, N'9', N'9', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'应收数量', N'qty', N'qty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'合格数量', N'qualifiedQty', N'qualifiedQty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'产品注册号', N'registrationNo', N'registrationNo', N'2', null, N'14', N'14', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2021', N'1', N'行号', N'seq', N'seq', N'2', null, N'1', N'1', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1')
GO
INSERT INTO [t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (N'2021', N'0', N'源单据类型', N'sourceBillType', N'sourceBillType', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1026', N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2021', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'7', N'7', N'63', N'200', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2021', N'1', N'单位', N'unit', N'unit', N'2', N'6', N'10', N'10', N'63', N'80', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'2021', N'1', N'不合格数量', N'unqualifiedQty', N'unqualifiedQty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'入库数量', N'actualQty', N'actualQty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'金额', N'amount', N'amount', N'1', null, N'15', N'15', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'5', N'0', N'0', N'1'), (N'2022', N'0', N'单据状态', N'baseStatus', N'baseStatus', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1021', N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2022', N'0', N'入库日期', N'bizDate', N'bizDate', N'3', N'12', N'2', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'生产厂家', N'dyManufacturer', N'dyManufacturer', N'2', null, N'13', N'13', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'11', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'生产日期', N'dyProDate', N'dyProDate', N'3', N'12', N'12', N'12', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'100', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'有效期', N'effectiveDate', N'effectiveDate', N'3', N'12', N'16', N'16', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'个体码', N'innercode', N'innercode', N'2', null, N'8', N'8', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'批次', N'lot', N'lot', N'1', null, N'6', N'6', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'物料名称', N'material', N'material', N'2', N'6', N'3', N'4', N'63', N'120', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'11', N'15', N'0', N'0', N'1'), (N'2022', N'0', N'入库单号', N'number', N'number', N'2', null, N'1', N'2', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'采购订单内码', N'orderId', N'orderId', N'2', null, N'2', N'2', N'0', N'100', N'4', N'0', N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'采购订单号', N'', N'orderNumber', N'2', null, N'2', N'2', N'63', N'100', N'5', N'0', N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'0'), (N'2022', N'1', N'采购订单行号', N'orderSeq', N'orderSeq', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'入库单内码', N'parent', N'parent', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'价格', N'price', N'price', N'1', null, N'9', N'9', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2022', N'1', N'产品注册号', N'registrationNo', N'registrationNo', N'2', null, N'14', N'14', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'1', N'行号', N'seq', N'seq', N'2', null, N'1', N'1', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2022', N'0', N'源单据类型', N'sourceBillType', N'sourceBillType', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1026', N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2022', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'7', N'7', N'63', N'200', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2022', N'1', N'单位', N'unit', N'unit', N'2', N'6', N'10', N'10', N'63', N'80', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'2023', N'0', N'单据状态', N'baseStatus', N'baseStatus', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1021', N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2023', N'0', N'退货日期', N'bizDate', N'bizDate', N'3', N'12', N'2', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'2023', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2023', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'2023', N'1', N'物料名称', N'material', N'material', N'2', N'6', N'3', N'4', N'63', N'120', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'11', N'15', N'0', N'0', N'1'), (N'2023', N'0', N'退货单号', N'number', N'number', N'2', null, N'1', N'2', N'63', N'100', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'1'), (N'2023', N'1', N'采购订单内码', N'orderId', N'orderId', N'2', null, N'2', N'2', N'0', N'100', N'4', N'0', N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', null, null, N'0', N'20', N'15', N'0', N'0', N'1'), (N'2023', N'1', N'采购订单号', N'', N'orderNumber', N'2', null, N'2', N'2', N'63', N'100', N'5', N'0', N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', null, null, N'15', N'20', N'15', N'0', N'0', N'0'), (N'2023', N'1', N'采购订单行号', N'orderSeq', N'orderSeq', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'15', N'0', N'0', N'1'), (N'2023', N'1', N'退货单内码', N'parent', N'parent', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2023', N'1', N'退货数量', N'returnQty', N'returnQty', N'1', null, N'11', N'11', N'63', N'80', N'0', N'0', N'', null, N'', null, null, N'', N'', null, null, null, N'15', N'4', N'0', N'0', N'0', N'1'), (N'2023', N'0', N'源单据类型', N'sourceBillType', N'sourceBillType', N'2', N'6', N'14', N'14', N'63', N'150', N'1', N'1026', N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2023', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'7', N'7', N'63', N'200', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'1', N'0', N'0', N'1'), (N'2023', N'1', N'单位', N'unit', N'unit', N'2', N'6', N'10', N'10', N'63', N'80', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'0', N'11', N'15', N'0', N'0', N'1'), (N'3010', N'0', N'发证机关', N'authOrg', N'authOrg', N'2', null, N'3', N'5', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'100', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'起始日期', N'beginDate', N'beginDate', N'3', N'12', N'4', N'6', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'备注', N'description', N'description', N'2', N'11', N'7', N'9', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'255', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'结束日期', N'endDate', N'endDate', N'3', N'12', N'5', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'255', N'0', N'0', N'0', N'1'), (N'3010', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'是否控制', N'isControl', N'isControl', N'4', N'3', N'9', N'11', N'63', N'80', N'3', N'1007', N't_sms_supplier_license_type', N'', N'', N'isControl', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'是否必须', N'isMust', N'isMust', N'4', N'3', N'8', N'10', N'63', N'80', N'3', N'1007', N't_sms_supplier_license_type', N'', N'', N'isMust', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'200', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'代码', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'3010', N'1', N'资质', N'parent', N'parent', N'2', null, N'2', N'2', N'0', N'80', N'0', N'0', N'', N'', N'', null, null, N'', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'是否停用', N'prohibited', N'prohibited', N'4', N'3', N'10', N'12', N'63', N'80', N'0', N'0', N'', N'', N'', null, N'', N'', N'', N'', null, null, N'15', N'1', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'11', N'13', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'3010', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'6', N'8', N'63', N'80', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3010', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'99', N'99', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'3010', N'0', N'证书类别', N'type', N'type', N'2', N'6', N'2', N'4', N'63', N'80', N'1', N'1007', N't_sms_supplier_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'8', N'0', N'0', N'0', N'1'), (N'3010', N'1', N'附件', N'url', N'url', N'2', null, N'3', N'3', N'63', N'150', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'255', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'发证机关', N'authOrg', N'authOrg', N'2', null, N'3', N'5', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'100', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'起始日期', N'beginDate', N'beginDate', N'3', N'12', N'4', N'6', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'备注', N'description', N'description', N'2', N'11', N'8', N'10', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'255', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'结束日期', N'endDate', N'endDate', N'3', N'12', N'5', N'7', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'255', N'0', N'0', N'0', N'1'), (N'3020', N'1', N'内码', N'id', N'entryId', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'是否控制', N'isControl', N'isControl', N'4', N'3', N'10', N'12', N'63', N'80', N'3', N'1023', N't_sms_item_license_type', N'', N'', N'isControl', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'是否必须', N'isMust', N'isMust', N'4', N'3', N'9', N'11', N'63', N'80', N'3', N'1023', N't_sms_item_license_type', N'', N'', N'isMust', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'物料', N'material', N'material', N'2', N'6', N'7', N'9', N'63', N'80', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'证书名称', N'name', N'name', N'2', null, N'3', N'3', N'63', N'200', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'证书编号', N'number', N'number', N'2', null, N'2', N'2', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'20', N'0', N'0', N'0', N'1'), (N'3020', N'1', N'物料证件', N'parent', N'parent', N'2', null, N'2', N'2', N'0', N'80', N'0', N'0', null, N'', null, null, null, null, N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'是否禁用', N'prohibited', N'prohibited', N'4', N'3', N'11', N'13', N'63', N'80', N'0', null, null, N'', N'', null, N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'12', N'14', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'3020', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'6', N'8', N'63', N'80', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'50', N'0', N'0', N'0', N'1'), (N'3020', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'99', N'99', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'1', N'0', N'1'), (N'3020', N'0', N'证书类别', N'type', N'type', N'2', N'6', N'2', N'4', N'63', N'80', N'1', N'1023', N't_sms_item_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', null, null, N'15', N'8', N'0', N'0', N'0', N'1'), (N'3020', N'1', N'附件路径', N'url', N'url', N'2', null, N'3', N'3', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'255', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'生效日期', N'effectualDate', N'effectualDate', N'3', N'12', N'10', N'10', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'内码', N'id', N'id', N'2', null, N'1', N'1', N'0', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'4', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'是否停用', N'isStopped', N'isStopped', N'4', N'3', N'12', N'12', N'63', N'80', N'0', N'0', N'', N'', N'', null, N'', N'', N'', N'', null, null, N'15', N'1', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'物料', N'materialItem', N'materialItem', N'2', N'6', N'3', N'3', N'63', N'80', N'1', N'1013', N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', null, null, N'15', N'50', N'0', N'1', N'0', N'1'), (N'3030', N'0', N'物料编码', N'', N'materialNumber', N'2', null, N'4', N'4', N'63', N'80', N'3', N'1013', N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'3030', N'0', N'基本计量单位', N'measureUnit', N'measureUnit', N'2', N'6', N'7', N'7', N'63', N'40', N'1', N'1018', N't_sms_unit', N'', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'采购计量单位', N'purMeasureUnit', N'purMeasureUnit', N'2', N'6', N'6', N'6', N'63', N'40', N'1', N'1018', N't_sms_unit', N't_sms_unit_base', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'审核状态', N'review', N'review', N'4', N'3', N'13', N'13', N'3', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'规格型号', N'', N'specification', N'2', null, N'5', N'5', N'63', N'80', N'3', N'1013', N't_sms_item', N'', null, N'specification', N'', N'', N'', N'', null, null, N'0', N'100', N'15', N'0', N'0', N'0'), (N'3030', N'0', N'供应商', N'supplier', N'supplier', N'2', N'6', N'2', N'2', N'63', N'80', N'1', N'1005', N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', null, null, N'15', N'50', N'0', N'1', N'0', N'1'), (N'3030', N'0', N'供货比例', N'supplierRate', N'supplierRate', N'1', N'1', N'8', N'8', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'同步状态', N'syncStatus', N'syncStatus', N'4', N'3', N'14', N'14', N'1', N'50', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'1', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'含税价格', N'taxPrice', N'taxPrice', N'1', N'1', N'9', N'9', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', N'0', N'0', N'0', N'1'), (N'3030', N'0', N'失效日期', N'uneffectualDate', N'uneffectualDate', N'3', N'12', N'11', N'11', N'63', N'80', N'0', N'0', N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'15', N'100', N'0', N'0', N'0', N'1'), (N'14001', N'0', N'入口', N'clazz', N'clazz', N'2', null, N'35', N'35', N'63', N'330', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1'), (N'14001', N'0', N'主键', N'id', N'id', N'1', null, N'5', N'5', N'0', N'80', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'11', null, N'0', N'0', N'1'), (N'14001', N'0', N'IP', N'ip', N'ip', N'2', null, N'20', N'20', N'63', N'80', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1'), (N'14001', N'0', N'描述', N'message', N'message', N'2', null, N'30', N'30', N'63', N'150', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1'), (N'14001', N'0', N'方法', N'method', N'method', N'2', null, N'40', N'40', N'63', N'60', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1'), (N'14001', N'0', N'时间', N'operateTime', N'operateTime', N'3', null, N'25', N'25', N'63', N'150', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'255', null, N'0', N'0', N'1'), (N'14001', N'0', N'参数', N'params', N'params', N'2', null, N'45', N'45', N'63', N'500', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1'), (N'14001', N'0', N'用户', N'userName', N'userName', N'2', null, N'15', N'15', N'63', N'80', N'0', null, N'', N'', N'', N'', N'', N'', N'', N'', null, null, N'0', N'50', null, N'0', N'0', N'1')
GO


-- ----------------------------
-- Records of t_sms_objectAccessType
-- ----------------------------

INSERT INTO [t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (N'10', N'1005', N'0', N'查看', N'1', N'1', N'3', N'用户管理查看权限-由此权限才可进入用户管理界面'), (N'10', N'1005', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'10', N'1005', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'10', N'1005', N'3', N'删除', N'8', N'1', N'3', N'删除'), (N'10', N'1005', N'5', N'禁用', N'64', N'1', N'3', N'禁用'), (N'10', N'1005', N'6', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'10', N'1010', N'0', N'查看', N'1', N'1', N'3', N'用户类别查看权限-由此权限才可进入用户类别界面'), (N'10', N'1015', N'0', N'查看', N'1', N'1', N'3', N'角色查看权限'), (N'10', N'1015', N'1', N'新增', N'2', N'1', N'3', N'角色新增权限'), (N'10', N'1015', N'2', N'修改', N'4', N'1', N'3', N'角色修改权限'), (N'10', N'1015', N'3', N'删除', N'8', N'1', N'3', N'角色删除权限'), (N'10', N'1020', N'0', N'查看', N'1', N'1', N'3', N'角色类别管理查看权限-由此权限才可进入角色类别界面'), (N'10', N'1025', N'0', N'查看', N'1', N'1', N'3', N'角色权限查看权限-由此权限才可进入角色权限界面'), (N'10', N'1025', N'1', N'授权', N'4', N'1', N'3', N'修改角色权限明细的权限(授权)'), (N'10', N'1030', N'0', N'查看', N'1', N'1', N'3', N'供应商管理查看权限'), (N'10', N'1030', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'10', N'1030', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'10', N'1030', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'10', N'1030', N'4', N'审核', N'16', N'1', N'1', N'审核'), (N'10', N'1030', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'10', N'1030', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'10', N'1030', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'10', N'1030', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'10', N'1035', N'0', N'查看', N'1', N'1', N'3', N'供应商类别查看权限'), (N'10', N'1045', N'0', N'查看', N'1', N'1', N'3', N'行业查看权限'), (N'10', N'1050', N'0', N'查看', N'1', N'1', N'3', N'结算方式查看权限'), (N'10', N'1055', N'0', N'查看', N'1', N'1', N'3', N'付款方式查看权限'), (N'10', N'1060', N'0', N'查看', N'1', N'1', N'3', N'物料查看权限'), (N'10', N'1065', N'0', N'查看', N'1', N'1', N'3', N'税种查看权限'), (N'10', N'1070', N'0', N'查看', N'1', N'1', N'3', N'城市查看权限'), (N'10', N'1075', N'0', N'查看', N'1', N'1', N'3', N'国家查看权限'), (N'10', N'1080', N'0', N'查看', N'1', N'1', N'3', N'区县查看权限'), (N'10', N'1085', N'0', N'查看', N'1', N'1', N'3', N'省份查看权限
'), (N'20', N'2005', N'0', N'查看', N'1', N'1', N'3', N'采购订单查看权限'), (N'20', N'2005', N'1', N'生成发货单', N'2', N'1', N'3', N'生成发货单'), (N'20', N'2010', N'0', N'查看', N'1', N'1', N'3', N'订单追踪查看权限'), (N'30', N'3005', N'0', N'查看', N'1', N'1', N'3', N'供应商资质维护查看权限'), (N'30', N'3005', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3005', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3005', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3005', N'4', N'审核', N'16', N'1', N'1', N'审核'), (N'30', N'3005', N'5', N'反审核', N'32', N'1', N'1', N'反审核'), (N'30', N'3005', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'30', N'3005', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'30', N'3005', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'30', N'3010', N'0', N'查看', N'1', N'1', N'3', N'物料证件维护查看权限'), (N'30', N'3010', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3010', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3010', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3010', N'4', N'审核', N'16', N'1', N'3', N'审核'), (N'30', N'3010', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'30', N'3010', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'30', N'3010', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'30', N'3010', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'30', N'3015', N'0', N'查看', N'1', N'1', N'3', N'供应商物料查询查看权限'), (N'30', N'3020', N'0', N'查看', N'1', N'1', N'3', N'供应商证件类别查看权限'), (N'30', N'3020', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3020', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3020', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3020', N'4', N'审核', N'16', N'1', N'3', N'审核'), (N'30', N'3020', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'30', N'3020', N'6', N'同步', N'256', N'1', N'3', N'禁用'), (N'30', N'3025', N'0', N'查看', N'1', N'1', N'3', N'物料证件类别查看权限'), (N'30', N'3025', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3025', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3025', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3025', N'4', N'审核', N'16', N'1', N'3', N'审核'), (N'30', N'3025', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'30', N'3025', N'6', N'同步', N'256', N'1', N'3', N'禁用'), (N'40', N'4005', N'0', N'查看', N'1', N'1', N'3', N'发货单查看权限'), (N'40', N'4005', N'1', N'新增', N'2', N'1', N'3', N'新增发货单'), (N'40', N'4005', N'2', N'修改', N'4', N'1', N'3', N'修改发货单'), (N'40', N'4005', N'3', N'删除', N'8', N'1', N'3', N'删除发货单'), (N'40', N'4005', N'4', N'发送到医院', N'256', N'1', N'3', N'发送到医院'), (N'40', N'4010', N'0', N'查看', N'1', N'1', N'3', N'收货单查看权限'), (N'40', N'4015', N'0', N'查看', N'1', N'1', N'3', N'入库单查看权限'), (N'40', N'4020', N'0', N'查看', N'1', N'1', N'3', N'退货单查看权限'), (N'60', N'6005', N'0', N'查看', N'1', N'1', N'3', N'订单统计查看'), (N'140', N'14005', N'0', N'查看', N'1', N'1', N'3', N'系统参数设置'), (N'140', N'14010', N'0', N'查看', N'1', N'1', N'3', N'系统日志查看'), (N'1000', N'100005', N'0', N'查看', N'1', N'1', N'3', N'API测试')
GO


-- ----------------------------
-- Records of t_sms_objectType
-- ----------------------------

INSERT INTO [t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (N'10', N'1005', N'10', N'1005', N'用户', N'用户管理', N'1001'), (N'10', N'1010', N'10', N'1010', N'用户类别', N'用户类别', N'1002'), (N'10', N'1015', N'10', N'1015', N'角色', N'角色管理', N'1003'), (N'10', N'1020', N'10', N'1020', N'角色类别', N'角色类别', N'1004'), (N'10', N'1025', N'10', N'1025', N'角色权限', N'角色权限', null), (N'10', N'1030', N'10', N'1030', N'供应商', N'供应商管理', N'1005'), (N'10', N'1035', N'10', N'1035', N'供应商分类', N'供应商分类', N'1006'), (N'10', N'1040', N'10', N'1040', N'证书', N'证书', null), (N'10', N'1045', N'10', N'1045', N'行业', N'行业', N'1012'), (N'10', N'1050', N'10', N'1050', N'结算方式', N'结算方式', N'1016'), (N'10', N'1055', N'10', N'1055', N'付款方式', N'付款方式', N'1014'), (N'10', N'1060', N'10', N'1060', N'物料', N'物料', N'1013'), (N'10', N'1065', N'10', N'1065', N'税种', N'税种', N'1017'), (N'10', N'1070', N'10', N'1070', N'城市', N'城市', N'1008'), (N'10', N'1075', N'10', N'1075', N'国家', N'国家', N'1009'), (N'10', N'1080', N'10', N'1080', N'区县', N'区县', N'1010'), (N'10', N'1085', N'10', N'1085', N'省份', N'省份', N'1015'), (N'20', N'2005', N'20', N'2005', N'采购订单', N'采购订单', N'2019'), (N'20', N'2010', N'20', N'2010', N'订单追踪', N'订单追踪', null), (N'30', N'3005', N'30', N'3005', N'供应商资质维护', N'供应商资质维护', N'3010'), (N'30', N'3010', N'30', N'3010', N'物料证件维护', N'物料证件维护', N'3020'), (N'30', N'3015', N'30', N'3015', N'供应商物料查询', N'供应商物料查询', N'3030'), (N'30', N'3020', N'30', N'3020', N'供应商证件类别', N'供应商证件类别', N'1007'), (N'30', N'3025', N'30', N'3025', N'物料证件类别', N'物料证件类别', N'1023'), (N'40', N'4005', N'40', N'4005', N'发货单', N'发货单', N'2020'), (N'40', N'4010', N'40', N'4010', N'收货单', N'收货单', N'2021'), (N'40', N'4015', N'40', N'4015', N'入库单', N'入库单', N'2022'), (N'40', N'4020', N'40', N'4020', N'退货单', N'退货单', N'2023'), (N'60', N'6005', N'60', N'6005', N'订单统计', N'订单统计', null), (N'140', N'14005', N'140', N'14005', N'参数设置', N'参数设置', null), (N'140', N'14010', N'140', N'14010', N'系统日志', N'系统日志', N'14001'), (N'1000', N'100005', N'1000', N'100005', N'API测试', N'API测试', null)
GO



-- ----------------------------
-- Records of t_sms_plugins
-- ----------------------------

INSERT INTO [t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (N'1', N'1001', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'0', N'用户管理插件'), (N'2', N'1002', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'1', N'用户管理插件'), (N'3', N'1005', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'2', N'供应商插件'), (N'4', N'2019', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'3', N'订单管理插件'), (N'5', N'1006', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'4', N'供应商分类插件'), (N'6', N'1007', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'5', N'证书插件'), (N'7', N'1008', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'6', N'城市插件'), (N'8', N'1009', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'7', N'国家插件'), (N'9', N'1010', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'8', N'区县插件'), (N'10', N'1011', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'9', N'币别插件'), (N'11', N'1012', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'10', N'行业插件'), (N'12', N'1013', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'11', N'物料插件'), (N'13', N'1014', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'12', N'付款方式插件'), (N'14', N'1015', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'13', N'省份插件'), (N'15', N'1016', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'14', N'结算方式插件'), (N'16', N'1017', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'15', N'税种插件'), (N'17', N'1018', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'16', N'单位插件'), (N'18', N'1024', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'17', N'采购员插件'), (N'19', N'2019', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'18', N'采购订单详情插件'), (N'20', N'2020', N'com.kingdee.eas.hrp.sms.service.plugin.impl.BillPlugin', N'19', N'发货单插件'), (N'21', N'3010', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'20', N'供应商资质插件'), (N'22', N'3020', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'21', N'物料证件插件'), (N'23', N'3030', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'22', N'中标库插件'), (N'24', N'1021', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'23', N'采购模式插件'), (N'25', N'1025', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'24', N'单据状态插件'), (N'26', N'1023', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'25', N'物料证件类别插件'), (N'27', N'3040', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'26', N'供应商资质附件插件'), (N'28', N'3050', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'27', N'供应商物料附件插件'), (N'29', N'1026', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'28', N'原单据类型插件'), (N'30', N'1003', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'1', N'角色插件'), (N'31', N'2020', N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', N'29', N'发货单插件')
GO


-- ----------------------------
-- Records of t_sms_role
-- ----------------------------

INSERT INTO [t_sms_role] ([roleId], [name], [number], [type], [status]) VALUES (N'0DJBujU4RkyvRhhcdEG1VRADEAM=', N'test', N'test', N'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=', N'0'), (N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'系统管理员', N'admin', N'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=', N'0'), (N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'设备供应商', N'supplier', N'f1sGInqJq0aUNY5MmpKM8RAEEAQ=', N'0')
GO


-- ----------------------------
-- Records of t_sms_roleType
-- ----------------------------

INSERT INTO [t_sms_roleType] ([typeId], [number], [name]) VALUES (N'f1sGInqJq0aUNY5MmpKM8RAEEAQ=', N'CUS', N'供应商角色'), (N'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=', N'SYS', N'系统角色')
GO


-- ----------------------------
-- Records of t_sms_serial_number
-- ----------------------------

SET IDENTITY_INSERT [t_sms_serial_number] ON
GO
INSERT INTO [t_sms_serial_number] ([id], [classId], [year], [number]) VALUES (N'1', N'2020', N'2017', N'1')
GO
SET IDENTITY_INSERT [t_sms_serial_number] OFF
GO


-- ----------------------------
-- Records of t_sms_status
-- ----------------------------

INSERT INTO [t_sms_status] ([id], [name], [number]) VALUES (N'0', N'未核准', null), (N'1', N'核准', null), (N'2', N'禁用', null)
GO


-- ----------------------------
-- Records of t_sms_subMessage
-- ----------------------------

INSERT INTO [t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (N'1', N'10', N'', N'数字', N'1', N'0'), (N'1', N'15', N'', N'新增时对于平台用户锁定', N'1', N'0'), (N'1', N'20', N'', N'对于平台用户列表显示', N'1', N'0'), (N'1', N'25', N'', N'新增时对于平台用户必填', N'1', N'0'), (N'1', N'30', N'', N'小数', N'1', N'0'), (N'1', N'35', N'', N'引用基础资料', N'1', N'0'), (N'2', N'10', N'', N'文本值', N'1', N'0'), (N'2', N'15', N'', N'编辑时对于平台用户锁定', N'1', N'0'), (N'2', N'20', N'', N'对于供应商用户列表显示', N'1', N'0'), (N'2', N'25', N'', N'编辑时对于平台用户必填', N'1', N'0'), (N'2', N'35', N'', N'引用辅助属性', N'1', N'0'), (N'3', N'10', N'', N'日期时间', N'1', N'0'), (N'3', N'30', N'', N'checkbox', N'1', N'0'), (N'3', N'35', N'', N'引用基础资料的附加属性', N'1', N'0'), (N'4', N'10', N'', N'布尔', N'1', N'0'), (N'4', N'15', N'', N'新增时对于供应商用户锁定', N'1', N'0'), (N'4', N'20', N'', N'编辑时对于平台用户显示', N'1', N'0'), (N'4', N'25', N'', N'新增时对于供应商用户必填', N'1', N'0'), (N'4', N'35', N'', N'普通引用其他表-关联查询', N'1', N'0'), (N'5', N'10', N'', N'密码', N'1', N'0'), (N'5', N'30', N'', N'下拉框', N'1', N'0'), (N'5', N'35', N'', N'普通引用其他表的其他字段-主要为了避免为4即引用他表数据时，需引用多个字段时关联表重复问题。依附于=4时存在', N'1', N'0'), (N'6', N'30', N'', N'F7选择框', N'1', N'0'), (N'7', N'30', N'', N'级联选择器', N'1', N'0'), (N'8', N'15', N'', N'编辑时对于供应商用户锁定', N'1', N'0'), (N'8', N'20', N'', N'编辑时对于供应商用户显示', N'1', N'0'), (N'8', N'25', N'', N'编辑时对于供应商用户必填', N'1', N'0'), (N'8', N'30', N'', N'手机号码', N'1', N'0'), (N'9', N'30', N'', N'座机电话', N'1', N'0'), (N'10', N'30', N'', N'普通文本', N'1', N'0'), (N'11', N'30', N'', N'多行文本', N'1', N'0'), (N'12', N'30', N'', N'日期时间', N'1', N'0'), (N'16', N'20', N'', N'新增时对于平台用户显示', N'1', N'0'), (N'32', N'20', N'', N'新增时对于供应商用户显示', N'1', N'0'), (N'64', N'20', N'', N'是否在列表中显示(子表模板独有,子表数据显示在表头列表中)', N'1', N'0'), (N'98', N'30', N'', N'男：女', N'1', N'0'), (N'99', N'30', N'', N'密码控件', N'1', N'0'), (N'128', N'20', N'', N'平台用户详情查看时显示', N'1', N'0'), (N'256', N'20', N'', N'供应商用户详情查看时显示', N'1', N'0')
GO


-- ----------------------------
-- Records of t_sms_subMesType
-- ----------------------------

INSERT INTO [t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (N'10', N'字段类型', N'基础资料元数据配置中的dataType值参考这里，一般可设置为该字段在数据库里面的储存类型对应的java数据类型', N'1'), (N'15', N'字段锁定性', N'基础资料元数据配置中的lock值参考这里，用于前段页面控制字段可用性', N'1'), (N'20', N'字段数据权限控制类型', N'后端元数据配置中的disPlay值参考这里', N'1'), (N'25', N'字段必录性控制类型', N'后端元数据配置中的mustInput值参考这里', N'1'), (N'30', N'控件类型', N'控件类型-用于描述元数据在前端页面的展示结构,后端元数据配置中的ctrlType值参考这里', N'1'), (N'35', N'标示字段的引用类型', N'标示字段的引用类型，如引用基础资料、辅助资料，基础资料属性等', N'1')
GO

-- ----------------------------
-- Records of t_sms_validation
-- ----------------------------

INSERT INTO [t_sms_validation] ([a]) VALUES (N'1')
GO


-- ----------------------------
-- Records of t_sms_sys_profile
-- ----------------------------

INSERT INTO [t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'FILE_PATH', N'上传文件保存目录', N'上传文件保存统一路径', N'C:\\file-upload\\files\\', N'12', N'{"ctlType":"text"}', N'1'), (N'sys', N'FILE_URL', N'服务器配置的文件映射地址', N'文件映射', N'/sms/attachment/', N'11', N'{"ctlType":"text"}', N'1'), (N'sys', N'hrp-login-authPattern', N'webservice登录验证方式', N'HRP公布的webservice登录验证方式', N'BaseDB', N'13', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-dbType', N'webservice登录数据库类型', N'HRP公布的webservice登录数据库类型', N'1', N'12', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-dcName', N'webservice登录数据中心', N'HRP公布的webservice登录数据中心', N'HRPTT', N'10', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-language', N'webservice登录语言', N'HRP公布的webservice登录语言', N'L2', N'11', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-namespace', N'webservice登录命名空间', N'HRP公布的webservice登录命名空间', N'http://10.0.1.37:56898/ormrpc/services/EASLogin', N'6', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-psw', N'webservice登录密码', N'HRP公布的webservice登录密码', N'kduser', N'8', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-slnName', N'webservice登录解决方案名称', N'HRP公布的webservice登录解决方案名称', N'eas', N'9', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-url', N'webservice登录地址', N'HRP公布的webservice登录地址', N'http://10.0.1.37:56898/ormrpc/services/EASLogin?wsdl', N'5', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-userName', N'webservice登录用户名称', N'HRP公布的webservice登录用户名称', N'user', N'7', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-header-namespace', N'webservice同步命名空间（头）', N'HRP公布的webservice命名空间（头）', N'http://login.webservice.bos.kingdee.com', N'4', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-mobie', N'同步HRP通知接收人电话', N'同步HRP通知接收人电话', N'18812345678', N'14', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-namespace', N'webservice同步命名空间', N'HRP公布的webservice命名空间', N'http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade', N'3', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-url', N'webservice同步地址', N'HRP公布的webservice地址', N'http://10.0.1.37:56898/ormrpc/services/WSDataSynWSFacade?wsdl', N'2', N'{"ctlType":"text"}', N'0')

-- ----------------------------
-- Records of t_sms_user_entry
-- ----------------------------

INSERT INTO [t_sms_user_entry] ([id], [parent], [mobile], [pic]) VALUES (N'1', N'z0RzgiTMV02vEntzvABoZhABEAE=', N'18617092729', null), (N'2', N'z0RzgiTMV02vEntzvABoZhABEAE=', N'15970077156', null)


-- ----------------------------
-- Records of t_sms_userType
-- ----------------------------

INSERT INTO [t_sms_userType] ([typeId], [number], [name]) VALUES (N'B3sMo22ZLkWApjO/oEeDOxACEAI=', N'CUS', N'业务用户'), (N'QpXq24FxxE6c3lvHMPyYCxACEAI=', N'SYS', N'系统用户')


-- ----------------------------
-- Records of t_sms_user
-- ----------------------------

INSERT INTO [t_sms_user] ([userId], [number], [name], [password], [type], [status], [role], [supplier], [token], [phone]) VALUES (N'WtmTpjCrEE6O0KlB6RGcyBABEAE=', N'admin', N'admin', N'202cb962ac59075b964b07152d234b70', N'QpXq24FxxE6c3lvHMPyYCxACEAI=', N'0', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'', N'23be6f0fcf4e44af8fbdf7f773526eee_cf39a4dd7b0843d08014bb6e4dccbfa1', N'18617092729')


COMMIT TRANSACTION
GO

