<<<<<<< HEAD
![lope_logo](https://www.ceslopedevega.com/wp-content/uploads/2020/03/pruebalogo.svg_.png)

# UA4 - Generación de Servicios en Red


- Temporalización: **2T**
- Duración: **12 horas**
- Ponderación respecto a la asignatura: **20%**

## Resultados de Aprendizaje

En esta Unidad de Aprendizaje 4 (UA4) se desarrollan los siguientes resultados de aprendizaje (RA):

- **RA4.** *Desarrolla aplicaciones que ofrecen servicios en red, utilizando librerías de clases y aplicando criterios de eficiencia y disponibilidad*

### Criterios de Evaluación

a) Se han analizado librerías que permitan implementar protocolos estándar de comunicación en red.
b) Se han programado clientes de protocolos estándar de comunicaciones y verificado su funcionamiento.
c) Se han desarrollado y probado servicios de comunicación en red.
d) Se han analizado los requerimientos necesarios para crear servicios capaces de gestionar varios clientes concurrentes.
e) Se han incorporado mecanismos para posibilitar la comunicación simultánea de varios clientes con el servicio.
f) Se ha verificado la disponibilidad del servicio.
g) Se han depurado y documentado las aplicaciones desarrolladas.

## Contenidos

Protocolos y servicios de la capa de aplicación.
Protocolos de capa de aplicación comunes: Telnet, HTTP, SMTP, POP3, IMAP.
Provisión de servicios de direccionamiento IP: DNS, DHCP.
Provisión de servicios de intercambio de archivos: FTP, SMB.
Programación de aplicaciones cliente.
Desarrollo de un cliente HTTP sencillo.
Programación de servidores y servicios.
Comunicaciones simultáneas con varios clientes.
Middleware Orientado a Mensajes (MOM).
El superservidor xinetd.
API Rest


## Evaluación

La evaluación de esta unidad didáctica estará basada en la entrega de las tareas, corregidas automáticamente por los tests , acorde a la siguiente tabla

| CRITERIO de EVALUACIÓN | PONDERACIÓN | INSTRUMENTOS de EVALUACIÓN|
|------------------------|-------------|-------------|
| a)                     |16 %       | Tareas 1,2 y 3      |
| b)                     |16 %       | Tareas 1,2 y 3      |
| c)                     |16 %       | Tareas 1,2 y 3      |
| d)                     |16 %       | Tareas 1,2 y 3      |
| e)                     |16 %       | Tareas 1,2 y 3     |
| f)                     |10 %       | Tareas 1,2 y 3      |
| g)                     |10 %       | Tareas 1,2 y 3      |
|                        |**100 %**    |             |



### **Tarea 1 - Servicios en Red (I)**
##### **Criterios a), b), c), d) e), f), g), h)**

Modifica el cliente FTP de ejemplo para que pueda conectarse a cualquier servidor FTP.
Haz que el usuario tenga que introducir el nombre del servidor FTP, el nombre de usuario y su clave, ya sea desde la línea de comandos o modificando la interfaz gráfica. 
La opción de la interfaz gráfica permitirá sacar el 10 en esta tarea, mientras que la opción por línea de comandos únicamente puntuará hasta 8 puntos.
```
Fecha de Entrega: 1/02/2023 - 14:45
```

### **Tarea 2 - Servicios en Red (II)**
##### **Criterios a), b), c), d) e), f), g), h)**

