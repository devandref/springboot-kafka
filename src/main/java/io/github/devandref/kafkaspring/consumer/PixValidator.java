package io.github.devandref.kafkaspring.consumer;

import io.github.devandref.kafkaspring.PixEnumeration.PixStatus;
import io.github.devandref.kafkaspring.component.BuscaKeyComponent;
import io.github.devandref.kafkaspring.model.Pix;
import io.github.devandref.kafkaspring.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PixValidator {

    @Autowired
    private PixRepository pixRepository;

    @Autowired
    private BuscaKeyComponent buscaKeyComponent;

    @KafkaListener(topics = "pix-topic", groupId = "grupo-01")
    public void consumerProcessPix(Pix pix) {
        System.out.println("Pix recebido: " + pix.getIdentifier());

        Optional<Pix> pixOptional = pixRepository.findByIdentifier(pix.getIdentifier());

        if(pixOptional.isPresent()) {
            Pix pixEntity = pixOptional.get();
            Boolean isChaveDestinoPresent = buscaKeyComponent.verificaSeChaveExiste(pixEntity.getChaveDestino());
            Boolean isChaveOrigemPresent = buscaKeyComponent.verificaSeChaveExiste(pixEntity.getChaveOrigem());

            if(isChaveOrigemPresent && isChaveDestinoPresent) {
                pixEntity.setStatus(PixStatus.PROCESSADO);
            } else {
                pixEntity.setStatus(PixStatus.ERRO);
            }
            pixRepository.save(pixEntity);
        }
    }

}
