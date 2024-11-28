package domain.entities.clientservice;

public abstract class QueueManagerDecorator implements QueueManagerInterface {
    protected QueueManagerInterface decoratedQueueManager;

    public QueueManagerDecorator(QueueManagerInterface queueManager) {
        this.decoratedQueueManager = queueManager;
    }

    @Override
    public void addToQueue(ClientServices clientService) {
        decoratedQueueManager.addToQueue(clientService);
    }

    @Override
    public ClientServices callNextNumber() {
        return decoratedQueueManager.callNextNumber();
    }

    @Override
    public boolean hasNext() {
        return decoratedQueueManager.hasNext();
    }

    @Override
    public String getQueueStatus() {
        return decoratedQueueManager.getQueueStatus();
    }
}
