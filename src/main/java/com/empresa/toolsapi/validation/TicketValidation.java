package com.empresa.toolsapi.validation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TicketValidation {

    //2        100.90 C/U
    public BigDecimal totalCost(int rented, BigDecimal price){
        //100.90 C/U  * 2
        // Multiplica el precio unitario por la cantidad alquilada,
        // luego redondea el resultado a 2 decimales (estándar para valores monetarios),
        // usando el modo HALF_UP (redondeo comercial clásico).
        return price.multiply(BigDecimal.valueOf(rented)).setScale(2, RoundingMode.HALF_UP);
        //201.80
    }

    //2         //10
    public int totalAvailable(int rented , int dispo){
        return dispo - rented; //8
    }
}
