package  AlgoritmosGeneticosSimples;

import java.util.LinkedList;

public class FuncionDeFitness {
    
    //Lista en donde se guardaran las actividades
    static LinkedList<Actividad> listaActividades = new LinkedList<>();
    static byte[] solucion = new byte[64];
    
    //Establece las actividades
    public static void establecerListaActividades(LinkedList<Actividad> pListaActividades)
    {
        listaActividades = pListaActividades;
    }
    
    // Establecer una solucion candidata como un arreglo de bytes
    public static void establecerSolucion(byte[] nuevaSolucion) {
        solucion = nuevaSolucion;
    }

    static void establecerSolucion(String nuevaSolucion) {
        solucion = new byte[nuevaSolucion.length()];
        for (int i = 0; i < nuevaSolucion.length(); i++) {
            String caracter = nuevaSolucion.substring(i, i + 1);
            if (caracter.contains("0") || caracter.contains("1")) {
                solucion[i] = Byte.parseByte(caracter);
            } else {
                solucion[i] = 0;
            }
        }
    }
    
    // Obtiene las actividades de un individuo de acuerdo a los genes(bits en 1)
    public static LinkedList<Actividad> obtenerActividades(Individuo pIndividuo)
    {
        LinkedList<Actividad> actividades = new LinkedList<>();
        LinkedList<Integer> indicesActividades = obtenerIndicesDeAsistenciaAActividades(pIndividuo);
        
        for(int index : indicesActividades)
        {
            Actividad actividad = listaActividades.get(index);
            actividades.add(actividad);
        }
        
        return actividades;
        
    }
    
    //Guarda la posicion de los bits que sean 1,(Que corresponderan a las actividades a las que quiere asistir el individuo)
    //de acuerdo a la lista de actividades.
    private static LinkedList<Integer> obtenerIndicesDeAsistenciaAActividades(Individuo pIndividuo)
    {
        LinkedList<Integer> res = new LinkedList<>();
        
        for (int i = 0; i < pIndividuo.tamanno() && i < solucion.length; i++) {
            if (pIndividuo.obtenerGen(i) == 1) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    //Dada la tabla de indices del individuo verifica si hay choques entres sus actividades
    private static boolean hayChoquesEntreActividadesDeInviduo(LinkedList<Integer> pIndicesDeAsistencia)
    {
        boolean res = false;
        
        for(int index : pIndicesDeAsistencia)
        {
            
            Actividad actividad = listaActividades.get(index);
            
            for(int indexCompara : pIndicesDeAsistencia)
            {
                if(index != indexCompara)
                {
                    Actividad actividadComparar = listaActividades.get(indexCompara);
                    res |= actividad.tieneChoqueCon(actividadComparar);
                }
            }
        }
        
        return res;
    }

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int obtenerFitness(Individuo individuo) {
        int fitness = 0;
        
        LinkedList<Integer> indiceAsistencia = obtenerIndicesDeAsistenciaAActividades(individuo);
        
        boolean tieneChoques = hayChoquesEntreActividadesDeInviduo(indiceAsistencia);
        
        if(!tieneChoques)
        {
            fitness = indiceAsistencia.size(); // Cantidad de actividades a las que puede asistir(estas son la cantidad de unos que
            // tenga el individuo en sus genes si no hay choques quiere decir que puede asistir a todas.
        }
        
        return fitness;
    }
    
    // Obtener el fitness optimo
    static int obtenerMaxFitness() {
        int maxFitness = solucion.length;
        return maxFitness;
    }
}
