package gorshkov.profi.software.controller;

import gorshkov.profi.software.data.Promo;
import gorshkov.profi.software.data.dto.PromoDto;
import gorshkov.profi.software.data.dto.PromoResult;
import gorshkov.profi.software.data.dto.RaffleResult;
import gorshkov.profi.software.data.repository.PromoRepository;
import gorshkov.profi.software.util.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static gorshkov.profi.software.util.ConvertUtil.convert;
import static gorshkov.profi.software.util.ExceptionUtil.createEntityNotFoundException;

@RestController
@RequestMapping("/promo")
@AllArgsConstructor
public class PromoController {
    private final PromoRepository promoRepository;

    @PostMapping
    @ApiOperation("Создать промоакцию")
    public Integer createPromo(@Valid PromoDto promoDto) {
        var promo = promoRepository.save(convert(promoDto));
        return promo.getId();
    }

    @GetMapping
    @ApiOperation("Получить все промоакции")
    public Iterable<PromoResult> getPromos() {
        var promos = promoRepository.findAll();
        return promos.stream().map(ConvertUtil::convert).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Получить промоакцию по id")
    public Promo getPromo(@PathVariable int id) {
        return promoRepository.findById(id).orElseThrow(createEntityNotFoundException(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("Изменить заметку")
    public Promo editPromo(@PathVariable int id, PromoDto promoDto) {
        var promo = promoRepository.findById(id).orElseThrow(createEntityNotFoundException(id));
        promo.setDescription(promoDto.getDescription());
        promo.setName(promoDto.getName());
        promoRepository.save(promo);
        return promo;
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удалить заметку")
    public void deletePromo(@PathVariable int id) {
        var promo = promoRepository.findById(id).orElseThrow(createEntityNotFoundException(id));
        promoRepository.delete(promo);
    }

    @PostMapping("/{id}/raffle")
    @ApiOperation("Разыграть призы")
    public List<RaffleResult> raffle(@PathVariable int id) {
        var promo = promoRepository.findById(id).orElseThrow(createEntityNotFoundException(id));
        if (promo.getParticipants().size() == 0 || promo.getParticipants().size() != promo.getPrizes().size()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "paticipants != prizes or empty");
        }

        List<RaffleResult> raffleResultList = new ArrayList<>();
        for (int i = 0; i < promo.getPrizes().size(); i++) {
            var raffleResult = new RaffleResult();
            raffleResult.setPrize(promo.getPrizes().get(i));
            raffleResult.setWinner(promo.getParticipants().get(i));
            raffleResultList.add(raffleResult);
        }
        return raffleResultList;
    }
}

