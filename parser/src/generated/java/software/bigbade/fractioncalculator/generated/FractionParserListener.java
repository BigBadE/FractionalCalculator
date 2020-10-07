// Generated from FractionParser.g4 by ANTLR 4.7.2
package software.bigbade.fractioncalculator.generated;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FractionParser}.
 */
public interface FractionParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FractionParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(FractionParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(FractionParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FractionParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FractionParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(FractionParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(FractionParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(FractionParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(FractionParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(FractionParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(FractionParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#addition}.
	 * @param ctx the parse tree
	 */
	void enterAddition(FractionParser.AdditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#addition}.
	 * @param ctx the parse tree
	 */
	void exitAddition(FractionParser.AdditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#subtraction}.
	 * @param ctx the parse tree
	 */
	void enterSubtraction(FractionParser.SubtractionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#subtraction}.
	 * @param ctx the parse tree
	 */
	void exitSubtraction(FractionParser.SubtractionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#division}.
	 * @param ctx the parse tree
	 */
	void enterDivision(FractionParser.DivisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#division}.
	 * @param ctx the parse tree
	 */
	void exitDivision(FractionParser.DivisionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void enterMultiplication(FractionParser.MultiplicationContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#multiplication}.
	 * @param ctx the parse tree
	 */
	void exitMultiplication(FractionParser.MultiplicationContext ctx);
	/**
	 * Enter a parse tree produced by {@link FractionParser#power}.
	 * @param ctx the parse tree
	 */
	void enterPower(FractionParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by {@link FractionParser#power}.
	 * @param ctx the parse tree
	 */
	void exitPower(FractionParser.PowerContext ctx);
}