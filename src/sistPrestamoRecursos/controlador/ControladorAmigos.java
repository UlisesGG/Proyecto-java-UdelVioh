/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.controlador;


import javax.swing.JOptionPane;
import sistPrestamoRecursos.modelo.AgendaAmigos;
import sistPrestamoRecursos.modelo.Amigo;
import sistPrestamoRecursos.modelo.Prestamo;

public class ControladorAmigos {
    private static ControladorAmigos instancia=null;
       
    
    private ControladorAmigos(){
    }
    public static ControladorAmigos getInstancia(){
        if(instancia==null){
            instancia=new ControladorAmigos();
        }
        return instancia;
    }
    public String [] listarAmigo(String rut){
        String [] vector=new String[4];
        AgendaAmigos agenda= AgendaAmigos.getInstancia();
        vector[0]=agenda.getByRut(rut).getRut();
        vector[1]=agenda.getByRut(rut).getNombre();
        vector[2]=agenda.getByRut(rut).getEmail();
        vector[3]=agenda.getByRut(rut).getFono();
        return vector;
    }
    public String [][] listarAmigos(){
        AgendaAmigos cos= AgendaAmigos.getInstancia();
        if(cos.getNroAmigos()>0){
            String [][] lista=new String[cos.getNroAmigos()][4];
            for(int i=0; i<cos.getNroAmigos(); i++){
                lista[i][0]=cos.getByPos(i).getRut();
                lista[i][1]=cos.getByPos(i).getNombre();
                lista[i][2]=cos.getByPos(i).getEmail();
                lista[i][3]=cos.getByPos(i).getFono();
            }
            return lista;
        }else{
            return null;
        }
    }
    public String [][] listarAmigosConPrestamoPendiente(){
        AgendaAmigos cos= AgendaAmigos.getInstancia();
        String [][] lista=new String[cos.getNroAmigos()][4];
        int cont=0;
        for(int i=0; i<cos.getNroAmigos(); i++){
            if(cos.getByPos(i).tienePrestamos()){
                lista[i][0]=cos.getByPos(i).getRut();
                lista[i][1]=cos.getByPos(i).getNombre();
                lista[i][2]=cos.getByPos(i).getEmail();
                lista[i][3]=cos.getByPos(i).getFono();
            }
        }
        for(int i=0; i<lista.length; i++){
            if(lista[i][0]!=null){
                cont++;
            }
        }
        if(cont>0){
            String [][] listaFinal=new String[cont][4];
            int i=0;
            int j=0;
            while(i<listaFinal.length && j<lista.length){
                if(lista[j][0]!=null){
                    listaFinal[i][0]=lista[j][0];
                    listaFinal[i][1]=lista[j][1];
                    listaFinal[i][2]=lista[j][2];
                    listaFinal[i][3]=lista[j][3];
                    i++;
                }
                j++;
            }
            return listaFinal;
        }else{
            return null;
        }
    }
    public String [][] listarAmigosSinPretamoPendiente(){
        AgendaAmigos cos= AgendaAmigos.getInstancia();
        String [][] lista=new String[cos.getNroAmigos()][4];
        int cont=0;
        for(int i=0; i<cos.getNroAmigos(); i++){
            if(!cos.getByPos(i).tienePrestamos()){
                lista[i][0]=cos.getByPos(i).getRut();
                lista[i][1]=cos.getByPos(i).getNombre();
                lista[i][2]=cos.getByPos(i).getEmail();
                lista[i][3]=cos.getByPos(i).getFono();
            }
        }
        for(int i=0; i<lista.length; i++){
            if(lista[i][0]!=null){
                cont++;
            }
        }
        if(cont>0){
            String [][] listaFinal=new String[cont][4];
            int i=0;
            int j=0;
            while(i<listaFinal.length && j<lista.length){
                if(lista[j][0]!=null){
                    listaFinal[i][0]=lista[j][0];
                    listaFinal[i][1]=lista[j][1];
                    listaFinal[i][2]=lista[j][2];
                    listaFinal[i][3]=lista[j][3];
                    i++;
                }
                j++;
            }
            return listaFinal;
        }else{
            return null;
        }
    }
    public void crearAmigo(String rut, String nomb, String email, String fono){
        Amigo am=new Amigo(rut, nomb, email, fono);
        AgendaAmigos agenda= AgendaAmigos.getInstancia();
        agenda.add(am);
    }
    public void eliminarAmigo(String rut){
        AgendaAmigos agenda= AgendaAmigos.getInstancia();
        agenda.Remove(agenda.getByRut(rut));
    }
}
