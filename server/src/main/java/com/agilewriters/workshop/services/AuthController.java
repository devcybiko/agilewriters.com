package com.agilewriters.workshop.services;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthConfig config;

    @GetMapping("/login")
    protected void login(HttpServletRequest req, HttpServletResponse resp, //
            @RequestParam String code, @RequestParam String state) //
            throws ServletException, IOException {
        System.out.println("/auth/login");
        System.out.println(" code: " + code + " state: " + state);
        Map<String, String> map = this.oauth(code, config.client_id, config.redirection_url);
        req.getSession().setAttribute("cognito_code", code);
        req.getSession().setAttribute("cognito_map", map);
        Cookie cookie = new Cookie("loggedin", "true");
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);   
        resp.addCookie(cookie);
        resp.sendRedirect(req.getContextPath() + config.destination_url);
    }

    @GetMapping("/logout")
    protected void logout(HttpServletRequest req, HttpServletResponse resp) //
            throws ServletException, IOException {
        System.out.println("/auth/logout");
        req.getSession().setAttribute("cognito_code", null);
        req.getSession().setAttribute("cognito_map", null);
        Cookie cookie = new Cookie("loggedin", "false");
        cookie.setPath("/");
        cookie.setSecure(false);
        cookie.setHttpOnly(false);   
        resp.addCookie(cookie);
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath() + config.destination_url);
    }

    private class ValidateDTO {
        private String id;
        private Boolean isvalid;

        public String getId() {
            return id;
        }

        public Boolean getIsvalid() {
            return isvalid;
        }

        public void setIsvalid(Boolean isvalid) {
            this.isvalid = isvalid;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    @GetMapping("/validate")
    protected ResponseEntity<ValidateDTO> validate(HttpServletRequest req, HttpServletResponse resp) //
            throws ServletException, IOException {
        System.out.println("/auth/validate");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ValidateDTO result = new ValidateDTO();
        result.setId(req.getSession().getId());
        result.setIsvalid(req.getSession().getAttribute("cognito_code") != null);
        return new ResponseEntity<ValidateDTO>(result, HttpStatus.OK);
    }

    private Map<String, String> oauth(String code, String client_id, String redirect_uri)
            throws JsonMappingException, JsonProcessingException, HttpClientErrorException {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", client_id);
        form.add("code", code);
        form.add("redirect_uri", redirect_uri);
        HttpEntity<MultiValueMap<String, Object>> r = new HttpEntity<>(form, headers);
        System.out.println(r.getBody().toString());
        String json = rt.postForObject(config.cognito_oauth_url, r, String.class);
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(json, Map.class);
        System.out.println(map);
        return map;
    }
}
