package com.bezkoder.spring.entity.repo;




import com.bezkoder.spring.entity.model.Kos;
import com.bezkoder.spring.login.models.User;

import org.springframework.data.repository.CrudRepository;

public interface KosRepo extends CrudRepository<Kos, Long>  {
    Iterable<Kos> findByOwner (User owner);
}
    
