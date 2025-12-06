import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int pos; // current position in the token list

    Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;
    }

    // Return the current token without consuming it
    Token currentToken() {
        // if  somehow go past the end, stay on last token
        if (pos >= tokens.size()) {
            return tokens.get(tokens.size() - 1);
        }
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

    // Entry point
    Node parse() throws ParseException {
        Node result = expression(); // start with lowest-precedence rule

        // Ensure all tokens are consumed
        if (currentToken().type != TokenType.EOF) {
            Token token = currentToken();
            throw new ParseException(
                "Parse error: unexpected token '" + token.value +
                "' at position " + token.pos
            );
        }
        return result;
    }

    // expression ::= term (('+' | '-') term)*
    private Node expression() throws ParseException {
        Node node = term();

        while (currentToken().type == TokenType.PLUS ||
               currentToken().type == TokenType.MINUS) {
            Token op = advance();          // '+' or '-'
            Node right = term();          // right operand
            node = Node.binaryOpNode(op.value, node, right);
        }
        return node;
    }

    // term ::= factor (('*' | '/') factor)*
    private Node term() throws ParseException {
        Node node = factor();

        while (currentToken().type == TokenType.MULTIPLY ||
               currentToken().type == TokenType.DIVIDE) {
            Token op = advance();          // '*' or '/'
            Node right = factor();        // right operand
            node = Node.binaryOpNode(op.value, node, right);
        }
        return node;
    }

    // factor ::= NUMBER | '(' expression ')' | '-' factor
    private Node factor() throws ParseException {
        Token token = currentToken();

        // number
        if (match(TokenType.NUMBER)) {
            return Node.createLeaf(token.value);
        }

        // ( expression )
        if (match(TokenType.LPAREN)) {
            Node node = expression();
            if (!match(TokenType.RPAREN)) {
                throw new ParseException(
                    "Parse error: expected ')' at position " + currentToken().pos
                );
            }
            return node;
        }

        // unary minus
        if (match(TokenType.MINUS)) {
            Node operand = factor();
            return Node.unaryOpNode("-", operand);
        }

        // anything else is a parse error
        throw new ParseException(
            "Parse error: unexpected token '" + token.value +
            "' at position " + token.pos
        );
    }
}
