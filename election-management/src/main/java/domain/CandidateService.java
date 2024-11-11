package domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped // Anotação de contexto e injeção de dependência
public class CandidateService {
    private final CandidateRepository repository;
    private final infrastructure.repositories.PanacheCandidateRepository panacheCandidateRepository;

    public CandidateService(CandidateRepository repository, infrastructure.repositories.PanacheCandidateRepository  panacheCandidateRepository) {
        this.repository = repository;
        this.panacheCandidateRepository = panacheCandidateRepository;
    }

    public void save(Candidate candidate) {
        repository.save(candidate);
    }

    public List<Candidate> findAll() {
        return repository.findAll();
    }

    public Candidate findById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(String id) {
        repository.delete(id);
    }

    public List<infrastructure.repositories.entities.Candidate> listAll() {
        return panacheCandidateRepository.findCandidate();
    }
}
