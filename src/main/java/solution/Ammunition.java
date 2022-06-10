package solution;

public class Ammunition implements Comparable<Ammunition> {
  private String power;
  private String weight;

  public Ammunition(String power, String weight) {
    this.power = power;
    this.weight = weight;
  }

  public String getPower() {
    return power;
  }

  public void setPower(String power) {
    this.power = power;
  }

  public String getWeight() {
    return weight;
  }

  public void setWeight(String weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "Ammo" + ", power='" + power + '\'' + ", weight='" + weight + '\'';
  }

  @Override
  public int compareTo(Ammunition o) {
    return 0;
  }
}
