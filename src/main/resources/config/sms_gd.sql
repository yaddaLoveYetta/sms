USE [master]

GO

IF EXISTS (
	SELECT
		*
	FROM
		sysdatabases
	WHERE
		name = 'hrp-sms-gd'
) 
BEGIN
	DROP DATABASE [hrp-sms-gd]
END
GO

/****** Object:  Database [hrp-sms-gd]    Script Date: 09/19/2017 15:34:15 ******/
CREATE DATABASE [hrp-sms-gd] ON  PRIMARY 
( NAME = N'hrpsmsgd', FILENAME = N'D:\hrp-sms-gd_data.mdf' , SIZE = 20480KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'hrpsmsgd_data', FILENAME = N'D:\hrp-sms-gd_log.ldf' , SIZE = 20480KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO


ALTER DATABASE [hrp-sms-gd] SET COMPATIBILITY_LEVEL = 100
GO


IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
BEGIN
EXEC [hrp-sms-gd].[dbo].[sp_fulltext_database] @ACTION = 'enable'
END
GO


ALTER DATABASE [hrp-sms-gd] SET ANSI_NULL_DEFAULT OFF
GO


ALTER DATABASE [hrp-sms-gd] SET ANSI_NULLS OFF
GO


ALTER DATABASE [hrp-sms-gd] SET ANSI_PADDING OFF
GO


ALTER DATABASE [hrp-sms-gd] SET ANSI_WARNINGS OFF
GO


ALTER DATABASE [hrp-sms-gd] SET ARITHABORT OFF
GO


ALTER DATABASE [hrp-sms-gd] SET AUTO_CLOSE OFF
GO


ALTER DATABASE [hrp-sms-gd] SET AUTO_CREATE_STATISTICS ON
GO


ALTER DATABASE [hrp-sms-gd] SET AUTO_SHRINK OFF
GO


ALTER DATABASE [hrp-sms-gd] SET AUTO_UPDATE_STATISTICS ON
GO


ALTER DATABASE [hrp-sms-gd] SET CURSOR_CLOSE_ON_COMMIT OFF
GO


ALTER DATABASE [hrp-sms-gd] SET CURSOR_DEFAULT  GLOBAL
GO


ALTER DATABASE [hrp-sms-gd] SET CONCAT_NULL_YIELDS_NULL OFF
GO


ALTER DATABASE [hrp-sms-gd] SET NUMERIC_ROUNDABORT OFF
GO


ALTER DATABASE [hrp-sms-gd] SET QUOTED_IDENTIFIER OFF
GO


ALTER DATABASE [hrp-sms-gd] SET RECURSIVE_TRIGGERS OFF
GO


ALTER DATABASE [hrp-sms-gd] SET  ENABLE_BROKER
GO


ALTER DATABASE [hrp-sms-gd] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO


ALTER DATABASE [hrp-sms-gd] SET DATE_CORRELATION_OPTIMIZATION OFF
GO


ALTER DATABASE [hrp-sms-gd] SET TRUSTWORTHY OFF
GO


ALTER DATABASE [hrp-sms-gd] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO


ALTER DATABASE [hrp-sms-gd] SET PARAMETERIZATION SIMPLE
GO


ALTER DATABASE [hrp-sms-gd] SET READ_COMMITTED_SNAPSHOT OFF
GO


ALTER DATABASE [hrp-sms-gd] SET HONOR_BROKER_PRIORITY OFF
GO


ALTER DATABASE [hrp-sms-gd] SET  READ_WRITE
GO


ALTER DATABASE [hrp-sms-gd] SET RECOVERY FULL
GO


ALTER DATABASE [hrp-sms-gd] SET  MULTI_USER
GO


ALTER DATABASE [hrp-sms-gd] SET PAGE_VERIFY CHECKSUM
GO


ALTER DATABASE [hrp-sms-gd] SET DB_CHAINING OFF
GO


EXEC sys.sp_db_vardecimal_storage_format N'hrp-sms-gd', N'ON'
GO


USE [hrp-sms-gd]
GO


/****** Object:  UserDefinedFunction [dbo].[base64encode]    Script Date: 09/19/2017 15:34:16 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


CREATE FUNCTION [dbo].[base64encode]
(@source VARBINARY(6000))
RETURNS VARCHAR(8000)
AS
BEGIN
DECLARE @g_base64 AS VARCHAR(64)
DECLARE @l_result AS VARCHAR(8000)
DECLARE @l_length AS INT
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


/****** Object:  View [dbo].[vw_newid]    Script Date: 09/19/2017 15:34:17 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[vw_newid] AS 
SELECT NEWID() [FNEWID]
GO


/****** Object:  Table [dbo].[t_sms_validation]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_validation](
	[a] [char](1) NULL
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_userType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_userType](
	[typeId] [varchar](50) NOT NULL,
	[number] [varchar](20) NOT NULL,
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_userType', @level2type=N'COLUMN',@level2name=N'typeId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_userType'
GO


/****** Object:  Table [dbo].[t_sms_user]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_user](
	[userId] [varchar](50) NOT NULL,
	[number] [varchar](20) NOT NULL,
	[name] [varchar](20) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[type] [varchar](50) NOT NULL,
	[status] [tinyint] NOT NULL,
	[role] [varchar](50) NOT NULL,
	[supplier] [varchar](50) NULL,
	[token] [varchar](255) NOT NULL,
	[phone] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'userId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'password'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户类别(基础资料1002)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'type'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否禁用(0可用1禁用，默认0可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'status'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'引用角色基础资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'role'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'引用供应商基础资料，当type为供应商用户时必须有' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'hrp端与sms''交互数据通过token校验' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'token'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user'
GO


/****** Object:  Table [dbo].[t_sms_unit]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_unit](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit'
GO


/****** Object:  Table [dbo].[t_sms_taxCategory]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_taxCategory](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税种名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税种' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory'
GO


/****** Object:  Table [dbo].[t_sms_sys_profile]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_sys_profile](
	[category] [varchar](255) NOT NULL,
	[key] [varchar](255) NOT NULL,
	[name] [varchar](255) NOT NULL,
	[desc] [varchar](255) NULL,
	[value] [varchar](255) NOT NULL,
	[index] [int] NOT NULL,
	[explanation] [varchar](255) NOT NULL,
	[readOnly] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[category] ASC,
	[key] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数类别(通常按模块来分参数，记录模块标示)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'category'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'key'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数名称(尽量简短，不要太长)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'desc'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'value'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'参数选项,配置参数的类型及类型明细
{ctlType:checkBox,}
{ctlType:selector,list:[{0:不启用},{1:启用}]}
{ctlType:text,}
{ctlType:number,}' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'explanation'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否只读参数(只读参数不可修改)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'readOnly'
GO


/****** Object:  Table [dbo].[t_sms_supplier_license_type]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_license_type](
	[id] [varchar](50) NOT NULL,
	[number] [varchar](20) NULL,
	[name] [varchar](50) NULL,
	[isMust] [tinyint] NULL,
	[isControl] [tinyint] NULL,
	[syncStatus] [tinyint] NULL,
	[review] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证件内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'isMust'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'isControl'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商证件类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type'
GO


/****** Object:  Table [dbo].[t_sms_supplier_license_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_license_entry](
	[id] [varchar](50) NOT NULL,
	[parent] [varchar](50) NULL,
	[url] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'外键，关联主表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'附件存放路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'url'
GO


/****** Object:  Table [dbo].[t_sms_supplier_license]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_license](
	[id] [varchar](50) NOT NULL,
	[type] [varchar](50) NULL,
	[supplier] [varchar](50) NULL,
	[authOrg] [varchar](100) NULL,
	[description] [varchar](255) NULL,
	[beginDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[syncStatus] [tinyint] NULL,
	[review] [tinyint] NULL,
	[isMust] [tinyint] NULL,
	[isControl] [tinyint] NULL,
	[prohibited] [tinyint] NULL,
	[name] [varchar](80) NULL,
	[number] [nvarchar](80) NULL,
	[idNumber] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证件类型(关联t_sms_supplier_license_type表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'type'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商(关联t_sms_supplier表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发证机关' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'authOrg'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'description'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'起始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'beginDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'结束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'endDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'isMust'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'isControl'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否停用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'prohibited'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证件编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'idNumber'
GO


/****** Object:  Table [dbo].[t_sms_supplier_item_license_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_item_license_entry](
	[id] [varchar](50) NOT NULL,
	[parent] [varchar](50) NULL,
	[url] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'外键，关联主表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'附件存放路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'url'
GO


/****** Object:  Table [dbo].[t_sms_supplier_item_license]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_item_license](
	[id] [varchar](50) NOT NULL,
	[type] [varchar](50) NULL,
	[supplier] [varchar](50) NULL,
	[authOrg] [varchar](100) NULL,
	[description] [varchar](255) NULL,
	[beginDate] [datetime] NULL,
	[endDate] [datetime] NULL,
	[syncStatus] [tinyint] NULL,
	[review] [tinyint] NULL,
	[material] [varchar](50) NULL,
	[isMust] [tinyint] NULL,
	[isControl] [tinyint] NULL,
	[prohibited] [tinyint] NULL,
	[name] [varchar](80) NULL,
	[number] [nvarchar](80) NULL,
	[dyManufacturer] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料证件类型(关联t_sms_material_license_type表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'type'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商(关联t_sms_supplier表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发证机关' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'authOrg'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'description'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'起始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'beginDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'结束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'endDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料（关联表t_sms_item主键id）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'isMust'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'isControl'
GO


/****** Object:  Table [dbo].[t_sms_supplier]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[taxId] [varchar](100) NULL,
	[corp] [varchar](255) NULL,
	[brno] [varchar](255) NULL,
	[taxCategoryId] [varchar](50) NULL,
	[taxRate] [decimal](28, 10) NULL,
	[country] [varchar](50) NULL,
	[city] [varchar](50) NULL,
	[province] [varchar](50) NULL,
	[county] [varchar](50) NULL,
	[address] [varchar](255) NULL,
	[industryId] [varchar](50) NULL,
	[categoryId] [varchar](50) NULL,
	[status] [tinyint] NULL,
	[number] [varchar](50) NULL,
	[syncStatus] [tinyint] NULL,
	[review] [tinyint] NULL,
	[mobile] [varchar](20) NULL,
	[contactPerson] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'简称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税务登记号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'法人代表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'corp'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'工商注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'brno'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxCategoryId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxRate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'国家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'country'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'city'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'省份' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'province'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'区县' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'county'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'address'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行业ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'industryId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'分类ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'categoryId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'status'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商手机号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'mobile'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'联系人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'contactPerson'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier'
GO


/****** Object:  Table [dbo].[t_sms_subMesType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_subMesType](
	[typeId] [int] NOT NULL,
	[name] [varchar](20) NOT NULL,
	[desc] [varchar](100) NOT NULL,
	[systemType] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性ID(内码)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'typeId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N' 描述信息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'desc'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'标示系统类型或用户类型(0系统类型1:用户类型)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'systemType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性类别表
存储系统中枚举类型名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType'
GO


/****** Object:  Table [dbo].[t_sms_subMessage]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_subMessage](
	[detailId] [int] NOT NULL,
	[typeId] [int] NOT NULL,
	[number] [varchar](20) NOT NULL,
	[name] [varchar](200) NULL,
	[enable] [tinyint] NOT NULL,
	[index] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[detailId] ASC,
	[typeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'明细属性内码（1--1000保留做系统属性，1001以上用作用户属性）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'detailId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性类别ID(内码)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'typeId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性明细名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否有效(0无效1:有效，默认有效)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'enable'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'辅助属性表
存储系统中枚举变量值，如币别有：人民币，美元，欧元等类似的可以只用代码名称两个属性描述的资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage'
GO


/****** Object:  Table [dbo].[t_sms_status]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_status](
	[id] [int] NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_sourceBillType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_sourceBillType](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'原单据类型ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sourceBillType', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'原单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sourceBillType'
GO


/****** Object:  Table [dbo].[t_sms_settlement]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_settlement](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'结算方式ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'结算方式名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'结算方式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement'
GO


/****** Object:  Table [dbo].[t_sms_serial_number]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


CREATE TABLE [dbo].[t_sms_serial_number](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[classId] [int] NULL,
	[year] [int] NULL,
	[number] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[t_sms_sendcargo_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_sendcargo_entry](
	[id] [varchar](255) NOT NULL,
	[parent] [varchar](255) NULL,
	[orderId] [varchar](255) NULL,
	[seq] [int] NULL,
	[material] [varchar](255) NULL,
	[lot] [varchar](255) NULL,
	[dyBatchNum] [varchar](255) NULL,
	[code] [varchar](255) NULL,
	[unit] [varchar](255) NULL,
	[price] [money] NULL,
	[qty] [numeric](18, 2) NULL,
	[dyProDate] [datetime] NULL,
	[dyManufacturer] [varchar](255) NULL,
	[registrationNo] [varchar](255) NULL,
	[amount] [money] NULL,
	[effectiveDate] [datetime] NULL,
	[orderSeq] [varchar](255) NULL,
	[actualQty] [numeric](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发货单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'批号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyBatchNum'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'code'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'price'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'应发数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'实发数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO


/****** Object:  Table [dbo].[t_sms_sendcargo]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_sendcargo](
	[id] [varchar](50) NOT NULL,
	[number] [varchar](255) NULL,
	[Date] [datetime] NULL,
	[supplier] [varchar](255) NULL,
	[logistics] [varchar](255) NULL,
	[logisticsNo] [varchar](255) NULL,
	[saleProxy] [tinyint] NULL,
	[type] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'Date'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物流公司' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'logistics'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物流单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'logisticsNo'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'saleProxy'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发货状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'type'
GO


/****** Object:  Table [dbo].[t_sms_sale_proxy]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_sale_proxy](
	[id] [int] NOT NULL,
	[name] [varchar](50) NULL,
	[number] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购模式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sale_proxy', @level2type=N'COLUMN',@level2name=N'name'
GO


/****** Object:  Table [dbo].[t_sms_roleType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_roleType](
	[typeId] [nvarchar](50) NOT NULL,
	[number] [varchar](20) NOT NULL,
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'typeId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'name'
GO


/****** Object:  Table [dbo].[t_sms_role]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_role](
	[roleId] [varchar](50) NOT NULL,
	[name] [varchar](20) NOT NULL,
	[number] [varchar](20) NOT NULL,
	[type] [varchar](50) NOT NULL,
	[status] [tinyint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'角色名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'角色代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'角色类别(1:平台角色2:供应商角色)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'type'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'status'
GO


/****** Object:  Table [dbo].[t_sms_purreturns_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purreturns_entry](
	[id] [varchar](255) NOT NULL,
	[parent] [varchar](255) NULL,
	[orderId] [varchar](255) NULL,
	[orderSeq] [varchar](255) NULL,
	[material] [varchar](255) NULL,
	[unit] [varchar](255) NULL,
	[returnQty] [numeric](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'退货单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'退货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'returnQty'
GO


/****** Object:  Table [dbo].[t_sms_purreturns]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purreturns](
	[id] [varchar](255) NOT NULL,
	[number] [varchar](255) NULL,
	[bizDate] [datetime] NULL,
	[baseStatus] [tinyint] NULL,
	[sourceBillType] [varchar](255) NULL,
	[supplier] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'退货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'退货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'bizDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'supplier'
GO


/****** Object:  Table [dbo].[t_sms_purreceival_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purreceival_entry](
	[id] [varchar](255) NOT NULL,
	[parent] [varchar](255) NULL,
	[seq] [int] NULL,
	[orderId] [varchar](255) NULL,
	[orderSeq] [varchar](255) NULL,
	[material] [varchar](255) NULL,
	[lot] [varchar](255) NULL,
	[innercode] [varchar](255) NULL,
	[unit] [varchar](255) NULL,
	[price] [money] NULL,
	[qty] [numeric](18, 2) NULL,
	[actualQty] [numeric](18, 2) NULL,
	[dyProDate] [datetime] NULL,
	[dyManufacturer] [varchar](255) NULL,
	[registrationNo] [varchar](255) NULL,
	[amount] [money] NULL,
	[effectiveDate] [datetime] NULL,
	[qualifiedQty] [numeric](18, 2) NULL,
	[unqualifiedQty] [numeric](18, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'收货单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'收货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'innercode'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'price'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'应收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'合格数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'qualifiedQty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'不合格数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'unqualifiedQty'
GO


/****** Object:  Table [dbo].[t_sms_purreceival]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purreceival](
	[id] [varchar](255) NOT NULL,
	[number] [varchar](255) NULL,
	[bizDate] [datetime] NULL,
	[baseStatus] [tinyint] NULL,
	[sourceBillType] [varchar](255) NULL,
	[supplier] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'收货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'收货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'收货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'bizDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'supplier'
GO


/****** Object:  Table [dbo].[t_sms_purinwarehs_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purinwarehs_entry](
	[id] [varchar](255) NOT NULL,
	[parent] [varchar](255) NULL,
	[seq] [int] NULL,
	[orderId] [varchar](255) NULL,
	[orderSeq] [varchar](255) NULL,
	[material] [varchar](255) NULL,
	[lot] [varchar](255) NULL,
	[innercode] [varchar](255) NULL,
	[unit] [varchar](255) NULL,
	[price] [money] NULL,
	[actualQty] [numeric](18, 2) NULL,
	[dyProDate] [datetime] NULL,
	[dyManufacturer] [varchar](255) NULL,
	[registrationNo] [varchar](255) NULL,
	[amount] [money] NULL,
	[effectiveDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'入库单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'入库单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'innercode'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'price'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO


/****** Object:  Table [dbo].[t_sms_purinwarehs]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purinwarehs](
	[id] [varchar](255) NOT NULL,
	[number] [varchar](255) NULL,
	[bizDate] [datetime] NULL,
	[baseStatus] [tinyint] NULL,
	[sourceBillType] [varchar](255) NULL,
	[supplier] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'入库单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'入库单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'入库日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'bizDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'supplier'
GO


/****** Object:  Table [dbo].[t_sms_purchase_order_entry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purchase_order_entry](
	[material] [varchar](255) NULL,
	[parent] [varchar](255) NULL,
	[price] [money] NULL,
	[qty] [numeric](10, 2) NULL,
	[deliveryDate] [datetime] NULL,
	[discountRate] [float] NULL,
	[taxRate] [float] NULL,
	[taxPrice] [money] NULL,
	[actualTaxPrice] [money] NULL,
	[discountAmount] [money] NULL,
	[tax] [money] NULL,
	[localAmount] [money] NULL,
	[seq] [int] NULL,
	[confirmDate] [datetime] NULL,
	[confirmQty] [numeric](10, 2) NULL,
	[unit] [varchar](255) NULL,
	[id] [varchar](255) NOT NULL,
	[amount] [money] NULL,
	[invoiceQty] [numeric](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'material'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'订单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'price'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'交货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'deliveryDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'折扣率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'discountRate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'taxRate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'含税单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'taxPrice'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'实际含税单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'actualTaxPrice'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'折扣额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'discountAmount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'tax'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'本位币金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'localAmount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'确认时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'confirmDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'确认数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'confirmQty'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'基本计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'发货数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'invoiceQty'
GO


/****** Object:  Table [dbo].[t_sms_purchase_order]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_purchase_order](
	[id] [varchar](50) NOT NULL,
	[supplier] [varchar](255) NULL,
	[bizDate] [date] NULL,
	[purchasePerson] [varchar](255) NULL,
	[saleProxy] [tinyint] NULL,
	[isInTax] [tinyint] NULL,
	[tickDate] [datetime] NULL,
	[confirmTickDate] [datetime] NULL,
	[tickType] [tinyint] NULL,
	[confirmTick] [tinyint] NULL,
	[number] [varchar](255) NULL,
	[isQuicken] [tinyint] NULL,
	[currency] [varchar](255) NULL,
	[totalAmount] [money] NULL,
	[totalTax] [money] NULL,
	[totalTaxAmount] [money] NULL,
	[createTime] [datetime] NULL,
	[baseStatus] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'订单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'订单日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'bizDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购员' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'purchasePerson'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购模式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'saleProxy'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否含税' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'isInTax'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'HRP确认接单时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'tickDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商接单时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'confirmTickDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'HRP确认是否接单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'tickType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供应商是否接单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'confirmTick'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否加急' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'isQuicken'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'币别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'currency'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalAmount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'税额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalTax'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'价税合计' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalTaxAmount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'制单日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'createTime'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO


/****** Object:  Table [dbo].[t_sms_province]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_province](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_plugins]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_plugins](
	[id] [int] NOT NULL,
	[classId] [int] NOT NULL,
	[plugName] [varchar](255) NOT NULL,
	[index] [int] NOT NULL,
	[desc] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_paymentType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_paymentType](
	[id] [varchar](255) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_pay]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_pay](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'付款方式ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'付款方式名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'付款方式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay'
GO


/****** Object:  Table [dbo].[t_sms_objectType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_objectType](
	[topClassId] [int] NOT NULL,
	[subSysId] [int] NOT NULL,
	[objectType] [int] NOT NULL,
	[objectId] [int] NOT NULL,
	[name] [varchar](50) NULL,
	[description] [varchar](100) NOT NULL,
	[classId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[topClassId] ASC,
	[subSysId] ASC,
	[objectType] ASC,
	[objectId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'objectType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'objectId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'description'
GO


/****** Object:  Table [dbo].[t_sms_objectAccessType]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_objectAccessType](
	[objectType] [int] NOT NULL,
	[objectId] [int] NOT NULL,
	[index] [int] NOT NULL,
	[name] [varchar](50) NULL,
	[accessMask] [int] NULL,
	[accessUse] [int] NULL,
	[ownerType] [int] NULL,
	[description] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[objectType] ASC,
	[objectId] ASC,
	[index] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'objectType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'objectId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限掩码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'accessMask'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联权限项' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'accessUse'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限用户者类别，使用该权限的用户(1平台用户，2供应商用户，3两者都可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'ownerType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'description'
GO


/****** Object:  Table [dbo].[t_sms_msglog]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_msglog](
	[seqid] [varchar](50) NOT NULL,
	[mobiles] [varchar](1000) NULL,
	[smsContent] [varchar](1000) NULL,
	[restr] [varchar](100) NULL,
	[sendtime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[seqid] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_log]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_log](
	[userName] [varchar](50) NULL,
	[ip] [varchar](20) NULL,
	[message] [varchar](255) NULL,
	[operateTime] [datetime] NULL,
	[clazz] [varchar](500) NULL,
	[method] [varchar](50) NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
	[params] [varchar](1000) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'操作用户名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'userName'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'操作用户ip' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'ip'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'message'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'操作时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'operateTime'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'操作类路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'clazz'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'方法名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'method'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'方法参数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'params'
GO


/****** Object:  Table [dbo].[t_sms_supplier_item_license_type]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_supplier_item_license_type](
	[id] [varchar](50) NOT NULL,
	[number] [varchar](50) NULL,
	[name] [varchar](50) NULL,
	[syncStatus] [tinyint] NULL,
	[isMust] [tinyint] NULL,
	[isControl] [tinyint] NULL,
	[review] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'number'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（1.未编辑，2.新增，3.已编辑）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'isMust'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'isControl'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料证件类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type'
GO


/****** Object:  Table [dbo].[t_sms_item]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_item](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
	[specification] [varchar](255) NULL,
	[highConsumable] [tinyint] NULL,
	[isLotNumber] [tinyint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'规格型号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'specification'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否高值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'highConsumable'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'isLotNumber'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item'
GO


/****** Object:  Table [dbo].[t_sms_industry]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_industry](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行业ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行业名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'行业' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry'
GO


/****** Object:  Table [dbo].[t_sms_formFields]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_formFields](
	[classId] [int] NOT NULL,
	[page] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[sqlColumnName] [varchar](30) NULL,
	[key] [varchar](30) NOT NULL,
	[dataType] [int] NULL,
	[ctrlType] [int] NULL,
	[ctlIndex] [int] NULL,
	[index] [int] NULL,
	[display] [int] NULL,
	[showWidth] [int] NULL,
	[lookUpType] [int] NULL,
	[lookUpClassID] [int] NULL,
	[srcTable] [varchar](50) NULL,
	[srcTableAlisAs] [varchar](30) NULL,
	[srcField] [varchar](30) NULL,
	[disPlayField] [varchar](30) NULL,
	[disPlayNum] [varchar](30) NULL,
	[joinType] [varchar](20) NULL,
	[filter] [varchar](500) NULL,
	[defaultValue] [varchar](255) NULL,
	[maxValue] [numeric](10, 2) NULL,
	[minValue] [numeric](10, 2) NULL,
	[mustInput] [int] NULL,
	[length] [int] NULL,
	[lock] [int] NULL,
	[isCondition] [int] NULL,
	[isCount] [int] NULL,
	[needSave] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[classId] ASC,
	[key] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'业务类别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'classId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'标示模板字段所在的页面（0是表头，1是第一个子表，2是第二个子表，以此类推...）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'page'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段名(显示的字段名)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物理表中的字段名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'sqlColumnName'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段唯一标示(用于关联表显示字段名与本表字段名同名的情况，在一个formClass中FKey是唯一的)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'key'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段类型(数字，文本等)1:数字2:文本3:日期4:布尔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'dataType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'控件类型，指示前端展示时的特殊类型，比如多选框，基础资料选择框等,3:多选按钮6:基础资料选择框' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'ctrlType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段的后台查询顺序，FIndex是用于前端界面展示时的tab顺序\r\nFCtlIndex用于后端查询构建查询脚本时模板的遍历顺序，处理因为可能涉及关联其他表查询，连接表时的顺序不可随意改变时产生的错误' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'ctlIndex'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段的显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段显示性:用于根据用户类别控制字段取值。\r\n采用二进制方式配置\r\n如3表示平台用户物业用户都显示\r\n参考t_SubSysEnum,typeID=3' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'display'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段在前端页面显示的宽度(单位px)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'showWidth'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'标示是否是引用基础资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lookUpType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'引用基础资料的类别ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lookUpClassID'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'引用的表名(表示此字段是要关联表查询)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcTable'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联表别名(用于关联多次同一张表时)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcTableAlisAs'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联表的字段名,当FSrcTable有值时，此字段为必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcField'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联表显示的字段名,当FSrcTable有值时，此字段为必填,即显示FSrcTable表的FDisPlayField字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'disPlayField'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联表显示的代码字段,当FSrcTable有值时，此字段为必填,，一般显示关联标的FNumber' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'disPlayNum'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'表链接类型(默认 INNER JOIN)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'joinType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'过滤条件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'filter'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'默认值(控件初始化时候的默认值)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'defaultValue'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'最大值(数值类型字段使用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'maxValue'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'最小值(数值类型字段使用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'minValue'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否必录： 采用二进制方式配置, 参考t_SubSysEnum,typeID=4' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'mustInput'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段长度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'length'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'字段锁定性:表示字段页面展现的控制可编辑形式。\r\n采用二进制方式配置\r\n如3表示新增修改时都锁定\r\n参考t_SubSysEnum,typeID=2' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lock'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否可作为过滤条件，0：否，1：是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'isCondition'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否统计项，0：否，1：是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'isCount'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否需要保存到数据库(即物理表是否有字段与之对应)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'needSave'
GO


/****** Object:  Table [dbo].[t_sms_formEntries]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_formEntries](
	[classId] [int] NOT NULL,
	[entryIndex] [int] NOT NULL,
	[tableName] [varchar](50) NOT NULL,
	[foreignKey] [varchar](20) NOT NULL,
	[primaryKey] [varchar](20) NOT NULL,
	[bosType] [varchar](8) NOT NULL,
	[joinType] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[classId] ASC,
	[entryIndex] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'与formClass主表classId一致' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'classId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'子表序号(从1开始)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'entryIndex'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'表名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'tableName'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联主表字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'foreignKey'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'primaryKey'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'唯一标识单据类别(参考EAS设计)设置成与主表bosType一致' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'bosType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'与主表的连接关系(默认 INNER JOIN)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'joinType'
GO


/****** Object:  Table [dbo].[t_sms_formClass]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_formClass](
	[classId] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[tableName] [varchar](50) NOT NULL,
	[primaryKey] [varchar](20) NOT NULL,
	[bosType] [varchar](8) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[classId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'业务类别-唯一' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'classId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'业务类别名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'物理存储表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'tableName'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'primaryKey'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'唯一标识单据类别(参考EAS设计)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'bosType'
GO


/****** Object:  Table [dbo].[t_sms_employee]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_employee](
	[id] [varchar](255) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](30) NULL,
	[mobile] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'职员手机号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_employee', @level2type=N'COLUMN',@level2name=N'mobile'
GO


/****** Object:  Table [dbo].[t_sms_dataFlowTopClass]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_dataFlowTopClass](
	[topClassId] [int] NOT NULL,
	[topClassName] [varchar](50) NOT NULL,
	[index] [int] NOT NULL,
	[visible] [bit] NOT NULL,
	[icon] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[topClassId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'模块ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'topClassId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'模块名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'topClassName'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'排序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否可见' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'visible'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'菜单图标' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'icon'
GO


/****** Object:  Table [dbo].[t_sms_dataFlowSubClass]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_dataFlowSubClass](
	[topClassId] [int] NOT NULL,
	[subSysId] [int] NOT NULL,
	[name] [varchar](255) NOT NULL,
	[index] [int] NOT NULL,
	[visible] [tinyint] NULL,
	[url] [varchar](500) NULL,
	[icon] [varchar](100) NULL,
	[ownerType] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[subSysId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'上级模块ID(关联t_DataFlowTopClass中topClassId)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'topClassId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'子模块ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'subSysId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'子模块名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'子模块排序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'index'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'明细菜单是否可见：
设置为0：系统用户不可见,供应商用户不可见
设置为1：系统用户可见,供应商用户不可见
设置为2：系统用户不可见,供应商用户可见
设置为3：系统用户可见,供应商用户可见
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'visible'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'子系统网页链接地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'url'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'title图标地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'icon'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'用户类别，使用该功能的用户(平台用户，供应链用户，两者都可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'ownerType'
GO


/****** Object:  Table [dbo].[t_sms_currency]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_currency](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'币种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'币种名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'币别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency'
GO


/****** Object:  Table [dbo].[t_sms_county]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_county](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_country]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_country](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_city]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_city](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_certificate]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_certificate](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate'
GO


/****** Object:  Table [dbo].[t_sms_category]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_category](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'分类ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'分类名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category', @level2type=N'COLUMN',@level2name=N'name'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'分类' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category'
GO


/****** Object:  Table [dbo].[t_sms_base_status]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_base_status](
	[id] [int] NOT NULL,
	[name] [varchar](255) NULL,
	[number] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[t_sms_approved_supplier]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_approved_supplier](
	[id] [varchar](50) NOT NULL,
	[supplier] [varchar](50) NULL,
	[materialItem] [varchar](50) NULL,
	[isStopped] [tinyint] NULL,
	[measureUnit] [varchar](44) NULL,
	[supplierRate] [decimal](20, 10) NULL,
	[taxPrice] [decimal](20, 10) NULL,
	[effectualDate] [date] NULL,
	[uneffectualDate] [date] NULL,
	[syncStatus] [tinyint] NULL,
	[review] [tinyint] NULL,
	[purMeasureUnit] [varchar](44) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'id'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'supplier'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'关联物料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'materialItem'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'是否停用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'isStopped'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'基本计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'measureUnit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'供货比例' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'supplierRate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'含税价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'taxPrice'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'生效日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'effectualDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'失效日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'uneffectualDate'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'同步状态（1.未编辑，2.新增，3.已编辑）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'review'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'采购计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'purMeasureUnit'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'证书' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier'
GO


/****** Object:  Table [dbo].[t_sms_accessControl]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


SET ANSI_PADDING ON
GO


CREATE TABLE [dbo].[t_sms_accessControl](
	[objectType] [int] NOT NULL,
	[objectId] [int] NOT NULL,
	[roleId] [varchar](50) NOT NULL,
	[accessMask] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[objectType] ASC,
	[objectId] ASC,
	[roleId] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


SET ANSI_PADDING OFF
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'objectType'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'objectId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'角色ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'roleId'
GO


EXEC sys.sp_addextendedproperty @name=N'MS_Description', @VALUE=N'权限掩码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'accessMask'
GO


/****** Object:  UserDefinedFunction [dbo].[newbosid]    Script Date: 09/19/2017 15:34:18 ******/
SET ANSI_NULLS ON
GO


SET QUOTED_IDENTIFIER ON
GO


CREATE FUNCTION [dbo].[newbosid]
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
IF (LEN(@typeString) = 4) -- ����Ϊ4
BEGIN
SELECT  @l_result =   CAST(@UUID AS VARCHAR(40))+ @typeString
END
ELSE IF LEN(@typeString) = 8 --����Ϊ8
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
IF @b_num = 0 -- ת��ʧ��
BEGIN
SELECT  @l_result =   CAST(@UUID AS VARCHAR(40))+ @typeString
END
ELSE -- ת���ɹ�
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


/****** Object:  Default [DF__t_sms_use__statu__656C112C]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_user] ADD  DEFAULT ((0)) FOR [status]
GO


/****** Object:  Default [DF__t_sms_use__suppl__66603565]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_user] ADD  DEFAULT ('') FOR [supplier]
GO


/****** Object:  Default [DF__t_sms_use__token__6754599E]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_user] ADD  DEFAULT ('') FOR [token]
GO


/****** Object:  Default [DF__t_sms_use__phone__68487DD7]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_user] ADD  DEFAULT ('') FOR [phone]
GO


/****** Object:  Default [DF__t_sms_sys__categ__5BE2A6F2]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ('') FOR [category]
GO


/****** Object:  Default [DF__t_sms_sys_p__key__5CD6CB2B]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ('') FOR [key]
GO


/****** Object:  Default [DF__t_sms_sys___name__5DCAEF64]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ('') FOR [name]
GO


/****** Object:  Default [DF__t_sms_sys___desc__5EBF139D]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ('') FOR [desc]
GO


/****** Object:  Default [DF__t_sms_sys__index__5FB337D6]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ((0)) FOR [index]
GO


/****** Object:  Default [DF__t_sms_sys__expla__60A75C0F]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ('') FOR [explanation]
GO


/****** Object:  Default [DF__t_sms_sys__readO__619B8048]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  DEFAULT ((0)) FOR [readOnly]
GO


/****** Object:  Default [DF__t_sms_sup__syncS__59063A47]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license_type] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_sup__revie__59FA5E80]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license_type] ADD  DEFAULT ((0)) FOR [review]
GO


/****** Object:  Default [DF__t_sms_sup__descr__5441852A]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  DEFAULT ('') FOR [description]
GO


/****** Object:  Default [DF__t_sms_sup__syncS__5535A963]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_sup__revie__5629CD9C]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  DEFAULT ((0)) FOR [review]
GO


/****** Object:  Default [DF__t_sms_sup__idNum__0E391C95]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  DEFAULT ('') FOR [idNumber]
GO


/****** Object:  Default [DF__t_sms_sup__syncS__5070F446]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_item_license] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_sup__revie__5165187F]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_item_license] ADD  DEFAULT ((0)) FOR [review]
GO


/****** Object:  Default [DF__t_sms_sup__syncS__4D94879B]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_sup__revie__4E88ABD4]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier] ADD  DEFAULT ((0)) FOR [review]
GO


/****** Object:  Default [DF__t_sms_sub__numbe__48CFD27E]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ('') FOR [number]
GO


/****** Object:  Default [DF__t_sms_sub__enabl__49C3F6B7]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ((1)) FOR [enable]
GO


/****** Object:  Default [DF__t_sms_sub__index__4AB81AF0]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ((0)) FOR [index]
GO


/****** Object:  Default [DF__t_sms_rol__statu__3F466844]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_role] ADD  DEFAULT ((0)) FOR [status]
GO


/****** Object:  Default [DF__t_sms_plu__index__33D4B598]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_plugins] ADD  DEFAULT ((0)) FOR [index]
GO


/****** Object:  Default [DF__t_sms_plug__desc__34C8D9D1]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_plugins] ADD  DEFAULT ('') FOR [desc]
GO


/****** Object:  Default [DF__t_sms_ite__syncS__2B3F6F97]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_item_license_type] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_ite__revie__2C3393D0]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_supplier_item_license_type] ADD  DEFAULT ((0)) FOR [review]
GO


/****** Object:  Default [DF__t_sms_form__page__117F9D94]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [page]
GO


/****** Object:  Default [DF__t_sms_formF__key__1273C1CD]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ('') FOR [key]
GO


/****** Object:  Default [DF__t_sms_for__dataT__1367E606]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [dataType]
GO


/****** Object:  Default [DF__t_sms_for__ctrlT__145C0A3F]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [ctrlType]
GO


/****** Object:  Default [DF__t_sms_for__ctlIn__15502E78]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [ctlIndex]
GO


/****** Object:  Default [DF__t_sms_for__index__164452B1]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [index]
GO


/****** Object:  Default [DF__t_sms_for__displ__173876EA]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((1)) FOR [display]
GO


/****** Object:  Default [DF__t_sms_for__showW__182C9B23]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((80)) FOR [showWidth]
GO


/****** Object:  Default [DF__t_sms_for__lookU__1920BF5C]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [lookUpType]
GO


/****** Object:  Default [DF__t_sms_for__lookU__1A14E395]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [lookUpClassID]
GO


/****** Object:  Default [DF__t_sms_for__srcTa__1B0907CE]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ('') FOR [srcTable]
GO


/****** Object:  Default [DF__t_sms_for__srcTa__1BFD2C07]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [srcTableAlisAs]
GO


/****** Object:  Default [DF__t_sms_for__srcFi__1CF15040]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ('') FOR [srcField]
GO


/****** Object:  Default [DF__t_sms_for__disPl__1DE57479]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [disPlayField]
GO


/****** Object:  Default [DF__t_sms_for__disPl__1ED998B2]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [disPlayNum]
GO


/****** Object:  Default [DF__t_sms_for__joinT__1FCDBCEB]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ('') FOR [joinType]
GO


/****** Object:  Default [DF__t_sms_for__filte__20C1E124]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ('') FOR [filter]
GO


/****** Object:  Default [DF__t_sms_for__defau__21B6055D]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [defaultValue]
GO


/****** Object:  Default [DF__t_sms_for__mustI__22AA2996]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [mustInput]
GO


/****** Object:  Default [DF__t_sms_for__lengt__239E4DCF]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT (NULL) FOR [length]
GO


/****** Object:  Default [DF__t_sms_form__lock__24927208]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [lock]
GO


/****** Object:  Default [DF__t_sms_for__isCon__25869641]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [isCondition]
GO


/****** Object:  Default [DF__t_sms_for__isCou__267ABA7A]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((0)) FOR [isCount]
GO


/****** Object:  Default [DF__t_sms_for__needS__276EDEB3]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formFields] ADD  DEFAULT ((1)) FOR [needSave]
GO


/****** Object:  Default [DF__t_sms_for__bosTy__0EA330E9]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formEntries] ADD  DEFAULT ('') FOR [bosType]
GO


/****** Object:  Default [DF__t_sms_for__joinT__0F975522]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formEntries] ADD  DEFAULT ('') FOR [joinType]
GO


/****** Object:  Default [DF__t_sms_for__bosTy__0CBAE877]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_formClass] ADD  DEFAULT ('') FOR [bosType]
GO


/****** Object:  Default [DF__t_sms_dat__owner__08EA5793]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_dataFlowSubClass] ADD  DEFAULT ((0)) FOR [ownerType]
GO


/****** Object:  Default [DF__t_sms_app__syncS__7F60ED59]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_approved_supplier] ADD  DEFAULT ((0)) FOR [syncStatus]
GO


/****** Object:  Default [DF__t_sms_app__revie__00551192]    Script Date: 09/19/2017 15:34:18 ******/
ALTER TABLE [dbo].[t_sms_approved_supplier] ADD  DEFAULT ((0)) FOR [review]
GO

/*****init data*******/

-- ----------------------------
-- Records of t_sms_formFields
-- ----------------------------


/****** Object:  Table [dbo].[t_sms_formFields]    Script Date: 09/19/2017 15:54:56 ******/
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 1, N'电话', N'mobile', N'mobile', 2, 8, 7, 7, 127, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'账号', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 10, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'姓名', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'密码', N'password', N'password', 99, NULL, 4, 4, 60, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'手机', N'phone', N'phone', 2, 8, 7, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 1, N'pic', N'pic', N'pic', 2, 8, 7, 7, 127, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'角色', N'role', N'role', 2, 6, 6, 6, 63, 80, 1, 1003, N't_sms_Role', N'', N'roleId', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'是否禁用', N'status', N'status', 4, 3, 9, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'关联供应商', N'supplier', N'supplier', 2, 6, 8, 8, 63, 150, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'用户类别', N'type', N'type', 2, 6, 5, 5, 61, 80, 1, 1002, N't_sms_userType', N'', N'typeId', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 8, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'内码', N'userId', N'userId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'内码', N'typeId', N'typeId', 1, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'内码', N'roleId', N'roleId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'是否禁用', N'status', N'status', 4, 3, 13, 13, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'角色类别', N'type', N'type', 2, 6, 5, 5, 61, 80, 1, 1004, N't_sms_roleType', N'', N'typeId', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 12, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'内码', N'typeId', N'typeId', 1, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'地址', N'address', N'address', 2, NULL, 15, 15, 63, 300, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 80, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'工商注册号', N'brno', N'brno', 2, NULL, 6, 6, 0, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'分类', N'categoryId', N'categoryId', 2, 6, 4, 4, 63, 60, 1, 1006, N't_sms_category', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'城市', N'city', N'city', 2, 6, 12, 12, 63, 80, 1, 1008, N't_sms_city', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'联系人', N'contactPerson', N'contactPerson', 2, NULL, 16, 16, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'法人代表', N'corp', N'corp', 2, NULL, 5, 5, 0, 70, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'国家', N'country', N'country', 2, 6, 11, 11, 63, 80, 1, 1009, N't_sms_country', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'区县', N'county', N'county', 2, 6, 14, 14, 63, 80, 1, 1010, N't_sms_county', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 40, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'行业', N'industryId', N'industryId', 2, 6, 10, 10, 63, 120, 1, 1012, N't_sms_industry', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'手机号码', N'mobile', N'mobile', 2, 8, 17, 17, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'省份', N'province', N'province', 2, 6, 13, 13, 63, 80, 1, 1015, N't_sms_province', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'审核状态', N'review', N'review', 4, 3, 18, 18, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'状态', N'status', N'status', 2, 6, 18, 18, 63, 80, 1, 1026, N't_sms_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 63, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 15, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税种', N'taxCategoryId', N'taxCategoryId', 2, 6, 7, 7, 63, 70, 1, 1017, N't_sms_taxCategory', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税务登记号', N'taxId', N'taxId', 2, NULL, 9, 9, 63, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税率', N'taxRate', N'taxRate', 1, 1, 8, 8, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 5, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'审核状态', N'review', N'review', 4, 3, 6, 6, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'是否高值', N'highConsumable', N'highConsumable', 4, 3, 5, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'是否批次', N'isLotNumber', N'isLotNumber', 4, 3, 6, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'规格型号', N'specification', N'specification', 2, NULL, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'审核状态', N'review', N'review', 4, 3, 6, 6, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'手机号码', N'mobile', N'mobile', 2, NULL, 15, 15, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
print 'Processed 100 total records'
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'实际含税单价', N'actualTaxPrice', N'actualTaxPrice', 1, NULL, 10, 10, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 13, 13, 447, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'订单日期', N'bizDate', N'bizDate', 3, 12, 2, 2, 447, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'确认交货日期', N'confirmDate', N'confirmDate', 3, 12, 15, 15, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'确认数量', N'confirmQty', N'confirmQty', 1, NULL, 14, 14, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商是否接单', N'confirmTick', N'confirmTick', 4, 3, 17, 17, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商接单时间', N'confirmTickDate', N'confirmTickDate', 3, 12, 12, 12, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'制单日期', N'createTime', N'createTime', 3, 12, 10, 10, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'币别', N'currency', N'currency', 2, 6, 7, 7, 447, 150, 1, 1011, N't_sms_currency', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'交货日期', N'deliveryDate', N'deliveryDate', 3, 12, 13, 13, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'折扣额', N'discountAmount', N'discountAmount', 1, NULL, 11, 11, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'折扣率', N'discountRate', N'discountRate', 1, NULL, 7, 7, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'是否高值', N'', N'highConsumable', 4, 3, 3, 2, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'发货数量', N'invoiceQty', N'invoiceQty', 1, NULL, 5, 5, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'是否含税', N'isInTax', N'isInTax', 4, 3, 14, 14, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'是否批次', N'', N'isLotNumber', 4, 3, 3, 2, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'是否加急', N'isQuicken', N'isQuicken', 4, 3, 15, 15, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'本位币金额', N'localAmount', N'localAmount', 1, NULL, 12, 12, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'物料名称', N'material', N'material', 2, 6, 2, 2, 447, 80, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'物料编码', N'', N'materialNumber', 2, NULL, 3, 2, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'单据编号', N'number', N'number', 2, NULL, 1, 1, 447, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'订单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'价格', N'price', N'price', 1, NULL, 6, 6, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'采购员', N'purchasePerson', N'purchasePerson', 2, 6, 4, 4, 447, 150, 1, 1024, N't_sms_employee', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'数量', N'qty', N'qty', 1, NULL, 4, 4, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'采购模式', N'saleProxy', N'saleProxy', 2, 6, 3, 3, 447, 60, 1, 1021, N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'规格型号', N'', N'specification', 2, NULL, 3, 2, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商', N'supplier', N'supplier', 2, 6, 5, 5, 447, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'税额', N'tax', N'tax', 1, NULL, 11, 11, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'含税单价', N'taxPrice', N'taxPrice', 1, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'税率', N'taxRate', N'taxRate', 1, NULL, 8, 8, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'HRP确认接单时间', N'tickDate', N'tickDate', 3, 12, 11, 11, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'HRP确认是否接单', N'tickType', N'tickType', 4, 3, 16, 16, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'金额', N'totalAmount', N'totalAmount', 2, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'税额', N'totalTax', N'totalTax', 2, NULL, 6, 6, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'价税合计', N'totalTaxAmount', N'totalTaxAmount', 2, NULL, 8, 8, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'基本计量单位', N'unit', N'unit', 2, 6, 3, 3, 447, 60, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'实发数量', N'actualQty', N'actualQty', 1, NULL, 12, 12, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'个体码', N'code', N'code', 2, NULL, 8, 8, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'发货日期', N'Date', N'Date', 3, 12, 2, 7, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'批号', N'dyBatchNum', N'dyBatchNum', 2, NULL, 7, 7, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 14, 14, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 13, 13, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'是否高值', N'', N'highConsumable', 4, 3, 5, 5, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'highConsumable', N'', N'LFET JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'是否批次', N'', N'isLotNumber', 4, 3, 5, 5, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'isLotNumber', N'', N'LFET JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'物流公司', N'logistics', N'logistics', 2, NULL, 1, 5, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'物流单号', N'logisticsNo', N'logisticsNo', 2, NULL, 1, 6, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 447, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'单据编号', N'number', N'number', 2, NULL, 1, 2, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 447, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'发货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'应发数量', N'qty', N'qty', 1, NULL, 11, 11, 48, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 15, 15, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'订单类型', N'saleProxy', N'saleProxy', 2, 6, 3, 3, 447, 60, 1, 1021, N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'规格型号', N'', N'specification', 2, NULL, 5, 5, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 3, 447, 100, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'是否发送到医院', N'type', N'type', 4, 3, 5, 5, 3, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 447, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'实收数量', N'actualQty', N'actualQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 5, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'收货日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 13, 13, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 12, 12, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'个体码', N'innercode', N'innercode', 2, NULL, 8, 8, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'收货单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'收货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'应收数量', N'qty', N'qty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'合格数量', N'qualifiedQty', N'qualifiedQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 14, 14, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
print 'Processed 200 total records'
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'不合格数量', N'unqualifiedQty', N'unqualifiedQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'入库数量', N'actualQty', N'actualQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 5, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'入库日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 13, 13, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 12, 12, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'个体码', N'innercode', N'innercode', 2, NULL, 8, 8, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'入库单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'入库单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 14, 14, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'退货日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'退货单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'退货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'退货数量', N'returnQty', N'returnQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'发证机关', N'authOrg', N'authOrg', 2, NULL, 3, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'起始日期', N'beginDate', N'beginDate', 3, 12, 4, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'备注', N'description', N'description', 2, 11, 7, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'结束日期', N'endDate', N'endDate', 3, 12, 5, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'证件编码', N'idNumber', N'idNumber', 2, NULL, 3, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 9, 11, 63, 80, 3, 1007, N't_sms_supplier_license_type', N'', N'', N'isControl', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 8, 10, 63, 80, 3, 1007, N't_sms_supplier_license_type', N'', N'', N'isMust', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'资质', N'parent', N'parent', 2, NULL, 2, 2, 0, 80, 0, 0, N'', N'', N'', NULL, NULL, N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否停用', N'prohibited', N'prohibited', 4, 3, 10, 12, 63, 80, 0, 0, N'', N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 15, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'审核状态', N'review', N'review', 4, 3, 11, 13, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'供应商', N'supplier', N'supplier', 2, 6, 6, 8, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'证书类别', N'type', N'type', 2, 6, 2, 4, 63, 80, 1, 1007, N't_sms_supplier_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'附件', N'url', N'url', 2, NULL, 3, 3, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'发证机关', N'authOrg', N'authOrg', 2, NULL, 3, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'起始日期', N'beginDate', N'beginDate', 3, 12, 4, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'备注', N'description', N'description', 2, 11, 8, 10, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 11, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'结束日期', N'endDate', N'endDate', 3, 12, 5, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 10, 12, 63, 80, 3, 1023, N't_sms_supplier_item_license_type', N'', N'', N'isControl', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 9, 11, 63, 80, 3, 1023, N't_sms_supplier_item_license_type', N'', N'', N'isMust', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'物料', N'material', N'material', 2, 6, 7, 9, 63, 200, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'注册证名称名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'注册证编号', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'物料证件', N'parent', N'parent', 2, NULL, 2, 2, 0, 80, 0, 0, NULL, N'', NULL, NULL, NULL, NULL, N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否禁用', N'prohibited', N'prohibited', 4, 3, 11, 13, 63, 80, 0, NULL, NULL, N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'审核状态', N'review', N'review', 4, 3, 12, 14, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'供应商', N'supplier', N'supplier', 2, 6, 6, 8, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'注册证类别', N'type', N'type', 2, 6, 2, 4, 63, 80, 1, 1023, N't_sms_supplier_item_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'附件路径', N'url', N'url', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'生效日期', N'effectualDate', N'effectualDate', 3, 12, 10, 10, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'是否停用', N'isStopped', N'isStopped', 4, 3, 12, 12, 63, 80, 0, 0, N'', N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 15, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'物料', N'materialItem', N'materialItem', 2, 6, 3, 3, 63, 80, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'物料编码', N'', N'materialNumber', 2, NULL, 4, 4, 63, 80, 3, 1013, N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'基本计量单位', N'measureUnit', N'measureUnit', 2, 6, 7, 7, 63, 40, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'采购计量单位', N'purMeasureUnit', N'purMeasureUnit', 2, 6, 6, 6, 63, 40, 1, 1018, N't_sms_unit', N't_sms_unit_base', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'审核状态', N'review', N'review', 4, 3, 13, 13, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'规格型号', N'', N'specification', 2, NULL, 5, 5, 63, 80, 3, 1013, N't_sms_item', N'', NULL, N'specification', N'', N'', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'供应商', N'supplier', N'supplier', 2, 6, 2, 2, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'供货比例', N'supplierRate', N'supplierRate', 1, 1, 8, 8, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 14, 14, 1, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'含税价格', N'taxPrice', N'taxPrice', 1, 1, 9, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'失效日期', N'uneffectualDate', N'uneffectualDate', 3, 12, 11, 11, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'入口', N'clazz', N'clazz', 2, NULL, 35, 35, 63, 330, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'主键', N'id', N'id', 1, NULL, 5, 5, 0, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'IP', N'ip', N'ip', 2, NULL, 20, 20, 63, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'描述', N'message', N'message', 2, NULL, 30, 30, 63, 150, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'方法', N'method', N'method', 2, NULL, 40, 40, 63, 60, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'时间', N'operateTime', N'operateTime', 3, NULL, 25, 25, 63, 150, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'参数', N'params', N'params', 2, NULL, 45, 45, 63, 500, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'用户', N'userName', N'userName', 2, NULL, 15, 15, 63, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO

INSERT INTO [t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (N'10', N'1005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'207'), (N'10', N'1010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1015', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'15'), (N'10', N'1020', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1025', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'5'), (N'10', N'1030', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'10', N'1030', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'293'), (N'10', N'1035', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1035', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1045', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1045', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1050', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1050', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1055', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1055', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1060', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1060', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1065', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1065', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1070', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1070', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1075', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1075', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1080', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1080', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'10', N'1085', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'10', N'1085', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'20', N'2005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'3'), (N'20', N'2005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'3'), (N'20', N'2010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'20', N'2010', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'30', N'3005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'30', N'3005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'511'), (N'30', N'3010', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3015', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'30', N'3015', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'30', N'3020', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'319'), (N'30', N'3020', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3025', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'319'), (N'30', N'3025', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'30', N'3030', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'30', N'3030', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'40', N'4005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'271'), (N'40', N'4005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'271'), (N'60', N'6005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'60', N'6005', N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'1'), (N'140', N'14005', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1'), (N'140', N'14010', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'1')
GO


-- ----------------------------
-- Records of t_sms_dataFlowSubClass
-- ----------------------------

INSERT INTO [t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (N'10', N'1005', N'用户', N'1', N'1', N'html/base/index.html?classId=1001', N'account.png', N'1'), (N'10', N'1010', N'用户类别', N'2', N'1', N'html/base/index.html?classId=1002', N'category.png', N'1'), (N'10', N'1015', N'角色', N'3', N'1', N'html/base/index.html?classId=1003', N'role.png', N'1'), (N'10', N'1020', N'角色类别', N'4', N'1', N'html/base/index.html?classId=1004', N'category.png', N'1'), (N'10', N'1025', N'角色权限', N'5', N'1', N'html/rolePerm/index.html', N'permision.png', N'1'), (N'10', N'1030', N'供应商', N'6', N'3', N'html/base/index.html?classId=1005', N'supplier-features.png', N'3'), (N'10', N'1035', N'供应商类别', N'7', N'1', N'html/base/index.html?classId=1006', N'category.png', N'3'), (N'10', N'1045', N'行业', N'9', N'1', N'html/base/index.html?classId=1012', N'industry.png', N'3'), (N'10', N'1050', N'结算方式', N'10', N'1', N'html/base/index.html?classId=1016', N'settlement.png', N'3'), (N'10', N'1055', N'付款方式', N'11', N'1', N'html/base/index.html?classId=1014', N'payment.png', N'3'), (N'10', N'1060', N'物料', N'12', N'1', N'html/base/index.html?classId=1013', N'goods.png', N'3'), (N'10', N'1065', N'税种', N'13', N'1', N'html/base/index.html?classId=1017', N'category.png', N'3'), (N'10', N'1070', N'城市', N'14', N'1', N'html/base/index.html?classId=1008', N'country.png', N'3'), (N'10', N'1075', N'国家', N'15', N'1', N'html/base/index.html?classId=1009', N'country.png', N'3'), (N'10', N'1080', N'区县', N'16', N'1', N'html/base/index.html?classId=1010', N'country.png', N'3'), (N'10', N'1085', N'省份', N'17', N'1', N'html/base/index.html?classId=1015', N'country.png', N'3'), (N'20', N'2005', N'采购订单', N'1', N'3', N'html/list/index.html?classId=2019', N'purchase.png', N'3'), (N'20', N'2010', N'订单追踪', N'1', N'3', N'html/report/order-track/index.html', N'purchase.png', N'3'), (N'30', N'3005', N'供应商资质维护', N'1', N'3', N'html/supplier/license/index.html?classId=3010', N'file.png', N'3'), (N'30', N'3010', N'物料证件维护', N'2', N'3', N'html/supplier/license/index.html?classId=3020', N'file.png', N'3'), (N'30', N'3015', N'供应商物料查询', N'3', N'3', N'html/base/index.html?classId=3030', N'search.png', N'3'), (N'30', N'3020', N'供应商证件类别', N'5', N'3', N'html/base/index.html?classId=1007', N'category.png', N'3'), (N'30', N'3025', N'物料证件类别', N'6', N'3', N'html/base/index.html?classId=1023', N'category.png', N'3'), (N'30', N'3030', N'物料证件查询', N'4', N'3', N'html/base/index.html?classId=3020', N'search.png', N'3'), (N'40', N'4005', N'发货单', N'2', N'3', N'html/list/index.html?classId=2020', N'deliver.png', N'3'), (N'60', N'6005', N'订单统计', N'2', N'3', N'html/report/order-count/index.html', N'calculator.png', N'3'), (N'140', N'14005', N'参数设置', N'1', N'1', N'html/sys/parameter/index.html', N'category.png', N'3'), (N'140', N'14010', N'系统日志', N'1', N'1', N'html/sys/log/index.html', N'calendar.png', N'3'), (N'1000', N'100005', N'API测试', N'1', N'0', N'html/api-tester/index.html', N'icon_order_normal.png', N'0')
GO


-- ----------------------------
-- Records of t_sms_dataFlowTopClass
-- ----------------------------

INSERT INTO [t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (N'10', N'基础资料', N'6', N'1', N'text.png'), (N'20', N'订单管理', N'1', N'1', N'manage-order.png'), (N'30', N'证件管理', N'4', N'1', N'supplier-features.png'), (N'40', N'发货管理', N'2', N'1', N'deliver.png'), (N'60', N'统计查询', N'5', N'1', N'search.png'), (N'140', N'系统管理', N'7', N'1', N'set.png'), (N'1000', N'Test', N'100', N'0', N'all.png')
GO


-- ----------------------------
-- Records of t_sms_formClass
-- ----------------------------

INSERT INTO [t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (N'1001', N'用户', N't_sms_user', N'userId', N'10011001'), (N'1002', N'用户类别', N't_sms_userType', N'typeId', N'10021002'), (N'1003', N'角色', N't_sms_role', N'roleId', N'10031003'), (N'1004', N'角色类别', N't_sms_roleType', N'typeId', N'10041004'), (N'1005', N'供应商', N't_sms_supplier', N'id', N'37C67DFC'), (N'1006', N'供应商类别', N't_sms_category', N'id', N'7A2569A2'), (N'1007', N'供应商证件类别', N't_sms_supplier_license_type', N'id', N'9D1A92CC'), (N'1008', N'城市', N't_sms_city', N'id', N'0C5DD6B6'), (N'1009', N'国家', N't_sms_country', N'id', N'2665126B'), (N'1010', N'区县', N't_sms_county', N'id', N'859D3D7F'), (N'1011', N'币别', N't_sms_currency', N'id', N'DEB58FDC'), (N'1012', N'行业', N't_sms_industry', N'id', N'C3FDE1A9'), (N'1013', N'物料', N't_sms_item', N'id', N'4409E7F0'), (N'1014', N'付款方式', N't_sms_pay', N'id', N'6BCA0AB5'), (N'1015', N'省份', N't_sms_province', N'id', N'818DCAFB'), (N'1016', N'结算方式', N't_sms_settlement', N'id', N'E96B2B8E'), (N'1017', N'税种', N't_sms_taxCategory', N'id', N'91E210CA'), (N'1018', N'单位', N't_sms_unit', N'id', N'5B825C57'), (N'1021', N'采购模式', N't_sms_sale_proxy', N'id', N''), (N'1023', N'物料证件类型', N't_sms_supplier_item_license_type', N'id', N'2F32B746'), (N'1024', N'职员', N't_sms_employee', N'id', N''), (N'1025', N'单据状态', N't_sms_base_status', N'id', N''), (N'1026', N'原单据类型', N't_sms_sourceBillType', N'id', N''), (N'1027', N'状态', N't_sms_status', N'id', N''), (N'2019', N'采购订单', N't_sms_purchase_order', N'id', N''), (N'2020', N'发货单', N't_sms_sendcargo', N'id', N'15F2BD83'), (N'2021', N'收货单', N't_sms_purreceival', N'id', N''), (N'2022', N'入库单', N't_sms_purinwarehs', N'id', N''), (N'2023', N'退货单', N't_sms_purreturns', N'id', N''), (N'3010', N'供应商资质', N't_sms_supplier_license', N'id', N'8D759F89'), (N'3020', N'物料证件', N't_sms_supplier_item_license', N'id', N'24631D4E'), (N'3030', N'中标库', N't_sms_approved_supplier', N'id', N'ssssssss'), (N'14001', N'系统日志', N't_sms_log', N'id', N'')
GO


-- ----------------------------
-- Records of t_sms_formEntries
-- ----------------------------

INSERT INTO [t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (N'2019', N'1', N't_sms_purchase_order_entry', N'parent', N'entryId', N'', N''), (N'2020', N'1', N't_sms_sendcargo_entry', N'parent', N'entryId', N'15F2BD83', N''), (N'2021', N'1', N't_sms_purreceival_entry', N'parent', N'entryId', N'', N''), (N'2022', N'1', N't_sms_purinwarehs_entry', N'parent', N'entryId', N'', N''), (N'2023', N'1', N't_sms_purreturns_entry', N'parent', N'entryId', N'', N''), (N'3010', N'1', N't_sms_supplier_license_entry', N'parent', N'entryId', N'10011001', N'left join'), (N'3020', N'1', N't_sms_supplier_item_license_entry', N'parent', N'entryId', N'10011001', N'left join')
GO


-- ----------------------------
-- Records of t_sms_objectAccessType
-- ----------------------------

INSERT INTO [t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (N'10', N'1005', N'0', N'查看', N'1', N'1', N'3', N'用户管理查看权限-由此权限才可进入用户管理界面'), (N'10', N'1005', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'10', N'1005', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'10', N'1005', N'3', N'删除', N'8', N'1', N'3', N'删除'), (N'10', N'1005', N'5', N'禁用', N'64', N'1', N'3', N'禁用'), (N'10', N'1005', N'6', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'10', N'1010', N'0', N'查看', N'1', N'1', N'3', N'用户类别查看权限-由此权限才可进入用户类别界面'), (N'10', N'1015', N'0', N'查看', N'1', N'1', N'3', N'角色查看权限'), (N'10', N'1015', N'1', N'新增', N'2', N'1', N'3', N'角色新增权限'), (N'10', N'1015', N'2', N'修改', N'4', N'1', N'3', N'角色修改权限'), (N'10', N'1015', N'3', N'删除', N'8', N'1', N'3', N'角色删除权限'), (N'10', N'1020', N'0', N'查看', N'1', N'1', N'3', N'角色类别管理查看权限-由此权限才可进入角色类别界面'), (N'10', N'1025', N'0', N'查看', N'1', N'1', N'3', N'角色权限查看权限-由此权限才可进入角色权限界面'), (N'10', N'1025', N'1', N'授权', N'4', N'1', N'3', N'修改角色权限明细的权限(授权)'), (N'10', N'1030', N'0', N'查看', N'1', N'1', N'3', N'供应商管理查看权限'), (N'10', N'1030', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'10', N'1030', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'10', N'1030', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'10', N'1030', N'4', N'审核', N'16', N'1', N'1', N'审核'), (N'10', N'1030', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'10', N'1030', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'10', N'1030', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'10', N'1030', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'10', N'1035', N'0', N'查看', N'1', N'1', N'3', N'供应商类别查看权限'), (N'10', N'1045', N'0', N'查看', N'1', N'1', N'3', N'行业查看权限'), (N'10', N'1050', N'0', N'查看', N'1', N'1', N'3', N'结算方式查看权限'), (N'10', N'1055', N'0', N'查看', N'1', N'1', N'3', N'付款方式查看权限'), (N'10', N'1060', N'0', N'查看', N'1', N'1', N'3', N'物料查看权限'), (N'10', N'1065', N'0', N'查看', N'1', N'1', N'3', N'税种查看权限'), (N'10', N'1070', N'0', N'查看', N'1', N'1', N'3', N'城市查看权限'), (N'10', N'1075', N'0', N'查看', N'1', N'1', N'3', N'国家查看权限'), (N'10', N'1080', N'0', N'查看', N'1', N'1', N'3', N'区县查看权限'), (N'10', N'1085', N'0', N'查看', N'1', N'1', N'3', N'省份查看权限
'), (N'20', N'2005', N'0', N'查看', N'1', N'1', N'3', N'采购订单查看权限'), (N'20', N'2005', N'1', N'生成发货单', N'2', N'1', N'3', N'生成发货单'), (N'20', N'2010', N'0', N'查看', N'1', N'1', N'3', N'订单追踪查看权限'), (N'30', N'3005', N'0', N'查看', N'1', N'1', N'3', N'供应商资质维护查看权限'), (N'30', N'3005', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3005', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3005', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3005', N'4', N'审核', N'16', N'1', N'1', N'审核'), (N'30', N'3005', N'5', N'反审核', N'32', N'1', N'1', N'反审核'), (N'30', N'3005', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'30', N'3005', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'30', N'3005', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'30', N'3010', N'0', N'查看', N'1', N'1', N'3', N'物料证件维护查看权限'), (N'30', N'3010', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3010', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3010', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3010', N'4', N'审核', N'16', N'1', N'1', N'审核'), (N'30', N'3010', N'5', N'反审核', N'32', N'1', N'1', N'反审核'), (N'30', N'3010', N'6', N'禁用', N'64', N'1', N'3', N'禁用'), (N'30', N'3010', N'7', N'反禁用', N'128', N'1', N'3', N'反禁用'), (N'30', N'3010', N'8', N'同步', N'256', N'1', N'3', N'同步'), (N'30', N'3015', N'0', N'查看', N'1', N'1', N'3', N'供应商物料查询查看权限'), (N'30', N'3020', N'0', N'查看', N'1', N'1', N'3', N'供应商证件类别查看权限'), (N'30', N'3020', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3020', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3020', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3020', N'4', N'审核', N'16', N'1', N'3', N'审核'), (N'30', N'3020', N'5', N'反审核', N'32', N'1', N'1', N'反审核'), (N'30', N'3020', N'6', N'同步', N'256', N'1', N'3', N'禁用'), (N'30', N'3025', N'0', N'查看', N'1', N'1', N'3', N'物料证件类别查看权限'), (N'30', N'3025', N'1', N'新增', N'2', N'1', N'3', N'新增'), (N'30', N'3025', N'2', N'修改', N'4', N'1', N'3', N'修改'), (N'30', N'3025', N'3', N'删除', N'8', N'1', N'3', N'修改'), (N'30', N'3025', N'4', N'审核', N'16', N'1', N'3', N'审核'), (N'30', N'3025', N'5', N'反审核', N'32', N'1', N'3', N'反审核'), (N'30', N'3025', N'6', N'同步', N'256', N'1', N'3', N'禁用'), (N'30', N'3030', N'0', N'查看', N'1', N'1', N'3', N'物料证件查看权限'), (N'40', N'4005', N'0', N'查看', N'1', N'1', N'3', N'发货单查看权限'), (N'40', N'4005', N'1', N'新增', N'2', N'1', N'3', N'新增发货单'), (N'40', N'4005', N'2', N'修改', N'4', N'1', N'3', N'修改发货单'), (N'40', N'4005', N'3', N'删除', N'8', N'1', N'3', N'删除发货单'), (N'40', N'4005', N'4', N'发送到医院', N'256', N'1', N'3', N'发送到医院'), (N'40', N'4010', N'0', N'查看', N'1', N'1', N'3', N'收货单查看权限'), (N'40', N'4015', N'0', N'查看', N'1', N'1', N'3', N'入库单查看权限'), (N'40', N'4020', N'0', N'查看', N'1', N'1', N'3', N'退货单查看权限'), (N'60', N'6005', N'0', N'查看', N'1', N'1', N'3', N'订单统计查看'), (N'140', N'14005', N'0', N'查看', N'1', N'1', N'3', N'系统参数设置'), (N'140', N'14010', N'0', N'查看', N'1', N'1', N'3', N'系统日志查看'), (N'1000', N'100005', N'0', N'查看', N'1', N'1', N'3', N'API测试')
GO


-- ----------------------------
-- Records of t_sms_objectType
-- ----------------------------

INSERT INTO [t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (N'10', N'1005', N'10', N'1005', N'用户', N'用户管理', N'1001'), (N'10', N'1010', N'10', N'1010', N'用户类别', N'用户类别', N'1002'), (N'10', N'1015', N'10', N'1015', N'角色', N'角色管理', N'1003'), (N'10', N'1020', N'10', N'1020', N'角色类别', N'角色类别', N'1004'), (N'10', N'1025', N'10', N'1025', N'角色权限', N'角色权限', null), (N'10', N'1030', N'10', N'1030', N'供应商', N'供应商管理', N'1005'), (N'10', N'1035', N'10', N'1035', N'供应商分类', N'供应商分类', N'1006'), (N'10', N'1040', N'10', N'1040', N'证书', N'证书', null), (N'10', N'1045', N'10', N'1045', N'行业', N'行业', N'1012'), (N'10', N'1050', N'10', N'1050', N'结算方式', N'结算方式', N'1016'), (N'10', N'1055', N'10', N'1055', N'付款方式', N'付款方式', N'1014'), (N'10', N'1060', N'10', N'1060', N'物料', N'物料', N'1013'), (N'10', N'1065', N'10', N'1065', N'税种', N'税种', N'1017'), (N'10', N'1070', N'10', N'1070', N'城市', N'城市', N'1008'), (N'10', N'1075', N'10', N'1075', N'国家', N'国家', N'1009'), (N'10', N'1080', N'10', N'1080', N'区县', N'区县', N'1010'), (N'10', N'1085', N'10', N'1085', N'省份', N'省份', N'1015'), (N'20', N'2005', N'20', N'2005', N'采购订单', N'采购订单', N'2019'), (N'20', N'2010', N'20', N'2010', N'订单追踪', N'订单追踪', null), (N'30', N'3005', N'30', N'3005', N'供应商资质维护', N'供应商资质维护', N'3010'), (N'30', N'3010', N'30', N'3010', N'物料证件维护', N'物料证件维护', N'3020'), (N'30', N'3015', N'30', N'3015', N'供应商物料查询', N'供应商物料查询', N'3030'), (N'30', N'3020', N'30', N'3020', N'供应商证件类别', N'供应商证件类别', N'1007'), (N'30', N'3025', N'30', N'3025', N'物料证件类别', N'物料证件类别', N'1023'), (N'30', N'3030', N'30', N'3030', N'物料证件查看', N'物料证件查看', N'3020'), (N'40', N'4005', N'40', N'4005', N'发货单', N'发货单', N'2020'), (N'40', N'4010', N'40', N'4010', N'收货单', N'收货单', N'2021'), (N'40', N'4015', N'40', N'4015', N'入库单', N'入库单', N'2022'), (N'40', N'4020', N'40', N'4020', N'退货单', N'退货单', N'2023'), (N'60', N'6005', N'60', N'6005', N'订单统计', N'订单统计', null), (N'140', N'14005', N'140', N'14005', N'参数设置', N'参数设置', null), (N'140', N'14010', N'140', N'14010', N'系统日志', N'系统日志', N'14001'), (N'1000', N'100005', N'1000', N'100005', N'API测试', N'API测试', null)
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
INSERT INTO [t_sms_serial_number] ([id], [classId], [year], [number]) VALUES (N'2', N'2020', N'2017', N'100'), (N'3', N'2020', N'2018', N'2')
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

INSERT INTO [t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'FILE_PATH', N'上传文件保存目录', N'上传文件保存统一路径', N'C:\\file-upload\\files\\', N'12', N'{"ctlType":"text"}', N'1'), (N'sys', N'FILE_URL', N'服务器配置的文件映射地址', N'文件映射', N'/sms/attachment/', N'11', N'{"ctlType":"text"}', N'1'), (N'sys', N'hrp-login-authPattern', N'webservice登录验证方式', N'HRP公布的webservice登录验证方式', N'BaseDB', N'13', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-dbType', N'webservice登录数据库类型', N'HRP公布的webservice登录数据库类型', N'1', N'12', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-dcName', N'webservice登录数据中心', N'HRP公布的webservice登录数据中心', N'HRPTT', N'10', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-language', N'webservice登录语言', N'HRP公布的webservice登录语言', N'L2', N'11', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-namespace', N'webservice登录命名空间', N'HRP公布的webservice登录命名空间', N'http://10.0.2.8:56898/ormrpc/services/EASLogin', N'6', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-psw', N'webservice登录密码', N'HRP公布的webservice登录密码', N'kduser', N'8', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-slnName', N'webservice登录解决方案名称', N'HRP公布的webservice登录解决方案名称', N'eas', N'9', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-url', N'webservice登录地址', N'HRP公布的webservice登录地址', N'http://10.0.2.8:56898/ormrpc/services/EASLogin?wsdl', N'5', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-login-userName', N'webservice登录用户名称', N'HRP公布的webservice登录用户名称', N'user', N'7', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-header-namespace', N'webservice同步命名空间（头）', N'HRP公布的webservice命名空间（头）', N'http://login.webservice.bos.kingdee.com', N'4', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-mobie', N'同步HRP通知接收人电话', N'同步HRP通知接收人电话', N'18812345678', N'14', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-namespace', N'webservice同步命名空间', N'HRP公布的webservice命名空间', N'http://10.0.2.8:56898/ormrpc/services/WSDataSynWSFacade', N'3', N'{"ctlType":"text"}', N'0'), (N'sys', N'hrp-sync-url', N'webservice同步地址', N'HRP公布的webservice地址', N'http://10.0.2.8:56898/ormrpc/services/WSDataSynWSFacade?wsdl', N'2', N'{"ctlType":"text"}', N'0')


-- ----------------------------
-- Records of t_sms_userType
-- ----------------------------

INSERT INTO [t_sms_userType] ([typeId], [number], [name]) VALUES (N'B3sMo22ZLkWApjO/oEeDOxACEAI=', N'CUS', N'业务用户'), (N'QpXq24FxxE6c3lvHMPyYCxACEAI=', N'SYS', N'系统用户')


-- ----------------------------
-- Records of t_sms_user
-- ----------------------------

INSERT INTO [t_sms_user] ([userId], [number], [name], [password], [type], [status], [role], [supplier], [token], [phone]) VALUES (N'WtmTpjCrEE6O0KlB6RGcyBABEAE=', N'admin', N'admin', N'202cb962ac59075b964b07152d234b70', N'QpXq24FxxE6c3lvHMPyYCxACEAI=', N'0', N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'', N'23be6f0fcf4e44af8fbdf7f773526eee_cf39a4dd7b0843d08014bb6e4dccbfa1', N'18617092729')

GO

