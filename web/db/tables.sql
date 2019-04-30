drop table admins;
drop table orders;
drop table serviceLinks;
drop table services;
drop table serviceProviders;
drop table categories;
drop table users;

create table users 
(
    user_id varchar(20),
    password varchar(20) not null,
    email varchar(50) not null unique,
    firstName varchar(20) not null,
    lastName varchar(20),
    gender varchar(1) not null check (gender = 'M' or gender = 'F' or gender = 'O'),
    age number(3, 0),
    contactNo number(10, 0) not null unique,
    addLine1 varchar(100) not null,
    addLine2 varchar(100),
    city varchar(30) not null,
    constraint users_pk primary key (user_id)  
);

create table categories 
(
    category_id varchar(20),
    category varchar(20) not null unique,
    monthlyPayment number(9,2) not null,
    constraint cat_pk primary key (category_id)
);

create table serviceProviders 
(
    user_id varchar(20),
    password varchar(20) not null,
    email varchar(50) not null unique,
    companyName varchar(20) not null,
    contactNo number(10, 0) not null unique,
    rating number(2, 1),
    category_id varchar(20),
    addLine1 varchar(100) not null,
    addLine2 varchar(100),
    city varchar(30) not null,
    -- Named constraints made so as to make the schema scalable
    constraint providers_pk primary key (user_id),
    constraint rating_check check(rating >= 0.0 and rating <= 5.0),
    constraint categoryId_fk foreign key (category_id) references categories(category_id)
);

create table services 
(
    service_id varchar(20),
    name varchar(30) not null,
    description varchar(250) not null,
    charge number(9, 2) not null,
    perHour number(1, 0) not null check (perHour = 0 or perHour = 1),
    commission number(7, 2) not null,
    availability number(1, 0) not null check (availability = 0 or availability = 1),
    constraint service_pk primary key (service_id)
);

create table serviceLinks
(
    provider_id varchar(20),
    service_id varchar(20),
    constraint providers_fk foreign key (provider_id) references serviceProviders(user_id),
    constraint services_fk foreign key (service_id) references services(service_id)
);


create table orders 
(
    order_id varchar2(36),
    user_id varchar(20),
    service_id varchar(20),
    date_of_order number(11, 0) not null,
    start_time number(11, 0) not null;
    completed number(1, 0) not null check (completed = 0 or completed = 1),
    rating number(2, 1),
    duration number(2, 0) check(duration > 0 and duration <= 24),
    constraint order_pk primary key (order_id),
    constraint order_user_fk foreign key (user_id) references users(user_id),
    constraint order_service_fk foreign key (service_id) references services(service_id),
    constraint order_rating_check check(rating >= 0.0 and rating <= 5.0)
);
￼
create table admins 
(
    user_id varchar(20),
    constraint admin_fk foreign key (user_id) references users(user_id)
);
commit;