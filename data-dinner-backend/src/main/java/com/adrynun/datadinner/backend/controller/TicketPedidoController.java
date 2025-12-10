package com.adrynun.datadinner.backend.controller;

import com.adrynun.datadinner.backend.service.JasperReportService;

import net.sf.jasperreports.engine.JRException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketPedidoController {

    private final JasperReportService jasperReportService;

    public TicketPedidoController(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    // --------------------------------------------------
    // Generar ticket PDF de un pedido
    // --------------------------------------------------
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<byte[]> generarTicket(@PathVariable Integer pedidoId) throws JRException {

        byte[] pdfBytes = jasperReportService.generarTicket(pedidoId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ticket_pedido_" + pedidoId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
