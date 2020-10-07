// Generated from FractionParser.g4 by ANTLR 4.7.2
package software.bigbade.fractioncalculator.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FractionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		OPEN_PARENTHESES=1, CLOSED_PARENTHESES=2, NUMBER=3, WHITESPACE=4, NEWLINE=5, 
		ADDITION=6, SUBTRACTION=7, MULTIPLICATION=8, DIVISION=9, POWER=10;
	public static final int
		RULE_equation = 0, RULE_expression = 1, RULE_value = 2, RULE_parenthesis = 3, 
		RULE_operation = 4, RULE_addition = 5, RULE_subtraction = 6, RULE_division = 7, 
		RULE_multiplication = 8, RULE_power = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"equation", "expression", "value", "parenthesis", "operation", "addition", 
			"subtraction", "division", "multiplication", "power"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", null, "' '", null, "'+'", "'-'", "'*'", "'/'", "'^'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "OPEN_PARENTHESES", "CLOSED_PARENTHESES", "NUMBER", "WHITESPACE", 
			"NEWLINE", "ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION", "POWER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FractionParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FractionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class EquationContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(FractionParser.EOF, 0); }
		public List<TerminalNode> ADDITION() { return getTokens(FractionParser.ADDITION); }
		public TerminalNode ADDITION(int i) {
			return getToken(FractionParser.ADDITION, i);
		}
		public List<TerminalNode> SUBTRACTION() { return getTokens(FractionParser.SUBTRACTION); }
		public TerminalNode SUBTRACTION(int i) {
			return getToken(FractionParser.SUBTRACTION, i);
		}
		public List<TerminalNode> DIVISION() { return getTokens(FractionParser.DIVISION); }
		public TerminalNode DIVISION(int i) {
			return getToken(FractionParser.DIVISION, i);
		}
		public List<TerminalNode> MULTIPLICATION() { return getTokens(FractionParser.MULTIPLICATION); }
		public TerminalNode MULTIPLICATION(int i) {
			return getToken(FractionParser.MULTIPLICATION, i);
		}
		public List<TerminalNode> POWER() { return getTokens(FractionParser.POWER); }
		public TerminalNode POWER(int i) {
			return getToken(FractionParser.POWER, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public EquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitEquation(this);
		}
	}

	public final EquationContext equation() throws RecognitionException {
		EquationContext _localctx = new EquationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_equation);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(20);
					expression();
					}
					}
					setState(23); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OPEN_PARENTHESES || _la==NUMBER );
				setState(25);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADDITION) | (1L << SUBTRACTION) | (1L << MULTIPLICATION) | (1L << DIVISION) | (1L << POWER))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(27); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(26);
						expression();
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(29); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPEN_PARENTHESES || _la==NUMBER );
			setState(35);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(37);
				operation();
				}
				break;
			case 2:
				{
				setState(38);
				parenthesis();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(FractionParser.NUMBER, 0); }
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(41);
				match(NUMBER);
				}
				break;
			case OPEN_PARENTHESES:
				{
				setState(42);
				parenthesis();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesisContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESES() { return getToken(FractionParser.OPEN_PARENTHESES, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSED_PARENTHESES() { return getToken(FractionParser.CLOSED_PARENTHESES, 0); }
		public ParenthesisContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesis; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterParenthesis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitParenthesis(this);
		}
	}

	public final ParenthesisContext parenthesis() throws RecognitionException {
		ParenthesisContext _localctx = new ParenthesisContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_parenthesis);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(OPEN_PARENTHESES);
			setState(46);
			expression();
			setState(47);
			match(CLOSED_PARENTHESES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OperationContext extends ParserRuleContext {
		public AdditionContext addition() {
			return getRuleContext(AdditionContext.class,0);
		}
		public SubtractionContext subtraction() {
			return getRuleContext(SubtractionContext.class,0);
		}
		public MultiplicationContext multiplication() {
			return getRuleContext(MultiplicationContext.class,0);
		}
		public DivisionContext division() {
			return getRuleContext(DivisionContext.class,0);
		}
		public PowerContext power() {
			return getRuleContext(PowerContext.class,0);
		}
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitOperation(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(49);
				addition();
				}
				break;
			case 2:
				{
				setState(50);
				subtraction();
				}
				break;
			case 3:
				{
				setState(51);
				multiplication();
				}
				break;
			case 4:
				{
				setState(52);
				division();
				}
				break;
			case 5:
				{
				setState(53);
				power();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AdditionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode ADDITION() { return getToken(FractionParser.ADDITION, 0); }
		public TerminalNode NUMBER() { return getToken(FractionParser.NUMBER, 0); }
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public AdditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterAddition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitAddition(this);
		}
	}

	public final AdditionContext addition() throws RecognitionException {
		AdditionContext _localctx = new AdditionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_addition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			value();
			setState(57);
			match(ADDITION);
			setState(60);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(58);
				match(NUMBER);
				}
				break;
			case OPEN_PARENTHESES:
				{
				setState(59);
				parenthesis();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubtractionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode SUBTRACTION() { return getToken(FractionParser.SUBTRACTION, 0); }
		public TerminalNode NUMBER() { return getToken(FractionParser.NUMBER, 0); }
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public SubtractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtraction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterSubtraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitSubtraction(this);
		}
	}

	public final SubtractionContext subtraction() throws RecognitionException {
		SubtractionContext _localctx = new SubtractionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_subtraction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			value();
			setState(63);
			match(SUBTRACTION);
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(64);
				match(NUMBER);
				}
				break;
			case OPEN_PARENTHESES:
				{
				setState(65);
				parenthesis();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DivisionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode DIVISION() { return getToken(FractionParser.DIVISION, 0); }
		public TerminalNode NUMBER() { return getToken(FractionParser.NUMBER, 0); }
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public DivisionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_division; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterDivision(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitDivision(this);
		}
	}

	public final DivisionContext division() throws RecognitionException {
		DivisionContext _localctx = new DivisionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_division);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			value();
			setState(69);
			match(DIVISION);
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(70);
				match(NUMBER);
				}
				break;
			case OPEN_PARENTHESES:
				{
				setState(71);
				parenthesis();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultiplicationContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode MULTIPLICATION() { return getToken(FractionParser.MULTIPLICATION, 0); }
		public TerminalNode NUMBER() { return getToken(FractionParser.NUMBER, 0); }
		public ParenthesisContext parenthesis() {
			return getRuleContext(ParenthesisContext.class,0);
		}
		public MultiplicationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplication; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterMultiplication(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitMultiplication(this);
		}
	}

	public final MultiplicationContext multiplication() throws RecognitionException {
		MultiplicationContext _localctx = new MultiplicationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_multiplication);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(74);
				value();
				setState(75);
				match(MULTIPLICATION);
				setState(76);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(NUMBER);
				setState(79);
				parenthesis();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				parenthesis();
				setState(81);
				match(NUMBER);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PowerContext extends ParserRuleContext {
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode POWER() { return getToken(FractionParser.POWER, 0); }
		public PowerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_power; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).enterPower(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FractionParserListener ) ((FractionParserListener)listener).exitPower(this);
		}
	}

	public final PowerContext power() throws RecognitionException {
		PowerContext _localctx = new PowerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_power);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			value();
			setState(86);
			match(POWER);
			setState(87);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\\\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\6\2\30\n\2\r\2\16\2\31\3\2\3\2\6\2\36\n\2\r\2\16\2\37\6\2\"\n\2\r\2"+
		"\16\2#\3\2\3\2\3\3\3\3\5\3*\n\3\3\4\3\4\5\4.\n\4\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\5\69\n\6\3\7\3\7\3\7\3\7\5\7?\n\7\3\b\3\b\3\b\3\b\5\bE"+
		"\n\b\3\t\3\t\3\t\3\t\5\tK\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n"+
		"V\n\n\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\3\3\2\b"+
		"\f\2_\2!\3\2\2\2\4)\3\2\2\2\6-\3\2\2\2\b/\3\2\2\2\n8\3\2\2\2\f:\3\2\2"+
		"\2\16@\3\2\2\2\20F\3\2\2\2\22U\3\2\2\2\24W\3\2\2\2\26\30\5\4\3\2\27\26"+
		"\3\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32\33\3\2\2\2\33\35"+
		"\t\2\2\2\34\36\5\4\3\2\35\34\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3"+
		"\2\2\2 \"\3\2\2\2!\27\3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2\2\2"+
		"%&\7\2\2\3&\3\3\2\2\2\'*\5\n\6\2(*\5\b\5\2)\'\3\2\2\2)(\3\2\2\2*\5\3\2"+
		"\2\2+.\7\5\2\2,.\5\b\5\2-+\3\2\2\2-,\3\2\2\2.\7\3\2\2\2/\60\7\3\2\2\60"+
		"\61\5\4\3\2\61\62\7\4\2\2\62\t\3\2\2\2\639\5\f\7\2\649\5\16\b\2\659\5"+
		"\22\n\2\669\5\20\t\2\679\5\24\13\28\63\3\2\2\28\64\3\2\2\28\65\3\2\2\2"+
		"8\66\3\2\2\28\67\3\2\2\29\13\3\2\2\2:;\5\6\4\2;>\7\b\2\2<?\7\5\2\2=?\5"+
		"\b\5\2><\3\2\2\2>=\3\2\2\2?\r\3\2\2\2@A\5\6\4\2AD\7\t\2\2BE\7\5\2\2CE"+
		"\5\b\5\2DB\3\2\2\2DC\3\2\2\2E\17\3\2\2\2FG\5\6\4\2GJ\7\13\2\2HK\7\5\2"+
		"\2IK\5\b\5\2JH\3\2\2\2JI\3\2\2\2K\21\3\2\2\2LM\5\6\4\2MN\7\n\2\2NO\5\6"+
		"\4\2OV\3\2\2\2PQ\7\5\2\2QV\5\b\5\2RS\5\b\5\2ST\7\5\2\2TV\3\2\2\2UL\3\2"+
		"\2\2UP\3\2\2\2UR\3\2\2\2V\23\3\2\2\2WX\5\6\4\2XY\7\f\2\2YZ\5\6\4\2Z\25"+
		"\3\2\2\2\f\31\37#)-8>DJU";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}