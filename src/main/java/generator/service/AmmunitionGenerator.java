package generator.service;

import generator.model.Ammunition;

import java.util.ArrayList;
import java.util.List;

public class AmmunitionGenerator {

    private List<Ammunition> ammunitionList = new ArrayList<>();

    public List<Ammunition> getAmmunitionList() {
        return ammunitionList;
    }

    public AmmunitionGenerator() {
        this.ammunitionList = ammunitionList;
    }

    public List<Ammunition> ammunitionList(int listSize) {


        for (int i = 0; i < listSize; i++) {
            ammunitionList.add(new Ammunition(randomValue(1, 9), randomValue(9, 50), randomValue(1, 20), randomValue(3, 45)));
        }

        return ammunitionList;

    }

    private int randomValue(int min, int max) {

        return (int) ((Math.random() * (max - min)) + min);
    }


}
