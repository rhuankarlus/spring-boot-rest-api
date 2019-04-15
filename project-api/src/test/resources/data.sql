-- INSERT INTO audit (id, url, content, type, date_time) VALUES (1, '/path1/path2/path3', 'some content 1', 1, 1554926483000);
-- INSERT INTO audit (id, url, content, type, date_time) VALUES (2, '/path1/path4/path5', 'some content 2', 1, 1555012883000);
-- INSERT INTO audit (id, url, content, type, date_time) VALUES (3, '/path1/path2/path3', 'some content 3', 2, 1555099283000);
-- INSERT INTO audit (id, url, content, type, date_time) VALUES (4, '/path6/path7', 'some content 4', 2, 1555358483000);
-- INSERT INTO audit (id, url, content, type, date_time) VALUES (5, '/path8', 'some content 5', 2, 1555358483000);
-- INSERT INTO audit (id, url, content, type, date_time) VALUES (6, '/', 'some content 6', 1, 1555358483000);
INSERT INTO audit (url, content, type, date_time) VALUES ('/', 'some content 6', 1, TIMESTAMP '2009-01-01 00:00:00');
