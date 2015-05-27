package AlgoritmosGeneticosSimples;

public class Poblacion {
    Individuo[] individuos;
    
    int[][]horario;// Arreglo en donde se guardarán las actividades a verificar
    int[] posiciones;// Arreglo donde se guardarán las posiciones de los bits en 1 en el gen del individuo
    
     //Arreglo de actividades con su hora de inicio y hora de finalizacion.
     int [][] actividades = {{7,8},{8,10},{9,12},{1,5},{2,3},{4,7},{8,10}};
    
 
       // Crear una poblacion
    public Poblacion(int tammanoPoblacion, boolean inicializar) {
        individuos = new Individuo[tammanoPoblacion];
        // inicializar poblacion
        if (inicializar) {
            // Iterar y crear individuos
          for (int i = 0; i < tamanno(); i++) {
                Individuo nuevoindividuo = new Individuo();
                nuevoindividuo.generarIndividuo();
                guardarIndividuo(i, nuevoindividuo);
            }
        }
    }
    
    // Busca en los genes del individuo los bits que esten en 1, los cuales
    //representan las actividades a las que este individuo desea asistir y coloca sus posiciones en el gen del individuo
    // en el arreglo de posiciones.
    public void buscarBitsenUno(Individuo individuo){
        int j = 0; 
        for (int i = 0; i < individuo.tamanno(); i++) {
            if (individuo.obtenerGen(i) == 1) {
                guardarPosicion(j,i);
                j++;     
            }
        }
     }
  
   // De acuerdo a las posiciones de los bits en 1, busca las posiciones de las 
   //actividades en el arreglo y las guarda en otro arreglo para verificar si chocan entre ellas.
    public void buscarActividades(){
        int pos;
        for(int i = 0; i < posiciones.length;i++){
           pos = posiciones[i];
           agregaraActividades(pos);
        }    
    }
    
    //Guarda las actividades a verificar en el arreglo horario.
    public void agregaraActividades(int indice){
        int i = 0;
        int j = 0;
        
         while(j < actividades.length){
                horario[indice][j] = actividades[indice][j];
               
                j++;
             } 
    }
    //Cuenta la cantidad de bits en 1 que tiene el individuo, para en caso de que ninguna actividad choque
    //colocarle un fitness con la cantidad de actividades a las que asistirá el individuo.
    public int contarBitsenUno(){
        int cantidad = 0;
        for(int i = 0; i < posiciones.length;i++){
            cantidad++;
        }
        return cantidad;
    }
  
   
    /* Getters */
    public Individuo obtenerIndividuo(int index) {
        return individuos[index];
    }

    public Individuo obtenerFittest() {
        Individuo mejor = individuos[0];
        // Iterar entre los individuos y devolver el mejor
        for (int i = 0; i < tamanno(); i++) {
            if (mejor.obtenerFitness() <= obtenerIndividuo(i).obtenerFitness()) {
                mejor = obtenerIndividuo(i);
            }
        }
        return mejor;
    }

    // Obtener el tamanno de la poblacion
    public int tamanno() {
        return individuos.length;
    }

    // Guardar el individuo
    public void guardarIndividuo(int index, Individuo indiv) {
        individuos[index] = indiv;
    }
    //Guarda la posicion del bit en 1 del gen del individuo
    public void guardarPosicion(int index, int pos) {
        posiciones[index] = pos;
    }   
    
    
}