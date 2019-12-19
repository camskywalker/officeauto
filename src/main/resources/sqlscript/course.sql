create table course
(
    id int auto_increment
        primary key,
    coursename varchar(128) charset utf8mb4 null,
    major_id int null,
    admin_id int null,
    finished tinyint null,
    constraint major_fk
        foreign key (major_id) references major (id)
            on update cascade on delete set null,
    constraint user_fk
        foreign key (admin_id) references user (id)
            on update cascade on delete set null
);