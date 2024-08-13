CREATE TABLE public.formulario (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100),
    apellidos VARCHAR(100),
    correo VARCHAR(100),
    semestre INTEGER,
    descripcion VARCHAR(500)
);