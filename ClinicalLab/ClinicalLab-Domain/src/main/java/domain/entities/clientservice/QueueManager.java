package domain.entities.clientservice;

import java.util.LinkedList;
import java.util.Queue;

public class QueueManager implements QueueManagerInterface {

    private static QueueManager instance; 

    private Queue<ClientServices> priorityQueue;
    private Queue<ClientServices> standardQueue;
    private int priorityCalls = 0;

    private QueueManager() {
        this.priorityQueue = new LinkedList<>();
        this.standardQueue = new LinkedList<>();
    }

    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    @Override
    public void addToQueue(ClientServices clientService) {
        if (clientService.getServiceNumber().isPriority()) {
            priorityQueue.offer(clientService);
        } else {
            standardQueue.offer(clientService);
        }
    }

    @Override
    public ClientServices callNextNumber() {
        if (!priorityQueue.isEmpty() && (priorityCalls < 2 || standardQueue.isEmpty())) {
            priorityCalls++;
            return priorityQueue.poll();
        } else if (!standardQueue.isEmpty()) {
            priorityCalls = 0;
            return standardQueue.poll();
        } else if (!priorityQueue.isEmpty()) {
            return priorityQueue.poll();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return !priorityQueue.isEmpty() || !standardQueue.isEmpty();
    }

    @Override
    public String getQueueStatus() {
        if (priorityQueue.isEmpty() && standardQueue.isEmpty()) {
            return "Nenhum número de atendimento disponível no momento.";
        } else {
            return "Há números aguardando atendimento.";
        }
    }
}
