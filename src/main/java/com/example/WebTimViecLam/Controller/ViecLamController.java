package com.example.WebTimViecLam.Controller;

import com.example.WebTimViecLam.Entity.ViecLam;
import com.example.WebTimViecLam.Service.ViecLamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/vieclam")
public class ViecLamController {

    @Autowired
    private ViecLamService viecLamService;

    // Lấy tất cả việc làm
    @GetMapping
    public ResponseEntity<List<ViecLam>> getAll() {
        return ResponseEntity.ok(viecLamService.getAll());
    }

    // Lấy việc làm theo id
    @GetMapping("/{id}")
    public ResponseEntity<ViecLam> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(viecLamService.getById(id));
    }

    // Tạo mới việc làm (upload file)
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ViecLam> create(
            @RequestParam("ten_viec_lam") String tenViecLam,
            @RequestParam("muc_luong") String mucLuong,
            @RequestParam("mo_ta") String moTa,
            @RequestParam("yeu_cau_cong_viec") String yeuCau,
            @RequestParam("so_luong_tuyen") Integer soLuong,
            @RequestParam("dia_chi") String diaChi,
            @RequestParam("ma_loai_viec") Integer maLoaiViec,
            @RequestParam("ma_doanh_nghiep") Integer maDoanhNghiep,
            @RequestParam(value = "avt", required = false) MultipartFile avtFile
    ) throws IOException {

        ViecLam viecLam = new ViecLam();
        viecLam.setTen_viec_lam(tenViecLam);
        viecLam.setMuc_luong(mucLuong);
        viecLam.setMo_ta(moTa);
        viecLam.setYeu_cau_cong_viec(yeuCau);
        viecLam.setSo_luong_tuyen(soLuong);
        viecLam.setDia_chi(diaChi);

        ViecLam created = viecLamService.save(viecLam, maLoaiViec, maDoanhNghiep, avtFile);
        return ResponseEntity.ok(created);
    }

    // Cập nhật việc làm (upload file)
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ViecLam> update(
            @PathVariable Integer id,
            @RequestParam("ten_viec_lam") String tenViecLam,
            @RequestParam("muc_luong") String mucLuong,
            @RequestParam("mo_ta") String moTa,
            @RequestParam("yeu_cau_cong_viec") String yeuCau,
            @RequestParam("so_luong_tuyen") Integer soLuong,
            @RequestParam("dia_chi") String diaChi,
            @RequestParam("ma_loai_viec") Integer maLoaiViec,
            @RequestParam("ma_doanh_nghiep") Integer maDoanhNghiep,
            @RequestParam(value = "avt", required = false) MultipartFile avtFile
    ) throws IOException {

        ViecLam viecLam = new ViecLam();
        viecLam.setTen_viec_lam(tenViecLam);
        viecLam.setMuc_luong(mucLuong);
        viecLam.setMo_ta(moTa);
        viecLam.setYeu_cau_cong_viec(yeuCau);
        viecLam.setSo_luong_tuyen(soLuong);
        viecLam.setDia_chi(diaChi);

        ViecLam updated = viecLamService.update(id, viecLam, maLoaiViec, maDoanhNghiep, avtFile);
        return ResponseEntity.ok(updated);
    }

    // Xóa việc làm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        viecLamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
