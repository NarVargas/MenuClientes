import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner teclado = new Scanner(System.in);
		
		ArrayList<Object> clientes = new ArrayList<>();
		try {
			String basedatos = "prueba";
		    String host = "localhost";
		    String port = "3306";
		    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
		    String user = "root";
		    String pwd = "";
		 
		    Connection conexion;
			
			conexion = DriverManager.getConnection(urlConnection, user, pwd);
			conexion.setAutoCommit(true);
				
				
				
			Statement sentencia = conexion.createStatement();
		
			ResultSet resultado = sentencia.executeQuery("select * from clientes");
			
			while (resultado.next())
			{
				int i = 0;
				for(i=0;i<1;i++)
				{
					clientes.add(resultado.getString (1)+" "+resultado.getString (2)+ " "+ resultado.getString (3));
				}	
			}
			
			
			int cont = 0;
			int fila = 0;
			boolean salir = false;
			String opc;
			while(!salir)
			{
				fila = cont+1;
				System.out.println("Fila "+fila+": "+clientes.get(cont));
				System.out.println("--MENU BASE DE DATOS--");
				System.out.println("Número de fila");
				System.out.println("k - Siguiente fila");
				System.out.println("d - Fila anterior");
				System.out.println(". - Para finalizar");
				System.out.println();
				System.out.println("Elige una opción");
				opc = teclado.nextLine();

				switch(opc) 
					{
					case "k":
						if(clientes.size() > cont+1)
						{
							cont++;
						}
						else
						{
							System.out.println("No hay mas clientes");
						}
						break;
					case "d":
						if(0 < cont)
						{
							cont--;
						}
						else
						{
							System.out.println("No hay mas clientes");
						}
						break;
					case ".":
						salir=true;
		                   break;
					default:
						
						int num;
						
				        if (isNumeric(opc) == true) 
				        {
				        	num = Integer.parseInt(opc);
				            
				        	if(0 < num+1 && clientes.size()+2 > num+1)
				        	{
				        		cont = num-1;
				        	}
				        	else
				        	{
				        		System.out.println("Numero de fila no correcta");
				        	}
				        } 
				        
				        else 
				        {
				            System.out.println("Opción incorrecta");
				        }
						
					}
		       }
			
			
			}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}
