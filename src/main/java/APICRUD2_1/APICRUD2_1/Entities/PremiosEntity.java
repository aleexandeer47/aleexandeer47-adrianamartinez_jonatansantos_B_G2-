package APICRUD2_1.APICRUD2_1.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.naming.Name;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "PREMIOS")
public class PremiosEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PREMIOS")
    @SequenceGenerator(name = "seqPremio" , sequenceName = "" , allocationSize = 1)
    @Column(name = "ID_PREMIO")
    private  Long Id_Premio;


    @Column(name = "NOMBRE_PREMIO")
    private String Nombre_Premio;

    @Column(name = "CATEGORIA")
    private String Categoria;

    @Column(name = "ANO_PREMIO")
    private  Number Ano_Premio;

    @Column(name = "RESULTADO")
    private String Resultado;

    @Column(name = "FECHA_REGISTRO")
    private String Fecha_Registro;


    @ManyToOne
    @JoinColumn(name = "ID_PELICULA")
   // @SequenceGenerator(name = "SEQ_PELICULAS" , sequenceName = "SEQ_PELICULAS" , allocationSize = 1)
    private  Long Id_Pelicula;









}
