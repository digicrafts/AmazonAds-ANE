package digicrafts.extensions;

import android.app.Activity;

import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import java.util.*;

import com.amazon.device.ads.*;
import digicrafts.extensions.function.*;

public class AmazonAdsContext extends FREContext {

	private static final String TAG = AmazonAdsContext.class.getName();


    private String _appKey;
    private Map<String, AdLayout> _adViewDictionary;
    private InterstitialAd interstitialAd;

	@Override
	public void dispose() {

        Iterator it = _adViewDictionary.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            AdLayout ad = (AdLayout) pairs.getValue();
            ad.destroy();
            it.remove(); // avoids a ConcurrentModificationException
        }
        _adViewDictionary.clear();
        _adViewDictionary=null;

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        functionMap.put("isSupported", new AmazonAdsSupportedFunction());
		functionMap.put("setAppKey", new AmazonAdsSetAppKeyFunction());
        functionMap.put("showAdView", new AmazonAdsShowAdViewFunction());
        functionMap.put("removeAdView", new AmazonAdsRemoveAdViewFunction());
	    functionMap.put("showInterstitial", new AmazonAdsShowInterstitialFunction());
        functionMap.put("removeInterstitial", new AmazonAdsRemoveInterstitialFunction());

        // Create hash map to hold the AdLayout
        _adViewDictionary=new HashMap<String, AdLayout>();
	    
		return functionMap;
	}

// Callback Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////


//    AmazonGamesCallback createCallback = new AmazonGamesCallback() {
//        @Override
//        public void onServiceNotReady(AmazonGamesStatus status) {
//            //unable to use service
//            log("onServiceNotReady");
//            dispatchStatusEventAsync("onServiceNotReady", "event");
//        }
//        @Override
//        public void onServiceReady(AmazonGamesClient amazonGamesClient) {
//            _agsClient = amazonGamesClient;
//            //ready to use GameCircle
//            log("onServiceReady");
//            dispatchStatusEventAsync("onServiceReady","ok");
//        }
//    };

    private RelativeLayout addView(Activity act, View v, Rect rect)
    {
        RelativeLayout rl = new RelativeLayout(act);

        ViewGroup fl = (ViewGroup)act.findViewById(android.R.id.content);
        fl = (ViewGroup)fl.getChildAt(0);

        fl.addView(rl, new FrameLayout.LayoutParams(-1, -1));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(rect.width(), rect.height());
        params.setMargins(rect.left, rect.top, 0, 0);
        rl.addView(v, params);

        return rl;
    }

// Public Methods
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param message
     */
    public void log(String message)
    {
        Log.d(TAG, message);
        dispatchStatusEventAsync("LOGGING", message);
    }

    /**
     *
     * @param appKey
     * @param test
     */
	public void setAppKey(String appKey, Boolean test) {

        this._appKey=appKey;

        if(test){
            // For debugging purposes enable logging, but disable for production builds.
            AdRegistration.enableLogging(true);
            // For debugging purposes flag all ad requests as tests, but set to false for production builds.
            AdRegistration.enableTesting(true);
        }

        AdRegistration.setAppKey(appKey);

    }

    /**
     *
     * @param act
     * @param id
     */
    public void showAdView(Activity act, String id) {

        AdLayout adView = _adViewDictionary.get(id);
        if(adView==null){
            log("showAdView: "+id);

            // Programmatically create the AmazonAdLayout
            adView=new AdLayout(act);

            // Add to dictionary
            _adViewDictionary.put(id, adView);

            // Set and load the ad
            final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            adView.setLayoutParams(layoutParams);
            adView.loadAd();

            // Add the ad to view
            ViewGroup fl = (ViewGroup)act.findViewById(android.R.id.content);
            fl = (ViewGroup)fl.getChildAt(0);
            fl.addView(adView);

        }

    }

    /**
     *
     * @param act
     * @param id
     */
    public void removeAdView(Activity act, String id) {

        AdLayout adView = _adViewDictionary.get(id);
        if(adView!=null){
            adView.destroy();
            _adViewDictionary.remove(id);
        }
    }

    /**
     *
     * @param act
     */
    public void showInterstitial(Activity act) {

        if(interstitialAd==null) {
            interstitialAd = new InterstitialAd(act);
            interstitialAd.setListener(new AdListener());
            interstitialAd.loadAd();
        }
    }


    /**
     * This class is for an event listener that tracks ad lifecycle events.
     * It extends DefaultAdListener, so you can override only the methods that you need.
     * In this case, there is no need to override methods specific to expandable ads.
     */
    class AdListener extends DefaultAdListener
    {
        /**
         * This event is called once an ad loads successfully.
         */
        @Override
        public void onAdLoaded(final Ad ad, final AdProperties adProperties) {
            if(AmazonAdsContext.this.interstitialAd!=null) interstitialAd.showAd();
            AmazonAdsContext.this.dispatchStatusEventAsync("onInterstitialAdLoaded","ok");
        }

        /**
         * This event is called if an ad fails to load.
         */
        @Override
        public void onAdFailedToLoad(final Ad view, final AdError error) {
            AmazonAdsContext.this.dispatchStatusEventAsync("onInterstitialAdFailedToLoad","ok");
        }

        /**
         * This event is called when an interstitial ad has been dismissed by the user.
         */
        @Override
        public void onAdDismissed(final Ad ad) {
            if(AmazonAdsContext.this.interstitialAd!=null) AmazonAdsContext.this.interstitialAd=null;
            AmazonAdsContext.this.dispatchStatusEventAsync("onInterstitialAdDismissed","ok");
        }

    }

}
