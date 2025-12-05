package com.adrynun.datadinner.backend.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import com.adrynun.datadinner.backend.entity.Pedido;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Servicio encargado de generar reportes Jasper para los pedidos.
 */
@Service
public class JasperReportService {

    /**
     * Genera un ticket en PDF para un pedido.
     *
     * @param pedido Entidad Pedido con los productos y datos necesarios
     * @return Array de bytes con el PDF generado
     * @throws JRException Si ocurre un error en JasperReports
     */
    public byte[] generarTicket(Pedido pedido) throws JRException {
        // Cargar el reporte compilado .jasper desde recursos
        InputStream reportStream = getClass().getResourceAsStream("/reports/ticket.jasper");
        if (reportStream == null) {
            throw new RuntimeException("No se encontró el reporte ticket.jasper en /resources/reports/");
        }

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

        // Fuente de datos: lista de productos del pedido
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.getProductos());

        // Parámetros adicionales del reporte
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pedidoId", pedido.getId());
        parameters.put("fecha", pedido.getFechaHora());
        parameters.put("mesa", pedido.getMesa().getId());
        parameters.put("usuario", pedido.getUsuario().getNombreUsuario());

        // Llenar reporte y generar PDF
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generarTicket(Integer pedidoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarTicket'");
    }
}