Analiza el código planteado en [UA4\tareas\dev_0\tarea_2](https://github.com/Lope-de-Vega-Test/psp-22-23/tree/main/UA4/tareas/dev_0/tarea_2)

Una vez tengas tu primera API básica completa aumenta su funcionalidad permitiendo que la API:

- Permita crear (CREATE) Personas en el depósito de datos.
- Permita leer (READ) Personas en el depósito de datos, utilizando números enteros como identificador.
- Permita actualizar (UPDATE) Personas en el depósito de datos.
- Permita eliminar (DELETE) Personas del depósito de datos.

Ejemplos de Peticiones
- READ - ​GET http://localhost:8080/api/greeting   <- Should return 200 OK
- READ - ​GET http://localhost:8080/api/bye   <- Should return 200 OK
- READ - ​GET http://localhost:8080/api/person?name=Ada   <- Should return 200 OK
- ​READ - GET http://localhost:8080/api/person?name=Paco  <- Should return 404 Not Found
- ​READ - GET http://localhost:8080/api/person?nane=Kevin <- Should return 400 Bad Request
- CREATE - POST http://localhost:8080/api/person?name=MANOLO&about=vaya movida gorda&birthYear=2033 <- Should return 200 OK
- CREATE - POST http://localhost:8080/api/person?nane=MANOLO&about=vaya movida gorda&birthYear=2033& <- Should return 400 Bad Request
- UPDATE - PUT http://localhost:8080/api/person?name=MANOLO&about=vaya movida no tan gorda&birthYear=2013 <- Should return 200 OK
- DELETE - DEL http://localhost:8080/api/person?name=MANOLO

Esta tarea se defenderá individualmente en clase el mismo día de la entrega.  Para la defensa de esta tarea deberás tener funcionando simultáneamente tu API y una librería de consultas en (Postman||RapidAPI) preparadas para esta finalidad, ojo, yo también las tendré.

Referencias:
- [HttpServer](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html)
- [HttpHandler](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpHandler.html)
- [HttpExchange](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpExchange.html)
- [InetSocketAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetSocketAddress.html)



```
Fecha de Entrega: 15/02/2023 - 14:45
```

### **Tarea 3 - Servicios en Red (III)**
##### **Criterios a), b), c), d) e), f), g), h)**

```
Fecha de Entrega: A definir
```


## Recursos

- [Repositorio Asignatura](https://github.com/daniteleco/psp-22-23)
- Apuntes del Profesorado //TODO
- [Presentación Generación de Servicios en Red (I)](https://docs.google.com/presentation/d/e/2PACX-1vStejrBe5G2aUIB9myXg05S8Mg3TXgsAk0q1QVjzDTYUqPY7clejikwhocUblM0GGlK7cBYtfWJkhoY/pub?start=false&loop=false&delayms=60000)
- [Presentación Generación de Servicios en Red (II)](https://docs.google.com/presentation/d/e/2PACX-1vRYHckWhMqkAlDn3qv9MXf9hyoie-8bkhtUuVgiu4_TOHC0MxECXGZQvcLOJvu7rzrfuqtF_bV2Pusw/pub?start=false&loop=false&delayms=60000&slide=id.p)
- Bibliografía Recomendada
  - **Programación de servicios y procesos - Técnico Superior en DAM.** *Mª Jesús Ramos Martín. Editorial Garceta. 2ª Edición. 2018. ISBN: 978-84-1728-931-7.*

## Autor

Creado por [Daniel Pérez Rodríguez](https://twitter.com/daniteleco) e inspirado en el trabajo de [José Luis González Sánchez](https://github.com/joseluisgs/ProgServiciosProcesos-00-2021-2022)

## Contacto
- Email: [dperez@ceslopedevega.com](mailto:dperez@ceslopedevega.com)
=======
![lope_logo](https://www.ceslopedevega.com/wp-content/uploads/2020/03/pruebalogo.svg_.png)

# UA4 - Generación de Servicios en Red


- Temporalización: **2T**
- Duración: **12 horas**
- Ponderación respecto a la asignatura: **20%**

## Resultados de Aprendizaje

En esta Unidad de Aprendizaje 4 (UA4) se desarrollan los siguientes resultados de aprendizaje (RA):

- **RA4.** *Desarrolla aplicaciones que ofrecen servicios en red, utilizando librerías de clases y aplicando criterios de eficiencia y disponibilidad*

### Criterios de Evaluación

a) Se han analizado librerías que permitan implementar protocolos estándar de comunicación en red.
b) Se han programado clientes de protocolos estándar de comunicaciones y verificado su funcionamiento.
c) Se han desarrollado y probado servicios de comunicación en red.
d) Se han analizado los requerimientos necesarios para crear servicios capaces de gestionar varios clientes concurrentes.
e) Se han incorporado mecanismos para posibilitar la comunicación simultánea de varios clientes con el servicio.
f) Se ha verificado la disponibilidad del servicio.
g) Se han depurado y documentado las aplicaciones desarrolladas.

## Contenidos

Protocolos y servicios de la capa de aplicación.
Protocolos de capa de aplicación comunes: Telnet, HTTP, SMTP, POP3, IMAP.
Provisión de servicios de direccionamiento IP: DNS, DHCP.
Provisión de servicios de intercambio de archivos: FTP, SMB.
Programación de aplicaciones cliente.
Desarrollo de un cliente HTTP sencillo.
Programación de servidores y servicios.
Comunicaciones simultáneas con varios clientes.
Middleware Orientado a Mensajes (MOM).
El superservidor xinetd.
API Rest


## Evaluación

La evaluación de esta unidad didáctica estará basada en la entrega de las tareas, corregidas automáticamente por los tests , acorde a la siguiente tabla

| CRITERIO de EVALUACIÓN | PONDERACIÓN | INSTRUMENTOS de EVALUACIÓN|
|------------------------|-------------|-------------|
| a)                     |16 %       | Tareas 1,2 y 3      |
| b)                     |16 %       | Tareas 1,2 y 3      |
| c)                     |16 %       | Tareas 1,2 y 3      |
| d)                     |16 %       | Tareas 1,2 y 3      |
| e)                     |16 %       | Tareas 1,2 y 3     |
| f)                     |10 %       | Tareas 1,2 y 3      |
| g)                     |10 %       | Tareas 1,2 y 3      |
|                        |**100 %**    |             |



