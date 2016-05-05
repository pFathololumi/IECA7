package net.internetengineering.myServiceHandlers;

import net.internetengineering.domain.dealing.BuyingOffer;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.server.StockMarket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class BuyOrder{
	public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		MyLogger logger = new MyLogger(new ArrayList<String>());
		PrintWriter out = response.getWriter();
		try {
			String id = request.getParameter("id");
			String instrument = request.getParameter("instrument");
			String type = request.getParameter("type");
			Long price = Long.parseLong(request.getParameter("price"));
			Long quantity = Long.parseLong(request.getParameter("quantity"));
			BuyingOffer buyingOffer = new BuyingOffer(price,quantity,type,id);
			if(instrument==null || instrument.isEmpty())
				throw new DataIllegalException("Mismatched Parameters");
			buyingOffer.validateVariables();
			StockMarket.getInstance().executeBuyingOffer(out,buyingOffer,instrument);
			//request.setAttribute("successes", logger.getAndFlushMyLogger());
		} catch (DataIllegalException e) {
			out.println(e.getMessage());
//			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}catch(Exception e){
			out.println("Mismatched Parameters");
//			request.setAttribute("errors", logger.getAndFlushMyLogger());
		}
//		request.getRequestDispatcher("show-info.jsp").forward(request, response);
	}

	public static void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
