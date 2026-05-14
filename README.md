# Proyecto Spring Boot - Música (Artista → Álbum)

Este proyecto es una aplicación web Spring Boot que gestiona artistas y sus álbumes, con una relación uno a muchos.

## Tecnologías utilizadas
- Spring Boot 4.0.6
- Spring Data JPA
- Spring Web MVC
- Thymeleaf
- MySQL
- Docker Compose

## Estructura del proyecto
- **Modelos**: Artista y Album con relación JPA
- **Repositorios**: Interfaces CrudRepository con consultas personalizadas
- **Servicio**: ServicioMusica con toda la lógica de negocio
- **Controladores**: API REST y controladores web con Thymeleaf
- **Plantillas**: Vistas HTML para la interfaz web

## Cómo ejecutar

### 1. Levantar la base de datos con Docker Compose
```bash
docker-compose up -d
```

Esto iniciará un contenedor MySQL con la base de datos `musica_db`.

### 2. Ejecutar la aplicación Spring Boot
```bash
mvn spring-boot:run
```

La aplicación estará disponible en http://localhost:8080

## Endpoints API REST

### Artistas
- `GET /api/artistas` - Listar todos (opcional: ?genero=)
- `POST /api/artistas` - Crear artista
- `GET /api/artistas/{id}` - Obtener por ID
- `PUT /api/artistas/{id}` - Actualizar
- `DELETE /api/artistas/{id}` - Borrar

### Álbumes
- `GET /api/albumes` - Listar todos
- `POST /api/albumes` - Crear álbum
- `GET /api/albumes/{id}` - Obtener por ID
- `PUT /api/albumes/{id}` - Actualizar
- `DELETE /api/albumes/{id}` - Borrar
- `GET /api/albumes/artista/{artistaId}` - Álbumes de un artista
- `GET /api/albumes/rango?añoInicio=&añoFin=` - Álbumes en rango de años
- `DELETE /api/albumes/anteriores/{año}` - Borrar álbumes anteriores a un año

## Interfaz web
- `/web/artistas` - Gestión de artistas
- `/web/albumes` - Gestión de álbumes

## Configuración de base de datos
La aplicación se conecta a MySQL corriendo en Docker Compose con:
- Host: localhost:3306
- Base de datos: musica_db
- Usuario: musica_user
- Contraseña: musica_pass