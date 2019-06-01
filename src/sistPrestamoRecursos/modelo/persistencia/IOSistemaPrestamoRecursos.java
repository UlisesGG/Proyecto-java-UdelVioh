/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistPrestamoRecursos.modelo.Amigo;
import sistPrestamoRecursos.modelo.Recurso;


public class IOSistemaPrestamoRecursos {
        private static IOSistemaPrestamoRecursos instancia=null;
        
        
        private IOSistemaPrestamoRecursos(){
        }
        
        public static IOSistemaPrestamoRecursos getInstancia(){
            if(instancia==null){
                instancia=new IOSistemaPrestamoRecursos();
            }
            return instancia;
        }
        
        public void grabarAmigosSinPrestamos(Amigo [] amigo){
            ArrayList<Amigo> amikos=new ArrayList<>();
            for(int i=0; i<amigo.length; i++){
                amikos.add(amigo[i]);
            }
            try {
                FileOutputStream fos= new FileOutputStream("amigosSinPrestamos.obj");
                ObjectOutputStream out=new ObjectOutputStream(fos);
                out.writeObject(amikos);
                out.flush();
                out.close();
                JOptionPane.showMessageDialog(null, "Se han guardado los datos de amigos sin prestamos exitosamente");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Los datos de amigos sin prestamos no pudieron ser almacenados", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Los datos no pudieron ser almacenados", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
        public Amigo[] leerAmigosSinPrestamos(){
            ArrayList<Amigo> amikos=new ArrayList<>();
            try{
                FileInputStream fis=new FileInputStream("amigosSinPrestamos.obj");
                ObjectInputStream in= new ObjectInputStream(fis);
                amikos=(ArrayList) in.readObject();
                in.close();
                fis.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No existen datos de amigos sin prestamos almacenados almacenados. Se almacenaran datos al seleccionar Guardar todo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            } catch (ClassNotFoundException ex) {
               
            }
            Amigo [] amigos=new Amigo[amikos.size()];
            amigos=amikos.toArray(amigos);
            return amigos;
        }
        public void grabarRecursosYPrestamos(Recurso [] recurso){
            ArrayList<Recurso> recursos=new ArrayList<>();
            for(int i=0; i<recurso.length; i++){
                recursos.add(recurso[i]);
            }
            try{
                FileOutputStream fos=new FileOutputStream("RecursosYPrestamos.obj");
                ObjectOutputStream out=new ObjectOutputStream(fos);
                out.writeObject(recursos);
                out.flush();
                out.close();
                JOptionPane.showMessageDialog(null, "Se han guardado los datos de recursos y prestamos exitosamente");
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "Los datos de recursos y prestamos no pudieron ser almacenados", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        }
        public Recurso[] leerRecursosYPrestamos(){
            ArrayList<Recurso> recursos=new ArrayList<>();
            try{
                FileInputStream fis= new FileInputStream("RecursosYPrestamos.obj");
                ObjectInputStream in= new ObjectInputStream(fis);
                recursos=(ArrayList) in.readObject();
                in.close();
                fis.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null, "No existen datos de recursos y prestamos almacenados. Se almacenaran datos al seleccionar Guardar todo", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

            } catch (ClassNotFoundException ex) {

            }
            Recurso [] recurso=new Recurso[recursos.size()];
            recurso=recursos.toArray(recurso);
            return recurso;
        }
    }
