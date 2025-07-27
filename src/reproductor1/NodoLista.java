/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reproductor1;
import java.net.*;
import java.util.*;
import javax.media.*;
import javax.swing.*;
import java.awt.Component;
import java.io.File;

/**
 *
 * @author Robert Moreira
 */

public class NodoLista {
    //URL urlArchivoMusica;
    public static LinkedList<URL> crearLista(LinkedList<URL> laLista) {//ok
        if(laLista==null)
        {    
            JOptionPane.showMessageDialog(null, "Acaba de crearse la lista");
            return new LinkedList<URL>();
        }
        
        int respuesta;
        respuesta=JOptionPane.showConfirmDialog(null, "La lista tiene elementos ¿Quiere borrarlos?");
        if(respuesta==0)//Quiere decir que el usuario presiona en "si"
         return null;
        return laLista;
    }
    

    
    /**
     * Configura el selector de archivos con filtros para formatos de audio
     */
    private static JFileChooser configurarSelectorArchivos() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        // Crear filtro para archivos de audio
        javax.swing.filechooser.FileFilter filtroAudio = new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return UtilidadesAudio.esFormatoSoportado(f.getName());
            }
            
            @Override
            public String getDescription() {
                return "Archivos de Audio (*.mp3, *.wav, *.au, *.aiff, *.m4a, *.ogg)";
            }
        };
        
        selectorArchivos.setFileFilter(filtroAudio);
        selectorArchivos.setAcceptAllFileFilterUsed(false);
        
        // Establecer directorio inicial
        File directorioInicial = new File("C:\\");
        if (directorioInicial.exists()) {
            selectorArchivos.setCurrentDirectory(directorioInicial);
        }
        
        return selectorArchivos;
    }
    
    public static LinkedList insercionCabecera(LinkedList lista)//OK
       {
           if (lista==null)
           {
               JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
               return lista;
           }
           
           // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
           JFileChooser selectorArchivos = configurarSelectorArchivos();
           int resultado = selectorArchivos.showOpenDialog(null);
           
           if (resultado == JFileChooser.APPROVE_OPTION) {
               File archivoSeleccionado = selectorArchivos.getSelectedFile();
               
               // Validación completa del archivo
               UtilidadesAudio.ValidacionResultado validacion = UtilidadesAudio.validarArchivoAudio(archivoSeleccionado);
               
               if (validacion.esValido) {
                   String rutaFormateada = cambiarRutaFormatoJMF(archivoSeleccionado.getAbsolutePath());
                   try {
                       URL url = new URL(rutaFormateada);
                       lista.addFirst(url);
                       UtilidadesAudio.mostrarExitoValidacion(archivoSeleccionado.getName());
                   } catch (Exception e) {
                       JOptionPane.showMessageDialog(null, "Error al convertir la ruta a URL: " + e.getMessage());
                   }
               } else {
                   UtilidadesAudio.mostrarErrorValidacion("Error de Validación", validacion.mensaje);
               }
           }
           
           return lista;
           
       }
    
    public static LinkedList insercionCima(LinkedList lista)//OK
       {
           if (lista==null)
           {
               JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
               return lista;
           }
           
           // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
           JFileChooser selectorArchivos = configurarSelectorArchivos();
           int resultado = selectorArchivos.showOpenDialog(null);
           
           if (resultado == JFileChooser.APPROVE_OPTION) {
               File archivoSeleccionado = selectorArchivos.getSelectedFile();
               
                               if (archivoSeleccionado != null && archivoSeleccionado.exists()) {
                    if (UtilidadesAudio.esFormatoSoportado(archivoSeleccionado.getName())) {
                        String rutaFormateada = cambiarRutaFormatoJMF(archivoSeleccionado.getAbsolutePath());
                        try {
                            URL url = new URL(rutaFormateada);
                            lista.addLast(url);
                            JOptionPane.showMessageDialog(null, "Elemento agregado con éxito: " + archivoSeleccionado.getName());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error al convertir la ruta a URL: " + e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato no soportado. Use archivos MP3, WAV, AU, AIFF, M4A o OGG.", 
                                                     "Error de formato", JOptionPane.ERROR_MESSAGE);
                    }
               } else {
                   JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo válido.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           
           return lista;
           
       }
    
    public static LinkedList insercionPorPosicion(LinkedList lista)//OK
       {
           if (lista==null)
           {
               JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
               return lista;
           }
           
           Integer eleccion;
           try {
               eleccion = Integer.parseInt(JOptionPane.showInputDialog
                        ("Número de elementos en la lista: "+lista.size()+"\n"+
                                    "¿En que posición desea introducir?"));
           } catch (NumberFormatException e) {
               JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", 
                                            "Error", JOptionPane.ERROR_MESSAGE);
               return lista;
           }
           
           if (eleccion < 1 || eleccion > lista.size() + 1) {
               JOptionPane.showMessageDialog(null, "Posición inválida. Debe estar entre 1 y " + (lista.size() + 1), 
                                            "Error", JOptionPane.ERROR_MESSAGE);
               return lista;
           }

           
           // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
           JFileChooser selectorArchivos = configurarSelectorArchivos();
           int resultado = selectorArchivos.showOpenDialog(null);
           
           if (resultado == JFileChooser.APPROVE_OPTION) {
               File archivoSeleccionado = selectorArchivos.getSelectedFile();
               
                               if (archivoSeleccionado != null && archivoSeleccionado.exists()) {
                    if (UtilidadesAudio.esFormatoSoportado(archivoSeleccionado.getName())) {
                        String rutaFormateada = cambiarRutaFormatoJMF(archivoSeleccionado.getAbsolutePath());
                        try {
                            URL url = new URL(rutaFormateada);
                            lista.add(eleccion-1, url);
                            JOptionPane.showMessageDialog(null, "Elemento agregado con éxito: " + archivoSeleccionado.getName());
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error al convertir la ruta a URL: " + e.getMessage());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato no soportado. Use archivos MP3, WAV, AU, AIFF, M4A o OGG.", 
                                                     "Error de formato", JOptionPane.ERROR_MESSAGE);
                    }
               } else {
                   JOptionPane.showMessageDialog(null, "No se seleccionó ningún archivo válido.", 
                                                "Error", JOptionPane.ERROR_MESSAGE);
               }
           }
           
           return lista;
           
       }
    
    public static String cambiarRutaFormatoJMF(String ruta)
    {
        //FORMATO DE ENTRADA: "C:\wav\eresparami.wav"
        //FORMATO DE SALIDA: "file:/C:/Pasionales/megustas.wav"
        ruta=ruta.replace("\\", "/");
        ruta="file:/"+ruta;
        return ruta;
        
    }
    
    public static Player reproducirLista(JFrame ventana,Player reproductor,LinkedList lista)
    {
        if (lista==null) {
            JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
            return reproductor;
        }
        
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null,"La lista está vacía. Agregue archivos primero.");
            return reproductor;
        }
        
        JOptionPane.showMessageDialog(null, "Número de elementos en la lista: " + lista.size());
        
        Integer eleccion;
        try {
            eleccion = Integer.parseInt(JOptionPane.showInputDialog("¿Cuál desea reproducir? (1-" + lista.size() + ")"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.", 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return reproductor;
        }
        
        if (eleccion < 1 || eleccion > lista.size()) {
            JOptionPane.showMessageDialog(null, "Posición inválida. Debe estar entre 1 y " + lista.size(), 
                                         "Error", JOptionPane.ERROR_MESSAGE);
            return reproductor;
        }
        
        try{
            if(reproductor!=null) {
                reproductor.stop();
                reproductor.close();
            }
            URL urlArchivo = (URL) lista.get(eleccion-1);
            reproductor = Manager.createRealizedPlayer(urlArchivo);
            // Mostrar información del archivo que se está reproduciendo
            String nombreArchivo = UtilidadesAudio.obtenerNombreArchivo(new File(urlArchivo.getPath()).getName());
            JOptionPane.showMessageDialog(null, "Reproduciendo: " + nombreArchivo, 
                                         "Reproduciendo", JOptionPane.INFORMATION_MESSAGE);
            reproductor.start();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al reproducir el archivo: " + e.getMessage(), 
                                         "Error de reproducción", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al abrir archivo: " + e.getMessage());
        }
        
        return reproductor;    
    }
    
    public static void pausarReproduccion(Player reproductor, LinkedList lista) {
        if (lista==null){
            JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
            return;
        }

        if (reproductor != null) {
            reproductor.stop();
        }
    }

    public static void reanudarReproduccion(Player reproductor, LinkedList lista) {
        if (lista == null) {
            JOptionPane.showMessageDialog(null, "Hey...se debe crear primero la lista..");
            return;
        }
        if (reproductor != null) {
            reproductor.start();
        }
    }
    
    public static void LlenarJlistConLista(JList elementos,LinkedList lista){
        if(lista!=null)
        {   
            DefaultListModel modelo=new DefaultListModel();
            elementos.setModel(modelo);
            modelo.removeAllElements();
            for(int i=0;i<lista.size();i++) {
                String ruta = lista.get(i).toString();
                String nombreArchivo = UtilidadesAudio.obtenerNombreArchivo(new File(((URL)lista.get(i)).getPath()).getName());
                modelo.addElement((i+1) + ". " + nombreArchivo);
            }
        }
    }
    
     public static void MirarSiListaVacia(LinkedList laLista){//Los libros lo llaman "cima"
        if (laLista==null)
            JOptionPane.showMessageDialog(null, "Flaco. La lista no estaba creada");
        else if (laLista.isEmpty())
            JOptionPane.showMessageDialog(null, "Flaco. La lista esta creada. pero no se le han guardado elementos");
        else 
            JOptionPane.showMessageDialog(null, "Flaco. Esta lista tiene " + laLista.size() + " elementos");
      
       
    }
    
}