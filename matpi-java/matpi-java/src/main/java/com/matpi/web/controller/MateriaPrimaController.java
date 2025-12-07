package com.matpi.web.controller;

import com.matpi.dominio.dto.MateriaPrimaDto;
import com.matpi.dominio.servicios.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materia-prima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaService materiaPrimaService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("materiasPrimas", materiaPrimaService.getAll());
        model.addAttribute("activeModule", "materia-prima");
        return "materia-prima";
    }

    @GetMapping("/{id}")
    public String getMateriaPrima(@PathVariable Long id, Model model) {
        return materiaPrimaService.findById(id)
                .map(materiaPrima -> {
                    model.addAttribute("materiaPrima", materiaPrima);
                    model.addAttribute("activeModule", "materia-prima");
                    return "materia-prima-detalle";
                })
                .orElse("redirect:/materia-prima");
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activeModule", "materia-prima");
        return "materia-prima-crear";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute MateriaPrimaDto materiaPrimaDto, Model model) {
        try {
            materiaPrimaService.save(materiaPrimaDto);
            model.addAttribute("success", "Materia prima creada exitosamente");
            return "redirect:/materia-prima";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear materia prima: " + e.getMessage());
            model.addAttribute("activeModule", "materia-prima");
            return "materia-prima-crear";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        return materiaPrimaService.findById(id)
                .map(materiaPrima -> {
                    model.addAttribute("materiaPrima", materiaPrima);
                    model.addAttribute("activeModule", "materia-prima");
                    return "materia-prima-editar";
                })
                .orElse("redirect:/materia-prima");
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute MateriaPrimaDto materiaPrimaDto, Model model) {
        try {
            materiaPrimaDto.setId(id);
            materiaPrimaService.update(materiaPrimaDto);
            model.addAttribute("success", "Materia prima actualizada exitosamente");
            return "redirect:/materia-prima";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar materia prima: " + e.getMessage());
            model.addAttribute("materiaPrima", materiaPrimaDto);
            model.addAttribute("activeModule", "materia-prima");
            return "materia-prima-editar";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            materiaPrimaService.delete(id);
            model.addAttribute("success", "Materia prima eliminada exitosamente");
        } catch (Exception e) {
            model.addAttribute("error", "No se pudo eliminar la materia prima");
        }
        return "redirect:/materia-prima";
    }
}
