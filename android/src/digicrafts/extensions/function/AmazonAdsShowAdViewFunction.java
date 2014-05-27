package digicrafts.extensions.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

import digicrafts.extensions.AmazonAdsContext;

public class AmazonAdsShowAdViewFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
    {
        FREObject adIdObj = args[0];
        try
        {
          String adId = adIdObj.getAsString();
          AmazonAdsContext cnt = (AmazonAdsContext)context;
          if(adId!=null){
              cnt.showAdView(context.getActivity(),adId);
          }

        }
        catch (IllegalStateException e) {
          e.printStackTrace();
        } catch (FRETypeMismatchException e) {
          e.printStackTrace();
        } catch (FREInvalidObjectException e) {
          e.printStackTrace();
        } catch (FREWrongThreadException e) {
          e.printStackTrace();
        }

        return null;
    }

}
