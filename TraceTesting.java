import java.util.List;
import java.util.Scanner;

public class TraceTesting {

    public static void main(String[] args) {
        System.out.println("Mini Expression Compiler (Enter 'exit' to quit)");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine();

            // allow user to quit
            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                // 1. Lexical analysis
                Lexer lexer = new Lexer(line);
                List<Token> tokens = lexer.tokenize();

                // print tokens in required format
                System.out.println("Tokens: " + tokens);

                // 2. Parsing
                Parser parser = new Parser(tokens);
                Node ast = parser.parse();

                // 3. Evaluation
                Evaluator evaluator = new Evaluator();
                double result = evaluator.evaluate(ast);

                // print evaluation result (cast to int to match handout)
                System.out.println("Evaluation Result: " + (int) result);

                // 4. Print the parse tree
                System.out.println("Parse Tree:");
                Printer printer = new Printer();
                printer.print(ast);

            } catch (LexicalException e) {
                // stop immediately if there is a lexical error
                System.err.println("Lexical Error: " + e.getMessage());
                continue;   // go to next loop iteration; do NOT parse/evaluate
            } catch (ParseException e) {
                // parsing phase errors
                System.err.println("Parse Error: " + e.getMessage());
            } catch (EvaluationException e) {
                // evaluation phase errors
                System.err.println("Evaluation Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
