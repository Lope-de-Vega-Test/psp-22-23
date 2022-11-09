{\rtf1\ansi\ansicpg1252\cocoartf2639
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset0 LucidaGrande;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww22980\viewh14460\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 import java.io.*;\
import java.util.Scanner;\
\
public class LanzadorTareaTres\{\
\
	public static void main(String[] args) throws IOException \{\
        String texto;\
        String fin = "\\n";\
\
        //Si no introducimos ning\'fan argumento inicializamos como un String vac\'edo \
\
        if(args.length==0)\{\
            texto="";\
        \}else\{\
            texto = args[0];\
        \}\
        ProcessBuilder pb;\
\
\
		String dir = System.getProperty("user.dir");\
        System.out.println("current dir = " + dir);\
		File directorio = new File(dir);\
\
        //si args esta vac\'edo en Lanzar, ejecutamos el programa sin a\'f1adirle argumentos\
        //si no est\'e1 vac\'edo, lo ejecutamos con los argumentos recogidos en la variable texto.\
\
        if(args.length==0)\{\
		    pb = new ProcessBuilder("java","Tarea3.java");\
        \}else\{\
            pb = new ProcessBuilder("java","Tarea3.java",""+texto);\
        \}\
        \
		pb.directory(directorio);\
\
\
		// se ejecuta el proceso\
\
		Process p = pb.start();\
\
		// escritura -- envia entrada \
\
		OutputStream os = p.getOutputStream();\
                System.out.println(texto);\
		os.write(texto.getBytes());\
		os.flush(); // vac
\f1 \uc0\u65533 
\f0 a el buffer de salida\
\
		// lectura -- obtiene la salida\
\
		InputStream is = p.getInputStream();\
		int c;\
		while ((c = is.read()) != -1)\
			System.out.print((char) c);\
		is.close();\
\
		// COMPROBACION DE ERROR - 0 bien - 1 error\
\
		int exitVal;\
		try \{\
			exitVal = p.waitFor();\
			System.out.println("Valor de Salida: " + exitVal);\
		\} catch (InterruptedException e) \{\
			e.printStackTrace();\
		\}\
\
		try \{\
			InputStream er = p.getErrorStream();\
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));\
			String liner = null;\
			while ((liner = brer.readLine()) != null)\
				System.out.println("ERROR >" + liner);\
		\} catch (IOException ioe) \{\
			ioe.printStackTrace();\
		\}\
	\}\
\
\}}