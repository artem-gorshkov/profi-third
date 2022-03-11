package gorshkov.profi.software.data.repository;

import gorshkov.profi.software.data.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository   extends JpaRepository<Participant, Integer> {
}
