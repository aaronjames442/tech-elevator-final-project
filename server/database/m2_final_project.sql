-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS  workouts, type_of_workout;
-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************

--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE type_of_workout (
	type_id serial,
	type_name varchar(20) NOT NULL,
	exercise_name varchar(20) NOT NULL,
    description text,
    sets int DEFAULT (1),
    reps int NOT NULL,
	CONSTRAINT PK_type_of_workout PRIMARY KEY (type_id)
);

CREATE TABLE workouts (
	workout_id serial,
	type_id int,
	user_id int,
	day varchar(9) NOT NULL,
	duration numeric(120) NOT NULL,
	CONSTRAINT PK_workout PRIMARY KEY (workout_id),
	CONSTRAINT FK_workout_link_type FOREIGN KEY (type_id) REFERENCES type_of_workout(type_id),
	CONSTRAINT FK_workout_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);
-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

INSERT INTO type_of_workout (type_name, exercise_name, description, sets, reps) VALUES ('Chest', 'Bench press', 'Lay flat on bench', 4, 8),
('Back', 'Lat pull down', null, 4, 8), ('Shoulders', 'Side lateral raises', 'have 15lb dumbbells at your side, raise them upwards', 4, 8),
('Chest', 'Cables flys', 'Pull cables in front of you', 4, 8);
INSERT INTO workouts (type_id, user_id, day, duration) VALUES (1, 1, 'Monday', 30), (2, 1, 'Tuesday', 45), (3, 1, 'Wednesday', 45),
(4, 1, 'Thursday', 45), (3, 1, 'Friday', 30);

COMMIT TRANSACTION;

