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
    
    public static LinkedList insercionCabecera(LinkedList lista)//OK
       {
           if (lista==null)
           {
               JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
               return lista;
           }
           
           // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
           JFileChooser selectorArchivos = new JFileChooser("C:\\wav\\");
           selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
           selectorArchivos.showOpenDialog(selectorArchivos);
           lista.addFirst(cambiarRutaFormatoJMF(selectorArchivos.getSelectedFile().getAbsolutePath()));
           JOptionPane.showMessageDialog(null, "Elemento agregado con éxito");
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
           JFileChooser selectorArchivos = new JFileChooser("C:\\wav\\");
           selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
           selectorArchivos.showOpenDialog(selectorArchivos);
           lista.addLast(cambiarRutaFormatoJMF(selectorArchivos.getSelectedFile().getAbsolutePath()));
           JOptionPane.showMessageDialog(null, "Elemento agregado con éxito");
           return lista;
           
       }
    
    public static LinkedList insercionPorPosicion(LinkedList lista)//OK
       {
           if (lista==null)
           {
               JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
               return lista;
           }
           
           Integer eleccion=Integer.parseInt(JOptionPane.showInputDialog
                        ("Número de elementos en la lista: "+lista.size()+"\n"+
                                    "¿En que posición desea introducir?"));

           
           // muestra el cuadro de diálogo de archivos, para que el usuario pueda elegir el archivo a abrir
           JFileChooser selectorArchivos = new JFileChooser("C:\\wav\\");
           selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
           selectorArchivos.showOpenDialog(selectorArchivos);
           lista.add(eleccion-1,cambiarRutaFormatoJMF(selectorArchivos.getSelectedFile().getAbsolutePath()));
           JOptionPane.showMessageDialog(null, "Elemento agregado con éxito");
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
        if (lista==null)
          JOptionPane.showMessageDialog(null,"Hey...se debe crear primero la lista..");
        else{
            JOptionPane.showMessageDialog(null, "Número de elementos en la lista: "
                                        +lista.size());
            Integer eleccion=Integer.parseInt(JOptionPane.showInputDialog("¿Cuál desea reproducir?"));
            try{
                if(reproductor!=null)
                reproductor.stop();
                reproductor=Manager.createRealizedPlayer
                        (new URL(lista.get(eleccion-1).toString()));
               
                
                /*Component controles=reproductor.getControlPanelComponent();
                if(controles!=null){
                    ventana.remove(controles);
                    //ventana.
                    ventana.add(controles);
                    controles.setLocation(10, 195);
                    controles.setSize(320,25);
                }*/
            
            reproductor.start();
  
                
            }catch(Exception e){
                   
                System.out.println("Error al abrir archivo");
                //return reproductor;
            }
            
        }
        
      return reproductor;    
    }     
    
    public static void LlenarJlistConLista(JList elementos,LinkedList lista){
        if(lista!=null)
        {   
            DefaultListModel modelo=new DefaultListModel();
            elementos.setModel(modelo);
            modelo.removeAllElements();
            for(int i=0;i<lista.size();i++)
                modelo.addElement(lista.get(i));
        }
    }
    
     public static void MirarSiListaVacia(LinkedList laLista){//Los libros lo llaman "cima"
        if (laLista==null)
            JOptionPane.showMessageDialog(null, "Flaco. La lista no estaba creada");
        else if (laLista.isEmpty())
            JOptionPane.showMessageDialog(null, "Flaco. La lista esta creada. pero no se le han guardado elementos");
        else 
            JOptionPane.showMessageDialog(null, "Flaco. Esta lista tiene algo");
      
       
    }
    
}