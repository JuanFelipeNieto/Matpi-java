package com.matpi.web.controller;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Vista principal de pedidos
    @GetMapping
    public String getPedidosPage(Model model) {
        model.addAttribute("titulo", "Gestión de Pedidos - Matpi");
        model.addAttribute("activeModule", "pedidos");
        model.addAttribute("pedidos", pedidoService.getAll());
        return "pedidos";
    }

    // Vista de reportes
    @GetMapping("/reportes")
    public String getReportesPage(Model model) {
        model.addAttribute("titulo", "Reportes de Pedidos - Matpi");
        model.addAttribute("activeModule", "pedidos");
        return "pedidos-reportes";
    }

    // ===== API REST ENDPOINTS =====

    @GetMapping("/api")
    @ResponseBody
    public List<PedidoDto> getAllAPI() {
        return pedidoService.getAll();
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<PedidoDto> getPedidoAPI(@PathVariable Long id) {
        return pedidoService.getPedido(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public PedidoDto saveAPI(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.save(pedidoDto);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAPI(@PathVariable Long id) {
        if (pedidoService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ===== ENDPOINTS DE REPORTES =====

    @GetMapping("/api/reportes/estado/{estado}")
    @ResponseBody
    public List<PedidoDto> getPedidosPorEstado(@PathVariable String estado) {
        return pedidoService.getPedidosPorEstado(estado);
    }

    @GetMapping("/api/reportes/fecha")
    @ResponseBody
    public List<PedidoDto> getPedidosPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        return pedidoService.getPedidosPorFecha(fechaInicio, fechaFin);
    }

    @GetMapping("/api/reportes/estadisticas")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getEstadisticas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        Map<String, Object> estadisticas = new HashMap<>();

        BigDecimal totalVentas = pedidoService.calcularTotalVentas(fechaInicio, fechaFin);
        Long pedidosPendientes = pedidoService.contarPedidosPorEstado("Pendiente");
        Long pedidosCompletados = pedidoService.contarPedidosPorEstado("Entregado");
        Long pedidosCancelados = pedidoService.contarPedidosPorEstado("Cancelado");

        estadisticas.put("totalVentas", totalVentas);
        estadisticas.put("pedidosPendientes", pedidosPendientes);
        estadisticas.put("pedidosCompletados", pedidosCompletados);
        estadisticas.put("pedidosCancelados", pedidosCancelados);
        estadisticas.put("fechaInicio", fechaInicio);
        estadisticas.put("fechaFin", fechaFin);

        return ResponseEntity.ok(estadisticas);
    }
}
