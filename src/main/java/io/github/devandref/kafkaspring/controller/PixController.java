package io.github.devandref.kafkaspring.controller;

import io.github.devandref.kafkaspring.dto.PixDTO;
import io.github.devandref.kafkaspring.service.PixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixController {

    @Autowired
    private PixService pixService;

    @PostMapping("v1/transferir")
    public PixDTO salvarPix(@RequestBody PixDTO pixDTO) {
        return pixService.salvarPix(pixDTO);
    }

}
