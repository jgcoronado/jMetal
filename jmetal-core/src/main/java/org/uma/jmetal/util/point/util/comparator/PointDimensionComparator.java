package org.uma.jmetal.util.point.util.comparator;

import java.util.Comparator;
import org.uma.jmetal.util.errorchecking.JMetalException;
import org.uma.jmetal.util.point.Point;

/**
 * This class implements the {@link Comparator} interface. It is used
 * to compare two points according the value of a particular dimension.
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 * @author Juan J. Durillo
 */
public class PointDimensionComparator implements Comparator<Point> {

  /**
   * Stores the value of the index to compare
   */
  private int index;

  /**
   * Constructor
   */
  public PointDimensionComparator(int index) {
    if (index < 0) {
      throw new JMetalException("The index value is negative");
    }
    this.index = index;
  }

  /**
   * Compares the objects o1 and o2.
   *
   * @param pointOne An object that reference a double[]
   * @param pointTwo An object that reference a double[]
   * @return -1 if o1 < o1, 1 if o1 > o2 or 0 in other case.
   */
  @Override
  public int compare(Point pointOne, Point pointTwo) {
    if (pointOne ==  null) {
      throw new JMetalException("PointOne is null") ;
    } else if (pointTwo == null) {
      throw new JMetalException("PointTwo is null") ;
    } else if (index >= pointOne.dimension()) {
      throw new JMetalException("The index value " + index
          + " is out of range (0,  " + (pointOne.dimension()-1) + ")") ;
    } else if (index >= pointTwo.dimension()) {
      throw new JMetalException("The index value " + index
          + " is out of range (0,  " + (pointTwo.dimension()-1) + ")") ;
    }

    return Double.compare(pointOne.value(index), pointTwo.value(index));
  }
}
