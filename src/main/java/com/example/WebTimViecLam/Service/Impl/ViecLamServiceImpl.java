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
import com.example.WebTimViecLam.Repository.ViecLamRepository;
import com.example.WebTimViecLam.Repository.LoaiViecRepository;
import com.example.WebTimViecLam.Repository.DoanhNghiepRepository;
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
    private CloudinaryService cloudinaryService;

    @Override
    public List<ViecLam> getAll() {
        return viecLamRepository.findAll();
    }

    @Override
    public ViecLam getById(Integer id) {
        Optional<ViecLam> opt = viecLamRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new RuntimeException("Không tìm thấy việc làm với id: " + id);
    }

    @Override
    public ViecLam save(ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, MultipartFile file) throws IOException {
        // Lấy đối tượng LoaiViec và DoanhNghiep từ database
        LoaiViec loaiViec = loaiViecRepository.findById(ma_loai_viec)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại việc với id: " + ma_loai_viec));
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(ma_doanh_nghiep)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy doanh nghiệp với id: " + ma_doanh_nghiep));

        // Gán cho ViecLam
        viecLam.setLoaiViec(loaiViec);
        viecLam.setDoanhNghiep(doanhNghiep);

        // Nếu có file upload cho avt, upload lên Cloudinary và set URL
        if (file != null && !file.isEmpty()) {
            String avtUrl = cloudinaryService.uploadImage(file);
            viecLam.setAvt(avtUrl);
        }

        // Khi tạo mới, đảm bảo id là null để Hibernate sử dụng persist thay vì merge
        viecLam.setMa_viec_lam(null);
        return viecLamRepository.save(viecLam);
    }

    @Override
    public ViecLam update(Integer id, ViecLam viecLam, Integer ma_loai_viec, Integer ma_doanh_nghiep, MultipartFile file) throws IOException {
        ViecLam exist = getById(id);

        // Cập nhật các trường cơ bản
        exist.setTen_viec_lam(viecLam.getTen_viec_lam());
        // Nếu có file mới, thực hiện upload và cập nhật avt, nếu không giữ nguyên giá trị cũ
        if (file != null && !file.isEmpty()) {
            String avtUrl = cloudinaryService.uploadImage(file);
            exist.setAvt(avtUrl);
        }
        exist.setMuc_luong(viecLam.getMuc_luong());
        exist.setMo_ta(viecLam.getMo_ta());
        exist.setYeu_cau_cong_viec(viecLam.getYeu_cau_cong_viec());
        exist.setSo_luong_tuyen(viecLam.getSo_luong_tuyen());
        exist.setDia_chi(viecLam.getDia_chi());

        // Cập nhật lại quan hệ nếu có thay đổi
        LoaiViec loaiViec = loaiViecRepository.findById(ma_loai_viec)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại việc với id: " + ma_loai_viec));
        DoanhNghiep doanhNghiep = doanhNghiepRepository.findById(ma_doanh_nghiep)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy doanh nghiệp với id: " + ma_doanh_nghiep));
        exist.setLoaiViec(loaiViec);
        exist.setDoanhNghiep(doanhNghiep);

        return viecLamRepository.save(exist);
    }

    @Override
    public void delete(Integer id) {
        viecLamRepository.deleteById(id);
    }
}
