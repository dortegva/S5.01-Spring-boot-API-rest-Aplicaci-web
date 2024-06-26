package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.service;


import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.dto.SucursalDTO;

import java.util.List;

public interface SucursalServiceInterface {
    SucursalDTO addSucursal (SucursalDTO sucursalDTO);
    SucursalDTO getSucursal (int id);
    SucursalDTO updateSucursal(int id, SucursalDTO sucursalDTO);
    void delete(int idSucursal);
    List<SucursalDTO> getAllSucursales();


}
