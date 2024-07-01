package com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Controller;


import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity.Cancion;
import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity.Cantante;
import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Repository.CancionRepository;
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
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionRepository cancionRepository;
    @Autowired
    private CantanteRepository cantanteRepository;

    @GetMapping
    public ResponseEntity<Page<Cancion>> listarCanciones(Pageable pageable) {
        return ResponseEntity.ok(cancionRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Cancion> guardarCancion(@Validated @RequestBody Cancion cancion) {
        Optional<Cantante> cantanteOptional = cantanteRepository.findById(cancion.getCantante().getId());

        if (!cantanteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        cancion.setCantante(cantanteOptional.get());
        Cancion cancionGuardada = cancionRepository.save(cancion);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cancionGuardada.getId()).toUri();

        return ResponseEntity.created(ubicacion).body(cancionGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizarCancion(@Validated @RequestBody Cancion cancion, @PathVariable Integer id) {
        Optional<Cantante> cantanteOptional = cantanteRepository.findById(cancion.getCantante().getId());

        if (!cantanteOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Cancion> cancionOptional = cancionRepository.findById(id);
        if (!cancionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        cancion.setCantante(cantanteOptional.get());
        cancion.setId(cancionOptional.get().getId());
        cancionRepository.save(cancion);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cancion> eliminarCancion(@PathVariable Integer id) {
        Optional<Cancion> cancionOptional = cancionRepository.findById(id);

        if (!cancionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        cancionRepository.delete(cancionOptional.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerCancionPorId(@PathVariable Integer id) {
        Optional<Cancion> cancionOptional = cancionRepository.findById(id);

        if (!cancionOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(cancionOptional.get());
    }
}
