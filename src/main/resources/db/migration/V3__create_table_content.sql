CREATE TABLE IF NOT EXISTS contents (
  id SERIAL PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  title character varying COLLATE pg_catalog."default" NOT NULL,
  description character varying COLLATE pg_catalog."default" NOT NULL,
  stream_channel_id integer NOT NULL,
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE contents
  ADD CONSTRAINT fk_stream_channel_id
  FOREIGN KEY (stream_channel_id)
  REFERENCES stream_channels (id)
  ON DELETE CASCADE;