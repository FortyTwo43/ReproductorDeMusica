# Guía de Usuario - Reproductor de Música

## 🎵 Introducción

Este reproductor de música te permite crear listas de reproducción y reproducir archivos de audio en múltiples formatos. Está diseñado para demostrar el uso de estructuras de datos en Java.

## 📋 Requisitos Previos

- **Java JDK 8 o superior** instalado en tu sistema
- **Archivos de audio** en formatos soportados (MP3, WAV, AU, AIFF, M4A, OGG)

## 🚀 Inicio Rápido

### Opción 1: Usando NetBeans (Recomendado)
1. Abre NetBeans IDE
2. Abre el proyecto `ReproductorDeMusica`
3. Presiona **F6** para ejecutar

### Opción 2: Usando Scripts (Windows)
1. Ejecuta `compilar.bat` para compilar el proyecto
2. Ejecuta `ejecutar.bat` para iniciar el reproductor

## 🎯 Funcionalidades Principales

### 1. Crear Lista de Reproducción
- **Botón**: "Crear lista"
- **Acción**: Inicializa una nueva lista vacía
- **Uso**: Siempre es el primer paso antes de agregar archivos

### 2. Agregar Archivos de Audio

#### Agregar al Inicio
- **Botón**: "Agregar al inicio"
- **Acción**: Inserta el archivo al principio de la lista
- **Uso**: Para archivos que quieres reproducir primero

#### Agregar al Final
- **Botón**: "Agregar al final"
- **Acción**: Añade el archivo al final de la lista
- **Uso**: Para archivos que quieres reproducir al final

#### Agregar por Posición
- **Botón**: "Agregar por posición"
- **Acción**: Inserta el archivo en una posición específica
- **Uso**: Para controlar el orden exacto de reproducción

### 3. Reproducir Música
- **Botón**: "Reproducir(Imprimir)"
- **Acción**: Reproduce el archivo seleccionado
- **Uso**: Selecciona el número del archivo que quieres reproducir

### 4. Ver Información

#### Estadísticas de la Lista
- **Botón**: "Estadísticas"
- **Acción**: Muestra resumen de la lista
- **Información**: Total de archivos, formatos, tamaño total

#### Información de Archivo
- **Acción**: Doble clic en cualquier archivo de la lista
- **Información**: Nombre, formato, duración, tamaño

#### Estado de la Lista
- **Botón**: "¿Lista vacia?"
- **Acción**: Verifica si la lista está creada y tiene elementos

## 📁 Formatos de Audio Soportados

| Formato | Extensión | Descripción |
|---------|-----------|-------------|
| MP3 | .mp3 | Formato de audio comprimido más común |
| WAV | .wav | Formato de audio sin comprimir |
| AU | .au | Formato de audio de Sun Microsystems |
| AIFF | .aiff | Formato de audio de Apple |
| M4A | .m4a | Formato de audio de Apple/iTunes |
| OGG | .ogg | Formato de audio libre y abierto |

## 🔧 Solución de Problemas

### Error: "Java no está instalado"
**Solución**: Instala Java JDK 8 o superior desde [Oracle](https://www.oracle.com/java/technologies/downloads/)

### Error: "Formato no soportado"
**Solución**: Asegúrate de que el archivo tenga una de las extensiones soportadas

### Error: "No se puede reproducir el archivo"
**Posibles causas**:
- El archivo está corrupto
- El archivo no existe en la ruta especificada
- Problemas con JMF (Java Media Framework)

### Error: "La lista no está creada"
**Solución**: Haz clic en "Crear lista" antes de agregar archivos

## 💡 Consejos de Uso

### Organización de Archivos
1. **Crea la lista** antes de agregar archivos
2. **Usa nombres descriptivos** para tus archivos de música
3. **Agrupa archivos similares** en la misma lista

### Reproducción Eficiente
1. **Agrega archivos al final** para mantener el orden cronológico
2. **Usa "Agregar por posición"** para insertar archivos específicos
3. **Revisa las estadísticas** para ver el resumen de tu lista

### Información de Archivos
1. **Doble clic** en cualquier archivo para ver información detallada
2. **Usa las estadísticas** para conocer el tamaño total de tu biblioteca
3. **Verifica el formato** antes de agregar archivos

## 🎵 Ejemplos de Uso

### Ejemplo 1: Crear una Lista de Música Clásica
1. Crear lista
2. Agregar al final: "Beethoven_Symphony_5.mp3"
3. Agregar al final: "Mozart_Requiem.wav"
4. Agregar al final: "Bach_Well_Tempered_Clavier.au"
5. Reproducir archivo 1

### Ejemplo 2: Crear una Lista de Música Pop
1. Crear lista
2. Agregar al inicio: "Latest_Hit.mp3"
3. Agregar por posición 2: "Favorite_Song.m4a"
4. Agregar al final: "Old_Classic.wav"
5. Ver estadísticas de la lista

### Ejemplo 3: Organizar por Género
1. Crear lista para Rock
2. Agregar múltiples archivos .mp3 de rock
3. Crear nueva lista para Jazz
4. Agregar archivos .wav de jazz
5. Comparar estadísticas entre listas

## 🔄 Funciones Avanzadas

### Validación Automática
- El reproductor verifica automáticamente los formatos de archivo
- Solo permite agregar archivos de audio válidos
- Muestra mensajes informativos para errores

### Información Detallada
- **Duración**: Tiempo de reproducción del archivo
- **Tamaño**: Espacio en disco ocupado
- **Formato**: Tipo de archivo de audio
- **Ruta**: Ubicación del archivo en el sistema

### Estadísticas Inteligentes
- **Conteo por formato**: Cuántos archivos de cada tipo
- **Tamaño total**: Espacio total ocupado por la lista
- **Resumen visual**: Información organizada y fácil de leer

## 📞 Soporte

Si encuentras problemas o tienes preguntas:

1. **Revisa esta guía** para soluciones comunes
2. **Verifica los requisitos** del sistema
3. **Prueba con archivos diferentes** para aislar el problema
4. **Consulta la documentación** en el README.md

---

*¡Disfruta tu música con el Reproductor de Estructuras de Datos!* 🎶 