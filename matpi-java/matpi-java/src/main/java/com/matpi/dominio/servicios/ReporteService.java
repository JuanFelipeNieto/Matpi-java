package com.matpi.dominio.servicios;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.dto.ReporteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private PedidoService pedidoService;

    public ReporteDto generarReporte(String estado, String fechaDesde, String fechaHasta) {
        List<PedidoDto> pedidos;

        // Obtener pedidos filtrados o todos
        if (estado != null || fechaDesde != null || fechaHasta != null) {
            pedidos = pedidoService.filtrarPedidos(estado, fechaDesde, fechaHasta);
        } else {
            pedidos = pedidoService.getAll();
        }

        return calcularEstadisticas(pedidos);
    }

    private ReporteDto calcularEstadisticas(List<PedidoDto> pedidos) {
        ReporteDto reporte = new ReporteDto();

        reporte.setTotalPedidos(pedidos.size());

        for (PedidoDto pedido : pedidos) {
            BigDecimal valor = pedido.getValor() != null ? pedido.getValor() : BigDecimal.ZERO;

            // Sumar al total general
            reporte.setTotalVentas(reporte.getTotalVentas().add(valor));

            // Contabilizar por estado
            switch (pedido.getEstado()) {
                case "Pendiente":
                    reporte.setPedidosPendientes(reporte.getPedidosPendientes() + 1);
                    reporte.setTotalPendientes(reporte.getTotalPendientes().add(valor));
                    break;
                case "En Preparación":
                    reporte.setPedidosEnPreparacion(reporte.getPedidosEnPreparacion() + 1);
                    reporte.setTotalEnPreparacion(reporte.getTotalEnPreparacion().add(valor));
                    break;
                case "Entregado":
                    reporte.setPedidosEntregados(reporte.getPedidosEntregados() + 1);
                    reporte.setTotalEntregados(reporte.getTotalEntregados().add(valor));
                    break;
                case "Pagado":
                    reporte.setPedidosPagados(reporte.getPedidosPagados() + 1);
                    reporte.setTotalPagados(reporte.getTotalPagados().add(valor));
                    break;
                case "Cancelado":
                    reporte.setPedidosCancelados(reporte.getPedidosCancelados() + 1);
                    reporte.setTotalCancelados(reporte.getTotalCancelados().add(valor));
                    break;
            }
        }

        return reporte;
    }
}
