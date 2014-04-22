create database JDASdb;

use JDASdb

CREATE TABLE publication(
pubID integer not null,
title varchar(100),
desc varchar(500),
year integer,
primary key(pubID));
