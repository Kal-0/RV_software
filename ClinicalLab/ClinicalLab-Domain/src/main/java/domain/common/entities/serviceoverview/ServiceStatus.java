package domain.common.entities.serviceoverview;

public class ServiceStatus {
    private int waitingClients;
    private int clientsInCollection;
    private int completedServices;
    private ServiceStatusId serviceStatusId;

    public ServiceStatus(int waitingClients, int clientsInCollection, int completedServices, ServiceStatusId serviceStatusId) {
        this.waitingClients = waitingClients;
        this.clientsInCollection = clientsInCollection;
        this.completedServices = completedServices;
        this.serviceStatusId = serviceStatusId;
    }

    public int getWaitingClients() {
        return waitingClients;
    }

    public void setWaitingClients(int waitingClients) {
        this.waitingClients = waitingClients;
    }

    public int getClientsInCollection() {
        return clientsInCollection;
    }

    public void setClientsInCollection(int clientsInCollection) {
        this.clientsInCollection = clientsInCollection;
    }

    public int getCompletedServices() {
        return completedServices;
    }

    public void setCompletedServices(int completedServices) {
        this.completedServices = completedServices;
    }

    public ServiceStatusId getServiceStatusId() {
        return serviceStatusId;
    }

    public void setServiceStatusId(ServiceStatusId serviceStatusId) {
        this.serviceStatusId = serviceStatusId;
    }
}

