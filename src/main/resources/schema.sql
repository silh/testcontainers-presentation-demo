create table if not exists users
(
    id   bigint primary key,
    name varchar(500) not null
);

create table if not exists products
(
    id    bigint primary key,
    name  varchar(200) not null,
    price decimal      not null
);

create table if not exists orders
(
    id     bigint primary key,
    user_id bigint not null,
--     metadata jsonb,
    foreign key (user_id) references users
);

create table if not exists order_to_products
(
    order_id   bigint not null,
    product_id bigint not null,
    foreign key (order_id) references orders,
    foreign key (product_id) references products
);
