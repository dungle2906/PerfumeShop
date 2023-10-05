/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package Filter;

import DAOs.ProductDAO;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 *
 * @author User
 */
public class ImageHandeling implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public ImageHandeling() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ImageHandeling:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("ImageHandeling:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("ImageHandeling:doFilter()");
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        doBeforeProcessing(request, response);

        Throwable problem = null;
        try {
            String btnCreate = request.getParameter("btnInsertPro");
            String btnUpdate = request.getParameter("btnUpdatePro");
            if (btnCreate != null) {
                AddingProduct(httpRequest, httpResponse);
            }
            if (btnUpdate != null) {
                UpdatingProduct(httpRequest, httpResponse);
            }
            chain.doFilter(request, response);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public boolean AddingProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String relImgPath = "";
        String txtProName = request.getParameter("txtProID");
        String ImageFolderPath = "Image";
        String HardPath = "C:\\Users\\User\\OneDrive\\FPTU Study Files\\FullProject_Test\\src\\main\\webapp";
        String uploadFilePath = HardPath + File.separator + ImageFolderPath;
        File fileSaveDirectory = new File(uploadFilePath);
        if (!fileSaveDirectory.exists()) {
            fileSaveDirectory.mkdirs();
        }

        Part filePart = request.getPart("fileProImg");
        if ((filePart != null)
                && ((!filePart.getContentType().equals("application/octet-stream")
                || ((filePart.getSize() != -1) && (filePart.getSize() != 0))))) {
            String extension = filePart.getContentType();
            extension = extension.substring(extension.lastIndexOf("/") + 1);
            String ImageName = txtProName + "." + extension;
            InputStream fileContent = filePart.getInputStream();

            File file = new File(uploadFilePath, ImageName);
            final PrintWriter writer = response.getWriter();
            try {
                Files.copy(fileContent, file.toPath());
            } catch (Exception e) {
                writer.println("<br/> ERROR: " + e);
            }
            relImgPath = ImageName;
        }

        request.setAttribute("fileProImg", relImgPath);
        return true;
    }

    public boolean UpdatingProduct(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String relImgPath = "";
        int txtProName = Integer.parseInt(request.getParameter("txtProID"));
        String ImageFolderPath = "Image";
        String HardPath = "C:\\Users\\User\\OneDrive\\FPTU Study Files\\FullProject_Test\\src\\main\\webapp";

        String uploadFilePath = HardPath + File.separator + ImageFolderPath;
        File fileSaveDirectory = new File(uploadFilePath);
        if (!fileSaveDirectory.exists()) {
            fileSaveDirectory.mkdirs();
        }

        Part filePart = request.getPart("fileProImg");

        if ((filePart != null)
                && ((!filePart.getContentType().equals("application/octet-stream")
                || ((filePart.getSize() != -1) && (filePart.getSize() != 0))))) {
            try {
                ProductDAO dao = new ProductDAO();
                String URL = dao.GetImageURL(txtProName);
                dao.DeleteImage(URL);
            } catch (Exception ex) {

            }
            String extension = filePart.getContentType();
            extension = extension.substring(extension.lastIndexOf("/") + 1);
            String ImageName = txtProName + "." + extension;
            InputStream fileContent = filePart.getInputStream();

            File file = new File(uploadFilePath, ImageName);
            final PrintWriter writer = response.getWriter();
            try {
                Files.copy(fileContent, file.toPath());
            } catch (Exception e) {
                writer.println("<br/> ERROR: " + e);
            }
            relImgPath = ImageName;
        }

        request.setAttribute("fileImgPath", relImgPath);
        return true;
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("ImageHandeling:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ImageHandeling()");
        }
        StringBuffer sb = new StringBuffer("ImageHandeling(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
