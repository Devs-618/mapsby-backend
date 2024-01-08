package by.maps.backend.core.base;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Controller
public interface BaseController<Dto extends BaseDto> {

    @GetMapping(value = "/{id}")
    ResponseEntity<Dto> getById(@PathVariable UUID id);

    @GetMapping("/all")
    ResponseEntity<List<Dto>> getAll(Pageable page);

    @PostMapping("/create")
    ResponseEntity<Dto> create(@RequestBody Dto dto);

    @PutMapping("/update")
    ResponseEntity<Dto> update(@RequestBody Dto dto);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<UUID> deleteById(@PathVariable UUID id);
}
