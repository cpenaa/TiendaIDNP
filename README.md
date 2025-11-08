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

```
app/src/main/
├─ java/com/example/tiendaidnp/
│   ├─ data/
│   │   ├─ model/      # Modelos de datos (Product, UserProfile).
│   │   ├─ repository/ # Repositorios para abstraer las fuentes de datos.
│   │   └─ datastore/  # Almacenamiento de datos locales.
│   ├─ ui/
│   │   ├─ components/ # Componentes de UI reutilizables (items de producto, barras de navegación).
│   │   ├─ navigation/ # Gestiona la navegación entre pantallas.
│   │   ├─ screens/    # Composables para cada pantalla de la app (Home, Profile, etc.).
│   │   ├─ theme/      # Tema de la aplicación (Color, Theme, Type).
│   │   ├─ viewmodel/  # ViewModels que exponen el estado a la UI.
│   │   └─ previews/   # Vistas previas para Composables.
│   └─ MainActivity.kt # Punto de entrada principal de la UI.
└─ res/
   ├─ drawable/       # Imágenes, íconos y otros recursos gráficos.
   └─ values/         # Archivos de recursos como strings, colors y themes.
```

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
