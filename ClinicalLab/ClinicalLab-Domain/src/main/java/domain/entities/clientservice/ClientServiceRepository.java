package domain.entities.clientservice;

import java.util.List;

public interface ClientServiceRepository {

public void save(ClientServices clientService);

public void delete(ClientServiceId id);

public ClientServices get(ClientServiceId id);

public void update(ClientServices clientService);

public List<ClientServices> getClientServicesAll();

}