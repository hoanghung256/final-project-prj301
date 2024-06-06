CREATE DATABASE FinalProjectPRJ301

USE FinalProjectPRJ301
GO

CREATE TABLE [User] (
	[id] INT IDENTITY(1,1),
	[username] VARCHAR(100),
	[password] VARCHAR(MAX),
	[email] VARCHAR(100),
	[phone] VARCHAR(10),
	[gender] BIT,
	[dob] DATE,
	[address] NVARCHAR(MAX),
	[joinAt] DATE DEFAULT GETDATE(),
	[avatarUrl] VARCHAR(MAX),
	[balance] INT DEFAULT 0,
	[isShop] BIT DEFAULT 0,
	[isAdmin] BIT DEFAULT 0,

	CONSTRAINT PK_User PRIMARY KEY ([id])
);

CREATE TABLE [Category] (
	[id] INT IDENTITY(1,1),
	[name] NVARCHAR(100),
	[parentId] INT,
	[createAt] DATETIME DEFAULT GETDATE(),

	CONSTRAINT PK_Category PRIMARY KEY ([id]),
	FOREIGN KEY ([parentId]) REFERENCES [Category]([id])
);

CREATE TABLE [Product] (
	[id] INT IDENTITY(1,1),
	[productName] NVARCHAR(MAX),
	[sellBy] INT,
	[categoryId] INT,
	[description] NVARCHAR(MAX),
	[price] INT DEFAULT 0,
	[createAt] DATETIME DEFAULT GETDATE(),
	[updateAt] DATETIME DEFAULT GETDATE(),
	[totalBuy] INT DEFAULT 0,
	[avatarUrl] VARCHAR(MAX) NOT NULL,

	CONSTRAINT PK_Product PRIMARY KEY ([id]),
	FOREIGN KEY ([sellBy]) REFERENCES [User]([id]),
	FOREIGN KEY ([categoryId]) REFERENCES [Category]([id])
);

CREATE TABLE [ProductImage] (
	[id] INT IDENTITY(1,1),
	[productId] INT,
	[url] VARCHAR(MAX) NOT NULL,
	[uploadedAt] DATETIME DEFAULT GETDATE(),

	CONSTRAINT PK_ProductImage PRIMARY KEY ([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id])
);

CREATE TABLE [Cart] (
	[id] INT IDENTITY(1,1),
	[userId] INT,

	CONSTRAINT PK_Cart PRIMARY KEY ([id], [userId]),
	FOREIGN KEY ([userId]) REFERENCES [User]([id])
);

CREATE TABLE [CartItem] (
	[id] INT IDENTITY(1,1),
	[cartId] INT,
);