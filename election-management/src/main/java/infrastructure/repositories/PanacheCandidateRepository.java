package infrastructure.repositories;

import infrastructure.repositories.entities.Candidate;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PanacheCandidateRepository implements domain.PanacheCandidateRepository {
    public List<Candidate> findCandidate() {
        return listAll();
    }
}
