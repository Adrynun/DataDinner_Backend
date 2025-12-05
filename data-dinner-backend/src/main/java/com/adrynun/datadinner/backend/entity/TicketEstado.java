package com.adrynun.datadinner.backend.entity;

/**
 * Define los estados posibles para un Ticket de Pedido, garantizando consistencia
 * y seguridad de tipos en lugar de usar un String.
 */
public enum TicketEstado {
    PENDIENTE,
    COBRADO
}