create table if not exists products
(
    id    bigint primary key,
    name  varchar(200) not null,
    price decimal      not null
);

create table if not exists orders
(
    id     bigint primary key,
    userId bigint not null,
    foreign key (userId) references users
);

create table if not exists order_to_products
(
    orderId   bigint not null,
    productId bigint not null,
    foreign key (orderId) references orders,
    foreign key (productId) references products
);

create table if not exists users
(
    id   bigint primary key,
    name varchar(500) not null
);
