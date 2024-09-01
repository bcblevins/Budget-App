DROP TABLE IF EXISTS "transaction" CASCADE;
DROP TABLE IF EXISTS "category" CASCADE;
DROP TABLE IF EXISTS "income" CASCADE;

CREATE TABLE "transaction" (
	"id" SERIAL PRIMARY KEY,
	"amount" DECIMAL(8,2) NOT NULL,
	"description" varchar(250) NOT NULL,
	"date" TIMESTAMP DEFAULT current_timestamp
);

CREATE TABLE "category" (
	"id" SERIAL PRIMARY KEY,
	"name" VARCHAR(250) NOT NULL,
	"amount_assigned" DECIMAL(8,2) NOT NULL,
	"amount_spent" DECIMAL(8,2) NOT NULL
);

CREATE TABLE "income" (
	"id" SERIAL PRIMARY KEY,
	"name" varchar(250) NOT NULL,
	"amount" DECIMAL(8,2),
	"is_monthly" BOOLEAN NOT NULL,
	"times_per_month" INTEGER,
	"weeks_per_period" INTEGER
);

insert into category ("name", amount_assigned, amount_spent) VALUES
	('Internet', 99.00, 0.00),
	('Electricity', 150.00, 0.00),
	('Water', 50.00, 0.00),
	('Groceries', 300.00, 0.00),
	('Gas', 50.00, 0.00),
	('Rent', 1500.00, 0.00),
	('Car Insurance', 100.00, 0.00),
	('Phone', 50.00, 0.00),
	('Miscellaneous', 100.00, 0.00);

insert into income ("name", amount, is_monthly, times_per_month, weeks_per_period) VALUES
	('Beth', 1200.00, true, 2, null),
	('Beau', 1000.00, false, null, 2),
	('Freelance', 100.00, false, 4, 1);