package shcher.test5;


public class RecyclerItem {
    private String title;
    private String description;
    private Integer number;

    public RecyclerItem(String title, String description, Integer number) {
        this.title = title;
        this.description = description;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}

