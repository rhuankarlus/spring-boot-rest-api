-- relation tables
DELETE FROM role_permission; ALTER SEQUENCE id_seq_role_permission RESTART WITH 1;
DELETE FROM user_permission; ALTER SEQUENCE id_seq_user_permission RESTART WITH 1;
DELETE FROM user_role; ALTER SEQUENCE id_seq_user_role RESTART WITH 1;

-- final tables
DELETE FROM audit; ALTER SEQUENCE id_seq_audit RESTART WITH 1;
DELETE FROM permission; ALTER SEQUENCE id_seq_permission RESTART WITH 1;
DELETE FROM role; ALTER SEQUENCE id_seq_role RESTART WITH 1;
DELETE FROM user; ALTER SEQUENCE id_seq_user RESTART WITH 1;