package CalcularImc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalcularIMC")
public class CalcularIMCServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		Locale.setDefault(Locale.US);
        
		double peso = Double.parseDouble(request.getParameter("peso"));
		double altura = Double.parseDouble(request.getParameter("altura"));
        
        IMC imc = new IMC();
        imc.setAltura(altura);
        imc.setPeso(peso);

            
            double calculoImc = peso / (Math.pow(altura, 2));
            imc.setValor(calculoImc);
           
            if(imc.getValor() < 18.5) {
        	   imc.setResultado("Seu IMC está Baixo");
           }
            else if(imc.getValor() >= 18.6 && imc.getValor() < 25.0) {
            	imc.setResultado("Seu IMC está Normal");
            }
            else if(imc.getValor() >= 25.0 && imc.getValor() < 29.9) {
            	imc.setResultado("Você está SobrePeso");
            }
            else {
            	imc.setResultado("Você está Obeso");
            }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado do IMC</title>");
            out.println("<link rel='stylesheet' href='Style.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Resultado do IMC</h1>");
            out.println("<ul>");
            out.println("<li>Peso: " + imc.getPeso() + " kg</li>");
            out.println("<li>Altura: " + imc.getAltura() + " m</li>");
            out.println("<li>IMC: " + String.format("%.2f", imc.getValor()) + "</li>");
            out.println("<li>Resultado: " + imc.getResultado() + "</li>");
            out.println("</ul>");
            out.println("<p><a class='link' href='index.html'>Voltar para a Página Inicial</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}