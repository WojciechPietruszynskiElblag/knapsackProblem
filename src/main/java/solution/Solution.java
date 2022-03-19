package solution;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import generator.dto.AmmunitionDTO;
import generator.model.Ammunition;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {


    public static void main(String[] args) throws IOException {


        File csvFile = new File("D:\\user_output.csv");

        System.out.println("Example 1 - from file");
        List<Ammunition> readAmmunitionList = readCsvFromFile(csvFile);
        readAmmunitionList.forEach(System.out::println);
    }


    private static List<Ammunition> readCsvFromFile(File csvFile) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        ObjectReader oReader = csvMapper.reader(Ammunition.class).with(schema);
        List<Ammunition> courses = new ArrayList<>();

        try (Reader reader = new FileReader(csvFile)) {
            MappingIterator<Ammunition> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                Ammunition current = mi.next();
                courses.add(current);
                System.out.println(current);
            }
        } catch (FileNotFoundException ignored) {


        } catch (IOException e) {
            e.printStackTrace();
        }


        return courses;
    }
}




