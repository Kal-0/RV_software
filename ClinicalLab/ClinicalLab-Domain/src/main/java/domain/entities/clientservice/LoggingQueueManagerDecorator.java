package domain.entities.clientservice;

public class LoggingQueueManagerDecorator extends QueueManagerDecorator {

    public LoggingQueueManagerDecorator(QueueManagerInterface queueManager) {
        super(queueManager);
    }

    @Override
    public void addToQueue(ClientServices clientService) {
        System.out.println("Adicionando cliente à fila: " + clientService.getServiceNumber().getNumber());
        super.addToQueue(clientService);
    }

    @Override
    public ClientServices callNextNumber() {
        ClientServices nextClient = super.callNextNumber();
        if (nextClient != null) {
            System.out.println("Chamando próximo cliente: " + nextClient.getServiceNumber().getNumber());
        } else {
            System.out.println("Nenhum cliente disponível para chamada.");
        }
        return nextClient;
    }
}
