package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.univocity.parsers.common.processor.BeanListProcessor;
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
import java.util.List;

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
        settings.setRowProcessor(rowProcessor);
        this.parser = new CsvParser(settings);
    }


    @PostConstruct
    public void read() throws IOException {
    parser.parse(getReader(RESOURCE_LOCATION));
    List<ProcessedFood> processedFoods = rowProcessor.getBeans();
    insertData(processedFoods);
    }


    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }


    private void insertData(List<ProcessedFood> processedFoods) {
        for (ProcessedFood processedFood : processedFoods) {
            if (!(processedFoodRepo.existsByBarcode(processedFood.getBarcode()))) {
                processedFoodRepo.saveAll(processedFoods);

                log.info("The processed food item: " + processedFood.getName() + ", has been imported");

            } else

                log.info("The processed food item with name: " + processedFood.getName() +
                        "and barcode: "+processedFood.getBarcode()+
                        " was not imported because it already exists");

        }
    }

}
