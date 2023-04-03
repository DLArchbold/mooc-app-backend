package com.in28minutes.rest.webservices.restfulwebservices.applicationuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
//Allows us to perform operations (add, delete, update) on Feedback entity
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long>{

}
