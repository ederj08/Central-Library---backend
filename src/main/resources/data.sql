-- =====================
-- LIBROS
-- =====================


INSERT INTO libro (autor, isbn, titulo)
SELECT 'Gabriel García Márquez', '9780060883287', 'Cien años de soledad'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780060883287');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'J.K. Rowling', '9780439708180', 'Harry Potter y la piedra filosofal'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780439708180');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Miguel de Cervantes', '9780142437230', 'Don Quijote de la Mancha'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780142437230');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'George Orwell', '9780451524935', '1984'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780451524935');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Antoine de Saint-Exupéry', '9780156012195', 'El Principito'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780156012195');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Isabel Allende', '9780060955458', 'La casa de los espíritus'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780060955458');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Mario Vargas Llosa', '9780312427528', 'La ciudad y los perros'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780312427528');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Franz Kafka', '9780805209990', 'La metamorfosis'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780805209990');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Fyodor Dostoevsky', '9780140449136', 'Crimen y castigo'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780140449136');

INSERT INTO libro (autor, isbn, titulo)
SELECT 'Leo Tolstoy', '9780143035008', 'Guerra y paz'
WHERE NOT EXISTS (SELECT 1 FROM libro WHERE isbn = '9780143035008');

-- =====================
-- ESTUDIANTES
INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Carlos', 'Andres', 'Martinez', 'Lopez', '2000-03-15', 'carlos.martinez@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'carlos.martinez@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Maria', 'Jose', 'Rodriguez', 'Garcia', '2001-07-22', 'maria.rodriguez@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'maria.rodriguez@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Juan', 'Pablo', 'Gomez', 'Torres', '1999-11-08', 'juan.gomez@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'juan.gomez@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Laura', 'Sofia', 'Perez', 'Moreno', '2002-05-30', 'laura.perez@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'laura.perez@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Andres', 'Felipe', 'Castro', 'Vargas', '2000-09-14', 'andres.castro@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'andres.castro@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Valentina', 'Isabel', 'Herrera', 'Diaz', '2001-01-25', 'valentina.herrera@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'valentina.herrera@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Santiago', 'David', 'Mora', 'Jimenez', '2003-08-17', 'santiago.mora@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'santiago.mora@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Camila', 'Andrea', 'Reyes', 'Sanchez', '2000-12-03', 'camila.reyes@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'camila.reyes@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Daniel', 'Esteban', 'Flores', 'Ruiz', '2002-04-19', 'daniel.flores@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'daniel.flores@email.com');

INSERT INTO estudiante (id_estudiante, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, fecha_nacimiento, email) SELECT nextval('sequence_estudiante'), 'Isabella', 'Mariana', 'Vargas', 'Mendez', '2001-06-11', 'isabella.vargas@email.com' WHERE NOT EXISTS (SELECT 1 FROM estudiante WHERE email = 'isabella.vargas@email.com');
-- =====================