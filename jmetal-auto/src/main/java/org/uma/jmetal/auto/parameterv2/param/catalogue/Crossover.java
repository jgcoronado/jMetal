package org.uma.jmetal.auto.parameterv2.param.catalogue;

import org.uma.jmetal.auto.parameterv2.param.CategoricalParameter;
import org.uma.jmetal.auto.parameterv2.param.Parameter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Crossover extends CategoricalParameter<String> {
  private String[] args;

  public Crossover(String args[]) {
    this(args, Arrays.asList("SBX", "BLX_ALPHA"));
  }

  public Crossover(String args[], List<String> crossoverOperators) {
    super(crossoverOperators);
    this.args = args;
  }

  public CategoricalParameter<String> parse() {
    value = on("--crossover", args, Function.identity());

    for (Parameter<?> parameter : getGlobalParameters()) {
      parameter.parse().check();
    }

    getSpecificParameters()
        .forEach(
            (key, parameter) -> {
              if (key.equals(this.value)) {
                parameter.parse().check();
              }
            });

    return this;
  }

  @Override
  public String getName() {
    return "crossover";
  }
}
