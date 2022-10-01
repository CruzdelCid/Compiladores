	package tarea2;
import java_cup.runtime.Symbol;

%%

%cupsym Simbolo
%class AnalizadorLexico
%cup
%public
%unicode
%public
%line
%column
%ignorecase

digito = [0-9]
//numero = ("-")?{digito}+("." {digito}+)?
numero = {digito}+
decimal = {digito}+"."{digito}+
//tchar = "'"~"'"
tchar = "'"([^\']|"^'"|"^^"|"^t"|"^n")?"'"
//tstring = "\"" ~"\""
//tstring = "\""(.|"#\"s"|"^^"|"^t"|"^n")*"\""
tstring = "\"" ([^(\"|\n)]|#\"|##|#t|#n)* "\""

%{
//VARIALES Y METODOS DEL SCANER

%}

%%	

<YYINITIAL>{

//Operadores Relacionales
"=="                {return new Symbol(Simbolo.igualacion,yycolumn,yyline);}
"!="                {return new Symbol(Simbolo.diferenciacion,yycolumn,yyline);}
"<"                 {return new Symbol(Simbolo.menor_que,yycolumn,yyline);}
"<="                {return new Symbol(Simbolo.menor_o_igual,yycolumn,yyline);}
">"                 {return new Symbol(Simbolo.mayor_que,yycolumn,yyline);}
">="                {return new Symbol(Simbolo.mayor_o_igual,yycolumn,yyline);}

//Operadores Lógicos
"||"                {return new Symbol(Simbolo.or,yycolumn,yyline);}
"!||"               {return new Symbol(Simbolo.nor,yycolumn,yyline);}
"&&"                {return new Symbol(Simbolo.and,yycolumn,yyline);}
"!&&"               {return new Symbol(Simbolo.nand,yycolumn,yyline);}
"&|"                {return new Symbol(Simbolo.xor,yycolumn,yyline);}
"!"                 {return new Symbol(Simbolo.not,yycolumn,yyline);}

//Operadores Aritméticos
"+"                 {return new Symbol(Simbolo.suma,yycolumn,yyline);}
"-"                 {return new Symbol(Simbolo.resta,yycolumn,yyline);}
"*"                 {return new Symbol(Simbolo.multiplicacion,yycolumn,yyline);}  
"/"                 {return new Symbol(Simbolo.division,yycolumn,yyline);}
"^"                 {return new Symbol(Simbolo.potencia,yycolumn,yyline);}
"("                 {return new Symbol(Simbolo.Ipar,yycolumn,yyline);}
")"                 {return new Symbol(Simbolo.Dpar,yycolumn,yyline);}

"false"             {return new Symbol(Simbolo.false_,yycolumn,yyline);}
"falso"             {return new Symbol(Simbolo.falso,yycolumn,yyline);}
"verdadero"         {return new Symbol(Simbolo.verdadero,yycolumn,yyline);}
"true"              {return new Symbol(Simbolo.true_,yycolumn,yyline);}
"null"              {return new Symbol(Simbolo.null_,yycolumn,yyline);}

{numero}       	{ return new Symbol(Simbolo.numero,  yycolumn, yyline, yytext());}
{decimal}       { return new Symbol(Simbolo.decimal,  yycolumn, yyline, yytext());}
{tstring}       { return new Symbol(Simbolo.tstring,  yycolumn, yyline, yytext());}
{tchar}         { return new Symbol(Simbolo.tchar,  yycolumn, yyline, yytext());}

/* BLANCOS */
[ \t\r\f\n]+        { /* Se ignoran */}


/* ERRORES LEXICOS */
.               { System.out.println("Error lexico: "+yytext() + " Linea: "+yyline + " Columna: "+yycolumn);}

}
























