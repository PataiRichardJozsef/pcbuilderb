CREATE TABLE hardware (
    id serial primary key,
    type TEXT NOT NULL,  -- MOTHERBOARD, GRAPHIC-CARD
    name TEXT NOT NULL,
    market_price int NOT NULL,
    model TEXT NOT NULL,
    manufacturer TEXT NOT NULL
);

CREATE TABLE gadget (
    id serial primary key,
    market_price int NOT NULL,
    name TEXT NOT NULL
);

CREATE TABLE hardware_mapping (
    motherboard_id int,
    hardware_id int
);