package infrastructure.lifecycle;

import domain.Election;
import infrastructure.repositories.RedisElectionRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

// Quando a aplicação iniciar, no lifecycle do quarkus, a classe será injetada
@Startup
@ApplicationScoped
public class Subscribe {

    private static final Logger LOGGER = Logger.getLogger(Subscribe.class);

    public Subscribe(RedisDataSource redisDataSource, RedisElectionRepository repository) {
        LOGGER.info("Startup: Subscribe");

        redisDataSource.pubsub(String.class).subscribe("election", id -> {
            LOGGER.info("Election: " + id + " received from subscription");
            Election election = repository.findById(id);
            LOGGER.info("Election " + election.id() + " starting");
        });
    }
}
