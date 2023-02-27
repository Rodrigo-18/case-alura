package br.com.alura.school.enroll;

import com.fasterxml.jackson.annotation.JsonProperty;

class EnrollReportResponse {

    @JsonProperty
    private final String email;

    @JsonProperty("quantidade_matriculas")
    private final Long quantidadeMatriculas;

    EnrollReportResponse(EnrollReportRecord enrollRecord) {
        this.email = enrollRecord.getEmail();
        this.quantidadeMatriculas = enrollRecord.getQuantidadeMatriculas();
    }


}