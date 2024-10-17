CREATE TABLE IF NOT EXISTS Publishers (
  id SERIAL PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  created_at timestamp(6) without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);