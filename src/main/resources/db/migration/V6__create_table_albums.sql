CREATE TABLE IF NOT EXISTS albums (
  id BIGINT PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  artist character varying COLLATE pg_catalog."default" NOT NULL,
  genre character varying COLLATE pg_catalog."default" NOT NULL,
  tracks integer NOT NULL,
  year integer NOT NULL,
  release_date timestamp(6) without time zone NOT NULL,
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE albums
  ADD CONSTRAINT fk_contents_id
  FOREIGN KEY (id)
  REFERENCES contents (id)
  ON DELETE CASCADE;