create table knowledge_points
(
    id int(11) unsigned auto_increment
        primary key,
    course_id int null,
    chapter int null,
    section int null,
    spot int null,
    name varchar(512) charset utf8mb4 null,
    ppt_first_draft_at datetime null,
    ppt_finalization_at datetime null,
    video_first_draft_at datetime null,
    video_finalization_at datetime null,
    video_upload_at datetime null,
    teacher_id int null,
    teachereditor_id int null,
    videoeditor_id int null,
    constraint course_fk
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint teacher_fk
        foreign key (teacher_id) references user (id)
            on update cascade on delete set null,
    constraint teachereditor_fk
        foreign key (teachereditor_id) references user (id)
            on update cascade on delete set null,
    constraint videoeditor_fk
        foreign key (videoeditor_id) references user (id)
            on update cascade on delete set null
);

