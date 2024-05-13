package fr.pepitruc.mandarine.service.csv.entity;

import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.service.csv.CsvEntityConverter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DailyHydricReportCsvEntityConverter implements CsvEntityConverter<DailyWeatherReport, DailyHydricReportCsvEntity> {

    @Override
    public List<DailyHydricReportCsvEntity> convertToEntities(final Collection<DailyWeatherReport> models) {
        return models.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DailyHydricReportCsvEntity convertToEntity(final DailyWeatherReport model) {
        return DailyHydricReportCsvEntity.builder()
                .station(model.getStationName())
                .date(model.getDate())
                .rainRate(model.getEtp())
                .etp(model.getEtp())
                .build();
    }
}
