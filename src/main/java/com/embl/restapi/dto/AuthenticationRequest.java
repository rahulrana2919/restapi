/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest
{
    private String username;
    private String password;
}
