package infrastructure.schedulers;

import domain.Election;
import domain.annotations.Principal;
import infrastructure.repositories.RedisElectionRepository;
import infrastructure.repositories.SQLElectionRepository;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Sync {

    private final SQLElectionRepository sqlRepository;
    private final RedisElectionRepository redisRepository;

    public Sync(@Principal SQLElectionRepository sqlRepository,
                RedisElectionRepository redisRepository) {
        this.sqlRepository = sqlRepository;
        this.redisRepository = redisRepository;
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void sync() {
        List<Election> elections = sqlRepository.findAll();

        elections.forEach(election -> {
            Election updated = redisRepository.sync(election);
            sqlRepository.sync(updated);
        });
    }
}
