package benchmark.runners;

import benchmark.output.CSV;
import benchmark.output.Output;
import benchmark.problem.AcoTSP;
import benchmark.stats.Diversity;
import benchmark.visualization.Performance;
import benchmark.visualization.Visualization;
import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.AntSystem;
import thiagodnf.jacof.aco.IndependentAntSystem;
import thiagodnf.jacof.problem.Problem;
import thiagodnf.jacof.util.ExecutionStats;
import tsplib.DistanceFunction;

import java.io.IOException;

public class IndependentACORunner implements Runner {

    private ACO aco;
    private String instance;
    private int iterationNumber;
    private Visualization visualization;
    private Output output;
    private DistanceFunction distanceFunction;
    private Diversity diversity;
    private Performance performance;
    private String name;

    public IndependentACORunner withACO(ACO aco) {
        this.aco = aco;
        return this;
    }

    public IndependentACORunner withAcoName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IndependentACORunner withPerformance(Performance performance) {
        this.performance = performance;
        return this;
    }

    @Override
    public IndependentACORunner withVisualization(boolean enabled) {
        this.visualization = new Visualization(enabled);
        return this;
    }

    @Override
    public IndependentACORunner withOutput(Output output) {
        this.output = output;
        return this;
    }

    @Override
    public IndependentACORunner withIteration(int iterationNumber) {
        this.iterationNumber = iterationNumber;
        return this;
    }

    @Override
    public IndependentACORunner withInstance(String instance) {
        this.instance = instance;
        return this;
    }

    @Override
    public IndependentACORunner withDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
        return this;
    }

    public IndependentACORunner withDiversity(boolean showPheromoneRatioChart, boolean showAttractivenessRationChart, boolean showAttractivenessRatioChart) {
        this.diversity = new Diversity(aco, showPheromoneRatioChart, showAttractivenessRationChart, showAttractivenessRatioChart);
        return this;
    }

    @Override
    public void start() throws IOException {
        Problem problem = new AcoTSP(instance)
                .withAcoName(name)
                .withDistanceFunction(distanceFunction)
                .withVisualization(this.visualization)
                .withDiversity(this.diversity)
                .withPerformance(this.performance)
                .build();

        aco.setProblem(problem);
        aco.setNumberOfIterations(this.iterationNumber);

        ExecutionStats es = ExecutionStats.execute(aco, problem);
        output.use(es);
    }

    public static void main(String[] args) throws IOException {

        String instance = "src/main/resources/problems/tsp/berlin52.tsp";

        IndependentAntSystem independentAntSystem = new IndependentAntSystem();
        independentAntSystem.setNumberOfAnts(100);
        independentAntSystem.setRho(0.1);


        AntSystem regularAntSystem = new AntSystem();
        regularAntSystem.setNumberOfAnts(100);
        regularAntSystem.setRho(0.1);
        regularAntSystem.setAlpha(2.0);
        regularAntSystem.setBeta(3.0);


        Performance performance = new Performance(false);

        new IndependentACORunner()
                .withACO(independentAntSystem)
                .withAcoName("IndependentAntSystem")
                .withInstance(instance)
                .withIteration(100)
                .withVisualization(false)
//                .withDiversity(false, false, false)
                .withOutput(new CSV("acoElistic.csv", 99))
                .withPerformance(performance)
                .start();

    }
}
