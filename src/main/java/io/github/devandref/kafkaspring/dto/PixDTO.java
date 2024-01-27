package io.github.devandref.kafkaspring.dto;

import io.github.devandref.kafkaspring.PixEnumeration.PixStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PixDTO {

    private String chaveOrigem;
    private String chaveDestino;
    private Double valor;
    private PixStatus pixStatus;

}
