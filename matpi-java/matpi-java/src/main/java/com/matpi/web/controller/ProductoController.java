package com.matpi.web.controller;

import com.matpi.dominio.dto.ProductoDto;
import com.matpi.dominio.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String getAll(Model model) {
        List<ProductoDto> productos = productoService.getAll();
        model.addAttribute("productos", productos);
        model.addAttribute("activeModule", "productos");
        return "productos";
    }

    @GetMapping("/{id}")
    public String getProducto(@PathVariable Long id, Model model) {
        return productoService.getProducto(id)
                .map(producto -> {
                    model.addAttribute("producto", producto);
                    return "producto-detalle"; // Asumiendo que crearás una vista de detalle
                })
                .orElse("redirect:/productos");
    }

    // Los métodos POST y DELETE deberían ajustarse para manejar redirecciones o
    // respuestas AJAX
    // Por ahora, los mantendremos como API endpoints si se usan desde JS, o se
    // pueden adaptar a formularios

    @PostMapping("/api")
    @ResponseBody
    public ProductoDto saveApi(@RequestBody ProductoDto productoDto) {
        return productoService.save(productoDto);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteApi(@PathVariable Long id) {
        if (productoService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
