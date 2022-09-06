package scanner;
import static scanner.Tokens.*;
%%

%public
%line
%column
%class Scanner
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
    public int line;
    public int column;
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
false  {lexeme=yytext(); column=yycolumn; line=yyline; return Reservadas;}

/* Espacios */

{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}

/* indentificadores*/
{digit}             {lexeme=yytext(); column=yycolumn; line=yyline; return Digit;}
{alpha}             {lexeme=yytext(); column=yycolumn; line=yyline; return Alpha;}
{alpha_num}         {lexeme=yytext(); column=yycolumn; line=yyline; return AlphaNum;}
{id}                {lexeme=yytext(); column=yycolumn; line=yyline; return Id;}
{hex_digit}         {lexeme=yytext(); column=yycolumn; line=yyline; return HexDigit;}
{hex_literal}       {lexeme=yytext(); column=yycolumn; line=yyline; return HexLiteral;}
{decimal_literal}   {lexeme=yytext(); column=yycolumn; line=yyline; return DecimalLiteral;}
{int_literal}       {lexeme=yytext(); column=yycolumn; line=yyline; return IntLiteral;}

/* comparadores y operadores*/
"="                 {lexeme=yytext(); column=yycolumn; line=yyline; return Asign;}
"+="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignSum;}
"+="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignSubs;}
"+"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Sum;}
"-"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Substract;}
"*"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Multiplication;}
"/"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Division;}
"%"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Percentage;}
"<"                 {lexeme=yytext(); column=yycolumn; line=yyline; return LessThan;}
">"                 {lexeme=yytext(); column=yycolumn; line=yyline; return GreaterThan;}
"<="                {lexeme=yytext(); column=yycolumn; line=yyline; return LessEqualThan;}
">="                {lexeme=yytext(); column=yycolumn; line=yyline; return GreaterEqualThan;}
"=="                {lexeme=yytext(); column=yycolumn; line=yyline; return Equal;}
"!="                {lexeme=yytext(); column=yycolumn; line=yyline; return NotEqual;}
"&&"                {lexeme=yytext(); column=yycolumn; line=yyline; return And;}
"||"                {lexeme=yytext(); column=yycolumn; line=yyline; return Or;}

/* agrupaciones*/
"("                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftParent;}
")"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightParent;}
"["                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftBracket;}
"]"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightBracket;}
"{"                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftKey;}
"}"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightKey;}

 . {lexeme=yytext(); column=yycolumn; line=yyline; return ERROR;}
