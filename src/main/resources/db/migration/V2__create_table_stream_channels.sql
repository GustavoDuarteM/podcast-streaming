CREATE TABLE IF NOT EXISTS stream_channels (
  id SERIAL PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  name character varying COLLATE pg_catalog."default" NOT NULL,
  description character varying COLLATE pg_catalog."default" NOT NULL,
  publisher_id integer NOT NULL,
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE stream_channels
  ADD CONSTRAINT fk_publisher_id
  FOREIGN KEY (publisher_id)
  REFERENCES publishers (id);

