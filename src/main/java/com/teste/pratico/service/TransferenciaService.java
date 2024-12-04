package com.teste.pratico.service;

import com.teste.pratico.exception.TransferenciaException;
import com.teste.pratico.model.Transferencia;
import com.teste.pratico.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    /**
     * Agendar uma transferência financeira.
     * 
     * @param transferencia
     * 
     * @return
     */
    public Transferencia agendarTransferencia(Transferencia transferencia) {
        LocalDate dataTransferencia = transferencia.getDataTransferencia();
        LocalDate dataHoje = LocalDate.now();

        if (dataTransferencia.isBefore(dataHoje)) {
            throw new TransferenciaException("A data da transferência está vencida");
        }

        // Calcular a diferença de dias entre a data de agendamento e a transferência
        long diasDeDiferenca = ChronoUnit.DAYS.between(dataHoje, dataTransferencia);

        // Calcular a taxa com base na tabela
        double taxa = calcularTaxa(diasDeDiferenca, transferencia.getValor());
        if (taxa == 0.0) {
            throw new TransferenciaException("Não há taxa aplicável para a data informada.");
        }

        // Validar os dados da transferência
        validarConta(transferencia.getContaOrigem());
        validarConta(transferencia.getContaDestino());
        validarValor(transferencia.getValor());

        // Definir a taxa e a data de agendamento
        transferencia.setTaxa(taxa);
        transferencia.setDataAgendamento(dataHoje);

        return transferenciaRepository.save(transferencia);
    }

    /**
     * Validar o formato da conta bancária.
     * 
     * @param conta
     */
    private void validarConta(String conta) {
        if (conta == null || conta.length() != 10 || !conta.matches("\\d{10}")) {
            throw new TransferenciaException("O número da conta deve ter exatamente 10 dígitos.");
        }
    }

    /**
     * Validar se o valor da transferência é positivo.
     * 
     * @param valor
     */
    private void validarValor(double valor) {
        if (valor <= 0) {
            throw new TransferenciaException("O valor da transferência deve ser maior que zero.");
        }
    }

    /**
     * Listar todas as transferências agendadas.
     * 
     * @return
     */
    public List<Transferencia> listarTransferencias() {
        return transferenciaRepository.findAll();
    }

    /**
     * Calcular a taxa de transferência com base na tabela.
     * 
     * @param diasDeDiferenca
     * 
     * @param valor
     * @return
     */
    private double calcularTaxa(long diasDeDiferenca, double valor) {
        if (diasDeDiferenca == 0) {
            return 3.0 + (valor * 0.025);
        } else if (diasDeDiferenca <= 10) {
            return 12.0;
        } else if (diasDeDiferenca <= 20) {
            return valor * 0.082;
        } else if (diasDeDiferenca <= 30) {
            return valor * 0.069;
        } else if (diasDeDiferenca <= 40) {
            return valor * 0.047;
        } else if (diasDeDiferenca <= 50) {
            return valor * 0.017;
        }
        return 0.0;
    }
}
