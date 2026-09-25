AgendaURG
AgendaURG es una aplicación móvil desarrollada nativamente en Android utilizando Kotlin, diseñada para cumplir con los requerimientos académicos de una rúbrica universitaria de desarrollo móvil. La app combina una interfaz moderna basada en Material Design con componentes esenciales de Android (Activities, Fragments, ViewModels y SQLite).
---
Características Principales
Arquitectura Modular (1 Activity + 2 Fragments):
Una única `MainActivity` que actúa como contenedor y anfitrión.
Dos pestañas intercambiables: Tareas y Contactos.
Almacenamiento Local (SQLite):
Base de datos local (`AgendaURG.db`) implementada mediante `SQLiteOpenHelper`.
Soporte para tablas independientes de Tareas (Nombre y Descripción) y Contactos (Nombre y Teléfono).
Interfaz Dinámica y Material Design:
FloatingActionButton (FAB): Botón flotante inteligente que despliega un diálogo (`AlertDialog`) adaptativo para registrar elementos nuevos según la pestaña activa.
Toolbar y Snackbar: Componentes visuales de Material Design para la navegación superior y notificaciones flotantes de éxito.
Navegación e Intents:
Intent Explícito: Envía datos dinámicos (`putExtra`) desde la lista de tareas hacia `DetailActivity` para visualizar información ampliada.
---
Tecnologías y Componentes Técnicos
Lenguaje: Kotlin
Entorno de desarrollo: Android Studio
Patrón de Arquitectura: MVVM (Model-View-ViewModel) básico con `SharedViewModel`.
Persistencia: SQLite (`SQLiteDatabase` y `SQLiteOpenHelper`).
UI: XML Layouts, ConstraintLayout, CoordinatorLayout, Material Components (FAB, Toolbar).
---
Estructura del Código Fuente
`MainActivity.kt`: Activity anfitriona que gestiona la barra de herramientas, la conmutación de fragmentos, el ciclo de vida y la lógica del botón flotante dinámico (`FAB`).
`TaskFragment.kt`: Fragmento encargado de listar las tareas almacenadas y disparar el Intent Explícito hacia el detalle.
`ContactFragment.kt`: Fragmento que administra y muestra el directorio de contactos con formato tabular.
`DetailActivity.kt`: Actividad secundaria que recibe y muestra la información detallada mediante `putExtra`.
`DatabaseHelper.kt`: Gestor de la base de datos SQLite encargado de la creación de tablas, inserción y consultas.
`SharedViewModel.kt`: ViewModel compartido para la comunicación de estados entre componentes.
---
Instrucciones de Ejecución
Abre este proyecto en Android Studio.
Sincroniza las dependencias de Gradle (`Sync Project with Gradle Files`).
Asegúrate de que el emulador esté configurado.
Ejecuta la aplicación presionando el botón Run ▶.
