-- Create database and use it
CREATE DATABASE FinalProjectPRJ301
GO;
USE FinalProjectPRJ301
GO;

-- Create User table first as it is referenced by multiple tables
CREATE TABLE [User] (
	[id] INT IDENTITY(1,1),
	[username] VARCHAR(100),
	[password] VARCHAR(MAX),
	[email] VARCHAR(100),
	[phone] VARCHAR(10),
	[gender] VARCHAR(20),
	[dob] DATE,
	[address] NVARCHAR(MAX),
	[joinAt] DATE DEFAULT GETDATE(),
	[avatarUrl] VARCHAR(MAX),
	[balance] INT DEFAULT 0,
	[isShop] BIT DEFAULT 0,
	[isAdmin] BIT DEFAULT 0,
	CONSTRAINT PK_User PRIMARY KEY ([id])
);

-- Insert data into User table
INSERT INTO [User] ([username], [password], [email], [phone], [gender], [dob], [address], [avatarUrl], [balance], [isShop], [isAdmin])
VALUES 
('Nguyen Minh Thang', '12345', 'anhnq@fpt.edu.vn', '0123456789', 'MALE', '2004-03-10', '35 Nguyen Dinh Hien', 'https://i.pinimg.com/564x/3d/46/ea/3d46ea3ac49f42439cf00d076cb9d626.jpg', 100000, 0, 0),
('Nguyen Quoc Anh', '12345', 'anhnq@fpt.edu.vn', '0123456789', 'MALE', '2004-03-10', '35 Nguyen Dinh Hien', 'https://i.pinimg.com/564x/3d/46/ea/3d46ea3ac49f42439cf00d076cb9d626.jpg', 100000, 1, 0)

-- Create Category table
CREATE TABLE [Category] (
	[id] INT IDENTITY(1,1),
	[name] NVARCHAR(100),
	[parentId] INT,
	[createAt] DATETIME DEFAULT GETDATE(),
	CONSTRAINT PK_Category PRIMARY KEY ([id]),
	FOREIGN KEY ([parentId]) REFERENCES [Category]([id])
);

-- Insert data into Category table
INSERT INTO [Category] ([name], [parentId])
VALUES 
('Electronics', NULL),
('Home Appliances', NULL),
('Kitchen Appliances', NULL),
('Fashion', NULL),
('Cosmetic', NULL)

-- Create Product table which references User and Category
CREATE TABLE [Product] (
	[id] INT IDENTITY(1,1),
	[productName] NVARCHAR(MAX),
	[sellBy] INT,
	[categoryId] INT,
	[description] NVARCHAR(MAX),
	[price] INT DEFAULT 0,
	[quantity] INT DEFAULT 0,
	[sold] INT DEFAULT 0,
	[createAt] DATETIME DEFAULT GETDATE(),
	[updateAt] DATETIME DEFAULT GETDATE(),
	[totalBuy] INT DEFAULT 0,
	[avatarUrl] VARCHAR(MAX) NOT NULL,
	CONSTRAINT PK_Product PRIMARY KEY ([id]),
	FOREIGN KEY ([sellBy]) REFERENCES [User]([id]),
	FOREIGN KEY ([categoryId]) REFERENCES [Category]([id])
);

-- Insert data into Product table
INSERT INTO [Product] ([productName], [sellBy], [categoryId], [description], [price], [avatarUrl])
VALUES
('T-Shirt', 1, 1, 'A comfortable T-shirt', 1000, 'https://i.pinimg.com/564x/0d/35/c5/0d35c5fad1af587657a2680d474ba503.jpg')

-- Create Review table which references Product and User
CREATE TABLE [Review] (
	[id] INT IDENTITY(1,1),
	[productId] INT,
	[userId] INT,
	[content] NVARCHAR(1000),
	[rate] INT DEFAULT 0,
	[createAt] DATETIME DEFAULT GETDATE(),
	CONSTRAINT PK_Review PRIMARY KEY ([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id]),
	FOREIGN KEY ([userId]) REFERENCES [User]([id])
);

