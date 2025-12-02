package com.matpi.web.controller;

import com.matpi.dominio.dto.ReservaDto;
import com.matpi.dominio.servicios.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<ReservaDto> getAll() {
        return reservaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getReserva(@PathVariable Long id) {
        return reservaService.getReserva(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ReservaDto save(@RequestBody ReservaDto reservaDto) {
        return reservaService.save(reservaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reservaService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
