package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.FruitAndVege;
import com.kasperin.inventory_management.repository.FruitAndVegeRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

//@AllArgsConstructor
@Service
public class FruitAndVegeCsvImporter {
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
    public void read() throws IOException, InterruptedException {

        //parser.parse(getReader("/book.csv"));

        List<Record> records = this.parser
                .parseAllRecords(getReader("/book.csv"));

        List<FruitAndVege> fruitAndVeges = records.stream()
                .map(FruitAndVege::new)
                .collect(Collectors.toList());
        insertData(fruitAndVeges);

    }

    private Reader getReader(String s) throws UnsupportedEncodingException {
        return new InputStreamReader(this.getClass().getResourceAsStream(s
        ), "UTF-8");
    }

    private void insertData(List<FruitAndVege> fruitAndVeges) {
        fruitAndVegeRepository.saveAll(fruitAndVeges);
    }
}
