# TiendaIDNP

Este es un proyecto de Android para una aplicación de tienda online.

## Características Principales

*   **Gestión de Perfil de Usuario:** Permite a los usuarios crear y gestionar sus perfiles.
*   **Catálogo de Productos:** Muestra los productos de la tienda organizados por categorías y Filtrado.
*   **Navegación Intuitiva:** Navegación fluida entre las diferentes pantallas de la aplicación usando Navigation Compose.
*   **Interfaz Moderna:** Construida con Jetpack Compose y Material 3 para una experiencia de usuario moderna y atractiva.
*   **Soporte para Temas:** Incluye soporte para temas, permitiendo a los usuarios elegir entre un tema claro y oscuro.

## Estructura del proyecto

El proyecto sigue una arquitectura de IU declarativa moderna de Android con Jetpack Compose. La estructura del proyecto está organizada de la siguiente manera:

*   **`app/src/main/java/com/example/tiendaidnp`**: Este es el paquete principal de la aplicación.
    *   **`data/`**: Este paquete contiene clases relacionadas con los datos.
        *   **`model/`**: Este subpaquete contiene los modelos de datos, como la clase `UserProfile`.
        *   **`datastore/`**: Este subpaquete se utiliza para el almacenamiento de datos locales.
        *   **`repository/`**: Este subpaquete contiene repositorios, que se utilizan para abstraer las fuentes de datos.
    *   **`ui/`**: Este paquete contiene las clases relacionadas con la interfaz de usuario.
        *   **`screens/`**: Este subpaquete contiene las diferentes pantallas de la aplicación como Composables.
        *   **`previews/`**: Este subpaquete contiene vistas previas de Composable para desarrollo y pruebas.
        *   **`viewmodel/`**: Este subpaquete contiene los ViewModels, que contienen y administran datos relacionados con la interfaz de usuario.
        *   **`components/`**: Este subpaquete contiene componentes de IU reutilizables.
        *   **`navigation/`**: Este subpaquete gestiona la navegación entre pantallas.
    *   **`theme/`**: Este paquete contiene la información de temas de la aplicación.
        *   **`Color.kt`**: Define la paleta de colores.
        *   **`Theme.kt`**: Define el tema de la aplicación.
        *   **`Type.kt`**: Define la tipografía.
    *   **`MainActivity.kt`**: Este es el punto de entrada principal de la aplicación.

## Cómo construir

Puedes construir el proyecto usando Android Studio o ejecutando el siguiente comando en la raíz del proyecto:

```
./gradlew assembleDebug
```

## Cómo ejecutar

1. Clonar el repositorio:
   git clone https://github.com/cpenaa/TiendaIDNP.git
2. Abrir el proyecto en Android Studio
3. Compilar y ejecutar en un emulador o dispositivo físico (API 34)

---

## Dependencias principales

- Jetpack Compose
- Material3
- Navigation Compose

---

## Contribuciones

Este proyecto sirve como plantilla base de e-commerce. Mejoras posibles:
- Mejorar soporte responsivo y grid dinámico
- ...

---

## Integrantes del proyecto (En construcción)

- **Christian Peña** - Arquitectura, integración de datos y diseño responsivo
- **Shirley Chambi** - Desarrollo de UI y navegación con Jetpack Compose
- **Victor Quispe** - Soporte de tema, pruebas en distintos dispositivos y optimización de layouts
- **Angel Caceres** - Integración de DataStore y almacenamiento local

Cada integrante contribuyó en las distintas áreas del proyecto según su especialidad, colaborando en el diseño y funcionalidad de la aplicación.