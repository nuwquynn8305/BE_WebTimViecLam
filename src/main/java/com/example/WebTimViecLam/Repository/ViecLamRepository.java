package com.example.WebTimViecLam.Repository;

import com.example.WebTimViecLam.Entity.ViecLam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViecLamRepository extends JpaRepository<ViecLam, Integer> {
    // Định nghĩa thêm các phương thức truy vấn nếu cần
}
