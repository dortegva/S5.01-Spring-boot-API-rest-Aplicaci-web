package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.service;

import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.domain.Sucursal;
import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.dto.SucursalDTO;
import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SucursalService implements SucursalServiceInterface {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public SucursalDTO addSucursal(SucursalDTO sucursalDTO) {
        Sucursal sucursal = DTOtoEntity(sucursalDTO);
        sucursal = sucursalRepository.save(sucursal);
        return EntitytoDTO(sucursal);
    }

    @Override
    public SucursalDTO getSucursal(int id) {
        return null;
    }

    @Override
    public SucursalDTO updateSucursal(int id, SucursalDTO sucursalDTO) {
        Sucursal newSucursal = DTOtoEntity(sucursalDTO);
        Optional<Sucursal> sucursal1 = sucursalRepository.findById(id);

        if (sucursal1.isPresent()) {
            Sucursal sucursal = sucursal1.get();
            sucursal.setName(newSucursal.getName());
            sucursal.setState(newSucursal.getState());
            sucursalRepository.save(sucursal);
            return EntitytoDTO(sucursal);

        } else {
            return null;
        }

    }


    @Override
    public List<SucursalDTO> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales.stream()
                .map(SucursalService::EntitytoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int idSucursal) {
        sucursalRepository.deleteById(idSucursal);
    }

    private static Sucursal DTOtoEntity(SucursalDTO sucursalDTO) {
        Sucursal sucursal = new Sucursal(sucursalDTO.getIdDTO(), sucursalDTO.getNameDto(), sucursalDTO.getStateDto());
        return sucursal;
    }

    private static SucursalDTO EntitytoDTO(Sucursal sucursal) {
        SucursalDTO sucursalDTO = new SucursalDTO(sucursal.getId(), sucursal.getName(), sucursal.getState());
        return sucursalDTO;
    }
}
