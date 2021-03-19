package edu.nwmsu.group6.hunt6.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.nwmsu.group6.hunt6.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
