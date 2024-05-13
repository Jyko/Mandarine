package fr.pepitruc.mandarine.service.csv.entity;

import fr.pepitruc.mandarine.model.DailyWeatherReport;
import fr.pepitruc.mandarine.service.csv.CsvEntityConverter;

import java.util.Collection;
import java.util.List;

public class DailyWeatherReportCsvEntityConverter implements CsvEntityConverter<DailyWeatherReport, DailyWeatherReportCsvEntity> {

    @Override
    public List<DailyWeatherReportCsvEntity> convertToEntities(Collection<DailyWeatherReport> models) {
        return models.stream()
                .map(this::convertToEntity)
                .toList();
    }

    @Override
    public DailyWeatherReportCsvEntity convertToEntity(final DailyWeatherReport model) {

        return DailyWeatherReportCsvEntity.builder()
                .station(model.getStationName())
                .date(model.getDate())
                .rainRate(model.getRainRate())
                .minTempIndoor(model.getMinTempIndoor())
                .minTempTimeIndoor(model.getMinTempTimeIndoor())
                .maxTempIndoor(model.getMaxTempIndoor())
                .maxTempTimeIndoor(model.getMaxTempTimeIndoor())
                .deltaTempIndoor(model.getDeltaTempIndoor())
                .avgHourlyTempIndoor(model.getAvgHourlyTempIndoor())
                .freezingPeriodIndoor(model.getFreezingPeriodIndoor())
                .avgWindSpeed(model.getAvgWindSpeed())
                .maxWindSpeed(model.getMaxWindSpeed())
                .maxWindTime(model.getMaxWindTime())
                .maxWindDirection(model.getMaxWindDirection())
                .insolationPeriod(model.getInsolationPeriod())
                .dailyGlobalSolarRadiation(model.getDailyGlobalSolarRadiation())
                .minHourlyHumidityPercentage(model.getMinHourlyHumidityPercentage())
                .minHourlyHumidityPercentageTime(model.getMinHourlyHumidityPercentageTime())
                .maxHourlyHumidityPercentage(model.getMaxHourlyHumidityPercentage())
                .maxHourlyHumidityPercentageTime(model.getMaxHourlyHumidityPercentageTime())
                .avgHourlyHumidityPercentage(model.getAvgHourlyHumidityPercentage())
                .etp(model.getEtp())
                .snowing(model.isSnowing())
                .snowQuantity(model.getSnowQuantity())
                .hailing(model.isHailing())
                .freezing(model.isFreezing())
                .build();
    }
}
