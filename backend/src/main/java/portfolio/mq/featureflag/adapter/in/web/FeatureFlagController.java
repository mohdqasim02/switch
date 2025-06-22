package portfolio.mq.featureflag.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import portfolio.mq.featureflag.application.dto.FeatureFlagDto;
import portfolio.mq.featureflag.application.service.FeatureFlagService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/toggles")
@Validated
public class FeatureFlagController {

    private final FeatureFlagService service;

    public FeatureFlagController(FeatureFlagService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<FeatureFlagDto> getAllFlags() {
        return service.getAllFlags();
    }

    @PostMapping("/create")
    public ResponseEntity<FeatureFlagDto> createFlag(@RequestBody FeatureFlagDto dto) {
        FeatureFlagDto created = service.createFlag(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
