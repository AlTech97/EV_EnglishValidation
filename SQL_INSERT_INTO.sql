INSERT INTO USER
VALUES('paolo1@studenti.unisa.it','Paolo','Benigno','M','password1','0');

INSERT INTO USER
VALUES('giacomo5@studenti.unisa.it','Giacomo','Lorenzin','M','password2','0');

INSERT INTO USER
VALUES('michele8@studenti.unisa.it','Michele','Paolella','M','password3','0');

INSERT INTO USER
VALUES('Giuseppina69@studenti.unisa.it','Giuseppina','Cirina','F','password4','0');

INSERT INTO USER
VALUES('Andrea96@studenti.unisa.it','Andrea','Iannaccone','M','password5','0');

INSERT INTO USER
VALUES('Francesco123@unisa.it','Francesco','Parisi','M','password1','1'); 

INSERT INTO USER
VALUES('Marco1@unisa.it','Marco','Cortiglione','M','password10','2'); 

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('a-different-slug','https://ev.com/a-different-slug/','paolo1@studenti.unisa.it'); 

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('an-alternative-slug','https://ev.com/an-alternative-slug/','Francesco123@unisa.it'); 

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('a-strange-slug','https://ev.com/a-strange-slug/','Marco1@unisa.it');
 
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('a-good-slug','https://ev.com/a-good-slug/','Giuseppina69@studenti.unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('a-bad-slug','https://ev.com/a-bad-slug/','Andrea96@studenti.unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('an-excellent-slug','https://ev.com/an-excellente-slug/','giacomo5@studenti.unisa.it');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('a-very-bad-slug','https://ev.com/a-very-bad-slug/','michele8@studenti.unisa.it');

INSERT INTO STATE 
VALUES ('12345','IN ELABORAZIONE');

INSERT INTO STATE 
VALUES ('98765','ACCETTATO');

INSERT INTO STATE 
VALUES ('25874','RIFIUTATO');

INSERT INTO STATE 
VALUES ('91375', 'ACCETTATO');

INSERT INTO STATE
VALUES ('78956','IN ELABORAZIONE');

INSERT INTO ENTE
VALUES ('56473', 'cambridge@gmail.com','Cambridge','https://www.cambridgeenglish.org/it/');

INSERT INTO ENTE
VALUES ('78945', 'trinity@gmail.com','Trinity','https://www.trinitycollege.it/');

INSERT INTO REQUEST
VALUES ('78256','A1','2017/05/25','2018/05/25','2018','3','0512103579','9','paolo1@studenti.unisa.it','56473','12345');

INSERT INTO REQUEST
VALUES ('35698','GRADE 1','2014/04/01','2019/04/01','2018','3','0512105846','6','giacomo5@studenti.unisa.it','78945', '98765');

INSERT INTO REQUEST
VALUES ('88425','C2','2018/07/14','2023/07/14','2018','3','0512106974','3','michele8@studenti.unisa.it','56473','25874');

INSERT INTO REQUEST
VALUES ('68741','B2','2016/02/08','2021/02/08','2018','3','0512103689','3','Giuseppina69@studenti.unisa.it','78945', '91375');

INSERT INTO REQUEST
VALUES ('62369','GRADE 3','2018/03/19','2023/03/18','2018','3','0512101425','6','Andrea96@studenti.unisa.it', '56473','78956');

INSERT INTO ATTACHED
VALUES ('12345','Certificato di lingua inglese','78256','paolo1@studenti.unisa.it');

INSERT INTO ATTACHED
VALUES ('98765','Certificato di lingua inglese','35698','giacomo5@studenti.unisa.it');

INSERT INTO ATTACHED
VALUES ('56789','Certificato di lingua inglese','88425','michele8@studenti.unisa.it');

INSERT INTO ATTACHED
VALUES ('65478','Certificato di lingua inglese','68741','Giuseppina69@studenti.unisa.it');

INSERT INTO ATTACHED
VALUES ('74125','Certificato di lingua inglese','62369','Andrea96@studenti.unisa.it');
