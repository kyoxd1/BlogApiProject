# Blog API project

### Este proyecto es con el fin de que usuarios creen Blogs donde pueden o no permitir que otros den comentarios y le pongan un rating, a su vez pueden modificarlo, no se deben usar base de datos en su lugar se leera y guardara los datos en un Json para su persistencia.

## 1. Requisitos

    - Java 17 (JDK 17) o superior
    - Maven 3.x o superior
    - Spring boot 3.x.
    - Libreria org.json para manejo del Json
    - Postman o cualquier otro programa para probar los endpoints

## 2. Instalación

### 2.1 Clona el Repositorio

    git clone https://github.com/kyoxd1/BlogApiProject.git
    
    cd BlogApiProject

### 2.2 Compila el proyecto y instala las dependencias del pom.xml

    mvn clean install

### 2.3 Ejecuta la aplicación
    
    mvn spring-boot:run

## 3. Estructura del proyecto

    src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── blogapiproject/
    │   │               ├── controller/
    │   │               ├── model/
    │   │               ├── service/
    │   │               └── BlogApiProjectApplication.java
    │   └── resources/
    │       └── application.properties
    └── test/
    └── java/
    └── com/
    └── example/
    └── blogapiproject/


## 4. Endpoints de la API

Puerto disponible de la aplicacion:
- http://localhost:8080

### 4.1 Autores

Crear Autor
  - 
- Metodo : 
      -    POST
- Ruta : 
  - /authors
- Cuerpo :

        {
            "firstName": "nombre",
            "lastName": "apellido",
            "birthDate": "1999-01-01",
            "country": "Bolivia",
            "email": "prueba@gmail.com"
        }

- Obtener todos los Autores
  - 
    - Metodo :
        -    GET
    - Ruta :
        - /authors

- Obtener un Autor por ID
  - 
    - Metodo :
        -    GET
    - Ruta :
        - /authors/{id}

### 4.2 BLOGs

Crear un Blog
  - 
- Metodo :
    -    POST
- Ruta :
    - /blogs
- Cuerpo :

        {
            "authorId": 1,
            "title": "Mi primer blog",
            "theme": "Tecnología",
            "periodicity": "DIARIA",
            "allowComments": true
        }

Actualizar un Blog
- 
- Metodo :
    -    PUT
- Ruta :
    - /blogs/{id}
- Cuerpo :

        {
            "updatedContent": "Nuevo contenido",
            "allowComments": false
        }

Obtener un Blog por Id
- 
- Metodo :
  -    GET
- Ruta :
  - /blogs/{id}

Obtener todos los blogs
- 
- Metodo :
  -    GET
- Ruta :
  - /blogs

### 4.3 Comentarios

Crear comentario de un blog que acepte comentario sino sale error
- 
- Metodo :
  -    POST
- Ruta :
  - /blogs/{id}/comments
- Cuerpo :

        {
            "authorName": "nombre",
            "authorEmail": "prueba@gmail.com",
            "authorCountry": "Bolivia",
            "content": "Buen blog!",
            "rating": 8
        }

Obtener todos los comentarios de un Blog
- 
- Metodo :
  -    GET
- Ruta :
  - /blogs/{id}/comments

Historial de actualizaciones del Blog
- 
- Metodo :
  -    GET
- Ruta :
  - /blog-update-history/{id}


