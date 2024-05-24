package com.pablolopezlujan.tfggimnasio.dto.DietaComida;

import com.pablolopezlujan.tfggimnasio.dto.Comida.ComidaDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietaComidaDTO {
    private Long id;
    private Long dietaId;
    private ComidaDTO comida;
    private String dia;
}