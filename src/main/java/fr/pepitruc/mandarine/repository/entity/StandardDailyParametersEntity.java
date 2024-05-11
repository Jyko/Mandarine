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
public class StandardDailyParametersEntity extends DailyParametersEntity {

    // ########## RAIN ##########

    /** Quantité de précipitation tombée en 24 heures (de 06h FU le jour J à 06h FU le jour J+1). La valeur relevée à J+1 est affectée au jour J (en mm et 1/10) */
    @CsvBindByName(column = "RR")
    private double rainRate;

    // ########## TEMPERATURE ##########

    /** Température minimale sous abri (en °C et 1/10) */
    @CsvBindByName(column = "TN")
    private double minTempIndoor;

    /** Heure de la température minimale sous abri (HHmm) */
    @CsvBindByName(column = "HTN")
    private String minTempTimeIndoor;

    /** Température maximale sous abri (en °C et 1/10) */
    @CsvBindByName(column = "TX")
    private double maxTempIndoor;

    /** Heure de température maximale sous abri (HHmm) */
    @CsvBindByName(column = "HTX")
    private String maxTempTimeIndoor;

    /** Moyenne quotidienne des températures horaires sous abri (en °C et 1/10) */
    @CsvBindByName(column = "TM")
    private double avgHourlyTempIndoor;

    /** Amplitude thermique quotidienne : écart entre Max et Min quotidiens (TX-TN) (en °C et 1/10) */
    @CsvBindByName(column = "TAMPLI")
    private double deltaTempIndoor;

    /** Température quotidienne minimale à 10 cm au-dessus du sol (en °C et 1/10) */
    @CsvBindByName(column = "TNSOL")
    private double minTempAtGroundLevel;

    /** Durée de gel sous abri (T ≤ 0°C) (en mn) */
    @CsvBindByName(column = "DG")
    private int freezingPeriodIndoor;

    // ########## WIND ##########

    /** Moyenne quotidienne de la force du vent moyenné sur 10 mn en ms/s à 10m */
    @CsvBindByName(column = "FFM")
    private double avgWindSpeed;

    /** Maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn en ms/s à 10m */
    @CsvBindByName(column = "FXY")
    private double maxWindSpeed;

    /** Heure du maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn (HHmm) */
    @CsvBindByName(column = "HXY")
    private String maxWindTime;

    /** Direction du maximum quotidien de la force maximale horaire du vent moyenné sur 10 mn (en rose de 360) */
    @CsvBindByName(column = "DXY")
    private int maxWindDirection;

}
