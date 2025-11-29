public class Token {
    TokenType type;
    String value;
    int pos; // position in the input string

    //
    Token(TokenType type, String value, int pos) {
        this.type = type;
        this.value = value;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", pos=" + pos +
                '}';
    }   
}
