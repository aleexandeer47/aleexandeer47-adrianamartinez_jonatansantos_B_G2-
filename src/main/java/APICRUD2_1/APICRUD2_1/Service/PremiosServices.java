package APICRUD2_1.APICRUD2_1.Service;

import APICRUD2_1.APICRUD2_1.Entities.PremiosEntity;
import APICRUD2_1.APICRUD2_1.Exceptions.ExceptionPeliculaNoEncontrada;
import APICRUD2_1.APICRUD2_1.Exceptions.ExceptionPremiosNoEncontrado;
import APICRUD2_1.APICRUD2_1.Exceptions.ExceptionPremiosrelacionadosConPelicula;
import APICRUD2_1.APICRUD2_1.Models.DTO.DTOPremios;
import APICRUD2_1.APICRUD2_1.Repositories.PremiosRepository;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PremiosServices {

    @Autowired
    private PremiosRepository repo;



    //AGREGAR

    public DTOPremios agregar(DTOPremios json){
        if (json == null){
            throw new IllegalArgumentException("No puedes agregar datos nulos");
        }
        PremiosEntity entity = repo.findById(json.getId_Premio()).
                orElseThrow(() -> new ExceptionPremiosNoEncontrado("No se ha encontrado al id" + json));



        try {
            PremiosEntity premiosEntity = new PremiosEntity();
            premiosEntity.setNombre_Premio(json.getNombre_Premio());
            premiosEntity.setCategoria(json.getCategoria());
            premiosEntity.setAno_Premio(json.getAno_Premio());
            premiosEntity.setResultado(json.getResultado());
            premiosEntity.setFecha_Registro(json.getFecha_Registro());

            PremiosEntity premiosCreados = repo.save(premiosEntity);
            return ConvertirAPremiosDTO(premiosCreados);
        }catch (Exception e){
            log.error("Error al registrar " + e);
            throw new IllegalArgumentException("Error al registrar los premios");
        }
}

    //ACTUALIZAR

    public DTOPremios Actualizar(Long id, DTOPremios dtoPremios){
        PremiosEntity entity = repo.findById(id).orElseThrow(() -> new ExceptionPremiosNoEncontrado("No se encontro el id"));

        entity.setNombre_Premio(dtoPremios.getNombre_Premio());
        entity.setCategoria(dtoPremios.getCategoria());
        entity.setAno_Premio(dtoPremios.getAno_Premio());
        entity.setResultado(dtoPremios.getResultado());
        entity.setFecha_Registro(dtoPremios.getFecha_Registro());

            if (dtoPremios.getId_Pelicula() != null){
                //PENDIENTE A ESTA LINEA
                    PremiosEntity premiosEntity = repo.findById(dtoPremios.getId_Pelicula()).orElseThrow(() -> new ExceptionPeliculaNoEncontrada("La pelicula no s eha encontrado"));
        }
            return ConvertirAPremiosDTO(repo.save(entity));

}

        //ELIMINAR

        public String eliminar (Long id){
                PremiosEntity entity = repo.findById(id).orElseThrow(() -> new ExceptionPremiosNoEncontrado("No se ha encontrado este premio"));

                try{
                    repo.delete(entity);
                    return "El premio ha sido eliminado";
                }catch (DataIntegrityViolationException e){
                    throw new ExceptionPremiosrelacionadosConPelicula("Los premios estan relacionados con una pelicula");
                }
        }


        //OBTENER
    public List<DTOPremios> Obtener(){
        List<PremiosEntity> lista = repo.findAll();
        return lista.stream().map(this::ConvertirAPremiosDTO).collect(Collectors.toList());
    }




    public DTOPremios  ConvertirAPremiosDTO(PremiosEntity entity){
        DTOPremios dtoPremios = new DTOPremios();

        dtoPremios.setId_Premio(entity.getId_Premio());
        dtoPremios.setId_Pelicula(entity.getId_Pelicula());
        dtoPremios.setNombre_Premio(entity.getNombre_Premio());
        dtoPremios.setCategoria(entity.getCategoria());
        dtoPremios.setAno_Premio(entity.getAno_Premio());
        dtoPremios.setResultado(entity.getResultado());
        dtoPremios.setFecha_Registro(entity.getFecha_Registro());

        return dtoPremios;
    }


}
