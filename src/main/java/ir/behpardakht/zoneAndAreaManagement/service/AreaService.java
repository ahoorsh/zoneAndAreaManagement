package ir.behpardakht.zoneAndAreaManagement.service;

import ir.behpardakht.zoneAndAreaManagement.model.dto.AreaDto;

import java.util.List;

public interface AreaService {
    List<AreaDto> getAllAreas();

    AreaDto getAreaById(Long id);

    AreaDto getAreaByCode(String code);

    AreaDto saveArea(AreaDto area);

    AreaDto updateArea(String code, AreaDto area);

    void deleteArea(String code);
}
