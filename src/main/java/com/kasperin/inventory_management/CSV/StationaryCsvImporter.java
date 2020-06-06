package com.kasperin.inventory_management.CSV;

import com.kasperin.inventory_management.domain.Items.Stationary;
import com.kasperin.inventory_management.repository.ItemsRepository.StationaryRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnResource(resources = StationaryCsvImporter.RESOURCE_LOCATION)
@Service
public class StationaryCsvImporter {

    public static final String RESOURCE_LOCATION = "/stationary.csv";
    private final StationaryRepository stationaryRepository;
    private final CsvParser parser;


    StationaryCsvImporter(StationaryRepository stationaryRepository) {
        this.stationaryRepository = stationaryRepository;

        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setLineSeparatorDetectionEnabled(true);
        this.parser = new CsvParser(settings);
    }


    @PostConstruct
    public void read() throws IOException, NullPointerException, IllegalArgumentException{
        //Map to store and compare barcodes in record for duplicates
        Map<String, Record> records = new HashMap<>();
        try {
            // call beginParsing to read records one by one, iterator-style.
            parser.beginParsing(getReader(RESOURCE_LOCATION));

            Record record;

            while ((record = parser.parseNextRecord()) != null) {
                if (!records.containsKey(record.getString("barcode")))

                    records.put(record.getString("barcode"), record);

                System.out.println(record.getString("name") + " was added to records");
            }

            //convert Map to List
            List<Record> recordList = new ArrayList<>(records.values());


            List<Stationary> stationarys = recordList
                    .stream()
                    .map(Stationary::new)
                    .collect(Collectors.toList());

            insertData(stationarys);
        }catch(NullPointerException | IllegalArgumentException e){
            log.error("Exception caught " + e);
            e.printStackTrace();
        }
    }

    private Reader getReader(String s) throws UnsupportedEncodingException {

                return new InputStreamReader(this.getClass()
                        .getResourceAsStream(s), "UTF-8");
    }

    private void insertData(List<Stationary> stationarys) {
        for (Stationary stationary : stationarys) {
            if (!(stationaryRepository.existsByBarcode(stationary.getBarcode()))) {
                stationaryRepository.save(stationary);

                log.info("The stationary item with name: " + stationary.getName() + ", has been imported");

            } else

                log.info("The stationary with name: " + stationary.getName() +
                        "and barcode: "+stationary.getBarcode()+
                        " was not imported because it already exists");

        }
    }

}
