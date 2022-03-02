package org.uma.jmetal.experimental.auto.parameter.catalogue;

import org.uma.jmetal.experimental.auto.parameter.CategoricalParameter;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.localbestupdate.LocalBestUpdate;
import org.uma.jmetal.experimental.componentbasedalgorithm.catalogue.pso.localbestupdate.impl.DefaultLocalBestUpdate;
import org.uma.jmetal.util.comparator.DominanceComparator;

import java.util.List;

public class LocalBestUpdateParameter extends CategoricalParameter {
  public LocalBestUpdateParameter(String[] args, List<String> localBestUpdateStrategies) {
    super("localBestUpdate", args, localBestUpdateStrategies);
  }

  public LocalBestUpdate getParameter(DominanceComparator comparator) {
    LocalBestUpdate result;

    switch (getValue()) {
      case "defaultLocalBestUpdate":
        result = new DefaultLocalBestUpdate(comparator);
        break;
      default:
        throw new RuntimeException("Local best update component unknown: " + getValue());
    }

    return result;
  }
}
