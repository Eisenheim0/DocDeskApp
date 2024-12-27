BEGIN

-- SCHEMA: DocDesk
-- DROP SCHEMA IF EXISTS DocDesk ;

CREATE SCHEMA IF NOT EXISTS DocDesk
    AUTHORIZATION postgres;

set schema 'DocDesk';

-- Eliminar tablas existentes en orden para evitar conflictos de claves foráneas
DROP TABLE IF EXISTS Informe CASCADE;
DROP TABLE IF EXISTS HistorialAcceso CASCADE;
DROP TABLE IF EXISTS Cita CASCADE;
DROP TABLE IF EXISTS HistoriaClinica CASCADE;
DROP TABLE IF EXISTS Usuario CASCADE;
DROP TABLE IF EXISTS Rol_Permiso CASCADE;
DROP TABLE IF EXISTS Permiso CASCADE;
DROP TABLE IF EXISTS Rol CASCADE;
DROP TABLE IF EXISTS Paciente CASCADE;

-- Crear tabla Rol
CREATE TABLE Rol (
                     id_rol SERIAL PRIMARY KEY,
                     nombre_rol VARCHAR(50) UNIQUE NOT NULL
);

-- Crear tabla Permiso
CREATE TABLE Permiso (
                         id_permiso SERIAL PRIMARY KEY,
                         nombre_permiso VARCHAR(50) UNIQUE NOT NULL
);

-- Crear tabla Usuario
CREATE TABLE Usuario (
                         id_usuario SERIAL NOT NULL PRIMARY KEY,
                         username VARCHAR(50) NOT NULL UNIQUE,
                         nombre VARCHAR(100) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                         correo_electronico VARCHAR(150) UNIQUE NOT NULL,
                         contrasenya VARCHAR(255) NOT NULL,
                         id_rol INT NOT NULL,
                         fecha_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES Rol (id_rol)
                             ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla Rol_Permiso
CREATE TABLE Rol_Permiso (
                             id_rol INT NOT NULL,
                             id_permiso INT NOT NULL,
                             PRIMARY KEY (id_rol, id_permiso),
                             CONSTRAINT fk_rol_permiso_rol FOREIGN KEY (id_rol) REFERENCES Rol (id_rol)
                                 ON DELETE CASCADE ON UPDATE CASCADE,
                             CONSTRAINT fk_rol_permiso_permiso FOREIGN KEY (id_permiso) REFERENCES Permiso (id_permiso)
                                 ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla Paciente
CREATE TABLE Paciente (
                          id_paciente SERIAL PRIMARY KEY,
                          nombre VARCHAR(100) NOT NULL,
                          apellido VARCHAR(100) NOT NULL,
                          fecha_nacimiento DATE NOT NULL,
                          direccion VARCHAR(255),
                          telefono VARCHAR(15)
);

-- Crear tabla HistoriaClinica
CREATE TABLE HistoriaClinica (
                                 id_historia SERIAL PRIMARY KEY,
                                 id_paciente INT NOT NULL,
                                 fecha_creacion DATE NOT NULL,
                                 diagnostico TEXT,
                                 tratamiento TEXT,
                                 observaciones TEXT,
                                 CONSTRAINT fk_historia_paciente FOREIGN KEY (id_paciente) REFERENCES Paciente (id_paciente)
                                     ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla Cita
CREATE TABLE Cita (
                      id_cita SERIAL PRIMARY KEY,
                      id_paciente INT NOT NULL,
                      id_usuario INT NOT NULL,
                      fecha_cita TIMESTAMP NOT NULL,
                      motivo TEXT,
                      estado VARCHAR(50) DEFAULT 'Programada',
                      CONSTRAINT fk_cita_paciente FOREIGN KEY (id_paciente) REFERENCES Paciente (id_paciente)
                          ON DELETE CASCADE ON UPDATE CASCADE,
                      CONSTRAINT fk_cita_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
                          ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla HistorialAcceso
CREATE TABLE HistorialAcceso (
                                 id_acceso SERIAL PRIMARY KEY,
                                 id_usuario INT NOT NULL,
                                 fecha_acceso TIMESTAMP NOT NULL,
                                 actividad VARCHAR(255) NOT NULL,
                                 CONSTRAINT fk_historial_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)
                                     ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla Informe
CREATE TABLE Informe (
                         id_informe SERIAL PRIMARY KEY,
                         id_historia INT NOT NULL,
                         fecha_creacion TIMESTAMP NOT NULL,
                         contenido TEXT,
                         CONSTRAINT fk_informe_historia FOREIGN KEY (id_historia) REFERENCES HistoriaClinica (id_historia)
                             ON DELETE CASCADE ON UPDATE CASCADE
);

-- Insertar roles básicos en la tabla Rol
INSERT INTO Rol (nombre_rol) VALUES
                                 ('Administrador'),
                                 ('Médico'),
                                 ('Enfermería'),
                                 ('Recepcionista');

-- Insertar permisos básicos en la tabla Permiso
INSERT INTO Permiso (nombre_permiso) VALUES
                                         ('MANAGE_USERS'),
                                         ('REGISTER_PATIENTS'),
                                         ('UPDATE_MEDICAL_RECORDS'),
                                         ('VIEW_APPOINTMENTS'),
                                         ('MODIFY_APPOINTMENTS'),
                                         ('CANCEL_APPOINTMENTS'),
                                         ('GENERATE_MEDICAL_REPORTS');


-- Asignar permisos al rol de Administrador
INSERT INTO Rol_Permiso (id_rol, id_permiso) VALUES
                                                 (1, 1), -- Administrador: Gestionar Usuarios
                                                 (1, 2), -- Administrador: Registrar Pacientes
                                                 (1, 3), -- Administrador: Actualizar Historias Clínicas
                                                 (1, 4), -- Administrador: Ver Citas
                                                 (1, 5), -- Administrador: Modificar Citas
                                                 (1, 6), -- Administrador: Cancelar Citas
                                                 (1, 7); -- Administrador: Generar Informes Médicos

-- Asignar permisos al rol de Médico
INSERT INTO Rol_Permiso (id_rol, id_permiso) VALUES
                                                 (2, 3), -- Médico: Actualizar Historias Clínicas
                                                 (2, 4), -- Médico: Ver Citas
                                                 (2, 7); -- Médico: Generar Informes Médicos

-- Asignar permisos al rol de Enfermería
INSERT INTO Rol_Permiso (id_rol, id_permiso) VALUES
                                                 (3, 2), -- Enfermería: Registrar Pacientes
                                                 (3, 4); -- Enfermería: Ver Citas

-- Asignar permisos al rol de Recepcionista
INSERT INTO Rol_Permiso (id_rol, id_permiso) VALUES
                                                 (4, 4), -- Recepcionista: Ver Citas
                                                 (4, 5), -- Recepcionista: Modificar Citas
                                                 (4, 6); -- Recepcionista: Cancelar Citas

-- Insertar Usuario básico en la tabla Usuario
INSERT INTO Usuario (
    username,
    nombre,
    apellido,
    correo_electronico,
    contrasenya,
    id_rol
) VALUES (
             'admin',            -- Username
             'John',            -- Nombre
             'Doe',             -- Apellido
             'admin@example.com', -- Correo Electrónico
             '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', -- Contraseña (encriptada)
             1                  -- ID del Rol
         );