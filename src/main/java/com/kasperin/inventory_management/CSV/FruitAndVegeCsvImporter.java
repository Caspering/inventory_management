package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnResource(resources = FruitAndVegeCsvImporter.RESOURCE_LOCATION)
@Service
public class FruitAndVegeCsvImporter {

    public static final String RESOURCE_LOCATION = "/food.csv";
    private final FruitAndVegeRepository fruitAndVegeRepository;
    private final CsvParser parser;


    FruitAndVegeCsvImporter(FruitAndVegeRepository fruitAndVegeRepository) {
        this.fruitAndVegeRepository = fruitAndVegeRepository;

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        this.parser = new CsvParser(settings);
    }




    @PostConstruct
    public void read() throws IOException {

          List<Record> records = this.parser
                  .parseAllRecords(getReader(RESOURCE_LOCATION));

          List<FruitAndVege> fruitAndVeges = records.stream()
                  .map(FruitAndVege::new)
                  .collect(Collectors.toList());
//        for (FruitAndVege fruitAndVege : fruitAndVeges) {
//            if (!(fruitAndVegeRepository.existsByBarcode(fruitAndVege.getBarcode()))) {
                insertData(fruitAndVeges);
//                log.info("This in the read method Stationary item: " + fruitAndVege.getName() + ", has been parsed to insert method");
//            }
//        }
    }

    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }

    private void insertData(List<FruitAndVege> fruitAndVeges) {
        for (FruitAndVege fruitAndVege : fruitAndVeges) {
            if (!(fruitAndVegeRepository.existsByBarcode(fruitAndVege.getBarcode()))) {
                fruitAndVegeRepository.saveAll(fruitAndVeges);

                log.info("The fruit or vegetable item with name: " + fruitAndVege.getName() + ", has been imported");

            } else

            log.info("A fruit or vegetable with name: " + fruitAndVege.getName() +
                        "and barcode: "+fruitAndVege.getBarcode()+
                        " was not imported because it already exists");

        }

    }

}
