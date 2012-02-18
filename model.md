Data model:
===========

client:
-------
* device uuid
* registered tv id

apps:
------
* app id > webview
* app name > WebView
* -- way to specify what params are needed for a specific app?

tvapps:
-------
* tv id
* app id

shows:
-------
* tv id > tv1
* app id > webview
* show id > www.flickr.com
* show duration (time in minutes)

voting:
-------
* show id
* client id

nextshow:
---------
* tv id
* show id