package backend.academy.web.controller;

import backend.academy.web.data.webDTO.ImageSettingsDTO;
import backend.academy.web.service.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class WebController {
    private final WebService webService;

    @PostMapping("/generate")
    public ResponseEntity<String> generate(@RequestBody ImageSettingsDTO imageSettingsDTO) {
        log.info("Generation starts: {}", imageSettingsDTO);
        return ResponseEntity.ok(webService.startGeneration(imageSettingsDTO));
    }

    // for interruption without rendering
    @DeleteMapping("stop/{id}")
    public ResponseEntity<Void> terminate(@PathVariable String id) {
        log.info("Termination for: {}", id);
        webService.terminateFractalProcessCompletely(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("progress/{id}")
    public ResponseEntity<String> progress(@PathVariable String id) {
        log.info("Fetching progress for: {}", id);
        return ResponseEntity.ok(webService.getProgress(id));
    }

    @PostMapping("render/{id}")
    public ResponseEntity<String> render(@PathVariable String id) {
        log.info("Rendering for: {}", id);
        webService.stopGeneration(id);
        return ResponseEntity.ok(webService.renderFractal(id));
    }
}
