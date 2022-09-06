package codigo;
import static codigo.Tokens.*;
%%

%line
%column
%class Lexer
%type Tokens

digit = [0-9]
alpha = [a-zA-Z]
alpha_num = {alpha}|{digit}
id = {alpha}({alpha_num})*
hex_digit = [0-9a-fA-F]
hex_literal = 0[xX]{hex_digit}({hex_digit})*
decimal_literal =  {digit}({digit})*
int_literal = {hex_literal} | {decimal_literal}
espacio = [ ,\t,\r,\n]+

%{
    public String lexeme;
%}


%%

/* palabras reservadas*/
void |
int |
boolean |
if |
else |
for |
return |
break |
continue |
callout |
true |
false  {lexeme=yytext(); return Reservadas;}

/* Espacios */

{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}

/* indentificadores*/
{digit}             {lexeme=yytext(); return Digit;}
{alpha}             {lexeme=yytext(); return Alpha;}
{alpha_num}         {lexeme=yytext(); return AlphaNum;}
{id}                {lexeme=yytext(); return Id;}
{hex_digit}         {lexeme=yytext(); return HexDigit;}
{hex_literal}       {lexeme=yytext(); return HexLiteral;}
{decimal_literal}   {lexeme=yytext(); return DecimalLiteral;}
{int_literal}       {lexeme=yytext(); return IntLiteral;}

/* comparadores y operadores*/
"="                 {lexeme=yytext(); return Asign;}
"+="                {lexeme=yytext(); return AsignSum;}
"+="                {lexeme=yytext(); return AsignSubs;}
"+"                 {lexeme=yytext(); return Sum;}
"-"                 {lexeme=yytext(); return Substract;}
"*"                 {lexeme=yytext(); return Multiplication;}
"/"                 {lexeme=yytext(); return Division;}
"%"                 {lexeme=yytext(); return Percentage;}
"<"                 {lexeme=yytext(); return LessThan;}
">"                 {lexeme=yytext(); return GreaterThan;}
"<="                {lexeme=yytext(); return LessEqualThan;}
">="                {lexeme=yytext(); return GreaterEqualThan;}
"=="                {lexeme=yytext(); return Equal;}
"!="                {lexeme=yytext(); return NotEqual;}
"&&"                {lexeme=yytext(); return And;}
"||"                {lexeme=yytext(); return Or;}

/* agrupaciones*/
"("                 {lexeme=yytext(); return LeftParent;}
")"                 {lexeme=yytext(); return RightParent;}
"["                 {lexeme=yytext(); return LeftBracket;}
"]"                 {lexeme=yytext(); return RightBracket;}
"{"                 {lexeme=yytext(); return LeftKey;}
"}"                 {lexeme=yytext(); return RightKey;}

 . {return ERROR;}
