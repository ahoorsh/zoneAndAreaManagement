package ir.behpardakht.zoneAndAreaManagement.service;

import ir.behpardakht.zoneAndAreaManagement.model.AreaModel;
import ir.behpardakht.zoneAndAreaManagement.model.dto.AreaDto;
import ir.behpardakht.zoneAndAreaManagement.model.dto.mapper.AreaMapper;
import ir.behpardakht.zoneAndAreaManagement.repository.AreaRepository;
import ir.behpardakht.zoneAndAreaManagement.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;
    private final ZoneRepository zoneRepository;
    private final AreaMapper areaMapper;

    @Override
    public List<AreaDto> getAllAreas() {
        return areaMapper.toDTOs(areaRepository.findAll());
    }

    @Override
    public AreaDto getAreaById(Long id) {
        return areaMapper.toDTO(areaRepository.findById(id).orElse(null));
    }

    @Override
    public AreaDto getAreaByCode(String code) {
        AreaModel areaModel = areaRepository.findAreaModelByCode(code);
        if (areaModel == null) {
            return null;
        }
        return areaMapper.toDTO(areaModel);
    }

    @Override
    public AreaDto saveArea(AreaDto area) {
        AreaModel model = areaRepository.findByCode(area.getCode());
        if (model != null) {
            return areaMapper.toDTO(model);
        }
        AreaModel areaModel = areaMapper.toEntity(area);
        areaRepository.saveAndFlush(areaModel);
        return areaMapper.toDTO(areaModel);
    }

    @Override
    public AreaDto updateArea(String code, AreaDto area) {
        AreaModel existingArea = areaRepository.findAreaModelByCode(code);
        if (existingArea == null) {
            throw new RuntimeException("Not Found");
        }
        existingArea.setName(area.getName());
        existingArea.setCode(area.getCode());
        areaRepository.save(existingArea);
        return areaMapper.toDTO(existingArea);
    }

    @Override
    public void deleteArea(String code) {
        AreaModel existingArea = areaRepository.findAreaModelByCode(code);
        if (existingArea == null) {
            throw new RuntimeException("Not Found");
        }
        long zoneCount = zoneRepository.countByArea(existingArea);
        if (zoneCount > 0) {
            throw new RuntimeException("Cannot delete area because it has related zones");
        }
        areaRepository.delete(existingArea);

    }

}