package domain.common.entities.clientservice;

public class ClinicSystem {

    private QueueManager queueManager;

    public ClinicSystem() {
        this.queueManager = new QueueManager();
    }

    public void handleClientArrival(ClientService clientService) {
        queueManager.addClientToQueue(clientService);
    }

    public ClientService callNextClient() {
        return queueManager.getNextClient();
    }
}
