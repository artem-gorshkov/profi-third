package gorshkov.profi.software.controller;

import gorshkov.profi.software.data.Participant;
import gorshkov.profi.software.data.Promo;
import gorshkov.profi.software.data.dto.ParticipantDto;
import gorshkov.profi.software.data.dto.PromoDto;
import gorshkov.profi.software.data.dto.PromoResult;
import gorshkov.profi.software.data.repository.ParticipantRepository;
import gorshkov.profi.software.data.repository.PromoRepository;
import gorshkov.profi.software.util.ConvertUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
public class ParticipantController {
    private final PromoRepository promoRepository;
    private final ParticipantRepository participantRepository;

    @PostMapping("/{promoId}/participant")
    @ApiOperation("Создать участника акции")
    public Integer createPromo(@Valid ParticipantDto participantDto, @PathVariable int promoId) {
        var promo = promoRepository.findById(promoId).orElseThrow(createEntityNotFoundException(promoId));
        var participant = participantRepository.save(convert(participantDto));
        promo.getParticipants().add(participant);
        promoRepository.save(promo);
        return participant.getId();
    }

    @DeleteMapping("/{promoId}/participant/{id}")
    @ApiOperation("Удалить участника")
    public void deletePromo(@PathVariable int id, @PathVariable int promoId) {
        var promo = promoRepository.findById(promoId)
                .orElseThrow(createEntityNotFoundException(promoId));

        var participant = promo.getParticipants().stream()
                .filter(it -> it.getId().equals(id))
                .findAny()
                .orElseThrow(createEntityNotFoundException(id));

        participantRepository.delete(participant);
    }
}

