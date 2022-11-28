package scanner;
import static scanner.Tokens.*;

%%

%public
%line
%column
%class Scan
%type Tokens

string_literal =([\"].*[\"])
char_literal =([\'].[\'])
digit = [0-9]
alpha = [a-zA-Z]
hex_digit = [0-9a-fA-F]
id = {alpha}({alpha}|{digit})*
hex_literal = 0[xX]{hex_digit}({hex_digit})*
decimal_literal =  {digit}({digit})*
espacio = [ \t\r\n]+

%{
    public String lexeme;
    public int line;
    public int column;
%}

%%

/* palabras reservadas*/
class               {lexeme=yytext(); column=yycolumn; line=yyline; return Class;}      
Program             {lexeme=yytext(); column=yycolumn; line=yyline; return Program;}      
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
{string_literal}    {lexeme=yytext(); column=yycolumn; line=yyline; return StringLiteral;}
{char_literal}      {lexeme=yytext(); column=yycolumn; line=yyline; return CharLiteral;}
{id}                {lexeme=yytext(); column=yycolumn; line=yyline; return Id;}
{hex_literal}       {lexeme=yytext(); column=yycolumn; line=yyline; return HexLiteral;}
{decimal_literal}   {lexeme=yytext(); column=yycolumn; line=yyline; return DecimalLiteral;}

/* comparadores y operadores*/
","                 {lexeme=yytext(); column=yycolumn; line=yyline; return Comma;} 
"="                 {lexeme=yytext(); column=yycolumn; line=yyline; return Asign;}  
"+="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignAdd;}  
"-="                {lexeme=yytext(); column=yycolumn; line=yyline; return AsignSubs;}  
"+"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Add;}  
"-"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Substract;}  
"*"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Multiplication;}  
"/"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Division;}  
"%"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Mod;}  
"<"                 {lexeme=yytext(); column=yycolumn; line=yyline; return LessThan;}  
">"                 {lexeme=yytext(); column=yycolumn; line=yyline; return GreaterThan;}  
"<="                {lexeme=yytext(); column=yycolumn; line=yyline; return LessEqualThan;}  
">="                {lexeme=yytext(); column=yycolumn; line=yyline; return GreaterEqualThan;}  
"=="                {lexeme=yytext(); column=yycolumn; line=yyline; return Equal;}  
"!="                {lexeme=yytext(); column=yycolumn; line=yyline; return NotEqual;}  
"&&"                {lexeme=yytext(); column=yycolumn; line=yyline; return And;}  
"||"                {lexeme=yytext(); column=yycolumn; line=yyline; return Or;}  
";"                 {lexeme=yytext(); column=yycolumn; line=yyline; return SemiColom;}
"!"                 {lexeme=yytext(); column=yycolumn; line=yyline; return Exclam;}

/* agrupaciones*/
"[]"                {lexeme=yytext(); column=yycolumn; line=yyline; return BracketOpenClose;} 
"("                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftParent;} 
")"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightParent;} 
"["                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftBracket;} 
"]"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightBracket;} 
"{"                 {lexeme=yytext(); column=yycolumn; line=yyline; return LeftKey;} 
"}"                 {lexeme=yytext(); column=yycolumn; line=yyline; return RightKey;} 
"()"                {lexeme=yytext(); column=yycolumn; line=yyline; return ParentOpenClose;} 
"{}"                {lexeme=yytext(); column=yycolumn; line=yyline; return KeyOpenClose;} 



 . {lexeme=yytext(); column=yycolumn; line=yyline; return ERROR;}
