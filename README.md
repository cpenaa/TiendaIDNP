# GamarraAQP - Tienda de Ropa

GamarraAQP es una app de ejemplo de e-commerce para venta de ropa, desarrollada en Android Studio usando Jetpack Compose y Material3. La app permite navegar entre pantallas, ver productos con imagen, precio y etiqueta de oferta, soporta modo claro y oscuro, y se adapta a distintos tamaños de pantalla y orientación.

---

## Características principales

- Pantalla principal con título, subtítulo, imagen de portada y botón de navegación
- Navegación entre pantallas usando Jetpack Compose Navigation
- Lista de productos con:
    - Imagen del producto
    - Nombre y precio
    - Badge "En oferta" cuando corresponda
    - Contenedores con borde y esquinas redondeadas
- Diseño responsivo para tablet y teléfono
- Soporte de modo claro y oscuro usando MaterialTheme
- Uso de Surface para consistencia de fondo entre pantallas
- Botones con iconos y texto alineado correctamente
- Tipografía y colores definidos en Theme.kt

---

## Estructura del proyecto

app/
└─ java/com/example/tiendaidnp/
│   ├─ ui/
│   │   ├─ screens/           # Pantallas de la app (Productos, Principal, etc.)
│   │   ├─ components/        # Componentes reutilizables (ProductoItem, TopBar, BottomBar)
│   │   └─ navigation/        # AppNavigation.kt
│   ├─ model/
│   │   └─ Producto.kt        # Data class Producto
│   └─ theme/
│       ├─ Color.kt
│       ├─ Theme.kt
│       └─ Type.kt
res/
├─ drawable/                  # Imágenes y vectores (webp, xml)
└─ values/                     # Colores, themes, strings

---

## Cómo ejecutar

1. Clonar el repositorio:
   git clone https://github.com/cpenaa/TiendaIDNP.git
2. Abrir el proyecto en Android Studio
3. Compilar y ejecutar en un emulador o dispositivo físico
4. Probar modo claro y oscuro
5. Probar distintas orientaciones y tamaños de pantalla

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
- **Nombre Apellido** - Desarrollo de UI y navegación con Jetpack Compose
- **Nombre Apellido** - Soporte de tema, pruebas en distintos dispositivos y optimización de layouts

Cada integrante contribuyó en las distintas áreas del proyecto según su especialidad, colaborando en el diseño y funcionalidad de la aplicación.