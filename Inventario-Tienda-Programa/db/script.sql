-- Crear tabla Videojuego
CREATE TABLE Videojuego (
    codigo TEXT PRIMARY KEY,           -- Código único de identificación del videojuego
    titulo TEXT NOT NULL,              -- Título del videojuego
    plataforma TEXT NOT NULL,          -- Plataforma (ej: PS5, Xbox, PC)
    precio INTEGER NOT NULL,           -- Precio del videojuego en enteros
    estado INTEGER NOT NULL DEFAULT 0,  -- Estado del videojuego (0: Disponible, 1: Vendido)
    ruta TEXT -- Ruta del videojuego
	
);

-- Crear tabla Cliente
CREATE TABLE Cliente (
    rut TEXT PRIMARY KEY,              -- Identificación única del cliente
    nombre TEXT NOT NULL               -- Nombre completo del cliente
);

-- Crear tabla Venta
CREATE TABLE Venta (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Identificador único de la venta
    fechaVenta DATE NOT NULL,             -- Fecha en la que se realizó la venta
    clienteRut TEXT NOT NULL,             -- Relación con el cliente que realizó la compra
    FOREIGN KEY (clienteRut) REFERENCES Cliente(rut) -- Clave foránea hacia Cliente
);

-- Crear tabla VentaVideojuego (tabla intermedia para registrar los videojuegos vendidos)
CREATE TABLE VentaVideojuego (
    idVenta INTEGER NOT NULL,              -- Relación con la venta correspondiente
    codigoVideojuego TEXT NOT NULL,        -- Relación con el videojuego vendido
    FOREIGN KEY (idVenta) REFERENCES Venta(id),         -- Clave foránea hacia Venta
    FOREIGN KEY (codigoVideojuego) REFERENCES Videojuego(codigo) -- Clave foránea hacia Videojuego
);



INSERT INTO Videojuego (codigo, titulo, plataforma, precio, estado) VALUES
('VJ001', 'The Last of Us Part II', 'PS4', 59, 1),
('VJ002', 'Halo Infinite', 'Xbox', 49, 0),
('VJ003', 'Cyberpunk 2077', 'PC', 39, 0),
('VJ004', 'God of War', 'PS5', 69, 0);


INSERT INTO Cliente (rut, nombre) VALUES
('12345678-9', 'Juan Pérez'),
('98765432-1', 'María González'),
('11223344-5', 'Carlos López'),
('55667788-0', 'Ana Martínez')

INSERT INTO Venta (fechaVenta, clienteRut) VALUES
('2024-10-01', '12345678-9');

INSERT INTO VentaVideojuego (idVenta, codigoVideojuego) VALUES
(1, 'VJ001');