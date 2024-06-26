package org.uma.jmetal.problem.multiobjective;

import java.util.ArrayList;
import java.util.List;
import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

/**
 * Class representing problem Fonseca
 */
@SuppressWarnings("serial")
public class Fonseca extends AbstractDoubleProblem {

  /**
   * Constructor
   */
  public Fonseca() {
    int numberOfVariables = 3;
    numberOfObjectives(2);
    numberOfConstraints(0);
    name("Fonseca");

    List<Double> lowerLimit = new ArrayList<>(numberOfVariables);
    List<Double> upperLimit = new ArrayList<>(numberOfVariables);

    for (int i = 0; i < numberOfVariables; i++) {
      lowerLimit.add(-4.0);
      upperLimit.add(4.0);
    }

    variableBounds(lowerLimit, upperLimit);
  }

  /**
   * Evaluate() method
   */
  @Override
  public DoubleSolution evaluate(DoubleSolution solution) {
    int numberOfVariables = numberOfVariables();

    double[] f = new double[solution.objectives().length];
    double[] x = new double[numberOfVariables];

    for (int i = 0; i < numberOfVariables; i++) {
      x[i] = solution.variables().get(i);
    }

    double sum1 = 0.0;
    for (int i = 0; i < numberOfVariables; i++) {
      sum1 += StrictMath.pow(x[i] - (1.0 / StrictMath.sqrt(numberOfVariables)), 2.0);
    }
    double exp1 = StrictMath.exp((-1.0) * sum1);
    f[0] = 1 - exp1;

    double sum2 = 0.0;
    for (int i = 0; i < numberOfVariables; i++) {
      sum2 += StrictMath.pow(x[i] + (1.0 / StrictMath.sqrt(numberOfVariables)), 2.0);
    }
    double exp2 = StrictMath.exp((-1.0) * sum2);
    f[1] = 1 - exp2;

    solution.objectives()[0] = f[0];
    solution.objectives()[1] = f[1];

    return solution;
  }
}


