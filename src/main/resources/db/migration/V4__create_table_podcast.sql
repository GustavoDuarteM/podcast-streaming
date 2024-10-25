DROP TABLE IF EXISTS podcasts;
CREATE TABLE IF NOT EXISTS podcasts (
  id BIGINT PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  release_date timestamp(6) without time zone NOT NULL,
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE podcasts
  ADD CONSTRAINT fk_contents_id
  FOREIGN KEY (id)
  REFERENCES contents (id)
  ON DELETE CASCADE;