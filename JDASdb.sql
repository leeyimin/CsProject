create database JDASdb;

use JDASdb

CREATE TABLE publication(
title varchar(100) not null,
desc varchar(500),
year integer,
primary key(title));
