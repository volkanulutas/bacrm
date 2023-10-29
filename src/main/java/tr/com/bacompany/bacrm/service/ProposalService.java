package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.Proposal;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface ProposalService {
    Proposal save(Proposal proposal);

    List<Proposal> getAll();

    Proposal getByProposalId(String proposalId) throws ResourceNotFoundException;

    Proposal getById(Long id) throws ResourceNotFoundException;

    boolean delete(Long id) throws ResourceNotFoundException;
}
