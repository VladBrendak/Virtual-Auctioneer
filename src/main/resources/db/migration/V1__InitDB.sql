CREATE TABLE IF NOT EXISTS users
(
    id_user bigint not null,
    login varchar(50),
    email varchar(255),
    user_password varchar(20),
    primary key(id_user)
    );

CREATE TABLE IF NOT EXISTS lot
(
    id_lot bigint NOT NULL,
    name_of_lot varchar(100),
    image text,
    author varchar(50),
    category text,
    description text,
    expiration timestamp,
    primary key(id_lot));

create table if not exists auction_bid
(
    id_bid bigint not null,
    id_user bigint,
    id_lot bigint,
    bid numeric(6,2),
    datetime timestamp,
    primary key(id_bid),
    foreign key(id_user) references Users(id_user),
    foreign key(id_lot) references Lot(id_lot));