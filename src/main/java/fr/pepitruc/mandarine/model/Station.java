package fr.pepitruc.mandarine.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Station {

    private Integer id;
    private String name;
}
