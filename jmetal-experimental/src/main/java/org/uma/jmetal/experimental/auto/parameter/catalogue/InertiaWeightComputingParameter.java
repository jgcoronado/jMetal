package org.uma.jmetal.experimental.auto.parameter.catalogue;

import java.util.List;
import org.uma.jmetal.experimental.auto.parameter.CategoricalParameter;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.inertiaweightcomputingstrategy.InertiaWeightComputingStrategy;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.inertiaweightcomputingstrategy.impl.ConstantValueStrategy;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.inertiaweightcomputingstrategy.impl.LinearDecreasingStrategy;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.inertiaweightcomputingstrategy.impl.LinearIncreasingStrategy;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.inertiaweightcomputingstrategy.impl.RandomSelectedValueStrategy;
import org.uma.jmetal.operator.mutation.MutationOperator;
import org.uma.jmetal.operator.mutation.impl.LinkedPolynomialMutation;
import org.uma.jmetal.operator.mutation.impl.NonUniformMutation;
import org.uma.jmetal.operator.mutation.impl.PolynomialMutation;
import org.uma.jmetal.operator.mutation.impl.UniformMutation;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

public class InertiaWeightComputingParameter extends CategoricalParameter {
  public InertiaWeightComputingParameter(String[] args, List<String> mutationOperators) {
    super("inertiaWeightComputingStrategy", args, mutationOperators);
  }

  public InertiaWeightComputingStrategy getParameter() {
    InertiaWeightComputingStrategy result;
    Double weightMin = (Double) findGlobalParameter("weightMin").getValue();
    Double weightMax = (Double) findGlobalParameter("weightMax").getValue();

    switch (getValue()) {
      case "constantValue":
        Double weight = (Double) findSpecificParameter("weight").getValue();
        result = new ConstantValueStrategy(weight) ;
        break;
      case "randomSelectedValue":
        result = new RandomSelectedValueStrategy(weightMin, weightMax) ;
        break;
      case "linearDecreasingStrategy":
        int iterations = (Integer) findSpecificParameter("maxIterations").getValue();
        int swarmSize = (Integer) findSpecificParameter("swarmSize").getValue();
        result = new LinearDecreasingStrategy(weightMin, weightMax, iterations, swarmSize) ;
        break;
      case "linearIncreasingStrategy":
        iterations = (Integer) findSpecificParameter("maxIterations").getValue();
        swarmSize = (Integer) findSpecificParameter("swarmSize").getValue();
        result =new LinearIncreasingStrategy(weightMin, weightMax, iterations, swarmSize) ;
        break;
      default:
        throw new RuntimeException("Inertia weight computing strategy does not exist: " + getName());
    }
    return result;
  }
}
