CREATE TABLE PUNCHLIST.ACCOUNT (
	account_id serial PRIMARY KEY,
	email VARCHAR ( 100 ) UNIQUE NOT NULL,
	first_name VARCHAR ( 50 ) NOT NULL,
	last_name VARCHAR ( 50 ) NOT NULL,
	pw_hash VARCHAR NOT NULL,
	created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
	modified TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);

CREATE TABLE PUNCHLIST.TASK (
	task_id SERIAL PRIMARY KEY,
	text VARCHAR(255) NOT NULL,
	priority integer NOT NULL,
	due TIMESTAMP WITH TIME ZONE,
	alert boolean NOT NULL DEFAULT FALSE,
	completed boolean NOT NULL,
	created TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
	modified TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
	account_id_fk integer REFERENCES punchlist.account(account_id)
);
