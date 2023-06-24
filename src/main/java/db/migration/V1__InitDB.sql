CREATE TABLE IF NOT EXISTS users
(
    id_user serial primary key,
    login varchar(50),
    email varchar(255),
    user_password varchar(255)
    );

CREATE TABLE IF NOT EXISTS lot
(
    id_lot serial primary key,
    name_of_lot varchar(100),
    image text,
    author varchar(50),
    category text,
    description text,
    expiration timestamp
    );

create table if not exists auction_bid
(
    id_bid serial primary key,
    id_user bigint,
    id_lot bigint,
    bid numeric(6,2),
    datetime timestamp,
    foreign key(id_user) references Users(id_user),
    foreign key(id_lot) references Lot(id_lot)
    );