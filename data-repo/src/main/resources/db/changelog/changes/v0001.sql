--create database if not exists weatherdata;
--use weatherdata;

create table coord (
    id bigserial primary key not null,
    lon numeric not null,
    lat numeric not null,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table main (
    id bigserial primary key not null,
    temp numeric not null,
    pressure numeric not null,
    humidity int not null,
    temp_min numeric,
    temp_max numeric,
    sea_level numeric,
    grnd_level numeric,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table rain (
    id bigserial primary key not null,
    one_hour numeric,
    three_hours numeric,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table snow (
    id bigserial primary key not null,
    one_hour numeric,
    three_hours numeric,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table sys (
    id bigserial primary key not null,
    country text,
    original_sunrise timestamp,
    original_sunset timestamp,
    sunrise text,
    sunset text,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table wind (
    id bigserial primary key not null,
    speed numeric,
    deg numeric,
    gust numeric,
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table weather_data (
    id bigserial primary key not null,
    city_id bigint,
    city_name text,
    cloudiness int,
    dct timestamp,
    timezone int,
    coord_id bigint not null references coord(id),
    main_id bigint not null references main(id),
    rain_id bigint references rain(id),
    snow_id bigint references snow(id),
    sys_id bigint not null references sys(id),
    wind_id bigint not null references wind(id),
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);

create table weather (
    id bigserial primary key not null,
    provider_id int,
    main text,
    description text,
    icon text,
    weather_data_id bigint not null references weather_data(id),
    created_date timestamp not null,
    created_by text,
    last_modified_date timestamp not null,
    last_modified_by text
);