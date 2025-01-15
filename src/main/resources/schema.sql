create table if not exists photo (
    id bigint auto_increment primary key,
    file_name varchar(255) not null,
    content_type varchar(255) not null,
    data bytea
);