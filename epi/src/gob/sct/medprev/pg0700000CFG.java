package gob.sct.medprev;

import gob.sct.medprev.dwr.MedPredDwr;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.net.URL;
import java.net.URLConnection;

import com.micper.ingsw.*;

/*                      */

/**
 * Clase de configuracion de la pï¿½gina de Frames Inicial.
 * <p>
 * Administracion y Seguridad generada en JAVA (J2EE).
 * <p>
 * Esta clase se encarga de asignar los valores a los diferente atributos del
 * objeto de Entorno de este JSP.
 * 
 * @version 1.0
 *          <p>
 * @author Tecnologï¿½a Inred S.A. de C.V.
 * @author <dd>LSC. Rafael Miranda Blumenkron
 *         <p>
 * @see <small><a
 *      href="JavaScript:alert('Consulte los archivos:\n-pg0700000.jsp');">Click
 *      para lista de JSP's</a></small> </dd>
 *      <p>
 *      </p>
 *      <dt><strong>Diagrama de Clase:</strong>
 *      <p>
 *      <img src="pg0700000CFG.png">
 */

public class pg0700000CFG {
	/**
	 * Constructor por omisiï¿½n de la clase de configuracion.
	 */
	public pg0700000CFG() {
	}

	/**
	 * Metodo encargado de inicializar los valores de atributos del objeto
	 * Entorno.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuracion del JSP.
	 */
	public void runConfig(TEntorno vEntorno) {
		// Se asignan valores a los diferentes elementos del entorno general
		vEntorno.setTituloVentana("Sistema Integral de Protección y Medicina Preventiva en el Transporte");
		vEntorno.setNumModulo(07);
		vEntorno.clearListaImgs();
		vEntorno.setOnLoad("");
		vEntorno.setUrlLogo("Acerca");
	}

	/**
	 * Metodo que se ejecuta desde el JSP en la secciï¿½n &lt;HEAD&gt;.
	 * 
	 * @param vEntorno
	 *            Objeto con los valores de configuracion del JSP.
	 * @param vErrores
	 *            Objeto que permite la acumulaciï¿½n de errores del JSP.
	 * @param pc
	 *            Contexto del JSP.
	 * @param httpReq
	 *            Peticiï¿½n de HTTP en la que se encuentra el JSP.
	 * @param httpResp
	 *            Respuesta de HTTP en la que se encuentra el JSP.
	 * @throws IOException
	 * @throws ServletException
	 */
	public void outputHeader(TEntorno vEntorno, TError vErrores,
			PageContext pc, HttpServletRequest httpReq,
			HttpServletResponse httpResp) throws IOException, ServletException {
		JspWriter out = pc.getOut();
		ServletRequest request = pc.getRequest();
		if (httpReq.getMethod().toUpperCase().compareTo("GET") == 0) {
		}
		if (httpReq.getMethod().toUpperCase().compareTo("POST") == 0) {
		}
	}

	public boolean existAllImages() {
		TParametro vParametros = new TParametro("07");
		String path = vParametros.getPropEspecifica("RutaDescargaImagen");// "/nas/img/medprev/";
		MedPredDwr dwr = new MedPredDwr();
		if (dwr.actualizaImagenes() == 0) {
			return true;
		}

		File directorio = new File(path);
		String[] ficheros = directorio.list();
		String folder = "c:\\sct\\img\\";
		File dir = new File(folder);

		if (!dir.exists())
			return false;

		for (int i = 0; i < ficheros.length; i++) {
			/*
			 * BufferedReader br = new BufferedReader(new FileReader(path +
			 * ficheros[i]));
			 */
			if (!ficheros[i].contains(".svn")) {
				if (ficheros[i].contains(".")) {
					// System.out.println(ficheros[i].substring(ficheros[i].length()-3,
					// ficheros[i].length()));
					// System.out.println("Contenido del fichero " +
					// ficheros[i]);
					boolean imagen = this.revisaImagen(ficheros[i]);
					if (!imagen) {
						return false;
					}
				}
			}

			/*
			 * while ((line = br.readLine()) != null) {
			 * System.out.println(line); }
			 */
		}
		return true;
	}

	public boolean revisaImagen(String nombreImg) {

		try {
			TParametro vParametros = new TParametro("07");
			URL url = new URL(vParametros.getPropEspecifica("UrlImagenes")
					+ nombreImg);
			URLConnection urlCon = url.openConnection();
			// System.out.println(urlCon.getContentType());
			String folder = "c:\\sct\\img\\";
			// Crea el directorio de destino en caso de que no exista
			File dir = new File(folder + nombreImg);
			if (dir.exists()) {
				// System.out.println("Ya existe " + nombreImg);
				String path = vParametros
						.getPropEspecifica("RutaDescargaImagen");
				File fileServer = new File(path + nombreImg);
				long ms = fileServer.lastModified();

				Date dServerMod = new Date(ms);
				Date dLocalMod = new Date(dir.lastModified());
				if (dLocalMod.after(dServerMod)) {
					// System.out.println("Ya existe y no actualizar" +
					// nombreImg);
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;

	}
}