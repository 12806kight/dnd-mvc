package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Classes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassesDao extends CrudRepository<Classes, Long> {
    List<Classes> findByName(String name);
}
