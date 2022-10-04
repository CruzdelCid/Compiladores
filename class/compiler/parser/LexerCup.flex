package parser;
import java_cup.runtime.Symbol;

%%
%class ScannerCup
%type java_cup.runtime.Symbol
%public
%cup
%full
%line
%char
%state STRING

digit = [0-9]
alpha = [a-zA-Z]
id = {alpha}({alpha}|{digit})*
hex_digit = [0-9a-fA-F]
hex_literal = 0[xX]{hex_digit}({hex_digit})*
decimal_literal =  {digit}({digit})*
espacio = [ \t\r\n]+
// char _ literal requerido  

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}

%%

/* palabras reservadas*/
class               {return new Symbol(sym.Class, yychar, yyline, yytext());}      
Program             {return new Symbol(sym.Program, yychar, yyline, yytext());}      
void                {return new Symbol(sym.Void, yychar, yyline, yytext());}      
int                 {return new Symbol(sym.Int, yychar, yyline, yytext());}      
boolean             {return new Symbol(sym.Boolean, yychar, yyline, yytext());}
if                  {return new Symbol(sym.If, yychar, yyline, yytext());}      
else                {return new Symbol(sym.Else, yychar, yyline, yytext());}      
for                 {return new Symbol(sym.For, yychar, yyline, yytext());}      
return              {return new Symbol(sym.Return, yychar, yyline, yytext());}      
break               {return new Symbol(sym.Break, yychar, yyline, yytext());}      
continue            {return new Symbol(sym.Continue, yychar, yyline, yytext());}      
callout             {return new Symbol(sym.Callout, yychar, yyline, yytext());}      
true                {return new Symbol(sym.True, yychar, yyline, yytext());}      
false               {return new Symbol(sym.False, yychar, yyline, yytext());}
string              {return new Symbol(sym.String, yychar, yyline, yytext());}      

/* Espacios */

{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}

/* indentificadores*/
/* {digit}             {return new Symbol(sym.Digit, yychar, yyline, yytext());} 
{alpha}             {return new Symbol(sym.Alpha, yychar, yyline, yytext());} */
{id}                {return new Symbol(sym.Id, yychar, yyline, yytext());} 
{hex_digit}         {return new Symbol(sym.HexDigit, yychar, yyline, yytext());} 
{hex_literal}       {return new Symbol(sym.HexLiteral, yychar, yyline, yytext());} 
{decimal_literal}   {return new Symbol(sym.DecimalLiteral, yychar, yyline, yytext());} 

/* comparadores y operadores*/
","                 {return new Symbol(sym.Comma, yychar, yyline, yytext());} 
"="                 {return new Symbol(sym.Asign, yychar, yyline, yytext());}  
"+="                {return new Symbol(sym.AsignAdd, yychar, yyline, yytext());}  
"-="                {return new Symbol(sym.AsignSubs, yychar, yyline, yytext());}  
"+"                 {return new Symbol(sym.Add, yychar, yyline, yytext());}  
"-"                 {return new Symbol(sym.Substract, yychar, yyline, yytext());}  
"*"                 {return new Symbol(sym.Multiplication, yychar, yyline, yytext());}  
"/"                 {return new Symbol(sym.Division, yychar, yyline, yytext());}  
"%"                 {return new Symbol(sym.Mod, yychar, yyline, yytext());}  
"<"                 {return new Symbol(sym.LessThan, yychar, yyline, yytext());}  
">"                 {return new Symbol(sym.GreaterThan, yychar, yyline, yytext());}  
"<="                {return new Symbol(sym.LessEqualThan, yychar, yyline, yytext());}  
">="                {return new Symbol(sym.GreaterEqualThan, yychar, yyline, yytext());}  
"=="                {return new Symbol(sym.Equal, yychar, yyline, yytext());}  
"!="                {return new Symbol(sym.NotEqual, yychar, yyline, yytext());}  
"&&"                {return new Symbol(sym.And, yychar, yyline, yytext());}  
"||"                {return new Symbol(sym.Or, yychar, yyline, yytext());}  
";"                 {return new Symbol(sym.SemiColom, yychar, yyline, yytext());}
"!"                 {return new Symbol(sym.Exclam, yychar, yyline, yytext());}


/* agrupaciones*/
"("                 {return new Symbol(sym.LeftParent, yychar, yyline, yytext());} 
")"                 {return new Symbol(sym.RightParent, yychar, yyline, yytext());} 
"["                 {return new Symbol(sym.LeftBracket, yychar, yyline, yytext());} 
"]"                 {return new Symbol(sym.RightBracket, yychar, yyline, yytext());} 
"{"                 {return new Symbol(sym.LeftKey, yychar, yyline, yytext());} 
"}"                 {return new Symbol(sym.RightKey, yychar, yyline, yytext());} 
"()"                {return new Symbol(sym.ParentOpenClose, yychar, yyline, yytext());} 
"[]"                {return new Symbol(sym.BracketOpenClose, yychar, yyline, yytext());} 
"{}"                {return new Symbol(sym.KeyOpenClose, yychar, yyline, yytext());} 


/* <STRING> {
    \"              {yybegin(YYINITIAL); 
                     return new Symbol(sym.StringLiteral, yychar, yyline, string.toString();)}
    [^\n\r\"\\]+    { string.append( yytext() ); }
    \\t             { string.append(’\t’); }
    \\n             { string.append(’\n’); }
    \\r             { string.append(’\r’); }
    \\\"            { string.append("\""); }
    \\              { string.append(’\\’); }
} */

                    


/* Error de analisis */
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}

 /* https://github.com/sebdeveloper6952/decaf-compiler/blob/main/Decaf.g4#L46 adicional gramatica */