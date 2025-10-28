# 🔧🌱Tools-API
Api Rest de gestión de herramientas y alquiler mediante tickets.\
Proyecto desarrollado para facilitar el monitoreo y la correcta gestión de herramientas o productos en general de una ferreteria, facilitando la búsqueda, control de stock y precios.\
ToolsApi permite al usuario alquilar herramientas, habiendo antes realizado el previo registro del cliente, generando un ticket con todos los datos necesarios.\
Esto con el propósito de facilitar a los clientes el poder acceder a diversos productos por un cierto periodo de tiempo.

## Principales Tecnologias usadas:
![Java](https://img.shields.io/badge/Java-red?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring_boot-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-blue.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-orange?style=for-the-badge&logo=postman&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-57E340?style=for-the-badge&logo=swagger&logoColor=black)
* [ver mas...](#dependencias)

## Indice 
- [Dependencias](#dependencias)
- [Base de datos](#base-de-datos)
- [Ejecucion del proyecto](#ejecución-del-proyecto)
- [Entidades](#entidades)
- [Ejecución de pruebas endpoints **POSTMAN**](#pruebas-de-endpoints-postman)

## Dependencias
* `spring-boot-starter-web`
* `spring-boot-starter-jpa`
* `spring-boot-starter-validation`
* `spring-boot-devtools`
* `mysql-connector-j`
* `lombok`
* `spring-boot-starter-test`
* `springdoc-openapi-starter-webmvc-ui`

## Base de datos
*Estructura de la base de datos relacional desarrollada con el gestor MySQL*

  <img width="897" height="683" alt="image" src="https://github.com/user-attachments/assets/3684080a-cb0e-42e0-8d15-baf2f0080a59" />


## Ejecución del proyecto

  **1. Requisitos previos:**

  - ☕ **Java 17**
  - 🐬 **MySQL Server** (versión recomendada 8.x)
  - 🔧 **Maven** o **Gradle**
  - 💡 IDE recomendado: IntelliJ IDEA o VS Code

  **2. Configurar la base de datos:** <br><br>
      - **2.1** Asegúrate de que MySQL esté corriendo \
      - **2.2** Abre MySQL Workbench y crea la base de datos. \
      - **2.3** Ejecuta el script que se encuentra en el proyecto: \
           -ruta →   `db/tool_db_v2.sql`
    
  **3. Configurar las variables de entorno:** <br><br>
     Crea un archivo `application-dev.properties` siguiendo estos pasos. \
     Ruta: `src/main/resource/application-dev.properties` con el siguiente contenido de ejemplo:
     
        #DatosDB
        spring.datasource.url=jdbc:mysql://localhost:3306/tools_db_v2?serverTimezone=America/Lima
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_clave
        
        #MANEJO_JPA
        spring.jpa.show-sql=true
        spring.jpa.hibernate.ddl-auto=validate
        spring.jpa.properties.hibernate.format_sql=true
     
        server.port=8080
        
        app.url.cross-origin=http://localhost:4200/
     
   En el `application.properties` principal indica el perfil activo:
     
        spring.profiles.active=dev
    
  **4. Ejecutar el proyecto:** 
  
  Desde tu IDE:
     
  - Abre el proyecto. 
  - Ejecuta la clase principal que contiene la anotación `@SpringBootApplication`.

  **5. Documentación de la API:**
   
   Para explorar los endpoints disponibles en swagger, accede a: 👉
        
         http://localhost:8080/swagger-ui/index.html
    

## Entidades
- **JPA** para el mapeo, permite interactuar con base de datos relacionales usando objetos de lenguaje java,
en lugar de escribir SQL directamente. Simplifica operaciones. Establece reglas y anotaciones que definen relaciones entre objetos y tablas. 
- **Hibernate** asocia las clases java a tablas en la base de datos y sus atributos a columnas de esas tablas, simplificando asi la persistencia
de datos, es el encargado de generar las sentencias SQL necesarias para las operaciones definidas, reduciendo la necesidad de codigo SQL manual.
Permite manipular la base de datos operando sobre objetos, utilizando la caracteristica de la programacion orientada a objetos.
- **Lombok** reduce código repetitivo, ayuda a un desarrollo mas rápido y limpio, pero se debe usar con cuidado, ya que puede permitir setear atributos que no deben cambiar.
  <hr>
* 📄 **Category** : Definir a que grupo pertenece cada herramienta, pueden ser herramientas de jardineria, construccion, etc. \
  Se establece la relacion `OneToMany` con herramientas con una lista para que al consultar por una categoria en especifico este tambien
  pueda traerme las herramientas relacionadas a esa categoria. No lo carga directamente al llamar gracias a que establecemos el tipo de
  carga `LAZY`, accedemos a las herramientas de esa categoria solo si consultamos por ello.

  <img width="534" height="694" alt="image" src="https://github.com/user-attachments/assets/5ad48c02-87a1-411d-820f-96d39724d823" />

* 📄 **Section** : Definir el lugar de almacenamiento físico, *ejemplo: Sala R* y en la descripcion se detalla exactamente donde esta, *ejemplo : Estante A1, caja color rojo*. 

  <img width="396" height="548" alt="image" src="https://github.com/user-attachments/assets/f138b303-f445-4b73-9348-61f2d0cc721b" />

  
* 📄 **Tool** : Define datos generales de la herramienta como nombre, descripción, imagen, sección y categoria, tambien contiene otros atributos
  escenciales para la logica de alquiler: *cantidad total, cantidad disponible, precio de alquiler y estado*.

  <img width="989" height="620" alt="image" src="https://github.com/user-attachments/assets/159449df-7d9f-402e-ab2e-3d6cb3a6183b" />
  <img width="969" height="562" alt="image" src="https://github.com/user-attachments/assets/bd83a811-26f2-44e3-bcba-fb654c0d2774" />

* 📄 **Customer** : Contiene la información del cliente necesaria para realizar un alquiler: dni, nombre completo, telefono, email y direccion.

  <img width="575" height="679" alt="image" src="https://github.com/user-attachments/assets/74cdcfce-2db7-45da-95e7-128d4c0c714a" />

* 📄 **Ticket** : Código unico de ticket, se establece una relacion `ManyToOne` porque muchos tickets puede pertenecer a un 'Customer', el ticket contiene
  tambien la fecha de alquiler, la fecha de retorno y si estado para saber si el ticket sigue activo.\
  Se tiene una relacion bidireccional, ya que al establecer la relacion en 'TicketTool'se puede acceder a la relacion mediante 'ticket' a traves
  de una lista en este caso, no se mapea en la base de datos. Solo se apoya en la relación ya definida.
  
  <img width="976" height="570" alt="image" src="https://github.com/user-attachments/assets/d67acfb2-7b1e-44bc-80c5-3274be42a03d" />
  <img width="960" height="352" alt="image" src="https://github.com/user-attachments/assets/3aa1f898-9673-4f23-90d9-25c3cd134d80" />
  
* 📄 **TicketTool** : Funciona como tabla intermedia entre `Ticket` y `Tool`, representando la relación muchos a muchos.
Utiliza una clave primaria compuesta (**TicketToolPK**) que garantiza la unicidad de la relación entre un ticket y una herramienta específica.
Además de los identificadores, esta tabla incluye información adicional relevante para el alquiler:

  - **quantity** → cantidad de unidades alquiladas.
  - **rentalCostUnit** → costo unitario de alquiler.
  - **totalCostRent** → costo total de la renta.

  <img width="971" height="659" alt="image" src="https://github.com/user-attachments/assets/8d0d15f2-2bc6-4dd0-a872-fd4bf3b070a3" />
  <img width="952" height="375" alt="image" src="https://github.com/user-attachments/assets/f9f47012-fc07-4151-80f3-df91ef06f18c" />

* 📄 **TicketToolPK** *-NO entidad-*: Clase auxiliar que no es una entidad.
Define la clave primaria compuesta usada en `TicketTool`, conformada por los id de `Ticket` y `Tool`.
Esta estructura permite que cada combinación (ticket-herramienta) sea única, asegurando la integridad y evitando registros duplicados en la tabla intermedia.

  <img width="571" height="225" alt="image" src="https://github.com/user-attachments/assets/d6df5b50-7f66-4e73-aef2-32377fe21516" />


* 📄 **ReturnDetails** : Entidad que almacena los datos de retorno de una herramienta, asociada a un `Ticket`.
Permite registrar el momento en que se devuelve el alquiler y otros detalles del proceso de devolución: *nombre, dni de la persona y nota indicando los detalles*.

  <img width="671" height="721" alt="image" src="https://github.com/user-attachments/assets/8eb4ff9a-1044-47cf-9f85-3a1bc9e8c6c8" />


## Pruebas de endpoints POSTMAN
### 1 - Herramienta 🔧
  - **🍃 1.1 Crear:** \
      ▫ **Método:** `POST` \
      ▫ **Ruta:** `/api/v2/tool/create` \
      ▫ **Body (Request):**
    
      ``` Request body
      {
        "name": "Atornillador 18V",
        "description": "Atornillador 18V TC-CD 18/35 LI y 1 batería 1.5Ah",
        "imageUrl": "http://link.com/img.jpg",
        "idSection": 1,
        "idCategory": 1,
        "totalQuantity": 100,
        "rentalPrice": 195.99
      }
      ```

    **✅ Creación exitosa:**
    
      <img width="1154" height="743" alt="image" src="https://github.com/user-attachments/assets/ede0eb2d-e65a-4e52-ba81-12eef92ff02f" />

    **🛑Excepción - Algún dato no fue enviado:**
    
      <img width="1159" height="604" alt="image" src="https://github.com/user-attachments/assets/b7371eb3-4c53-412d-91ef-7850c2ca0f19" />


  - **🍃 1.2 Actualizar:**
    
      ▫ **Método:** `PATCH` \
      ▫ **Ruta:** `/api/v2/tool/update/4` <h6>*cambiar por su ID correspondiente*</h6> 
      ▫ **Nota:** `Actualizacion parcial, no se requiere enviar todos los datos si no es necesario` \
      ▫ **Body (Request):**
    
      ``` Request body
      {
        "description": "Atornillador 18V TC-CD 18/35 LI y 1 batería 1.5Ah - Color Rojo"
      }
      ```
    
    **✅Actualizacón parcial con éxito:**
    
      <img width="1152" height="748" alt="image" src="https://github.com/user-attachments/assets/86be3ef9-5478-4350-b505-ca06661d14cc" />

    **🛑Excepción - Categoria o Sección que se quiere relacionar no existe:**

      <img width="1159" height="653" alt="image" src="https://github.com/user-attachments/assets/022c08ac-cfef-4372-b0a6-d5250794190c" />


    
  - **🍃 1.3 Listar:**
    
      ▫ **Método:** `GET` \
      ▫ **Ruta:** `/api/v2/tool/list?page=0&size=1` \
      ▫ **Nota:** `Listado con paginacion para evitar cargar todos los registros` 
    
    **✅ Mostrar la primera página con un total de 1 registros por página**

      <img width="1161" height="718" alt="image" src="https://github.com/user-attachments/assets/2d004fe5-0fcb-4f9f-9458-457f915f6c9d" />

  - **🍃 1.4 Obtener por ID:**
    
      ▫ **Método:** `GET` \
      ▫ **Ruta:** `/api/v2/tool/4` <h6>*cambiar por su ID correspondiente*</h6> 
      
    **✅ Obtener registro por ID exitoso:**

      <img width="1165" height="673" alt="image" src="https://github.com/user-attachments/assets/a76d32fc-3f93-48b4-84be-9a59f493cbff" />

    **🛑Excepción - Si no existe el registro por el ID proporcionado:**

      <img width="1152" height="516" alt="image" src="https://github.com/user-attachments/assets/2f4bb3cd-def2-4da2-b57e-5ecb8fc585b0" />


  - **🍃 1.5 Desactivar:**

      ▫ **Método:** `PATCH` \
      ▫ **Ruta:** `/api/v2/tool/deactivate/4` <h6>*cambiar por su ID correspondiente*</h6>
      ▫ **Nota:** `Este endpoint solo desactiva el recurso especificado por ID` 

    **✅ Desactivacion exitosa:**
    
      <img width="1156" height="458" alt="image" src="https://github.com/user-attachments/assets/19f89f47-02a5-4bbb-a8f7-49113cf35477" />

    
    **🛑Excepción - Si el recurso tiene tickets activos o esta siendo utilizado no se permite la desactivación:**

      <img width="1160" height="540" alt="image" src="https://github.com/user-attachments/assets/2c1351b6-7e83-468a-a98d-ff161be4355b" />


    
    
<!--### Categoria 📫
### Seccion 📌 -->
### 2 - Ticket 🎫
  - **🍃 2.1 Crear:**
    
      ▫ **Método:** `POST` \
      ▫ **Ruta:** `/api/v2/ticket/create` \
      ▫ **Body (Request):**
      ``` Request body
      {
          "dni":"33344455",
          "tools":[
              {
                  "rentedQuantity": 1,
                  "id_tool": 1
              }
          ]
      }
      ```
    
    **✅ Creación de ticket**
    
      <img width="1162" height="752" alt="image" src="https://github.com/user-attachments/assets/ad274e0a-bff4-4ab0-93ee-b34926187c18" />

    **🛑Excepción - Datos incorrectos**
    
      <img width="1173" height="595" alt="image" src="https://github.com/user-attachments/assets/7dd8f956-b00d-453a-aca7-e527b9bfb540" />
    
    **🛑Excepción - El cliente no fue registrado previamente**
    
      <img width="1160" height="578" alt="image" src="https://github.com/user-attachments/assets/b9c899ee-741d-4260-b765-55cd62b35182" />


  - **🍃 2.2 Obtener por código de ticket:**

      ▫ **Método:** `GET` \
      ▫ **Ruta:** `/api/v2/ticket/getTicketCode?ticketCode=TCK-24CCD5D7-DB7` <h6>*cambiar por su código correspondiente*</h6>

    **✅ Ticket obtenido por su codigo:**
    
      <img width="1167" height="751" alt="image" src="https://github.com/user-attachments/assets/51089d48-5c0e-447b-98fb-d3a424fee098" />

    **🛑Excepción - Ticket buscado no existe:**
    
      <img width="1165" height="616" alt="image" src="https://github.com/user-attachments/assets/5131248f-0277-4f95-9e97-3c9ebab9ab34" />


  - **🍃 2.3 Retornar herramienta y cerrar ticket:**

      ▫ **Método:** `POST` \
      ▫ **Ruta:** `/api/v2/ticket/returnToolAndCloseTicket` \
      ▫ **Body (Request):**
    
      ``` Request body
      {
        "idTicket": 9,
        "deliveredBy": {
          "namePerson": "Pedro Moreno",
          "dniPerson": "33344455"
        },
        "note": "Entrega en excelente estado, puntual."
      }
      ```
    **✅ Retorno exitoso:**

    *Se guarda en una nueva entidad llamada **returnDetails** con los datos del retorno y relacionado con el codigo del ticket* 
    
      <img width="1159" height="626" alt="image" src="https://github.com/user-attachments/assets/5b956244-4e44-42a8-b877-4526f00f8544" />

    **🛑Excepción - Se quiere cerrar un ticket NO existente:**
    
      <img width="1175" height="596" alt="image" src="https://github.com/user-attachments/assets/5e3650c2-8110-4041-8874-0f9acdf28c45" />

    **🛑Excepción - Se quiere cerrar un ticket ya cerrado:**
    
      <img width="1161" height="603" alt="image" src="https://github.com/user-attachments/assets/a78bf531-33dd-41f7-973a-74154959c7fd" />
<hr>

 ### 🚧 EN DESARROLLO 🚧
  - Se explicará más a detalle el código (las funcionalidades), dtos, mapper, como se manejaron las excepciones, pruebas unitarias y más.
  - Sigo mejorando el código, agregando nuevas funcionalidades.
  - Posteriormente microservicios.
  - **Gracias por ver.**


