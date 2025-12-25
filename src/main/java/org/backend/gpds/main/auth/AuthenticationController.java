package org.backend.gpds.main.auth;


import org.backend.gpds.main.dto.auth.AuthResponse;
import org.backend.gpds.main.dto.auth.LoginRequest;
import org.backend.gpds.main.dto.auth.RegisterRequest;
import org.backend.gpds.main.exeptions.BusinessRuleViolationException;
import org.backend.gpds.main.model.RefreshToken;
import org.backend.gpds.main.security.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final RefreshTokenService refreshTokenService;

    private final JwtUtils jwtUtils;

    public AuthenticationController(AuthenticationService authService , RefreshTokenService refreshTokenService , JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody String refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new AuthResponse(token, refreshTokenRequest));
                })
                .orElseThrow(() -> new BusinessRuleViolationException("Refresh token is not in database!"));
    }
}