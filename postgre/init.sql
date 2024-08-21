CREATE TABLE public.arrendadores (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(100),
    contraseña VARCHAR(100)
);
CREATE TABLE public.arrendatarios (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    correo VARCHAR(100)
    contraseña VARCHAR(100)
);
CREATE TABLE public.propiedades (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_arrendatario INTEGER REFERENCES public.arrendatarios(id),
    nombrePropiedad VARCHAR(100)
);
CREATE TABLE public.contratos (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_propiedad INTEGER REFERENCES public.propiedades(id),
    id_arrendador INTEGER REFERENCES public.arrendadores(id),
    precio NUMERIC(10,2),
    fechaInicio TIMESTAMP,
    fechaFinal TIMESTAMP
);
