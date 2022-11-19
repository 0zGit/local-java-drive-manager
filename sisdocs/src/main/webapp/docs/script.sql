CREATE database dbsisdocs CHARACTER SET utf8 COLLATE utf8_general_ci;

use dbsisdocs;

CREATE TABLE pasta(
	id int auto_increment NOT NULL PRIMARY KEY,
	path varchar(100)
);

CREATE TABLE arquivo(
	id int auto_increment NOT NULL PRIMARY KEY,
	name varchar(100),
	pathid int,
	FOREIGN KEY (pathid) REFERENCES pasta(id)
);


create table cliente(
	id int auto_increment primary key,
	nome varchar(100),
	login varchar(100),
	senha varchar(100),
	FOREIGN KEY (pathid) REFERENCES pasta(id)
);