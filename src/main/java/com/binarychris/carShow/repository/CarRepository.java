package com.binarychris.carShow.repository;

import com.binarychris.carShow.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {// data type for id is Long
    // Could use JpaRepository for extension, it has more methods to implement

}
