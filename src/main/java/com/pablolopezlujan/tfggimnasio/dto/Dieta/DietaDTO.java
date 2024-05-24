package com.pablolopezlujan.tfggimnasio.dto.Dieta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.pablolopezlujan.tfggimnasio.dto.DietaComida.DietaComidaDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DietaDTO {
    private Long id;
    private String nombreDieta;
    private List<DietaComidaDTO> dietaComidas;
}