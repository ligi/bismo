endpoints:
==========

Client:
-------
* register a client with a tv
    * request: /tv/{tvId}/client/{clientId} (POST)
    * response: message,tvId,clientId
* get available shows for a tv
    * request: /tv/{tvId}/shows (GET)
    * JSONObject with an array of shows called "shows"
* vote for a particular show
    * request: /show/{showId}/client/{clientId} (POST)
    * response: message: vote registered / vote failed + show JSONObject
* get next show
    * request /tv/{tvId}/nextShow
    * returns a JSONObject of a show
* remove vote (step 2)
* find available apps for a tv (step 2)
* suggest/add a new show for a tv (step 2)

TV:
---
* register a tv with the server
    * request: /tv/{tvId} (POST)
    * response: message,tvId
* add shows to a tv
    * request: /show (POST) params: tvId, showName, appId, showDuration
    * returns a JSON object of a show
* close voting    
* get next show
* add apps to a tv (step 2)

* remove shows from a tv
* remove apps from a tv


Dummy:
------
* create default apps?
* create dummy shows for a tv