package ir.behpardakht.zoneAndAreaManagement.service;

import ir.behpardakht.zoneAndAreaManagement.model.dto.ZoneDto;

import java.util.List;

public interface ZoneService {
    List<ZoneDto> getAllZones(String code);

    List<ZoneDto> getZonesByAreaCode(String areaCode);

    ZoneDto saveZone(ZoneDto zone, String areaCode);

    ZoneDto updateZone(String areaCode, ZoneDto zone);

    void deleteZone(Long id);

    ZoneDto getZoneByCode(String code);
}
