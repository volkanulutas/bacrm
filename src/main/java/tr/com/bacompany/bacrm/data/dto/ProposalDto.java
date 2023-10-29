package tr.com.bacompany.bacrm.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProposalDto {
    private Long id;

    private String proposalId;

    private String definition;

    private CustomerDto customer;

    private long date;

    private boolean deleted;
}
