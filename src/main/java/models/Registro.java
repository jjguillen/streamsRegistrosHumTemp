package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class Registro {

    private LocalDateTime fechaHora;
    private Double temperatura;
    private Double humedad;
}