-- Insert data into Review table
INSERT INTO [Review] ([productId], [userId], [content], [rate])
VALUES
(1, 2, 'Great T-shirt, very fit and comfortable.', 5)

-- Create ReviewReply table which references Review
CREATE TABLE [ReviewReply] (
	[id] INT IDENTITY(1,1),
	[reviewId] INT,
	[content] NVARCHAR(1000),
	[createAt] DATETIME DEFAULT GETDATE(),
	CONSTRAINT PK_ReviewReply PRIMARY KEY ([id]),
	FOREIGN KEY ([reviewId]) REFERENCES [Review]([id])
);

-- Insert data into ReviewReply table
INSERT INTO [ReviewReply] ([reviewId], [content])
VALUES
(3, 'Thank you for your feedback!')

-- Create ProductImage table which references Product
CREATE TABLE [ProductImage] (
	[id] INT IDENTITY(1,1),
	[productId] INT,
	[url] VARCHAR(MAX) NOT NULL,
	[uploadedAt] DATETIME DEFAULT GETDATE(),
	CONSTRAINT PK_ProductImage PRIMARY KEY ([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id])
);

-- Insert data into ProductImage table
INSERT INTO [ProductImage] ([productId], [url])
VALUES
(1, 'https://i.pinimg.com/564x/0d/35/c5/0d35c5fad1af587657a2680d474ba503.jpg'),
(1, 'https://i.pinimg.com/564x/88/fe/6c/88fe6c2eefbc33eea6fe310c68b6c665.jpg'),
(1, 'https://i.pinimg.com/564x/c3/00/62/c300623b97640b93c40fcfad069236c3.jpg')

-- Create Cart table which references User
CREATE TABLE [Cart] (
	[id] INT IDENTITY(1,1),
	[userId] INT,
	CONSTRAINT PK_Cart PRIMARY KEY ([id]),
	FOREIGN KEY ([userId]) REFERENCES [User]([id])
);

-- Create CartItem table which references Cart and Product
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

-- Create PaymentType table
CREATE TABLE [PaymentType] (
	[id] INT IDENTITY(1,1),
	[paymentName] NVARCHAR(100),
	CONSTRAINT PK_PaymentType PRIMARY KEY ([id])
);

-- Insert data into PaymentType table
INSERT INTO PaymentType(paymentName) 
VALUES 
	('COD'), 
	('QR CODE')

-- Create Order table which references User and PaymentType
CREATE TABLE [Order] (
	[id] INT IDENTITY(1,1),
	[userId] INT,
	[orderAt] DATETIME,
	[isPay] BIT DEFAULT 0,
	[paymentTypeId] INT,
	CONSTRAINT PK_Order PRIMARY KEY ([id]),
	FOREIGN KEY ([userId]) REFERENCES [User]([id]),
	FOREIGN KEY ([paymentTypeId]) REFERENCES [PaymentType]([id])
);

-- Create OrderDetail table which references Order and Product
CREATE TABLE [OrderDetail] (
	[id] INT IDENTITY(1,1),
	[productId] INT,
	[orderId] INT,
	[quantity] INT,
	[totalPrice] INT,
	[status] VARCHAR(50) DEFAULT 'CONFIRMATION_WAITING', -- 'PREPARING' 'DELIVERING' 'DELIVERED'
	[deliveryAddress] NVARCHAR(MAX),
	CONSTRAINT PK_OrderDetail PRIMARY KEY ([id]),
	FOREIGN KEY ([orderId]) REFERENCES [Order]([id]),
	FOREIGN KEY ([productId]) REFERENCES [Product]([id])
);

-- Insert data into Product table
INSERT INTO [Product] ([productName], [sellBy], [categoryId], [description], [price], [avatarUrl])
VALUES
('knife', 1, 2, 'A comfortable knife', 1000, 'https://i.pinimg.com/564x/0d/35/c5/0d35c5fad1af587657a2680d474ba503.jpg')

INSERT INTO PaymentType(paymentName) 
VALUES 
	('COD'), 
	('QR CODE')
