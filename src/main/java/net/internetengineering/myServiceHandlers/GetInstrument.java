package net.internetengineering.myServiceHandlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.server.StockMarket;
import net.internetengineering.domain.dealing.Instrument;
import net.internetengineering.domain.dealing.SellingOffer;
import net.internetengineering.domain.dealing.BuyingOffer;
import net.internetengineering.utils.JsonBuilder;

 

@WebServlet("/getinstrument")
public class GetInstrument extends MyHttpServlet{

    @Override
    public void doMyGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
            out.println("1");
            List<Instrument> instruments = StockMarket.getInstance().getInstruments();
            
            List<Object> myList = new ArrayList<Object>();
            
            for (int i = 0; i < instruments.size(); i++) {
                Map<String,Object> map = new HashMap<String, Object>();

                map.put("name", instruments.get(i).getSymbol());
                map.put("quantity", instruments.get(i).getQuantity());

                List<Object> sells = new ArrayList<Object>();
                List<Object> buys = new ArrayList<Object>();
                out.println("2");
                for (int j = 0; j < instruments.get(i).getSellingOffers().size(); j++) {
                    Map<String,Object> sell = new HashMap<String, Object>();
                    sell.put("id", instruments.get(i).getSellingOffers().get(j).getID());
                    sell.put("quantity", instruments.get(i).getSellingOffers().get(j).getQuantity());
                    sell.put("price", instruments.get(i).getSellingOffers().get(j).getPrice());
                    sell.put("type", instruments.get(i).getSellingOffers().get(j).getType());

                    sells.add(sell);
                }
                out.println("3");
                for (int j = 0; j < instruments.get(i).getBuyingOffers().size(); j++) {
                    Map<String,Object> buy = new HashMap<String, Object>();
                    buy.put("id", instruments.get(i).getBuyingOffers().get(j).getID());
                    buy.put("quantity", instruments.get(i).getBuyingOffers().get(j).getQuantity());
                    buy.put("price", instruments.get(i).getBuyingOffers().get(j).getPrice());
                    buy.put("type", instruments.get(i).getBuyingOffers().get(j).getType());

                    buys.add(buy);
                }

                map.put("sellingOffers", sells);
                map.put("buyingOffers", buys);

                myList.add(map);
            }
            
            // map.put("name", instruments.getSymbol());
            // map.put("quantity", instruments.getQuantity());

            // Object[] sells;
            // for (int i = 0; i < instruments.size(); i++ ) {
            //     sells.push(Json.createObjectBuilder().add("city", "Bursa"))
            // }
            // map.put("sellingOffers", );
            // map.put("buyingOffers", );

            // JsonObject map = new JsonObject();

            // map.addProperty("name", instruments.get(0).getSymbol());
            // map.addProperty("quantity", instruments.get(0).getQuantity());

            // JsonArray sellingOffers = new JsonArray();
 
            // for (int i = 0; i < instruments.get(0).getSellingOffers().size(); i++) {
            //     JsonObject sellingOffer = new JsonObject();

            //     sellingOffer.addProperty("id", instruments.get(0).getSellingOffers().get(i).getID());
            //     sellingOffer.addProperty("quantity", instruments.get(0).getSellingOffers().get(i).getQuantity());
            //     sellingOffer.addProperty("price", instruments.get(0).getSellingOffers().get(i).getPrice());
            //     sellingOffer.addProperty("type", instruments.get(0).getSellingOffers().get(i).getType());

            //     sellingOffers.add(sellingOffer);
            // }
            
            // map.add("sellingOffers",sellingOffers);

            // JsonArray buyingOffers = new JsonArray();
 
            // for (int i = 0; i < instruments.get(0).getBuyingOffers().size(); i++) {
            //     JsonObject buyingOffer = new JsonObject();

            //     buyingOffer.addProperty("id", instruments.get(0).getBuyingOffers().get(i).getID());
            //     buyingOffer.addProperty("quantity", instruments.get(0).getBuyingOffers().get(i).getQuantity());
            //     buyingOffer.addProperty("price", instruments.get(0).getBuyingOffers().get(i).getPrice());
            //     buyingOffer.addProperty("type", instruments.get(0).getBuyingOffers().get(i).getType());

            //     buyingOffers.add(buyingOffer);
            // }
            
            // map.add("buyingOffers", buyingOffers);
            //  Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            //     System.out.println(gson.toJson(map));
            
            //JsonBuilder.writeToJSON(map, response);

            for (int i = 0; i < myList.size(); i++) {
                out.println(myList.get(i));
            }
            
    }
    
}
