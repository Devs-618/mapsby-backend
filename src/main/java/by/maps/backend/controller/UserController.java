package by.maps.backend.controller;

import by.maps.backend.api.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@SecurityRequirement(name = "Bearer Auth")
@Tag(name = "User service", description = "User controller")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found")
@ApiResponse(responseCode = "401", description = "Unauthorized")
@ApiResponse(responseCode = "503", description = "Service unavailable")
public interface UserController {
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Getting user details. This endpoint requires a JWT token in the Authorization header")
    ResponseEntity<UserDto> getAuthenticatedUser();

    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    @Operation(description = "Updating user. This endpoint requires a JWT token in the Authorization header")
    ResponseEntity<UserDto> update(@RequestBody UserDto userDto);
}
