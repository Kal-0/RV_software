package domain.common.entities.clientservice;

import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {

    private Queue<ClientServices> priorityQueue;
    private Queue<ClientServices> standardQueue;
    private int priorityCalls = 0;

    public QueueManager() {
        this.priorityQueue = new LinkedList<>();
        this.standardQueue = new LinkedList<>();
    }

    // Adicionar cliente à fila, verificando prioridade
    public void addToQueue(ClientServices clientService) {
        if (clientService.getServiceNumber().isPriority()) {
            priorityQueue.offer(clientService);
        } else {
            standardQueue.offer(clientService);
        }
    }

    // Chamar próximo número de acordo com as regras
    public ClientServices callNextNumber() {
        if (!priorityQueue.isEmpty() && (priorityCalls < 2 || standardQueue.isEmpty())) {
            priorityCalls++;
            return priorityQueue.poll(); // Chama o próximo da fila prioritária
        } else if (!standardQueue.isEmpty()) {
            priorityCalls = 0; // Reseta o contador de prioridade
            return standardQueue.poll(); // Chama o próximo da fila padrão
        } else if (!priorityQueue.isEmpty()) {
            return priorityQueue.poll(); // Chama o próximo da fila prioritária
        } else {
            return null; // Fila vazia
        }
    }

    // Verifica se há mais números na fila
    public boolean hasNext() {
        return !priorityQueue.isEmpty() || !standardQueue.isEmpty();
    }

    // Retorna mensagem quando a fila está vazia
    public String getQueueStatus() {
        if (priorityQueue.isEmpty() && standardQueue.isEmpty()) {
            return "Nenhum número de atendimento disponível no momento.";
        } else {
            return "Há números aguardando atendimento.";
        }
    }
}
