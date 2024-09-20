package infrastructure.repositories;

import domain.CandidateRepository;
import domain.CandidateRepositoryTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class SQLCandidateRepositoryTest extends CandidateRepositoryTest {
    @Inject
    SQLCandidateRepository repository;

    @Override
    public CandidateRepository repository() {
        return repository;
    }
}