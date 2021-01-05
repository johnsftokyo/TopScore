CREATE DATABASE IF NOT EXISTS test;
USE test;

CREATE TABLE IF NOT EXISTS score (
id BIGINT NOT NULL AUTO_INCREMENT,
created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
player_name CHAR(20) NOT NULL,
score BIGINT NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO score (created_date, player_name, score) VALUES('2020-01-04 01:51:37', 'test_player', 1);
INSERT INTO score (created_date, player_name, score) VALUES('2020-01-07 05:26:35', 'test_player', 2);
INSERT INTO score (created_date, player_name, score) VALUES('2020-02-13 11:10:06', 'test_player', 3);
INSERT INTO score (created_date, player_name, score) VALUES('2020-02-19 16:06:49', 'test_player', 4);
INSERT INTO score (created_date, player_name, score) VALUES('2020-02-23 20:35:22', 'test_player', 5);

INSERT INTO score (created_date, player_name, score) VALUES('2020-03-04 01:51:37', 'test_player', 6);
INSERT INTO score (created_date, player_name, score) VALUES('2020-03-07 05:26:35', 'test_player', 7);
INSERT INTO score (created_date, player_name, score) VALUES('2020-04-13 11:10:06', 'test_player', 8);
INSERT INTO score (created_date, player_name, score) VALUES('2020-04-19 16:06:49', 'test_player', 9);
INSERT INTO score (created_date, player_name, score) VALUES('2020-04-23 20:35:22', 'test_player', 10);

INSERT INTO score (created_date, player_name, score) VALUES('2020-05-04 01:51:37', 'test_bill', 11);
INSERT INTO score (created_date, player_name, score) VALUES('2020-05-07 05:26:35', 'test_bill', 12);
INSERT INTO score (created_date, player_name, score) VALUES('2020-06-13 11:10:06', 'test_bill', 13);
INSERT INTO score (created_date, player_name, score) VALUES('2020-06-19 16:06:49', 'test_bill', 14);
INSERT INTO score (created_date, player_name, score) VALUES('2020-06-23 20:35:22', 'test_bill', 15);

INSERT INTO score (created_date, player_name, score) VALUES('2020-07-04 01:51:37', 'test_jane', 16);
INSERT INTO score (created_date, player_name, score) VALUES('2020-07-07 05:26:35', 'test_jane', 17);
INSERT INTO score (created_date, player_name, score) VALUES('2020-08-13 11:10:06', 'test_jane', 18);
INSERT INTO score (created_date, player_name, score) VALUES('2020-08-19 16:06:49', 'test_jane', 19);
INSERT INTO score (created_date, player_name, score) VALUES('2020-08-23 20:35:22', 'test_jane', 20);

INSERT INTO score (created_date, player_name, score) VALUES('2020-09-04 01:51:37', 'test_player', 21);
INSERT INTO score (created_date, player_name, score) VALUES('2020-09-07 05:26:35', 'test_player', 22);
INSERT INTO score (created_date, player_name, score) VALUES('2020-10-13 11:10:06', 'test_player', 23);
INSERT INTO score (created_date, player_name, score) VALUES('2020-10-19 16:06:49', 'test_player', 24);
INSERT INTO score (created_date, player_name, score) VALUES('2020-10-23 20:35:22', 'test_player', 25);

INSERT INTO score (created_date, player_name, score) VALUES('2020-11-04 01:51:37', 'test_tom', 26);
INSERT INTO score (created_date, player_name, score) VALUES('2020-11-07 05:26:35', 'test_tom', 27);
INSERT INTO score (created_date, player_name, score) VALUES('2020-12-13 11:10:06', 'test_tom', 28);
INSERT INTO score (created_date, player_name, score) VALUES('2020-12-19 16:06:49', 'test_tom', 29);
INSERT INTO score (created_date, player_name, score) VALUES('2020-12-23 20:35:22', 'test_tom', 30);

INSERT INTO score (created_date, player_name, score) VALUES('2019-01-04 01:51:37', 'test_player', 1);
INSERT INTO score (created_date, player_name, score) VALUES('2019-01-07 05:26:35', 'test_player', 2);
INSERT INTO score (created_date, player_name, score) VALUES('2019-02-13 11:10:06', 'test_player', 3);
INSERT INTO score (created_date, player_name, score) VALUES('2019-02-19 16:06:49', 'test_player', 4);
INSERT INTO score (created_date, player_name, score) VALUES('2019-02-23 20:35:22', 'test_player', 5);

INSERT INTO score (created_date, player_name, score) VALUES('2019-03-04 01:51:37', 'test_player', 6);
INSERT INTO score (created_date, player_name, score) VALUES('2019-03-07 05:26:35', 'test_player', 7);
INSERT INTO score (created_date, player_name, score) VALUES('2019-04-13 11:10:06', 'test_player', 8);
INSERT INTO score (created_date, player_name, score) VALUES('2019-04-19 16:06:49', 'test_player', 9);
INSERT INTO score (created_date, player_name, score) VALUES('2019-04-23 20:35:22', 'test_player', 10);

INSERT INTO score (created_date, player_name, score) VALUES('2019-05-04 01:51:37', 'test_player', 11);
INSERT INTO score (created_date, player_name, score) VALUES('2019-05-07 05:26:35', 'test_player', 12);
INSERT INTO score (created_date, player_name, score) VALUES('2019-06-13 11:10:06', 'test_player', 13);
INSERT INTO score (created_date, player_name, score) VALUES('2019-06-19 16:06:49', 'test_player', 14);
INSERT INTO score (created_date, player_name, score) VALUES('2019-06-23 20:35:22', 'test_player', 15);

INSERT INTO score (created_date, player_name, score) VALUES('2019-07-04 01:51:37', 'test_jim', 16);
INSERT INTO score (created_date, player_name, score) VALUES('2019-07-07 05:26:35', 'test_jim', 17);
INSERT INTO score (created_date, player_name, score) VALUES('2019-08-13 11:10:06', 'test_jim', 18);
INSERT INTO score (created_date, player_name, score) VALUES('2019-08-19 16:06:49', 'test_jim', 19);
INSERT INTO score (created_date, player_name, score) VALUES('2019-08-23 20:35:22', 'test_jim', 20);

INSERT INTO score (created_date, player_name, score) VALUES('2019-09-04 01:51:37', 'test_player', 21);
INSERT INTO score (created_date, player_name, score) VALUES('2019-09-07 05:26:35', 'test_player', 22);
INSERT INTO score (created_date, player_name, score) VALUES('2019-10-13 11:10:06', 'test_player', 23);
INSERT INTO score (created_date, player_name, score) VALUES('2019-10-19 16:06:49', 'test_player', 24);
INSERT INTO score (created_date, player_name, score) VALUES('2019-10-23 20:35:22', 'test_player', 25);

INSERT INTO score (created_date, player_name, score) VALUES('2019-11-04 01:51:37', 'test_player', 26);
INSERT INTO score (created_date, player_name, score) VALUES('2019-11-07 05:26:35', 'test_player', 27);
INSERT INTO score (created_date, player_name, score) VALUES('2019-12-13 11:10:06', 'test_player', 28);
INSERT INTO score (created_date, player_name, score) VALUES('2019-12-19 16:06:49', 'test_player', 29);
INSERT INTO score (created_date, player_name, score) VALUES('2019-12-23 20:35:22', 'test_player', 30);