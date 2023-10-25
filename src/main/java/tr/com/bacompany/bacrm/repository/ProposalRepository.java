package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    Proposal getByProposalId(String proposalId);
}
