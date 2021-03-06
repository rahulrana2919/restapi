/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class Users
{
    @Id
    private String username;
    private String password;
    private boolean enabled;
}
