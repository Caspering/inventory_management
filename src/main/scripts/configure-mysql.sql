docker run --name kasperin-mysql -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE kasperin_dev;
CREATE DATABASE kasperin_prod;

#Create database service accounts
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'kasperin'
CREATE USER 'kasperin_prod_user'@'localhost' IDENTIFIED BY 'guru'
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'kasperin';
CREATE USER 'kasperin_prod_user'@'%' IDENTIFIED BY 'kasperin';

#Database grants
GRANT SELECT ON kasperin_dev.* to 'dev_user'@'localhost';
GRANT INSERT ON kasperin_dev.* to 'dev_user'@'localhost';
GRANT DELETE ON kasperin_dev.* to 'dev_user'@'localhost';
GRANT UPDATE ON kasperin_dev.* to 'dev_user'@'localhost';
GRANT SELECT ON kasperin_prod.* to 'kasperin_prod_user'@'localhost';
GRANT INSERT ON kasperin_prod.* to 'kasperin_prod_user'@'localhost';
GRANT DELETE ON kasperin_prod.* to 'kasperin_prod_user'@'localhost';
GRANT UPDATE ON kasperin_prod.* to 'kasperin_prod_user'@'localhost';
GRANT SELECT ON kasperin_dev.* to 'dev_user'@'%';
GRANT INSERT ON kasperin_dev.* to 'dev_user'@'%';
GRANT DELETE ON kasperin_dev.* to 'dev_user'@'%';
GRANT UPDATE ON kasperin_dev.* to 'dev_user'@'%';
GRANT SELECT ON kasperin_prod.* to 'kasperin_prod_user'@'%';
GRANT INSERT ON kasperin_prod.* to 'kasperin_prod_user'@'%';
GRANT DELETE ON kasperin_prod.* to 'kasperin_prod_user'@'%';
GRANT UPDATE ON kasperin_prod.* to 'kasperin_prod_user'@'%';