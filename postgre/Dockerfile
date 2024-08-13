FROM postgres:latest

WORKDIR /home

COPY init.sql /docker-entrypoint-initdb.d/

EXPOSE 5432