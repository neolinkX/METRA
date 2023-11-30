package gob.sct.medprev.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sct.utic.JSON.JSONArray;
import mx.gob.sct.utic.JSON.JSONObject;
import com.micper.ingsw.TParametro;

/**
 * Servlet implementation class ObtenPropiedades
 */
public class ObtenPropiedades extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ObtenPropiedades() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		try {
			String tipoInfo = request.getParameter("info");
			if (tipoInfo.equals("propierties")) {
				out.print(getJSONPropiedades());
			}
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			out.close();
		}
	}

	public String getJSONPropiedades() {
		TParametro VParametros2 = new TParametro("7");
		StringBuilder builder = new StringBuilder();
		JSONObject Json = new JSONObject(VParametros2.hmPropiedades);
		builder.append(Json.toString());
		// System.out.println(Json.toString());
		return builder.toString();
	}
}
