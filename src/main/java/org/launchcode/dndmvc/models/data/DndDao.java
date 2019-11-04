package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Dnd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DndDao extends CrudRepository<Dnd, Integer> {
}
