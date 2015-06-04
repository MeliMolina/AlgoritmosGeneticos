
package AlgoritmosGeneticosSimples;

import java.lang.Math;
import static java.lang.Math.abs;
import java.util.Random;


public class Actividad {

    
    private int horaInicio; // hora de inicio de las actividades
    private int horaFin; // hora de finalizacion de las actividades
    
    private static final Random random = new Random();
    
    private int actividadId;
    
    private static int contadorActividades = 0;
    
    private static int horasDiaLaboral = 8;
    
    // Hace de manera aleatoria la hora de inicio y la hora de finalizacion para las actividades 
    //y con la hora de finalizacion hace un random con horas  que sean mayores
    //a esa hora de inicio.
    public Actividad()
    {
        contadorActividades++;
        actividadId = contadorActividades;
        
        int num = abs(random.nextInt(horasDiaLaboral));
        
        if(num == horasDiaLaboral-1)
        {
            num--;
        }
        
        horaInicio = num;
        horaFin = 0;
        while(horaFin <= horaInicio)
        {
            num = abs(random.nextInt(horasDiaLaboral));
            horaFin = num;
        }
    }
    
    //Establece la hora de inicio y la hora final
    public Actividad(int pHoraInicio, int pHoraFin)
    {
        horaInicio = pHoraInicio;
        horaFin = pHoraFin;
    }
    
    public static void ponerHorasDiaLaboral(int pHoras)
    {
        horasDiaLaboral = pHoras;
    }

    
    public int getHoraInicio() {
        return horaInicio;
    }

    
    public int getHoraFin() {
        return horaFin;
    }
    
    // Verifica si las actividades del individuo chocan
    public boolean tieneChoqueCon(Actividad pActividad)
    {
        boolean res = false;
        int horaInicioComparar = pActividad.getHoraInicio();
        int horaFinComparar = pActividad.getHoraFin();
        
        res |= (horaInicio >= horaInicioComparar && horaFin >= horaFinComparar);
        
        res |= (horaInicio < horaInicioComparar && (horaFin < horaFinComparar && horaFin > horaInicioComparar));
        
        res |= (horaInicio > horaInicioComparar && (horaFin > horaFinComparar && horaInicio < horaFinComparar));
        
        
        return res;
    }
    
   
    @Override
    public String toString() {
        return String.format("Actividad %d\n"
                + "hora de inicio: %d\n"
                + "hora de finalización: %d\n", actividadId, horaInicio, horaFin);
    }
    
    
}
