package br.com.alura.school.enroll;

public class EnrollReportRecord {
    
    private Long quantidadeMatriculas;

    private String email;

    public EnrollReportRecord(String email, Long quantidadeMatriculas) {
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
