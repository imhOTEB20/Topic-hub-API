ALTER TABLE topics
ADD COLUMN creator_user BIGINT;

ALTER TABLE topics
ADD CONSTRAINT fk_topics_creator_user
FOREIGN KEY (creator_user) REFERENCES
users(user_id);