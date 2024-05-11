package fr.pepitruc.mandarine.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class DailyWeatherReport {

    private String stationName;

    private LocalDate date;

    /**
     * Quantité de précipitation tombée en 24 heures (de 06h FU le jour J à 06h FU le jour J+1). La valeur relevée à J+1 est affectée au jour J (en mm et 1/10)
     */
    private double rainRate;

    /**
     * Température minimale sous abri (en °C et 1/10)
     */
    private double minTempIndoor;

    /**
     * Heure de la température minimale sous abri
     */
    private LocalTime minTempTimeIndoor;

    /**
     * Température maximale sous abri (en °C et 1/10)
     */
    private double maxTempIndoor;

    /**
     * Heure de température maximale sous abri
     */
    private LocalTime maxTempTimeIndoor;

    /**
     * Amplitude thermique quotidienne : écart entre Max et Min quotidiens (TX-TN) (en °C et 1/10)
     */
    private double deltaTempIndoor;

    /**
     * Température quotidienne minimale à 10 cm au-dessus du sol (en °C et 1/10)
     */
    private double minTempAtGroundLevel;

    /**
     * Moyenne quotidienne des températures horaires sous abri (en °C et 1/10)
     */
    private double avgHourlyTempIndoor;

    /**
     * Durée de gel sous abri (T ≤ 0°C) (en mn)
     */
    private int freezingPeriodIndoor;

    /**
     * Moyenne quotidienne de la force du vent moyenné sur 10 mn en ms/s à 10m
     */
    private double avgWindSpeed;

    /**
     * Maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn en ms/s à 10m
     */
    private double maxWindSpeed;

    /**
     * Heure du maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn
     */
    private LocalTime maxWindTime;

    /**
     * Direction du maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn (en rose de 360)
     */
    private int maxWindDirection;

    /**
     * Durée d’insolation quotidienne (en mn)
     */
    private int insolationPeriod;

    /**
     * Rayonnement global quotidien (en J/cm2)
     */
    private int dailyGlobalSolarRadiation;

    /**
     * Minimum quotidien des humidités relatives minimales horaires (en %)
     */
    private int minHourlyHumidityPercentage;

    /**
     * Heure du minimum quotidien des humidités relatives minimales horaires (HHmm)
     */
    private LocalTime minHourlyHumidityPercentageTime;

    /**
     * Maximum quotidien des humidités relatives minimales horaires (en %)
     */
    private int maxHourlyHumidityPercentage;

    /**
     * Heure du maximum quotidien des humidités relatives minimales horaires (HHmm)
     */
    private LocalTime maxHourlyHumidityPercentageTime;

    /**
     * Moyenne quotidienne des humidités relatives horaires (en %)
     */
    private int avgHourlyHumidityPercentage;

    /**
     * ETP calculée au point de grille le plus proche (en mm et 1/10)
     */
    private double etp;

    /**
     * Occurrence de neige (false s’il n’a pas neigé, true s'il a neigé)
     */
    private boolean snowing;

    /**
     * Hauteur de neige fraîche tombée en 24 heures (de 06h FU le jour J à 06h FU le jour J+1) qui reste au sol à 06h FU. La valeur relevée à J+1 est affectée au jour J (en cm)
     */
    private double snowQuantity;

    /**
     * Occurrence de grêle (false s'il n'a pas grêlé, true s'il a grêlé)
     */
    private boolean hailing;

    /**
     * Occurrence de gelée blanche (false s'il n'a pas gelé, true s'il a gelé)
     */
    private boolean freezing;

}
