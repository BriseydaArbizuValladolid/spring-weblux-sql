CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion TEXT,
    precio DECIMAL(10,2),
    stock INT,
    categoria VARCHAR(100),
    marca VARCHAR(100),
    estado BOOLEAN
);