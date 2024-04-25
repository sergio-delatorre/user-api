# Users API

API RESTful de creación de Usuarios

## Diagrama
![Diagrama de Secuencia](src/main/resources/images/Diagrama%20de%20Secuencia.png)

## Requisitos
Para la ejecución de este proyecto se debe tener instalado:
- Java 11
- Maven

## Ejecución
### Aplicación
1. Descargar o clonar el repositorio
2. Navegar al directorio raíz del proyecto
```
$ cd ruta/al/directorio/raíz/del/proyecto
```
3. Ejecutar en consola el siguiente comando:
```
$ mvn spring-boot:run
```
4. URL para ver la documentación en Swagger:  
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Tests
Para ejecutar los tests usar el siguiente comando en la raiz del proyecto:
```
$ mvn test
```


## Endpoints
- `GET /users` Listar todos los usuarios 
- `GET /users/{id}` Retornar un usuario por id
- `POST /users/sign-up` Registrar nuevo usuario  
El endpoint de registro de usuario genera el JWT que, en la interfaz de Swagger, 
encontrará un botón "Authorize" donde puede ingresarlo y así poder consultar los otros endpoints.

## Base de datos
Se utiliza H2 como base de datos en memoria. Para acceder a la consola de H2:
1. Dirigirse a la URL [http://localhost:8080/h2-console](http://localhost:8080/h2-console).
2. Ingresar la cadena de conexión JDBC URL como `jdbc:h2:mem:usersdb`
3. Utilizar `sa` como usuario y `password` como contraseña (valores del application.properties)

