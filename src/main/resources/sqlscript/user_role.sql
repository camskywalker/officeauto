-- auto-generated definition
create table user_role
(
    id  int auto_increment
        primary key,
    uid int null,
    rid int null,
    constraint rid_fk
        foreign key (rid) references role (id)
            on update cascade on delete cascade,
    constraint uid_fk
        foreign key (uid) references user (id)
            on update cascade on delete cascade
);