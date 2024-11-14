package infrastructure.repositories;


import domain.CandidateQuery;
import domain.CandidateRepository;
import domain.PCandidateRepository;
import infrastructure.repositories.entities.Candidate;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class PanacheCandidateRepository implements PCandidateRepository,PanacheRepositoryBase<Candidate, String> {


    @Transactional
    @Override
    public void save(List<domain.Candidate> candidates) {
        List<Candidate> entities = candidates.stream()
                .map(Candidate::fromDomain)
                .toList();
        persist(entities);
    }

    @Transactional
    @Override
    public List<domain.Candidate> find(CandidateQuery query) {
        var queryBuilder = find("");

        Map<String, Object> params = new HashMap<>();
        if (query.ids().isPresent()) {
            Set<String> ids = query.ids().get();
            queryBuilder.filter("id in (?1)", (Parameters) ids);
        }
        if (query.name().isPresent()) {
            String name = query.name().get();
            queryBuilder.filter("lower(givenName) like ?1 or lower(familyName) like ?2");
        }

        return queryBuilder.stream()
                .map(Candidate::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<domain.Candidate> findCandidate(String id) {
        return find("id", id).firstResultOptional().map(Candidate::toDomain);
    }

    @Override
    public void remove(domain.Candidate candidate) {
        delete("id", candidate.id());
    }
}
