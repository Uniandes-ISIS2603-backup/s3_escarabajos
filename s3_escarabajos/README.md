# Ejemplo REST Cities básico:

Ejemplo de un servicio REST implementado usando JAX-RS. 
En este ejemplo no se conecta los servicios de los recursos con la lógica. EL propósito es ilustrar las signaturas de los servicios REST y su documentación. 

## Estructura del proyecto

El proyecto sigue la estructura de los proyectos Maven. 
Por lo tanto, el código fuente Java está ubicado en la carpeta `src/main/java` dentro del proyecto.
El código de la página web está ubicado en la carpeta `src/main/webapp`. 

Para entender el código fuente del ejemplo, es necesario revisar los siguientes archivos:

```

│   README.md
│
└───src/main/java
    ├───co.edu.uniandes.csw.cities.resources
    │   │   RestConfig.java
    │   │   CityResource.java
    │
    ├───co.edu.uniandes.csw.cities.dtos
    │   │   CityDTO.java
    │	│   CityDetailDTO.java
    │   
    └───co.edu.uniandes.csw.cities.mappers
        │   BusinessLogicExceptionMapper.java        
```

| Clase | Descripción |
| ----- | ----------- |
| `RestConfig.java` | Indica que la aplicación expone recursos REST. Solo se requiere uno en la aplicación. Especifica la ruta `/api` como prefijo para los recursos REST |
| `CityResource.java` | Define el recurso con la ruta `/api/cities`. Contiene métodos para procesar las peticiones GET /api/cities y POST de acuerdo con el API definido |
| `CityDTO.java` | Define los datos que se transfiere entre el cliente y el servidor. Como se usa como tipo de retorno en los métodos de `CityResource`, JAX-RS convierte automáticamente de JSON a esta clase y de esta clase a JSON.  |
| `BusinessLogicExceptionMapper.java` | Convertidor de la excepción a mensajes REST. |


## Documentación del API

### Entidad City

La comunicación entre el cliente y el servidor se realiza intercambiando objetos JSON que siguen el siguiente formato:

```javascript
{
    "id" : 1,     /* Tipo Long */
    "name" : ''    /* Tipo String */
}
```

Si se solicta la servidor una lista de ciudades, el servidor retorna un arreglo de esos objetos siguiendo el siguiente formato: 

 ```javascript
[ 
  {
    "id" : 1,     /* Tipo Long */
    "name" : ''    /* Tipo String */
  }, {
    "id" : 2,     /* Tipo Long */
    "name" : ''    /* Tipo String */
  } /* ... otras ciudades */   
]
```

### Servicios REST

Al ejecutarlo en su propia máquina, el recurso REST estará disponible en:
*  `http://localhost:8080/cities-web/api/cities` 

La descripción del API REST se presenta a continuación:

Método|URI|Acción|Parámetros|Cuerpo|Retorno
:--:|:--:|:--:|:--:|:--:|:--:
**GET**|/cities|Lista los registros de City (READ)|||Colección de registros de City 
**GET**|/cities/*:id*|Obtener los atributos de una instancia de City (READ)|**@PathParam id**: Identificador del registro||Atributos de la instancia de City
**POST**|/cities|Crear una nueva instancia de la entidad City (CREATE)||Atributos de la instancia de City a crear|Instancia de City creada, incluyendo su nuevo ID
**PUT**|/cities/*:id*|Actualiza una instancia de la entidad City (UPDATE)|**@PathParam id**: Identificador del registro|Objeto JSON de City|Instancia de City actualizada
**DELETE**|/cities/*:id*|Borra instancia de City en el servidor (DELETE)|**@PathParam id**: Identificador del registro||



## Ejecutando y probando el proyecto

El proyecto se ejecuta como un proyecto web tradicional. 
En Netbeans basta con ejecutar "Clean and Build" en el proyecto y luego usar la opción de "Run".

Es posible usar [Postman](http://www.getpostman.com/) para probar el servicio REST.

| Ejemplo | Comando |
| ------- | ------- |
| Obtener las ciudades | GET http://localhost:8080/cities-web/api/cities |
| Agregar una ciudad   | POST http://localhost:8080/cities-web/api/cities  incluyendo en la petición una ciudad. Por ejemplo, es posible usar `{"name": "barranquilla" }` |
