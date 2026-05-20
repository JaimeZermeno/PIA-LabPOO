**Práctica Web --- CRUD Tareas**

Spring Boot + MySQL + Tomcat

Alumno: Jaime Zermeno \| Materia: Laboratorio de Programación Orientada
a Objetos

# 1. ¿Qué hace la aplicación?

La aplicación es una lista de tareas escolares con la que puedes hacer
cinco operaciones: agregar tareas nuevas, ver todas las que existen,
buscar una en específico, modificar una y borrarla. No tiene pantalla
propia, sino que recibe y responde peticiones desde una herramienta
externa como PowerShell.

Cada tarea tiene tres campos: un título (nombre de la tarea), la materia
a la que pertenece, y su estatus (por ejemplo: pendiente o completada).

# 2. Arquitectura

El proyecto está dividido en 4 archivos, cada uno con una
responsabilidad distinta. Esta separación hace que el código sea más
ordenado y fácil de mantener: si algo falla o hay que cambiar algo,
sabes exactamente en qué archivo buscar.

  -----------------------------------------------------------------------------
  **Archivo**            **Qué es**    **Qué hace**
  ---------------------- ------------- ----------------------------------------
  Tarea.java             El modelo     Define cómo es una tarea: qué campos
                                       tiene (id, titulo, materia, estatus) y
                                       cómo se guarda en la base de datos.

  TareaRepository.java   El            Se encarga de hablar con la base de
                         repositorio   datos. Guardar, buscar y borrar tareas
                                       sin necesidad de escribir SQL a mano.

  TareaService.java      El servicio   Es el intermediario. El controlador le
                                       pide cosas y él se las pide al
                                       repositorio.

  TareaController.java   El            Es la puerta de entrada. Recibe las
                         controlador   peticiones del usuario, llama al
                                       servicio y devuelve la respuesta.
  -----------------------------------------------------------------------------

# 3. Configuración

## Base de datos

Crear la base de datos en MySQL:

> CREATE DATABASE practica_web;

## application.properties

> spring.application.name=crud-tareas
>
> spring.datasource.url=jdbc:mysql://localhost:3306/practica_web
>
> ?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
>
> spring.datasource.username=\_\_\_\_\_
>
> spring.datasource.password=\_\_\_\_\_\_
>
> spring.jpa.hibernate.ddl-auto=update
>
> spring.jpa.show-sql=true

**Cambia username y password según tu instalación local de MySQL.**

# 4. Cómo ejecutar

-   Abre el proyecto en IntelliJ: File → Open → selecciona la carpeta
    crud-tareas

-   Espera a que IntelliJ descargue las dependencias del proyecto
    (aparece una barra de progreso abajo)

-   Abre el archivo CrudTareasApplication.java y dale clic al botón ▶
    que aparece junto al nombre de la clase

-   Si el programa continua sin deternese significa que esta activo.

-   la aplicación está corriendo y lista para recibir peticiones en
    http://localhost:8080

# 5. Pruebas rápidas (PowerShell)

## Listar todas

> Invoke-RestMethod -Uri \"http://localhost:8080/api/tareas\" -Method
> Get

## Obtener por ID

> Invoke-RestMethod -Uri \"http://localhost:8080/api/tareas/1\" -Method
> Get

## Crear

> Invoke-RestMethod -Uri \"http://localhost:8080/api/tareas\" -Method
> Post \`
>
> -ContentType \"application/json\" \`
>
> -Body
> \'{\"titulo\":\"Reporte\",\"materia\":\"Web\",\"estatus\":\"pendiente\"}\'

## Actualizar

> Invoke-RestMethod -Uri \"http://localhost:8080/api/tareas/1\" -Method
> Put \`
>
> -ContentType \"application/json\" \`
>
> -Body \'{\"titulo\":\"Reporte
> v2\",\"materia\":\"Web\",\"estatus\":\"completada\"}\'

## Eliminar

> Invoke-RestMethod -Uri \"http://localhost:8080/api/tareas/1\" -Method
> Delete

# 6. Subir al repositorio

-   Crear repositorio en GitHub / GitLab / Bitbucket

-   git init → git remote add origin \<URL\>

-   git add . → git commit -m \"feat: crud tareas spring boot\"

-   git push -u origin main

# 7. Conclusiones

-   Usar Spring Boot nos ahorra tener que configurar todo a mano. El
    servidor (Tomcat) ya viene incluido, así que con solo correr el
    proyecto ya está funcionando en el puerto 8080.

-   Dividir el proyecto en partes (controlador, servicio, repositorio y
    modelo) hace que cada archivo tenga una sola responsabilidad. Esto
    hace el código más ordenado y más fácil de entender o modificar
    después.

-   No fue necesario escribir ninguna consulta SQL a mano. Spring se
    conecta a MySQL y crea la tabla de tareas automáticamente al correr
    la aplicación por primera vez.

-   Aprendí a construir una API que responde correctamente según lo que
    pase: si encuentra la tarea devuelve los datos, si no existe avisa
    con un error 404. Así funciona cualquier aplicación web real.
