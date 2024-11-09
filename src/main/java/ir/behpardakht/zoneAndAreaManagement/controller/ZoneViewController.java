package ir.behpardakht.zoneAndAreaManagement.controller;

import ir.behpardakht.zoneAndAreaManagement.model.dto.ZoneDto;
import ir.behpardakht.zoneAndAreaManagement.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/zones")
public class ZoneViewController {

    private final ZoneService zoneService;

    @GetMapping("/create-zone")
    public String showCreateZoneForm(@RequestParam("areaCode") String areaCode, Model model) {
        ZoneDto zoneDto = new ZoneDto();
        model.addAttribute("zoneDto", zoneDto);
        model.addAttribute("areaCode", areaCode);
        return "zone-form";
    }

    @PostMapping("/create")
    public ResponseEntity<Model> createZone(@ModelAttribute ZoneDto zone, @RequestParam("areaCode") String areaCode, Model model) {
        try {
            ZoneDto dto = zoneService.saveZone(zone, areaCode);
            model.addAttribute("zoneDto", dto);
            model.addAttribute("message", "ناحیه با موفقیت ایجاد شد.");
            return ResponseEntity.ok(model);
        } catch (RuntimeException e) {
            model.addAttribute("message", "خطا در ایجاد ناحیه.");
            return ResponseEntity.status(HttpStatus.OK).body(model);
        }
    }


    @GetMapping("/get-all-zones")
    public String getAllZones(@RequestParam("areaCode") String code, Model model) {
        List<ZoneDto> zones = zoneService.getAllZones(code);
        model.addAttribute("zones", zones);
        return "zone-list";
    }

    @GetMapping("/edit-zone")
    public String editZoneForm(@RequestParam(name = "code") String code, Model model) {
        ZoneDto zoneDto = zoneService.getZoneByCode(code);
        model.addAttribute("zoneDto", zoneDto);

        if (zoneDto.getArea() != null) {
            model.addAttribute("areaCode", zoneDto.getArea().getCode());
        } else {
            model.addAttribute("areaCode", "");
        }

        return "edit-zone";
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> updateZone(@ModelAttribute ZoneDto zoneDto, @RequestParam("areaCode") String areaCode) {
        Map<String, Object> response = new HashMap<>();
        try {
            zoneService.updateZone(areaCode, zoneDto);
            response.put("message", "ناحیه با موفقیت ویرایش شد!");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e) {
            response.put("message", "خطا در ویرایش ناحیه!");
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);
        }
    }
}
