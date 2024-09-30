package domain.common.entities.clientservice;
import java.util.LinkedList;
import java.util.Queue;

import domain.common.entities.client.Client;

public class QueueManager {
    private Queue<Client> priorityQueue = new LinkedList<>();
    private Queue<Client> standardQueue = new LinkedList<>();
    private int priorityCounter = 0; // Counts how many priority clients have been called

    // Add a client to the appropriate queue
    public void addClient(Client client, boolean isPriority) {
        if (isPriority) {
            priorityQueue.add(client);
        } else {
            standardQueue.add(client);
        }
    }

    // Get the next client based on the rule: 2 priority clients, then 1 standard
    public Client getNextClient() {
        if (!priorityQueue.isEmpty() && (priorityCounter < 2 || standardQueue.isEmpty())) {
            priorityCounter++;
            return priorityQueue.poll(); // Call priority client
        } else if (!standardQueue.isEmpty()) {
            priorityCounter = 0; // Reset after calling a standard client
            return standardQueue.poll(); // Call standard client
        } else if (!priorityQueue.isEmpty()) {
            priorityCounter = 0;
            return priorityQueue.poll(); // Call priority if no standard left
        } else {
            System.out.println("No clients in the queue");
            return null; // No clients left
        }
    }

    // Method to check if the queues are empty
    public boolean isQueueEmpty() {
        return priorityQueue.isEmpty() && standardQueue.isEmpty();
    }
}

