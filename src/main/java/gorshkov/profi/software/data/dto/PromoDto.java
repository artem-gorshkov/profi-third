package gorshkov.profi.software.data.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PromoDto {
    @NotBlank(message = "name of promo can't be null")
    private String name;
    private String description;
}
