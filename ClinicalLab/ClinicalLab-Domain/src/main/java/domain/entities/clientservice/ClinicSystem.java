package domain.entities.clientservice;

import domain.entities.client.Client;

public class ClinicSystem {

    private QueueManager queueManager;

    public ClinicSystem() {
        this.queueManager = new QueueManager();
    }

    /*
    public void handleClientArrival(ClientService clientService) {
        queueManager.addClientToQueue(clientService);
    }
    */

}
