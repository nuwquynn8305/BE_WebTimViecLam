package com.example.WebTimViecLam.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.WebTimViecLam.Entity.DoanhNghiep;
import com.example.WebTimViecLam.Service.DoanhNghiepService;

@RestController
@RequestMapping("/api/doanhnghiep")
@CrossOrigin
public class DoanhNghiepController {

    @Autowired
    private DoanhNghiepService doanhNghiepService;

    // Lấy danh sách tất cả doanh nghiệp
    @GetMapping
    public ResponseEntity<List<DoanhNghiep>> getAll() {
        List<DoanhNghiep> list = doanhNghiepService.getAll();
        return ResponseEntity.ok(list);
    }

    // Lấy thông tin doanh nghiệp theo id
    @GetMapping("/{id}")
    public ResponseEntity<DoanhNghiep> getById(@PathVariable Integer id) {
        DoanhNghiep dn = doanhNghiepService.getById(id);
        return ResponseEntity.ok(dn);
    }

    // Tạo mới doanh nghiệp
    @PostMapping
    public ResponseEntity<DoanhNghiep> create(@RequestBody DoanhNghiep doanhNghiep) {
        DoanhNghiep created = doanhNghiepService.save(doanhNghiep);
        return ResponseEntity.ok(created);
    }

    // Cập nhật doanh nghiệp theo id
    @PutMapping("/{id}")
    public ResponseEntity<DoanhNghiep> update(@PathVariable Integer id, @RequestBody DoanhNghiep doanhNghiep) {
        DoanhNghiep updated = doanhNghiepService.update(id, doanhNghiep);
        return ResponseEntity.ok(updated);
    }

    // Xóa doanh nghiệp theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        doanhNghiepService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
