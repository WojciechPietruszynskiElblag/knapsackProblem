package generator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import generator.model.Ammunition;
import generator.service.AmmunitionGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RunGenerate {
    public static void main(String[] args) throws IOException {
        final int listSize = 2000;
        AmmunitionGenerator generator = new AmmunitionGenerator();
        generator.ammunitionList(listSize);
        // TODO Zorganizować normalną ścieżkę
        File csvOutputFile = new File("D:\\user_output-.csv");


        List<Ammunition> list = generator.getAmmunitionList();
        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("size")
                .addColumn("power")
                .addColumn("type")
                .addColumn("weight")
                .build();

        ObjectWriter writer = mapper.writerFor(Ammunition.class).with(schema);

        writer.writeValues(csvOutputFile).writeAll(list);

        System.out.println("File save in localization : " + csvOutputFile);

    }
}