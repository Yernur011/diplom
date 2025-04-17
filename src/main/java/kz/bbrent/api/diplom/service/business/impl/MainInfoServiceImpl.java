package kz.bbrent.api.diplom.service.business.impl;

import kz.bbrent.api.diplom.domain.entity.TabBar;
import kz.bbrent.api.diplom.domain.entity.business.Items;
import kz.bbrent.api.diplom.domain.repository.TabBarRepository;
import kz.bbrent.api.diplom.service.business.MainInfoService;
import kz.bbrent.api.diplom.service.crud.ItemCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainInfoServiceImpl implements MainInfoService {
    private final ItemCrudService itemCrudService;
    private final TabBarRepository tabBarRepository;

    @Override
    public List<Map<String, Map<String, String>>>getTabBar() {
        return tabBarRepository.findAll().stream().map(TabBar::getData).toList();
    }

    @Override
    public List<Items> forYou() {
        return itemCrudService.findAll();
    }

    @Override
    public List<Items> popular() {
        return itemCrudService.findAll()
                .stream()
                .sorted(Comparator.comparing(Items::getRating))
                .toList();
    }

    @Override
    public List<Items> news() {
        return itemCrudService.findAll()
                .stream()
                .sorted(Comparator.comparing(Items::getCreatedAt).reversed())
                .toList();
    }
}
