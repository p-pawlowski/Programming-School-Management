use school;

create table user_group(id INT PRIMARY key auto_increment, name varchar(100) unique);

create table users(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100),
surname VARCHAR (100), email VARCHAR (200) Unique, password VARCHAR(100), user_group_id INT,
foreign key(user_group_id) references user_group(id) ON	DELETE	CASCADE);

create table excercise (id INT PRIMARY KEY AUTO_INCREMENT, title varchar (255),
description Varchar (500));

create table solution (id INT AUTO_INCREMENT PRIMARY KEY, created DATETIME, updated DATETIME, 
description VARCHAR(500), excercise_id INT, user_id INT,
UNIQUE (excercise_id, user_id),
FOREIGN KEY (user_id) REFERENCES users(id) ON	DELETE	CASCADE,
FOREIGN KEY (excercise_id) REFERENCES excercise(id) ON	DELETE	CASCADE);

insert into user_group(name) Values 
('WAW_JAV_S_01'),('WAW_JAV_W_01');

INSERT INTO users (name,surname,email,user_group_id,password)Values
('Piotr', 'Pawlowski', 'piotr@piotr', 1, 'pa55word'),
('Marek', 'Sosnowski', 'marek@marek', 1, 'pa55word'),
('Jan', 'Kowalski', 'jan@jan', 1, 'pa55word'),
('Wojtek', 'Kwiatkowski', 'wojtek@wojtek', 1, 'pa55word'),
('Antoni', 'Czartoryski', 'antoni@antoni', 1, 'pa55word'),
('Piotr2', 'Pawlowski2', '2piotr@piotr2', 2, 'pa55word'),
('Marek2', 'Sosnowski2', '2marek@marek2', 2, 'pa55word'),
('Jan2', 'Kowalski2', '2jan@jan2', 2, 'pa55word'),
('Wojtek2', 'Kwiatkowski2', '2wojtek@wojtek2', 2, 'pa55word'),
('Antoni2', 'Czartoryski2', '2antoni@antoni2', 2, 'pa55word');


INSERT INTO excercise(title,description) VALUES 
('zadanie 1', 'tresc zadania nr 1'),
('zadanie 2', 'tresc zadania nr 2'),
('zadanie 3', 'tresc zadania nr 3'),
('zadanie 4', 'tresc zadania nr 4'),
('zadanie 5', 'tresc zadania nr 5');

INSERT INTO solution(created, updated, description, excercise_id, user_id)
VALUES ('2001-09-22 00:00:00', '2001-09-22 00:00:00', 'rozwiazanie zadania ', '4', '9'),
('2002-09-22 00:00:00', '2002-09-22 00:00:00', 'rozwiazanie zadania ', '2', '5'),
 ('2009-09-22 00:00:00', '2009-09-22 00:00:00', 'rozwiazanie zadania ', '3', '4'),
('2008-09-22 00:00:00', '2008-09-22 00:00:00', 'rozwiazanie zadania ', '4', '3'),
('2007-09-22 00:00:00', '2007-09-22 00:00:00', 'rozwiazanie zadania ', '5', '1'),
('2006-09-22 00:00:00', '2006-09-22 00:00:00', 'rozwiazanie zadania ', '3', '2'),
 ('2005-09-22 00:00:00', '2005-09-22 00:00:00', 'rozwiazanie zadania ', '4', '8'),
 ('2004-09-22 00:00:00', '2004-09-22 00:00:00', 'rozwiazanie zadania ', '1', '2'),
 ('2003-09-22 00:00:00', '2003-09-22 00:00:00', 'rozwiazanie zadania ', '2', '1'),
('2002-09-22 00:00:00', '2002-09-22 00:00:00', 'rozwiazanie zadania ', '3', '9');
