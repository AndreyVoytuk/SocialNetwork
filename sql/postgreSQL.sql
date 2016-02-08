--create databade
CREATE DATABASE "SocialNetwork";

--
CREATE TABLE "users" (
    "user_id" serial,
    "user_name" varchar(50) NOT NULL,
    "user_password" varchar(50) NOT NULL,
    "user_email" varchar(50) NOT NULL,
    "user_status" varchar(50) NOT NULL,
    PRIMARY KEY (user_id)
);

--ALTER TABLE "users" ADD CONSTRAINT ?(name_constraint) FOREIGN KEY (NAME) REFERENCES "table" (name);

CREATE TABLE "posts" (
    "post_id" serial,
    "post_author" integer NOT NULL,
    "post_title" varchar(50) NOT NULL,
    "post_content" varchar(50) NOT NULL,
    "post_date" date NOT NULL,
    PRIMARY KEY (post_id)
);

ALTER TABLE posts ADD CONSTRAINT fk_post_author FOREIGN KEY (post_author) REFERENCES users (user_id);

CREATE TABLE "comments" (
    "comment_id" serial,
    "comment_post_id" integer NOT NULL,
    "comment_author" integer NOT NULL,
    "comment_content" varchar(50) NOT NULL,
    PRIMARY KEY (comment_id)
);

ALTER TABLE comments ADD CONSTRAINT fk_comment_post_id FOREIGN KEY (comment_post_id) REFERENCES posts (post_id);
ALTER TABLE comments ADD CONSTRAINT fk_comment_author FOREIGN KEY (comment_author) REFERENCES users (user_id);

CREATE TABLE "likes" (
    "like_id" serial,
    "like_post" integer NOT NULL,
    "like_author" integer NOT NULL,
    PRIMARY KEY (like_id)
);    

ALTER TABLE likes ADD CONSTRAINT fk_like_post FOREIGN KEY (like_post) REFERENCES posts (post_id);
ALTER TABLE likes ADD CONSTRAINT fk_like_author FOREIGN KEY (like_author) REFERENCES users (user_id);


--INSERT "users"--

INSERT INTO users (user_id, user_name, user_password, user_email, user_status) VALUES(DEFAULT, 'Bob', 'bob123', 'bob@gmail.com', 'active');
INSERT INTO users (user_id, user_name, user_password, user_email, user_status) VALUES(DEFAULT, 'Debra', 'debra123', 'debra@gmail.com', 'active');
INSERT INTO users (user_id, user_name, user_password, user_email, user_status) VALUES(DEFAULT, 'Alex', 'alex123', 'alex@gmail.com', 'active');

--INSERT "posts"--

INSERT INTO posts VALUES(DEFAULT, 2, 'First place!!!', 'PostgreSQL the best', '20.12.2016');
INSERT INTO posts VALUES(DEFAULT, 1, 'Second place!!!', 'MySQL better', '28.12.2016');
INSERT INTO posts VALUES(DEFAULT, 2, 'Third place!!!', 'MongoDB', '13.12.2016');

--INSERT "comments"--

INSERT INTO comments VALUES(DEFAULT, 2, 1, 'I know');
INSERT INTO comments VALUES(DEFAULT, 1, 1, '+1');
INSERT INTO comments VALUES(DEFAULT, 3, 2, 'ok');


--INSERT "comments"--

INSERT INTO likes VALUES(DEFAULT, 3, 2);
INSERT INTO likes VALUES(DEFAULT, 1, 2);
INSERT INTO likes VALUES(DEFAULT, 1, 0);
