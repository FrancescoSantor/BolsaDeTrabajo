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

