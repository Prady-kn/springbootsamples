CREATE SCHEMA IF NOT EXISTS sca;
CREATE SCHEMA IF NOT EXISTS scb;
CREATE SCHEMA IF NOT EXISTS scbtest;

create table sca.emptab (id integer not null, name varchar(255), primary key (id));
create table scb.emptab (id integer not null, name varchar(255), primary key (id));
create table scbtest.emptab (id integer not null, name varchar(255), primary key (id));