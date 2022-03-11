package gorshkov.profi.software.controller;

import gorshkov.profi.software.data.dto.ParticipantDto;
import gorshkov.profi.software.data.dto.PrizeDto;
import gorshkov.profi.software.data.dto.PromoResult;
import gorshkov.profi.software.data.repository.ParticipantRepository;
import gorshkov.profi.software.data.repository.PrizeRepository;
import gorshkov.profi.software.data.repository.PromoRepository;
import gorshkov.profi.software.util.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static gorshkov.profi.software.util.ConvertUtil.convert;
import static gorshkov.profi.software.util.ExceptionUtil.createEntityNotFoundException;

@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PrizeController {
    private final PromoRepository promoRepository;
    private final PrizeRepository prizeRepository;


    @PostMapping("/{promoId}/prize")
    @ApiOperation("Создать приз")
    public Integer createPrize(@Valid PrizeDto prizeDto, @PathVariable int promoId) {
        var promo = promoRepository.findById(promoId).orElseThrow(createEntityNotFoundException(promoId));
        var prize = prizeRepository.save(convert(prizeDto));
        promo.getPrizes().add(prize);
        promoRepository.save(promo);
        return prize.getId();
    }

    @DeleteMapping("/{promoId}/prize/{id}")
    @ApiOperation("Удалить приз")
    public void deletePrize(@PathVariable int id, @PathVariable int promoId) {
        var promo = promoRepository.findById(promoId)
                .orElseThrow(createEntityNotFoundException(promoId));

        var prize = promo.getPrizes().stream()
                .filter(it -> it.getId().equals(id))
                .findAny()
                .orElseThrow(createEntityNotFoundException(id));

        prizeRepository.delete(prize);
    }
}

