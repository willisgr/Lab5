create table products
(
    id          bigint auto_increment
        primary key,
    name        varchar(255) not null,
    description varchar(255) not null,
    price       decimal      not null,
    category_id bigint       not null,
    constraint products_category_id_fk
        foreign key (category_id) references categories (id)
);