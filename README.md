Conecta es un sistema de Bolsa de Trabajo desarrollado con Spring Boot, orientado a la gestión y vinculación entre oferentes y demandantes de empleo.
El proyecto busca ofrecer una plataforma digital unificada que facilite la interacción entre empresas y postulantes, optimizando los procesos de búsqueda, selección y comunicación.

Este trabajo práctico se enmarca dentro de la materia de Metodologías de Programación, aplicando estándares de documentación de requisitos (IEEE 830) y buenas prácticas de desarrollo.

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

Estado actual:
El sistema implementa las funcionalidades principales:

Registro y gestión de usuarios (empresas y candidatos).

Publicación y postulación a ofertas laborales.

Mensajería interna y notificaciones.

Filtros y búsquedas optimizadas.
