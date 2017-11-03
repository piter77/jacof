package tsplib.moeatsp.output;

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.EvolutionaryAlgorithm;
import tsplib.TSPInstance;
import tsplib.Tour;

import static tsplib.Tour.toTour;

public class CSV implements Output{


    @Override
    public void use(Algorithm algorithm, TSPInstance instance) {

        if (algorithm instanceof EvolutionaryAlgorithm) {
            EvolutionaryAlgorithm ea = (EvolutionaryAlgorithm) algorithm;
            Tour tour = toTour(algorithm.getResult().get(0));
            System.out.println(algorithm.getProblem().getName() +" : "+ tour.distance(instance));
        }
    }
}
