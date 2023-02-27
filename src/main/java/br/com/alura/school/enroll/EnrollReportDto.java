package br.com.alura.school.enroll;

public class EnrollReportDto {
    
    private Long quantidadeMatriculas;

    private String email;

    public EnrollReportDto(String email, Long quantidadeMatriculas) {
        this.quantidadeMatriculas = quantidadeMatriculas;
        this.email = email;
    }

    public Long getQuantidadeMatriculas() {
        return quantidadeMatriculas;
    }

    public void setQuantidadeMatriculas(Long quantidadeMatriculas) {
        this.quantidadeMatriculas = quantidadeMatriculas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
