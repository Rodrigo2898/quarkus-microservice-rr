package domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped // Anotação de contexto e injeção de dependência
public class CandidateService {
    private final CandidateRepository repository;
    private final PCandidateRepository candidateRepository;

    public CandidateService(CandidateRepository repository, PCandidateRepository candidateRepository) {
        this.repository = repository;
        this.candidateRepository = candidateRepository;
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

    public void saveCandidate(Candidate candidate) {
        candidateRepository.saveCandidate(candidate);
    }

    public Candidate findCandidateById(String id) {
        return candidateRepository.findCandidate(id).orElseThrow();
    }

    public List<Candidate> findCandidates() {
        return candidateRepository.listAllCandidates();
    }

    public void deleteCandidate(String id) {
        candidateRepository.removeCandidate(id);
    }
}
