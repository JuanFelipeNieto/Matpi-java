package com.matpi.web.controller;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.dominio.servicios.PedidoService;
import com.matpi.dominio.servicios.ProductoService;
import com.matpi.dominio.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public String getAll(
            @RequestParam(required = false) String estado,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            Model model) {

        List<PedidoDto> pedidos;

        if (estado != null || fechaDesde != null || fechaHasta != null) {
            pedidos = pedidoService.filtrarPedidos(estado, fechaDesde, fechaHasta);
        } else {
            pedidos = pedidoService.getAll();
        }

        model.addAttribute("pedidos", pedidos);
        model.addAttribute("activeModule", "pedidos");
        return "pedidos";
    }

    @GetMapping("/{id}")
    public String getPedido(@PathVariable Long id, Model model) {
        return pedidoService.getPedido(id)
                .map(pedido -> {
                    model.addAttribute("pedido", pedido);
                    model.addAttribute("activeModule", "pedidos");
                    return "pedido-detalle";
                })
                .orElse("redirect:/pedidos");
    }

    @GetMapping("/create")
    public String showCreateForm(Model model, Principal principal) {
        model.addAttribute("activeModule", "pedidos");
        model.addAttribute("productos", productoService.getAll());
        model.addAttribute("reservas", reservaService.getAll());

        if (principal != null) {
            model.addAttribute("currentEmpleadoId", principal.getName());
        }

        return "pedido-crear";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PedidoDto pedidoDto, Model model) {
        try {
            // Configurar fecha actual si no viene
            if (pedidoDto.getFecha() == null) {
                pedidoDto.setFecha(LocalDateTime.now());
            }

            // Calcular valor (esto debería hacerse en el servicio basado en precios reales)
            // Aquí podríamos iterar sobre los productos para sumar precios si fuera
            // necesario,
            // pero idealmente el servicio lo maneja.
            pedidoDto.setValor(BigDecimal.ZERO);

            // Validar que haya productos
            if (pedidoDto.getProductos() == null || pedidoDto.getProductos().isEmpty()) {
                throw new RuntimeException("Debe agregar al menos un producto al pedido");
            }

            // Filtrar productos con IDs nulos (por si acaso el binding crea objetos vacíos)
            pedidoDto.getProductos().removeIf(p -> p.getProductoId() == null || p.getCantidad() == null);

            pedidoService.save(pedidoDto);
            model.addAttribute("success", "Pedido creado exitosamente");
            return "redirect:/pedidos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear pedido: " + e.getMessage());
            model.addAttribute("activeModule", "pedidos");
            model.addAttribute("productos", productoService.getAll());
            model.addAttribute("reservas", reservaService.getAll());
            // Necesitamos devolver el DTO para no perder los datos del formulario,
            // pero con cuidado de listas nulas
            model.addAttribute("pedidoDto", pedidoDto);
            return "pedido-crear";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return pedidoService.getPedido(id)
                .map(pedido -> {
                    model.addAttribute("pedido", pedido);
                    model.addAttribute("activeModule", "pedidos");
                    return "pedido-editar";
                })
                .orElse("redirect:/pedidos");
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute PedidoDto pedidoDto, Model model) {
        try {
            pedidoDto.setId(id);
            pedidoService.update(pedidoDto);
            model.addAttribute("success", "Pedido actualizado exitosamente");
            return "redirect:/pedidos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar pedido: " + e.getMessage());
            model.addAttribute("pedido", pedidoDto);
            model.addAttribute("activeModule", "pedidos");
            return "pedido-editar";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        if (pedidoService.delete(id)) {
            model.addAttribute("success", "Pedido eliminado exitosamente");
        } else {
            model.addAttribute("error", "No se pudo eliminar el pedido");
        }
        return "redirect:/pedidos";
    }
}
