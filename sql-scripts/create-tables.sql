drop table if exists quiz_taker cascade;
drop table if exists question cascade;
drop table if exists admin cascade;

create table quiz_taker(
	id serial primary key,
	username varchar(50),
	score int,
	date_registered date
);

create table question(
	id serial primary key,
	question_text varchar(255),
	option_0 varchar(255),
	option_1 varchar(255),
	option_2 varchar(255),
	option_3 varchar(255),
	correct_option_index int
);

create table admin(
	id serial primary key,
	email varchar(255),
	username varchar(255),
	password varchar(255)
);