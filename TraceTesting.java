import java.util.List;
import java.util.Scanner;

public class TraceTesting {
 public static void main(String[] args) {
       System.out.println("Mini Expression Compiler (Enter 'exit' to quit)");
        

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                Lexer lexer = new Lexer(line);
                List<Token> tokens = lexer.tokenize();

                Parser parser = new Parser(tokens);
                Node ast = parser.parse();

                Evaluator evaluator = new Evaluator();
                double result = evaluator.evaluate(ast);

                System.out.println("Result: " + result);
                
                // Optional: Print the AST
                System.out.println("AST:");
                Printer printer = new Printer();
                printer.print(ast, "");

            } catch (LexicalException | ParseException | EvaluationException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

