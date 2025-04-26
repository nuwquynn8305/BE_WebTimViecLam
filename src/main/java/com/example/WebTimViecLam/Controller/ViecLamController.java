package com.example.WebTimViecLam.Controller;

import com.example.WebTimViecLam.Entity.ViecLam;
import com.example.WebTimViecLam.Service.ViecLamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/vieclam")
@CrossOrigin
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
            @RequestParam("ma_linh_vuc") Integer maLinhVuc,
            @RequestParam(value = "avt", required = false) MultipartFile avtFile
    ) throws IOException {

        ViecLam viecLam = new ViecLam();
        viecLam.setTen_viec_lam(tenViecLam);
        viecLam.setMuc_luong(mucLuong);
        viecLam.setMo_ta(moTa);
        viecLam.setYeu_cau_cong_viec(yeuCau);
        viecLam.setSo_luong_tuyen(soLuong);
        viecLam.setDia_chi(diaChi);

        ViecLam created = viecLamService.save(viecLam, maLoaiViec, maDoanhNghiep, maLinhVuc, avtFile);
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
            @RequestParam("ma_linh_vuc") Integer maLinhVuc,
            @RequestParam(value = "avt", required = false) MultipartFile avtFile
    ) throws IOException {

        ViecLam viecLam = new ViecLam();
        viecLam.setTen_viec_lam(tenViecLam);
        viecLam.setMuc_luong(mucLuong);
        viecLam.setMo_ta(moTa);
        viecLam.setYeu_cau_cong_viec(yeuCau);
        viecLam.setSo_luong_tuyen(soLuong);
        viecLam.setDia_chi(diaChi);

        ViecLam updated = viecLamService.update(id, viecLam, maLoaiViec, maDoanhNghiep, maLinhVuc, avtFile);
        return ResponseEntity.ok(updated);
    }

    // Xóa việc làm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        viecLamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Lấy việc làm theo doanh nghiệp
    @GetMapping("/doanhnghiep/{maDoanhNghiep}")
    public ResponseEntity<List<ViecLam>> getByCompanyId(@PathVariable Integer maDoanhNghiep) {
        List<ViecLam> viecLamList = viecLamService.getByCompanyId(maDoanhNghiep);
        return ResponseEntity.ok(viecLamList);
    }

    // Tìm kiếm việc làm
    @GetMapping("/search")
    public ResponseEntity<List<ViecLam>> searchJobs(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "jobType", required = false) String jobType) {

        List<String> jobTypes = null;
        if (jobType != null && !jobType.isEmpty()) {
            jobTypes = Arrays.stream(jobType.split(","))
                    .map(String::trim)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        }

        List<ViecLam> results = viecLamService.searchJobs(
                keyword != null ? keyword : "",
                location != null ? location : "",
                jobTypes
        );
        return ResponseEntity.ok(results);
    }
}
