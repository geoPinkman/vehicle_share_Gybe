create table users_auth (
    id serial not null constraint usersAuthPkey primary key,
    email varchar(512),
    system_code varchar(16),
    created_at timestamp default now(),
    code_status varchar(32)
);
