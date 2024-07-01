package com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter @Getter
@Table (name = "Cantante")
public class Cantante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nombre;
    private int edad;
    private String paisOrigen;

    @OneToMany(mappedBy = "cantante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cancion> canciones = new HashSet<>();

    public Set<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(Set<Cancion> canciones){
        this.canciones =canciones;
        for (Cancion cancion : canciones){
            cancion.setCantante(this);
        }
    }
}
