package ws.prager.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UptimeRepository extends CrudRepository<Uptime, Long> {
	List<Uptime> findAll();
}
