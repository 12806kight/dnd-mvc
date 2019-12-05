package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Dungeon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface DungeonRepository extends CrudRepository<Dungeon, Long> {

    List<Dungeon> findByName(String name);


}
