package com.agilewriters.workshop.services;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {
    String client_id;
    String destination_url;
    String redirection_url;
    String cognito_oauth_url;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getDestination_url() {
        return destination_url;
    }

    public void setDestination_url(String destination_url) {
        this.destination_url = destination_url;
    }

    public String getRedirection_url() {
        return redirection_url;
    }

    public void setRedirection_url(String redirection_url) {
        this.redirection_url = redirection_url;
    }

    public String getCognito_oauth_url() {
        return cognito_oauth_url;
    }

    public void setCognito_oauth_url(String cognito_oauth_url) {
        this.cognito_oauth_url = cognito_oauth_url;
    }

}