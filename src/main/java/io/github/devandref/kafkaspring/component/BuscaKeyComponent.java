package io.github.devandref.kafkaspring.component;

import io.github.devandref.kafkaspring.model.Key;
import io.github.devandref.kafkaspring.repository.KeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuscaKeyComponent {

    @Autowired
    private KeyRepository keyRepository;

    public Boolean verificaSeChaveExiste(String chave) {
        Optional<Key> optionalChave = keyRepository.findByChave(chave);
        return optionalChave.isPresent();
    }

}
