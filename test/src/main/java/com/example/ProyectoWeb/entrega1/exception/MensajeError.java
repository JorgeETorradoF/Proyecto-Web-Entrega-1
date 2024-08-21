package com.example.ProyectoWeb.entrega1.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MensajeError {
    
    private String messaje;
    private Date timestamp;
    private int status;
    private String error;
}
