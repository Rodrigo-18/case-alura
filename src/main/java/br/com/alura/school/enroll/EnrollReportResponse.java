package br.com.alura.school.enroll;

import com.fasterxml.jackson.annotation.JsonProperty;

class EnrollReportResponse {

    @JsonProperty
    private final String email;

    @JsonProperty("quantidade_matriculas")
    private final Long quantidadeMatriculas;

    EnrollReportResponse(EnrollReportDto enrollDto) {
        this.email = enrollDto.getEmail();
        this.quantidadeMatriculas = enrollDto.getQuantidadeMatriculas();
    }


}