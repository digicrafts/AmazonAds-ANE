AmazonAds Native Extension for Adobe Air
=========

This AmazonAds ANE add supprt to using Amazon Mobile Ads with Adobe Air. Supports Android only.

##About Amazon Mobile Ads?

https://developer.amazon.com/appsandservices/apis/earn/mobile-ads

##Install the library

Add the AmazonAds-ANE library to your project.

In Flash Professional CS6:

  1. Create a new mobile project
  2. Choose File > PublishSettings... 
  3. Select the wrench icon next to 'Script' for 'ActionScriptSettings' 
  4. Select the Library Path tab. 
  5. Click 'Browse for Native Extension(ANE) File' and select the Mopub.ane file. 

In Flash Builder 4.6:

  1. Goto Project Properties
  2. Select Native Extensions under Actionscript Build Path
  3. Choose Add ANE... and navigate to the Mopub.ane file 
  4. Select Actionscript Build Packaging > Google Android or Apple IOS
  5. Select the Native Extensions tab, and click the 'Package' check box next to the extension

In Flash Professional CS5.5 or Lower:

  1. Select File>PublishSettings>Flash>ActionScript 3.0 Settings 
  2. Select External Library Path
  3. Click Browseto SWC File
  4. Select the Mopub.swc

In Flash Builder 4.5:

  1. Goto Project Properties
  2. Select Action Script Build Path
  3. Select Add Swc
  4. Navigate to Mopub.swc and choose External Library type

In FlashDevelop:

  1. Copy the Mopub.swc file to your project folder.
  2. In the explorer panel, right click the .swc and select Add to Library.
  3. Right-click the swc file in the explorer, choose Options, and select External Library

##Add the Actionscript

Import the library

```javascript
  import digicrafts.extensions.AmazonAds;
  import digicrafts.extensions.events.AmazonAdsEvent;
```

Set the appKey. You can display test ads by supply true to second parameter.

```javascript
  AmazonAds.setAppKey("AMAZON_APP_ID",false);
```

Show a banner AD. Supply an unique name for each banner. You need it for removing the ad. 

```javascript
  AmazonAds.showAdView('banner_name');
```

Remove the Ad

```javascript
  AmazonAds.removeAdView('banner_name');
```

Show a Interstitial Ad

```javascript
  AmazonAds.showInterstitial();
```

Remove the Interstitial Ad

```javascript
  AmazonAds.removeInterstitial();
```

##Setup for Android

Update Your Application Descriptor

You'll need to be using the AIR 3.1 SDK or higher, include the extension in your Application Descriptor XML, and update the Android Manifest Additions with some settings.

Add the following settings in <application> tag.

```xml
<activity android:name="com.amazon.device.ads.AdActivity" android:configChanges="keyboardHidden|orientation|screenSize"/>
```

Add the following permission if you want the ad target location.

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
```

##Game we made using this ANE

[Flippy Day](http://www.amazon.com/Digicrafts-Flippy-Day/dp/B00KL3TYJE/ref=sr_1_1?ie=UTF8&qid=1401244788&sr=8-1&keywords=Flippy+Day)

[Recycle Rangers](http://www.amazon.com/Recycle-Rangers-Free-Kindle-Tablet/dp/B00B4MTUEU/ref=sr_1_1?ie=UTF8&qid=1401244943&sr=8-1&keywords=Recycle+rangers)

[Diamond Speedy](http://www.amazon.com/Diamond-Speedy-Kindle-Tablet-Edition/dp/B0091FQNHO/ref=sr_1_1?ie=UTF8&qid=1401244971&sr=8-1&keywords=Diamond+Speedy)

##Developer

The software is developed by Digicrafts.

http://www.facebook.com/DigicraftsComponents

http://www.digicrafts.com.hk/components

http://www.html5components.com

##License

This project is licensed under the BSD license
