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

	CONSTRAINT PK_Cart PRIMARY KEY ([id]),
	FOREIGN KEY ([userId]) REFERENCES [User]([id])
);

CREATE TABLE [CartItem] (
	[id] INT IDENTITY(1,1),
	[cartId] INT,
	[productId] INT,
	[quantity] INT,
	[totalPrice] INT,

	CONSTRAINT PK_CartItem PRIMARY KEY ([id]),
	FOREIGN KEY ([cartId]) REFERENCES [Cart]([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id])
);

CREATE TABLE [PaymentType] (
	[id] INT IDENTITY(1,1),
	[paymentName] NVARCHAR(100),

	CONSTRAINT PK_PaymentType PRIMARY KEY ([id])
);

CREATE TABLE [Order] (
	[id] INT IDENTITY(1,1),
	[userId] INT,
	[orderAt] DATETIME,
	[isPay] BIT DEFAULT 0,
	[paymentTypeId] INT,

	CONSTRAINT PK_Order PRIMARY KEY ([id]),
	FOREIGN KEY ([paymentTypeId]) REFERENCES [PaymentType]([id])
);

CREATE TABLE [OrderDetail] (
	[id] INT IDENTITY(1,1),
	[productId] INT,
	[orderId] INT,
	[quantity] INT,
	[totalPrice] INT,
	[deliveryStatus] VARCHAR(50) DEFAULT 'preparing', -- 'preparing' 'shipping' 'delivered'
	[deliveryAddress] NVARCHAR(MAX),

	CONSTRAINT PK_OrderDetail PRIMARY KEY ([id]),
	FOREIGN KEY ([orderId]) REFERENCES [Order]([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id])
);

INSERT INTO PaymentType(paymentName) 
VALUES 
	('COD'), 
	('QR CODE')
