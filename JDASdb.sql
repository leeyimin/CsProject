create database JDASdb;

use JDASdb

CREATE TABLE publication(
title varchar(100) not null,
desc1 varchar(500),
year integer,
primary key(title)
);



CREATE TABLE rierecords (
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
);

