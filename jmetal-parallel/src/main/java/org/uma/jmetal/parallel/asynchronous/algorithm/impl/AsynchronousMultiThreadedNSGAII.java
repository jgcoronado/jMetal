package org.uma.jmetal.parallel.asynchronous.algorithm.impl;

import org.uma.jmetal.component.catalogue.common.termination.Termination;
import org.uma.jmetal.component.catalogue.ea.replacement.Replacement;
import org.uma.jmetal.component.catalogue.ea.replacement.impl.RankingAndDensityEstimatorReplacement;
import org.uma.jmetal.operator.crossover.CrossoverOperator;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.selection.impl.BinaryTournamentSelection;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.densityestimator.impl.CrowdingDistanceDensityEstimator;
import org.uma.jmetal.util.ranking.impl.MergeNonDominatedSortRanking;

public class AsynchronousMultiThreadedNSGAII<S extends Solution<?>>
    extends AsynchronousMultiThreadedGeneticAlgorithm<S> {

  public AsynchronousMultiThreadedNSGAII(
      int numberOfCores,
      Problem<S> problem,
      int populationSize,
      CrossoverOperator<S> crossover,
      MutationOperator<S> mutation,
      Termination termination) {
    super(numberOfCores,problem, populationSize, crossover,mutation, new BinaryTournamentSelection<>(new RankingAndCrowdingDistanceComparator<>()),
            new RankingAndDensityEstimatorReplacement<>(
                    new MergeNonDominatedSortRanking<>(),
                    new CrowdingDistanceDensityEstimator<>(),
                    Replacement.RemovalPolicy.ONE_SHOT),termination);
  }
}
