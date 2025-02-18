package hbv.web.Module;

public class VaccinationCenters {
    private Integer id;
    private String name;
    private String strasse;
    private String stadt;
    private String postal;


public VaccinationCenters(){

}

public VaccinationCenters(Integer id, String name, String strasse, String stadt, String postal) {
this.id = id;
this.name = name;
this.strasse = strasse;
this.stadt = stadt;
this.postal = postal;

}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}
