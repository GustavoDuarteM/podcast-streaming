CREATE TABLE IF NOT EXISTS stream_channels (
  id SERIAL PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default",
  name character varying COLLATE pg_catalog."default",
  description character varying COLLATE pg_catalog."default",
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);