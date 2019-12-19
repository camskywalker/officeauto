create table asr
(
    id int auto_increment
        primary key,
    uuid varchar(512) null,
    user_id int null,
    request_id bigint null,
    text text null,
    constraint asr_user_id_fk
        foreign key (user_id) references user (id)
            on update cascade on delete set null
);

