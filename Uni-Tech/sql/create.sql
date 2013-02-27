DROP DATABASE unitech;

DROP USER 'unitech'@'localhost';
DROP USER 'unitech'@'%';

commit;

CREATE DATABASE unitech;

CREATE USER 'unitech'@'localhost' IDENTIFIED BY 'unitech@123';
CREATE USER 'unitech'@'%' IDENTIFIED BY 'unitech@123';

GRANT CREATE, ALTER, DROP, UPDATE, SELECT, INSERT, DELETE ON unitech.* TO unitech@'localhost' IDENTIFIED BY 'unitech@123';
GRANT CREATE, ALTER, DROP, UPDATE, SELECT, INSERT, DELETE ON unitech.* TO unitech@'%' IDENTIFIED BY 'unitech@123';

commit;