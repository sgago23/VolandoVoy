package com.example.VolandoVoy.utils;

import java.time.LocalDate;

public class FechaUtils {
    public static boolean fechaValida(LocalDate inicio, LocalDate fin){
        return !inicio.isAfter(fin);
    }
}
