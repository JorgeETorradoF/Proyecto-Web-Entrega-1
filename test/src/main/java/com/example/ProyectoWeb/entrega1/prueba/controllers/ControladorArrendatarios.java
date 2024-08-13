package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.ProyectoWeb.entrega1.model.Arrendatarios;
import com.example.ProyectoWeb.entrega1.service.ServicioArrendatario;

import java.util.List;

@Controller
@RequestMapping("/arrendatarios")
public class ControladorArrendatarios {

    @Autowired
    private ServicioArrendatario servicioArrendatario;

    // Obtener arrendatarios
    @GetMapping
    public String listArrendatarios(Model model) {
        List<Arrendatarios> arrendatarios = servicioArrendatario.findAll();
        model.addAttribute("arrendatarios", arrendatarios);
        return "arrendatarios/list";
    }

    // crea formulario para nuevo arrendatario
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("arrendatarios", new Arrendatarios());
        return "arrendatarios/create";
    }

    //  guarda arrendatario
    @PostMapping
    public String saveArrendatarios(@ModelAttribute("arrendatarios") Arrendatarios arrendatarios) {
        servicioArrendatario.save(arrendatarios);
        return "redirect:/arrendatarios";
    }

    // muestra formulario de actualización de nuevo arrendatario
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Arrendatarios arrendatarios = servicioArrendatario.findById(id);
        model.addAttribute("arrendatarios", arrendatarios);
        return "arrendatarios/edit";
    }

    // actualiza arrendatario existente
    @PostMapping("/update/{id}")
    public String updateArrendatarios(@PathVariable("id") Integer id,
                                      @ModelAttribute("arrendatarios") Arrendatarios arrendatarios) {
        arrendatarios.setId(id);
        servicioArrendatario.update(arrendatarios);
        return "redirect:/arrendatarios";
    }

    // borra un arrendatario
    @GetMapping("/delete/{id}")
    public String deleteArrendatarios(@PathVariable("id") Integer id) {
        servicioArrendatario.delete(id);
        return "redirect:/arrendatarios";
    }
}
