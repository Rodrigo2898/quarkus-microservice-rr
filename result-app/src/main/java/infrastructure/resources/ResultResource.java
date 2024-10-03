package infrastructure.resources;

import api.dto.Election;
import infrastructure.rest.ElectionManagement;
import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestStreamElementType;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Path("/")
public class ResultResource {

    private final ElectionManagement electionManagement;

    public ResultResource(@RestClient ElectionManagement electionManagement) {
        this.electionManagement = electionManagement;
    }

    @GET
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    public Multi<List<Election>> results() {
        // A cada 5 segundos cria um evento dentro da Stream,
        // para fazer uma nova requisição do getElections()
        return Multi.createFrom()
                .ticks()
                .every(Duration.of(5, ChronoUnit.SECONDS))
                .onItem()
                .transformToMultiAndMerge(n-> electionManagement.getElections().toMulti());
    }
}
