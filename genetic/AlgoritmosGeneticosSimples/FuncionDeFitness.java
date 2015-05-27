package AlgoritmosGeneticosSimples;

public class FuncionDeFitness {

    static byte[] solucion = new byte[64];
    static Poblacion poblacion;
   
   

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

    // Calculate inidividuals fittness by comparing it to our candidate solution
    static int obtenerFitness(Individuo individuo) {
        int fitness;
        int i = 0;
        int cantidad_fitness;
        
        poblacion.buscarBitsenUno(individuo);// Busca en los genes del individuo los bits que esten en 1, los cuales
        //representan las actividades a las que este individuo desea asistir y coloca sus posiciones en el gen del individuo
        // en un arreglo.
        cantidad_fitness = poblacion.contarBitsenUno();//Cuenta la cantidad de bits en 1 del individuo
        poblacion.buscarActividades();// De acuerdo a las posiciones de los bits en 1, busca las posiciones de las 
        //actividades en el arreglo y las guarda en otro arreglo para verificarlas.
        
        boolean bandera = true;
        while(i < poblacion.horario.length && bandera== true){
             //Si esta condicion se cumple, quiere decir que las actividades chocan y se sale del ciclo  
            if(poblacion.horario[i+1][0] > poblacion.horario[i][0] && poblacion.horario[i+1][0] < poblacion.horario[i][1]){
                bandera = false;
                i++;
            }
            i++;
        }   
        // Si llega hasta el final del arreglo del horario, las actividades no chocan y por lo tanto el individuo puede asistir
        // a todas, se coloca el fitness como la cantidad de bits en 1 que tiene el individuo, que es la cantidad de actividades
        // a las que puede asistir.
        if(i == poblacion.horario.length){
            fitness = cantidad_fitness;
        }
        else{
            fitness = 0;// si las actividades chocan el fitnees se coloca en 0
        }
        
        return fitness;
    }
   
        
    // Obtener el fitness optimo
    static int obtenerMaxFitness() {
        int maxFitness = solucion.length;
        return maxFitness;
    }
}
