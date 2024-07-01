package com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Controller;


import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity.Cantante;
import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/cantante")
public class CantanteController {

    @Autowired
    private CantanteRepository cantanteRepository;

    @GetMapping
    public ResponseEntity<Page<Cantante>> listarCantantes(Pageable pageable) {
        return ResponseEntity.ok(cantanteRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Cantante> guardarCantante(@Validated @RequestBody Cantante cantante) {
        Cantante cantanteGuardado = cantanteRepository.save(cantante);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cantanteGuardado.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(cantanteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cantante> actualizarCantante(@PathVariable Integer id, @Validated @RequestBody Cantante cantante) {
        Optional<Cantante> cantanteOptional = cantanteRepository.findById(id);

        if (!cantanteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        cantante.setId(cantanteOptional.get().getId());
        cantanteRepository.save(cantante);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cantante> eliminarCantante(@PathVariable Integer id) {
        Optional<Cantante> cantanteOptional = cantanteRepository.findById(id);

        if(!cantanteOptional.isPresent()){
           return ResponseEntity.unprocessableEntity().build();
        }
        cantanteRepository.delete(cantanteOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cantante> obtenerCantante(@PathVariable Integer id){
        Optional<Cantante> cantanteOptional = cantanteRepository.findById(id);

        if(!cantanteOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(cantanteOptional.get());
    }
}

