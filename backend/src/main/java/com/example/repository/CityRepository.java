package com.example.repository;

import com.example.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT DISTINCT c.name FROM City c WHERE UPPER(c.name) LIKE ?1% ORDER BY c.name ASC")
    List<String> findByNameStartsWithIgnoreCaseOrderByNameAsc(String name);
    @Query("SELECT DISTINCT c.name FROM City c WHERE UPPER(c.name) LIKE %?1% ORDER BY c.name ASC")
    List<String> findByNameContainsIgnoreCaseOrderByNameAsc(String name);
    @Query("SELECT DISTINCT c.name FROM City c WHERE UPPER(c.name) LIKE %?1 ORDER BY c.name ASC")
    List<String> findByNameEndsWithIgnoreCaseOrderByNameAsc(String name);

}
