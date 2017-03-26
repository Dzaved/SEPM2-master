CREATE TABLE Box(id INTEGER auto_increment PRIMARY KEY,price BIGINT,boxSize BIGINT,food_quantity INTEGER,has_windows BOOLEAN,is_outside BOOLEAN, image VARCHAR(255), deleted BOOLEAN);

CREATE TABLE Reservierung(id INTEGER auto_increment PRIMARY KEY, nameClient VARCHAR(255), nameHorse VARCHAR(255), beginDate DATE, endDate DATE, deleted BOOLEAN);

CREATE TABLE BoxHorse(boxID INTEGER, resID INTEGER, horseName VARCHAR(255), price BIGINT, FOREIGN KEY(boxID) REFERENCES Box(id), FOREIGN KEY(resID) REFERENCES Reservierung (id));