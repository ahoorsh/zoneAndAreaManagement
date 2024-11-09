package ir.behpardakht.zoneAndAreaManagement.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AreaDto {

    @NotNull
    private String name;

    @NotNull
    private String code;
}
