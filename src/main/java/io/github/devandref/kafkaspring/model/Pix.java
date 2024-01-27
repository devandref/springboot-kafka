package io.github.devandref.kafkaspring.model;

import io.github.devandref.kafkaspring.PixEnumeration.PixStatus;
import io.github.devandref.kafkaspring.dto.PixDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(schema = "pix", name = "transferencia")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    @Column(name = "chave_origem")
    private String chaveOrigem;

    @Column(name = "chave_destino")
    private String chaveDestino;

    private Double valor;

    @Column(name = "data_transferencia")
    private LocalDateTime dataTransferencia;

    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(UUID.randomUUID().toString());
        pix.setChaveOrigem(pixDTO.getChaveOrigem());
        pix.setChaveDestino(pixDTO.getChaveDestino());
        pix.setValor(pixDTO.getValor());
        pix.setDataTransferencia(LocalDateTime.now());
        return pix;
    }

}
