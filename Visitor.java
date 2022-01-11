import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Visitor extends labBaseVisitor<Void> {
    public PrintStream ps = new PrintStream(new FileOutputStream(Main.args1));
    public String s = "";

    public Visitor() throws FileNotFoundException {
        System.setOut(ps);
    }

    @Override
    public Void visitCompUnit(labParser.CompUnitContext ctx) {
        System.out.print("define dso_local i32 @main() ");
        return super.visitCompUnit(ctx);
    }

    @Override
    public Void visitFuncDef(labParser.FuncDefContext ctx) {
        return super.visitFuncDef(ctx);
    }

    @Override
    public Void visitFuncType(labParser.FuncTypeContext ctx) {
        return super.visitFuncType(ctx);
    }

    @Override
    public Void visitIdent(labParser.IdentContext ctx) {
        return super.visitIdent(ctx);
    }

    @Override
    public Void visitBlock(labParser.BlockContext ctx) {
        System.out.println("{");
        visit(ctx.stmt());
        System.out.println("}");
        return null;
    }

    @Override
    public Void visitStmt(labParser.StmtContext ctx) {
        System.out.print("    ret ");
        visit(ctx.exp());
        System.out.println(s);
        return null;
    }

    @Override
    public Void visitExp(labParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    @Override
    public Void visitAddExp(labParser.AddExpContext ctx) {
        return super.visitAddExp(ctx);
    }

    @Override
    public Void visitMulExp(labParser.MulExpContext ctx) {
        return super.visitMulExp(ctx);
    }

    @Override
    public Void visitUnaryExp(labParser.UnaryExpContext ctx) {
        return super.visitUnaryExp(ctx);
    }

    @Override
    public Void visitPrimaryExp(labParser.PrimaryExpContext ctx) {
        return super.visitPrimaryExp(ctx);
    }

    @Override
    public Void visitUnaryOp(labParser.UnaryOpContext ctx) {
        return super.visitUnaryOp(ctx);
    }

    @Override
    public Void visitNumber(labParser.NumberContext ctx) {
        if (ctx.DecimalConst() != null) {
            s += ctx.getText();
        } else if (ctx.OctalConst() != null) {
            if (ctx.getText().equals("0")) {
                s += "0";
            } else {
                s += Integer.valueOf(ctx.getText().substring(1), 8).toString();
            }
        } else {
            s += Integer.valueOf(ctx.getText().substring(2), 16).toString();
        }
        return null;
    }
}
