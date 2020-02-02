create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table secret(
	secret_key varchar_ignorecase(50) not null primary key
);