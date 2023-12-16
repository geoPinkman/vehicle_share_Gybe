create table users (
    id serial not null constraint usersPkey primary key,
    uuid varchar(512) not null unique,
    email varchar(256) not null unique,
    user_password varchar(1024) not null,
    created_at timestamp with time zone default now(),
    updated_at timestamp with time zone default now()
);

create table roles(
    id serial not null constraint rolesPkey primary key,
    role_name varchar(32)
);

create table users_roles (
    id serial not null constraint usersRolesPkey primary key,
    user_id integer references users (id)
        on delete restrict
        on update restrict,
    role_id integer references roles(id)
        on delete restrict
        on update restrict
);

alter table owners
    drop column email;
alter table owners
    drop column owner_password;
alter table owners
    add column user_id integer references users(id);

