package gorshkov.profi.software.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PrizeDto {
    @NotBlank(message = "description of prize can't be null")
    private String description;
}
