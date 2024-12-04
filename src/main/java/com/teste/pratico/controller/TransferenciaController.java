package com.teste.pratico.controller;

import com.teste.pratico.model.Transferencia;
import com.teste.pratico.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public Transferencia agendarTransferencia(@RequestBody Transferencia transferencia) {
        return transferenciaService.agendarTransferencia(transferencia);
    }

    @GetMapping
    public List<Transferencia> listarTransferencias() {
        return transferenciaService.listarTransferencias();
    }
}
