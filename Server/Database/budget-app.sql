DROP TABLE IF EXISTS "transaction" CASCADE;
DROP TABLE IF EXISTS "category" CASCADE;
DROP TABLE IF EXISTS "income" CASCADE;
DROP TABLE IF EXISTS "group" CASCADE;

CREATE TABLE "group" (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(250) NOT NULL
);

CREATE TABLE "category" (
	"id" SERIAL PRIMARY KEY,
    "group_id" INTEGER REFERENCES "group" ("id") ON DELETE CASCADE,
	"name" VARCHAR(250) NOT NULL,
	"amount_assigned" DECIMAL(8,2) NOT NULL
);

CREATE TABLE "income" (
	"id" SERIAL PRIMARY KEY,
	"name" varchar(250) NOT NULL,
	"amount" DECIMAL(8,2),
	"is_monthly" BOOLEAN NOT NULL,
	"times_per_month" INTEGER,
	"weeks_per_period" INTEGER
);

CREATE TABLE "transaction" (
	"id" SERIAL PRIMARY KEY,
	"category_id" INTEGER NOT NULL REFERENCES "category" ("id") ON DELETE CASCADE,
	"amount" DECIMAL(8,2) NOT NULL,
	"description" varchar(250) NOT NULL,
	"date" TIMESTAMP DEFAULT current_timestamp
);

insert into "group" ("name") VALUES
    ('Bills'),
    ('Needs'),
    ('Wants');

insert into category ("name", amount_assigned, group_id) VALUES
	('Internet', 99.00, 1),
	('Electricity', 150.00, 1),
	('Water', 50.00, 1),
	('Groceries', 300.00, 2),
	('Gas', 50.00, 2),
	('Rent', 1500.00, 1),
	('Car Insurance', 100.00, 1),
	('Phone', 50.00, 1),
	('Subscriptions', 50.00, 3);

insert into income ("name", amount, is_monthly, times_per_month, weeks_per_period) VALUES
	('Beth', 1200.00, true, 2, null),
	('Beau', 1000.00, false, null, 2),
	('Freelance', 100.00, false, 4, 1);

INSERT INTO "transaction" ("amount", "description", "category_id") VALUES
    (99.00, 'Food Lion', 4),
    (150.00, 'Duke Energy', 2),
    (30.00, 'BP', 5);