package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.univocity.parsers.common.DataProcessingException;
import com.univocity.parsers.common.processor.BeanListProcessor;
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

@Slf4j
@ConditionalOnResource(resources = ProcessedFoodCsvImporter.RESOURCE_LOCATION)
@Service
public class ProcessedFoodCsvImporter {

    public static final String RESOURCE_LOCATION = "/processed_food.csv";
    private final ProcessedFoodRepo processedFoodRepo;
    private final CsvParser parser;

    BeanListProcessor<ProcessedFood> rowProcessor = new BeanListProcessor<ProcessedFood>(ProcessedFood.class);


    ProcessedFoodCsvImporter(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        settings.setProcessor(rowProcessor);

        this.parser = new CsvParser(settings);
    }


    @PostConstruct
    public void read() throws IOException, DataProcessingException {
        //Map to store and compare barcodes in record for duplicates
        Map<String, ProcessedFood> records = new HashMap<>();

        parser.parse(getReader(RESOURCE_LOCATION));

       //iterate through the csv data
        for (ProcessedFood record : rowProcessor.getBeans()) {

            //do not pick items with duplicates barcode
            if (!records.containsKey(record.getBarcode())) {

                try {

                  Date expDate = record.getExpDate();
                  Date mfgDate = record.getMfgDate();

                  //compare dates
                  int diff = expDate.compareTo(mfgDate);

                  if (diff == 0) {

                      log.error(expDate + " is Equal to " + mfgDate);

                  } else if (diff > 0) {

                      records.put(record.getBarcode(), record);
                      log.info(expDate + " is AFTER " + mfgDate);

                  } else {

                      log.error(expDate + " is BEFORE " + mfgDate);

                  }

              }catch (DataProcessingException e){
                  log.error("can not convert: " + e);
              }

            }
        }

        List<ProcessedFood> processedFoods = new ArrayList<>(records.values());

        insertData(processedFoods);
    }


    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }


    private void insertData(List<ProcessedFood> processedFoods) {
        for (ProcessedFood processedFood : processedFoods) {
            if (!(processedFoodRepo.existsByBarcode(processedFood.getBarcode()))) {
                processedFoodRepo.save(processedFood);

                log.info("The processed food item: " + processedFood.getName() + ", has been imported");
            }
            else
                log.warn("The processed food item with name: " + processedFood.getName() +
                        "and barcode: "+processedFood.getBarcode()+
                        " was not imported because it already exists");
        }
    }

}
