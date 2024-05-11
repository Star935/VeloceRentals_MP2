package Filters;

import Services.ServiceJdbcException;
import annotations.MysqlConn;
import jakarta.inject.Inject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@WebFilter("/*")
public class ConnectionFilter implements Filter {
    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        try {
            Connection connRequest = this.conn;
            if (connRequest.getAutoCommit()) {
                connRequest.setAutoCommit(false);
            }
            try {
                request.setAttribute("conn", connRequest);
                chain.doFilter(request, response);
                connRequest.commit();
            } catch (ServiceJdbcException e) {
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
