create table course_user
(
    id int auto_increment
        primary key,
    course_id int null,
    user_id int null,
    constraint fk_course
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint fk_user
        foreign key (user_id) references user (id)
            on update cascade on delete cascade
);

