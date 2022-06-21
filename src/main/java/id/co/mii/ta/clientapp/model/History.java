/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.model;

import java.time.LocalDateTime;
import javafx.animation.Animation.Status;
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
public class History {

    private Long id;
    private String keterangan;
    private LocalDateTime date;
    private Request request;
    private Employee employee;
    private Status status;
    
}
