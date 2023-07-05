ALTER TABLE lot ADD COLUMN user_id integer;

UPDATE lot
SET user_id = u.id_user
FROM users u
WHERE lot.author = u.login;

ALTER TABLE lot DROP COLUMN author;

ALTER TABLE lot
ADD CONSTRAINT lot_user_id_fkey FOREIGN KEY (user_id)
REFERENCES users (id_user) MATCH SIMPLE
ON UPDATE NO ACTION
ON DELETE NO ACTION;