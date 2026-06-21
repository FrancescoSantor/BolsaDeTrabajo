WorkMatch es un sistema de Bolsa de Trabajo desarrollado con Spring Boot, orientado a la gestión y vinculación entre oferentes y demandantes de empleo.
El proyecto busca ofrecer una plataforma digital unificada que facilite la interacción entre empresas y postulantes, optimizando los procesos de búsqueda, selección y comunicación.

Este trabajo práctico se enmarca dentro de la materia de Metodologías de sistemas y Programacion III de la universidad UTN de Mar del Plata.
Se basa en la creacion de una API RESTful
----------------------------------------------------------------------------------------------------------------------------------------------
Problemáticas que aborda:

Acceso centralizado a oportunidades: evita la dispersión de ofertas laborales en múltiples portales.

Gestión de perfiles: permite a candidatos y empresas administrar su información de manera estructurada.

Optimización de postulaciones: reduce tiempos y mejora la eficiencia en la selección.

Comunicación directa: integra mensajería y notificaciones para mantener informados a los usuarios.

Reglas de negocio:

Roles diferenciados: control estricto entre candidatos y empresas.

Validación de ABMs: todas las altas, bajas y modificaciones deben cumplir reglas de consistencia.

Bajas lógicas: los registros no se eliminan físicamente, se marcan como inactivos.

Filtros rápidos: las búsquedas deben responder en menos de 2 segundos bajo carga normal.

Escalabilidad en notificaciones: el sistema soporta picos de uso en mensajería y alertas.

-----------------------------------------------------------------------------------------------------------------------------------------------
HERRAMIENTAS Y TECNOLOGIAS APLICADAS :

Lenguaje: Java 25+

Framework: Spring Boot 3.x

Capa Web: Spring Web (MVC, REST)

Persistencia: Spring Data JPA / Hibernate

Base de Datos: MySQL Workbench 8.0

Seguridad: Spring Security + JWT (JSON Web Tokens

Mapeo de Objetos: MapStruct / Patron Builder

Manejo de Dependencias: Maven

Validaciones: Spring Validation API (@Valid, @NotBlank, @Positive, etc.)

Documentación: OPEN API

Control de Versiones: GitHub

----------------------------------------------------------------------------------------------------------------------------------------------
Prerrequisitos:

Java Development Kit (JDK) → Versión 17 o superior.

Apache Maven → Versión 3.8 o superior.

MySQL Server → Versión 8.0 o superior.

IDE compatible → Se recomienda IntelliJ IDEA, aunque también puedes usar Eclipse o VS Code con las extensiones de Spring instaladas.

---------------------------------------------------------------------------------------------------------------------------------------------

# Reglas de Negocio del Sistema Bolsa de Trabajo

 Gestión de Usuarios

* Un usuario debe registrarse como Candidato o Empresa.
* El email de cada usuario debe ser único dentro del sistema.
* El username utilizado para autenticación debe ser único.
* La contraseña debe cumplir con una longitud mínima establecida por el sistema.
* Un usuario solo puede iniciar sesión si su cuenta se encuentra habilitada.

 Gestión de Empresas

* El CUIT de una empresa debe ser único.
* Una empresa debe completar sus datos obligatorios para poder operar dentro del sistema.
* Una empresa solo puede modificar o eliminar información perteneciente a su propio perfil.
* Una empresa puede seguir candidatos y guardar perfiles para futuras búsquedas.

 Gestión de Candidatos

* Un candidato debe poseer un perfil profesional.
* Un candidato solo puede modificar o eliminar información perteneciente a su propio perfil.
* Un candidato puede agregar, modificar y eliminar proyectos.
* Un candidato puede agregar, modificar y eliminar habilidades.
* Un candidato puede cargar y actualizar su experiencia laboral.
* Un candidato puede seguir empresas y guardar ofertas laborales.

 Gestión de Ofertas Laborales

* Solo las empresas pueden crear ofertas laborales.
* El salario mínimo no puede ser mayor que el salario máximo.
* Una empresa solo puede modificar o cerrar ofertas que le pertenecen.
* Un administrador puede administrar cualquier oferta laboral.
* Una oferta cerrada no puede seguir recibiendo postulaciones.

 Gestión de Postulaciones

* Solo los candidatos pueden postularse a ofertas laborales.
* Un candidato no puede postularse dos veces a la misma oferta.
* No se pueden realizar postulaciones sobre ofertas cerradas.
* Las empresas pueden visualizar las postulaciones recibidas en sus ofertas.

 Gestión de Mensajes

* Los mensajes deben poseer contenido válido.
* Un usuario no puede enviarse mensajes a sí mismo.
* Solo usuarios registrados pueden intercambiar mensajes.
* Solo el emisor puede eliminar un mensaje enviado.
* Los mensajes pueden marcarse como leídos.
* Los usuarios pueden filtrar y buscar mensajes.

 Gestión de Entrevistas

* Solo las empresas pueden crear entrevistas.
* Las entrevistas deben estar asociadas a una postulación existente.
* Un candidato puede aceptar o rechazar una entrevista.
* Las entrevistas deben registrar fecha y estado.

 Gestión de Publicaciones y Comentarios

* Solo las empresas pueden crear publicaciones.
* Los candidatos y empresas pueden visualizar publicaciones.
* Los comentarios deben estar asociados a una publicación existente.
* Solo el autor y admin puede modificar o eliminar sus comentarios.
* Los candidatos pueden indicar "Me gusta" sobre publicaciones.
* Un candidato no puede registrar múltiples likes sobre la misma publicación.

 Gestión de Seguimientos

* Los candidatos pueden seguir empresas.
* Las empresas pueden seguir candidatos.
* No se permiten seguimientos duplicados entre los mismos usuarios.

 Seguridad y Autorización

* Todo usuario autenticado accede mediante JWT.
* Las operaciones protegidas requieren autenticación válida.
* Los permisos se controlan mediante Roles y Permisos.
* Un candidato no puede ejecutar acciones exclusivas de empresas.
* Una empresa no puede ejecutar acciones exclusivas de candidatos.
* Los administradores poseen acceso total al sistema.
* Además del rol, el sistema valida la propiedad del recurso antes de permitir modificaciones o eliminaciones (por ejemplo: ofertas, mensajes, perfiles, publicaciones y comentarios).
