package pojo;

public class TriangleResponseBody {
    private String id;
    private String firstSide;
    private String secondSide;
    private String thirdSide;

    public TriangleResponseBody() {
    }

    public TriangleResponseBody(String id, String firstSide, String secondSide, String thirdSide) {
        this.id = id;
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstSide() {
        return firstSide;
    }

    public void setFirstSide(String firstSide) {
        this.firstSide = firstSide;
    }

    public String getSecondSide() {
        return secondSide;
    }

    public void setSecondSide(String secondSide) {
        this.secondSide = secondSide;
    }

    public String getThirdSide() {
        return thirdSide;
    }

    public void setThirdSide(String thirdSide) {
        this.thirdSide = thirdSide;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\nfirstSide: " + firstSide +
                "\nsecondSide: " + secondSide +
                "\nthirdSide='" + thirdSide;
    }
}
