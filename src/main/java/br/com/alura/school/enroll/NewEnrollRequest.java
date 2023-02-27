package br.com.alura.school.enroll;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class NewEnrollRequest {

    @NotBlank
    @Size(max = 20)
    @JsonProperty("username")
    private final String username;

    @JsonCreator
    NewEnrollRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


}