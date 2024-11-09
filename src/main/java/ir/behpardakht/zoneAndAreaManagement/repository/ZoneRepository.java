package ir.behpardakht.zoneAndAreaManagement.repository;

import ir.behpardakht.zoneAndAreaManagement.model.AreaModel;
import ir.behpardakht.zoneAndAreaManagement.model.ZoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneModel, Long> {
    long countByArea(AreaModel area);

    List<ZoneModel> findByArea(AreaModel area);

    ZoneModel findByCode(String code);
}