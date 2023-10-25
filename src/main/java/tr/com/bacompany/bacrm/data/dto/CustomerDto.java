package tr.com.bacompany.bacrm.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private Long id;

    private String name;

    private String definition;

    private String address;

    private String telephone;

    private Set<ProposalDto> proposals = new HashSet<>();

    // TODO: teklif başlangıc tarihi
    // TODO: teklif bitiş tarihi
}
