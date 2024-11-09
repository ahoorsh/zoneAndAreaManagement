package ir.behpardakht.zoneAndAreaManagement.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ZoneDto {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private AreaDto area;
}
