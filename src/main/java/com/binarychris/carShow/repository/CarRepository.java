package com.binarychris.carShow.repository;

import com.binarychris.carShow.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {// data type for id is Long
    // Could use JpaRepository for extension, it has more methods to implement
    // extension provides implementation for this interface
}
// REST (Representational State Transfer)
    // is an architectural style for creating web services
    // Remember REST by heart
    // Six constraints:
        // Stateless : the server does not hold any information about the client state
        // Client and Server : person typing into browser, and service returning info to client
        // Cacheable : allows client to reuse previous fetched data
        // Uniform Interface : request from different client looks the same
        // Layered System : a set of hierarchical layers restricts components to interact only with adjacent layer
        // Code on Demand Option
// the difference between @RestController and @Controller is that @RestController has @ResponseBody
// @Component, which is in @Repository, allows us to create a new object, no need to type Class name = new Class;
// @Autowired will ask to create new object from class @Repository is in.