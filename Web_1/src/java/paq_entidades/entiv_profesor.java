package paq_entidades;

public class entiv_profesor extends entiv_persona {

    public String pr_tipo = "";
    public String pr_contrato = "";

    public String getPr_tipo() {
        return pr_tipo;
    }

    public void setPr_tipo(String pr_tipo) {
        this.pr_tipo = pr_tipo;
    }

    public String getPr_contrato() {
        return pr_contrato;
    }

    public void setPr_contrato(String pr_contrato) {
        this.pr_contrato = pr_contrato;
    }
}
