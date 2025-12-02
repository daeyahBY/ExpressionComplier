public class Token {
   
    //What kind of token
    TokenType type;
    
    //Actual text of the token from input
    String value;
    
    int pos; // position in the input string

    //constructor 
    public Token(TokenType type, String value, int pos) {
        this.type = type;
        this.value = value;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return value;
        }
}
