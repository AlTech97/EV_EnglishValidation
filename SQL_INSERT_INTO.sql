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
VALUES('request-partially-completed', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-received', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-secretary', '3','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-working-admin', '4','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-accepted', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-refused', '6','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-number-max-upload', '2','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-allowed-extension-upload', '.pdf','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-matriculation-year-range', '5','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-min-cfu', '1','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-max-cfu', '12','fferrucci@unisa.it');
INSERT INTO SYSTEM_ATTRIBUTE
VALUES('request-upload-path', 'E:\\Progetto-IS\\uploads\\','fferrucci@unisa.it');

INSERT INTO ENTE
VALUES ('1', '','Cambridge Assessment English','', 1);
INSERT INTO ENTE
VALUES ('2', '','City and Guilds (Pitman)','', 1);
INSERT INTO ENTE
VALUES ('3', '','Edexcel /Pearson Ltd','', 1);
INSERT INTO ENTE
VALUES ('4', '','Educational Testing Service (ETS)','', 1);
INSERT INTO ENTE
VALUES ('5', '','English Speaking Board (ESB)','', 1);
INSERT INTO ENTE
VALUES ('6', '','International English Language Testing System (IELTS)','', 1);
INSERT INTO ENTE
VALUES ('7', '','Pearson - LCCI','', 1);
INSERT INTO ENTE
VALUES ('8', '','Pearson - EDI','', 1);
INSERT INTO ENTE
VALUES ('9', '','Trinity College London (TCL)','', 1);
INSERT INTO ENTE
VALUES ('10', '','Department of English, Faculty of Arts - University of Malta','', 1);
INSERT INTO ENTE
VALUES ('11', '','NQAI - ACELS','', 1);
INSERT INTO ENTE
VALUES ('12', '','Ascentis','', 1);
INSERT INTO ENTE
VALUES ('13', '','AIM Awards','', 1);
INSERT INTO ENTE
VALUES ('14', '','Learning Resource Network (LRN)','', 1);
INSERT INTO ENTE
VALUES ('15', '','British Institutes','', 1);
INSERT INTO ENTE
VALUES ('16', '','Gatehouse Awards Ltd','', 1);
INSERT INTO ENTE
VALUES ('17', '','LanguageCert','', 1);


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
