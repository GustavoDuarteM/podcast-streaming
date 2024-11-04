CREATE TABLE IF NOT EXISTS medias(
  id SERIAL PRIMARY KEY,
  uuid character varying COLLATE pg_catalog."default" NOT NULL,
  file_name character varying COLLATE pg_catalog."default" NOT NULL, 
  file_url character varying COLLATE pg_catalog."default" NOT NULL,
  file_key character varying COLLATE pg_catalog."default" NOT NULL,
  file_extension character varying COLLATE pg_catalog."default" NOT NULL,
  duration_time_seconds bigint NOT NULL,
  content_id bigint NOT NULL
);

ALTER TABLE medias
  ADD CONSTRAINT fk_content_id
  FOREIGN KEY (content_id)
  REFERENCES contents (id);