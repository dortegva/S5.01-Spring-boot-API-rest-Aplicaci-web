package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.controller;


import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @PostMapping("/add")
    public ResponseEntity<?> addSucursal(@RequestBody SucursalDTO sucursalDTO) {

        try {
            SucursalDTO sucursaladded = sucursalService.addSucursal(sucursalDTO);
            return new ResponseEntity<>(sucursaladded, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getOneSucursal(@PathVariable int id) {
        SucursalDTO sucursal1 = sucursalService.getSucursal(id);

        if (sucursal1 == null) {
            return new ResponseEntity<>("Sucursal not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(sucursal1, HttpStatus.OK);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllSucursales() {
        List<SucursalDTO> sucursales = sucursalService.getAllSucursales();

        if (sucursales.isEmpty()) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(sucursales, HttpStatus.OK);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSucursal(@PathVariable int id, @RequestBody SucursalDTO newSucursal) {
        SucursalDTO sucursal1 = sucursalService.getSucursal(id);

        if (sucursal1 == null) {
            return new ResponseEntity<>("Sucursal no encontrada con el id " + id, HttpStatus.NOT_FOUND);
        } else {
            SucursalDTO sucursalUpdate = sucursalService.updateSucursal(id, newSucursal);
            return new ResponseEntity<>(sucursalUpdate, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSucursal(@PathVariable int id) {
        SucursalDTO sucursal1 = sucursalService.getSucursal(id);

        if (sucursal1 == null) {
            return new ResponseEntity<>("Sucursal not found", HttpStatus.NOT_FOUND);
        } else {
            sucursalService.delete(id);
            return new ResponseEntity<>("Sucursal deleted", HttpStatus.OK);
        }
    }
}

