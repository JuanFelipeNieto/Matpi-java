package com.matpi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashboardController {

    @GetMapping("/administrador/dashboard")
    public String administradorDashboard(Model model) {
        model.addAttribute("titulo", "Panel de Administrador");
        model.addAttribute("rol", "Administrador");
        model.addAttribute("activeModule", "dashboard");
        // Aquí se podrían cargar estadísticas reales
        model.addAttribute("totalUsuarios", 150);
        model.addAttribute("pedidosPendientes", 12);
        model.addAttribute("ingresosMes", "$ 15,000,000");
        return "dashboard";
    }

    @GetMapping("/empleado/dashboard")
    public String empleadoDashboard(Model model) {
        model.addAttribute("titulo", "Panel de Empleado");
        model.addAttribute("rol", "Empleado");
        model.addAttribute("activeModule", "dashboard");
        model.addAttribute("pedidosPendientes", 5);
        return "dashboard";
    }
}
