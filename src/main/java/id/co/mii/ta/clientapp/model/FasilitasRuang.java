/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.model;

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
public class FasilitasRuang {
    
    private Integer id;
    private Ruang ruang;
    private Fasilitas fasilitas;
//    private List<Request> requests;
    
}
