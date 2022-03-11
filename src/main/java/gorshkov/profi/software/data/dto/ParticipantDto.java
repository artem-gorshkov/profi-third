package gorshkov.profi.software.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ParticipantDto {
    @NotBlank(message = "name of participant can't be null")
    private String name;
}
