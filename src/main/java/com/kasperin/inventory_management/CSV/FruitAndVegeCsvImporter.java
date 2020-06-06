package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.Items.FruitAndVege;
import com.kasperin.inventory_management.repository.ItemsRepository.FruitAndVegeRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnResource(resources = FruitAndVegeCsvImporter.RESOURCE_LOCATION)
@Service
public class FruitAndVegeCsvImporter {

    public static final String RESOURCE_LOCATION = "/fruitAndVegetables.csv";
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
    public void read() throws IOException, NullPointerException, IllegalArgumentException {

        //Map to store and compare barcodes in record for duplicates
        Map<String, Record> records = new HashMap<>();
        try{
        // call beginParsing to read records one by one, iterator-style.
        parser.beginParsing(getReader(RESOURCE_LOCATION));

        Record record;

        while ((record = parser.parseNextRecord()) != null) {
            if (!records.containsKey(record.getString("barcode") ))

                records.put(record.getString("barcode"),record);

            log.info(record.getString("name") + " was added to parsed");
        }

        //convert Map to List
        List<Record> recordList = new ArrayList<>(records.values());

        List<FruitAndVege> fruitAndVeges = recordList
                .stream()
                .map(FruitAndVege::new)
                .collect(Collectors.toList());

        insertData(fruitAndVeges);
        }catch(NullPointerException | IllegalArgumentException e){
            log.error("Exception caught " + e);
            e.printStackTrace();
        }
    }


    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }


    private void insertData(List<FruitAndVege> fruitAndVeges) {
        for (FruitAndVege fruitAndVege : fruitAndVeges) {
            if (!(fruitAndVegeRepository.existsByBarcode(fruitAndVege.getBarcode()))) {
                fruitAndVegeRepository.save(fruitAndVege);

                log.info("The fruit or vegetable item with name: " + fruitAndVege.getName() + ", has been imported");

            } else {
                log.warn("A fruit or vegetable with name: " + fruitAndVege.getName() +
                        ", and barcode: " + fruitAndVege.getBarcode() +
                        " was not imported because it already exists in the database");
            }
        }
    }
}
