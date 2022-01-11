package cc.openhome;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(
    urlPatterns={"/mail"},
    initParams={
        @WebInitParam(name = "host", value = "smtp.gmail.com"),
        @WebInitParam(name = "port", value = "587"),
        @WebInitParam(name = "username", value = "ccxx2806449@gmail.com"),
        @WebInitParam(name = "password", value = "cool1327")
    }
)
public class Mail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final Pattern fileNameRegex =
            Pattern.compile("filename=\"(.*)\"");    
    
    private final String html = 
    "<html>" + 
    "    <head>" +
    "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>" +
    "    </head>" +
    "    <body>" +
    "    <img" +
    "        style='border: 3px solid ; font-weight: bold;'" +
    "            src='cid:#filename'" +
    "            hspace='3' vspace='3'> <br>" +
    "        #text" +
    "    </body>" +
    "</html>";
    
    private String host;
    private String port;
    private String username;
    private String password;
    private Properties props;

    @Override
    public void init() throws ServletException {
        host = getServletConfig().getInitParameter("host");
        port = getServletConfig().getInitParameter("port");
        username = getServletConfig().getInitParameter("username");
        password = getServletConfig().getInitParameter("password");

        props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", port);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");
        Part part = request.getPart("image");

        try {
            Message message = createMessage(from, to, subject, text, part);
            Transport.send(message);
            response.getWriter().println("郵件傳送成功");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private Message createMessage(String from, String to, String subject, String text, Part part)
            throws MessagingException, AddressException, IOException {

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setSentDate(new Date());
        message.setContent(multiPart(text, part));

        return message;
    }

    private Multipart multiPart(String text, Part part)
            throws MessagingException, UnsupportedEncodingException, IOException {
        String filename = getSubmittedFileName(part);
        
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html.replace("#filename", filename).replace("#text", text), "text/html;charset=UTF-8");

        MimeBodyPart filePart = new MimeBodyPart();
        filePart.setFileName(MimeUtility.encodeText(filename, "UTF-8", "B"));
        filePart.setHeader("Content-ID", "<" + filename + ">");        
        filePart.setDataHandler(
                new DataHandler(
                    new ByteArrayDataSource(part.getInputStream(), part.getContentType())    
                )
            );
        
        MimeMultipart multiPart = new MimeMultipart();
        multiPart.addBodyPart(htmlPart);
        multiPart.addBodyPart(filePart);
        return multiPart;
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("Content-Disposition");
        Matcher matcher = fileNameRegex.matcher(header);
        matcher.find();

        String filename = matcher.group(1);
        if (filename.contains("\\")) {
            return filename.substring(filename.lastIndexOf("\\") + 1);
        }
        return filename;
    }
}
