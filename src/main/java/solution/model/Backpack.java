package solution.model;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements Comparable<Backpack> {
  private int overallValue = 0;
  private int maxBackpackWeight;
  public List<Ammunition> ammunition = new ArrayList<>();

  public Backpack(int maxBackpackWeight) {
    this.maxBackpackWeight = maxBackpackWeight;
  }

  public int getOverallValue() {
    return overallValue;
  }

  public void setOverallValue(int overallValue) {
    this.overallValue = overallValue;
  }

  public int getMaxBackpackWeight() {
    return maxBackpackWeight;
  }

  public void setMaxBackpackWeight(int maxBackpackWeight) {
    this.maxBackpackWeight = maxBackpackWeight;
  }

  public List<Ammunition> getAmmunition() {
    return ammunition;
  }

  public void setAmmunition(List<Ammunition> ammunitions) {
    this.ammunition = ammunitions;
  }

  @Override
  public String toString() {
    return String.valueOf(this.getOverallValue());
  }

  @Override
  public int compareTo(Backpack backpack) {
    int compareCost = backpack.getOverallValue();
    return this.overallValue - compareCost;
  }
}
