package fr.pepitruc.mandarine.repository.converter;

import com.opencsv.bean.CsvToBeanFilter;

import java.util.Arrays;
import java.util.List;

public class CSVBeanFilterOnStation implements CsvToBeanFilter {

    private final List<String> stationsNameAllowed;

    public  CSVBeanFilterOnStation(final List<String> stationsName) {
        this.stationsNameAllowed = stationsName;
    }

    @Override
    public boolean allowLine(final String[] line) {
        var stationName = line[1].trim().toUpperCase();
        return stationsNameAllowed.contains(stationName);
    }
}
