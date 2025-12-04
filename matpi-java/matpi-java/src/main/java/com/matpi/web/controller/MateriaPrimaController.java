package com.matpi.web.controller;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.servicios.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia-prima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @GetMapping
    public List<MateriaPrimaDto> getAll() {
        return materiaPrimaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaPrimaDto> getMateriaPrima(@PathVariable Long id) {
        return materiaPrimaService.getMateriaPrima(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MateriaPrimaDto save(@RequestBody MateriaPrimaDto materiaPrimaDto) {
        return materiaPrimaService.save(materiaPrimaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (materiaPrimaService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
