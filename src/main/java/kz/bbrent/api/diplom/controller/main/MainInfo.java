package kz.bbrent.api.diplom.controller.main;

import kz.bbrent.api.diplom.domain.entity.business.Items;
import kz.bbrent.api.diplom.service.business.MainInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static kz.bbrent.api.diplom.utills.codes.Uris.API_V1;
import static kz.bbrent.api.diplom.utills.codes.Uris.MAIN_INFO;

@RestController
@RequestMapping(API_V1 + MAIN_INFO)
@RequiredArgsConstructor
public class MainInfo {
    private final MainInfoService mainInfoService;

    @GetMapping("/tab-bar")
    public ResponseEntity<List<Map<String, Map<String, String>>>> tabBar() {
        return ResponseEntity.ok(mainInfoService.getTabBar());
    }
    @GetMapping("/for-you")
    public ResponseEntity<List<Items>> forYou() {
        return ResponseEntity.ok(mainInfoService.forYou());
    }
    @GetMapping("/popular")
    public ResponseEntity<List<Items>> popular() {
        return ResponseEntity.ok(mainInfoService.popular());
    }
    @GetMapping("/news")ResponseEntity<List<Items>> news(){
        return ResponseEntity.ok(mainInfoService.news());
    }
}
