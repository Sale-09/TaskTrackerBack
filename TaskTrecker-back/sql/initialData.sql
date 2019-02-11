use dbTaskTracker;

-- insert authorities
insert into security_authority (name) values ('ROLE_ADMIN');
insert into security_authority (name) values ('ROLE_USER');

-- insert users (admin pass -> 12345, user pass -> abcdef)
insert into security_user (username, password, first_name, last_name, email) values 
	('admin', '$2a$04$4pqDFh9SxLAg/uSH59JCB.LwIS6QoAjM9qcE7H9e2drFuWhvTnDFi', 'Saša', 'Ivanović', 'emperor.papalin@gmail.com');
insert into security_user (username, password, first_name, last_name, email) values
	('user', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Petar', 'Petrovic', 'petar@petrovic.com');
insert into security_user (username, password, first_name, last_name, email) values
	('jovana', '$2a$04$Yr3QD6lbcemnrRNLbUMLBez2oEK15pdacIgfkvymQ9oMhqsEE56EK', 'Jovana', 'Jovanovic', 'jovana@jovanovic.com');
    
-- insert mappings between users and authorities
insert into security_user_authority (user_id, authority_id) values (1, 1);
insert into security_user_authority (user_id, authority_id) values (1, 2);
insert into security_user_authority (user_id, authority_id) values (2, 2);
insert into security_user_authority (user_id, authority_id) values (3, 2);


-- insert projects
insert into project (name) values ('Medical record management');
insert into project (name) values ('Weather app');
insert into project (name) values ('Student service management');

-- insert tasks
insert into task (date, is_completed, text, project_id, user_id) values ('2019-01-19 10:12:49', false, 'Create database', 1, 2);
insert into task (date, is_completed, text, project_id, user_id) values ('2019-01-10 09:44:09', true, 'Fix "add new" bug', 1, 2);
insert into task (date, is_completed, text, project_id, user_id) values ('2019-02-01 12:30:00', false, 'Make new icons', 1, 3);

-- insert comments
insert into comment (date, text, task_id, user_id) values ('2019-01-15 10:12:49', 'I found some bugs...', 1, 1);
insert into comment (date, text, task_id, user_id) values ('2019-01-15 12:12:12', 'I ll take a loog as soon as possible', 1, 2);
