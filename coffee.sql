USE [master]
GO
/****** Object:  Database [coffee]    Script Date: 7/27/2022 3:39:07 PM ******/
CREATE DATABASE [coffee]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'coffee', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\coffee.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'coffee_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\coffee_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [coffee] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [coffee].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [coffee] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [coffee] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [coffee] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [coffee] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [coffee] SET ARITHABORT OFF 
GO
ALTER DATABASE [coffee] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [coffee] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [coffee] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [coffee] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [coffee] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [coffee] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [coffee] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [coffee] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [coffee] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [coffee] SET  ENABLE_BROKER 
GO
ALTER DATABASE [coffee] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [coffee] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [coffee] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [coffee] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [coffee] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [coffee] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [coffee] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [coffee] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [coffee] SET  MULTI_USER 
GO
ALTER DATABASE [coffee] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [coffee] SET DB_CHAINING OFF 
GO
ALTER DATABASE [coffee] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [coffee] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [coffee] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [coffee] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [coffee] SET QUERY_STORE = OFF
GO
USE [coffee]
GO
/****** Object:  Table [dbo].[admin]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[admin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](30) NULL,
	[password] [varchar](30) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[mausac] [varchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[coffee_table]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[coffee_table](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[customer]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[customer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[table_id] [int] NULL,
	[phone] [varchar](30) NULL,
	[email] [varchar](100) NULL,
	[create_at] [datetime] NULL,
	[quantity] [int] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[employee]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[employee](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NOT NULL,
	[username] [varchar](30) NOT NULL,
	[password] [varchar](30) NOT NULL,
	[gender] [bit] NOT NULL,
	[card] [bigint] NOT NULL,
	[birthday] [date] NULL,
	[phone] [varchar](30) NULL,
	[email] [varchar](100) NULL,
	[address] [ntext] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[discount] [float] NULL,
	[table_id] [int] NULL,
	[emp_id] [int] NULL,
	[create_at] [datetime] NULL,
	[total_price] [float] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[order_id] [int] NULL,
	[pro_id] [int] NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 7/27/2022 3:39:07 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[cat_id] [int] NULL,
	[price] [float] NOT NULL,
	[create_at] [datetime] NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[customer] ADD  DEFAULT (getdate()) FOR [create_at]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT (getdate()) FOR [create_at]
GO
ALTER TABLE [dbo].[product] ADD  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[customer]  WITH CHECK ADD FOREIGN KEY([table_id])
REFERENCES [dbo].[coffee_table] ([id])
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([emp_id])
REFERENCES [dbo].[employee] ([id])
GO
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([table_id])
REFERENCES [dbo].[coffee_table] ([id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[order] ([id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([pro_id])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD FOREIGN KEY([cat_id])
REFERENCES [dbo].[category] ([id])
GO
USE [master]
GO
ALTER DATABASE [coffee] SET  READ_WRITE 
GO
