package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import model.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerDAO customerdao = new CustomerDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String addressLine1 = request.getParameter("address1");
		String addressLine2 = request.getParameter("address2");
		String city = request.getParameter("city");
		String postCode = request.getParameter("postcode");
		String telephone = request.getParameter("telephone");
		String url = request.getParameter("url");
		System.out.println(url);
		ArrayList<String> emails = customerdao.readAllEmails();
		for(String em: emails) {
			if(em.equals(email)) {
				String errMsg = "Email already exists!";
				request.setAttribute("errMsg", errMsg);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		
		Customer customer = new Customer(email, title, firstName, lastName, addressLine1, addressLine2, city, postCode, telephone);
		
		boolean msg = customerdao.insert(customer, url);
		if(msg) {
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
	}

}
