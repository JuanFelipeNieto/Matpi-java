package com.matpi.persistencia.crud;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.servicios.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/materiaprima/")
public class MateriaPrimaCrud {
    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @PostMapping
    private ResponseEntity<MateriaPrimaDto> guardar(@RequestBody MateriaPrimaDto materiaPrima) {
        MateriaPrimaDto temporal = materiaPrimaService.create(materiaPrima);
        try {
            return ResponseEntity.created(new URI("/api/materiaprima/" + temporal.getId())).body(temporal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    private ResponseEntity<List<MateriaPrimaDto>> lista() {
        return ResponseEntity.ok(materiaPrimaService.getAll());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        materiaPrimaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<MateriaPrimaDto> listaporid(@PathVariable("id") Long id) {
        return ResponseEntity.ok(materiaPrimaService.findById(id).orElse(null));
    }
}
