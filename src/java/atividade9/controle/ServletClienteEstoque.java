/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade9.controle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 80119050
 */
public class ServletClienteEstoque extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            URL url = new URL("http://localhost:8080/JSON-Serializacao/servletservicoestoque");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            String jsonString = "";
            while ((inputLine = br.readLine()) != null){
            jsonString += inputLine;
            }
            br.close();
            
            Gson gson = new GsonBuilder().create();
            ArrayList<Estoque> listaDeItensFromJSON = (ArrayList<Estoque>)gson.fromJson(jsonString,new TypeToken<ArrayList<Estoque>>(){}.getType());
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletClienteEstoque</title>");            
            out.println("</head>");
            out.println("<body>");
            for(Estoque e: listaDeItensFromJSON){
                out.println("<b>Código do Produto:</b> " +e.getCodigoDoEstoque());
                out.println("<br/>");
                out.println("<b>Nome:</b> " +e.getNome());
                out.println("<br/>");
                out.println("<b>Descrição:</b> " +e.getDescricao());
                out.println("<br/>");
                out.println("<b>Valor:</b> " +e.getValor());
                out.println("<br/>");
                out.println("<b>e-mail do vendedor:</b> " +e.getEmai());
                out.println("<hr/>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
