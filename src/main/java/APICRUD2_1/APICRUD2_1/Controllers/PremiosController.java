package APICRUD2_1.APICRUD2_1.Controllers;

import APICRUD2_1.APICRUD2_1.Exceptions.ExceptionPremiosNoEncontrado;
import APICRUD2_1.APICRUD2_1.Models.DTO.DTOPremios;
import APICRUD2_1.APICRUD2_1.Service.PremiosServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/apiPremios")
@CrossOrigin
public class PremiosController {

    @Autowired
    private PremiosServices services;

    @GetMapping("/getPremios")
    public List<DTOPremios> obtenerPremios(){
        return services.Obtener();
    }


    @PostMapping("/createPremio")
    public ResponseEntity<?> nuevoPremio(@Valid @RequestBody DTOPremios json, HttpServletRequest request){
        try{
            DTOPremios respuesta = services.agregar(json);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Status", "insercion fallida",
                        "errorType" , "VALIDATION_ERROR",
                        "message" , "los datos no pudieron ser registrados"
                ));

            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data" , respuesta
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status" , "Error",
                    "message" , "Error no controlado al registrar",
                    "detail" , e.getMessage()
            ));
        }
    }


    @PutMapping("/actualizarPremio/{id}")
    public ResponseEntity<?> ActualizarPremio(@PathVariable Long id, @Valid @RequestBody DTOPremios json, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try{
            DTOPremios dto = services.Actualizar(id, json);
            return ResponseEntity.ok(dto);
        }catch (ExceptionPremiosNoEncontrado e){
            return ResponseEntity.notFound().build();

        }
    }


    @DeleteMapping("/eliminarPremio/{id}")
    public ResponseEntity<Map<String, String>> EliminarPremio(@PathVariable Long id){
        try{
            services.eliminar(id);
            return ResponseEntity.ok(Map.of(
                    "status" , "Exito",
                    "message" , "Eliminado"
            ));
        }catch (ExceptionPremiosNoEncontrado e){
            
        }
    }

}
