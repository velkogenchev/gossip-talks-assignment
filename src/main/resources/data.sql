INSERT INTO USERS VALUES(1, 'velko@gmail.com', 'Velko Genchev', '$2a$10$bTtysZc9bGRvBGD8k5IXpe54pBeicV2lfD.BHhLPh4e62XDF4Rpxq', 'velaka');
INSERT INTO USERS VALUES(2, 'messi@gmail.com', 'Leo Messi', '$2a$10$bTtysZc9bGRvBGD8k5IXpe54pBeicV2lfD.BHhLPh4e62XDF4Rpxq', 'messi');
INSERT INTO USERS VALUES(3, 'ronaldinho@gmail.com', 'Ronaldinho', '$2a$10$bTtysZc9bGRvBGD8k5IXpe54pBeicV2lfD.BHhLPh4e62XDF4Rpxq', 'ronaldinho');
INSERT INTO USERS VALUES(4, 'test@gmail.com', 'Test Testov', '$2a$10$bTtysZc9bGRvBGD8k5IXpe54pBeicV2lfD.BHhLPh4e62XDF4Rpxq', 'test');
INSERT INTO FOLLOWING VALUES(1, 2);
INSERT INTO FOLLOWING VALUES(1, 3);
INSERT INTO GOSSIPS VALUES(1, CURRENT_DATE, 'Gossip from Messi', 2);
INSERT INTO GOSSIPS VALUES(2, CURRENT_DATE, 'Gossip from Ronaldinho', 3);
INSERT INTO GOSSIPS VALUES(3, CURRENT_DATE, 'Gossip from Test', 4);