USE englishvalidation;

INSERT INTO user
VALUES('prova@unisa.it','Paolo','Benigno','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','0');

INSERT INTO user
VALUES('g.prova@studenti.unisa.it','Giacomo','Lorenzin','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','0');

INSERT INTO user
VALUES('m.prova@studenti.unisa.it','Michele','Paolella','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','0');

INSERT INTO user
VALUES('g.prova2@studenti.unisa.it','Giuseppina','Cirina','F','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','0');

INSERT INTO user
VALUES('a.prova@studenti.unisa.it','Andrea','Iannaccone','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','0');

INSERT INTO user
VALUES('segreteria@unisa.it','Segreteria','Studenti','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','1'); 

INSERT INTO user
VALUES('fferrucci@unisa.it','Filomena','Ferrucci','M','4bb47fd2a6c598d2a52ef7de3473fd3ea8401a9b','2');

INSERT INTO STATE 
VALUES (1,'Parzialmente Completata');
INSERT INTO STATE 
VALUES (2,'Ricevuta');
INSERT INTO STATE 
VALUES (3,'In elaborazione dalla Segreteria');
INSERT INTO STATE 
VALUES (4,'In elaborazione dal Consiglio Didattico');
INSERT INTO STATE 
VALUES (5,'Conclusa e Accettata');
INSERT INTO STATE 
VALUES (6,'Conclusa e Rifiutata');

INSERT INTO SYSTEM_ATTRIBUTE
VALUES('partially-completed', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('received', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('working-secretary', '3','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('working-admin', '4','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('accepted', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('refused', '6','fferrucci@unisa.it');


INSERT INTO ENTE
VALUES ('1', 'cambridge@gmail.com','Cambridge','https://www.cambridgeenglish.org/it/');

INSERT INTO ENTE
VALUES ('2', 'trinity@gmail.com','Trinity','https://www.trinitycollege.it/');

INSERT INTO REQUEST
VALUES ('1','B.6.56546', 'A1','2017/05/25','2018/05/25','2018','3','0512103579','9','prova@unisa.it','1','2');

INSERT INTO REQUEST
VALUES ('2','A00000052', 'GRADE 1','2014/04/01','2019/04/01','2018','3','0512105846','6','g.prova@studenti.unisa.it','1', '2');

INSERT INTO REQUEST
VALUES ('3','A00000021', 'C2','2018/07/14','2023/07/14','2018','3','0512106974','3','m.prova@studenti.unisa.it','1','1');

INSERT INTO ATTACHED
VALUES ('1','certificato.pdf','1','prova@unisa.it');

INSERT INTO ATTACHED
VALUES ('2','certificato.pdf','1','prova@unisa.it');

INSERT INTO ATTACHED
VALUES ('3','certificato.pdf','2','g.prova@studenti.unisa.it');

INSERT INTO ATTACHED
VALUES ('4','certificato.pdf','2','g.prova@studenti.unisa.it');
