/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.modelo;

import java.util.ArrayList;


public class RecursoCompuesto extends Recurso{
    ArrayList<Recurso> componentes;
    public RecursoCompuesto(int codigo, String nombre, String descripcion, boolean activo) {
        super(codigo, nombre, descripcion, activo);
        componentes=new ArrayList<>();
    }
    @Override
    public long getValor() {
        long valor=0;
        for (Recurso componentes: componentes) {
            valor+=componentes.getValor();
        }
        return valor;
    }
    public int getNroComponentes(){
        return componentes.size();
    }
    public Recurso getComponente(int pos){
        return componentes.get(pos);
    }
    public void addComponente(Recurso recurso){
        if(!componentes.contains(recurso)){
            componentes.add(recurso);
        }
    }
    public String toString(){
        String string="";
        string+="(codigo: "+this.getCodigo()+") "+this.getNombre()+"\n";
        for(Recurso componentes:componentes){
            string+="\t";
            string+=componentes.toString()+"\n";
        }
        return string;
    }
    
}
