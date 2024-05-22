package fr.pepitruc.mandarine.repository;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;

import fr.pepitruc.mandarine.model.Station;

@Repository
public class StationRestRepository {

    private Map<Integer, Station> stationCache;

    public Station getStationById(final int stationId) {
        return this.stationCache.get(stationId);
    }
    
    public Station getStationByName(final String stationName) {
        return this.stationCache.entrySet().stream()
            .filter(entry -> stationName.trim().toUpperCase().equals(entry.getValue().getName().trim().toUpperCase()))
            .findFirst()
            .orElseThrow(Exception.class)) // TODO : Modifier çà.
            .getValue();
    }

}
