-- AUDIT
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/path1/path2/path3', 'some content 1', 1, TIMESTAMP '2018-12-23 15:46:13');
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/path1/path4/path5', 'some content 2', 1, TIMESTAMP '2019-01-02 09:30:13');
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/path1/path2/path3', 'some content 3', 2, TIMESTAMP '2019-01-02 10:15:55');
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/path6/path7', 'some content 4', 2, TIMESTAMP '2019-02-15 14:00:55');
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/path8', 'some content 5', 2, TIMESTAMP '2019-02-22 21:02:47');
INSERT INTO audit (url, content, TYPE, date_time) VALUES ('/', 'some content 6', 1, TIMESTAMP '2019-04-18 05:52:21');