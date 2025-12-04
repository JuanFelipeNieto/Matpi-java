package com.matpi.web.controller;

import com.matpi.dominio.dto.PedidoDto;
import com.matpi.dominio.servicios.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<PedidoDto> getAll() {
        return pedidoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDto> getPedido(@PathVariable Long id) {
        return pedidoService.getPedido(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PedidoDto save(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.save(pedidoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (pedidoService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
