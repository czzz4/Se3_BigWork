package vo;

public class IndustryVO {

    private String name;
    private int rank;

    public IndustryVO(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public String getName() {
        return this.name;
    }

    public int getRank() {
        return this.rank;
    }
}
