package com.example.WebTimViecLam.Repository;

import com.example.WebTimViecLam.Entity.DoanhNghiep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoanhNghiepRepository extends JpaRepository<DoanhNghiep, Integer> {
    // Nếu cần, có thể định nghĩa thêm các phương thức tìm kiếm theo tên, địa chỉ, v.v.
}
