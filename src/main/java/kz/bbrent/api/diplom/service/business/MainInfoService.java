package kz.bbrent.api.diplom.service.business;

import kz.bbrent.api.diplom.domain.entity.business.Items;

import java.util.List;
import java.util.Map;

public interface MainInfoService {
    List<Map<String, Map<String, String>>> getTabBar();

    List<Items> forYou();

    List<Items> popular();

    List<Items> news();

}
