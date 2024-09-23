package location.service.domain.model.dto;

import location.service.domain.model.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequestDTO {

    private String cpf;
    private String city;

    public Location toEntity() {
        return Location.builder()
                       .cpf(this.cpf)
                       .city(this.city)
                       .build();
    }
}
