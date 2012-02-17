Preface
=======

This is just a README at the moment as the implementation will take place at the [Global Android Dev Camp](https://plus.google.com/102283760516132651199) hackathon


What it is in a single sentence
===============================

BISMO ( BIg Screen MOderator ) is a service running on Android Devices with big screens ( Tablet / GTV ) where the watching crowd can vote for the content displayed

Details
=======

The System will have 3 base components:
---------------------------------------
 1. the service running on the Big Screen Device ( from now on called BSD :-)
 2. Installed Apps on the BSD that obey the NOIF ( NO Interaction and Finite ) Specification which has to be invented
 3. client apps for voting


ideas for NOI apps:
-------------------

 * video player
 * gobandroid ( replaying go games ) - easy task to implement that as it is already NOI in TV mode - just need to add the F part ;-)
 * picture dia show ( doing something like this in my 20% project at the company I am working at the moment )
 * G+/Twiter Wall
 * website display
 * hdmi playout
 * ...

notes on the service:
---------------------

 * if there are no votes => all registered NOIF's get time equally 
 * there can be auto-votes configured ( e.g. Thursdays at +c-base spacestation a vote for +gobandroid in TV mode as there is go lounge )
 * in a later phase ( will not fit in the 48h implementation frame ) add algorithms to detect voting patterns and replay them 


Motivation
==========

 * TV's are corrupting the brains of humans - it is hard to get rid of those, but we can change the content displayed
