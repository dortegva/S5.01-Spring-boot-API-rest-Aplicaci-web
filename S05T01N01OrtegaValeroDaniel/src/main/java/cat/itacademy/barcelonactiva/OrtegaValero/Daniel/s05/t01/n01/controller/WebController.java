package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.controller;

import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sucursales")
public class WebController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping({"", "/"})
    public String listarSucursales(Model model) {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();
        model.addAttribute("sucursales", sucursales);
        return "sucursales/listarSucursales";
    }
    @GetMapping("/buscar/{id}")
    public String buscarSucursalPorId(@RequestParam("id") String id, Model model) {

        try {
            int idSucursal = Integer.parseInt(id);
            SucursalDTO sucursalDTO = sucursalService.getSucursal(idSucursal);

            if (sucursalDTO != null) {
                model.addAttribute("sucursales", Arrays.asList(sucursalDTO));
                return "sucursales/listarSucursales";
            } else {
                model.addAttribute("mensaje", "No se encontró ninguna sucursal con el ID proporcionado.");
                return "sucursales/listarSucursales";
            }

        } catch (NumberFormatException e) {
            model.addAttribute("mensaje", "Por favor, proporciona un ID válido.");
            return "redirect:/sucursales/";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Error al buscar la sucursal. " + e.getMessage());
            return "redirect:/sucursales/";
        }
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("sucursalDTO", new SucursalDTO());
        return "sucursales/agregarSucursal";
    }

    @PostMapping("/agregar")
    public String agregarSucursal(@Valid @ModelAttribute SucursalDTO sucursalDTO, BindingResult result, Model model) {

        if(result.hasErrors()){
            model.addAttribute("sucursalDTO", new SucursalDTO());
            model.addAttribute("error", "Todos los campos son obligatorios. Por favor, complete todos los campos.");
            return "sucursales/agregarSucursal";

        }  else {

            try {
                sucursalService.addSucursal(sucursalDTO);
                return "redirect:/sucursales";

            } catch (RuntimeException e) {
                model.addAttribute("mensaje", "Error al crear la sucursal. " + e.getMessage());
                return "sucursales/agregarSucursal";
            }
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable int id, Model model) {
        SucursalDTO sucursalDTO = sucursalService.getSucursal(id);
        if (sucursalDTO != null) {
            model.addAttribute("sucursalDTO", sucursalDTO);
            return "sucursales/editarSucursal";

        } else {
            return "Error al editar la sucursal.";
        }
    }

    @PostMapping("/editar/{id}")
    public String editarSucursal(@Valid @ModelAttribute SucursalDTO sucursalDTO, BindingResult result, @PathVariable int id, Model model) {

        if(result.hasErrors()){
            model.addAttribute("error", "Todos los campos son obligatorios. Por favor, complete todos los campos.");
            return "sucursales/editarSucursal";

        } else{
            try {
                sucursalService.updateSucursal(id, sucursalDTO);
                return "redirect:/sucursales";

            } catch (Exception e) {
                return "Error al editar la sucursal." + e.getMessage();
            }
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarSucursal(@PathVariable int id) {
        try {
            sucursalService.delete(id);
            return "redirect:/sucursales";

        } catch (Exception e) {
            return "Error al eliminar la sucursal." + e.getMessage();}}}