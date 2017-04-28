# noinspection SqlNoDataSourceInspectionForFile

-- Insert role
insert into role (name) values ('ROLE_USER');

-- Insert two users (passwords are both 'password')
insert into user (username,enabled,password,role_id) values ('user',true,'password',1);
insert into user (username,enabled,password,role_id) values ('user2',true,'password',1);