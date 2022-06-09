--liquibase formatted sql

--changeset litvina.e:init-db
create table if not exists entry_group
(
    id
    serial
    primary
    key,
    title
    varchar
);

create table if not exists entry
(
    id
    serial
    primary
    key,
    title
    varchar,
    entry_group_id
    int
    references
    entry_group
(
    id
) on delete cascade
    );

insert into entry_group (title)
values ('Group 1'),
       ('Group 2'),
       ('Group 3');

insert into entry (title, entry_group_id)
values ('Entry 1', 1),
       ('Entry 2', 1),
       ('Entry 1', 2),
       ('Entry 2', 2);