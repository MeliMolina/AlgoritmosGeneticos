package algoritmos_geneticos;
/*
Instituto Tecnologico de Costa Rica
    Analisis de  Algoritmos
Melissa Molina Corrales 2013006074
*/

import java.util.LinkedList;

public class AG {
    
    //Genera actividades de manera aleatoria con la cantidad que se le de a la funcion para generar.
    private static LinkedList<Actividad> generarListaActividades(int pCantidadActividades)
    {
        LinkedList<Actividad> listaActividades = new LinkedList<>();
        
        for(int i = 0; i < pCantidadActividades; i++)
        {
            listaActividades.add(new Actividad());
        }
        
        return listaActividades;
    }
 
    public static void main(String[] args) {

        Actividad.ponerHorasDiaLaboral(24);// Rango de horas de las actividades
        
        //Imprime todas las actividades que se generaron
        LinkedList<Actividad> listaActividades = generarListaActividades(17);
       
        System.out.println("Actividades");
        System.out.println("------------------------------\n");
        for(Actividad actividad : listaActividades)
        {
            System.out.println(actividad.toString());
        }
        
        int largoGenes = listaActividades.size(); // El largo de los genes será la cantidad de actividades que se generaron
        
        Individuo.establecerLargoDeGenesPorDefecto(largoGenes);
        
        FuncionDeFitness.establecerListaActividades(listaActividades);// Utiliza la lista de actividades como solucion final
        
        // Crear una poblacion inicial
        Poblacion poblacion = new Poblacion(50, true);
        
        // Evolve our Poblacion until we reach an optimum solution
        int contadorDeGeneraciones = 0;
        while (contadorDeGeneraciones < 30) {
            contadorDeGeneraciones++;
            System.out.println("Generacion: " + contadorDeGeneraciones + " Mejor: " + poblacion.obtenerFittest().obtenerFitness());
            poblacion = Algoritmo.evolucionarPoblacion(poblacion);
        }
        
        LinkedList<Actividad> mejoresActividades = poblacion.obtenerActividadesDeFittest();
        System.out.println("Solucion encontrada!");
        System.out.println("Genes:");
        System.out.println(poblacion.obtenerFittest());
        System.out.println("");
        System.out.println("mejor combinación de actividades:");
        System.out.println("------------------------------\n");
        
        //Imprime actividades del individuo fitness, el mejor horario
        for(Actividad actividad : mejoresActividades)
        {
            System.out.println(actividad.toString());
        }

    }
}
