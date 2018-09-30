CREATE TABLE printer (
  code int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  model varchar(50) NOT NULL,
  color char(1)  NOT NULL,
  type varchar(10) NOT NULL,
  price double,
    UNIQUE KEY(code)
);

CREATE TABLE pc (
  code int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  model varchar(50) NOT NULL,
  speed SMALLINT NOT NULL,
    ram SMALLINT NOT NULL,
    hd REAL NOT NULL,
    cd varchar(10) NOT NULL,
  price double,
    UNIQUE KEY(code)
);

CREATE TABLE laptop (
  code int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  model varchar(50) NOT NULL,
  speed SMALLINT NOT NULL,
    ram SMALLINT NOT NULL,
    hd REAL NOT NULL,
  price double ,
     screen tinyint not null,
    UNIQUE KEY(code)
);
ALTER TABLE laptop MODIFY code int(11) AUTO_INCREMENT;

create table product (
maker varchar(10) not null,
    model varchar(50)not null primary key,
    type varchar(50) not null,
    UNIQUE KEY(model)
); 
ALTER TABLE printer ADD FOREIGN KEY (model) REFERENCES product(model);
ALTER TABLE laptop ADD FOREIGN KEY (model) REFERENCES product(model);
ALTER TABLE pc ADD FOREIGN KEY (`model`) REFERENCES product(`model`);
