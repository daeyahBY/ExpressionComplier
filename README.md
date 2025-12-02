# CSC220 – Expression Compiler Project
Fall 2025

This project is a small expression compiler that takes a math expression typed by the user, breaks it into pieces, builds a parse tree using operator precedence, and then evaluates the final result. It also prints out the tokens, the parse tree, and the final answer.

How the Program Works

### 1. Lexical Analysis (Tokenizing)
The `Lexer` class scans the input string and converts it into tokens such as:

- numbers (3, 8, 2, …)
- operators (`+`, `-`, `*`, `/`)
- parentheses (`(`, `)`)

It also detects and throws `LexicalException` for invalid characters.

---

### 2. Parsing (Building the AST)
The `Parser` takes the list of tokens and builds an **Abstract Syntax Tree (AST)** using:

- recursive descent parsing  
- correct operator precedence  
  - `*` and `/` before `+` and `-`  
- parentheses control grouping  
- unary negative is supported (ex: `-5`)

If the format is incorrect, it throws `ParseException`.

---

### 3. Evaluation
The `Evaluator` walks the AST and performs the calculations:

- addition
- subtraction
- multiplication
- division  
  - includes “division by zero” protection (throws `EvaluationException`)

---

### 4. Parse Tree Printing
The `Printer` class prints the AST using a diagonal text tree format:
### 5. Driver Program (TraceTesting.java)
This is the main file that:

1. reads user input  
2. calls the lexer  
3. prints the tokens  
4. builds the parse tree  
5. prints the tree  
6. evaluates the result  
7. handles exceptions cleanly  
8. loops until the user types `exit`  

## Project Files Summary

| File | Purpose |
|------|---------|
| `Token.java` | Defines tokens and text representation |
| `TokenType.java` | Enum listing NUMBER, PLUS, MINUS, MULTIPLY, DIVIDE, LPAREN, RPAREN, EOF |
| `Lexer.java` | Breaks input into tokens |
| `Parser.java` | Builds the AST using recursive descent |
| `Node.java` | AST node class |
| `Evaluator.java` | Computes the value of the AST |
| `Printer.java` | Prints the AST visually |
| `TraceTesting.java` | Main driver program |
| `LexicalException.java` | Error handling for invalid characters |
| `ParseException.java` | Error handling for invalid grammar |
| `EvaluationException.java` | Handles runtime errors |

## How to Compile & Run

In jGRASP:

1. Press **Build → Compile All**
2. Run **TraceTesting.java**
3. Type an expression  
4. To quit, type:

---

## Error Handling
The program detects:

- invalid characters  
- mismatched parentheses  
- missing operands or operators  
- division by zero  
- empty input  

All errors print clean messages.

---

## Author
Sanai Moore  &  Sadaeyah Baht-Yisrael
CSC220 – Fall 2025  
West Chester University  
