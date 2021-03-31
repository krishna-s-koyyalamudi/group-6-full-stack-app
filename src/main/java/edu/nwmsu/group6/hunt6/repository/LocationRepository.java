package edu.nwmsu.group6.hunt6.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.nwmsu.group6.hunt6.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
//	 @Query(value="SELECT location_name FROM locations",nativeQuery=true)
//	 Iterable<Location> findByName();
 

//	   @Query("from Location i where i.location_name =:location_name")
//	    //  @Query(value="SELECT * FROM item" "WHERE uses=:uses",nativeQuery=true)
//	    List<Location> findBySearch(@Param("location_name")String location_name);

}
