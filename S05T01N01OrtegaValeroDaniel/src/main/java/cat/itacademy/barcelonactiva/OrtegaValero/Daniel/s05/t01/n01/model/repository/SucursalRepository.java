package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.repository;


import cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.domain.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    }

