/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.controlador;
import javax.swing.JOptionPane;
import sistPrestamoRecursos.modelo.CatalogoRecursos;
import sistPrestamoRecursos.modelo.Recurso;
import sistPrestamoRecursos.modelo.RecursoCompuesto;
import sistPrestamoRecursos.modelo.RecursoSimple;

public class ControladorRecursos {
    private static ControladorRecursos instancia=null;
    private ControladorRecursos(){
    }
    public static ControladorRecursos getInstancia(){
        if(instancia==null){
            instancia=new ControladorRecursos();
        }
        return instancia;
    }
    public String [][] listarRecursos(){
        CatalogoRecursos instan=CatalogoRecursos.getInstancia();
        String [][] recursos=new String [instan.getNroRecursos()][5];
        int cont=0;
        for(int i=0; i<instan.getNroRecursos(); i++){
            int cod=instan.getByPos(i).getCodigo();
            long valor=instan.getByPos(i).getValor();
            recursos[i][0]=Integer.toString(cod);
            recursos[i][1]=instan.getByPos(i).getNombre();
            if(instan.getByPos(i).isActivo()){
                recursos[i][2]="activo";
            }else{
                recursos[i][2]="inactivo";
            }
            recursos[i][3]=Long.toString(valor);
            if(instan.getByPos(i) instanceof RecursoSimple){
                recursos[i][4]="simple";
            }else{
                recursos[i][4]="compuesto";
            }
        }
        for(int i=0; i<recursos.length; i++){
            if(recursos[i][0]!=null){
                cont++;
            }
        }
        if(cont>0){
            return recursos;
        }else{
            return null;
        }
    }
    public String [][] listarRecursosActivosNoPrestados(){
        CatalogoRecursos instan=CatalogoRecursos.getInstancia();
        String [][] recursos=new String [instan.getNroRecursos()][5];
        int cont=0;
        for(int i=0; i<instan.getNroRecursos(); i++){
            if(!instan.getByPos(i).estaPrestado() && instan.getByPos(i).isActivo()){
                int cod=instan.getByPos(i).getCodigo();
                long valor=instan.getByPos(i).getValor();
                recursos[i][0]=Integer.toString(cod);
                recursos[i][1]=instan.getByPos(i).getNombre();
                recursos[i][2]="activo";
                recursos[i][3]=Long.toString(valor);
                if(instan.getByPos(i) instanceof RecursoSimple){
                    recursos[i][4]="simple";
                }else{
                    recursos[i][4]="compuesto";
                }
            }
        }
        for(int i=0; i<recursos.length; i++){
            if(recursos[i][0]!=null){
                cont++;
            }
        }
        if(cont>0){
            String [][] recursosFinal=new String [cont][5];
            int i=0;
            int j=0;
            while(i<recursosFinal.length && j<recursos.length){
                if(recursos[j][0]!=null){
                    recursosFinal[i][0]=recursos[j][0];
                    recursosFinal[i][1]=recursos[j][1];
                    recursosFinal[i][2]=recursos[j][2];
                    recursosFinal[i][3]=recursos[j][3];
                    recursosFinal[i][4]=recursos[j][4];
                    i++;
                }
                j++;
            }

            return recursosFinal;
        }else{
            return null;
        }
    }
    public String [][] listarRecursosDisponibles(){
        CatalogoRecursos instan=CatalogoRecursos.getInstancia();
        String [][] recursos=new String [instan.getNroRecursos()][5];
        int cont=0;
        for(int i=0; i<instan.getNroRecursos(); i++){
            if(!instan.getByPos(i).tienePrestamo() && instan.getByPos(i).isActivo()){
                int cod=instan.getByPos(i).getCodigo();
                long valor=instan.getByPos(i).getValor();
                recursos[i][0]=Integer.toString(cod);
                recursos[i][1]=instan.getByPos(i).getNombre();
                recursos[i][2]="activo";
                recursos[i][3]=Long.toString(valor);
                if(instan.getByPos(i) instanceof RecursoSimple){
                    recursos[i][4]="simple";
                }else{
                    recursos[i][4]="compuesto";
                }
            }
        }
        for(int i=0; i<recursos.length; i++){
            if(recursos[i][0]!=null){
                cont++;
            }
        }
        if(cont>0){
            String [][] recursosFinal=new String [cont][5];
            int i=0;
            while(i<recursosFinal.length){
                for(int j=0; j<recursos.length; j++){
                    if(recursos[j][0]!=null){
                        recursosFinal[i][0]=recursos[j][0];
                        recursosFinal[i][1]=recursos[j][1];
                        recursosFinal[i][2]=recursos[j][2];
                        recursosFinal[i][3]=recursos[j][3];
                        recursosFinal[i][4]=recursos[j][4];
                    } 
                }
                i++;
            }
            return recursosFinal;
        }else{
            return null;
        }
    }
    public String [] listarRecurso(int codigo){
        String [] recurso=new String [0];
        return recurso;
    }
    public String obtenerRecursosComoString(String codigo){
        int cod=Integer.parseInt(codigo);
        try{
            String recurso=CatalogoRecursos.getInstancia().getByCod(cod).toString();
            return recurso;
        }catch(NullPointerException e){
            return null;
        }
    }
    public void crearRecursoSimple(int cod, String nom, String desc, long valor){
        RecursoSimple recurso = new RecursoSimple(valor, cod, nom, desc, true);
        CatalogoRecursos catalogo=CatalogoRecursos.getInstancia();
        catalogo.add(recurso);

    }
    public void crearRecursoCompuesto(int cod, String nom, String desc, int [] codComponentes){
        RecursoCompuesto recurso= new RecursoCompuesto(cod, nom, desc, true);
        CatalogoRecursos insta=CatalogoRecursos.getInstancia();
        for(int i=0; i<codComponentes.length; i++){
            recurso.addComponente(insta.getByCod(codComponentes[i]));
            insta.remove(insta.getByCod(codComponentes[i]));
        }
        insta.add(recurso);
    }
    public void darDeBajaRecurso(int codigo){
        if(CatalogoRecursos.getInstancia().getByCod(codigo)!=null){
            CatalogoRecursos.getInstancia().getByCod(codigo).setActivo(false);
        }else{
            JOptionPane.showMessageDialog(null, "El codigo ingresado no es valido", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }
}
