package kz.bbrent.api.diplom.service;

import kz.bbrent.api.diplom.domain.repository.TabBarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final TabBarRepository tabBarRepository;
}
