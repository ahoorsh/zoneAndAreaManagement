package ir.behpardakht.zoneAndAreaManagement.model.dto.mapper;

import ir.behpardakht.zoneAndAreaManagement.model.AreaModel;
import ir.behpardakht.zoneAndAreaManagement.model.dto.AreaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper extends BaseMapper<AreaDto , AreaModel >  {
}
