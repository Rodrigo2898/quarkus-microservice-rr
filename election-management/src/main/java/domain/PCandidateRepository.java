package domain;

import java.util.List;
import java.util.Optional;

public interface PCandidateRepository {
    void save(List<Candidate> candidates);

    default void saveCandidate(Candidate candidate) {
        save(List.of(candidate));
    }

    List<Candidate> find(CandidateQuery query);

    default List<Candidate> listAllCandidates() {
        return find(new CandidateQuery.Builder().build());
    }

    Optional<Candidate> findCandidate(String id);

    void remove(Candidate candidate);

    default void removeCandidate(String id) {
        Candidate candidate = findCandidate(id).orElseThrow();
        remove(candidate);
    }
}
