create table owners (
    id serial not null constraint ownersPkey primary key,
    firstname varchar(64),
    lastname varchar(64),
    company_name varchar(64),
    phone_number varchar(32),
    address varchar(512),

    email varchar(256) unique not null,
    owner_password varchar(1024) not null,

    rating numeric,

    created_at timestamp with time zone,
    updated_at timestamp with time zone
);

create table owner_options (
    id serial not null constraint ownerOptionsPkey primary key,
    owner_id integer constraint ownersIdFk references owners (id),
    option_1 boolean default false,
    option_2 boolean default false,
    option_3 boolean default false
);

create table vehicle_patterns (
    id integer not null constraint vehiclePatternsPkey primary key,
    vehicle_type varchar(32) not null,
    company varchar(32) not null,
    model varchar(64) not null,
    color varchar(32) not null,
    gearbox varchar(32) not null,
    engine_value integer not null,
    year_of_issue integer not null,
    constraint allFieldsAreUnique unique (vehicle_type, company, model, color, gearbox, engine_value, year_of_issue)
);

create table vehicles (
    id serial not null constraint vehiclesPkey primary key,
    vehicle_pattern_id integer constraint vehiclePatternsIdFk references vehicle_patterns (id)
        on update restrict
        on delete restrict,
    owner_id integer constraint vehiclesOwnerIdFk references owners (id)
        on update restrict
        on delete restrict,
    uuid uuid not null constraint vehiclesUuidKey unique,

    price_per_day integer default 0 constraint pricePerDay
            check (price_per_day >= 0),
    price_per_month integer default 0 constraint pricePerMonth
            check (price_per_month >= 0),
    deposit integer default 0 constraint deposit
            check (deposit >= 0),

    description varchar(1024),
    logo varchar(64),

    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    available_now boolean default false
);

create table vehicle_documents (
    id integer not null constraint vehicleDocumentsPk primary key,
    vehicle_id integer constraint vehiclesOwnerIdFk references vehicles (id)
        on update cascade
        on delete cascade,
    document_url_1 varchar(512),
    document_url_2 varchar(512),
    document_url_3 varchar(512),

    created_at timestamp with time zone,
    updated_at timestamp with time zone
);

create table vehicle_rent_dates (
    id serial not null constraint rentDatesPkey primary key,
    vehicle_id integer constraint rentDatesVehicleIdFk references vehicles (id)
        on update cascade
        on delete cascade,
    date_from date not null,
    date_to date not null
);

create table vehicle_pictures (
    id integer not null constraint vehiclePicturesPk primary key,
    vehicle_id  integer constraint vehiclePicturesVehicleIdFk references vehicles (id)
        on update cascade
        on delete cascade,
    picture_url varchar(512)
);

