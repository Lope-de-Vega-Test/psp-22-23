# UA4 - Tarea 3
## Simple API
### 1. Cambios y mejoras
He modificado un poco la API, siendo ahora de Usuarios, no de Personas.
### 2. Cómo usar (peticiones)
#### 2.1 Endpoint /api/greetings
- Método GET
- Muestra mensaje de bienvenida
#### 2.2 Endpoint /api/farewell
- Método GET
- Muestra mensaje de despedida
#### 2.3 Endpoint /api/users
- Métodos GET, PUT, POST, DELETE
- Muestra usuario(s)
- Crea usuarios
- Actualiza usuarios
- Elimina usuarios
- Argumentos en la Query de la URI:
  - **GET**: index (entero), uuid(long), id (texto), creationDate (texto), idLike (texto), creationDateAfter (texto), creationDateBefore (texto)
    - Ejemplos:
      - hostname:port/api/users?index=1
      - hostname:port/api/users?uuid=3
      - hostname:port/api/users?id=Paco
      - hostname:port/api/users?creationDate=2023-03-11
      - hostname:port/api/users?idLike=adr
      - hostname:port/api/users?creationDateAfter=2020-01-01
      - hostname:port/api/users?creationDateAfter=2019-12-31&creationDateBefore=2021-01-01
  - **PUT**: index (entero), id (texto)
  - **POST**: index (entero), id (texto)
  - **DELETE**: index (entero)
