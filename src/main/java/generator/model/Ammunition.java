package generator.model;

public class Ammunition {
    private int size;
    private int power;
    private int type;
    private int weight;

    public Ammunition(int size, int power, int type, int weight) {
        this.size = size;
        this.power = power;
        this.type = type;
        this.weight = weight;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public int getPower() {
        return power;
    }

    public int getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public void getWithDesc(){
        System.out.println("Power" + getPower());

    }
}
