CREATE DATABASE /*IF NOT EXISTS*/ demomotto DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE demomotto;
CREATE TABLE motto (
  motto_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  content VARCHAR(255),
  author VARCHAR(255),
  rating BIGINT,
  created TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
insert into motto (content, author) values ('I consider those are rich who are doing something they feel worthwhile and which they enjoy doing.', 'Eleanor Roosevelt');
insert into motto (content, author) values ('With slight efforts, how can we obtain great results? It is foolish even to desire it.', 'Euripides');
insert into motto (content, author) values ('Education is the most powerful weapon which you can use to change the world.', 'Nelson Mandela');
insert into motto (content, author) values ('It''s good to be middle-aged, things don''t matter so much, you don''t take it so hard when things happen to you that you don''t like.', 'Eleanor Roosevelt');
insert into motto (content, author) values ('Never give up on yourself. Then you will never give up on others.', 'Pema Chodron');
insert into motto (content, author) values ('I love you. Not only for what you are but for what I am when I am with you.', 'Roy Croft');
-- more mottos: see e.g. http://www.wisdomquotes.com/

