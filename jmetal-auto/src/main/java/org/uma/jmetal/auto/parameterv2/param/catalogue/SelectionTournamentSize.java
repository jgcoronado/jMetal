package org.uma.jmetal.auto.parameterv2.param.catalogue;

import org.uma.jmetal.auto.parameterv2.param.IntegerParameter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class SelectionTournamentSize extends IntegerParameter {
  private String[] args ;

  public SelectionTournamentSize(String args[], Integer lowerBound, Integer upperBound) {
    super(lowerBound, upperBound) ;
    this.args = args ;
  }

  @Override
  public IntegerParameter parse() {
    value = on("--selectionTournamentSize", args, Integer::parseInt);
    return this ;
  }

  @Override
  public String getName() {
    return "selectionTournamentSize";
  }
}
