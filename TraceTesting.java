import java.util.List;
import java.util.Scanner;

public class TraceTesting {
 
 public static void main(String[] args) {
       System.out.println("Mini Expression Compiler (Enter 'exit' to quit)");
        

        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print(">> ");
            String line = scanner.nextLine();
            
            //allow user to quit
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            

            try {
                  //Lexical analysis
                Lexer lexer = new Lexer(line);
                List<Token> tokens = lexer.tokenize();
                
                //printer tokens in required format
                System.out.println("Tokens: " + tokens);
                
                //Parsing
                Parser parser = new Parser(tokens);
                Node ast = parser.parse();
                
                // Evaluation
                Evaluator evaluator = new Evaluator();
                double result = evaluator.evaluate(ast);
                
                //cast to int
                System.out.println("Evaluation Result: " + (int) result);
                
                
                // Print the parse tree
                System.out.println("Parse Tree:");
                Printer printer = new Printer();
                printer.print(ast);

                          
                } catch (LexicalException | ParseException | EvaluationException e) {
                   System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

