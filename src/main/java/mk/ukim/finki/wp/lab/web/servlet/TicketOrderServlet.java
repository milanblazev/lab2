package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.TicketOrder;
import mk.ukim.finki.wp.lab.services.MovieService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/ticketOrder")
public class TicketOrderServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;


    public TicketOrderServlet(SpringTemplateEngine springTemplateEngine,
                              MovieService movieService) {
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);

        WebContext context = new WebContext(webExchange);
        context.setVariable("order", (TicketOrder)req.getSession().getAttribute("ticketOrder"));

        this.springTemplateEngine.process("orderConfirmation.html",context,resp.getWriter());

    }
}
