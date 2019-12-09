package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Alignment;
import org.springframework.data.repository.CrudRepository;

public interface AlignmentDao extends CrudRepository<Alignment, Long> {
}
