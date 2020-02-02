/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.repo;

import com.embl.restapi.dto.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String>
{
}
