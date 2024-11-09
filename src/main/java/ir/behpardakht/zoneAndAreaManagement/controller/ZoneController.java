package ir.behpardakht.zoneAndAreaManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import ir.behpardakht.zoneAndAreaManagement.model.dto.ZoneDto;
import ir.behpardakht.zoneAndAreaManagement.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/byAreaCode")
    public ResponseEntity<List<ZoneDto>> getZonesByAreaCode(@RequestParam String areaCode) {
        try {
            List<ZoneDto> zoneDtos = zoneService.getZonesByAreaCode(areaCode);
            return ResponseEntity.ok(zoneDtos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @PatchMapping("/edit")
    public ResponseEntity<ZoneDto> updateZone(@RequestParam(name = "zoneCode") String zoneCode, @RequestBody ZoneDto zoneDto) {
        ZoneDto updatedZone = zoneService.updateZone(zoneCode, zoneDto);
        return new ResponseEntity<>(updatedZone, HttpStatus.OK);
    }

    @Operation(summary = "Delete an zone")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteZone(@RequestParam(name = "zoneId") Long id) {
        zoneService.deleteZone(id);
        return ResponseEntity.ok("ناحیه با موفقیت حذف شد!");
    }
}
