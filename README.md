Este es mi primer proyecto como backend developer utilizando Java con Maven.
El objetivo fue extender un código base existente, agregando nuevas funciones y mejorando la lógica para gestionar estudiantes junto con sus materias.

Tecnologias utilizadas:
Virutal Box
Lubuntu
Java 8+
Maven 
MySQL

Estructura
src/main/java/co/com/
│
├── co.com.bd/              # Conexión a la base de datos
├── co.com.model/           # Modelos (Estudiante, Materia, etc.)
├── co.com.manager/         # Lógica de negocio
└── co.com.ws.rest/         # Servicios REST (endpoints)

En este trabajo aprendi lo sigueinte:

-Uso de Maven para gestionar un proyecto backend.
-Creación y exposición de servicios REST en Java.
-Conexión a base de datos y consultas con JDBC.
-Optimización con INNER JOIN para mejorar eficiencia.
-Pruebas con Postman para validar endpoints.

Este proyecto representa mi primer acercamiento al desarrollo backend con Java, combinando teoría con práctica real.

Ejemplo para que puedas guiarte en las url:

Consultar todos los estudiantes
GET /servicesRest/WsEstudiante/ConsultarTodos

