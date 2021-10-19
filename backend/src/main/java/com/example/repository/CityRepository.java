package com.example.repository;

import com.example.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameStartsWithIgnoreCaseOrderByNameAsc(String name);
    List<City> findByNameContainsIgnoreCaseOrderByNameAsc(String name);
    List<City> findByNameEndsWithIgnoreCaseOrderByNameAsc(String name);

}
