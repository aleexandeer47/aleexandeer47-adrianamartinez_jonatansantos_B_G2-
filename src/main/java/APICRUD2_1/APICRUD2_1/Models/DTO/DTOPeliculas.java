package APICRUD2_1.APICRUD2_1.Models.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DTOPeliculas {

    @NotNull
    @Positive
    private  Long Id_Pelicula;

    @NotBlank
    private String Titulo;

    @NotBlank
    private String Director;

    @Size(max = 4, message = "El año no puede tener mas de cuatro de caracteres")
    @NotBlank
    private  String Genero;

    @NotBlank
    private Number ano_estreno;

    @NotBlank
    private Number duracion_min;

    @NotBlank
    private Number Fecha_Creacion;

}
