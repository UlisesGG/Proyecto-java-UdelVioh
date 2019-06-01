/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class AgendaAmigos {
    private static AgendaAmigos instancia=null;
    private ArrayList<Amigo> agenditaAmiguitos;
    
    private AgendaAmigos(){
        agenditaAmiguitos=new ArrayList<>();
    }
    public static AgendaAmigos getInstancia(){
        if(instancia==null){
            instancia=new AgendaAmigos();
        }
        return instancia;
    }
    public int getNroAmigos(){
        return agenditaAmiguitos.size();
    }
    public Amigo getByPos(int pos){
        return agenditaAmiguitos.get(pos);
    }
    public Amigo getByRut(String rut){
        for (int i=0; i<agenditaAmiguitos.size(); i++) {
            if(agenditaAmiguitos.get(i).getRut().equals(rut)){
                return agenditaAmiguitos.get(i);
            }
        }
        return null;
    }

    public void add(Amigo amigo){
        if(!agenditaAmiguitos.contains(amigo)){
            agenditaAmiguitos.add(amigo);
            JOptionPane.showMessageDialog(null, "Se ha guardado exitosamente al amigo en la agenda");
        }else{
            JOptionPane.showMessageDialog(null, "Ya existe amigo con rut-->"+amigo.getRut(), "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }
    public void Remove(Amigo amigo){
        if(agenditaAmiguitos.contains(amigo)){
            agenditaAmiguitos.remove(agenditaAmiguitos.indexOf(amigo));
        }else{
            JOptionPane.showMessageDialog(null, "El rut ingresado no es valido o el amigo que intenta eliminar ya fue eliminado", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);

        }
    }
    
}
