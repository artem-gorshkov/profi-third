package gorshkov.profi.software.data.dto;

import gorshkov.profi.software.data.Participant;
import gorshkov.profi.software.data.Prize;
import lombok.Data;

@Data
public class RaffleResult {
    private Participant winner;
    private Prize prize;
}
