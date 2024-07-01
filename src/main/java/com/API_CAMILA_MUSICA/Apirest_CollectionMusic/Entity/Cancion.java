package com.API_CAMILA_MUSICA.Apirest_CollectionMusic.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "Cancion", uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo"})})
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String titulo;
    private String duracion;
    private Date fechaLanzamiento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "cantante_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cantante cantante;
}

