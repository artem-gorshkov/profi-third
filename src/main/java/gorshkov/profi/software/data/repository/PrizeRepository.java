package gorshkov.profi.software.data.repository;

import gorshkov.profi.software.data.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizeRepository   extends JpaRepository<Prize, Integer> {
}
