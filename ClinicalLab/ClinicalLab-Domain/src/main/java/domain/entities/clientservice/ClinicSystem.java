package domain.entities.clientservice;

import domain.entities.client.Client;

public class ClinicSystem {

    private QueueManager queueManager;

    public ClinicSystem() {
        this.queueManager = QueueManager.getInstance();
    }

    /*
    public void handleClientArrival(ClientServices clientService) {
        queueManager.addToQueue(clientService);
    }
    */
}
