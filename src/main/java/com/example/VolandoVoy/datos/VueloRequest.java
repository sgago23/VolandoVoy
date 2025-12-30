package com.example.VolandoVoy.datos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record VueloRequest(
        @NotBlank String nombreVuelo,
        @NotBlank String empresa,
        @NotBlank String lugarSalida,
        @NotBlank String lugarLlegada,
        @NotNull LocalDate fechaSalida,
        @NotNull LocalDate fechaLlegada
) {}
