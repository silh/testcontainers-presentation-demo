insert into users (id, name)
values (1, 'ilia'),
       (2, 'some_one');

insert into products (id, name, price)
values (1, 'milk', 10.00),
       (2, 'cheese', 100.99),
       (3, 'umbrella', 200.91),
       (4, 'apple', 999.99);

insert into orders(id, user_id)
values (1, 1),
       (2, 1),
       (3, 2),
       (4, 1),
       (5, 2);

insert into order_to_products (order_id, product_id)
values (1, 1),
       (1, 2),
       (1, 4),
       (2, 1),
       (3, 2),
       (3, 1),
       (3, 3),
       (4, 3);
