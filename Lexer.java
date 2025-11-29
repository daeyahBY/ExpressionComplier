import java.util.*;

public class Lexer {
    private final String text;
    private final int length;
    private int i; // current position in the input string

    Lexer(String text) {
        this.text = text;
        this.length = text.length();
        this.i = 0;
    }

    List<Token> tokenize() throws LexicalException{
        List<Token> tokens = new ArrayList<>();
        //
        while(true) {
            skipWhitespace(); // ignore whitespace

            if (i >= length) { // End of input
                tokens.add(new Token(TokenType.EOF, "", i));
                break;
            }

            // Check current character
            char ch = text.charAt(i); 

        
            if (isDigit(ch)) { // number
                String number = readNumber();
                tokens.add(new Token(TokenType.NUMBER, number, i - number.length()));
            } 
            
            // Operators and parentheses
            else if (ch == '+') {
                tokens.add(new Token(TokenType.PLUS, "+", i));
                i++;
            } else if (ch == '-') {
                tokens.add(new Token(TokenType.MINUS, "-", i));
                i++;
            } else if (ch == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*", i));
                i++;
            } else if (ch == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/", i));
                i++;
            } else if (ch == '(') {
                tokens.add(new Token(TokenType.LPAREN, "(", i));
                i++;
            } else if (ch == ')') {
                tokens.add(new Token(TokenType.RPAREN, ")", i));
                i++;
            } else { // Unknown character
                throw new LexicalException("Unexpected character: " + ch + " at position " + i);
            }
        }
        return tokens;
    }
        

    
    private void skipWhitespace() { //
        while (i < length && Character.isWhitespace(text.charAt(i))) {
            i++;
        }
    }   

    private boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';  
    }
    
    // Read a number (integer or decimal)   
    private String readNumber() throws LexicalException {
        int start = i;
        boolean hasDot = false;

        while (i < length) {
            char ch = text.charAt(i);
            if (isDigit(ch)) {
                i++; // continue reading digits
            } else if (ch == '.' && !hasDot) {
                hasDot = true; // allow one dot for decimal point
                i++;
            } else {
                break;
            }
        }
        return text.substring(start, i);
    }
}
