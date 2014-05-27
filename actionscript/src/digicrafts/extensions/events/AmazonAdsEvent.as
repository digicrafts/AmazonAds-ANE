/**
 * (R)2002-2013 Digicrafts
 * All Rights Reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package.
 *
 * Created with IntelliJ IDEA.
 * User: tsangwailam
 * Date: 26/5/14
 * Time: 1:34 PM
 */
package digicrafts.extensions.events {
import flash.events.Event;

public class AmazonAdsEvent extends Event {

    public static const INTERSTITIAL_DISMISSED:String='onInterstitialAdDismissed';
    public static const INTERSTITIAL_FAIL_LOAD:String='onInterstitialAdFailedToLoad';
    public static const INTERSTITIAL_LOADED:String='onInterstitialAdLoaded';


    /** Name of the location related to this event. */
    public var location:String;

    public function AmazonAdsEvent(type:String, location:String=null, bubbles:Boolean=false, cancelable:Boolean=false)
    {
        super(type, bubbles, cancelable);
        this.location = location;
    }
}
}
