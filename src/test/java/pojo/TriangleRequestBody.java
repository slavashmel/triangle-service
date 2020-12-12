package pojo;

public class TriangleRequestBody {
    private String separator;
    private String input;

    public TriangleRequestBody() {
    }

    public TriangleRequestBody(String separator, String input) {
        this.separator = separator;
        this.input = input;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return "{\"separator\": \"" + separator + "\", \"input\": \"" + input + "\"}";
    }
}
