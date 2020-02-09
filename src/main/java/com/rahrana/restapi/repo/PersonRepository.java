/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.repo;

import com.rahrana.restapi.dto.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>
{
}
