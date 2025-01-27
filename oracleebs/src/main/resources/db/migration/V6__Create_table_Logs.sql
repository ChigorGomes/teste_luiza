CREATE TABLE IF NOT EXISTS tb_log (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	"method" varchar(255) NULL,
	"path" varchar(255) NULL,
	payload text NULL,
	status int4 NULL,
	"timestamp" timestamp(6) NULL,
	CONSTRAINT tb_log_pkey PRIMARY KEY (id)
);