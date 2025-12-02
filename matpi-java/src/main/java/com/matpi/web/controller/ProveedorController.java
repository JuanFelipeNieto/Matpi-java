package com.matpi.web.controller;

import com.matpi.dominio.dto.ProveedorDto;
import com.matpi.dominio.servicios.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<ProveedorDto> getAll() {
        return proveedorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> getProveedor(@PathVariable Long id) {
        return proveedorService.getProveedor(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProveedorDto save(@RequestBody ProveedorDto proveedorDto) {
        return proveedorService.save(proveedorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (proveedorService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
