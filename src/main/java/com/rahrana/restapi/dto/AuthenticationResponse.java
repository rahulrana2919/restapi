/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthenticationResponse
{
    private final String jwt;
}
