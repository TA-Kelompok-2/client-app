/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.model;

import id.co.mii.ta.clientapp.Status;
import java.time.LocalDateTime;
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
public class Request {

    private Long id;
    private String keterangan;
    private String gambar;
    private LocalDateTime date;
    private Employee employee;
    private Status status;
    private FasilitasRuang fasilitasRuang;
    private List<History> history;

}
