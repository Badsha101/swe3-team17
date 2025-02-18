package hbv.example.Module;


public class Vaccine {

    private Integer id;
    private String name;
    private String count;



    public Vaccine(Integer id, String name, String count) {
        this.id = id;
        this.name = name;
        this.count =count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
