package ir.behpardakht.zoneAndAreaManagement.controller;

import ir.behpardakht.zoneAndAreaManagement.model.dto.AreaDto;
import ir.behpardakht.zoneAndAreaManagement.service.AreaServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page/areas")
public class AreaViewController {

    private final AreaServiceImpl areaServiceImpl;

    @GetMapping("/get-all-areas")
    public String getAllAreas(Model model) {
        List<AreaDto> areas = areaServiceImpl.getAllAreas();
        model.addAttribute("areas", areas);
        return "area-list";
    }

    @GetMapping("/create-area")
    public String createAreaForm(Model model) {
        model.addAttribute("areaDto", new AreaDto());
        return "create-area";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createArea(@ModelAttribute AreaDto areaDto) {
        try {
            areaServiceImpl.saveArea(areaDto);
            return ResponseEntity.ok("منطقه با موفقیت اضافه شد!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("خطا در اضافه کردن منطقه!");
        }
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @ModelAttribute
    public void preventCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @GetMapping("/edit-area")
    public String editAreaForm(@RequestParam(name = "code") String code, Model model) {
        AreaDto areaDto = areaServiceImpl.getAreaByCode(code);
        model.addAttribute("areaDto", areaDto);
        return "edit-area";
    }

    @PostMapping("/edit")
    public ResponseEntity<Model> updateArea(@ModelAttribute AreaDto areaDto, Model model) {
        try {
            AreaDto dto = areaServiceImpl.updateArea(areaDto.getCode(), areaDto);
            model.addAttribute("areaDto", dto);
            model.addAttribute("message", "منطقه با موفقیت ویرایش شد!");
            return ResponseEntity.ok(model);
        } catch (Exception e) {
            model.addAttribute("message", "خطا در ویرایش منطقه!");
            return ResponseEntity.status(HttpStatus.OK).body(model);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteArea(@RequestParam(name = "code") String code) {
        try {
            areaServiceImpl.deleteArea(code);
            return ResponseEntity.ok("منطقه با موفقیت حذف شد!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("خطا در حذف منطقه!");
        }
    }
}