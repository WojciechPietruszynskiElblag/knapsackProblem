package solution;

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
    for (int i = 0; i < 100; i++) {
      List<Ammunition> ammunitionList = readAmmoFromCSV("ammunition.csv");
      ammunitionList.remove(0);
      Backpack randomSolution = new Backpack(55);
      Random randomGenerator = new Random();
      int index;
      Ammunition item;
      while (!ammunitionList.isEmpty()) {
        index = randomGenerator.nextInt(ammunitionList.size());
        item = ammunitionList.get(index);
        if ((randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight())) > 0) {
          randomSolution.ammunitions.add(item);
          ammunitionList.remove(index);
          randomSolution.setOverallValue(
              randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight()));
          randomSolution.setOverallValue(
              randomSolution.getOverallValue() + Integer.parseInt(item.getPower()));
        } else {
          ammunitionList.remove(index);
        }
      }
      backpackOfBackpacks.add(randomSolution);
    }
    Collections.sort(backpackOfBackpacks);
    System.out.println("\u001b[37m" + backpackOfBackpacks.toString());
    // -------------------WITH HMCR---------------------------------------//
    for (int j = 0; j < 1000; j++) {
      int HMCR = 70;
      Random randomPercents = new Random();
      int result = randomPercents.nextInt(101);
      if (result < HMCR) {
        List<Ammunition> items = readAmmoFromCSV("ammunition.csv");
        items.remove(0);
        Backpack randomSolution = new Backpack(55);
        Random randomGenerator = new Random();
        int index;
        Ammunition item;
        while (!items.isEmpty()) {
          index = randomGenerator.nextInt(items.size());
          item = items.get(index);
          if ((randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight())) > 0) {
            randomSolution.ammunitions.add(item);
            items.remove(index);
            randomSolution.setMaxBackpackWeight(
                randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight()));
            randomSolution.setOverallValue(
                randomSolution.getOverallValue() + Integer.parseInt(item.getPower()));
          } else {
            items.remove(index);
          }
        }
        System.out.println(randomSolution);
        for (int i = 0; i < 10; i++) {
          if (backpackOfBackpacks.get(i).getOverallValue() < randomSolution.getOverallValue()) {
            System.out.println("\u001B[31m" + "Dobre rozwiązanie");
            System.out.print("\u001b[37m");
            backpackOfBackpacks.remove(i);
            backpackOfBackpacks.add(randomSolution);
            break;
          }
        }
        System.out.println(backpackOfBackpacks.toString());
      } else {
        System.out.println("HMCR - " + result);
        Backpack backpack = new Backpack(45);
        List<Ammunition> items = readAmmoFromCSV("ammunition.csv");
        for (int i = 0; i < 20; i++) {
          Random r = new Random();
          int number = r.nextInt(10);
          backpack.setOverallValue(
              backpack.getOverallValue()
                  + Integer.parseInt(
                      backpackOfBackpacks.get(number).ammunitions.get(i).getPower()));
          backpack.setMaxBackpackWeight(
              backpack.getMaxBackpackWeight()
                  - Integer.parseInt(
                      backpackOfBackpacks.get(number).ammunitions.get(i).getWeight()));
        }

        System.out.println(backpack.getOverallValue());
        System.out.println(backpack.getMaxBackpackWeight());
        for (int i = 0; i < 10; i++) {
          if (backpackOfBackpacks.get(i).getOverallValue() < backpack.getOverallValue()) {
            System.out.println("\u001B[31m" + "Dobre rozwiązanie");
            backpackOfBackpacks.remove(i);
            backpackOfBackpacks.add(backpack);
            break;
          }
        }
        Collections.sort(backpackOfBackpacks);
        System.out.println(backpackOfBackpacks.toString());
      }
    }
  }

  private static List<Ammunition> readAmmoFromCSV(String fileName) {
    List<Ammunition> ammunitionList = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        Ammunition item = createBook(attributes);
        ammunitionList.add(item);
        line = br.readLine();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return ammunitionList;
  }

  private static Ammunition createBook(String[] metadata) {
    String power = metadata[0];
    String weight = metadata[1];
    return new Ammunition(power, weight);
  }
}
