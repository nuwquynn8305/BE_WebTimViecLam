package com.example.WebTimViecLam.Service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebTimViecLam.Entity.ViecLam;
import com.example.WebTimViecLam.Entity.LoaiViec;
import com.example.WebTimViecLam.Entity.DoanhNghiep;
import com.example.WebTimViecLam.Entity.LinhVuc;
import com.example.WebTimViecLam.Repository.ViecLamRepository;
import com.example.WebTimViecLam.Repository.LoaiViecRepository;
import com.example.WebTimViecLam.Repository.DoanhNghiepRepository;
import com.example.WebTimViecLam.Repository.LinhVucRepository;
import com.example.WebTimViecLam.Service.ViecLamService;

@Service
public class ViecLamServiceImpl implements ViecLamService {

    @Autowired
    private ViecLamRepository viecLamRepository;

    @Autowired
    private LoaiViecRepository loaiViecRepository;

    @Autowired
    private DoanhNghiepRepository doanhNghiepRepository;

    @Autowired
    private LinhVucRepository linhVucRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public List<ViecLam> getAll() {
        return viecLamRepository.findAll();
    }

    @Override
    public ViecLam getById(Integer id) {
        return viecLamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy việc làm với id: " + id));
    }

    @Override
    public ViecLam save(ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, Integer ma_linh_vuc, MultipartFile file) throws IOException {
        // Gán quan hệ
        LoaiViec loaiViec = loaiViecRepository.findById(ma_loai_viec)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại việc với id: " + ma_loai_viec));
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(ma_doanh_nghiep)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy doanh nghiệp với id: " + ma_doanh_nghiep));
        LinhVuc linhVuc = linhVucRepository.findById(ma_linh_vuc)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lĩnh vực với id: " + ma_linh_vuc));

        viecLam.setLoaiViec(loaiViec);
        viecLam.setDoanhNghiep(doanhNghiep);
        viecLam.setLinhVuc(linhVuc);

        // Upload ảnh nếu có
        if (file != null && !file.isEmpty()) {
            String avtUrl = cloudinaryService.uploadImage(file);
            viecLam.setAvt(avtUrl);
        }

        viecLam.setMa_viec_lam(null); // Để Hibernate tự insert
        return viecLamRepository.save(viecLam);
    }

    @Override
    public ViecLam update(Integer id, ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, Integer ma_linh_vuc, MultipartFile file) throws IOException {
        ViecLam exist = getById(id);

        exist.setTen_viec_lam(viecLam.getTen_viec_lam());
        exist.setMuc_luong(viecLam.getMuc_luong());
        exist.setMo_ta(viecLam.getMo_ta());
        exist.setYeu_cau_cong_viec(viecLam.getYeu_cau_cong_viec());
        exist.setSo_luong_tuyen(viecLam.getSo_luong_tuyen());
        exist.setDia_chi(viecLam.getDia_chi());

        // Nếu có file mới thì cập nhật avt
        if (file != null && !file.isEmpty()) {
            String avtUrl = cloudinaryService.uploadImage(file);
            exist.setAvt(avtUrl);
        }

        // Cập nhật quan hệ
        LoaiViec loaiViec = loaiViecRepository.findById(ma_loai_viec)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại việc với id: " + ma_loai_viec));
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(ma_doanh_nghiep)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy doanh nghiệp với id: " + ma_doanh_nghiep));
        LinhVuc linhVuc = linhVucRepository.findById(ma_linh_vuc)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy lĩnh vực với id: " + ma_linh_vuc));

        exist.setLoaiViec(loaiViec);
        exist.setDoanhNghiep(doanhNghiep);
        exist.setLinhVuc(linhVuc);

        return viecLamRepository.save(exist);
    }

    @Override
    public void delete(Integer id) {
        viecLamRepository.deleteById(id);
    }

    @Override
    public List<ViecLam> getByCompanyId(Integer maDoanhNghiep) {
        return viecLamRepository.findAllByDoanhNghiepMaDoanhNghiep(maDoanhNghiep);
    }

    @Override
    public List<ViecLam> searchJobs(String keyword, String location, List<String> jobType) {
        return viecLamRepository.searchJobs(keyword, location, jobType);
    }
}
