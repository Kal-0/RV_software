package domain.common.entities.clientservice;

import java.util.LinkedList;
import java.util.Queue;

public class QueueManager {

    private Queue<ClientService> priorityQueue;
    private Queue<ClientService> regularQueue;

    public QueueManager() {
        this.priorityQueue = new LinkedList<>();
        this.regularQueue = new LinkedList<>();
    }


    public void addClientToQueue(ClientService clientService) {
        if (clientService.isPriority()) {
            priorityQueue.offer(clientService);
        } else {
            regularQueue.offer(clientService);
        }
    }


    public ClientService getNextClient() {
        if (!priorityQueue.isEmpty() && priorityQueue.size() >= 2) {
            return priorityQueue.poll(); 
        } else if (!regularQueue.isEmpty()) {
            return regularQueue.poll();  
        } else if (!priorityQueue.isEmpty()) {
            return priorityQueue.poll(); 
        }
        return null;
    }

    public int getPriorityQueueSize() {
        return priorityQueue.size();
    }

    public int getRegularQueueSize() {
        return regularQueue.size();
    }
}

