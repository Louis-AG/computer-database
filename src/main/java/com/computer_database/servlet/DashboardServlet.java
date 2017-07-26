package com.computer_database.servlet;

import com.computer_database.mapper.PageDtoMapper;
import com.computer_database.service.ComputerService;
import com.computer_database.service.IComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lag on 04/07/17.
 */
@WebServlet(name = "dashboard", urlPatterns = {"/index", "/dashboard"})
public class DashboardServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(DashboardServlet.class);

    private IComputerService computerService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        computerService = ComputerService.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index;
        int limit;
        String search;
        String order;

       /* try {
            index = Integer.valueOf(request.getParameter("page"));
            limit = Integer.valueOf(request.getParameter("limit"));

        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
        }*/

        if (request.getParameter("page") == null) {
            index = 0;
        } else {
            index = Integer.valueOf(request.getParameter("page"));
        }

        if (request.getParameter("limit") == null) {
            limit = 10;
        } else {
            limit = Integer.valueOf(request.getParameter("limit"));
        }

        search = request.getParameter("search");
        if (search == null) {
            search = "";
        } else {
            search = request.getParameter("search");
        }

        if (request.getParameter("order") == null) {
            order = "computer.name";
        } else {
            order = request.getParameter("order");
        }

        request.setAttribute("search", search);
        request.setAttribute("order", order);
        request.setAttribute("pageDto", PageDtoMapper.map(computerService.listAllWithPagingAndCompanyName(index, limit, search, order)));
        request.setAttribute("computerCount", computerService.getCountSearch(search));
        this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = "";
        String order = "";

        if (!request.getParameter("selection").isEmpty()) {
            String selection = request.getParameter("selection");
            computerService.deleteMany(selection);
        }

        search = request.getParameter("search");
        if (search == null) {
            search = "";
        } else {
            search = request.getParameter("search");
        }

        if (request.getParameter("order") == null) {
            order = "computer.name";
        } else {
            order = request.getParameter("order");
        }
        response.sendRedirect("dashboard?search=" + search + "&order=" + order);
    }
}
