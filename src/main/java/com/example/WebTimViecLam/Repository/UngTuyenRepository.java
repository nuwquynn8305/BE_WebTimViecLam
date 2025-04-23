package com.example.WebTimViecLam.Repository;

import com.example.WebTimViecLam.Entity.UngTuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UngTuyenRepository extends JpaRepository<UngTuyen, Integer> {
    // Nếu cần bổ sung các phương thức truy vấn riêng, thêm ở đây
}
