package domain;

import infrastructure.repositories.entities.Candidate;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;


public interface PanacheCandidateRepository extends PanacheRepositoryBase<Candidate, String> {
}
