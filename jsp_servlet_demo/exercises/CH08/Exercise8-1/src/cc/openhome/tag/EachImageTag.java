package cc.openhome.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class EachImageTag extends SimpleTagSupport {
    private String var;
    private String dir;

    @Override
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) this.getJspContext();
        
        pageContext.getServletContext()
                   .getResourcePaths(dir)
                   .forEach(imagePath -> {
                       pageContext.setAttribute(var, "." + imagePath);
                       System.out.println("var = "+var);
                       System.out.println("imagePath = "+imagePath);
                       try {
                           this.getJspBody().invoke(null);
                       } catch (JspException | IOException e) {
                           throw new RuntimeException(e); 
                       } 
                   });
        System.out.println("ResourcePath = "+pageContext.getServletContext()
                   .getResourcePaths(dir));
        System.out.println("dir = "+dir);
            
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
