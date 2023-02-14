package com.toniclecb.springtests.model.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toniclecb.springtests.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    
}
