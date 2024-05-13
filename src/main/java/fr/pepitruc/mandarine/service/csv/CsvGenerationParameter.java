package fr.pepitruc.mandarine.service.csv;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CsvGenerationParameter<M, E> {

    private CsvEntityConverter<M, E> converter;
    private String fileNamePrefix;
    private int departement;
    private List<String> stationsName;
}
