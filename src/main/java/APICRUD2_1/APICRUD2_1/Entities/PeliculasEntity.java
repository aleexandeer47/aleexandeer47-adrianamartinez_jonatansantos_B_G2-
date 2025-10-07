package APICRUD2_1.APICRUD2_1.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "PELICULAS")
public class PeliculasEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PELICULAS")
    @SequenceGenerator(name = "seqPelicula" , sequenceName = "" , allocationSize = 1)
    @Column(name = "ID_PELICULA")
    private Long Id_Pelicula;


    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DIRECTOR")
    private String Director;

    @Column(name = "GENERO")
    private  String Genero;

    @Column(name = "ANO_ESTRENO")
    private Number Ano_Estreno;

    @Column(name = "DURACION_MIN")
    private Number Duracion_min;


    @Column(name = "FECHA_CREACION")
    private Number Fecha_creacion;

}
