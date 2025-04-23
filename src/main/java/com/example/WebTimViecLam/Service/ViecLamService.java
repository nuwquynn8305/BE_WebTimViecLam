package com.example.WebTimViecLam.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.WebTimViecLam.Entity.ViecLam;

public interface ViecLamService {
    List<ViecLam> getAll();
    ViecLam getById(Integer id);
    // Phương thức save với upload file (avt)
    ViecLam save(ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, MultipartFile file) throws IOException;
    // Phương thức update với upload file (avt)
    ViecLam update(Integer id, ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, MultipartFile file) throws IOException;
    void delete(Integer id);
}
