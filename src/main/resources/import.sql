# noinspection SqlNoDataSourceInspectionForFile

-- Insert role
insert into Role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into User (username,enabled,password,role_id) values ('user',true,'password',1);
insert into User (username,enabled,password,role_id) values ('user2',true,'password',1);