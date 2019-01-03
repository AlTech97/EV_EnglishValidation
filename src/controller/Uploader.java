package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import model.SystemAttribute;
import java.sql.Timestamp;

/**
 * Servlet implementation class Uploader.
 */
@WebServlet("/Uploader")
public class Uploader extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private boolean isMultipart;
  private String filePath;
  private int maxFileSize = 50 * 102400;
  private int maxMemSize = 4 * 1024;
  @SuppressWarnings("unused")
  private File file;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Uploader() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @SuppressWarnings({"unchecked", "unused", "rawtypes"})
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Integer result = 0;
    String error = "";
    String content = "";



    filePath = new SystemAttribute().getValueByKey("request-upload-path")
        + request.getSession().getAttribute("idRequest") + "\\";
    File file = new File(filePath);
    if (!file.exists()) {
      if (!file.mkdir()) {
        result = 0;
        error = "error Creazione Cartella per le immagini del Prodotto";
        return;
      }
    }

    isMultipart = ServletFileUpload.isMultipartContent(request);
    response.setContentType("text/html");
    if (!isMultipart) {
      result = 0;
      error = "No file uploaded";
      return;
    }

    DiskFileItemFactory factory = new DiskFileItemFactory();
    factory.setSizeThreshold(maxMemSize);
    factory.setRepository(new File("c:\\temp"));
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setSizeMax(maxFileSize);

    try {
      List fileItems = upload.parseRequest(request);
      Iterator i = fileItems.iterator();

      while (i.hasNext()) {
        FileItem fi = (FileItem) i.next();
        if (!fi.isFormField()) {
          // Get the uploaded file parameters
          Timestamp timestamp = new Timestamp(System.currentTimeMillis());
          String fieldName = fi.getFieldName();
          String fileName = timestamp.getTime() + "-" + fi.getName().replaceAll("\\s+", "");
          String contentType = fi.getContentType();
          boolean isInMemory = fi.isInMemory();
          long sizeInBytes = fi.getSize();
          // Write the file
          if (fileName.lastIndexOf("\\") >= 0) {
            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
          } else {
            file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
          }
          fi.write(file);
          content += fileName;
          // System.out.println(filePath + fileName);
          result = 1;
        }
      }
    } catch (Exception ex) {
      result = 0;
      error = ex.getMessage();
    }



    JSONObject res = new JSONObject();
    java.io.PrintWriter out = response.getWriter();
    res.put("result", result);
    res.put("error", error);
    res.put("content", content);
    out.println(res);

  }

}
