CREATE DATABASE MEETAPP
USE MEETAPP;

CREATE TABLE Location(
	IDLocation int IDENTITY(1,1) not null PRIMARY KEY,
	Latitude float not null,
	Longitude float not null
)

-- MeetApp User
CREATE TABLE MA_User (
	IDUser int IDENTITY(1,1) not null PRIMARY KEY,
	Location varchar(50),
	Name varchar(50),
	LastName varchar(50),
	Birthdate date,
	Gender	varchar(1), --"M"|"F"
	FacebookID	varchar(50),
	IsActive varchar(1),
	MaxInteractionsFriends int, -- TO FILL IN PROCEDURE
	MaxInteractionsPlaces int -- TO FILL IN PROCEDURE
);

CREATE TABLE Category (
	IDCategory int not null PRIMARY KEY,
	Name varchar(50)
)

CREATE TABLE Place (
	IDPlace int IDENTITY(1,1) not null PRIMARY KEY,
	IDLocation int FOREIGN KEY REFERENCES Location(IDLocation),
	IDCategory int FOREIGN KEY REFERENCES Category(IDCategory),
	Name	varchar(50),
	Description varchar(500)
)

CREATE TABLE Event (
	IDEvent int IDENTITY(1,1) not null PRIMARY KEY, 
	IDPlace int FOREIGN KEY REFERENCES Place(IDPlace),
	IDUser int FOREIGN KEY REFERENCES MA_User(IDUser),
	Name varchar(50),
	Description varchar(500),
	DateTime datetime,
	Visibility varchar(50)
)

CREATE TABLE Comment (
	IDComment int IDENTITY(1,1) not null PRIMARY KEY,
	IDEvent int FOREIGN KEY REFERENCES Event(IDEvent),
	IDUserCreator int FOREIGN KEY REFERENCES MA_User(IDUser),
	CreationDate datetime,
	CommentText varchar(240)
)

CREATE TABLE MA_User_Event (
	PRIMARY KEY CLUSTERED (IDEvent, IDUser),
	IDEvent int not null FOREIGN KEY REFERENCES Event(IDEvent),
	IDUser int not null FOREIGN KEY REFERENCES MA_User(IDUser),
	Attendance	bit
)

CREATE TABLE MA_User_Place (
	PRIMARY KEY CLUSTERED (IDPlace, IDUser),
	IDPlace int not null FOREIGN KEY REFERENCES Place(IDPlace),
	IDUser int not null FOREIGN KEY REFERENCES MA_User(IDuser),
	Interactions int												-- calculate by query
)

CREATE TABLE MA_Users_Relation (
	PRIMARY KEY CLUSTERED (IDUser_1, IDUser_2),
	IDUser_1 int not null FOREIGN KEY REFERENCES MA_User(IDUser),
	IDUser_2 int not null FOREIGN KEY REFERENCES MA_User(IDUser),
	Interactions int												-- calculate by query
)

CREATE TABLE Recommendation (
	PRIMARY KEY CLUSTERED (IDEvent, IDUser),
	IDEvent int not null FOREIGN KEY REFERENCES Event(IDEvent),
	IDUser int not null FOREIGN KEY REFERENCES MA_User(IDuser),
	Weight float(24)												-- calculate by query-- if recommendation doesn't exist Weight = 0
)
																	