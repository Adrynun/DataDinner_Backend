package com.adrynun.datadinner.backend.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrynun.datadinner.backend.dto.FacturaDTO;
import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.service.FacturaService;
import com.adrynun.datadinner.backend.service.JasperReportService;
import com.adrynun.datadinner.backend.service.PedidoService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaService facturaService;
    private final PedidoService pedidoService;
    private final JasperReportService jasperReportService;

    public FacturaController(FacturaService facturaService,
            PedidoService pedidoService,
            JasperReportService jasperReportService) {
        this.facturaService = facturaService;
        this.pedidoService = pedidoService;
        this.jasperReportService = jasperReportService;
    }

    // Generar factura a partir de un pedido
    @PostMapping("/generar/{pedidoId}")
    public ResponseEntity<FacturaDTO> generarFactura(@PathVariable Integer pedidoId) {
        // Obtenemos la entidad Pedido
        Pedido pedido = pedidoService.getPedidoEntityById(pedidoId);
        FacturaDTO facturaDTO = facturaService.generarFactura(pedido);
        return ResponseEntity.ok(facturaDTO);
    }

    // Obtener facturas pendientes
    @GetMapping("/pendientes")
    public ResponseEntity<List<FacturaDTO>> getFacturasPendientes() {
        List<FacturaDTO> pendientes = facturaService.getFacturasPendientes();
        return ResponseEntity.ok(pendientes);
    }

    // Cerrar una factura
    @PostMapping("/cerrar/{id}")
    public ResponseEntity<Void> cerrarFactura(@PathVariable Integer id) {
        facturaService.cerrarFactura(id);
        return ResponseEntity.ok().build();
    }

    // Descargar ticket PDF de un pedido
    @GetMapping("/ticket/{pedidoId}")
    public ResponseEntity<byte[]> descargarTicket(@PathVariable Integer pedidoId) throws JRException {
        Pedido pedido = pedidoService.getPedidoEntityById(pedidoId);
        byte[] pdf = jasperReportService.generarTicket(pedido);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ticket_" + pedidoId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
