create table if not exists photo (
    id bigint auto_increment primary key,
    file_name varchar(255) not null,
    content_type varchar(255) not null,
    data bytea
);

create table if not exists user_account (
    id bigint auto_increment primary key,
    user_name varchar(255) not null,
    password varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    created_at timestamp not null,
    role varchar(255) not null
);

create table if not exists user_photo (
    user_id bigint not null,
    photo_id bigint not null,
    primary key (user_id, photo_id),
    foreign key (user_id) references user_account(id),
    foreign key (photo_id) references photo(id)
);