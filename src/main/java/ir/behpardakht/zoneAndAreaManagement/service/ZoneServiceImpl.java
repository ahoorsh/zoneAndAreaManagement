package ir.behpardakht.zoneAndAreaManagement.service;

import ir.behpardakht.zoneAndAreaManagement.model.AreaModel;
import ir.behpardakht.zoneAndAreaManagement.model.ZoneModel;
import ir.behpardakht.zoneAndAreaManagement.model.dto.ZoneDto;
import ir.behpardakht.zoneAndAreaManagement.model.dto.mapper.ZoneMapper;
import ir.behpardakht.zoneAndAreaManagement.repository.AreaRepository;
import ir.behpardakht.zoneAndAreaManagement.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;
    private final AreaRepository areaRepository;
    private final ZoneMapper zoneMapper;

    @Override
    public List<ZoneDto> getAllZones(String code) {
        AreaModel areaModel = areaRepository.findAreaModelByCode(code);
        return zoneMapper.toDTOs(areaModel.getZoneModels());
    }

    @Override
    public List<ZoneDto> getZonesByAreaCode(String areaCode) {
        AreaModel areaModel = areaRepository.findByCode(areaCode);
        if (areaModel == null) {
            throw new RuntimeException("Area not found for code: " + areaCode);
        }

        List<ZoneModel> zoneModels = zoneRepository.findByArea(areaModel);
        return zoneMapper.toDTOs(zoneModels);
    }

    @Override
    public ZoneDto saveZone(ZoneDto zone, String areaCode) {
        AreaModel areaModel = areaRepository.findAreaModelByCode(areaCode);
        if (areaModel == null) {
            throw new RuntimeException("Area does not exist");
        }

        ZoneModel zoneModel = zoneMapper.toEntity(zone);
        zoneModel.setArea(areaModel);
        zoneRepository.saveAndFlush(zoneModel);
        return zoneMapper.toDTO(zoneModel);
    }

    @Override
    public ZoneDto updateZone(String areaCode, ZoneDto zone) {
        AreaModel areaModel = areaRepository.findAreaModelByCode(areaCode);
        ZoneModel existingZone = zoneRepository.findByCode(zone.getCode());
        if (existingZone == null) {
            throw new RuntimeException("Not Found");
        }
        existingZone.setName(zone.getName());
        existingZone.setCode(zone.getCode());
        existingZone.setArea(areaModel);
        zoneRepository.save(existingZone);
        return zoneMapper.toDTO(existingZone);
    }


    @Override
    public void deleteZone(Long id) {
        ZoneModel existingZone = zoneRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        zoneRepository.deleteById(existingZone.getId());
    }

    @Override
    public ZoneDto getZoneByCode(String code) {
        ZoneModel zoneModel = zoneRepository.findByCode(code);
        if (zoneModel == null) {
            throw new RuntimeException("Zone not found for code: " + code);
        }
        return zoneMapper.toDTO(zoneModel);
    }
}
