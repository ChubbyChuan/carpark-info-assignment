CREATE TABLE IF NOT EXISTS car_park (
    car_park_no VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
    x_coord DECIMAL(10,4),
    y_coord DECIMAL(10,4),
    car_park_type VARCHAR(100),
    type_of_parking_system VARCHAR(100),
    short_term_parking VARCHAR(255),
    free_parking VARCHAR(255),
    night_parking VARCHAR(255),
    car_park_deck INT,
    gantry_height DECIMAL(3,2),
    car_park_basement VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS customers (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
    favourites VARCHAR(255)
);