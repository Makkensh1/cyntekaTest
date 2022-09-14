public class NonExistentWordException extends IllegalArgumentException{
    private  String word;

    public NonExistentWordException(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "NonExistentWordException{" +
                "word='" + word + '\'' +
                '}';
    }
}
