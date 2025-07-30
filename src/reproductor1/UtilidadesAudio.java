/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.Time;
import javax.swing.JOptionPane;

/**
 * Clase utilitaria para manejar funcionalidades de audio
 * @author Robert Moreira
 */
public class UtilidadesAudio {
    
    // Formatos de audio soportados (sin MP3)
    public static final String[] FORMATOS_SOPORTADOS = {".wav", ".au", ".aiff", ".m4a", ".ogg"};
    
    // Tamaño mínimo para un archivo de audio válido (en bytes)
    private static final long TAMANO_MINIMO = 1024; // 1 KB
    
    // Firmas de archivos de audio (magic numbers)
    private static final byte[] MP3_SIGNATURE = {(byte)0xFF, (byte)0xFB};
    private static final byte[] WAV_SIGNATURE = {'R', 'I', 'F', 'F'};
    private static final byte[] AU_SIGNATURE = {'.', 's', 'n', 'd'};
    private static final byte[] AIFF_SIGNATURE = {'F', 'O', 'R', 'M'};
    private static final byte[] M4A_SIGNATURE = {'f', 't', 'y', 'p'};
    private static final byte[] OGG_SIGNATURE = {'O', 'g', 'g', 'S'};
    
    /**
     * Verifica si el archivo tiene un formato de audio soportado
     */
    public static boolean esFormatoSoportado(String nombreArchivo) {
        if (nombreArchivo == null) return false;
        String nombreLower = nombreArchivo.toLowerCase();
        for (String formato : FORMATOS_SOPORTADOS) {
            if (nombreLower.endsWith(formato)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validación completa de un archivo de audio
     * Verifica extensión, tamaño y contenido del archivo
     */
    public static ValidacionResultado validarArchivoAudio(File archivo) {
        ValidacionResultado resultado = new ValidacionResultado();
        
        // Verificar que el archivo existe
        if (archivo == null || !archivo.exists()) {
            resultado.esValido = false;
            resultado.mensaje = "El archivo no existe o no se puede acceder.";
            return resultado;
        }
        
        // Verificar que es un archivo (no directorio)
        if (!archivo.isFile()) {
            resultado.esValido = false;
            resultado.mensaje = "El elemento seleccionado no es un archivo.";
            return resultado;
        }
        
        // Verificar extensión
        String nombreArchivo = archivo.getName();
        if (!esFormatoSoportado(nombreArchivo)) {
            resultado.esValido = false;
            resultado.mensaje = "Formato no soportado. Formatos permitidos: WAV, AU, AIFF, M4A, OGG.";
            return resultado;
        }
        
        // Verificar tamaño mínimo
        if (archivo.length() < TAMANO_MINIMO) {
            resultado.esValido = false;
            resultado.mensaje = "El archivo es demasiado pequeño para ser un archivo de audio válido.";
            return resultado;
        }
        
        // Verificar que se puede leer
        if (!archivo.canRead()) {
            resultado.esValido = false;
            resultado.mensaje = "No se puede leer el archivo. Verifique los permisos.";
            return resultado;
        }
        
        // Si pasa todas las validaciones
        resultado.esValido = true;
        resultado.mensaje = "Archivo válido: " + nombreArchivo;
        return resultado;
    }
    
    /**
     * Verifica la firma del archivo (magic numbers) para validar que es realmente un archivo de audio
     */
    private static boolean verificarFirmaArchivo(File archivo) {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] buffer = new byte[12]; // Suficiente para leer las firmas
            int bytesLeidos = fis.read(buffer);
            
            if (bytesLeidos < 4) return false;
            
            String nombreArchivo = archivo.getName().toLowerCase();
            
            // Verificar según el formato
            if (nombreArchivo.endsWith(".mp3")) {
                return verificarFirma(buffer, MP3_SIGNATURE, 0);
            } else if (nombreArchivo.endsWith(".wav")) {
                return verificarFirma(buffer, WAV_SIGNATURE, 0);
            } else if (nombreArchivo.endsWith(".au")) {
                return verificarFirma(buffer, AU_SIGNATURE, 0);
            } else if (nombreArchivo.endsWith(".aiff")) {
                return verificarFirma(buffer, AIFF_SIGNATURE, 0);
            } else if (nombreArchivo.endsWith(".m4a")) {
                // M4A puede tener la firma en diferentes posiciones
                return verificarFirma(buffer, M4A_SIGNATURE, 4) || verificarFirma(buffer, M4A_SIGNATURE, 8);
            } else if (nombreArchivo.endsWith(".ogg")) {
                return verificarFirma(buffer, OGG_SIGNATURE, 0);
            }
            
            // Si no reconoce el formato, asumir que es válido
            return true;
            
        } catch (IOException e) {
            System.out.println("Error al verificar firma del archivo: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Verifica si el buffer contiene la firma especificada en la posición dada
     */
    private static boolean verificarFirma(byte[] buffer, byte[] firma, int offset) {
        if (buffer.length < offset + firma.length) return false;
        
        for (int i = 0; i < firma.length; i++) {
            if (buffer[offset + i] != firma[i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Muestra un diálogo de error con información detallada
     */
    public static void mostrarErrorValidacion(String titulo, String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Muestra un diálogo de éxito con información del archivo
     */
    public static void mostrarExitoValidacion(String nombreArchivo) {
        String mensaje = "Archivo agregado exitosamente:\n" + nombreArchivo;
        JOptionPane.showMessageDialog(null, mensaje, "Archivo Válido", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Obtiene la duración de un archivo de audio en formato legible
     */
    public static String obtenerDuracionArchivo(String rutaArchivo) {
        try {
            Player player = Manager.createPlayer(new MediaLocator(rutaArchivo));
            player.realize();
            
            Time duracion = player.getDuration();
            if (duracion != null) {
                long duracionMs = duracion.getNanoseconds() / 1000000; // Convertir a milisegundos
                return formatearDuracion(duracionMs);
            }
            
            player.close();
        } catch (Exception e) {
            System.out.println("Error al obtener duración: " + e.getMessage());
        }
        
        return "Duración desconocida";
    }
    
    /**
     * Formatea la duración en milisegundos a formato MM:SS
     */
    private static String formatearDuracion(long duracionMs) {
        long segundos = duracionMs / 1000;
        long minutos = segundos / 60;
        segundos = segundos % 60;
        
        return String.format("%02d:%02d", minutos, segundos);
    }
    
    /**
     * Obtiene el tamaño del archivo en formato legible
     */
    public static String obtenerTamañoArchivo(String rutaArchivo) {
        try {
            // Remover el prefijo "file:/" si existe
            String rutaLocal = rutaArchivo;
            if (rutaLocal.startsWith("file:/")) {
                rutaLocal = rutaLocal.substring(6);
            }
            
            File archivo = new File(rutaLocal);
            if (archivo.exists()) {
                long bytes = archivo.length();
                return formatearTamaño(bytes);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener tamaño: " + e.getMessage());
        }
        
        return "Tamaño desconocido";
    }
    
    /**
     * Formatea el tamaño en bytes a formato legible (KB, MB, GB)
     */
    private static String formatearTamaño(long bytes) {
        if (bytes < 1024) {
            return bytes + " B";
        } else if (bytes < 1024 * 1024) {
            return String.format("%.1f KB", bytes / 1024.0);
        } else if (bytes < 1024 * 1024 * 1024) {
            return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
        } else {
            return String.format("%.1f GB", bytes / (1024.0 * 1024.0 * 1024.0));
        }
    }
    
    /**
     * Muestra información detallada de un archivo de audio
     */
    public static void mostrarInformacionArchivo(String rutaArchivo) {
        try {
            String nombreArchivo = obtenerNombreArchivo(rutaArchivo);
            String duracion = obtenerDuracionArchivo(rutaArchivo);
            String tamaño = obtenerTamañoArchivo(rutaArchivo);
            String formato = obtenerFormatoArchivo(nombreArchivo);
            
            String mensaje = "Información del archivo:\n\n" +
                           "Nombre: " + nombreArchivo + "\n" +
                           "Formato: " + formato + "\n" +
                           "Duración: " + duracion + "\n" +
                           "Tamaño: " + tamaño;
            
            JOptionPane.showMessageDialog(null, mensaje, "Información del archivo", 
                                         JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener información del archivo: " + e.getMessage(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Extrae el nombre del archivo de la ruta completa
     */
    public static String obtenerNombreArchivo(String ruta) {
        if (ruta == null) return "Archivo desconocido";
        
        // Remover el prefijo "file:/"
        if (ruta.startsWith("file:/")) {
            ruta = ruta.substring(6);
        }
        
        // Obtener solo el nombre del archivo
        int ultimaBarra = ruta.lastIndexOf('/');
        if (ultimaBarra != -1) {
            return ruta.substring(ultimaBarra + 1);
        }
        
        return ruta;
    }
    
    /**
     * Obtiene el formato del archivo basado en su extensión
     */
    public static String obtenerFormatoArchivo(String nombreArchivo) {
        if (nombreArchivo == null) return "Desconocido";
        
        int punto = nombreArchivo.lastIndexOf('.');
        if (punto != -1) {
            return nombreArchivo.substring(punto + 1).toUpperCase();
        }
        
        return "Sin extensión";
    }
    
    /**
     * Verifica si un archivo existe y es accesible
     */
    public static boolean archivoExiste(String rutaArchivo) {
        try {
            String rutaLocal = rutaArchivo;
            if (rutaLocal.startsWith("file:/")) {
                rutaLocal = rutaLocal.substring(6);
            }
            
            File archivo = new File(rutaLocal);
            return archivo.exists() && archivo.canRead();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Obtiene estadísticas de la lista de reproducción
     */
    public static String obtenerEstadisticasLista(java.util.LinkedList lista) {
        if (lista == null || lista.isEmpty()) {
            return "Lista vacía";
        }
        
        int totalArchivos = lista.size();
        long tamañoTotal = 0;
        int archivosMP3 = 0;
        int archivosWAV = 0;
        int otrosFormatos = 0;
        
        for (Object item : lista) {
            String ruta = item.toString();
            String nombreArchivo = obtenerNombreArchivo(ruta);
            
            // Contar formatos
            String formato = obtenerFormatoArchivo(nombreArchivo).toLowerCase();
            if (formato.equals("mp3")) {
                archivosMP3++;
            } else if (formato.equals("wav")) {
                archivosWAV++;
            } else {
                otrosFormatos++;
            }
            
            // Calcular tamaño total
            String rutaLocal = ruta;
            if (rutaLocal.startsWith("file:/")) {
                rutaLocal = rutaLocal.substring(6);
            }
            File archivo = new File(rutaLocal);
            if (archivo.exists()) {
                tamañoTotal += archivo.length();
            }
        }
        
        return String.format("Total archivos: %d\n" +
                           "MP3: %d | WAV: %d | Otros: %d\n" +
                           "Tamaño total: %s", 
                           totalArchivos, archivosMP3, archivosWAV, otrosFormatos, 
                           formatearTamaño(tamañoTotal));
    }
    
    /**
     * Clase para almacenar el resultado de la validación
     */
    public static class ValidacionResultado {
        public boolean esValido;
        public String mensaje;
        
        public ValidacionResultado() {
            this.esValido = false;
            this.mensaje = "";
        }
    }
} 