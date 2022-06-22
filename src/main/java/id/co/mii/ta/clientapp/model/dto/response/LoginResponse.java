/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  id.co.mii.ta.clientapp.model.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Fathullah
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String fullname;
    private String username;
    private String email;
    private List<String> authorities;

}
