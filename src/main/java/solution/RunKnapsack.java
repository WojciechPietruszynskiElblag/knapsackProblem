package solution;

import solution.model.Ammunition;
import solution.model.Backpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RunKnapsack {
  public static void main(String... args) {
    List<Backpack> backpackOfBackpacks = new ArrayList<>();
    generateWithoutHCMR(backpackOfBackpacks);
            generateWithHCMR(backpackOfBackpacks);
  }

  private static void generateWithHCMR(List<Backpack> backpackList) {
    for (int j = 0; j < 20; j++) {
      int HMCR = 60;
      Random randomPercents = new Random();
      int result = randomPercents.nextInt(80);
      if (result < HMCR) {
        List<Ammunition> ammunitions = readBooksFromCSV("ammunition.csv");
        ammunitions.remove(0);
        Backpack randomBackpack = new Backpack(100);
        Random randomGenerator = new Random();
//        Ammunition ammunition;
        while (!ammunitions.isEmpty()) {
          int index = randomGenerator.nextInt(ammunitions.size());
          Ammunition ammunition = ammunitions.get(index);
          if ((randomBackpack.getMaxBackpackWeight() - Integer.parseInt(ammunition.getWeight()))
              > 0) {
            randomBackpack.ammunition.add(ammunition);
            ammunitions.remove(index);

            randomBackpack.setMaxBackpackWeight(
                randomBackpack.getMaxBackpackWeight() - Integer.parseInt(ammunition.getWeight()));
            randomBackpack.setOverallValue(
                randomBackpack.getOverallValue() + Integer.parseInt(ammunition.getPower()));
          } else {
            ammunitions.remove(index);
          }
        }
        System.out.println(randomBackpack);
        for (int i = 0; i < 5; i++) {
          if (backpackList.get(i).getOverallValue() < randomBackpack.getOverallValue()) {
            System.out.println("\u001B[31m" + "Extra!! Add this");
            System.out.print("\u001b[37m");
            backpackList.remove(i);
            backpackList.add(randomBackpack);
            break;
          }
        }
        Collections.sort(backpackList);
        System.out.println("ZZZZZZZZZZZZZZ"+backpackList.toString());
      } else {
        System.out.println("HMCR - " + result);
        Backpack randomSolutionn = new Backpack(100);
        List<Ammunition> ammunitions = readBooksFromCSV("ammunition.csv");
        for (int i = 0; i < 20; i++) {
          Random r = new Random();
          int number = r.nextInt(10);
          randomSolutionn.setOverallValue(
              randomSolutionn.getOverallValue()
                  + Integer.parseInt(backpackList.get(number).ammunition.get(i).getPower()));
          randomSolutionn.setMaxBackpackWeight(
              randomSolutionn.getMaxBackpackWeight()
                  - Integer.parseInt(
                      backpackList.get(number).ammunition.get(i).getWeight()));
        }

        System.out.println(randomSolutionn.getOverallValue());
        System.out.println(randomSolutionn.getMaxBackpackWeight());
        for (int i = 0; i < 5; i++) {
          if (backpackList.get(i).getOverallValue() < randomSolutionn.getOverallValue()) {
            System.out.println("\u001B[31m" + "Extra!! Add this");
            System.out.print("\u001b[37m");
            backpackList.remove(i);
            backpackList.add(randomSolutionn);
            break;
          }
        }
        Collections.sort(backpackList);
        System.out.println(backpackList);
      }
    }
  }

  private static void generateWithoutHCMR(List<Backpack> backpackList) {
    for (int i = 0; i < 20; i++) {
      List<Ammunition> ammo = readBooksFromCSV("ammunition.csv");
      ammo.remove(0);
      Backpack randomBackpack = new Backpack(150);
      Random randomGenerator = new Random();
      int index;
      Ammunition ammunition;
      while (!ammo.isEmpty()) {
        index = randomGenerator.nextInt(ammo.size());
        ammunition = ammo.get(index);
        if ((randomBackpack.getMaxBackpackWeight() - Integer.parseInt(ammunition.getWeight()))
            > 0) {
          randomBackpack.ammunition.add(ammunition);
          ammo.remove(index);
          randomBackpack.setMaxBackpackWeight(
              randomBackpack.getMaxBackpackWeight() - Integer.parseInt(ammunition.getWeight()));
          randomBackpack.setOverallValue(
              randomBackpack.getOverallValue() + Integer.parseInt(ammunition.getPower()));
        } else {
          ammo.remove(index);
        }
      }
      backpackList.add(randomBackpack);
    }
    Collections.sort(backpackList);

    backpackList
        .forEach(
            power -> {
              System.out.println("Max power in backpack :" + power);
            });
  }

  private static List<Ammunition> readBooksFromCSV(String fileName) {
    List<Ammunition> ammunition = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        Ammunition item = createAmmunitionObj(attributes);
        ammunition.add(item);
        line = br.readLine();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return ammunition;
  }

  private static Ammunition createAmmunitionObj(String[] metadata) {
    String power = metadata[0];
    String weight = metadata[1];
    return new Ammunition(power, weight);
  }
}
