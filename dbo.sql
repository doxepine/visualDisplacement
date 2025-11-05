/*
 Navicat Premium Dump SQL

 Source Server         : 本地_SQLServer
 Source Server Type    : SQL Server
 Source Server Version : 16001000 (16.00.1000)
 Source Host           : localhost:1433
 Source Catalog        : VD
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 16001000 (16.00.1000)
 File Encoding         : 65001

 Date: 08/09/2025 14:21:50
*/


-- ----------------------------
-- Table structure for aruco
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[aruco]') AND type IN ('U'))
	DROP TABLE [dbo].[aruco]
GO

CREATE TABLE [dbo].[aruco] (
  [ID] int  NOT NULL,
  [camera_id] int  NULL,
  [physical_width] float(53)  NULL,
  [pixel_width] float(53)  NULL,
  [init_x] float(53)  NULL,
  [init_y] float(53)  NULL,
  [rate] float(53)  NULL
)
GO

ALTER TABLE [dbo].[aruco] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for camera
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[camera]') AND type IN ('U'))
	DROP TABLE [dbo].[camera]
GO

CREATE TABLE [dbo].[camera] (
  [ip] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [port] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [username] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [password] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [id] int  IDENTITY(1,1) NOT NULL
)
GO

ALTER TABLE [dbo].[camera] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for config
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[config]') AND type IN ('U'))
	DROP TABLE [dbo].[config]
GO

CREATE TABLE [dbo].[config] (
  [id] int  NOT NULL,
  [background_image] varchar(max) COLLATE Chinese_PRC_CI_AS  NULL,
  [backend_refresh_time] int  NULL,
  [frontend_refresh_time] int  NULL
)
GO

ALTER TABLE [dbo].[config] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for displacement
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[displacement]') AND type IN ('U'))
	DROP TABLE [dbo].[displacement]
GO

CREATE TABLE [dbo].[displacement] (
  [datetime] varchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [aruco_id] int  NULL,
  [camera_id] int  NULL,
  [dx] float(53)  NULL,
  [dy] float(53)  NULL,
  [img_path] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[displacement] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Table structure for record
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[record]') AND type IN ('U'))
	DROP TABLE [dbo].[record]
GO

CREATE TABLE [dbo].[record] (
  [tm] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [camera_ID] int  NULL,
  [aruco_ID] int  NULL,
  [width] float(53)  NULL,
  [pos_x] float(53)  NULL,
  [pos_y] float(53)  NULL,
  [img_path] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[record] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Primary Key structure for table aruco
-- ----------------------------
ALTER TABLE [dbo].[aruco] ADD CONSTRAINT [PK__Aruco__3214EC27FC149E17] PRIMARY KEY CLUSTERED ([ID])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for camera
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[camera]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table camera
-- ----------------------------
ALTER TABLE [dbo].[camera] ADD CONSTRAINT [PK__camera__3213E83F4DAEC47D] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table config
-- ----------------------------
ALTER TABLE [dbo].[config] ADD CONSTRAINT [PK__config__3213E83F4328D3CA] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Triggers structure for table record
-- ----------------------------
CREATE TRIGGER [dbo].[trg_Insert_Record_To_Displacement]
ON [dbo].[record]
WITH EXECUTE AS CALLER
FOR INSERT
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO [dbo].[displacement] (
        [datetime],
        [aruco_id],
        [camera_id],
        [dx],
        [dy],
        [img_path]
    )
    SELECT 
        -- 这里将 record.tm 转换为 datetime 类型
        i.tm,
        i.aruco_ID,
        i.camera_ID,
        (i.pos_x - a.init_x) * a.rate AS dx,
        (i.pos_y - a.init_y) * a.rate AS dy,
        i.img_path
    FROM inserted i
    INNER JOIN [dbo].[aruco] a
        ON i.camera_ID = a.camera_id
       AND i.aruco_ID = a.ID;
END
GO

