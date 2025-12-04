package com.matpi.web.controller;

import com.matpi.dominio.dto.ClienteDto;
import com.matpi.dominio.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String getAll(Model model) {
        List<ClienteDto> clientes = clienteService.getAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("activeModule", "clientes");
        return "clientes";
    }

    @GetMapping("/{id}")
    public String getCliente(@PathVariable BigInteger id, Model model) {
        return clienteService.getCliente(id)
                .map(cliente -> {
                    model.addAttribute("cliente", cliente);
                    return "cliente-detalle";
                })
                .orElse("redirect:/clientes");
    }

    @PostMapping("/api")
    @ResponseBody
    public ClienteDto saveApi(@RequestBody ClienteDto clienteDto) {
        return clienteService.save(clienteDto);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteApi(@PathVariable BigInteger id) {
        if (clienteService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
