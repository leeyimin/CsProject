create database JDASdb;

use JDASdb

CREATE TABLE publication(
title varchar(100) not null,
descript varchar(500),
year integer,
primary key(title)
);

/*CREATE TABLE records(
	asset_id integer,
	award varchar(20),
	category varchar(100) not null,
	/*checked_out_time ,
	created_by ,
	desc1 varchar(500),
	desc2 varchar(500),
	id integer not null,	
	score varchar(20),
	/*state ,
	title varchar(100),
	/*userid ,
	year integer,
	primary key(id)
);*/

CREATE TABLE records (
  id int(11) unsigned NOT NULL,
  asset_id int(10) unsigned DEFAULT '0',
  state tinyint(1),
  checked_out_time datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  created_by int(11) NOT NULL,
  category int(11) NOT NULL,
  title varchar(500),
  desc1 text NOT NULL,
  desc2 text,
  award varchar(50),
  year year(4) NOT NULL,
  score varchar(255),
  userid varchar(255) NOT NULL,
  PRIMARY KEY (id)
) /*ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=24*/ ;

