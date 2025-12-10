package com.adrynun.datadinner.backend.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.adrynun.datadinner.backend.entity.Pedido;
import com.adrynun.datadinner.backend.repository.PedidoRepository;

import jakarta.annotation.PostConstruct;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Servicio encargado de generar reportes Jasper para los pedidos.
 * Compila automáticamente el JRXML al iniciar la aplicación.
 */
@Service
public class JasperReportService {

    private JasperReport jasperReport;
    private final PedidoRepository pedidoRepository;

    // Inyección de PedidoService
    public JasperReportService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /**
     * Inicializa y compila el reporte JRXML al iniciar la aplicación.
     */
    @PostConstruct
    public void init() throws JRException {
        InputStream reportStream = getClass().getResourceAsStream("/reports/ticket_factura.jrxml");
        if (reportStream == null) {
            throw new RuntimeException("No se encontró el reporte ticket_factura.jrxml en /resources/reports/");
        }

        // Compila el JRXML a JasperReport
        jasperReport = JasperCompileManager.compileReport(reportStream);
    }

    /**
     * Genera un ticket en PDF para un pedido.
     *
     * @param pedido Entidad Pedido con los productos y datos necesarios
     * @return Array de bytes con el PDF generado
     * @throws JRException Si ocurre un error en JasperReports
     */
    public byte[] generarTicket(Pedido pedido) throws JRException {
        // Fuente de datos: lista de productos del pedido
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.getProductos());

        // Parámetros adicionales del reporte
        Map<String, Object> parameters = new HashMap<>();

        // 1. Coincidir con el JRXML
        parameters.put("idPedido", pedido.getId());
        // 2. Coincidir con el JRXML
        parameters.put("fechaPedido", pedido.getFechaHora().toString()); // Convertir a String si es necesario
        // 3. Coincidir con el JRXML
        parameters.put("nombreCliente", pedido.getUsuario().getNombreUsuario());

        // Este ya coincidía
        parameters.put("mesa", pedido.getMesa().getId().toString()); // Convertir Mesa ID a String si el JRXML lo espera
                                                                     // así

        // 4. Agregar el parámetro totalPedido que el JRXML espera, pero Java no envía
        // *Necesitas un método para calcular el total del pedido, aquí se asume
        // pedido.getTotal()*
        parameters.put("totalPedido", pedido.calcularTotal());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public byte[] generarTicket(Integer pedidoId) throws JRException {
        // 1. USAR EL NUEVO MÉTODO PARA CARGAR DETALLES
        Pedido pedido = pedidoRepository.findByIdWithDetails(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + pedidoId));

        // El resto del código que ya tienes (lo agregué de nuevo para claridad)
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.getProductos());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("idPedido", pedido.getId());
        parameters.put("fechaPedido", pedido.getFechaHora().toString());
        parameters.put("nombreCliente", pedido.getUsuario().getNombreUsuario());
        parameters.put("mesa", pedido.getMesa().getId().toString());
        parameters.put("totalPedido", pedido.calcularTotal());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
