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
 * Time: 2:55 PM
 */
package digicrafts.extensions {
import digicrafts.extensions.events.AmazonAdsEvent;

import flash.events.EventDispatcher;
import flash.events.StatusEvent;
import flash.external.ExtensionContext;
import flash.system.Capabilities;

public class AmazonAds extends EventDispatcher {

    // static
    public static var instance:AmazonAds;

    // private
    private static var allowInstance:Boolean=false;
    private static var extensionContext:ExtensionContext = null;

    public function AmazonAds() {

        if(!allowInstance){
            throw new Error("Error: Instantiation failed: Use AmazonAds.getInstance() instead of new.");
        }
    }

// Private Static Function
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @return
     *
     */
    public static function getInstance():AmazonAds
    {
        if(instance==null){
            allowInstance=true;
            instance=new AmazonAds();
            if ( !extensionContext && Capabilities.os.indexOf("x86_64")==-1)
            {
                trace("[AmazonAds] Get AmazonAds Extension Instance...");
                extensionContext = ExtensionContext.createExtensionContext("digicrafts.extensions.AmazonAds","AmazonAds");
                extensionContext.addEventListener(StatusEvent.STATUS,instance._handleStatusEvents);
            }
            allowInstance=false;
        }
        return instance;
    }

// Public Static Function
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     *
     */
    public static function isSupported():Boolean
    {
        return getInstance()._isSupported();
    }

    /**
     *
     *
     */
    public static function setAppKey(appKey:String,test:Boolean=false):void
    {
        getInstance()._setAppKey(appKey,test);
    }

    /**
     *
     *
     */
    public static function showAdView(id:String,animated:Boolean=false):void
    {
        getInstance()._showAdView(id,animated);
    }

    /**
     *
     *
     */
    public static function showInterstitial():void
    {
        getInstance()._showInterstitial();
    }

    /**
     *
     *
     */
    public static function removeAdView(id:String,animated:Boolean=false):void
    {
        getInstance()._removeAdView(id,animated);
    }

    /**
     *
     *
     */
    public static function removeInterstitial():void
    {
        getInstance()._removeInterstitial();
    }

// Private Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     *
     */
    protected function _isSupported():Boolean
    {
        if(extensionContext)
            return extensionContext.call("isSupported");
        return false;
    }
    /**
     *
     *
     */
    protected function _setAppKey(appKey:String,test:Boolean):void
    {
        if(extensionContext)
            extensionContext.call("setAppKey",appKey,test);
    }

    /**
     *
     *
     */
    protected function _showAdView(id:String,animated:Boolean):void
    {
        if(extensionContext)
            extensionContext.call("showAdView",id,animated);
    }

    /**
     *
     *
     */
    protected function _removeAdView(id:String,animated:Boolean):void
    {
        if(extensionContext)
            extensionContext.call("removeAdView",id,animated);
    }

    /**
     *
     */
    protected function _showInterstitial():void
    {
        if(extensionContext)
            extensionContext.call("showInterstitial");
    }

    /**
     *
     */
    protected function _removeInterstitial():void
    {

        if(extensionContext)
            extensionContext.call("removeInterstitial");
    }


// Private Handler Functions
/////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param e
     *
     */
    protected function _handleStatusEvents(e:StatusEvent):void
    {
        var event:AmazonAdsEvent;

        trace('[AmazonAds] events:',e.code,e.level);

        switch (e.code) {
            case AmazonAdsEvent.INTERSTITIAL_DISMISSED:
            case AmazonAdsEvent.INTERSTITIAL_FAIL_LOAD:
            case AmazonAdsEvent.INTERSTITIAL_LOADED:
                event = new AmazonAdsEvent(e.code, e.level);
                break;
            case "LOGGING":
                trace('[AmazonAds] ' + e.level);
        }

        if (event) dispatchEvent(event);
    }
}
}
