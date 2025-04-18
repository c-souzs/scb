package com.souzs.scb.services;

import com.souzs.scb.config.security.UserDetailsImpl;
import com.souzs.scb.config.security.jwt.JwtUtils;
import com.souzs.scb.domain.dtos.MemberUserDTO;
import com.souzs.scb.domain.dtos.UserAuthDTO;
import com.souzs.scb.domain.dtos.UserBasicDTO;
import com.souzs.scb.domain.dtos.UserDTO;
import com.souzs.scb.domain.entities.Address;
import com.souzs.scb.domain.entities.Member;
import com.souzs.scb.domain.entities.RefreshToken;
import com.souzs.scb.domain.entities.User;
import com.souzs.scb.domain.payloads.TokenCookies;
import com.souzs.scb.repositories.AddressRepository;
import com.souzs.scb.repositories.MemberRepository;
import com.souzs.scb.repositories.RefreshTokenRepository;
import com.souzs.scb.repositories.UserRepository;
import com.souzs.scb.services.exceptions.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MemberRepository memberRepository;

    public UserDTO currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        return new UserDTO(user);
    }

    @Transactional
    public TokenCookies signin(UserAuthDTO userDTO) {
        Authentication authentication = null;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Usuário ou senha inválidos.");
        }

        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();

        String accessToken = jwtUtils.generateAccessJwt(user);
        ResponseCookie accessTokenCookie = jwtUtils.setAccessTokenCookie(accessToken);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUser().getId());
        ResponseCookie refreshTokenCookie = jwtUtils.setRefreshTokenCookie(refreshToken.getToken());

        return new TokenCookies(accessTokenCookie, refreshTokenCookie);
    }

    @Transactional
    public TokenCookies refreshToken(HttpServletRequest request) {
        String accessToken = jwtUtils.getAccessTokenFromCookies(request);

        if(jwtUtils.validateJwtToken(accessToken)) throw new AuthException("Token de acesso ainda válido.");

        String refreshTokenCookie = jwtUtils.getRefreshTokenFromCookies(request);
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenCookie).orElseThrow(
                () -> new AuthException("Refresh Token inválido.")
        );

        if(
                refreshToken != null &&
                        jwtUtils.validateJwtToken(refreshTokenCookie) &&
                        refreshTokenCookie.equals(refreshToken.getToken())
        ) {
            String newAccessToken = jwtUtils.generateAccessJwt(refreshToken.getUser());
            ResponseCookie cookieAccessToken = jwtUtils.setAccessTokenCookie(newAccessToken);

            return new TokenCookies(cookieAccessToken, null);
        } else {
            throw new AuthException("Refresh token expirado.");
        }
    }

    @Transactional
    public TokenCookies signout(HttpServletRequest request) {
        String refreshTokenCookie = jwtUtils.getRefreshTokenFromCookies(request);
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenCookie).orElseThrow(
                () -> new AuthException("Refresh Token inválido.")
        );

        refreshTokenRepository.deleteByUser(refreshToken.getUser());

        ResponseCookie accessClear = jwtUtils.getCleanAccessToken();
        ResponseCookie refreshClear = jwtUtils.getCleanRefreshToken();

        return new TokenCookies(accessClear, refreshClear);
    }

    @Transactional
    public UserDTO signup(MemberUserDTO memberUserDTO) {
        User user = new User();
        user.setEmail(memberUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(memberUserDTO.getPassword()));

        Member member = new Member();
        member.setName(memberUserDTO.getName());
        member.setSurname(memberUserDTO.getSurname());
        member.setPhoneNumber(memberUserDTO.getPhoneNumber());

        Address address = new Address();
        address.setCity(memberUserDTO.getAddress().getCity());
        address.setState(memberUserDTO.getAddress().getState());
        address.setCep(memberUserDTO.getAddress().getCep());
        address.setRoad(memberUserDTO.getAddress().getRoad());
        address.setNumber(Integer.parseInt(memberUserDTO.getAddress().getNumber()));

        member.setAddress(address);
        member.setUser(user);
        user.setMember(member);

        addressRepository.save(address);
        memberRepository.save(member);
        userRepository.save(user);

        return new UserDTO(user);
    }
}
