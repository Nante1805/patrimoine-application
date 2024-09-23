// src/main/java/com/example/demo/PatrimoineController.java
package com.example.patrimoine;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/patrimoines")
public class PatrimoineController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Path storagePath = Paths.get("patrimoines");

    public PatrimoineController() throws IOException {
        if (!Files.exists(storagePath)) {
            Files.createDirectories(storagePath);
        }
    }

    @PutMapping("/{id}")
    public void createOrUpdatePatrimoine(@PathVariable String id, @RequestBody Patrimoine patrimoine) throws IOException {
        patrimoine.setDerniereModification(LocalDateTime.now());
        Path filePath = storagePath.resolve(id + ".json");
        Files.write(filePath, objectMapper.writeValueAsBytes(patrimoine));
    }

    @GetMapping("/{id}")
    public Patrimoine getPatrimoine(@PathVariable String id) throws IOException {
        Path filePath = storagePath.resolve(id + ".json");
        if (Files.exists(filePath)) {
            return objectMapper.readValue(Files.readAllBytes(filePath), Patrimoine.class);
        } else {
            throw new RuntimeException("Patrimoine non trouvé");
        }
    }
}