create table test (
	id int ,
    name varchar(50)
);
show global variables like 'local_infile';
set global local_infile=true;
-- Kết nối DB support import text
-- mysql --local_infile=1 -u root -p routermanager; 

LOAD DATA LOCAL INFILE 'D:\test.txt' INTO TABLE test FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\r\n' IGNORE 1 ROWS;