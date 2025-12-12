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
app/
└── src/
    └── main/
        ├── java/com/example/tiendaidnp/
        │   ├── data/
        │   │   ├── db/
        │   │   │   ├── dao/
        │   │   │   └── entities/
        │   │   │       └── ProductDB.kt   # Entidad de Room para la tabla de productos.
        │   │   ├── datastore/             # Para almacenamiento simple con DataStore.
        │   │   ├── model/
        │   │   └── repository/            # Repositorios que abstraen el origen de los datos.
        │   │
        │   ├── ui/
        │   │   ├── components/            # Componentes de UI reutilizables.
        │   │   │   └── buttons/
        │   │   ├── navigation/
        │   │   │   └── AppNavigation.kt   # Grafo de navegación y rutas de la app.
        │   │   ├── previews/
        │   │   ├── screens/               # Composables para cada pantalla.
        │   │   │   ├── admin/
        │   │   │   ├── cart/
        │   │   │   ├── catalog/
        │   │   │   ├── profile/
        │   │   │   └── PantallaPrincipal.kt
        │   │   │
        │   │   ├── theme/                 # Tema personalizado para la UI.
        │   │   └── viewmodel/             # ViewModels que gestionan la lógica y el estado.
        │   │
        │   ├── utils/                     # Módulos de inyección de dependencias (Hilt/Koin).
        │   │
        │   └── MainActivity.kt            # Actividad principal y punto de entrada de la UI.
        │
        └── res/
            ├── drawable/                  # Iconos, imágenes y otros recursos gráficos.
            └── values/
                ├── strings.xml            # Textos de la aplicación.
                ├── colors.xml             # Paleta de colores.
                └── themes.xml             # Estilos y temas de la aplicación.
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


## Integrantes del proyecto

- **Christian Peña** - Arquitectura, Integración de datos, Estandarización de componentes y tema, Personalización
- **Shirley Chambi** - Desarrollo de UI y navegación con Jetpack Compose
- **Victor Quispe** - Soporte de tema, pruebas en distintos dispositivos y optimización de layouts
- **Angel Caceres** - Integración de DataStore y almacenamiento local

Cada integrante contribuyó en las distintas áreas del proyecto según su especialidad, colaborando en el diseño y funcionalidad de la aplicación.