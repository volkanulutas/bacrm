package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.data.dto.ProposalDto;
import tr.com.bacompany.bacrm.data.entity.Proposal;

public class ProposalConverter {
    public static Proposal toEntity(ProposalDto source) {
        Proposal target = new Proposal();
        target.setId(source.getId());
        target.setProposalId(source.getProposalId());
        target.setDefinition(source.getDefinition());
        target.setCustomer(CustomerConverter.toEntity(source.getCustomer()));
        target.setDate(source.getDate());
        target.setDeleted(source.isDeleted());
        return target;
    }

    public static ProposalDto toDto(Proposal source) {
        ProposalDto target = new ProposalDto();
        target.setId(source.getId());
        target.setProposalId(source.getProposalId());
        target.setDefinition(source.getDefinition());
        target.setCustomer(CustomerConverter.toDto(source.getCustomer()));
        target.setDate(source.getDate());
        target.setDeleted(source.isDeleted());
        return target;
    }
}
