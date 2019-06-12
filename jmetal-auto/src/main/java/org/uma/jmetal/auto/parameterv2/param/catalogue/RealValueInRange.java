package org.uma.jmetal.auto.parameterv2.param.catalogue;

import org.uma.jmetal.auto.parameterv2.param.RealParameter;

public class RealValueInRange extends RealParameter {
  private String name ;
  private String[] args ;

  public RealValueInRange(String args[], String name, Double lowerBound, Double upperBound)  {
    super(lowerBound, upperBound) ;
    this.name = name ;
    this.args = args ;
  }

  @Override
  public RealParameter parse() {
    value = on("--"+name, args, Double::parseDouble);
    return this ;
  }

  @Override
  public String getName() {
    return name;
  }
}
