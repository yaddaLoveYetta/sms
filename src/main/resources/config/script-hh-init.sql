USE [master]
GO
/****** Object:  Database [hrp-sms-hh]    Script Date: 2017/10/12 15:19:14 ******/
IF EXISTS (
    SELECT
        *
    FROM
        sysdatabases
    WHERE
        name = 'hrp-sms-hh'
) 
BEGIN
    DROP DATABASE [hrp-sms-hh]
END
GO
CREATE DATABASE [hrp-sms-hh] ON  PRIMARY 
( NAME = N'hrp-sms-hh', FILENAME = N'D:\SMS_DATA\hrp-sms-hh.mdf' , SIZE = 17408KB , MAXSIZE = UNLIMITED, FILEGROWTH = 10%)
 LOG ON 
( NAME = N'hrp-sms-hh_log', FILENAME = N'D:\SMS_DATA\hrp-sms-hh.ldf' , SIZE = 123648KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [hrp-sms-hh] SET COMPATIBILITY_LEVEL = 100
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [hrp-sms-hh].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [hrp-sms-hh] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET ARITHABORT OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [hrp-sms-hh] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [hrp-sms-hh] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [hrp-sms-hh] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET  DISABLE_BROKER 
GO
ALTER DATABASE [hrp-sms-hh] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [hrp-sms-hh] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [hrp-sms-hh] SET RECOVERY FULL 
GO
ALTER DATABASE [hrp-sms-hh] SET  MULTI_USER 
GO
ALTER DATABASE [hrp-sms-hh] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [hrp-sms-hh] SET DB_CHAINING OFF 
GO
EXEC sys.sp_db_vardecimal_storage_format N'hrp-sms-dev', N'ON'
GO
USE [hrp-sms-hh]
GO
/****** Object:  UserDefinedFunction [dbo].[base64encode]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER OFF
GO

CREATE FUNCTION [dbo].[base64encode]
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
/****** Object:  UserDefinedFunction [dbo].[newbosid]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER OFF
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
/****** Object:  Table [dbo].[t_sms_accessControl]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_accessControl] PRIMARY KEY CLUSTERED 
(
    [objectType] ASC,
    [objectId] ASC,
    [roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_anomaly_params]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[t_sms_anomaly_params](
    [key] [varchar](255) NOT NULL,
    [value] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
    [key] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_approved_supplier]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_approved_supplier] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_base_status]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_category]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_category] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_certificate]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_certificate] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_city]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_city] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_country]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_country] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_county]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_county] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_currency]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_currency] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_dataFlowSubClass]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_dataFlowTopClass]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_employee]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_employee] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_formClass]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_formEntries]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_formFields]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_industry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_industry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_item]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_item] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_log]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK__t_sms_lo__3213E83F5224328E] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_msglog]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_msglog] PRIMARY KEY CLUSTERED 
(
    [seqid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_objectAccessType]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_objectType]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_objectType] PRIMARY KEY CLUSTERED 
(
    [topClassId] ASC,
    [subSysId] ASC,
    [objectType] ASC,
    [objectId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_pay]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_pay] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_paymentType]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[t_sms_paymentType](
    [id] [varchar](255) NOT NULL,
    [name] [varchar](255) NULL,
 CONSTRAINT [PK_t_sms_paymentType] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_plugins]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_province]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_province] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purchase_order]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purchase_order] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purchase_order_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purchase_order_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purinwarehs]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purinwarehs] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purinwarehs_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purinwarehs_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purreceival]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purreceival] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purreceival_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purreceival_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purreturns]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purreturns] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_purreturns_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_purreturns_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_role]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_role] PRIMARY KEY CLUSTERED 
(
    [roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_roleType]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_roleType] PRIMARY KEY CLUSTERED 
(
    [typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_sale_proxy]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_sendcargo]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_sendcargo] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_sendcargo_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_sendcargo_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_serial_number]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[t_sms_serial_number](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [classId] [int] NULL,
    [year] [int] NULL,
    [number] [int] NULL,
 CONSTRAINT [PK_t_sms_serial] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[t_sms_settlement]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_settlement] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_sourceBillType]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_sourceBillType] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_status]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_subMessage]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK__t_sms_sy__3213E83F7B5B524B] PRIMARY KEY CLUSTERED 
(
    [detailId] ASC,
    [typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_subMesType]    Script Date: 2017/10/12 15:19:14 ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier]    Script Date: 2017/10/12 15:19:14 ******/
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
    [simpleName] [varchar](255) NULL,
 CONSTRAINT [PK_t_sms_supplier] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_item_license]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_supplier_item_license] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_item_license_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
    [check] [tinyint] NULL,
 CONSTRAINT [PK_t_sms_supplier_item_license_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_item_license_type]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_item_license_type] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_license]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_supplier_license] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_license_entry]    Script Date: 2017/10/12 15:19:14 ******/
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
    [check] [tinyint] NULL,
 CONSTRAINT [PK_t_sms_supplier_license_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_supplier_license_type]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_supplier_license_type] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_sys_profile]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_sys_profile] PRIMARY KEY CLUSTERED 
(
    [category] ASC,
    [key] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_taxCategory]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_taxCategory] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_unit]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_unit] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_user]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_user] PRIMARY KEY CLUSTERED 
