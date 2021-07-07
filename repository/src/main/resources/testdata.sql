INSERT INTO springdb.tag (name) values ('epam');
INSERT INTO springdb.tag (name) values ('mjc');
INSERT INTO springdb.tag (name) values ('hello');

INSERT INTO springdb.gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('first','desc', 12, 13, '2016-06-06 23:59:59','2016-06-07 23:59:59');
INSERT INTO springdb.gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('second','desc', 16, 13, '2016-06-06 23:59:59','2016-06-07 23:59:59');
INSERT INTO springdb.gift_certificate (name, description, price, duration, create_date, last_update_date) VALUES ('third','desc', 178, 13, '2016-06-06 23:59:59','2016-06-07 23:59:59');

INSERT INTO springdb.gift_certificate_has_tag VALUES (1, 1);
INSERT INTO springdb.gift_certificate_has_tag VALUES (1, 2);
INSERT INTO springdb.gift_certificate_has_tag VALUES (2, 1);