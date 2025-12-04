package com.matpi.persistencia.crud;

import com.matpi.persistencia.entity.MateriaPrimaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.matpi.dominio.servicios.MateriaPrimaService;

@restController
@ResquestMapping ("/api/materiaprima/")
public class materiaprima{
    @Autowired
    private MateriaPrimaService MateriaPrimaService;
    @PostMappinng
    private ResponseEntity<materiaprima>guardar(@RequestBody Materiaprima materiaprima){
    materiaprima temporal= MateriaPrimaService.create(materiaprima);
    try{
        return ResponseEntity.created(new URI("/api/materiaprima"+temporal.getId())).body(temporal)
    }catch(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    }

     @GetMapping
    private ResponseEntity<list<materiaprima>>lista(){
   return ResponseEntity.ok(MateriaPrimaService.getAllmateriaprima());

    }

    @DeleteMapping
    private ResponseEntity<void>eliminar(@RequestBody Materiaprima materiaprima){
   MateriaPrimaService.delete(materiaprima);
   return ResponseEntity.ok().build();

    }

     @GetMapping (value= "{id}")
    private ResponseEntity<list<materiaprima>>listaporid(@pathVariable ("id") long id){
   return ResponseEntity.ok(MateriaPrimaService.findById(id));

    }
   
}                                        
