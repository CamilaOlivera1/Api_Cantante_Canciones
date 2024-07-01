package com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Repository;


import com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancionRepository extends JpaRepository <Cancion, Integer> {
}

