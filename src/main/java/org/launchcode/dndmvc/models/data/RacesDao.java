package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Races;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RacesDao extends CrudRepository<Races, Long> {
    List<Races> findByName(String name);

}
