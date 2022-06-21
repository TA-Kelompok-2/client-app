/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fathullah
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private Long roles;

}
