package net.internetengineering.domain.dealing;

import net.internetengineering.domain.Customer;
import net.internetengineering.domain.Transaction;
import net.internetengineering.domain.dealing.types.GTC;
import net.internetengineering.domain.dealing.types.ITypeExecutor;
import net.internetengineering.exception.DataIllegalException;
import net.internetengineering.utils.CSVFileWriter;
import net.internetengineering.server.StockMarket;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by Hamed Ara on 2/18/2016.
 */
public class Instrument {
    private String symbol;
    private Long quantity;
    private List<SellingOffer> sellingOffers;
    private List<BuyingOffer> buyingOffers;

	public Instrument(String sym,Long quantity) {
        this.symbol = sym;
        this.quantity = quantity;
        this.sellingOffers = new ArrayList<SellingOffer>();
        this.buyingOffers = new ArrayList<BuyingOffer>();
    }

	public String getSymbol() {

		return symbol;
	}

	public Long getQuantity() {
		return quantity;
	}

	public List<SellingOffer> getSellingOffers() {
		return sellingOffers;
	}

	public List<BuyingOffer> getBuyingOffers() {
		return buyingOffers;
	}

	public void initialOfferLists(){
		initialBuyingOfferList();
		initialSellingOfferList();
	}

	private void initialBuyingOfferList(){
		BuyingOffer b = new BuyingOffer(440L,55L,"KQL","45");
		buyingOffers.add(b);
		b=new BuyingOffer(44L,56L,"ZDF","85");
		buyingOffers.add(b);
	}

	private void initialSellingOfferList(){
		SellingOffer s = new SellingOffer(220L,130L,"GTC","12");
		sellingOffers.add(s);
		s = new SellingOffer(22L,13L,"MTF","11");
		sellingOffers.add(s);
	}

    public void executeSellingByType(PrintWriter out, SellingOffer offer) throws DataIllegalException {
		try {
			System.out.println( GTC.class.getName());
			Class clazz = Class.forName("net.internetengineering.domain.dealing.types."+offer.getType());
			Object obj= clazz.newInstance();
			if(obj instanceof ITypeExecutor){
				((ITypeExecutor)obj).sellingExecute(out,offer,sellingOffers,buyingOffers,symbol);
			}
		}catch (ClassNotFoundException ex){
			throw new DataIllegalException("Invalid type");
		}catch (IllegalAccessException ex){
			throw new DataIllegalException("Invalid type");
		}catch (InstantiationException ex){
			throw new DataIllegalException("Invalid type");
		}
        
    }

	public void executeBuyingByType(PrintWriter out, BuyingOffer offer) throws DataIllegalException {
		try {
			Class clazz = Class.forName("net.internetengineering.domain.dealing.types."+offer.getType());
			Object obj= clazz.newInstance();
			if(obj instanceof ITypeExecutor){
				((ITypeExecutor)obj).buyingExecute(out,offer,sellingOffers,buyingOffers,symbol);
			}
		}catch (ClassNotFoundException ex){
			throw new DataIllegalException("Invalid type");
		}catch (IllegalAccessException ex){
			throw new DataIllegalException("Invalid type");
		}catch (InstantiationException ex){
			throw new DataIllegalException("Invalid type");
		}

    }


	public static void matchingOffers(PrintWriter out,Boolean basedOnBuyerPrice,
			List<SellingOffer> sellingOffers,List<BuyingOffer>buyingOffers,String sym,String type){

    	SellingOffer sellingOffer = sellingOffers.get(0);
    	BuyingOffer buyingOffer = buyingOffers.get(0);

    	if(sellingOffer.getPrice() > buyingOffer.getPrice()){
    		out.println("Order is queued");
    		return;
    	}

    	while(true){
	    	if(sellingOffer.getPrice() <= buyingOffer.getPrice()){
	    		Long buyPrice = basedOnBuyerPrice? buyingOffer.getPrice():sellingOffer.getPrice();
	    		Long buyQuantity = (long) 0 ;
	    		if(buyingOffer.getQuantity() < sellingOffer.getQuantity()){
	    			buyQuantity = buyingOffer.getQuantity();
	    			buyingOffers.remove(0);
	    			sellingOffer.setQuantity("delete", buyQuantity);
	    			sellingOffers.set(0, sellingOffer);
					if(sellingOffer.getQuantity()==0L){
						sellingOffers.remove(0);
					}
	    		}
	    		else{
	    			buyQuantity = sellingOffer.getQuantity();
	    			sellingOffers.remove(0);
	    			buyingOffer.setQuantity("delete", buyQuantity);
	    			buyingOffers.set(0, buyingOffer);
					if(buyingOffer.getQuantity()==0L){
						buyingOffers.remove(0);
					}
	    		}
	    		StockMarket.changeCustomerProperty(sellingOffer, buyingOffer, buyPrice, buyQuantity, sym);
				Customer seller = StockMarket.getInstance().getCustomer(sellingOffer.getID());
				Customer buyer = StockMarket.getInstance().getCustomer(buyingOffer.getID());
				Transaction t = new Transaction(buyer.getId(),seller.getId(),sym,type,String.valueOf(buyQuantity),String.valueOf(buyer.getMoney()),
						String.valueOf(seller.getMoney()));
				CSVFileWriter.writeCsvFile(t);
	    		out.println(sellingOffer.getID()+" sold "+buyQuantity+" shares of "+sym+" @"+buyPrice+" to "+buyingOffer.getID());
	    	}else
				break;
	    	if(!sellingOffers.isEmpty()&&!buyingOffers.isEmpty()) {
				sellingOffer = sellingOffers.get(0);
				buyingOffer = buyingOffers.get(0);
			}else
				break;
    	}
    }

    public static void sortOfferingListByPrice(List<? extends Offering> offers){
        Collections.sort(offers, new Comparator<Offering>() {
            @Override
            public int compare(Offering o1, Offering o2) {
                return o1.getPrice()>o2.getPrice()?1:-1;
            }
        });
//		return offers;
    }


    public Boolean symbolIsMatched(String sym){
        return this.symbol.equals(sym);
    }
    
    public Boolean HasQuantity(Long count){
    	if(count <= this.quantity)
    		return true;
    	return false;
    }
    
    public void changeQuantity(String type,Long count){
    	if(type.equals("add"))
    		this.quantity += count;
    	else if(type.equals("delete") && HasQuantity(count))
    		this.quantity -= count;
    }
    
}
