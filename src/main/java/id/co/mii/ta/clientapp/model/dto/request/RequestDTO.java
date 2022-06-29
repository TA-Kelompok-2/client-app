/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.mii.ta.clientapp.model.dto.request;

import java.time.LocalDateTime;
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
public class RequestDTO {

    private String keterangan;
    private String gambar;
    private LocalDateTime date = LocalDateTime.now();
    private String picName;
    private Integer employee;
    private Integer status;
    private Integer fasilitasruang;
}
