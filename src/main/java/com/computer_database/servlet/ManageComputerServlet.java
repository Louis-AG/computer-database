package com.computer_database.servlet;

import com.computer_database.mapper.ComputerDtoMapper;
import com.computer_database.model.Computer;
import com.computer_database.service.CompanyService;
import com.computer_database.service.ComputerService;
import com.computer_database.service.ICompanyService;
import com.computer_database.service.IComputerService;
import com.computer_database.validator.ComputerFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lag on 03/07/17.
 */
@WebServlet(name = "manageComputer", urlPatterns = {"/manageComputer"})
public class ManageComputerServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(ManageComputerServlet.class);
    private IComputerService computerService;
    private ICompanyService companyService;

    /**
     * constructor.
     */
    public ManageComputerServlet() {
        super();
        computerService = ComputerService.getInstance();
        companyService = CompanyService.getInstance();
    }

    /**
     * @param request  request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("companies", companyService.listAll());

        try {
            Long id;

            id = Long.valueOf(request.getParameter("id"));
            request.setAttribute("computerId", String.valueOf(id));
            if (id == 0) {
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
            } else {
                request.setAttribute("computer", ComputerDtoMapper.map(computerService.detail(id)));
                this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * @param request  request
     * @param response response
     * @throws ServletException ServletException
     * @throws IOException      IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");

        Long id = null;
        if (paramId == null) {
            id = 0L;
        } else {
            try {
                id = Long.valueOf(paramId);
            } catch (NumberFormatException e) {
                logger.error(e.getMessage());
            }
        }

        if (id != null) {
            request.setAttribute("computerId", String.valueOf(id));
            if (id == 0) {
                doPostStuffFor(request, response, "/WEB-INF/views/addComputer.jsp");

            } else {
                doPostStuffFor(request, response, "/WEB-INF/views/editComputer.jsp");
            }
        }
    }

    /**
     * @param request  request
     * @param response response
     * @param s        s
     * @throws IOException      IOException
     * @throws ServletException ServletException
     */
    private void doPostStuffFor(HttpServletRequest request, HttpServletResponse response, String s) throws IOException, ServletException {
        ComputerFormValidator computerFormValidator = new ComputerFormValidator();
        Computer computer = computerFormValidator.validate(request);

        if (computerFormValidator.getErrors().isEmpty()) {
            computerService.update(computer);
            response.sendRedirect("dashboard");
        } else {
            request.setAttribute("form", computerFormValidator);
            this.getServletContext().getRequestDispatcher(s).forward(request, response);
        }
    }


}
