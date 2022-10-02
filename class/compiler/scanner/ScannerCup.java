/* The following code was generated by JFlex 1.4.3 on 2/10/22 16:31 */

package scanner;
import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 2/10/22 16:31 from the specification file
 * <tt>C:/Users/chuzd/Compiladores/class/compiler/scanner/LexerCup.flex</tt>
 */
public class ScannerCup implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = {
     0,  0,  0,  0,  0,  0,  0,  0,  0,  6,  8,  0,  0, 48,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
    13, 37, 47,  0,  0, 34, 38,  0, 41, 42, 33, 31,  6, 32,  0, 29, 
     4,  1,  1,  1,  1,  1,  1,  1,  1,  1,  0, 40, 35, 30, 36,  0, 
     0,  3,  3,  3,  3,  3,  3,  2,  2,  2,  2,  2,  2,  2,  2,  2, 
    14,  2,  2,  2,  2,  2,  2,  2,  5,  2,  2, 43,  7, 44,  0,  0, 
     0, 11, 24,  9, 21, 25, 26, 17,  2, 20,  2, 28, 10, 18, 22, 16, 
     2,  2, 15, 12, 23, 27, 19,  2,  5,  2,  2, 45, 39, 46,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 
     0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0
  };

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\1\2\1\3\1\2\1\4\10\3\1\5"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\3\1"+
    "\1\15\1\16\1\17\1\20\1\21\1\22\1\23\1\24"+
    "\1\2\1\3\1\2\1\4\1\25\10\3\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\3\24\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\26\1\27"+
    "\1\30\1\0\6\30\1\31\6\30\1\4\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\27\1\30"+
    "\1\24\1\42\1\43\1\44\1\45\6\30\1\31\6\30"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\46\5\30\1\47\5\30\1\50\1\46\5\30\1\47"+
    "\5\30\1\50\4\30\1\51\1\52\2\30\1\53\5\30"+
    "\1\51\1\52\2\30\1\53\5\30\1\54\1\30\1\55"+
    "\4\30\1\54\1\30\1\55\1\0\2\30\1\56\1\30"+
    "\1\24\2\30\1\56\1\30\1\0\1\57\1\30\1\60"+
    "\1\24\1\57\1\30\1\60\1\0\1\61\1\24\1\61"+
    "\1\0\1\24\1\0\1\24\1\0\1\24\1\0\1\24"+
    "\2\62";

  private static int [] zzUnpackAction() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\61\0\142\0\223\0\304\0\365\0\u0126\0\u0157"+
    "\0\u0188\0\u01b9\0\u01ea\0\u021b\0\u024c\0\u027d\0\u02ae\0\u02df"+
    "\0\u0310\0\u0341\0\u0372\0\142\0\142\0\u03a3\0\u03d4\0\u0405"+
    "\0\u0436\0\u0467\0\142\0\142\0\142\0\142\0\142\0\142"+
    "\0\142\0\u0498\0\u04c9\0\u04fa\0\u052b\0\u055c\0\u058d\0\u05be"+
    "\0\u05ef\0\u0620\0\u0651\0\u0682\0\u06b3\0\u06e4\0\u0715\0\u0746"+
    "\0\u0777\0\u07a8\0\u07d9\0\u0498\0\u0498\0\u080a\0\u083b\0\u086c"+
    "\0\u089d\0\u08ce\0\u0498\0\u0498\0\u0498\0\u0498\0\u0498\0\u0498"+
    "\0\u0498\0\142\0\223\0\304\0\u08ff\0\u0930\0\u0961\0\u0992"+
    "\0\u09c3\0\u09f4\0\u0a25\0\304\0\u0a56\0\u0a87\0\u0ab8\0\u0ae9"+
    "\0\u0b1a\0\u0b4b\0\u0b7c\0\142\0\142\0\142\0\142\0\142"+
    "\0\142\0\142\0\142\0\u04c9\0\u04fa\0\u0bad\0\142\0\142"+
    "\0\142\0\142\0\u0bde\0\u0c0f\0\u0c40\0\u0c71\0\u0ca2\0\u0cd3"+
    "\0\u04fa\0\u0d04\0\u0d35\0\u0d66\0\u0d97\0\u0dc8\0\u0df9\0\u0498"+
    "\0\u0498\0\u0498\0\u0498\0\u0498\0\u0498\0\u0498\0\u0498\0\u08ff"+
    "\0\u0e2a\0\u0e5b\0\u0e8c\0\u0ebd\0\u0eee\0\304\0\u0f1f\0\u0f50"+
    "\0\u0f81\0\u0fb2\0\u0fe3\0\304\0\u0bad\0\u1014\0\u1045\0\u1076"+
    "\0\u10a7\0\u10d8\0\u04fa\0\u1109\0\u113a\0\u116b\0\u119c\0\u11cd"+
    "\0\u04fa\0\u11fe\0\u122f\0\u1260\0\u1291\0\304\0\304\0\u12c2"+
    "\0\u12f3\0\304\0\u1324\0\u1355\0\u1386\0\u13b7\0\u13e8\0\u04fa"+
    "\0\u04fa\0\u1419\0\u144a\0\u04fa\0\u147b\0\u14ac\0\u14dd\0\u150e"+
    "\0\u153f\0\304\0\u1570\0\304\0\u15a1\0\u15d2\0\u1603\0\u1634"+
    "\0\u04fa\0\u1665\0\u04fa\0\u1696\0\u16c7\0\u16f8\0\304\0\u1729"+
    "\0\u175a\0\u178b\0\u17bc\0\u04fa\0\u17ed\0\u181e\0\304\0\u184f"+
    "\0\304\0\u1880\0\u04fa\0\u18b1\0\u04fa\0\u18e2\0\304\0\u1913"+
    "\0\u04fa\0\u1944\0\u1975\0\u19a6\0\u19d7\0\u1a08\0\u1a39\0\u1a6a"+
    "\0\u1a9b\0\142\0\u0498";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\5\1\6\1\5\1\7\1\3\1\7"+
    "\1\10\3\5\1\7\1\5\1\11\3\5\1\12\1\13"+
    "\2\5\1\14\1\15\1\16\1\17\2\5\1\20\1\21"+
    "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\41"+
    "\1\3\1\7\1\42\1\43\2\44\1\45\1\44\1\46"+
    "\1\47\1\7\1\50\3\44\1\46\1\44\1\51\3\44"+
    "\1\52\1\53\2\44\1\54\1\55\1\56\1\57\2\44"+
    "\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67"+
    "\1\70\1\71\1\72\1\73\1\74\1\75\1\76\1\77"+
    "\1\100\1\101\1\102\1\7\62\0\1\103\2\0\1\103"+
    "\55\0\5\104\3\0\4\104\1\0\17\104\25\0\1\103"+
    "\2\0\1\103\1\105\61\0\1\7\1\0\1\7\4\0"+
    "\1\7\42\0\1\7\1\0\5\104\3\0\1\104\1\106"+
    "\1\107\1\104\1\0\2\104\1\110\14\104\25\0\5\104"+
    "\3\0\4\104\1\0\13\104\1\111\3\104\25\0\5\104"+
    "\3\0\4\104\1\0\2\104\1\112\14\104\25\0\5\104"+
    "\3\0\4\104\1\0\10\104\1\113\3\104\1\114\2\104"+
    "\25\0\5\104\3\0\4\104\1\0\1\104\1\115\15\104"+
    "\25\0\5\104\3\0\4\104\1\0\1\104\1\116\1\117"+
    "\14\104\25\0\5\104\3\0\1\104\1\120\2\104\1\0"+
    "\17\104\25\0\5\104\3\0\2\104\1\121\1\104\1\0"+
    "\2\104\1\122\14\104\61\0\1\123\61\0\1\124\60\0"+
    "\1\125\60\0\1\126\60\0\1\127\60\0\1\130\60\0"+
    "\1\131\70\0\1\132\61\0\1\133\11\0\7\42\2\0"+
    "\46\42\2\0\1\42\1\134\2\42\1\134\2\42\2\0"+
    "\46\42\2\0\1\42\5\135\1\42\2\0\4\135\1\42"+
    "\17\135\22\42\2\0\1\42\1\134\2\42\1\134\1\136"+
    "\1\42\2\0\46\42\2\0\6\42\1\46\1\0\1\7"+
    "\4\42\1\46\41\42\1\0\1\7\17\0\1\137\6\0"+
    "\1\140\1\141\27\0\1\142\1\0\1\42\5\135\1\42"+
    "\2\0\1\135\1\143\1\144\1\135\1\42\2\135\1\145"+
    "\14\135\22\42\2\0\1\42\5\135\1\42\2\0\4\135"+
    "\1\42\13\135\1\146\3\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\4\135\1\42\2\135\1\147\14\135\22\42"+
    "\2\0\1\42\5\135\1\42\2\0\4\135\1\42\10\135"+
    "\1\150\3\135\1\151\2\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\4\135\1\42\1\135\1\152\15\135\22\42"+
    "\2\0\1\42\5\135\1\42\2\0\4\135\1\42\1\135"+
    "\1\153\1\154\14\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\1\135\1\155\2\135\1\42\17\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\2\135\1\156\1\135\1\42"+
    "\2\135\1\157\14\135\22\42\2\0\7\42\2\0\24\42"+
    "\1\123\21\42\2\0\7\42\2\0\25\42\1\160\20\42"+
    "\2\0\7\42\2\0\25\42\1\161\20\42\2\0\7\42"+
    "\2\0\25\42\1\162\20\42\2\0\7\42\2\0\25\42"+
    "\1\163\20\42\2\0\7\42\2\0\25\42\1\164\20\42"+
    "\2\0\7\42\2\0\25\42\1\165\20\42\2\0\7\42"+
    "\2\0\35\42\1\166\10\42\2\0\7\42\2\0\36\42"+
    "\1\167\7\42\3\0\1\170\1\0\2\170\4\0\1\170"+
    "\1\0\1\170\11\0\1\170\2\0\3\170\27\0\5\104"+
    "\3\0\2\104\1\171\1\104\1\0\17\104\25\0\5\104"+
    "\3\0\1\104\1\172\2\104\1\0\17\104\25\0\5\104"+
    "\3\0\4\104\1\0\10\104\1\173\6\104\25\0\5\104"+
    "\3\0\4\104\1\0\11\104\1\174\5\104\25\0\5\104"+
    "\3\0\4\104\1\0\6\104\1\175\10\104\25\0\5\104"+
    "\3\0\4\104\1\0\11\104\1\176\5\104\25\0\5\104"+
    "\3\0\4\104\1\0\15\104\1\177\1\104\25\0\5\104"+
    "\3\0\4\104\1\0\13\104\1\200\3\104\25\0\5\104"+
    "\3\0\4\104\1\0\2\104\1\201\14\104\25\0\5\104"+
    "\3\0\3\104\1\202\1\0\17\104\25\0\5\104\3\0"+
    "\1\104\1\203\2\104\1\0\17\104\25\0\5\104\3\0"+
    "\4\104\1\0\1\104\1\204\15\104\24\0\10\123\1\0"+
    "\50\123\1\42\1\205\1\42\2\205\2\42\2\0\1\205"+
    "\1\42\1\205\11\42\1\205\2\42\3\205\24\42\2\0"+
    "\1\42\5\135\1\42\2\0\2\135\1\206\1\135\1\42"+
    "\17\135\22\42\2\0\1\42\5\135\1\42\2\0\1\135"+
    "\1\207\2\135\1\42\17\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\4\135\1\42\10\135\1\210\6\135\22\42"+
    "\2\0\1\42\5\135\1\42\2\0\4\135\1\42\11\135"+
    "\1\211\5\135\22\42\2\0\1\42\5\135\1\42\2\0"+
    "\4\135\1\42\6\135\1\212\10\135\22\42\2\0\1\42"+
    "\5\135\1\42\2\0\4\135\1\42\11\135\1\213\5\135"+
    "\22\42\2\0\1\42\5\135\1\42\2\0\4\135\1\42"+
    "\15\135\1\214\1\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\4\135\1\42\13\135\1\215\3\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\2\135\1\216"+
    "\14\135\22\42\2\0\1\42\5\135\1\42\2\0\3\135"+
    "\1\217\1\42\17\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\1\135\1\220\2\135\1\42\17\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\1\135\1\221"+
    "\15\135\22\42\3\0\5\104\3\0\3\104\1\222\1\0"+
    "\17\104\25\0\5\104\3\0\1\104\1\223\2\104\1\0"+
    "\17\104\25\0\5\104\3\0\4\104\1\0\11\104\1\224"+
    "\5\104\25\0\5\104\3\0\4\104\1\0\15\104\1\225"+
    "\1\104\25\0\5\104\3\0\4\104\1\0\7\104\1\226"+
    "\7\104\25\0\5\104\3\0\4\104\1\0\13\104\1\227"+
    "\3\104\25\0\5\104\3\0\2\104\1\230\1\104\1\0"+
    "\17\104\25\0\5\104\3\0\1\104\1\231\2\104\1\0"+
    "\17\104\25\0\5\104\3\0\4\104\1\0\13\104\1\232"+
    "\3\104\25\0\5\104\3\0\3\104\1\233\1\0\17\104"+
    "\24\0\1\42\5\135\1\42\2\0\3\135\1\234\1\42"+
    "\17\135\22\42\2\0\1\42\5\135\1\42\2\0\1\135"+
    "\1\235\2\135\1\42\17\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\4\135\1\42\11\135\1\236\5\135\22\42"+
    "\2\0\1\42\5\135\1\42\2\0\4\135\1\42\15\135"+
    "\1\237\1\135\22\42\2\0\1\42\5\135\1\42\2\0"+
    "\4\135\1\42\7\135\1\240\7\135\22\42\2\0\1\42"+
    "\5\135\1\42\2\0\4\135\1\42\13\135\1\241\3\135"+
    "\22\42\2\0\1\42\5\135\1\42\2\0\2\135\1\242"+
    "\1\135\1\42\17\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\1\135\1\243\2\135\1\42\17\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\13\135\1\244"+
    "\3\135\22\42\2\0\1\42\5\135\1\42\2\0\3\135"+
    "\1\245\1\42\17\135\22\42\3\0\5\104\3\0\3\104"+
    "\1\246\1\0\17\104\25\0\5\104\3\0\4\104\1\0"+
    "\2\104\1\247\14\104\25\0\5\104\3\0\4\104\1\0"+
    "\6\104\1\250\10\104\25\0\5\104\3\0\4\104\1\0"+
    "\1\104\1\251\15\104\25\0\5\104\3\0\4\104\1\0"+
    "\16\104\1\252\25\0\5\104\3\0\4\104\1\0\13\104"+
    "\1\253\3\104\25\0\5\104\3\0\4\104\1\0\13\104"+
    "\1\254\3\104\24\0\1\42\5\135\1\42\2\0\3\135"+
    "\1\255\1\42\17\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\4\135\1\42\2\135\1\256\14\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\6\135\1\257"+
    "\10\135\22\42\2\0\1\42\5\135\1\42\2\0\4\135"+
    "\1\42\1\135\1\260\15\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\4\135\1\42\16\135\1\261\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\13\135\1\262"+
    "\3\135\22\42\2\0\1\42\5\135\1\42\2\0\4\135"+
    "\1\42\13\135\1\263\3\135\22\42\3\0\5\104\3\0"+
    "\4\104\1\264\17\104\25\0\5\104\3\0\4\104\1\0"+
    "\15\104\1\265\1\104\25\0\5\104\3\0\4\104\1\0"+
    "\10\104\1\266\6\104\25\0\5\104\3\0\4\104\1\0"+
    "\10\104\1\267\6\104\25\0\5\104\3\0\2\104\1\270"+
    "\1\104\1\0\17\104\24\0\1\42\5\135\1\42\2\0"+
    "\4\135\1\271\17\135\22\42\2\0\1\42\5\135\1\42"+
    "\2\0\4\135\1\42\15\135\1\272\1\135\22\42\2\0"+
    "\1\42\5\135\1\42\2\0\4\135\1\42\10\135\1\273"+
    "\6\135\22\42\2\0\1\42\5\135\1\42\2\0\4\135"+
    "\1\42\10\135\1\274\6\135\22\42\2\0\1\42\5\135"+
    "\1\42\2\0\2\135\1\275\1\135\1\42\17\135\22\42"+
    "\20\0\1\276\43\0\5\104\3\0\4\104\1\0\11\104"+
    "\1\277\5\104\25\0\5\104\3\0\4\104\1\0\15\104"+
    "\1\300\1\104\25\0\5\104\3\0\4\104\1\0\10\104"+
    "\1\301\6\104\24\0\7\42\2\0\5\42\1\302\40\42"+
    "\2\0\1\42\5\135\1\42\2\0\4\135\1\42\11\135"+
    "\1\303\5\135\22\42\2\0\1\42\5\135\1\42\2\0"+
    "\4\135\1\42\15\135\1\304\1\135\22\42\2\0\1\42"+
    "\5\135\1\42\2\0\4\135\1\42\10\135\1\305\6\135"+
    "\22\42\21\0\1\306\42\0\5\104\3\0\4\104\1\0"+
    "\13\104\1\307\3\104\24\0\7\42\2\0\6\42\1\310"+
    "\37\42\2\0\1\42\5\135\1\42\2\0\4\135\1\42"+
    "\13\135\1\311\3\135\22\42\22\0\1\312\40\0\7\42"+
    "\2\0\7\42\1\313\36\42\23\0\1\314\37\0\7\42"+
    "\2\0\10\42\1\315\35\42\21\0\1\316\41\0\7\42"+
    "\2\0\6\42\1\317\37\42\15\0\1\320\45\0\7\42"+
    "\2\0\2\42\1\321\43\42\24\0\1\322\36\0\7\42"+
    "\2\0\11\42\1\323\34\42\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[6860];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\20\1\2\11\5\1\7\11\40\1\1\11"+
    "\2\1\1\0\16\1\10\11\3\1\4\11\121\1\1\0"+
    "\11\1\1\0\7\1\1\0\3\1\1\0\1\1\1\0"+
    "\1\1\1\0\1\1\1\0\1\1\1\11\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[211];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public ScannerCup(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public ScannerCup(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 12: 
          { return new Symbol(sym.GreaterThan, yychar, yyline, yytext());
          }
        case 51: break;
        case 26: 
          { return new Symbol(sym.Equal, yychar, yyline, yytext());
          }
        case 52: break;
        case 41: 
          { return new Symbol(sym.Void, yychar, yyline, yytext());
          }
        case 53: break;
        case 39: 
          { return new Symbol(sym.Int, yychar, yyline, yytext());
          }
        case 54: break;
        case 11: 
          { return new Symbol(sym.LessThan, yychar, yyline, yytext());
          }
        case 55: break;
        case 17: 
          { return new Symbol(sym.RightBracket, yychar, yyline, yytext());
          }
        case 56: break;
        case 44: 
          { return new Symbol(sym.Break, yychar, yyline, yytext());
          }
        case 57: break;
        case 43: 
          { return new Symbol(sym.Else, yychar, yyline, yytext());
          }
        case 58: break;
        case 2: 
          { return new Symbol(sym.Digit, yychar, yyline, yytext());
          }
        case 59: break;
        case 14: 
          { return new Symbol(sym.LeftParent, yychar, yyline, yytext());
          }
        case 60: break;
        case 24: 
          { return new Symbol(sym.Id, yychar, yyline, yytext());
          }
        case 61: break;
        case 5: 
          { return new Symbol(sym.Division, yychar, yyline, yytext());
          }
        case 62: break;
        case 27: 
          { return new Symbol(sym.AsignSum, yychar, yyline, yytext());
          }
        case 63: break;
        case 10: 
          { return new Symbol(sym.Percentage, yychar, yyline, yytext());
          }
        case 64: break;
        case 18: 
          { return new Symbol(sym.LeftKey, yychar, yyline, yytext());
          }
        case 65: break;
        case 48: 
          { return new Symbol(sym.Boolean, yychar, yyline, yytext());
          }
        case 66: break;
        case 8: 
          { return new Symbol(sym.Substract, yychar, yyline, yytext());
          }
        case 67: break;
        case 15: 
          { return new Symbol(sym.RightParent, yychar, yyline, yytext());
          }
        case 68: break;
        case 40: 
          { return new Symbol(sym.For, yychar, yyline, yytext());
          }
        case 69: break;
        case 13: 
          { return new Symbol(sym.SemiColom, yychar, yyline, yytext());
          }
        case 70: break;
        case 49: 
          { return new Symbol(sym.Continue, yychar, yyline, yytext());
          }
        case 71: break;
        case 7: 
          { return new Symbol(sym.Sum, yychar, yyline, yytext());
          }
        case 72: break;
        case 28: 
          { return new Symbol(sym.AsignSubs, yychar, yyline, yytext());
          }
        case 73: break;
        case 29: 
          { return new Symbol(sym.LessEqualThan, yychar, yyline, yytext());
          }
        case 74: break;
        case 19: 
          { return new Symbol(sym.RightKey, yychar, yyline, yytext());
          }
        case 75: break;
        case 31: 
          { return new Symbol(sym.NotEqual, yychar, yyline, yytext());
          }
        case 76: break;
        case 21: 
          { string.append(’\\’);
          }
        case 77: break;
        case 37: 
          { string.append("\"");
          }
        case 78: break;
        case 38: 
          { return new Symbol(sym.HexLiteral, yychar, yyline, yytext());
          }
        case 79: break;
        case 46: 
          { return new Symbol(sym.Return, yychar, yyline, yytext());
          }
        case 80: break;
        case 23: 
          { return new Symbol(sym.DecimalLiteral, yychar, yyline, yytext());
          }
        case 81: break;
        case 25: 
          { return new Symbol(sym.If, yychar, yyline, yytext());
          }
        case 82: break;
        case 32: 
          { return new Symbol(sym.And, yychar, yyline, yytext());
          }
        case 83: break;
        case 6: 
          { return new Symbol(sym.Asign, yychar, yyline, yytext());
          }
        case 84: break;
        case 50: 
          { return new Symbol(sym.ClassProgram, yychar, yyline, yytext());
          }
        case 85: break;
        case 47: 
          { return new Symbol(sym.Callout, yychar, yyline, yytext());
          }
        case 86: break;
        case 35: 
          { string.append(’\n’);
          }
        case 87: break;
        case 30: 
          { return new Symbol(sym.GreaterEqualThan, yychar, yyline, yytext());
          }
        case 88: break;
        case 34: 
          { string.append(’\r’);
          }
        case 89: break;
        case 1: 
          { return new Symbol(sym.ERROR, yychar, yyline, yytext());
          }
        case 90: break;
        case 36: 
          { string.append(’\t’);
          }
        case 91: break;
        case 9: 
          { return new Symbol(sym.Multiplication, yychar, yyline, yytext());
          }
        case 92: break;
        case 45: 
          { return new Symbol(sym.False, yychar, yyline, yytext());
          }
        case 93: break;
        case 33: 
          { return new Symbol(sym.Or, yychar, yyline, yytext());
          }
        case 94: break;
        case 4: 
          { /*Ignore*/
          }
        case 95: break;
        case 42: 
          { return new Symbol(sym.True, yychar, yyline, yytext());
          }
        case 96: break;
        case 20: 
          { string.append( yytext() );
          }
        case 97: break;
        case 16: 
          { return new Symbol(sym.LeftBracket, yychar, yyline, yytext());
          }
        case 98: break;
        case 22: 
          { yybegin(YYINITIAL); 
                     return new Symbol(sym.StringLiteral, yychar, yyline, string.toString();)
          }
        case 99: break;
        case 3: 
          { return new Symbol(sym.Alpha, yychar, yyline, yytext());
          }
        case 100: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
