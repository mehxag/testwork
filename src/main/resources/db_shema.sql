CREATE TABLE Workers (
     id SERIAL PRIMARY KEY,
     name VARCHAR (20),
     position VARCHAR (20),
     avatar VARCHAR (50)
);

CREATE TABLE Tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR (100),
    description VARCHAR (200),
    time timestamptz,
    status VARCHAR (20),
    performer INTEGER,
    FOREIGN KEY (performer) REFERENCES workers (id)
);
