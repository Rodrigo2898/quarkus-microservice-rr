package infrastructure.repositories;

import domain.Candidate;
import domain.CandidateQuery;
import domain.CandidateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class SQLCandidateRepository implements CandidateRepository {

    private final EntityManager entityManager;

    public SQLCandidateRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public void save(List<Candidate> candidates) {
        candidates.stream()
                .map(infrastructure.repositories.entities.Candidate::fromDomain)
                .forEach(entityManager::merge);
    }

    @Override
    @Transactional
    public List<Candidate> find(CandidateQuery query) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(infrastructure.repositories.entities.Candidate.class);
        var root = cq.from(infrastructure.repositories.entities.Candidate.class);

        cq.select(root).where(conditions(query, cb, root));

        return entityManager
                .createQuery(cq)
                .getResultStream()
                .map(infrastructure.repositories.entities.Candidate::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Candidate candidate) {
        var entity = entityManager.find(infrastructure.repositories.entities.Candidate.class, candidate.id());

        if (entity != null) {
            entityManager.remove(entity);
        }
    }


    private Predicate[] conditions(CandidateQuery query,
                                   CriteriaBuilder cb,
                                   Root<infrastructure.repositories.entities.Candidate> root) {
        return Stream.of(query.ids().map(id -> cb.in(root.get("id")).value(id)),
                        query.name().map(name -> cb.or(cb.like(cb.lower(root.get("familyName")), name.toLowerCase() + "%"),
                                cb.like(cb.lower(root.get("givenName")), name.toLowerCase() + "%"))))
                .flatMap(Optional::stream)
                .toArray(Predicate[]::new);
    }
}
