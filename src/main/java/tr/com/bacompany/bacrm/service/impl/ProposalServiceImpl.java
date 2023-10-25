package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.Proposal;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.ProposalRepository;
import tr.com.bacompany.bacrm.service.ProposalService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("proposalService")
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;

    @Autowired
    public ProposalServiceImpl(ProposalRepository proposalRepository) {this.proposalRepository = proposalRepository;}

    @Override
    public Proposal add(Proposal proposal) {
        return proposalRepository.save(proposal);
    }

    @Override
    public List<Proposal> getAll() {
        return proposalRepository.findAll();
    }

    @Override
    public Proposal getByProposalId(String proposalId) throws ResourceNotFoundException {
        return proposalRepository.getByProposalId(proposalId);
    }

    @Override
    public Proposal getById(Long id) throws ResourceNotFoundException {
        Optional<Proposal> proposalOpt = proposalRepository.findById(id);
        if (proposalOpt.isEmpty()) {
            throw new ResourceNotFoundException("Proposal is not found.", "Proposal");
        }
        return proposalOpt.get();
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException {
        try {
            Proposal proposal = getById(id);
            proposalRepository.delete(proposal);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
