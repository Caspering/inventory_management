package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcessedFoodCsvImporter {



    private final ProcessedFoodRepo processedFoodRepo;

    private final CsvParser parser;

    BeanListProcessor<ProcessedFood> rowProcessor = new BeanListProcessor<ProcessedFood>(ProcessedFood.class);

    ProcessedFoodCsvImporter(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        settings.setRowProcessor(rowProcessor);//just added this today
        this.parser = new CsvParser(settings);
    }

    @PostConstruct
    public void read() throws IOException, InterruptedException {



        parser.parse(getReader("/processed_food.csv"));

        List<ProcessedFood> processedFoods = rowProcessor.getBeans();

        insertData(processedFoods);

//        List<Record> records = this.parser
//                .parseAllRecords(getReader("/processed_food.csv"));
//
//        List<ProcessedFood> processedFoods = records.stream()
//                .map(ProcessedFood::new)
//                .collect(Collectors.toList());
//        insertData(processedFoods);
    }

    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass()
                .getResourceAsStream(s), "UTF-8");
    }

    private void insertData(List<ProcessedFood> processedFoods) {
        processedFoodRepo.saveAll(processedFoods);
    }
}
