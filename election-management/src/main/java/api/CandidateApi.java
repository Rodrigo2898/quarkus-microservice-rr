package api;

import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import domain.Candidate;
import domain.CandidateService;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidateApi {
    private final CandidateService service;

    public CandidateApi(CandidateService service) {
        this.service = service;
    }

    public void create(CreateCandidate dto) {
        service.save(dto.toDomain());
    }

    public api.dto.out.Candidate update(String id, UpdateCandidate dto) {
        service.save(dto.toDomain(id));
        Candidate candidate = service.findById(id);
        return api.dto.out.Candidate.fromDomain(candidate);
    }

    public List<api.dto.out.Candidate> list() {
        return service.findAll().stream().map(api.dto.out.Candidate::fromDomain).toList();
    }

    public api.dto.out.Candidate findById(String id) {
        Candidate candidate = service.findById(id);
        return api.dto.out.Candidate.fromDomain(candidate);
    }

    public void delete(String id) {
        service.delete(id);
    }

    public void createCandidate(CreateCandidate dto) {
        service.saveCandidate(dto.toDomain());
    }

    public api.dto.out.Candidate findCandidateById(String id) {
        Candidate candidate = service.findCandidateById(id);
        return api.dto.out.Candidate.fromDomain(candidate);
    }

    public List<api.dto.out.Candidate> listCandidates() {
        return service.findCandidates().stream().map(api.dto.out.Candidate::fromDomain).toList();
    }

    public void deleteCandidate(String id) {
        service.deleteCandidate(id);
    }
}
