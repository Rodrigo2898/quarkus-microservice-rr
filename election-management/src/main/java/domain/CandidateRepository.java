package domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CandidateRepository {
    void save(List<Candidate> candidates);

    default void save(Candidate candidate) {
        save(List.of(candidate));
    }

    List<Candidate> find(CandidateQuery query);

    default List<Candidate> findAll() {
        return find(new CandidateQuery.Builder().build());
    }

    default Optional<Candidate> findById(String id) {
        CandidateQuery.Builder builder = new CandidateQuery.Builder();
        builder.ids(Set.of(id));
        CandidateQuery query = builder.build();

        return find(query).stream().findFirst();
    }

    void delete(Candidate candidate);

    default void delete(String id) {
        Candidate candidate = findById(id).orElseThrow();
        delete(candidate);
    }

}
