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
    for(int i = 0; i < 10; i++){
      List<Ammunition> items = readBooksFromCSV("ammunition.csv");
      items.remove(0);
      Backpack randomSolution = new Backpack(10000);
      Random randomGenerator = new Random();
      int index;
      Ammunition item;
      while(!items.isEmpty()){
        index = randomGenerator.nextInt(items.size());
        item = items.get(index);
        if((randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight())) > 0){
          randomSolution.ammunitions.add(item);
          items.remove(index);
          randomSolution.setOverallValue(randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight()));
          randomSolution.setOverallValue(randomSolution.getOverallValue() + Integer.parseInt(item.getPower()));
        }else{
          items.remove(index);
        }
      }
      backpackOfBackpacks.add(randomSolution);
    }
    Collections.sort(backpackOfBackpacks);
    System.out.println("\u001b[37m" + backpackOfBackpacks.toString());
//------------------------------------------------------------------------------//
    for(int j = 0; j < 100000; j++) {
      int HMCR = 70;
      Random randomPercents = new Random();
      int result = randomPercents.nextInt(101);
      if(result < HMCR){
        List<Ammunition> items = readBooksFromCSV("ammunition.csv");
        items.remove(0);
        Backpack randomSolution = new Backpack(10000);
        Random randomGenerator = new Random();
        int index;
        Ammunition item;
        while(!items.isEmpty()){
          index = randomGenerator.nextInt(items.size());
          item = items.get(index);
          if((randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight())) > 0){
            randomSolution.ammunitions.add(item);
            items.remove(index);
            randomSolution.setMaxBackpackWeight(randomSolution.getMaxBackpackWeight() - Integer.parseInt(item.getWeight()));
            randomSolution.setOverallValue(randomSolution.getOverallValue() + Integer.parseInt(item.getPower()));
          }else{
            items.remove(index);
          }
        }
        System.out.println(randomSolution);
        for (int i = 0; i < 10; i++) {
          if (backpackOfBackpacks.get(i).getOverallValue() < randomSolution.getOverallValue()) {
            System.out.println("\u001B[31m" +"I will add this");
            System.out.print("\u001b[37m");
            backpackOfBackpacks.remove(i);
            backpackOfBackpacks.add(randomSolution);
            break;
          }
        }
        //Collections.sort(backpackOfBackpacks);
        System.out.println(backpackOfBackpacks.toString());
      }
      else{
        System.out.println("HMCR - " + result);
        Backpack randomSolutionn = new Backpack(10000);
        List<Ammunition> items = readBooksFromCSV("ammunition.csv");
        for(int i = 0; i < 20; i++){
          Random r = new Random();
          int number = r.nextInt(10);
          randomSolutionn.setOverallValue(randomSolutionn.getOverallValue() + Integer.parseInt(backpackOfBackpacks.get(number).ammunitions.get(i).getPower()));
          randomSolutionn.setMaxBackpackWeight(randomSolutionn.getMaxBackpackWeight() - Integer.parseInt(backpackOfBackpacks.get(number).ammunitions.get(i).getWeight()));
        }

        System.out.println(randomSolutionn.getOverallValue());
        System.out.println(randomSolutionn.getMaxBackpackWeight());
        for (int i = 0; i < 10; i++) {
          if (backpackOfBackpacks.get(i).getOverallValue() < randomSolutionn.getOverallValue()) {
            System.out.println("\u001B[31m" +"I will add this");
            System.out.print("\u001b[37m");
            backpackOfBackpacks.remove(i);
            backpackOfBackpacks.add(randomSolutionn);
            break;
          }
        }
        Collections.sort(backpackOfBackpacks);
        System.out.println(backpackOfBackpacks.toString());
      }
    }

  }
  private static List<Ammunition> readBooksFromCSV(String fileName) {
    List<Ammunition> items = new ArrayList<>();
    Path pathToFile = Paths.get(fileName);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        Ammunition item = createBook(attributes);
        items.add(item);
        line = br.readLine();
      }
    }catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return items;
  }
  private static Ammunition createBook(String[] metadata) {
    String power = metadata[0];
    String weight = metadata[1];
    return new Ammunition(power,weight);
  }
}
