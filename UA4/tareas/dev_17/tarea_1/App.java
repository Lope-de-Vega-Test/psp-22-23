import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.net.ftp.*;

public class App extends JFrame {

	private JTextField servidorField;
    private JTextField usuarioField;
    private JPasswordField claveField;
    private JButton conectarBtn;
    private JLabel estadoLabel;

	public App() {
        inicializarInterfaz();
    }

	private void inicializarInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel servidorLabel = new JLabel("Servidor FTP:");
        servidorLabel.setBounds(10, 10, 80, 25);
        panel.add(servidorLabel);

        servidorField = new JTextField();
        servidorField.setBounds(100, 10, 165, 25);
        panel.add(servidorField);

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setBounds(10, 40, 80, 25);
        panel.add(usuarioLabel);

        usuarioField = new JTextField();
        usuarioField.setBounds(100, 40, 165, 25);
        panel.add(usuarioField);

        JLabel claveLabel = new JLabel("Clave:");
        claveLabel.setBounds(10, 70, 80, 25);
        panel.add(claveLabel);

        claveField = new JPasswordField();
        claveField.setBounds(100, 70, 165, 25);
        panel.add(claveField);

        conectarBtn = new JButton("Conectar");
        conectarBtn.setBounds(10, 105, 100, 25);
        conectarBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                conectarFTP();
            }
        });
        panel.add(conectarBtn);

        estadoLabel = new JLabel("Estado: desconectado");
        estadoLabel.setBounds(10, 140, 250, 25);
        panel.add(estadoLabel);

        add(panel);
        setTitle("Cliente FTP");
		setSize(280,170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void conectarFTP() {

	FTPClient cliente = new FTPClient();
	String servFTP = servidorField.getText();
	System.out.println("Nos conectamos a: " + servFTP);
	String usuario = usuarioField.getText();
	String clave = claveField.getPassword().toString();
	try {
		cliente.connect(servFTP);
        cliente.enterLocalPassiveMode(); //modo pasivo

		boolean login = cliente.login(usuario, clave);
		if (login)
			System.out.println("Login correcto...");
		else {
			System.out.println("Login Incorrecto...");
			cliente.disconnect();
			System.exit(1);
		}
		System.out.println("Directorio actual: "
				         + cliente.printWorkingDirectory());

		FTPFile[] files = cliente.listFiles();
		System.out.println("Ficheros en el directorio actual:"
					+ files.length);
            //array para visualizar el tipo de fichero
		String tipos[] = {"Fichero", "Directorio","Enlace simb."};
		
		for (int i = 0; i < files.length; i++) {
			System.out.println("\t" + files[i].getName() + " => "
					+ tipos[files[i].getType()]);
		}
		boolean logout = cliente.logout();
		if (logout) 
			System.out.println("Logout del servidor FTP...");
                else 
		        System.out.println("Error al hacer Logout...");
		//
		cliente.disconnect();
		System.out.println("Desconectado...");
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}		
  }// ..


  public static void main(String[] args) {

            App cliente = new App();
            cliente.setVisible(true);
}
}// ..
