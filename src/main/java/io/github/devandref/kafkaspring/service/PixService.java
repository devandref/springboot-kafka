package io.github.devandref.kafkaspring.service;

import io.github.devandref.kafkaspring.PixEnumeration.PixStatus;
import io.github.devandref.kafkaspring.component.BuscaKeyComponent;
import io.github.devandref.kafkaspring.dto.PixDTO;
import io.github.devandref.kafkaspring.model.Pix;
import io.github.devandref.kafkaspring.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PixService {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private BuscaKeyComponent buscaKeyComponent;

    @Autowired
    private KafkaTemplate<String, Pix> kafkaTemplate;

    public PixDTO salvarPix(PixDTO pixDTO) {
        Boolean statusChaveOrigem  = buscaKeyComponent.verificaSeChaveExiste(pixDTO.getChaveOrigem());
        Boolean statusChaveDestino = buscaKeyComponent.verificaSeChaveExiste(pixDTO.getChaveDestino());
        Pix pixEntity = Pix.toEntity(pixDTO);

        if(statusChaveDestino && statusChaveOrigem) {
            pixEntity.setStatus(PixStatus.EM_PROCESSAMENTO);
        } else {
            pixEntity.setStatus(PixStatus.ERRO);
        }
        pixRepository.save(pixEntity);
        kafkaTemplate.send("pix-topic", pixEntity.getIdentifier(), pixEntity);
        pixDTO.setPixStatus(pixEntity.getStatus());
        return pixDTO;
    }

}
