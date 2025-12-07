package com.matpi.web.controller;

import com.matpi.dominio.dto.UsuarioDto;
import com.matpi.dominio.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/perfil")
public class ProfileController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String showProfile(Authentication authentication, Model model) {
        String userId = authentication.getName(); // El ID del usuario autenticado

        return usuarioService.getUsuario(userId)
                .map(usuario -> {
                    model.addAttribute("usuario", usuario);
                    model.addAttribute("activeModule", "perfil");
                    return "perfil/index";
                })
                .orElse("redirect:/auth/login");
    }

    @GetMapping("/editar")
    public String showEditProfile(Authentication authentication, Model model) {
        String userId = authentication.getName();

        return usuarioService.getUsuario(userId)
                .map(usuario -> {
                    model.addAttribute("usuario", usuario);
                    model.addAttribute("activeModule", "perfil");
                    return "perfil/edit";
                })
                .orElse("redirect:/auth/login");
    }

    @PostMapping("/editar")
    public String updateProfile(Authentication authentication,
            @ModelAttribute UsuarioDto usuarioDto,
            @RequestParam(required = false) String password,
            Model model) {
        try {
            String userId = authentication.getName();

            // Get existing usuario and update fields
            return usuarioService.getUsuario(userId)
                    .map(existingUsuario -> {
                        existingUsuario.setNombreCompleto(usuarioDto.getNombreCompleto());
                        existingUsuario.setCorreoElectronico(usuarioDto.getCorreoElectronico());
                        existingUsuario.setTelefono(usuarioDto.getTelefono());
                        existingUsuario.setDireccion(usuarioDto.getDireccion());
                        existingUsuario.setFechaNacimiento(usuarioDto.getFechaNacimiento());
                        existingUsuario.setExperienciaLaboral(usuarioDto.getExperienciaLaboral());

                        // Update password only if provided
                        if (password != null && !password.trim().isEmpty()) {
                            existingUsuario.setPassword(password); // Use password field so UsuarioService will encode
                                                                   // it
                        }

                        usuarioService.save(existingUsuario);
                        model.addAttribute("success", "Perfil actualizado exitosamente");
                        return "redirect:/perfil";
                    })
                    .orElse("redirect:/auth/login");
        } catch (Exception e) {
            model.addAttribute("error", "Error al actualizar perfil: " + e.getMessage());
            model.addAttribute("usuario", usuarioDto);
            model.addAttribute("activeModule", "perfil");
            return "perfil/edit";
        }
    }
}
