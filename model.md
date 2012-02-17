Data model:
===========

client:
-------
* device uuid
* registered tv id

tvapps:
-------
* tv id
* app id
* app name
* -- way to specify what params are needed for a specific app?

shows:
-------
* tv id
* app id
* show id (ex: twitter hashtag, youtube video id, eyeem album id)
* show duration (time in minutes)

voting:
-------
* show id
* client id

nextshow:
---------
* tv id
* show id