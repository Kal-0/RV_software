package domain.common.entities.clientservice;

import domain.common.entities.client.Client;

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

    public Client callNextClient() {
        return queueManager.getNextNumber();
    }
}
