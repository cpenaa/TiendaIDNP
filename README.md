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
└─ java/com/example/tiendaidnp/
│   ├─ data/
│   │   ├─ model/             # Este subpaquete contiene los modelos de datos, como la clase `UserProfile`.
│   │   ├─ datastore/         # Este subpaquete se utiliza para el almacenamiento de datos locales.
│   │   ├─ repository/        # Este subpaquete contiene repositorios, que se utilizan para abstraer las fuentes de datos.
│   ├─ ui/                    # Este paquete contiene las clases relacionadas con la interfaz de usuario.
│   │   ├─ screens/           # Pantallas de la app (Productos, Principal, etc.)
│   │   ├─ previews/          # Este subpaquete contiene vistas previas de Composable para desarrollo y pruebas.
│   │   ├─ viewmodel/         # Este subpaquete contiene los ViewModels, que contienen y administran datos relacionados con la interfaz de usuario.
│   │   ├─ components/        # Componentes reutilizables (ProductoItem, TopBar, BottomBar)
│   │   └─ navigation/        # AppNavigation.kt: Este subpaquete gestiona la navegación entre pantallas.
│   ├─ model/
│   │   └─ Producto.kt        # Data class Producto
│   ├─ theme/
│   │   ├─ Color.kt
│   │   ├─ Theme.kt
│   │   └─ Type.kt
│   └─ MainActivity.kt
res/
├─ drawable/                  # Imágenes y vectores (webp, xml)
└─ values/                     # Colores, themes, strings
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