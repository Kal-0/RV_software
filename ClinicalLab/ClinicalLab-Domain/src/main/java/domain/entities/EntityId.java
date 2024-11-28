package domain.entities;

import java.util.Objects;

public abstract class EntityId {
	protected int id;
	
	public EntityId(Integer id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntityId other = (EntityId) obj;
		return id == other.id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	
}
