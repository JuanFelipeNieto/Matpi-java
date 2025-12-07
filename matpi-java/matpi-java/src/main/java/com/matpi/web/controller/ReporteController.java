package com.matpi.web.controller;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.dto.ReporteDto;
import com.matpi.dominio.servicios.PedidoService;
import com.matpi.dominio.servicios.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public String generarReporte(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            Model model) {
        
        // Solo generar reporte si hay al menos un filtro
        if (estado != null || fechaDesde != null || fechaHasta != null) {
            // Generar estadísticas
            ReporteDto reporte = reporteService.generarReporte(estado, fechaDesde, fechaHasta);
            model.addAttribute("reporte", reporte);
            
            // Obtener pedidos filtrados para la tabla
            List<PedidoDto> pedidos = pedidoService.filtrarPedidos(estado, fechaDesde, fechaHasta);
            model.addAttribute("pedidos", pedidos);
            
            // Mantener los valores de los filtros
            model.addAttribute("estado", estado);
            model.addAttribute("fechaDesde", fechaDesde);
            model.addAttribute("fechaHasta", fechaHasta);
        }
        
        model.addAttribute("activeModule", "reportes");
        return "reportes";
    }
}
