package fr.pepitruc.mandarine.service.csv.entity;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyWeatherReportCsvEntity implements Comparable<DailyWeatherReportCsvEntity> {

    @CsvBindByName(column = "00_Station")
    private String station;

    @CsvBindByName(column = "01_Date")
    @CsvDate("dd/MM/yyyy")
    private LocalDate date;

    @CsvBindByName(column = "02_Précipitations", locale = "fr")
    private double rainRate;

    @CsvBindByName(column = "03_Température_min_sous_abris", locale = "fr")
    private double minTempIndoor;

    @CsvBindByName(column = "04_Heure_température_min")
    @CsvDate("HH:mm")
    private LocalTime minTempTimeIndoor;

    @CsvBindByName(column = "05_Température_max_sous_abris", locale = "fr")
    private double maxTempIndoor;

    @CsvBindByName(column = "06_Heure_température_max")
    @CsvDate("HH:mm")
    private LocalTime maxTempTimeIndoor;

    @CsvBindByName(column = "07_Différence_température_sous_abris", locale = "fr")
    private double deltaTempIndoor;

    @CsvBindByName(column = "08_Température_moyenne_sous_abris", locale = "fr")
    private double avgHourlyTempIndoor;

    @CsvBindByName(column = "09_Période_de_gel", locale = "fr")
    private int freezingPeriodIndoor;

    @CsvBindByName(column = "10_Vitesse_moyenne_du_vent", locale = "fr")
    private double avgWindSpeed;

    @CsvBindByName(column = "11_Vitesse_max_du_vent", locale = "fr")
    private double maxWindSpeed;

    @CsvBindByName(column = "12_Heure_vitesse_max")
    @CsvDate("HH:mm")
    private LocalTime maxWindTime;

    @CsvBindByName(column = "13_Direction_vitesse_max", locale = "fr")
    private int maxWindDirection;

    @CsvBindByName(column = "14_Durée_insolation", locale = "fr")
    private int insolationPeriod;

    @CsvBindByName(column = "15_Rayonnement_global", locale = "fr")
    private int dailyGlobalSolarRadiation;

    @CsvBindByName(column = "16_Taux_humidité_min", locale = "fr")
    private int minHourlyHumidityPercentage;

    @CsvBindByName(column = "17_Heure_taux_humidité_min")
    @CsvDate("HH:mm")
    private LocalTime minHourlyHumidityPercentageTime;

    @CsvBindByName(column = "18_Taux_humidité_max", locale = "fr")
    private int maxHourlyHumidityPercentage;

    @CsvBindByName(column = "19_Heure_taux_humidité_max")
    @CsvDate("HH:mm")
    private LocalTime maxHourlyHumidityPercentageTime;

    @CsvBindByName(column = "20_Taux_humidité_moyen", locale = "fr")
    private int avgHourlyHumidityPercentage;

    @CsvBindByName(column = "21_ETP", locale = "fr")
    private double etp;

    @CsvBindByName(column = "22_Neige")
    private boolean snowing;

    @CsvBindByName(column = "23_Quantité_neige", locale = "fr")
    private double snowQuantity;

    @CsvBindByName(column = "24_Grêle")
    private boolean hailing;

    @CsvBindByName(column = "25_Gelée_blanche")
    private boolean freezing;

    @Override
    public int compareTo(DailyWeatherReportCsvEntity o) {

        var stationCompare = this.getStation().compareTo(o.getStation());
        if (stationCompare == 0)
            stationCompare = this.getDate().compareTo(o.getDate());
        return stationCompare;
    }
}
