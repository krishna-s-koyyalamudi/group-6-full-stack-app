package edu.nwmsu.group6.hunt6.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.nwmsu.group6.hunt6.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Serializable> {

}
