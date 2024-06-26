package cat.itacademy.barcelonactiva.OrtegaValero.Daniel.s05.t01.n01.model.dto;

import java.text.Collator;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import jakarta.validation.constraints.NotEmpty;
public class SucursalDTO {
    private int idDTO;
    @NotEmpty(message = "Name required")
    private String nameDto;
    @NotEmpty(message = "State required")
    private String stateDto;
    private String typeDto;
    private static final List<String> PAISES_EU = Arrays.asList("Alemania", "Austria", "Bélgica", "Bulgaria",
            "Chipre", "Croacia", "Dinamarca", "Eslovaquia", "Eslovenia", "España", "Estonia", "Finlandia", "Francia",
            "Grecia", "Hungría", "Irlanda", "Italia", "Letonia", "Lituania", "Luxemburgo", "Malta", "Países Bajos",
            "Polonia", "Portugal", "República Checa", "Rumanía", "Suecia");


    public SucursalDTO(int idDTO, String nameDto, String stateDto) {
        this.idDTO = idDTO;
        this.nameDto = nameDto;
        this.stateDto = stateDto;
        this.typeDto = valType();
    }

    public SucursalDTO() {

    }

    public int getIdDTO() {
        return idDTO;
    }

    public void setIdDTO(int idDTO) {
        this.idDTO = idDTO;
    }

    public String getNameDto() {
        return nameDto;
    }

    public void setNameDto(String nameDto) {
        this.nameDto = nameDto;
    }

    public String getStateDto() {
        return stateDto;
    }

    public void setStateDto(String stateDto) {
        this.stateDto = stateDto;
    }

    public String getTypeDto() {
        return typeDto;
    }

    public void setTypeDto(String typeDto) {
        this.typeDto = typeDto;
    }

    public String valType(){

        String result = "";

        if(stateDto != null){
            Collator collator = Collator.getInstance(Locale.getDefault());
            collator.setStrength(Collator.PRIMARY);

            result = PAISES_EU.stream().anyMatch(pais -> collator.equals(pais, stateDto)) ? "EU" : "Not EU";
        } else {
            result = "not specified";
        }

        return result;

    }

    @Override
    public String toString() {
        return "SucursalDTO{" +
                "pk_SucursalID=" + idDTO +
                ", nombreSucursal='" + nameDto + '\'' +
                ", paisSucursal='" + stateDto + '\'' +
                ", tipoSucursal='" + typeDto + '\'' +
                '}';
    }
}
