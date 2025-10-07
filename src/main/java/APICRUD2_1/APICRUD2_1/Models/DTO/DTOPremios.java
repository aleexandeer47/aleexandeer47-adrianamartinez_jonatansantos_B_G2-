package APICRUD2_1.APICRUD2_1.Models.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOPremios {


    @NotNull
    @Positive
    private  Long Id_Premio;
    @NotNull
    @Positive
    private  Long Id_Pelicula;

    @NotBlank
    private String Nombre_Premio;

    @NotBlank
    private String Categoria;

    @Size(max = 4, message = "El año no puede tener mas de cuatro de caracteres")
    @NotBlank
    private  Number Ano_Premio;

    @NotBlank
    private String Resultado;

    @NotBlank
    private String Fecha_Registro;
}
