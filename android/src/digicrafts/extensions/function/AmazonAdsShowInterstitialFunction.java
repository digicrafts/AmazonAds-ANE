package digicrafts.extensions.function;

import com.adobe.fre.*;
import digicrafts.extensions.AmazonAdsContext;

public class AmazonAdsShowInterstitialFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args)
    {

        AmazonAdsContext cnt = (AmazonAdsContext)context;
        cnt.showInterstitial(context.getActivity());

        return null;
    }

}
