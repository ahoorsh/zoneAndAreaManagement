package ir.behpardakht.zoneAndAreaManagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ir.behpardakht.zoneAndAreaManagement.model.dto.AreaDto;
import ir.behpardakht.zoneAndAreaManagement.service.AreaServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
@Tag(name = "Area Management", description = "Operations related to areas")
public class AreaController {

    private final AreaServiceImpl areaServiceImpl;

    @GetMapping("/get-area")
    public ResponseEntity<AreaDto> getArea(@RequestParam(name = "areaId") long id) {
        AreaDto area = areaServiceImpl.getAreaById(id);
        return new ResponseEntity<>(area, HttpStatus.OK);
    }

    @GetMapping("/get-all-areas")
    public List<AreaDto> getAreas() {
        return areaServiceImpl.getAllAreas();
    }

    @PostMapping("/create")
    public ResponseEntity<AreaDto> createArea(@RequestBody @Valid AreaDto area) {
        AreaDto areaDto = areaServiceImpl.saveArea(area);
        return new ResponseEntity<>(areaDto, HttpStatus.CREATED);
    }

    @PatchMapping("/edit")
    public ResponseEntity<AreaDto> updateArea(@RequestParam(name = "code") String code, @RequestBody AreaDto areaDto) {
        AreaDto updatedArea = areaServiceImpl.updateArea(code, areaDto);
        return new ResponseEntity<>(updatedArea, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteArea(@RequestParam(name = "code") String code) {
        areaServiceImpl.deleteArea(code);
        return new ResponseEntity<>("منطقه با موفقیت حذف شد!", HttpStatus.OK);
    }
}