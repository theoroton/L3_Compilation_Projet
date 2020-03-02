package yal.analyse ;

import java_cup.runtime.*;
import yal.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

idf = [A-Za-z_][A-Za-z_0-9]*

csteE = [0-9]+

finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

comment = [/]{2}.*

%%

"programme"            { return symbol(CodesLexicaux.PROGRAMME); }
"fonction"             { return symbol(CodesLexicaux.FONCTION); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"lire"                 { return symbol(CodesLexicaux.LIRE); }

"retourne"             { return symbol(CodesLexicaux.RETOURNE); }

"entier"               { return symbol(CodesLexicaux.ENTIER); }

"si"                   { return symbol(CodesLexicaux.SI); }
"alors"                { return symbol(CodesLexicaux.ALORS); }
"sinon"                { return symbol(CodesLexicaux.SINON); }
"finsi"                { return symbol(CodesLexicaux.FINSI); }

"tantque"              { return symbol(CodesLexicaux.TANTQUE); }
"repeter"              { return symbol(CodesLexicaux.REPETER); }
"fintantque"           { return symbol(CodesLexicaux.FINTANTQUE); }

"non"                  { return symbol(CodesLexicaux.NON); }

"+"                    { return symbol(CodesLexicaux.ADDITION); }
"-"                    { return symbol(CodesLexicaux.SOUSTRACTION); }
"*"                    { return symbol(CodesLexicaux.MULTIPLICATION); }
"/"                    { return symbol(CodesLexicaux.DIVISION); }

"<"                    { return symbol(CodesLexicaux.INFERIEUR); }
">"                    { return symbol(CodesLexicaux.SUPERIEUR); }

"("                    { return symbol(CodesLexicaux.PARENTHESEGAUCHE); }
")"                    { return symbol(CodesLexicaux.PARENTHESEDROITE); }

"=="                   { return symbol(CodesLexicaux.EGAL); }
"!="                   { return symbol(CodesLexicaux.DIFFERENT); }

"et"                   { return symbol(CodesLexicaux.ET); }
"ou"                   { return symbol(CodesLexicaux.OU); }

";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
"="                    { return symbol(CodesLexicaux.AFFECT); }

{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }

{idf}      	           { return symbol(CodesLexicaux.IDF, yytext()); }

{comment}              { }

{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

