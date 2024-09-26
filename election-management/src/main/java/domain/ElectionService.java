package domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;

@ApplicationScoped
public class ElectionService {
    private final CandidateService candidateService;
    private final Instance<ElectionRepository> repositories;

    public ElectionService(CandidateService candidateService, Instance<ElectionRepository> repositories) {
        this.candidateService = candidateService;
        this.repositories = repositories;
    }

    public void submit() {
        Election election = Election.create(candidateService.findAll());
        repositories.forEach(repository -> repository.submit(election));
    }
}
