package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonAdsContext;

public class AmazonAdsSetAppKeyFunction implements FREFunction {
	
	@Override
  	public FREObject call(FREContext context, FREObject[] args)
	{

        try
        {
            FREObject appKeyObj = args[0];
            String appKey = appKeyObj.getAsString();
            FREObject testObj = args[1];
            Boolean test = testObj.getAsBool();

            AmazonAdsContext cnt = (AmazonAdsContext)context;
            if(appKey!=null){
                cnt.log("setAppKey:"+appKey);
                cnt.setAppKey(appKey,test);
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
