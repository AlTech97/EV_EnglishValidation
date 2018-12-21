CREATE TABLE USER (
EMAIL varchar(50) not null,
NAME varchar(50) not null,
SURNAME varchar(50) not null,
SEX char not null,
PASSWORD varchar(50) not null,
USER_TYPE tinyint(1) not null,
primary key (EMAIL)
);

CREATE TABLE SYSTEM_ATTRIBUTE (
SLUG varchar(20) not null, 
VALUE varchar(50) not null, 
FK_USER varchar(50) not null,
primary key (SLUG),
foreign key (FK_USER) references USER(EMAIL)
);


CREATE TABLE REQUEST (
ID_REQUEST int(20) not null,
LEVEL varchar(7) not null,
RELEASE_DATE date not null, 
EXPIRY_DATE date not null, 
YEAR year not null, 
REQUESTED_CFU tinyint(2) not null, 
SERIAL int(10) not null, 
VALIDATED_CFU tinyint(2) not null, 
FK_USER varchar(50) not null,
FK_CERTIFIER int(20) not null, 
FK_STATE int(20) not null, 
primary key(ID_REQUEST)
);

CREATE TABLE ATTACHED (
ID_ATTACHED int(20) not null,
FILENAME varchar(50) not null,
FK_REQUEST int(20) not null,
FK_USER varchar(50) not null,
primary key(ID_ATTACHED)
);


CREATE TABLE ENTE (
ID_ENTE int(20) not null,
EMAIL varchar(50) not null,
NAME varchar(50) not null,
SITE varchar(50) not null,
primary key (ID_ENTE)
);

CREATE TABLE STATE (
ID_STATE int(20) not null, 
DESCRIPTION varchar(50) not null,
primary key (ID_STATE)
);
