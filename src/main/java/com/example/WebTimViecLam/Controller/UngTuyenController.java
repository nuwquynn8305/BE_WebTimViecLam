package com.example.WebTimViecLam.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WebTimViecLam.Entity.UngTuyen;
import com.example.WebTimViecLam.Service.UngTuyenService;

@RestController
@RequestMapping("/api/ungtuyen")
public class UngTuyenController {

    @Autowired
    private UngTuyenService ungTuyenService;

    // Lấy danh sách tất cả ứng tuyển
    @GetMapping
    public ResponseEntity<List<UngTuyen>> getAll() {
        List<UngTuyen> list = ungTuyenService.getAll();
        return ResponseEntity.ok(list);
    }

    // Lấy thông tin ứng tuyển theo id
    @GetMapping("/{id}")
    public ResponseEntity<UngTuyen> getById(@PathVariable Integer id) {
        UngTuyen ungTuyen = ungTuyenService.getById(id);
        return ResponseEntity.ok(ungTuyen);
    }

    // Tạo mới ứng tuyển
    @PostMapping
    public ResponseEntity<UngTuyen> create(@RequestBody Map<String, Object> payload) {
        String full_name = (String) payload.get("full_name");
        String email = (String) payload.get("email");
        String cau_hoi = (String) payload.get("cau_hoi");
        String ghi_chu = (String) payload.get("ghi_chu");
        String tep_dinh_kem = (String) payload.get("tep_dinh_kem");
        String status = (String) payload.get("status");
        // Lấy các khóa ngoại
        Integer ma_viec_lam = (Integer) payload.get("ma_viec_lam");
        String user_id = (String) payload.get("user_id");

        UngTuyen ungTuyen = new UngTuyen();
        ungTuyen.setFull_name(full_name);
        ungTuyen.setEmail(email);
        ungTuyen.setCau_hoi(cau_hoi);
        ungTuyen.setGhi_chu(ghi_chu);
        ungTuyen.setTep_dinh_kem(tep_dinh_kem);
        ungTuyen.setStatus(status);

        UngTuyen created = ungTuyenService.save(ungTuyen, ma_viec_lam, user_id);
        return ResponseEntity.ok(created);
    }

    // Cập nhật ứng tuyển
    @PutMapping("/{id}")
    public ResponseEntity<UngTuyen> update(@PathVariable Integer id, @RequestBody Map<String, Object> payload) {
        String full_name = (String) payload.get("full_name");
        String email = (String) payload.get("email");
        String cau_hoi = (String) payload.get("cau_hoi");
        String ghi_chu = (String) payload.get("ghi_chu");
        String tep_dinh_kem = (String) payload.get("tep_dinh_kem");
        String status = (String) payload.get("status");
        Integer ma_viec_lam = (Integer) payload.get("ma_viec_lam");
        String user_id = (String) payload.get("user_id");

        UngTuyen ungTuyen = new UngTuyen();
        ungTuyen.setFull_name(full_name);
        ungTuyen.setEmail(email);
        ungTuyen.setCau_hoi(cau_hoi);
        ungTuyen.setGhi_chu(ghi_chu);
        ungTuyen.setTep_dinh_kem(tep_dinh_kem);
        ungTuyen.setStatus(status);

        UngTuyen updated = ungTuyenService.update(id, ungTuyen, ma_viec_lam, user_id);
        return ResponseEntity.ok(updated);
    }

    // Xóa ứng tuyển theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ungTuyenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
