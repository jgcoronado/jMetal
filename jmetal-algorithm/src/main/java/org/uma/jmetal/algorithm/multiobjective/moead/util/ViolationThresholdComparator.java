package org.uma.jmetal.algorithm.multiobjective.moead.util;

import java.util.Comparator;
import java.util.List;
import org.uma.jmetal.solution.Solution;
import org.uma.jmetal.util.ConstraintHandling;

/**
 * This class implements the ViolationThreshold Comparator
 *
 * @author Juan J. Durillo
 */
@SuppressWarnings("serial")
public class ViolationThresholdComparator<S extends Solution<?>> implements Comparator<S> {
  private double threshold = 0.0;

  /**
   * Compares two solutions. If the solutions has no constraints the method return 0
   *
   * @param solution1 Object representing the first <code>Solution</code>.
   * @param solution2 Object representing the second <code>Solution</code>.
   * @return -1, or 0, or 1 if o1 is less than, equal, or greater than o2,
   * respectively.
   */
  @Override
  public int compare(S solution1, S solution2) {
    double overall1, overall2;
    overall1 = ConstraintHandling.numberOfViolatedConstraints(solution1) * ConstraintHandling.overallConstraintViolationDegree(solution1);
    overall2 = ConstraintHandling.numberOfViolatedConstraints(solution2) * ConstraintHandling.overallConstraintViolationDegree(solution2);

    if ((overall1 < 0) && (overall2 < 0)) {
      return Double.compare(overall2, overall1);
    } else if ((overall1 == 0) && (overall2 < 0)) {
      return -1;
    } else if ((overall1 < 0) && (overall2 == 0)) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Returns true if solutions s1 and/or s2 have an overall constraint
   * violation with value less than 0
   */
  public boolean needToCompare(S solution1, S solution2) {
    boolean needToCompare;
    double overall1, overall2;
    overall1 = Math.abs(ConstraintHandling.numberOfViolatedConstraints(solution1) * ConstraintHandling.overallConstraintViolationDegree(solution1));
    overall2 = Math.abs(ConstraintHandling.numberOfViolatedConstraints(solution2) * ConstraintHandling.overallConstraintViolationDegree(solution2));

    needToCompare = (overall1 > this.threshold) || (overall2 > this.threshold);

    return needToCompare;
  }

  /**
   * Computes the feasibility ratio
   * Return the ratio of feasible solutions
   */
  public double feasibilityRatio(List<S> solutionSet) {
    double aux = 0.0;
    for (S solution : solutionSet) {
      if (ConstraintHandling.numberOfViolatedConstraints(solution) < 0) {
        aux = aux + 1.0;
      }
    }
    return aux / (double) solutionSet.size();
  }

  /**
   * Computes the feasibility ratio
   * Return the ratio of feasible solutions
   */
  public double meanOverallViolation(List<S> solutionSet) {
    double aux = 0.0;
    for (S solution : solutionSet) {
      aux += Math.abs(ConstraintHandling.numberOfViolatedConstraints(solution) *
              ConstraintHandling.overallConstraintViolationDegree(solution));
    }
    return aux / (double) solutionSet.size();
  }

  /**
   * Updates the threshold value using the population
   */
  public void updateThreshold(List<S> set) {
    threshold = feasibilityRatio(set) * meanOverallViolation(set);
  }
}
