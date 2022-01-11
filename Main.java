import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static Error error = new Error();
    public static String args1 = "";

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(args[0]);
        args1 = args[1];

        char[] str = new char[1000];
        int n = 0;
        StringBuilder s = new StringBuilder();
        while ((n = fr.read(str, 0, 1000)) != -1) {
            s.append(new String(str, 0, n));
        }

        CharStream inputStream = CharStreams.fromString(s.toString());
        labLexer lexer = new labLexer(inputStream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(error);

        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        labParser parser = new labParser(tokenStream);
        parser.removeErrorListeners();
        parser.addErrorListener(error);

        ParseTree tree = parser.compUnit(); // 获取语法树的根节点
        Visitor visitor = new Visitor();
        visitor.visit(tree);

        fr.close();
    }
}
