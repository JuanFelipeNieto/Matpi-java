package com.matpi.web.controller;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.dto.PedidoProductoDto;
import com.matpi.dominio.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

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
    public String showCreateForm(Model model) {
        model.addAttribute("activeModule", "pedidos");
        return "pedido-crear";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PedidoDto pedidoDto,
            @RequestParam(value = "productos[0].productoId", required = false) Long[] productosIds,
            @RequestParam(value = "productos[0].cantidad", required = false) Integer[] productosCantidades,
            Model model) {
        try {
            // Configurar fecha actual
            pedidoDto.setFecha(LocalDateTime.now());

            // Procesar productos
            List<PedidoProductoDto> productos = new ArrayList<>();
            if (productosIds != null && productosCantidades != null) {
                for (int i = 0; i < productosIds.length; i++) {
                    if (productosIds[i] != null && productosCantidades[i] != null) {
                        PedidoProductoDto productoDto = new PedidoProductoDto();
                        productoDto.setProductoId(productosIds[i]);
                        productoDto.setCantidad(productosCantidades[i]);
                        productos.add(productoDto);
                    }
                }
            }
            pedidoDto.setProductos(productos);

            // Calcular valor (esto debería hacerse en el servicio basado en precios reales)
            pedidoDto.setValor(BigDecimal.ZERO);

            pedidoService.save(pedidoDto);
            model.addAttribute("success", "Pedido creado exitosamente");
            return "redirect:/pedidos";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear pedido: " + e.getMessage());
            model.addAttribute("activeModule", "pedidos");
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
