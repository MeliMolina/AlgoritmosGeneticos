package AlgoritmosGeneticosSimples;
/*
Instituto Tecnológico de Costa Rica
Analisis de  Algoritmos
Melissa Molina Corrales 2013006074
*/

public class AG {

    public static void main(String[] args) {

        //FuncionDeFitness.establecerSolucion("1001");

        // Crear una poblacion inicial
        Poblacion poblacion = new Poblacion(50, true);
        
        // Evolve our Poblacion until we reach an optimum solution
        int contadorDeGeneraciones = 0;
        while (poblacion.obtenerFittest().obtenerFitness() < 20){
        //FuncionDeFitness.obtenerMaxFitness()
            contadorDeGeneraciones++;
            System.out.println("Horario: " + contadorDeGeneraciones + " Mejor: " + poblacion.obtenerFittest().obtenerFitness());
            poblacion = Algoritmo.evolucionarPoblacion(poblacion);
        }
        System.out.println("Solucion encontrada!");
        System.out.println("Horario: " + contadorDeGeneraciones);
        System.out.println("Genes:");
        System.out.println(poblacion.obtenerFittest());

    }
}
