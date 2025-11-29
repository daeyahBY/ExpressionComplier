import java.util.List;

public class Parser {
   private final List<Token> tokens;
    private int pos; // current position in the token list

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    // Return the current token without consuming it
    Token currentToken() {
        return tokens.get(pos);
    }

    // Consume and return the current token
    Token advance() {
        return tokens.get(pos++);
    }

    // Check if the current token matches the expected type
    boolean match(TokenType type) {
        if (currentToken().type == type) {
            advance();
            return true;
        }
        return false;
    }

    // Recursive descent parsing methods
    Node parse() throws ParseException {
        Node result = expression(); // start parsing from the highest precedence
        // Ensure all tokens are consumed
        if (currentToken().type != TokenType.EOF) {
            Token token = currentToken();
            throw new ParseException("Unexpected token: " + currentToken().value + " at position " + currentToken().pos);
        }
        return result;
    }

    // expression ::= term (('+' | '-') term)*
    private Node expression() throws ParseException {
        Node node = term();
        // Handle + and - operators
        while (currentToken().type == TokenType.PLUS || currentToken().type == TokenType.MINUS) {
            Token token = advance(); // consume operator
            Node right = term(); // parse right operand
            node = Node.binaryOpNode(token.value, node, right);
        }
        return node;
    }   

    // term ::= factor (('*' | '/') factor)*
    private Node term() throws ParseException {
        Node node = factor();
        // Handle * and / operators
        while (currentToken().type == TokenType.MULTIPLY || currentToken().type == TokenType.DIVIDE) {
            Token token = advance();
            Node right = factor();
            node = Node.binaryOpNode(token.value, node, right);
        }
        return node;
    }

    // factor ::= NUMBER | '(' expression ')' | '-' factor
    private Node factor() throws ParseException {
        Token token = currentToken();
        if (match(TokenType.NUMBER)) {
            return Node.createLeaf(token.value);
        
        } else if (match(TokenType.LPAREN)) {
            Node node = expression();
            if (!match(TokenType.RPAREN)) {
                throw new ParseException("Expected ')' at position " + currentToken().pos);
            }
            return node;
        } else if (match(TokenType.MINUS)) {
            Node operand = factor();
            return Node.unaryOpNode("-", operand);
        }
        throw new ParseException("Unexpected token: " + token.value + " at position " + token.pos);
    }
}
