package edu.nwmsu.group6.hunt6.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import antlr.collections.List;
import edu.nwmsu.group6.hunt6.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
//	 @Query(value="SELECT location_name FROM locations",nativeQuery=true)
//	 Iterable<Location> findByName();
 
	

}
