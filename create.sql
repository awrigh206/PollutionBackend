create sequence hibernate_sequence start with 1 increment by 1
create table city (city_id integer not null, latitude double, longitude double, country varchar(255), distance double, location varchar(255), name varchar(255), primary key (city_id))
create table city_air_quality (city_city_id integer not null, unit varchar(255), value double, last_updated varchar(255), parameter_used varchar(255), source_name varchar(255), units varchar(255), value_of double)
create table country (id integer not null, count integer, country_code varchar(255), locations integer, name varchar(255), number_of_cities integer, primary key (id))
create table country_cities_within_country (country_id integer not null, cities_within_country_city_id integer not null)
create table statistics (id integer not null, kurtoise double, city_name varchar(255), geometric_mean double, max double, mean double, min double, n_terms bigint not null, pollution_type varchar(255), standard_deviation double, trend double, variance double, primary key (id))
create table user_information (id integer not null, email varchar(255), password varchar(255), phone_number varchar(255), user_name varchar(255), primary key (id))
alter table country_cities_within_country add constraint UK_dtw9b9jtlf25gqclhik76b5w2 unique (cities_within_country_city_id)
alter table city_air_quality add constraint FKce163kankn7vr10axfvj7kcth foreign key (city_city_id) references city
alter table country_cities_within_country add constraint FK2lt25wi5oa9ag4rtuk6ogw4hs foreign key (cities_within_country_city_id) references city
alter table country_cities_within_country add constraint FK57xx322mrfxqdkdnlus7kt5jm foreign key (country_id) references country
