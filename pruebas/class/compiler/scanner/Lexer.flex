package scanner;
import static scanner.Tokens.*;

%%

%public
%line
%column
%class Scanner1
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
char_literal = [^a]


%{
    public String lexeme;
    public int line;
    public int column;
%}

%%

/* palabras reservadas*/
void                {lexeme=yytext(); column=yycolumn; line=yyline; return Void;}
int                 {lexeme=yytext(); column=yycolumn; line=yyline; return Int;}
boolean             {lexeme=yytext(); column=yycolumn; line=yyline; return Boolean;}
if                  {lexeme=yytext(); column=yycolumn; line=yyline; return If;}
else                {lexeme=yytext(); column=yycolumn; line=yyline; return Else;}
for                 {lexeme=yytext(); column=yycolumn; line=yyline; return For;}
return              {lexeme=yytext(); column=yycolumn; line=yyline; return Return;}
break               {lexeme=yytext(); column=yycolumn; line=yyline; return Break;}
continue            {lexeme=yytext(); column=yycolumn; line=yyline; return Continue;}
callout             {lexeme=yytext(); column=yycolumn; line=yyline; return Callout;}
true                {lexeme=yytext(); column=yycolumn; line=yyline; return True;}
false               {lexeme=yytext(); column=yycolumn; line=yyline; return False;}

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
\'{char_literal}\'          {lexeme=yytext(); column=yycolumn; line=yyline; return CharLiteral;} 
/*\"{string_literal}*\"       {lexeme=yytext(); column=yycolumn; line=yyline; return StringLiteral;}*/

/* comparadores y operadores*/
"="                 {lexeme=yytext(); column=yycolumn; line=yyline; return Asign;}
"+="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignSum;}
"-="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignSubs;}
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
