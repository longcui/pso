package test.pso;

import net.sourceforge.jswarm_pso.Neighborhood;
import net.sourceforge.jswarm_pso.Neighborhood1D;
import net.sourceforge.jswarm_pso.Swarm;
import net.sourceforge.jswarm_pso.example_1.MyFitnessFunction;
import net.sourceforge.jswarm_pso.example_1.MyParticle;
import net.sourceforge.jswarm_pso.example_2.SwarmShow2D;

public class Test1 {

	//-------------------------------------------------------------------------
	// Main
	//-------------------------------------------------------------------------
	public static void main(String[] args) {
		System.out.println("Begin: Example 1\n");

		int numParticles = 50000;
		// Create a swarm (using 'MyParticle' as sample particle and 'MyFitnessFunction' as fitness function)
		Swarm swarm = new Swarm(numParticles, new MyParticle(), new MyFitnessFunction());

		// Use neighborhood
		Neighborhood neigh = new Neighborhood1D(numParticles / 5, true);
		swarm.setNeighborhood(neigh);
		swarm.setNeighborhoodIncrement(0.9);

		// Set position (and velocity) constraints. I.e.: where to look for solutions
		swarm.setInertia(0.95);
		swarm.setMaxPosition(1);
		swarm.setMinPosition(0);
		swarm.setMaxMinVelocity(0.1);

		int numberOfIterations = 100;
		boolean showGraphics = false;

		if (showGraphics) {
			int displayEvery = numberOfIterations / 100 + 1;
			SwarmShow2D ss2d = new SwarmShow2D(swarm, numberOfIterations, displayEvery, true);
			ss2d.run();
		} else {
			// Optimize (and time it)
			for (int i = 0; i < numberOfIterations; i++)
				swarm.evolve();
		}

		// Print results
		System.out.println(swarm.toStringStats());
		System.out.println("End: Example 1");
	}
}
