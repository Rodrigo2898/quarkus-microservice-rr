package infrastructure.repositories;

import domain.Election;
import domain.ElectionRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisElectionRepository implements ElectionRepository {
    @Override
    public void submit(Election election) {

    }
}
