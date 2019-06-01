/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistPrestamoRecursos.vista.RecursoSimple;


public class CatalogoRecursos {
    private static CatalogoRecursos instancia=null;
    ArrayList <Recurso> recursos;
    private CatalogoRecursos(){
        recursos=new ArrayList<>();
    }
    public static CatalogoRecursos getInstancia(){
        if(instancia==null){
            instancia=new CatalogoRecursos();
        }
        return instancia;
    }
    public int getNroRecursos(){
        return recursos.size();
    }
    public Recurso getByPos(int pos){
        return recursos.get(pos);
    }
    public Recurso getByCod(int cod){
        for(int i=0; i<recursos.size(); i++){
            if(recursos.get(i).getCodigo()==cod){
                return recursos.get(i);
            }
        }
        return null;
    }
    public void add(Recurso recurso){
        if(!recursos.contains(recurso)){
            recursos.add(recurso);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe un recurso con el codigo --> "+ recurso.getCodigo(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }


    public void remove(Recurso recurso){
        if(recursos.contains(recurso)){
            recursos.remove(recurso);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe un recurso con el codigo --> "+ recurso.getCodigo(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

        }
    }
}
