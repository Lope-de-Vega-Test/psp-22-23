{\rtf1\ansi\ansicpg1252\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww14240\viewh14220\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 public class TareaTres \{\
	public static void main(String[] args)  \{\
		try \{\
\
			//En caso de que no haya argumentos, systemexit devuelve 1\
\
	  		if (args.length == 0) \{\
				System.out.println("No hay argumentos D:");\
				System.exit(1);\
			\}else\{\
				//Indica True si hemos introducido int o  String\
				//llamamos a la funci\'f3n menor, para ver si devolvemos 0 o 3.\
\
				if(tipoargs(args[0]) == true)\{\
					if(menor(args[0])==true)\{\
						System.out.println("Es mayor de 0");\
						System.exit(0);\
					\}else\{\
						System.out.println("es menor de 0");\
						System.exit(3);\
					\}\
\
				//Devuelve dos si es un String\
\
				\}else\{\
					System.out.println("Es una cadena");\
					System.exit(2);\
				\}\
			\}\
		\} catch (Exception e)\{\
			e.printStackTrace();\
		\}\
	\}\
\
  public static boolean tipoargs(String args)\{\
	boolean fin;\
\
	//Devuelve true si podemos convertir a int args\
\
	try \{\
		Integer.parseInt(args);\
		fin=true;\
\
	////Devuelve false si no podemos convertir a int args, por lo que es una cadena\
\
	\} catch (NumberFormatException exception) \{\
\
		fin=false;\
	\}\
	return fin;\
  \}\
\
  public static boolean menor(String args)\{\
	boolean fin=false;\
\
	//El argumento es un int\
\
	int numero=Integer.parseInt(args);\
	try \{\
		\
	// Devuelve true si es mayor que 0 e int, si no devuelve false\
\
		if(tipoargs(args)==true && numero>=0)\{\
			fin = true;\
		\}else\{\
			fin=false;\
		\}\
	\} catch (NumberFormatException exception) \{\
		fin = false;\
		\}\
		return fin;\
	\}\
  \}}