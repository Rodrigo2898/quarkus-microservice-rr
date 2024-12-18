package infrastructure.resources;

import api.CandidateApi;
import api.dto.in.CreateCandidate;
import api.dto.in.UpdateCandidate;
import api.dto.out.Candidate;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/candidates")
public class CandidateResource {
    private final CandidateApi api;

    public CandidateResource(CandidateApi api) {
        this.api = api;
    }

    @POST
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void create(CreateCandidate dto) {
        api.create(dto);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Candidate update(@PathParam("id") String id, UpdateCandidate dto) {
        return api.update(id, dto);
    }

    @GET
    public List<Candidate> list() {
        return api.list();
    }

    @GET
    @Path("/{id}")
    public Candidate get(@PathParam("id") String id) {
        return api.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") String id) {
        api.delete(id);
    }

    @POST
    @Path("/candidates")
    @ResponseStatus(RestResponse.StatusCode.CREATED)
    @Transactional
    public void createCandidate(CreateCandidate dto) {
        api.createCandidate(dto);
    }

    @GET
    @Path("/candidates/{id}")
    public Candidate getCandidate(@PathParam("id") String id) {
        return api.findCandidateById(id);
    }

    @GET
    @Path("/candidates")
    public List<Candidate> listCandidates() {
        return api.listCandidates();
    }

    @DELETE
    @Path("/candidates/{id}")
    @Transactional
    public void deleteCandidate(@PathParam("id") String id) {
        api.deleteCandidate(id);
    }
}
