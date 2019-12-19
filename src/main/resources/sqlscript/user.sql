create table user
(
    id int auto_increment
        primary key,
    password varchar(255) charset utf8mb4 null,
    username varchar(32) charset utf8mb4 null,
    email varchar(128) charset utf8mb4 null,
    admin_id int null,
    constraint admin_fk
        foreign key (admin_id) references user (id)
            on update cascade on delete set null
);

