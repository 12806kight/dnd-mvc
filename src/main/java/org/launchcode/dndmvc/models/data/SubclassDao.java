package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.Classes;
import org.launchcode.dndmvc.models.Subclasses;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubclassDao extends CrudRepository<Subclasses, Long>{

    List<Subclasses> findByClasses(Classes classes);

}