### **Tarea 1 - Servicios en Red (I)**
##### **Criterios a), b), c), d) e), f), g), h)**

Modifica el cliente FTP de ejemplo para que pueda conectarse a cualquier servidor FTP.
Haz que el usuario tenga que introducir el nombre del servidor FTP, el nombre de usuario y su clave, ya sea desde la línea de comandos o modificando la interfaz gráfica. 
La opción de la interfaz gráfica permitirá sacar el 10 en esta tarea, mientras que la opción por línea de comandos únicamente puntuará hasta 8 puntos.
```
Fecha de Entrega: 1/02/2023 - 14:45
```

### **Tarea 2 - Servicios en Red (II)**
##### **Criterios a), b), c), d) e), f), g), h)**

Analiza el código planteado en [UA4\tareas\dev_0\tarea_2](https://github.com/Lope-de-Vega-Test/psp-22-23/tree/main/UA4/tareas/dev_0/tarea_2)

Una vez tengas tu primera API básica completa aumenta su funcionalidad permitiendo que la API:

- Permita crear (CREATE) Personas en el depósito de datos.
- Permita leer (READ) Personas en el depósito de datos, utilizando números enteros como identificador.
- Permita actualizar (UPDATE) Personas en el depósito de datos.
- Permita eliminar (DELETE) Personas del depósito de datos.

Ejemplos de Peticiones
- READ - ​GET http://localhost:8080/api/greeting   <- Should return 200 OK
- READ - ​GET http://localhost:8080/api/bye   <- Should return 200 OK
- READ - ​GET http://localhost:8080/api/person?name=Ada   <- Should return 200 OK
- ​READ - GET http://localhost:8080/api/person?name=Paco  <- Should return 404 Not Found
- ​READ - GET http://localhost:8080/api/person?nane=Kevin <- Should return 400 Bad Request
- CREATE - POST http://localhost:8080/api/person?name=MANOLO&about=vaya movida gorda&birthYear=2033 <- Should return 200 OK
- CREATE - POST http://localhost:8080/api/person?nane=MANOLO&about=vaya movida gorda&birthYear=2033& <- Should return 400 Bad Request
- UPDATE - PUT http://localhost:8080/api/person?name=MANOLO&about=vaya movida no tan gorda&birthYear=2013 <- Should return 200 OK
- DELETE - DEL http://localhost:8080/api/person?name=MANOLO

Esta tarea se defenderá individualmente en clase el mismo día de la entrega.  Para la defensa de esta tarea deberás tener funcionando simultáneamente tu API y una librería de consultas en (Postman||RapidAPI) preparadas para esta finalidad, ojo, yo también las tendré.

Referencias:
- [HttpServer](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html)
- [HttpHandler](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpHandler.html)
- [HttpExchange](https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpExchange.html)
- [InetSocketAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetSocketAddress.html)



```
Fecha de Entrega: 15/02/2023 - 14:45
```

### **Tarea 3 - Servicios en Red (III)**
##### **Criterios a), b), c), d) e), f), g), h)**

```
Fecha de Entrega: A definir
```


## Recursos

- [Repositorio Asignatura](https://github.com/daniteleco/psp-22-23)
- Apuntes del Profesorado //TODO
- [Presentación Generación de Servicios en Red (I)](https://docs.google.com/presentation/d/e/2PACX-1vStejrBe5G2aUIB9myXg05S8Mg3TXgsAk0q1QVjzDTYUqPY7clejikwhocUblM0GGlK7cBYtfWJkhoY/pub?start=false&loop=false&delayms=60000)
- [Presentación Generación de Servicios en Red (II)](https://docs.google.com/presentation/d/e/2PACX-1vRYHckWhMqkAlDn3qv9MXf9hyoie-8bkhtUuVgiu4_TOHC0MxECXGZQvcLOJvu7rzrfuqtF_bV2Pusw/pub?start=false&loop=false&delayms=60000&slide=id.p)
- Bibliografía Recomendada
  - **Programación de servicios y procesos - Técnico Superior en DAM.** *Mª Jesús Ramos Martín. Editorial Garceta. 2ª Edición. 2018. ISBN: 978-84-1728-931-7.*

## Autor

Creado por [Daniel Pérez Rodríguez](https://twitter.com/daniteleco) e inspirado en el trabajo de [José Luis González Sánchez](https://github.com/joseluisgs/ProgServiciosProcesos-00-2021-2022)

## Contacto
- Email: [dperez@ceslopedevega.com](mailto:dperez@ceslopedevega.com)
>>>>>>> 66dadf1 (UA4 Assigment)