(
    [userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_user_entry]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[t_sms_user_entry](
    [id] [varchar](50) NOT NULL,
    [parent] [varchar](50) NULL,
    [mobile] [varchar](11) NULL,
    [pic] [varchar](255) NULL,
 CONSTRAINT [PK_t_sms_user_entry] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_userType]    Script Date: 2017/10/12 15:19:14 ******/
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
 CONSTRAINT [PK_t_sms_userType] PRIMARY KEY CLUSTERED 
(
    [typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[t_sms_validation]    Script Date: 2017/10/12 15:19:14 ******/
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
/****** Object:  Table [dbo].[t_user_session]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[t_user_session](
    [userId] [int] NOT NULL,
    [session] [varchar](50) NOT NULL,
    [lostTime] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  View [dbo].[vw_newid]    Script Date: 2017/10/12 15:19:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER OFF
GO

CREATE VIEW [dbo].[vw_newid]
AS
SELECT NEWID() [FNEWID]




GO
ALTER TABLE [dbo].[t_sms_approved_supplier] ADD  CONSTRAINT [DF__t_sms_app__syncS__46B27FE2]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_approved_supplier] ADD  CONSTRAINT [DF__t_sms_app__revie__45BE5BA9]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_dataFlowSubClass] ADD  CONSTRAINT [DF__t_sms_dat__owner__72C60C4A]  DEFAULT ((0)) FOR [ownerType]
GO
ALTER TABLE [dbo].[t_sms_formClass] ADD  DEFAULT ('') FOR [bosType]
GO
ALTER TABLE [dbo].[t_sms_formEntries] ADD  DEFAULT ('') FOR [bosType]
GO
ALTER TABLE [dbo].[t_sms_formEntries] ADD  DEFAULT ('') FOR [joinType]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFiel__page__2F10007B]  DEFAULT ((0)) FOR [page]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_formF__key__60A75C0F]  DEFAULT ('') FOR [key]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__dataT__619B8048]  DEFAULT (NULL) FOR [dataType]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__ctrlT__628FA481]  DEFAULT (NULL) FOR [ctrlType]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__ctlIn__6383C8BA]  DEFAULT (NULL) FOR [ctlIndex]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFie__index__33D4B598]  DEFAULT ((0)) FOR [index]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFie__displ__34C8D9D1]  DEFAULT ((1)) FOR [display]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFie__showW__35BCFE0A]  DEFAULT ((80)) FOR [showWidth]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFie__lookU__36B12243]  DEFAULT ((0)) FOR [lookUpType]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_formFie__lookU__37A5467C]  DEFAULT ((0)) FOR [lookUpClassID]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__srcTa__693CA210]  DEFAULT ('') FOR [srcTable]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__srcTa__6A30C649]  DEFAULT (NULL) FOR [srcTableAlisAs]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__srcFi__6B24EA82]  DEFAULT ('') FOR [srcField]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__disPl__6C190EBB]  DEFAULT (NULL) FOR [disPlayField]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__disPl__6D0D32F4]  DEFAULT (NULL) FOR [disPlayNum]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__joinT__6E01572D]  DEFAULT ('') FOR [joinType]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__filte__6EF57B66]  DEFAULT ('') FOR [filter]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__defau__6FE99F9F]  DEFAULT (NULL) FOR [defaultValue]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__mustI__70DDC3D8]  DEFAULT ((0)) FOR [mustInput]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__lengt__71D1E811]  DEFAULT (NULL) FOR [length]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_form__lock__72C60C4A]  DEFAULT ((0)) FOR [lock]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__isCon__73BA3083]  DEFAULT ((0)) FOR [isCondition]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__isCou__74AE54BC]  DEFAULT ((0)) FOR [isCount]
GO
ALTER TABLE [dbo].[t_sms_formFields] ADD  CONSTRAINT [DF__t_sms_for__needS__2CF2ADDF]  DEFAULT ((1)) FOR [needSave]
GO
ALTER TABLE [dbo].[t_sms_plugins] ADD  DEFAULT ((0)) FOR [index]
GO
ALTER TABLE [dbo].[t_sms_plugins] ADD  DEFAULT ('') FOR [desc]
GO
ALTER TABLE [dbo].[t_sms_role] ADD  CONSTRAINT [DF_t_sms_role_status]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ('') FOR [number]
GO
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ((1)) FOR [enable]
GO
ALTER TABLE [dbo].[t_sms_subMessage] ADD  DEFAULT ((0)) FOR [index]
GO
ALTER TABLE [dbo].[t_sms_supplier] ADD  CONSTRAINT [DF__t_sms_sup__syncS__4F47C5E3]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_supplier] ADD  CONSTRAINT [DF__t_sms_sup__revie__43D61337]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_supplier_item_license] ADD  CONSTRAINT [DF__t_sms_sup__syncS__498EEC8D]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_supplier_item_license] ADD  CONSTRAINT [DF__t_sms_sup__revie__4A8310C6]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_supplier_item_license_type] ADD  CONSTRAINT [DF__t_sms_ite__syncS__47A6A41B]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_supplier_item_license_type] ADD  CONSTRAINT [DF__t_sms_ite__revie__489AC854]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  DEFAULT ('') FOR [description]
GO
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  CONSTRAINT [DF__t_sms_sup__syncS__4B7734FF]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_supplier_license] ADD  CONSTRAINT [DF__t_sms_sup__revie__4C6B5938]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_supplier_license_entry] ADD  DEFAULT (NULL) FOR [check]
GO
ALTER TABLE [dbo].[t_sms_supplier_license_type] ADD  CONSTRAINT [DF__t_sms_sup__syncS__4D5F7D71]  DEFAULT ((0)) FOR [syncStatus]
GO
ALTER TABLE [dbo].[t_sms_supplier_license_type] ADD  CONSTRAINT [DF__t_sms_sup__revie__4E53A1AA]  DEFAULT ((0)) FOR [review]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_pro__categ__367C1819]  DEFAULT ('') FOR [category]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_profi__key__37703C52]  DEFAULT ('') FOR [key]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_prof__name__3864608B]  DEFAULT ('') FOR [name]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_prof__desc__395884C4]  DEFAULT ('') FOR [desc]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_pro__index__3A4CA8FD]  DEFAULT ((0)) FOR [index]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_pro__expla__3B40CD36]  DEFAULT ('') FOR [explanation]
GO
ALTER TABLE [dbo].[t_sms_sys_profile] ADD  CONSTRAINT [DF__t_sys_pro__readO__3C34F16F]  DEFAULT ((0)) FOR [readOnly]
GO
ALTER TABLE [dbo].[t_sms_user] ADD  CONSTRAINT [DF__t_user__status__5165187F]  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[t_sms_user] ADD  CONSTRAINT [DF__t_sms_use__suppl__7C1A6C5A]  DEFAULT ('') FOR [supplier]
GO
ALTER TABLE [dbo].[t_sms_user] ADD  CONSTRAINT [DF__t_sms_use__token__14270015]  DEFAULT ('') FOR [token]
GO
ALTER TABLE [dbo].[t_sms_user] ADD  CONSTRAINT [DF__t_sms_use__phone__7D439ABD]  DEFAULT ('') FOR [phone]
GO
ALTER TABLE [dbo].[t_user_session] ADD  DEFAULT ((0)) FOR [userId]
GO
ALTER TABLE [dbo].[t_user_session] ADD  DEFAULT ('') FOR [session]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'objectType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'objectId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'roleId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限掩码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_accessControl', @level2type=N'COLUMN',@level2name=N'accessMask'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_anomaly_params', @level2type=N'COLUMN',@level2name=N'key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_anomaly_params', @level2type=N'COLUMN',@level2name=N'value'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用来存储系统不规则参数
如新增资料某字段默认值规则生成

保证key不同，value自由发挥' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_anomaly_params'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联物料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'materialItem'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否停用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'isStopped'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'基本计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'measureUnit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供货比例' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'supplierRate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'含税价格' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'taxPrice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生效日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'effectualDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'失效日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'uneffectualDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（1.未编辑，2.新增，3.已编辑）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier', @level2type=N'COLUMN',@level2name=N'purMeasureUnit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_approved_supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证书' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_certificate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'币种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'币种名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'币别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_currency'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上级模块ID(关联t_DataFlowTopClass中topClassId)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'topClassId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子模块ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'subSysId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子模块名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子模块排序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'明细菜单是否可见：
设置为0：系统用户不可见,供应商用户不可见
设置为1：系统用户可见,供应商用户不可见
设置为2：系统用户不可见,供应商用户可见
设置为3：系统用户可见,供应商用户可见
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'visible'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子系统网页链接地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'title图标地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'icon'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户类别，使用该功能的用户(平台用户，供应链用户，两者都可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowSubClass', @level2type=N'COLUMN',@level2name=N'ownerType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'模块ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'topClassId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'模块名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'topClassName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'排序号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可见' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'visible'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单图标' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_dataFlowTopClass', @level2type=N'COLUMN',@level2name=N'icon'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'职员手机号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_employee', @level2type=N'COLUMN',@level2name=N'mobile'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务类别-唯一' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'classId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务类别名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物理存储表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'tableName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'primaryKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'唯一标识单据类别(参考EAS设计)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formClass', @level2type=N'COLUMN',@level2name=N'bosType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'与formClass主表classId一致' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'classId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子表序号(从1开始)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'entryIndex'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'tableName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联主表字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'foreignKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'primaryKey'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'唯一标识单据类别(参考EAS设计)设置成与主表bosType一致' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'bosType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'与主表的连接关系(默认 INNER JOIN)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formEntries', @level2type=N'COLUMN',@level2name=N'joinType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务类别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'classId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标示模板字段所在的页面（0是表头，1是第一个子表，2是第二个子表，以此类推...）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'page'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段名(显示的字段名)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物理表中的字段名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'sqlColumnName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段唯一标示(用于关联表显示字段名与本表字段名同名的情况，在一个formClass中FKey是唯一的)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段类型(数字，文本等)1:数字2:文本3:日期4:布尔' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'dataType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控件类型，指示前端展示时的特殊类型，比如多选框，基础资料选择框等,3:多选按钮6:基础资料选择框' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'ctrlType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段的后台查询顺序，FIndex是用于前端界面展示时的tab顺序\r\nFCtlIndex用于后端查询构建查询脚本时模板的遍历顺序，处理因为可能涉及关联其他表查询，连接表时的顺序不可随意改变时产生的错误' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'ctlIndex'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段的显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段显示性:用于根据用户类别控制字段取值。\r\n采用二进制方式配置\r\n如3表示平台用户物业用户都显示\r\n参考t_SubSysEnum,typeID=3' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'display'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段在前端页面显示的宽度(单位px)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'showWidth'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标示是否是引用基础资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lookUpType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'引用基础资料的类别ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lookUpClassID'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'引用的表名(表示此字段是要关联表查询)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcTable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联表别名(用于关联多次同一张表时)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcTableAlisAs'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联表的字段名,当FSrcTable有值时，此字段为必填' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'srcField'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联表显示的字段名,当FSrcTable有值时，此字段为必填,即显示FSrcTable表的FDisPlayField字段' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'disPlayField'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联表显示的代码字段,当FSrcTable有值时，此字段为必填,，一般显示关联标的FNumber' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'disPlayNum'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'表链接类型(默认 INNER JOIN)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'joinType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'过滤条件' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'filter'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'默认值(控件初始化时候的默认值)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'defaultValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最大值(数值类型字段使用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'maxValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最小值(数值类型字段使用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'minValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必录： 采用二进制方式配置, 参考t_SubSysEnum,typeID=4' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'mustInput'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段长度' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'length'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'字段锁定性:表示字段页面展现的控制可编辑形式。\r\n采用二进制方式配置\r\n如3表示新增修改时都锁定\r\n参考t_SubSysEnum,typeID=2' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'lock'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可作为过滤条件，0：否，1：是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'isCondition'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否统计项，0：否，1：是' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'isCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否需要保存到数据库(即物理表是否有字段与之对应)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_formFields', @level2type=N'COLUMN',@level2name=N'needSave'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行业ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行业名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行业' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_industry'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'规格型号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'specification'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否高值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'highConsumable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item', @level2type=N'COLUMN',@level2name=N'isLotNumber'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_item'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作用户名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'userName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作用户ip' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'ip'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'message'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'operateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作类路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'clazz'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'方法名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'method'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'方法参数' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_log', @level2type=N'COLUMN',@level2name=N'params'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'objectType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'objectId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限掩码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'accessMask'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联权限项' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'accessUse'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限用户者类别，使用该权限的用户(1平台用户，2供应商用户，3两者都可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'ownerType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectAccessType', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限类别(用于区分模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'objectType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限ID(用于区分子模块)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'objectId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'权限名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_objectType', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'付款方式ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'付款方式名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'付款方式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_pay'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'bizDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购员' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'purchasePerson'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购模式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'saleProxy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否含税' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'isInTax'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'HRP确认接单时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'tickDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商接单时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'confirmTickDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'HRP确认是否接单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'tickType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商是否接单' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'confirmTick'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据编号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否加急' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'isQuicken'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'币别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'currency'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalTax'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'价税合计' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'totalTaxAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'制单日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'createTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'deliveryDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'折扣率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'discountRate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'taxRate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'含税单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'taxPrice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实际含税单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'actualTaxPrice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'折扣额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'discountAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'tax'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'本位币金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'localAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'确认时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'confirmDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'确认数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'confirmQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'基本计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发货数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purchase_order_entry', @level2type=N'COLUMN',@level2name=N'invoiceQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'bizDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'入库单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'innercode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purinwarehs_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'bizDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收货单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'收货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'innercode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'应收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'合格数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'qualifiedQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'不合格数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreceival_entry', @level2type=N'COLUMN',@level2name=N'unqualifiedQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'退货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'退货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'bizDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'baseStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'源单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'sourceBillType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'退货单子表内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'退货单内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实收数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_purreturns_entry', @level2type=N'COLUMN',@level2name=N'returnQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色类别(1:平台角色2:供应商角色)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_role', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'typeId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_roleType', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购模式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sale_proxy', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发货单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发货日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'Date'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物流公司' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'logistics'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物流单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'logisticsNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单据状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'saleProxy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发货状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发货单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'采购订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'orderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'seq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'批次' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'lot'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'批号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyBatchNum'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'个体码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'计量单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'price'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'应发数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'qty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyProDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产厂家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'dyManufacturer'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'产品注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'registrationNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'有效期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'effectiveDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单行号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'orderSeq'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实发数量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sendcargo_entry', @level2type=N'COLUMN',@level2name=N'actualQty'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结算方式ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结算方式名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结算方式' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_settlement'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'原单据类型ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sourceBillType', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'原单据类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sourceBillType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'明细属性内码（1--1000保留做系统属性，1001以上用作用户属性）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'detailId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性类别ID(内码)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'typeId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性明细名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否有效(0无效1:有效，默认有效)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'enable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'显示顺序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性表
存储系统中枚举变量值，如币别有：人民币，美元，欧元等类似的可以只用代码名称两个属性描述的资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMessage'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性ID(内码)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'typeId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N' 描述信息' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'desc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标示系统类型或用户类型(0系统类型1:用户类型)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType', @level2type=N'COLUMN',@level2name=N'systemType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'辅助属性类别表
存储系统中枚举类型名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_subMesType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'简称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税务登记号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'法人代表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'corp'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'工商注册号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'brno'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxCategoryId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税率' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'taxRate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'国家' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'country'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'城市' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'city'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'省份' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'province'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'区县' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'county'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'行业ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'industryId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分类ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'categoryId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商状态' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商手机号码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'mobile'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'联系人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'contactPerson'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'简称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier', @level2type=N'COLUMN',@level2name=N'simpleName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料证件类型(关联t_sms_material_license_type表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商(关联t_sms_supplier表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发证机关' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'authOrg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'起始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'beginDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'endDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料（关联表t_sms_item主键id）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'material'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'isMust'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license', @level2type=N'COLUMN',@level2name=N'isControl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外键，关联主表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件存放路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件是否正确' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_entry', @level2type=N'COLUMN',@level2name=N'check'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（1.未编辑，2.新增，3.已编辑）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'isMust'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'isControl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'物料证件类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_item_license_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证件类型(关联t_sms_supplier_license_type表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商(关联t_sms_supplier表主键id)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发证机关' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'authOrg'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'description'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'起始日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'beginDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结束日期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'endDate'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'isMust'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'isControl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否停用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license', @level2type=N'COLUMN',@level2name=N'prohibited'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'外键，关联主表主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'parent'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件存放路径' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件是否正确' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_entry', @level2type=N'COLUMN',@level2name=N'check'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'证件内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否必须' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'isMust'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否控制' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'isControl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'同步状态（0.未同步，1.已同步）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'syncStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核状态：0.未审核，1.已审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type', @level2type=N'COLUMN',@level2name=N'review'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'供应商证件类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_supplier_license_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数类别(通常按模块来分参数，记录模块标示)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'category'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数名称(尽量简短，不要太长)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'desc'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'value'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'index'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参数选项,配置参数的类型及类型明细
{ctlType:checkBox,}
{ctlType:selector,list:[{0:不启用},{1:启用}]}
{ctlType:text,}
{ctlType:number,}' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'explanation'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否只读参数(只读参数不可修改)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_sys_profile', @level2type=N'COLUMN',@level2name=N'readOnly'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税种ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税种名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'税种' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_taxCategory'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'单位' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_unit'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'userId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户代码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'number'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'password'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户类别(基础资料1002)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否禁用(0可用1禁用，默认0可用)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'引用角色基础资料' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'role'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'引用供应商基础资料，当type为供应商用户时必须有' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'supplier'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'hrp端与sms''交互数据通过token校验' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user', @level2type=N'COLUMN',@level2name=N'token'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_user'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_userType', @level2type=N'COLUMN',@level2name=N'typeId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户类型' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_sms_userType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'用户内码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_user_session', @level2type=N'COLUMN',@level2name=N'userId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'session' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N't_user_session', @level2type=N'COLUMN',@level2name=N'session'
GO
USE [master]
GO
ALTER DATABASE [hrp-sms-hh] SET  READ_WRITE 
GO

-- 初始化数据

USE [hrp-sms-hh]
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 207)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1010, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1015, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 15)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1020, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1025, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 5)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1030, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 511)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1030, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 293)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1035, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1035, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1045, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1045, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1050, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1050, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1055, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1055, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1060, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1060, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1065, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1065, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1070, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1070, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1075, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1075, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1080, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1080, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1085, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (10, 1085, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (20, 2005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 3)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (20, 2005, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 3)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (20, 2010, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (20, 2010, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 511)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3005, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3010, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 511)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3010, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3015, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3015, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3020, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 319)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3020, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3025, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 319)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3025, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3030, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (30, 3030, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (40, 4005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (40, 4005, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 271)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (60, 6005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (60, 6005, N'X05C+StBuUGXK5J3yIPsaRADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (140, 14005, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_accessControl] ([objectType], [objectId], [roleId], [accessMask]) VALUES (140, 14010, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1005, N'用户', 1, 1, N'html/base/index.html?classId=1001', N'account.png', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1010, N'用户类别', 2, 1, N'html/base/index.html?classId=1002', N'category.png', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1015, N'角色', 3, 1, N'html/base/index.html?classId=1003', N'role.png', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1020, N'角色类别', 4, 1, N'html/base/index.html?classId=1004', N'category.png', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1025, N'角色权限', 5, 1, N'html/rolePerm/index.html', N'permision.png', 1)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1030, N'供应商', 6, 3, N'html/base/index.html?classId=1005', N'supplier-features.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1035, N'供应商类别', 7, 1, N'html/base/index.html?classId=1006', N'category.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1045, N'行业', 9, 1, N'html/base/index.html?classId=1012', N'industry.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1050, N'结算方式', 10, 1, N'html/base/index.html?classId=1016', N'settlement.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1055, N'付款方式', 11, 1, N'html/base/index.html?classId=1014', N'payment.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1060, N'物料', 12, 1, N'html/base/index.html?classId=1013', N'goods.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1065, N'税种', 13, 1, N'html/base/index.html?classId=1017', N'category.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1070, N'城市', 14, 1, N'html/base/index.html?classId=1008', N'country.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1075, N'国家', 15, 1, N'html/base/index.html?classId=1009', N'country.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1080, N'区县', 16, 1, N'html/base/index.html?classId=1010', N'country.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (10, 1085, N'省份', 17, 1, N'html/base/index.html?classId=1015', N'country.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (20, 2005, N'采购订单', 1, 3, N'html/list/index.html?classId=2019', N'purchase.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (20, 2010, N'订单追踪', 1, 3, N'html/report/order-track/index.html', N'purchase.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3005, N'供应商资质维护', 1, 3, N'html/supplier/license/index.html?classId=3010', N'file.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3010, N'物料证件维护', 2, 3, N'html/supplier/license/index.html?classId=3020', N'file.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3015, N'供应商物料查询', 3, 3, N'html/base/index.html?classId=3030', N'search.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3020, N'供应商证件类别', 5, 3, N'html/base/index.html?classId=1007', N'category.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3025, N'物料证件类别', 6, 3, N'html/base/index.html?classId=1023', N'category.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (30, 3030, N'物料证件查询', 4, 3, N'html/base/index.html?classId=3020', N'search.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (40, 4005, N'发货单', 2, 3, N'html/list/index.html?classId=2020', N'deliver.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (60, 6005, N'订单统计', 2, 3, N'html/report/order-count/index.html', N'calculator.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (140, 14005, N'参数设置', 1, 1, N'html/sys/parameter/index.html', N'category.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (140, 14010, N'系统日志', 1, 1, N'html/sys/log/index.html', N'calendar.png', 3)
GO
INSERT [dbo].[t_sms_dataFlowSubClass] ([topClassId], [subSysId], [name], [index], [visible], [url], [icon], [ownerType]) VALUES (1000, 100005, N'API测试', 1, 0, N'html/api-tester/index.html', N'icon_order_normal.png', 0)
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (10, N'基础资料', 6, 1, N'text.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (20, N'订单管理', 1, 1, N'manage-order.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (30, N'证件管理', 4, 1, N'supplier-features.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (40, N'发货管理', 2, 1, N'deliver.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (60, N'统计查询', 5, 1, N'search.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (140, N'系统管理', 7, 1, N'set.png')
GO
INSERT [dbo].[t_sms_dataFlowTopClass] ([topClassId], [topClassName], [index], [visible], [icon]) VALUES (1000, N'Test', 100, 0, N'all.png')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1001, N'用户', N't_sms_user', N'userId', N'10011001')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1002, N'用户类别', N't_sms_userType', N'typeId', N'10021002')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1003, N'角色', N't_sms_role', N'roleId', N'10031003')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1004, N'角色类别', N't_sms_roleType', N'typeId', N'10041004')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1005, N'供应商', N't_sms_supplier', N'id', N'37C67DFC')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1006, N'供应商类别', N't_sms_category', N'id', N'7A2569A2')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1007, N'供应商证件类别', N't_sms_supplier_license_type', N'id', N'9D1A92CC')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1008, N'城市', N't_sms_city', N'id', N'0C5DD6B6')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1009, N'国家', N't_sms_country', N'id', N'2665126B')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1010, N'区县', N't_sms_county', N'id', N'859D3D7F')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1011, N'币别', N't_sms_currency', N'id', N'DEB58FDC')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1012, N'行业', N't_sms_industry', N'id', N'C3FDE1A9')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1013, N'物料', N't_sms_item', N'id', N'4409E7F0')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1014, N'付款方式', N't_sms_pay', N'id', N'6BCA0AB5')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1015, N'省份', N't_sms_province', N'id', N'818DCAFB')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1016, N'结算方式', N't_sms_settlement', N'id', N'E96B2B8E')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1017, N'税种', N't_sms_taxCategory', N'id', N'91E210CA')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1018, N'单位', N't_sms_unit', N'id', N'5B825C57')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1021, N'采购模式', N't_sms_sale_proxy', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1023, N'物料证件类型', N't_sms_supplier_item_license_type', N'id', N'2F32B746')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1024, N'职员', N't_sms_employee', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1025, N'单据状态', N't_sms_base_status', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1026, N'原单据类型', N't_sms_sourceBillType', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (1027, N'状态', N't_sms_status', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (2019, N'采购订单', N't_sms_purchase_order', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (2020, N'发货单', N't_sms_sendcargo', N'id', N'15F2BD83')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (2021, N'收货单', N't_sms_purreceival', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (2022, N'入库单', N't_sms_purinwarehs', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (2023, N'退货单', N't_sms_purreturns', N'id', N'')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (3010, N'供应商资质', N't_sms_supplier_license', N'id', N'8D759F89')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (3020, N'物料证件', N't_sms_supplier_item_license', N'id', N'24631D4E')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (3030, N'中标库', N't_sms_approved_supplier', N'id', N'ssssssss')
GO
INSERT [dbo].[t_sms_formClass] ([classId], [name], [tableName], [primaryKey], [bosType]) VALUES (14001, N'系统日志', N't_sms_log', N'id', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (1001, 1, N't_sms_user_entry', N'parent', N'entryId', N'10011001', N'left join')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (2019, 1, N't_sms_purchase_order_entry', N'parent', N'entryId', N'', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (2020, 1, N't_sms_sendcargo_entry', N'parent', N'entryId', N'15F2BD83', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (2021, 1, N't_sms_purreceival_entry', N'parent', N'entryId', N'', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (2022, 1, N't_sms_purinwarehs_entry', N'parent', N'entryId', N'', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (2023, 1, N't_sms_purreturns_entry', N'parent', N'entryId', N'', N'')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (3010, 1, N't_sms_supplier_license_entry', N'parent', N'entryId', N'10011001', N'left join')
GO
INSERT [dbo].[t_sms_formEntries] ([classId], [entryIndex], [tableName], [foreignKey], [primaryKey], [bosType], [joinType]) VALUES (3020, 1, N't_sms_supplier_item_license_entry', N'parent', N'entryId', N'10011001', N'left join')
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 1, N'电话', N'mobile', N'mobile', 2, 8, 7, 7, 127, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'账号', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 10, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'用户名', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'密码', N'password', N'password', 99, NULL, 4, 4, 60, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'手机', N'phone', N'phone', 2, 8, 7, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 1, N'pic', N'pic', N'pic', 2, 8, 7, 7, 127, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 15, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'角色', N'role', N'role', 2, 6, 6, 6, 63, 80, 1, 1003, N't_sms_Role', N'', N'roleId', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'是否禁用', N'status', N'status', 4, 3, 9, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'关联供应商', N'supplier', N'supplier', 2, 6, 8, 8, 63, 150, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'用户类别', N'type', N'type', 2, 6, 5, 5, 61, 80, 1, 1002, N't_sms_userType', N'', N'typeId', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 8, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1001, 0, N'内码', N'userId', N'userId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1002, 0, N'内码', N'typeId', N'typeId', 1, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'内码', N'roleId', N'roleId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'是否禁用', N'status', N'status', 4, 3, 13, 13, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1003, 0, N'角色类别', N'type', N'type', 2, 6, 5, 5, 61, 80, 1, 1004, N't_sms_roleType', N'', N'typeId', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 12, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1004, 0, N'内码', N'typeId', N'typeId', 1, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'地址', N'address', N'address', 2, NULL, 15, 15, 63, 300, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 80, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'工商注册号', N'brno', N'brno', 2, NULL, 6, 6, 0, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'分类', N'categoryId', N'categoryId', 2, 6, 4, 4, 63, 60, 1, 1006, N't_sms_category', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'城市', N'city', N'city', 2, 6, 12, 12, 63, 80, 1, 1008, N't_sms_city', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'联系人', N'contactPerson', N'contactPerson', 2, NULL, 16, 16, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'法人代表', N'corp', N'corp', 2, NULL, 5, 5, 0, 70, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'国家', N'country', N'country', 2, 6, 11, 11, 63, 80, 1, 1009, N't_sms_country', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'区县', N'county', N'county', 2, 6, 14, 14, 63, 80, 1, 1010, N't_sms_county', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 40, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'行业', N'industryId', N'industryId', 2, 6, 10, 10, 63, 120, 1, 1012, N't_sms_industry', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'手机号码', N'mobile', N'mobile', 2, 8, 17, 17, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'省份', N'province', N'province', 2, 6, 13, 13, 63, 80, 1, 1015, N't_sms_province', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'审核状态', N'review', N'review', 4, 3, 18, 18, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'简称', N'simpleName', N'simpleName', 2, NULL, 4, 4, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 80, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'状态', N'status', N'status', 2, 6, 18, 18, 63, 80, 1, 1026, N't_sms_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 63, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 15, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税种', N'taxCategoryId', N'taxCategoryId', 2, 6, 7, 7, 63, 70, 1, 1017, N't_sms_taxCategory', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税务登记号', N'taxId', N'taxId', 2, NULL, 9, 9, 63, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1005, 0, N'税率', N'taxRate', N'taxRate', 1, 1, 8, 8, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1006, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 5, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'审核状态', N'review', N'review', 4, 3, 6, 6, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1007, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1008, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1009, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1010, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1011, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1012, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'是否高值', N'highConsumable', N'highConsumable', 4, 3, 5, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'是否批次', N'isLotNumber', N'isLotNumber', 4, 3, 6, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1013, 0, N'规格型号', N'specification', N'specification', 2, NULL, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1014, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1015, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1016, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1017, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1018, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1021, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 4, 4, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'审核状态', N'review', N'review', 4, 3, 6, 6, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1023, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'手机号码', N'mobile', N'mobile', 2, NULL, 15, 15, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1024, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1025, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (1026, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'实际含税单价', N'actualTaxPrice', N'actualTaxPrice', 1, NULL, 10, 10, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 13, 13, 447, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'订单日期', N'bizDate', N'bizDate', 3, 12, 2, 2, 447, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'确认交货日期', N'confirmDate', N'confirmDate', 3, 12, 15, 15, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'确认数量', N'confirmQty', N'confirmQty', 1, NULL, 14, 14, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商是否接单', N'confirmTick', N'confirmTick', 4, 3, 17, 17, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商接单时间', N'confirmTickDate', N'confirmTickDate', 3, 12, 12, 12, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'制单日期', N'createTime', N'createTime', 3, 12, 10, 10, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'币别', N'currency', N'currency', 2, 6, 7, 7, 447, 150, 1, 1011, N't_sms_currency', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'交货日期', N'deliveryDate', N'deliveryDate', 3, 12, 13, 13, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'折扣额', N'discountAmount', N'discountAmount', 1, NULL, 11, 11, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'折扣率', N'discountRate', N'discountRate', 1, NULL, 7, 7, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'是否高值', N'', N'highConsumable', 4, 3, 3, 2, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'发货数量', N'invoiceQty', N'invoiceQty', 1, NULL, 5, 5, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'是否含税', N'isInTax', N'isInTax', 4, 3, 14, 14, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'是否批次', N'', N'isLotNumber', 4, 3, 3, 2, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'是否加急', N'isQuicken', N'isQuicken', 4, 3, 15, 15, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'本位币金额', N'localAmount', N'localAmount', 1, NULL, 12, 12, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'物料名称', N'material', N'material', 2, 6, 2, 2, 447, 80, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'物料编码', N'', N'materialNumber', 2, NULL, 3, 2, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'单据编号', N'number', N'number', 2, NULL, 1, 1, 447, 120, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'订单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'价格', N'price', N'price', 1, NULL, 6, 6, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'采购员', N'purchasePerson', N'purchasePerson', 2, 6, 4, 4, 447, 150, 1, 1024, N't_sms_employee', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'数量', N'qty', N'qty', 1, NULL, 4, 4, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'采购模式', N'saleProxy', N'saleProxy', 2, 6, 3, 3, 447, 60, 1, 1021, N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'规格型号', N'', N'specification', 2, NULL, 3, 2, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'供应商', N'supplier', N'supplier', 2, 6, 5, 5, 447, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'税额', N'tax', N'tax', 1, NULL, 11, 11, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'含税单价', N'taxPrice', N'taxPrice', 1, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'税率', N'taxRate', N'taxRate', 1, NULL, 8, 8, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'HRP确认接单时间', N'tickDate', N'tickDate', 3, 12, 11, 11, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'HRP确认是否接单', N'tickType', N'tickType', 4, 3, 16, 16, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'金额', N'totalAmount', N'totalAmount', 2, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'税额', N'totalTax', N'totalTax', 2, NULL, 6, 6, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 0, N'价税合计', N'totalTaxAmount', N'totalTaxAmount', 2, NULL, 8, 8, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2019, 1, N'基本计量单位', N'unit', N'unit', 2, 6, 3, 3, 447, 60, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'实发数量', N'actualQty', N'actualQty', 1, NULL, 12, 12, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'个体码', N'code', N'code', 2, NULL, 8, 8, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'发货日期', N'Date', N'Date', 3, 12, 2, 7, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'批号', N'dyBatchNum', N'dyBatchNum', 2, NULL, 7, 7, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 14, 14, 447, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 13, 13, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 447, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'是否高值', N'', N'highConsumable', 4, 3, 5, 5, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'highConsumable', N'', N'LFET JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'是否批次', N'', N'isLotNumber', 4, 3, 5, 5, 0, 80, 3, 1013, N't_sms_item', N'', N'id', N'isLotNumber', N'', N'LFET JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'物流公司', N'logistics', N'logistics', 2, NULL, 1, 5, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'物流单号', N'logisticsNo', N'logisticsNo', 2, NULL, 1, 6, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 447, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'单据编号', N'number', N'number', 2, NULL, 1, 2, 447, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 447, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'发货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'应发数量', N'qty', N'qty', 1, NULL, 11, 11, 48, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 15, 15, 447, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'订单类型', N'saleProxy', N'saleProxy', 2, 6, 3, 3, 447, 60, 1, 1021, N't_sms_sale_proxy', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'规格型号', N'', N'specification', 2, NULL, 5, 5, 447, 80, 3, 1013, N't_sms_item', N'', N'id', N'specification', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 3, 447, 100, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 0, N'是否发送到医院', N'type', N'type', 4, 3, 5, 5, 3, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2020, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 447, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'实收数量', N'actualQty', N'actualQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 5, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'收货日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 13, 13, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 12, 12, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'个体码', N'innercode', N'innercode', 2, NULL, 8, 8, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'收货单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'收货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'应收数量', N'qty', N'qty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'合格数量', N'qualifiedQty', N'qualifiedQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 14, 14, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2021, 1, N'不合格数量', N'unqualifiedQty', N'unqualifiedQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'入库数量', N'actualQty', N'actualQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'金额', N'amount', N'amount', 1, NULL, 15, 15, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 5, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'入库日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'生产厂家', N'dyManufacturer', N'dyManufacturer', 2, NULL, 13, 13, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'生产日期', N'dyProDate', N'dyProDate', 3, 12, 12, 12, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'有效期', N'effectiveDate', N'effectiveDate', 3, 12, 16, 16, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'个体码', N'innercode', N'innercode', 2, NULL, 8, 8, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'批次', N'lot', N'lot', 1, NULL, 6, 6, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'入库单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'入库单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'价格', N'price', N'price', 1, NULL, 9, 9, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'产品注册号', N'registrationNo', N'registrationNo', 2, NULL, 14, 14, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'行号', N'seq', N'seq', 2, NULL, 1, 1, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2022, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'单据状态', N'baseStatus', N'baseStatus', 2, 6, 14, 14, 63, 150, 1, 1021, N't_sms_base_status', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'退货日期', N'bizDate', N'bizDate', 3, 12, 2, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'物料名称', N'material', N'material', 2, 6, 3, 4, 63, 120, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'退货单号', N'number', N'number', 2, NULL, 1, 2, 63, 100, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单内码', N'orderId', N'orderId', 2, NULL, 2, 2, 0, 100, 4, 0, N't_sms_purchase_order', N'', N'id', N'id', N'', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 20, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单号', N'', N'orderNumber', 2, NULL, 2, 2, 63, 100, 5, 0, N't_sms_purchase_order', N'', N'id', N'number', N'', N'', N'', N'', NULL, NULL, 15, 20, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'采购订单行号', N'orderSeq', N'orderSeq', 2, NULL, 3, 3, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'退货单内码', N'parent', N'parent', 2, NULL, 1, 1, 0, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'退货数量', N'returnQty', N'returnQty', 1, NULL, 11, 11, 63, 80, 0, 0, N'', NULL, N'', NULL, NULL, N'', N'', NULL, NULL, NULL, 15, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'源单据类型', N'sourceBillType', N'sourceBillType', 2, 6, 14, 14, 63, 150, 1, 1026, N't_sms_sourceBillType', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 0, N'供应商', N'supplier', N'supplier', 2, 6, 7, 7, 63, 200, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 1, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (2023, 1, N'单位', N'unit', N'unit', 2, 6, 10, 10, 63, 80, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 0, 11, 15, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'发证机关', N'authOrg', N'authOrg', 2, NULL, 3, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'起始日期', N'beginDate', N'beginDate', 3, 12, 4, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'备注', N'description', N'description', 2, 11, 7, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'结束日期', N'endDate', N'endDate', 3, 12, 5, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 9, 11, 63, 80, 3, 1007, N't_sms_supplier_license_type', N'', N'', N'isControl', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 8, 10, 63, 80, 3, 1007, N't_sms_supplier_license_type', N'', N'', N'isMust', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'代码', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'资质', N'parent', N'parent', 2, NULL, 2, 2, 0, 80, 0, 0, N'', N'', N'', NULL, NULL, N'', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'是否停用', N'prohibited', N'prohibited', 4, 3, 10, 12, 63, 80, 0, 0, N'', N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 15, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'审核状态', N'review', N'review', 4, 3, 11, 13, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'供应商', N'supplier', N'supplier', 2, 6, 6, 8, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'LEFT JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 0, N'证书类别', N'type', N'type', 2, 6, 2, 4, 63, 80, 1, 1007, N't_sms_supplier_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3010, 1, N'附件', N'url', N'url', 2, NULL, 3, 3, 63, 150, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'发证机关', N'authOrg', N'authOrg', 2, NULL, 3, 5, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 100, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'起始日期', N'beginDate', N'beginDate', 3, 12, 4, 6, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'备注', N'description', N'description', 2, 11, 8, 10, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'结束日期', N'endDate', N'endDate', 3, 12, 5, 7, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'内码', N'id', N'entryId', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否控制', N'isControl', N'isControl', 4, 3, 10, 12, 63, 80, 3, 1023, N't_sms_supplier_item_license_type', N'', N'', N'isControl', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否必须', N'isMust', N'isMust', 4, 3, 9, 11, 63, 80, 3, 1023, N't_sms_supplier_item_license_type', N'', N'', N'isMust', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'物料', N'material', N'material', 2, 6, 7, 9, 63, 200, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'证书名称', N'name', N'name', 2, NULL, 3, 3, 63, 200, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'证书编号', N'number', N'number', 2, NULL, 2, 2, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 20, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'物料证件', N'parent', N'parent', 2, NULL, 2, 2, 0, 80, 0, 0, NULL, N'', NULL, NULL, NULL, NULL, N'', N'', NULL, NULL, 15, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'是否禁用', N'prohibited', N'prohibited', 4, 3, 11, 13, 63, 80, 0, NULL, NULL, N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'审核状态', N'review', N'review', 4, 3, 12, 14, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'供应商', N'supplier', N'supplier', 2, 6, 6, 8, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 99, 99, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 0, N'证书类别', N'type', N'type', 2, 6, 2, 4, 63, 80, 1, 1023, N't_sms_supplier_item_license_type', N'', N'id', N'name', N'number', N'INNER JOIN ', N'', N'', NULL, NULL, 15, 8, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3020, 1, N'附件路径', N'url', N'url', 2, NULL, 3, 3, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 255, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'生效日期', N'effectualDate', N'effectualDate', 3, 12, 10, 10, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'内码', N'id', N'id', 2, NULL, 1, 1, 0, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 4, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'是否停用', N'isStopped', N'isStopped', 4, 3, 12, 12, 63, 80, 0, 0, N'', N'', N'', NULL, N'', N'', N'', N'', NULL, NULL, 15, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'物料', N'materialItem', N'materialItem', 2, 6, 3, 3, 63, 80, 1, 1013, N't_sms_item', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'物料编码', N'', N'materialNumber', 2, NULL, 4, 4, 63, 80, 3, 1013, N't_sms_item', N'', N'id', N'number', N'', N'INNER JOIN', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'基本计量单位', N'measureUnit', N'measureUnit', 2, 6, 7, 7, 63, 40, 1, 1018, N't_sms_unit', N'', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'采购计量单位', N'purMeasureUnit', N'purMeasureUnit', 2, 6, 6, 6, 63, 40, 1, 1018, N't_sms_unit', N't_sms_unit_base', N'id', N'name', N'number', N'LEFT JOIN', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'审核状态', N'review', N'review', 4, 3, 13, 13, 3, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'规格型号', N'', N'specification', 2, NULL, 5, 5, 63, 80, 3, 1013, N't_sms_item', N'', NULL, N'specification', N'', N'', N'', N'', NULL, NULL, 0, 100, 15, 0, 0, 0)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'供应商', N'supplier', N'supplier', 2, 6, 2, 2, 63, 80, 1, 1005, N't_sms_supplier', N'', N'id', N'name', N'number', N'INNER JOIN', N'', N'', NULL, NULL, 15, 50, 0, 1, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'供货比例', N'supplierRate', N'supplierRate', 1, 1, 8, 8, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'同步状态', N'syncStatus', N'syncStatus', 4, 3, 14, 14, 1, 50, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 1, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'含税价格', N'taxPrice', N'taxPrice', 1, 1, 9, 9, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (3030, 0, N'失效日期', N'uneffectualDate', N'uneffectualDate', 3, 12, 11, 11, 63, 80, 0, 0, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 15, 100, 0, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'入口', N'clazz', N'clazz', 2, NULL, 35, 35, 63, 330, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'主键', N'id', N'id', 1, NULL, 5, 5, 0, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 11, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'IP', N'ip', N'ip', 2, NULL, 20, 20, 63, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'描述', N'message', N'message', 2, NULL, 30, 30, 63, 150, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'方法', N'method', N'method', 2, NULL, 40, 40, 63, 60, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'时间', N'operateTime', N'operateTime', 3, NULL, 25, 25, 63, 150, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 255, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'参数', N'params', N'params', 2, NULL, 45, 45, 63, 500, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_formFields] ([classId], [page], [name], [sqlColumnName], [key], [dataType], [ctrlType], [ctlIndex], [index], [display], [showWidth], [lookUpType], [lookUpClassID], [srcTable], [srcTableAlisAs], [srcField], [disPlayField], [disPlayNum], [joinType], [filter], [defaultValue], [maxValue], [minValue], [mustInput], [length], [lock], [isCondition], [isCount], [needSave]) VALUES (14001, 0, N'用户', N'userName', N'userName', 2, NULL, 15, 15, 63, 80, 0, NULL, N'', N'', N'', N'', N'', N'', N'', N'', NULL, NULL, 0, 50, NULL, 0, 0, 1)
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 0, N'查看', 1, 1, 3, N'用户管理查看权限-由此权限才可进入用户管理界面')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 3, N'删除', 8, 1, 3, N'删除')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 5, N'禁用', 64, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1005, 6, N'反禁用', 128, 1, 3, N'反禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1010, 0, N'查看', 1, 1, 3, N'用户类别查看权限-由此权限才可进入用户类别界面')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1015, 0, N'查看', 1, 1, 3, N'角色查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1015, 1, N'新增', 2, 1, 3, N'角色新增权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1015, 2, N'修改', 4, 1, 3, N'角色修改权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1015, 3, N'删除', 8, 1, 3, N'角色删除权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1020, 0, N'查看', 1, 1, 3, N'角色类别管理查看权限-由此权限才可进入角色类别界面')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1025, 0, N'查看', 1, 1, 3, N'角色权限查看权限-由此权限才可进入角色权限界面')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1025, 1, N'授权', 4, 1, 3, N'修改角色权限明细的权限(授权)')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 0, N'查看', 1, 1, 3, N'供应商管理查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 3, N'删除', 8, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 4, N'审核', 16, 1, 1, N'审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 5, N'反审核', 32, 1, 1, N'反审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 6, N'禁用', 64, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 7, N'反禁用', 128, 1, 3, N'反禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1030, 8, N'同步', 256, 1, 3, N'同步')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1035, 0, N'查看', 1, 1, 3, N'供应商类别查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1045, 0, N'查看', 1, 1, 3, N'行业查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1050, 0, N'查看', 1, 1, 3, N'结算方式查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1055, 0, N'查看', 1, 1, 3, N'付款方式查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1060, 0, N'查看', 1, 1, 3, N'物料查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1065, 0, N'查看', 1, 1, 3, N'税种查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1070, 0, N'查看', 1, 1, 3, N'城市查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1075, 0, N'查看', 1, 1, 3, N'国家查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1080, 0, N'查看', 1, 1, 3, N'区县查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (10, 1085, 0, N'查看', 1, 1, 3, N'省份查看权限
')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (20, 2005, 0, N'查看', 1, 1, 3, N'采购订单查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (20, 2005, 1, N'生成发货单', 2, 1, 3, N'生成发货单')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (20, 2010, 0, N'查看', 1, 1, 3, N'订单追踪查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 0, N'查看', 1, 1, 3, N'供应商资质维护查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 3, N'删除', 8, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 4, N'审核', 16, 1, 1, N'审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 5, N'反审核', 32, 1, 1, N'反审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 6, N'禁用', 64, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 7, N'反禁用', 128, 1, 3, N'反禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3005, 8, N'同步', 256, 1, 3, N'同步')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 0, N'查看', 1, 1, 3, N'物料证件维护查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 3, N'删除', 8, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 4, N'审核', 16, 1, 1, N'审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 5, N'反审核', 32, 1, 1, N'反审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 6, N'禁用', 64, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 7, N'反禁用', 128, 1, 3, N'反禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3010, 8, N'同步', 256, 1, 3, N'同步')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3015, 0, N'查看', 1, 1, 3, N'供应商物料查询查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 0, N'查看', 1, 1, 3, N'供应商证件类别查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 3, N'删除', 8, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 4, N'审核', 16, 1, 3, N'审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 5, N'反审核', 32, 1, 1, N'反审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3020, 6, N'同步', 256, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 0, N'查看', 1, 1, 3, N'物料证件类别查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 1, N'新增', 2, 1, 3, N'新增')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 2, N'修改', 4, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 3, N'删除', 8, 1, 3, N'修改')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 4, N'审核', 16, 1, 3, N'审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 5, N'反审核', 32, 1, 3, N'反审核')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3025, 6, N'同步', 256, 1, 3, N'禁用')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (30, 3030, 0, N'查看', 1, 1, 3, N'物料证件查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4005, 0, N'查看', 1, 1, 3, N'发货单查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4005, 1, N'新增', 2, 1, 3, N'新增发货单')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4005, 2, N'修改', 4, 1, 3, N'修改发货单')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4005, 3, N'删除', 8, 1, 3, N'删除发货单')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4005, 4, N'发送到医院', 256, 1, 3, N'发送到医院')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4010, 0, N'查看', 1, 1, 3, N'收货单查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4015, 0, N'查看', 1, 1, 3, N'入库单查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (40, 4020, 0, N'查看', 1, 1, 3, N'退货单查看权限')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (60, 6005, 0, N'查看', 1, 1, 3, N'订单统计查看')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (140, 14005, 0, N'查看', 1, 1, 3, N'系统参数设置')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (140, 14010, 0, N'查看', 1, 1, 3, N'系统日志查看')
GO
INSERT [dbo].[t_sms_objectAccessType] ([objectType], [objectId], [index], [name], [accessMask], [accessUse], [ownerType], [description]) VALUES (1000, 100005, 0, N'查看', 1, 1, 3, N'API测试')
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1005, 10, 1005, N'用户', N'用户管理', 1001)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1010, 10, 1010, N'用户类别', N'用户类别', 1002)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1015, 10, 1015, N'角色', N'角色管理', 1003)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1020, 10, 1020, N'角色类别', N'角色类别', 1004)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1025, 10, 1025, N'角色权限', N'角色权限', NULL)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1030, 10, 1030, N'供应商', N'供应商管理', 1005)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1035, 10, 1035, N'供应商分类', N'供应商分类', 1006)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1040, 10, 1040, N'证书', N'证书', NULL)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1045, 10, 1045, N'行业', N'行业', 1012)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1050, 10, 1050, N'结算方式', N'结算方式', 1016)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1055, 10, 1055, N'付款方式', N'付款方式', 1014)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1060, 10, 1060, N'物料', N'物料', 1013)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1065, 10, 1065, N'税种', N'税种', 1017)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1070, 10, 1070, N'城市', N'城市', 1008)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1075, 10, 1075, N'国家', N'国家', 1009)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1080, 10, 1080, N'区县', N'区县', 1010)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (10, 1085, 10, 1085, N'省份', N'省份', 1015)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (20, 2005, 20, 2005, N'采购订单', N'采购订单', 2019)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (20, 2010, 20, 2010, N'订单追踪', N'订单追踪', NULL)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3005, 30, 3005, N'供应商资质维护', N'供应商资质维护', 3010)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3010, 30, 3010, N'物料证件维护', N'物料证件维护', 3020)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3015, 30, 3015, N'供应商物料查询', N'供应商物料查询', 3030)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3020, 30, 3020, N'供应商证件类别', N'供应商证件类别', 1007)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3025, 30, 3025, N'物料证件类别', N'物料证件类别', 1023)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (30, 3030, 30, 3030, N'物料证件查看', N'物料证件查看', 3020)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (40, 4005, 40, 4005, N'发货单', N'发货单', 2020)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (40, 4010, 40, 4010, N'收货单', N'收货单', 2021)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (40, 4015, 40, 4015, N'入库单', N'入库单', 2022)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (40, 4020, 40, 4020, N'退货单', N'退货单', 2023)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (60, 6005, 60, 6005, N'订单统计', N'订单统计', NULL)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (140, 14005, 140, 14005, N'参数设置', N'参数设置', NULL)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (140, 14010, 140, 14010, N'系统日志', N'系统日志', 14001)
GO
INSERT [dbo].[t_sms_objectType] ([topClassId], [subSysId], [objectType], [objectId], [name], [description], [classId]) VALUES (1000, 100005, 1000, 100005, N'API测试', N'API测试', NULL)
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (1, 1001, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 0, N'用户管理插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (2, 1002, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 1, N'用户管理插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (3, 1005, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 2, N'供应商插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (4, 2019, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 3, N'订单管理插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (5, 1006, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 4, N'供应商分类插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (6, 1007, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 5, N'证书插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (7, 1008, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 6, N'城市插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (8, 1009, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 7, N'国家插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (9, 1010, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 8, N'区县插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (10, 1011, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 9, N'币别插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (11, 1012, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 10, N'行业插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (12, 1013, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 11, N'物料插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (13, 1014, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 12, N'付款方式插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (14, 1015, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 13, N'省份插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (15, 1016, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 14, N'结算方式插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (16, 1017, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 15, N'税种插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (17, 1018, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 16, N'单位插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (18, 1024, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 17, N'采购员插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (19, 2019, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 18, N'采购订单详情插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (20, 2020, N'com.kingdee.eas.hrp.sms.service.plugin.impl.BillPlugin', 19, N'发货单插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (21, 3010, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 20, N'供应商资质插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (22, 3020, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 21, N'物料证件插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (23, 3030, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 22, N'中标库插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (24, 1021, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 23, N'采购模式插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (25, 1025, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 24, N'单据状态插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (26, 1023, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 25, N'物料证件类别插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (27, 3040, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 26, N'供应商资质附件插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (28, 3050, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 27, N'供应商物料附件插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (29, 1026, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 28, N'原单据类型插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (30, 1003, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 1, N'角色插件')
GO
INSERT [dbo].[t_sms_plugins] ([id], [classId], [plugName], [index], [desc]) VALUES (31, 2020, N'com.kingdee.eas.hrp.sms.service.plugin.impl.ItemPlugin', 29, N'发货单插件')
GO
INSERT [dbo].[t_sms_role] ([roleId], [name], [number], [type], [status]) VALUES (N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'系统管理员', N'admin', N'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=', 0)
GO
INSERT [dbo].[t_sms_role] ([roleId], [name], [number], [type], [status]) VALUES (N'X05C+StBuUGXK5J3yIPsaRADEAM=', N'设备供应商', N'supplier', N'f1sGInqJq0aUNY5MmpKM8RAEEAQ=', 0)
GO
INSERT [dbo].[t_sms_roleType] ([typeId], [number], [name]) VALUES (N'f1sGInqJq0aUNY5MmpKM8RAEEAQ=', N'CUS', N'供应商角色')
GO
INSERT [dbo].[t_sms_roleType] ([typeId], [number], [name]) VALUES (N'Ro9iCuOsVEmznmE+YZSi7hAEEAQ=', N'SYS', N'系统角色')
GO
SET IDENTITY_INSERT [dbo].[t_sms_serial_number] ON 

GO
INSERT [dbo].[t_sms_serial_number] ([id], [classId], [year], [number]) VALUES (2, 2020, 2017, 100)
GO
INSERT [dbo].[t_sms_serial_number] ([id], [classId], [year], [number]) VALUES (3, 2020, 2018, 2)
GO
SET IDENTITY_INSERT [dbo].[t_sms_serial_number] OFF
GO
INSERT [dbo].[t_sms_status] ([id], [name], [number]) VALUES (0, N'未核准', NULL)
GO
INSERT [dbo].[t_sms_status] ([id], [name], [number]) VALUES (1, N'核准', NULL)
GO
INSERT [dbo].[t_sms_status] ([id], [name], [number]) VALUES (2, N'禁用', NULL)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 10, N'', N'数字', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 15, N'', N'新增时对于平台用户锁定', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 20, N'', N'对于平台用户列表显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 25, N'', N'新增时对于平台用户必填', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 30, N'', N'小数', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (1, 35, N'', N'引用基础资料', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (2, 10, N'', N'文本值', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (2, 15, N'', N'编辑时对于平台用户锁定', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (2, 20, N'', N'对于供应商用户列表显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (2, 25, N'', N'编辑时对于平台用户必填', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (2, 35, N'', N'引用辅助属性', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (3, 10, N'', N'日期时间', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (3, 30, N'', N'checkbox', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (3, 35, N'', N'引用基础资料的附加属性', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (4, 10, N'', N'布尔', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (4, 15, N'', N'新增时对于供应商用户锁定', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (4, 20, N'', N'编辑时对于平台用户显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (4, 25, N'', N'新增时对于供应商用户必填', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (4, 35, N'', N'普通引用其他表-关联查询', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (5, 10, N'', N'密码', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (5, 30, N'', N'下拉框', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (5, 35, N'', N'普通引用其他表的其他字段-主要为了避免为4即引用他表数据时，需引用多个字段时关联表重复问题。依附于=4时存在', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (6, 30, N'', N'F7选择框', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (7, 30, N'', N'级联选择器', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (8, 15, N'', N'编辑时对于供应商用户锁定', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (8, 20, N'', N'编辑时对于供应商用户显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (8, 25, N'', N'编辑时对于供应商用户必填', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (8, 30, N'', N'手机号码', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (9, 30, N'', N'座机电话', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (10, 30, N'', N'普通文本', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (11, 30, N'', N'多行文本', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (12, 30, N'', N'日期时间', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (16, 20, N'', N'新增时对于平台用户显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (32, 20, N'', N'新增时对于供应商用户显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (64, 20, N'', N'是否在列表中显示(子表模板独有,子表数据显示在表头列表中)', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (98, 30, N'', N'男：女', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (99, 30, N'', N'密码控件', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (128, 20, N'', N'平台用户详情查看时显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMessage] ([detailId], [typeId], [number], [name], [enable], [index]) VALUES (256, 20, N'', N'供应商用户详情查看时显示', 1, 0)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (10, N'字段类型', N'基础资料元数据配置中的dataType值参考这里，一般可设置为该字段在数据库里面的储存类型对应的java数据类型', 1)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (15, N'字段锁定性', N'基础资料元数据配置中的lock值参考这里，用于前段页面控制字段可用性', 1)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (20, N'字段数据权限控制类型', N'后端元数据配置中的disPlay值参考这里', 1)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (25, N'字段必录性控制类型', N'后端元数据配置中的mustInput值参考这里', 1)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (30, N'控件类型', N'控件类型-用于描述元数据在前端页面的展示结构,后端元数据配置中的ctrlType值参考这里', 1)
GO
INSERT [dbo].[t_sms_subMesType] ([typeId], [name], [desc], [systemType]) VALUES (35, N'标示字段的引用类型', N'标示字段的引用类型，如引用基础资料、辅助资料，基础资料属性等', 1)
GO


INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'FILE_PATH', N'上传文件保存目录', N'上传文件保存统一路径', N'C:\\file-upload\\files\\', 12, N'{"ctlType":"text"}', 1)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'FILE_URL', N'服务器配置的文件映射地址', N'文件映射', N'/sms/attachment/', 11, N'{"ctlType":"text"}', 1)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-authPattern', N'webservice登录验证方式', N'HRP公布的webservice登录验证方式', N'BaseDB', 13, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-dbType', N'webservice登录数据库类型', N'HRP公布的webservice登录数据库类型', N'1', 12, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-dcName', N'webservice登录数据中心', N'HRP公布的webservice登录数据中心', N'HRPTT', 10, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-language', N'webservice登录语言', N'HRP公布的webservice登录语言', N'L2', 11, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-namespace', N'webservice登录命名空间', N'HRP公布的webservice登录命名空间', N'http://10.0.2.8:56898/ormrpc/services/EASLogin', 6, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-psw', N'webservice登录密码', N'HRP公布的webservice登录密码', N'kduser', 8, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-slnName', N'webservice登录解决方案名称', N'HRP公布的webservice登录解决方案名称', N'eas', 9, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-url', N'webservice登录地址', N'HRP公布的webservice登录地址', N'http://10.0.2.8:56898/ormrpc/services/EASLogin?wsdl', 5, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-login-userName', N'webservice登录用户名称', N'HRP公布的webservice登录用户名称', N'user', 7, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-sync-header-namespace', N'webservice同步命名空间（头）', N'HRP公布的webservice命名空间（头）', N'http://login.webservice.bos.kingdee.com', 4, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-sync-mobie', N'同步HRP通知接收人电话', N'同步HRP通知接收人电话', N'18812345678', 14, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-sync-namespace', N'webservice同步命名空间', N'HRP公布的webservice命名空间', N'http://10.0.2.8:56898/ormrpc/services/WSDataSynWSFacade', 3, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'hrp-sync-url', N'webservice同步地址', N'HRP公布的webservice地址', N'http://10.0.2.8:56898/ormrpc/services/WSDataSynWSFacade?wsdl', 2, N'{"ctlType":"text"}', 0)
GO
INSERT [dbo].[t_sms_sys_profile] ([category], [key], [name], [desc], [value], [index], [explanation], [readOnly]) VALUES (N'sys', N'Prompt-lead-day',N'证件过期提示提前期(天)', N'距离证件过期多少天开启过期提示', N'7', 0, N'{"ctlType":"int"}', 0);
GO
INSERT [dbo].[t_sms_user] ([userId], [number], [name], [password], [type], [status], [role], [supplier], [token], [phone]) VALUES (N'WtmTpjCrEE6O0KlB6RGcyBABEAE=', N'admin', N'admin', N'202cb962ac59075b964b07152d234b70', N'QpXq24FxxE6c3lvHMPyYCxACEAI=', 0, N'V57Q1jPcikWaqcCeDlvvfxADEAM=', N'', N'2a8dfd4ebd3d45bd9f93046a82dd4d07_d54f3078385144d6afb42f2d974fc061', N'18617092729')
GO
INSERT [dbo].[t_sms_userType] ([typeId], [number], [name]) VALUES (N'B3sMo22ZLkWApjO/oEeDOxACEAI=', N'CUS', N'业务用户')
GO
INSERT [dbo].[t_sms_userType] ([typeId], [number], [name]) VALUES (N'QpXq24FxxE6c3lvHMPyYCxACEAI=', N'SYS', N'系统用户')
GO
INSERT [dbo].[t_sms_validation] ([a]) VALUES (N'1')
GO

---- script end------