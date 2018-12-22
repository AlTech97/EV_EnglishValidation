package controller;

import java.io.Serializable;
import javax.servlet.http.HttpSession;



public class CheckSession implements Serializable{
  /**
   * 
   */
  private static final long serialVersionUID = -6469233075067075161L;
  private String pageFolder;
  private String pageName;
  private String urlRedirect;  
  private HttpSession session;
  private boolean allowed;

  /**
   * 
   */
  public CheckSession(String pf, String pn, HttpSession s) {
    this.pageFolder = pf;
    this.pageName = pn;		 
    this.session = s;
    this.urlRedirect = "/login.jsp";
  }

  /**
   * 
   */
  public HttpSession getSession() {
    return this.session;
  }

  /**
   * 
   */
  public void setSession(HttpSession session) {
    this.session = session;
  }

  /**
   * 
   */
  public String getUrlRedirect() {
    return this.urlRedirect;
  }

  /**
   * 
   */
  public void setUrlRedirect(String urlRedirect) {
    this.urlRedirect = urlRedirect;
  }

  /**
   * 
   */
  public String getPageName() {
    return pageName;
  }

  /**
   * 
   */
  public void setPageName(String pageName) {
    this.pageName = pageName;
  }

  /**
   * 
   */
  public String getPageFolder() {
    return pageFolder;
  }

  /**
   * 
   */
  public void setPageFolder(String pageFolder) {
    this.pageFolder = pageFolder;
  }

  /**
   * 
   */
  public boolean isAllowed() {
    this.setAllowed(true);
    return allowed;
  }

  /**
   * 
   */
  public void setAllowed(boolean allowed) {
    this.allowed = allowed;
  }

}
