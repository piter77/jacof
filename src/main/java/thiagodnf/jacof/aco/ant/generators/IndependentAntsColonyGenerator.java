package thiagodnf.jacof.aco.ant.generators;

import thiagodnf.jacof.aco.ACO;
import thiagodnf.jacof.aco.ant.ScAnt;
import thiagodnf.jacof.aco.ant.exploration.anttypebased.AntTypeBasedExploration;
import thiagodnf.jacof.aco.ant.initialization.AnAntAtEachVertex;
import thiagodnf.jacof.aco.ant.selection.RouletteWheel;
import thiagodnf.jacof.aco.graph.AntType;

import java.util.Random;


public class IndependentAntsColonyGenerator {

    public static ScAnt[] generate(int numberOfAnts, ACO aco) {
        ScAnt[] scAnts = new ScAnt[numberOfAnts];
        Random r = new Random();
        for (int i = 0; i < numberOfAnts; i++) {
            ScAnt scAnt = new ScAnt(AntType.A, aco, i);

            scAnt.setAlpha(r.nextGaussian() + 2.0);
            scAnt.setBeta(r.nextGaussian() + 3.0);

            scAnt.setAntExploration(new AntTypeBasedExploration(aco, new RouletteWheel()));
//            scAnt.setAntLocalUpdate();
            scAnt.setAntInitialization(new AnAntAtEachVertex(aco));
            scAnt.addObserver(aco);
            scAnts[i] = scAnt;
        }

        return scAnts;
    }

}
