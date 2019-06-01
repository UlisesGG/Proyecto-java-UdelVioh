/*
    CREADOR JAVIER GÓMEZ GALLEGOS
*/
package sistPrestamoRecursos.controlador;


import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import sistPrestamoRecursos.modelo.AgendaAmigos;
import sistPrestamoRecursos.modelo.Amigo;
import sistPrestamoRecursos.modelo.CatalogoRecursos;
import sistPrestamoRecursos.modelo.persistencia.IOSistemaPrestamoRecursos;
import sistPrestamoRecursos.modelo.Prestamo;
import sistPrestamoRecursos.modelo.Recurso;


public class ControladorPrestamos {
    private static ControladorPrestamos instancia=null;
    ArrayList<Prestamo> prestamos;
    
    private ControladorPrestamos(){
        prestamos=new ArrayList<>();
    }
    public static ControladorPrestamos getInstancia(){
        if(instancia==null){
            instancia=new ControladorPrestamos();
        }
        return instancia;
    }
    public void inicio(){
 
    }
    public void prestamos(LocalDate fPrest, LocalDate fDev, int codRecurso, String rutPrest){
        CatalogoRecursos insta=CatalogoRecursos.getInstancia();
        AgendaAmigos inst= AgendaAmigos.getInstancia();
        Prestamo pres = new Prestamo(fPrest, fDev, insta.getByCod(codRecurso), inst.getByRut(rutPrest));
        insta.getByCod(codRecurso).addPrestamo(pres);
        inst.getByRut(rutPrest).addPrestamo(pres);
        prestamos.add(pres);
        JOptionPane.showMessageDialog(null, "Se añadio un prestamo");
    }
    public void devolucion(String rutPrestatario){
        AgendaAmigos instan=AgendaAmigos.getInstancia();
        instan.getByRut(rutPrestatario).getUltimoPrestamo().devolucion(LocalDate.now());
    }
    public String [][] listarPrestamos(){
        String [][] pres=new String [prestamos.size()][6];
        if(prestamos.size()>0){
            for(int i=0; i<pres.length; i++){
                pres[i][0]=prestamos.get(i).getFechaPrestamo().getDayOfMonth()+"/"+prestamos.get(i).getFechaPrestamo().getMonthValue()+"/"+prestamos.get(i).getFechaPrestamo().getYear();
                pres[i][1]=prestamos.get(i).getFechaDevolucion().getDayOfMonth()+"/"+prestamos.get(i).getFechaDevolucion().getMonthValue()+"/"+prestamos.get(i).getFechaDevolucion().getYear();
                if(prestamos.get(i).estaPendiente()){
                    pres[i][2]="No devuelto";
                }else{
                    pres[i][2]=prestamos.get(i).getFechaDevolucionReal().getDayOfMonth()+"/"+prestamos.get(i).getFechaDevolucionReal().getMonthValue()+"/"+prestamos.get(i).getFechaDevolucionReal().getYear();
                }    
                pres[i][3]=prestamos.get(i).getRecurso().getNombre();
                pres[i][4]=prestamos.get(i).getPrestatario().getRut();
                pres[i][5]=prestamos.get(i).getPrestatario().getNombre();
            }
            return pres;
        }else{
            return null;
        }
    }
    public String [][] listarPrestamosPendientes(){
        int cont=0;
        for(int i=0; i<prestamos.size(); i++){
            if(prestamos.get(i).estaPendiente()){
                cont++;
            }
        }
        if(cont>0){
            String [][] pres= new String[cont][6];
            for(int i=0; i<pres.length; i++){
                if(prestamos.get(i).estaPendiente()){
                    pres[i][0]=prestamos.get(i).getFechaPrestamo().getDayOfMonth()+"/"+prestamos.get(i).getFechaPrestamo().getMonthValue()+"/"+prestamos.get(i).getFechaPrestamo().getYear();
                    pres[i][1]=prestamos.get(i).getFechaDevolucion().getDayOfMonth()+"/"+prestamos.get(i).getFechaDevolucion().getMonthValue()+"/"+prestamos.get(i).getFechaDevolucion().getYear();
                    pres[i][2]="No devuelto";
                    pres[i][3]=prestamos.get(i).getRecurso().getNombre();
                    pres[i][4]=prestamos.get(i).getPrestatario().getRut();
                    pres[i][5]=prestamos.get(i).getPrestatario().getNombre();
                }
            }
            return pres;
        }else{
            return null;
        }
    }
    public Prestamo listarPrestamo(Prestamo prestamo){
        return prestamo;
    }
    public void guardarDatosSistema(){
        this.guardarAmigosSinPrestamos();
        this.guardarRecursos();
    }

    public void leerDatosSistema(){
        this.leerAmigosSinPrestamos();
        this.leerRecursos();
    }
    private void guardarAmigosSinPrestamos(){
        AgendaAmigos con= AgendaAmigos.getInstancia();
        IOSistemaPrestamoRecursos cos= IOSistemaPrestamoRecursos.getInstancia();
        ArrayList<Amigo> amigos=new ArrayList<>();
        for(int i=0; i<con.getNroAmigos(); i++){
            if(!con.getByPos(i).tienePrestamos()){
                amigos.add(con.getByPos(i));
            }
        }
        Amigo [] am=new Amigo[amigos.size()];
        am=amigos.toArray(am);
        cos.grabarAmigosSinPrestamos(am);
    }
    private void leerAmigosSinPrestamos(){
        IOSistemaPrestamoRecursos cos= IOSistemaPrestamoRecursos.getInstancia();
        AgendaAmigos con=AgendaAmigos.getInstancia();
        Amigo [] am;
        am=cos.leerAmigosSinPrestamos();
        for(int i=0; i<am.length; i++){
            con.add(am[i]);
        }
    }
    private void guardarRecursos(){
        CatalogoRecursos ins=CatalogoRecursos.getInstancia();
        IOSistemaPrestamoRecursos cos= IOSistemaPrestamoRecursos.getInstancia();
        Recurso [] recursos= new Recurso[ins.getNroRecursos()];
        for(int i=0; i<recursos.length; i++){
            recursos[i]=ins.getByPos(i);
        }
        cos.grabarRecursosYPrestamos(recursos);
    }
    private void leerRecursos(){
        IOSistemaPrestamoRecursos cos= IOSistemaPrestamoRecursos.getInstancia();
        CatalogoRecursos ins=CatalogoRecursos.getInstancia();
        AgendaAmigos in=AgendaAmigos.getInstancia();
        Recurso [] recursos=cos.leerRecursosYPrestamos();
        for(int i=0; i<recursos.length; i++) {
            ins.add(recursos[i]);
            for(int j=0;j<recursos[i].getNroPrestamos(); j++){
                prestamos.add(recursos[i].getPrestamo(j));
                in.add(recursos[i].getPrestamo(j).getPrestatario());
            }
        }
    }
}
