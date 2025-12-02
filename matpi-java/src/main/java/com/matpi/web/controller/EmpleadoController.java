package com.matpi.web.controller;

import com.matpi.dominio.dto.EmpleadoDto;
import com.matpi.dominio.servicios.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public String getAll(Model model) {
        List<EmpleadoDto> empleados = empleadoService.getAll();
        model.addAttribute("empleados", empleados);
        model.addAttribute("activeModule", "empleados");
        return "empleados";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("activeModule", "empleados");
        return "empleado-crear";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute EmpleadoDto empleadoDto, Model model) {
        try {
            empleadoService.save(empleadoDto);
            model.addAttribute("success", "Empleado creado exitosamente");
            return "redirect:/empleados";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear empleado: " + e.getMessage());
            model.addAttribute("activeModule", "empleados");
            return "empleado-crear";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            return empleadoService.getEmpleado(id)
                    .map(empleado -> {
                        // Poblar campos planos para el formulario
                        empleado.setId(empleado.getIdUsr());
                        if (empleado.getUsuario() != null) {
                            empleado.setNombreCompleto(empleado.getUsuario().getNombreCompleto());
                            empleado.setCorreoElectronico(empleado.getUsuario().getCorreoElectronico());
                            empleado.setTelefono(empleado.getUsuario().getTelefono());
                            empleado.setDireccion(empleado.getUsuario().getDireccion());
                            empleado.setExperienciaLaboral(empleado.getUsuario().getExperienciaLaboral());
                            empleado.setFechaNacimiento(empleado.getUsuario().getFechaNacimiento());
                            empleado.setFechaIngreso(empleado.getUsuario().getFechaIngreso());
                            empleado.setEstado(empleado.getUsuario().getEstado());
                            if (empleado.getUsuario().getRol() != null) {
                                empleado.setRol(empleado.getUsuario().getRol().toString());
                            }
                        }
                        model.addAttribute("empleado", empleado);
                        model.addAttribute("activeModule", "empleados");
                        return "empleado-editar";
                    })
                    .orElse("redirect:/empleados");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al cargar el formulario de edición: " + e.getMessage());
            // Necesitamos cargar la lista para mostrar el error en la vista 'empleados'
            List<EmpleadoDto> empleados = empleadoService.getAll();
            model.addAttribute("empleados", empleados);
            model.addAttribute("activeModule", "empleados");
            return "empleados";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable String id, @ModelAttribute EmpleadoDto empleadoDto, Model model) {
        try {
            empleadoDto.setId(id); // Asegurar que el ID es correcto
            empleadoService.update(empleadoDto);
            model.addAttribute("success", "Empleado actualizado exitosamente");
            return "redirect:/empleados";
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar empleado: " + e.getMessage());
            model.addAttribute("empleado", empleadoDto);
            model.addAttribute("activeModule", "empleados");
            return "empleado-editar";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        if (empleadoService.delete(id)) {
            model.addAttribute("success", "Empleado eliminado exitosamente");
        } else {
            model.addAttribute("error", "No se pudo eliminar el empleado");
        }
        return "redirect:/empleados";
    }

    @GetMapping("/{id}")
    public String getEmpleado(@PathVariable String id, Model model) {
        return empleadoService.getEmpleado(id)
                .map(empleado -> {
                    model.addAttribute("empleado", empleado);
                    return "empleado-detalle";
                })
                .orElse("redirect:/empleados");
    }

    @PostMapping("/api")
    @ResponseBody
    public EmpleadoDto saveApi(@RequestBody EmpleadoDto empleadoDto) {
        return empleadoService.save(empleadoDto);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteApi(@PathVariable String id) {
        if (empleadoService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
