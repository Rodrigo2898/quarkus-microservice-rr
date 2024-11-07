package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository repository();

    @Test
    void save() {
        Candidate domain = Instancio.create(Candidate.class);
        repository().save(domain);

        Optional<Candidate> result = repository().findById(domain.id());

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findAll() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(30).toList();

        repository().save(candidates);

        List<Candidate> result = repository().findAll();

        assertEquals(candidates.size(), result.size());
    }

    @Test
    void findByName() {
        Candidate candidate1 = Instancio.create(Candidate.class);
        Candidate candidate2 = Instancio.of(Candidate.class)
                .set(field("familyName"), "Feitosa").create();

        CandidateQuery query = new CandidateQuery.Builder().name("FEI").build();

        repository().save(List.of(candidate1, candidate2));

        List<Candidate> result = repository().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate2, result.get(0));
    }

    @Test
    void delete() {
        Candidate candidate = Instancio.create(Candidate.class);
        repository().save(candidate);

        Optional<Candidate> savedCandidate = repository().findById(candidate.id());
        assertTrue(savedCandidate.isPresent());


        repository().delete(candidate.id());
        Optional<Candidate> deletedCandidate = repository().findById(candidate.id());
        assertFalse(deletedCandidate.isPresent());
    }
}