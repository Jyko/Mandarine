package fr.pepitruc.mandarine.repository.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

/*
 * OpenCSV being a big pile of hot garbage and the quality of the parsed files being abysmal,
 * I choose to parse some data as String and perform the right cast afterward in Repository.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OtherDailyParametersEntity extends DailyParametersEntity {

    // ########## RAYONNEMENT ##########

    /** Durée d’insolation quotidienne (en mn) */
    @CsvBindByName(column = "INST")
    private int insolationPeriod;

    /** Rayonnement global quotidien (en J/cm2) */
    @CsvBindByName(column = "GLOT")
    private int dailyGlobalSolarRadiation;

    // ########## HUMIDITE ##########

    /** Minimum quotidien des humidités relatives minimales horaires (en %) */
    @CsvBindByName(column = "UN")
    private int minHourlyHumidityPercentage;

    /** Heure du minimum quotidien des humidités relatives minimales horaires (HHmm) */
    @CsvBindByName(column = "HUN")
    private String minHourlyHumidityPercentageTime;

    /** Maximum quotidien des humidités relatives minimales horaires (en %) */
    @CsvBindByName(column = "UX")
    private int maxHourlyHumidityPercentage;

    /** Heure du maximum quotidien des humidités relatives minimales horaires (HHmm) */
    @CsvBindByName(column = "HUX")
    private String maxHourlyHumidityPercentageTime;

    /** Moyenne quotidienne des humidités relatives horaires (en %) */
    @CsvBindByName(column = "UM")
    private int avgHourlyHumidityPercentage;

    // ########## ETP ##########
    /** ETP calculée au point de grille le plus proche (en mm et 1/10) */
    @CsvBindByName(column = "ETPGRILLE")
    private double etp;

    // ########## AUTRES PHENOMENES ##########

    /** Occurrence de neige (0 s’il n’a pas neigé, 1 s'il a neigé) */
    @CsvBindByName(column = "NEIG")
    private int snowing;

    /** Hauteur de neige fraîche tombée en 24 heures (de 06h FU le jour J à 06h FU le jour J+1) qui reste au sol à 06h FU. La valeur relevée à J+1 est affectée au jour J (en cm) */
    @CsvBindByName(column = "HNEIGEF")
    private double snowQuantity;

    /** Occurrence de grêle (0 s'il n'a pas grêlé, 1 s'il a grêlé) */
    @CsvBindByName(column = "GRELE")
    private int hailing;

    /** Occurrence de gelée blanche (0 s'il n'a pas gelé, 1 s'il a gelé) */
    @CsvBindByName(column = "GELEE")
    private int freezing;
}
