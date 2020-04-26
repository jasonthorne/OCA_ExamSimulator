create database if not exists blood_red_skies_db;

use blood_red_skies_db;

/* airforces involved */
drop table if exists airforces;
CREATE TABLE airforces( 
  airforceID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (airforceID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/*
INSERT INTO airforces (name, description) VALUES ('RAF','sss');
INSERT INTO airforces (name, description) VALUES ('Soviet','ssqqsss');
INSERT INTO airforces (name, description) VALUES ('USAAF','asadade');
*/

/* planes involved */
drop table if exists planes;
CREATE TABLE planes (
  planeID int NOT NULL AUTO_INCREMENT,
  name varchar(64) DEFAULT NULL,
  description varchar(64) DEFAULT NULL,
  PRIMARY KEY (planeID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


/* years covered */
drop table if exists years;
CREATE TABLE years (
  yearID int NOT NULL AUTO_INCREMENT,
  name varchar(4) DEFAULT NULL, 
  PRIMARY KEY (yearID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO years (name) VALUES ('1940');
INSERT INTO years (name) VALUES ('1941');
INSERT INTO years (name) VALUES ('1942');
INSERT INTO years (name) VALUES ('1943');
INSERT INTO years (name) VALUES ('1944');
INSERT INTO years (name) VALUES ('1945');


/* blocks of a year (early, mid, late) */
drop table if exists blocks;
CREATE TABLE blocks (
  blockID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL, 
  PRIMARY KEY (blockID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO blocks (name) VALUES ('Early');
INSERT INTO blocks (name) VALUES ('Mid');
INSERT INTO blocks (name) VALUES ('Late');


/* status values of a plane (none, limit, auto) */
drop table if exists status;
CREATE TABLE status (
  statusID int NOT NULL AUTO_INCREMENT,
  name varchar(5) DEFAULT NULL,
  PRIMARY KEY (statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO status (name) VALUES ('None');
INSERT INTO status (name) VALUES ('Limit');
INSERT INTO status (name) VALUES ('Auto');


/* every potential status for each period of history covered (eg: early, 1940, limit) */
drop table if exists period_status;
CREATE TABLE period_status (
  period_statusID int NOT NULL AUTO_INCREMENT,
  blockID int,
  yearID int,
  statusID int,
  PRIMARY KEY (period_statusID),
  FOREIGN KEY (blockID) REFERENCES blocks(blockID),
  FOREIGN KEY (yearID) REFERENCES years(yearID),
  FOREIGN KEY (statusID) REFERENCES status(statusID)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/* works but should be without SET
INSERT INTO period_status SET 
	blockID = (SELECT blockID FROM blocks WHERE name = 'Early'),
	yearID = (SELECT yearID FROM years WHERE name = '1940'),
	statusID = (SELECT statusID FROM status WHERE name = 'None');
*/

/*
INSERT INTO period_status (blockID, yearID, statusID)
	SELECT blockID FROM blocks WHERE name = 'Early',
	yearID FROM years WHERE name = '1940',
	statusID FROM status WHERE name = 'None';
*/

/*
INSERT INTO period_status (blockID, yearID, statusID)
	SELECT blockID, yearID, statusID FROM blocks, years, status 
	WHERE blocks.name = 'Early'
	AND WHERE years.name = '1940'
	AND WHERE status.name = 'None';
*/
	

/* Early 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Late 1940 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Late'),
	(SELECT yearID FROM years WHERE years.name = '1940'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/*----------------*/

/* Early 1941 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Early'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));

/* Mid 1941 */
INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'None'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Limit'));

INSERT INTO period_status (blockID, yearID, statusID) VALUES (
	(SELECT blockID FROM blocks WHERE blocks.name = 'Mid'),
	(SELECT yearID FROM years WHERE years.name = '1941'),
	(SELECT statusID FROM status WHERE status.name = 'Auto'));





