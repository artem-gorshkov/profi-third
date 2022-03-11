package gorshkov.profi.software.util;

import gorshkov.profi.software.data.Participant;
import gorshkov.profi.software.data.Prize;
import gorshkov.profi.software.data.Promo;
import gorshkov.profi.software.data.dto.ParticipantDto;
import gorshkov.profi.software.data.dto.PrizeDto;
import gorshkov.profi.software.data.dto.PromoDto;
import gorshkov.profi.software.data.dto.PromoResult;

public class ConvertUtil {
    public static Promo convert(PromoDto promoDto) {
        Promo promo = new Promo();
        promo.setName(promoDto.getName());
        promo.setDescription(promoDto.getDescription());
        return promo;
    }

    public static PromoResult convert(Promo promo) {
        PromoResult promoResult = new PromoResult();
        promoResult.setId(promo.getId());
        promoResult.setDescription(promo.getDescription());
        promoResult.setName(promo.getName());
        return promoResult;
    }

    public static Participant convert(ParticipantDto promoDto) {
        Participant promo = new Participant();
        promo.setName(promoDto.getName());
        return promo;
    }

    public static Prize convert(PrizeDto prizeDto) {
        Prize prize = new Prize();
        prize.setDescription(prizeDto.getDescription());
        return prize;
    }
}
