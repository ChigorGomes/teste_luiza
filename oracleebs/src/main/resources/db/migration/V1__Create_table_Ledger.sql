CREATE TABLE IF NOT EXISTS tb_ledger (
     transcation_id uuid NOT NULL,
     description varchar(255) NULL,
    journal_id int8 NULL,
    CONSTRAINT tb_ledger_pkey PRIMARY KEY (transcation_id)
);
