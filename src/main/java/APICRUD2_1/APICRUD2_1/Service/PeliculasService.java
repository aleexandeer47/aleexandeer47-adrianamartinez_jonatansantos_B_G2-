package APICRUD2_1.APICRUD2_1.Service;

import APICRUD2_1.APICRUD2_1.Entities.PeliculasEntity;
import APICRUD2_1.APICRUD2_1.Entities.PremiosEntity;
import APICRUD2_1.APICRUD2_1.Models.DTO.DTOPeliculas;
import APICRUD2_1.APICRUD2_1.Models.DTO.DTOPremios;
import APICRUD2_1.APICRUD2_1.Repositories.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PeliculasService {


    @Autowired
    PeliculasRepository repository;

    public DTOPeliculas ConvertirAPeliculasDTO(PeliculasEntity entity){
        DTOPeliculas dtoPeliculas = new DTOPeliculas();

        dtoPeliculas.setId_Pelicula(entity.getId_Pelicula());
        dtoPeliculas.setTitulo(entity.getTitulo());
        dtoPeliculas.setDirector(entity.getDirector());
        dtoPeliculas.setAno_estreno(entity.getAno_Estreno());
        dtoPeliculas.setGenero(entity.getGenero());
        dtoPeliculas.setDuracion_min(entity.getDuracion_min());
        dtoPeliculas.setFecha_Creacion(entity.getFecha_creacion());


        return dtoPeliculas;
    }



    public List<DTOPeliculas> obtenerPeliculas(){
        List<PeliculasEntity> lista = repository.findAll();
        return  lista.stream().map(this::ConvertirAPeliculasDTO).collect(Collectors.toList());
    }

}
