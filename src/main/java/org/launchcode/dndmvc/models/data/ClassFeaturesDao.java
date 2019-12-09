package org.launchcode.dndmvc.models.data;

import org.launchcode.dndmvc.models.ClassFeatures;
import org.launchcode.dndmvc.models.Classes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassFeaturesDao extends CrudRepository<ClassFeatures, Long> {
    List<ClassFeatures> findByClasses(Classes classes);

}
