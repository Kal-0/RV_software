package domain.entities.clientservice;

public interface QueueManagerInterface {
    void addToQueue(ClientServices clientService);
    ClientServices callNextNumber();
    boolean hasNext();
    String getQueueStatus();
}
