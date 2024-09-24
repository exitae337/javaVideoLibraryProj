CREATE DATABASE DBDatabaseJava;
USE DBDatabaseJava;

CREATE TABLE user_role(
	role_id int identity(1,1) primary key not null,
	role_name varchar(20) not null
);

CREATE TABLE users(
	user_id int identity(1,1) primary key not null,
	user_fullname varchar(100) not null,
	user_email varchar(100) not null unique,
	user_password varchar(20) not null,
	user_role_id int not null,
	foreign key (user_role_id) references user_role(role_id)
);

CREATE TABLE genre_film(
	genre_id int identity(1,1) primary key not null,
	genre_name varchar(30) not null unique
);

CREATE TABLE country_film(
	country_id int identity(1,1) primary key not null,
	country_name varchar(50) not null unique
);

CREATE TABLE actors_and_directors(
    person_id int identity(1,1) primary key not null,
    person_fullname varchar(100) not null,
    person_role varchar(10) not null
);

CREATE TABLE films(
    film_id int identity(1,1) primary key not null,
    film_name varchar(100) not null unique,
    film_director int not null,
    foreign key (film_director) references actors_and_directors(person_id),
    film_start_date date not null,
    film_country int not null,
    foreign key (film_country) references country_film(country_id),
    film_genre int not null,
    foreign key (film_genre) references genre_film(genre_id)
);

CREATE TABLE film_to_actor(
    film_to_actor_id int not null,
    actor_to_film_id int not null,
    primary key (film_to_actor_id, actor_to_film_id),
    foreign key (film_to_actor_id) references films(film_id),
    foreign key (actor_to_film_id) references actors_and_directors(person_id)
);

CREATE TABLE user_reviews(
	review_id int identity(1,1) primary key not null,
	review_film_id int not null,
	foreign key (review_film_id) references films(film_id),
	review_user_id int not null,
	foreign key (review_user_id) references users(user_id),
	review_text text,
	review_mark int not null,
    review_created_date datetime default CURRENT_TIMESTAMP not null
);

ALTER TABLE actors_and_directors
ADD CONSTRAINT ck_Enum_Role CHECK (person_role IN ('credit', 'actor'))


ALTER TABLE user_reviews
ADD CONSTRAINT ck_Mark_Between CHECK (review_mark >= 1 AND review_mark <= 5)