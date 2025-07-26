# Reproductor de Música - Estructuras de Datos

Un reproductor de música desarrollado en Java que implementa estructuras de datos para la gestión de listas de reproducción.

## Características

### ✅ Funcionalidades Básicas
- **Crear lista de reproducción**: Inicializa una nueva lista vacía
- **Agregar archivos al inicio**: Inserta archivos al principio de la lista
- **Agregar archivos al final**: Añade archivos al final de la lista
- **Agregar por posición**: Inserta archivos en una posición específica
- **Reproducir archivos**: Reproduce archivos de audio seleccionados
- **Verificar estado de la lista**: Comprueba si la lista está vacía o creada

### 🎵 Formatos de Audio Soportados
- **MP3** (.mp3)
- **WAV** (.wav)
- **AU** (.au)
- **AIFF** (.aiff)
- **M4A** (.m4a)
- **OGG** (.ogg)

### 🆕 Nuevas Funcionalidades
- **Selector de archivos mejorado**: Filtro automático para formatos de audio soportados
- **Validación de formatos**: Verificación automática de archivos compatibles
- **Información de archivos**: Muestra duración, tamaño y formato de los archivos
- **Estadísticas de la lista**: Resumen con total de archivos, formatos y tamaño total
- **Doble clic en lista**: Muestra información detallada del archivo seleccionado
- **Manejo de errores mejorado**: Mensajes informativos para errores comunes

## Requisitos del Sistema

- **Java**: Versión 8 o superior
- **JMF (Java Media Framework)**: Incluido en el proyecto
- **Sistema Operativo**: Windows, macOS, Linux

## Instalación y Uso

### 1. Compilar el Proyecto
```bash
# En NetBeans
# Abrir el proyecto y presionar F11 para compilar

# O desde línea de comandos
javac -cp "JMF-2.1.1e/lib/*" src/reproductor1/*.java
```

### 2. Ejecutar la Aplicación
```bash
# En NetBeans
# Presionar F6 para ejecutar

# O desde línea de comandos
java -cp "JMF-2.1.1e/lib/*:src" reproductor1.Reproductor1
```

## Guía de Uso

### Crear una Lista de Reproducción
1. Hacer clic en **"Crear lista"**
2. Confirmar la creación de la lista

### Agregar Archivos de Audio
1. **Al inicio**: Hacer clic en **"Agregar al inicio"**
2. **Al final**: Hacer clic en **"Agregar al final"**
3. **Por posición**: Hacer clic en **"Agregar por posición"** e indicar la posición
4. Seleccionar archivos de audio desde el explorador de archivos

### Reproducir Música
1. Hacer clic en **"Reproducir(Imprimir)"**
2. Seleccionar el número del archivo a reproducir
3. El archivo comenzará a reproducirse automáticamente

### Ver Información
- **Estadísticas**: Hacer clic en **"Estadísticas"** para ver resumen de la lista
- **Información de archivo**: Hacer doble clic en cualquier archivo de la lista
- **Estado de la lista**: Hacer clic en **"¿Lista vacia?"**

## Estructura del Proyecto

```
ReproductorDeMusica/
├── src/reproductor1/
│   ├── Reproductor1.java          # Clase principal
│   ├── VentanaPrincipal.java      # Interfaz gráfica
│   ├── NodoLista.java             # Lógica de listas
│   └── UtilidadesAudio.java       # Utilidades de audio
├── JMF-2.1.1e/                    # Java Media Framework
├── lib/                           # Bibliotecas adicionales
└── nbproject/                     # Configuración de NetBeans
```

## Tecnologías Utilizadas

- **Java Swing**: Interfaz gráfica de usuario
- **Java Media Framework (JMF)**: Reproducción de medios
- **LinkedList**: Estructura de datos para la lista de reproducción
- **File I/O**: Manejo de archivos de audio

## Mejoras Implementadas

### Versión 2.0
- ✅ Soporte para múltiples formatos de audio (MP3, WAV, AU, AIFF, M4A, OGG)
- ✅ Selector de archivos con filtros automáticos
- ✅ Validación de formatos de archivo
- ✅ Información detallada de archivos (duración, tamaño, formato)
- ✅ Estadísticas de la lista de reproducción
- ✅ Interfaz mejorada con doble clic para información
- ✅ Manejo de errores más robusto
- ✅ Mensajes informativos en español

## Autor

**Robert Moreira** - Estructuras de Datos

## Licencia

Este proyecto es parte de un curso de Estructuras de Datos y está destinado únicamente para fines educativos.

---

*Desarrollado con Java y Java Media Framework*
