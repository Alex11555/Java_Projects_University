import java.util.Date;

public class Stire {

    private SubiectStiri subiectStiri;

    private String titlu;

    private String continut;

    private Date data;

    public Stire(SubiectStiri subiectStiri, String titlu, String continut, Date data) {
        this.subiectStiri = subiectStiri;
        this.titlu = titlu;
        this.continut = continut;
        this.data = data;
    }

    public SubiectStiri getSubiectStiri() {
        return subiectStiri;
    }

    public void setSubiectStiri(SubiectStiri subiectStiri) {
        this.subiectStiri = subiectStiri;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
