BEGIN;

-- SCHEMA: DocDesk
-- DROP SCHEMA IF EXISTS DocDesk ;

CREATE SCHEMA IF NOT EXISTS docdesk;

SET search_path TO docdesk;

-- Eliminar tablas existentes en orden para evitar conflictos de claves foráneas
DROP TABLE IF EXISTS Consulta CASCADE;
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
                                 fecha DATE NOT NULL,
                                 antecedentes_familiares_heredados TEXT,
                                 antecedentes_patologicos_personales TEXT,
                                 antecedentes_personales_no_patológicos TEXT,
                                 antecedentes_quirurgicos_traumaticos TEXT,
                                 otros TEXT,
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

-- Crear tabla Consulta
CREATE TABLE Consulta (
                          id_consulta SERIAL PRIMARY KEY,
                          id_paciente INT NOT NULL,
                          fecha_creacion TIMESTAMP NOT NULL,
                          consulta TEXT,
                          subjetivo TEXT,
                          objetivo TEXT,
                          analisis TEXT,
                          tratamiento TEXT,
                          interaccion_medicamentos TEXT,
                          consejos_paciente TEXT,
                          epicrisis TEXT,
                          seguimiento_protocolos TEXT,
                          informe BYTEA,
                              CONSTRAINT fk_consulta_paciente FOREIGN KEY (id_paciente) REFERENCES Paciente (id_paciente)
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

-- Poblar DB
-- Insertar usuarios
INSERT INTO Usuario (username, nombre, apellido, correo_electronico, contrasenya, id_rol) VALUES
                                                                                              ('admin', 'Administrador', 'Principal', 'admin@docdesk.com', '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', 1),
                                                                                              ('medico1', 'Carlos', 'Pérez', 'carlos.perez@docdesk.com', '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', 2),
                                                                                              ('medico2', 'María', 'González', 'maria.gonzalez@docdesk.com', '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', 2),
                                                                                              ('enfermeria1', 'Laura', 'Martínez', 'laura.martinez@docdesk.com', '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', 3),
                                                                                              ('recepcionista1', 'Javier', 'López', 'javier.lopez@docdesk.com', '$2a$12$ngePC.tQzywYgdS3IW/4iOSWLMpipMG3Z/HAxw6lxL9hGXL7/vu0i', 4);

-- Insertar pacientes
INSERT INTO Paciente (nombre, apellido, fecha_nacimiento, direccion, telefono) VALUES
                                                                                   ('Juan', 'García', '1980-05-10', 'Calle Mayor 15, Madrid', '600123456'),
                                                                                   ('Ana', 'López', '1975-09-20', 'Avenida de la Paz 22, Barcelona', '610987654'),
                                                                                   ('Luis', 'Martínez', '1990-03-15', 'Calle San Juan 7, Valencia', '620345678'),
                                                                                   ('Sofía', 'Gómez', '1985-12-30', 'Plaza de España 3, Sevilla', '630876543'),
                                                                                   ('Miguel', 'Rodríguez', '2000-01-01', 'Calle del Sol 8, Bilbao', '640234567');

-- Insertar historias clínicas
INSERT INTO HistoriaClinica (
    id_paciente,
    fecha,
    antecedentes_familiares_heredados,
    antecedentes_patologicos_personales,
    antecedentes_personales_no_patológicos,
    antecedentes_quirurgicos_traumaticos,
    otros
) VALUES
      (1, '2024-01-10', 'Diabetes tipo 2', 'Hipertensión arterial', 'No fuma, consume alcohol ocasionalmente', 'Cirugía de apendicitis en 2015', 'Control regular cada 6 meses'),
      (2, '2024-01-15', 'Cáncer de mama en la familia', 'Asma bronquial', 'No fuma, vegetariana', 'Sin antecedentes quirúrgicos', 'Alérgica a la penicilina'),
      (3, '2024-01-20', 'Hipertensión en padres', 'Ninguna condición personal destacable', 'Vida activa, consume alcohol regularmente', 'Fractura de tobillo en 2020', 'Vacunación al día'),
      (4, '2024-01-25', 'Obesidad en hermanos', 'Colesterol alto', 'Fuma ocasionalmente', 'Cirugía dental en 2010', 'Pendiente de análisis recientes'),
      (5, '2024-02-01', 'Enfermedad cardiovascular en padres', 'Ninguna', 'Vida activa, no fuma ni bebe', 'Fractura de brazo en 2018', 'Seguimiento anual recomendado');

COMMIT;