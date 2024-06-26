package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.domain;

import jakarta.persistence.*;


@Entity
@Table(name = "Sucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    private String state;

    public Sucursal(int id, String nombreSucursal, String paisSucursal) {
        this.id = id;
        this.name = nombreSucursal;
        this.state = paisSucursal;
    }

    public Sucursal() {
    }

    public Sucursal(String nombreSucursal, String paisSucursal) {
        this.name = nombreSucursal;
        this.state = paisSucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

