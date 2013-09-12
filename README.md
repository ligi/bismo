[![Build Status](https://ligi.ci.cloudbees.com/buildStatus/icon?job=bismo)](https://ligi.ci.cloudbees.com/job/bismo/)

What it is in a single sentence
===============================

BISMO ( BIg Screen MOderator ) is intended to run on Android Devices with big screens ( Tablet / GoogleTV ) where the watching crowd can vote for the content displayed

Motivation
==========

TV's are corrupting the brains of humans - it is hard to get rid of those, but we can change the content displayed

Details
=======

The System has 4 base components:
---------------------------------------
 1. the App running on the Big Screen Device ( from now on called BSD :-)
 2. Installed Apps on the BSD that obey the NOIF ( NO Interaction and Finite ) Specification
 3. client app(s) for voting
 4. Backend ( AppEngine )

available NOI apps:
-------------------

 * video player
 * gobandroid ( replaying go games ) - easy task to implement that as it is already NOI in TV mode - just need to add the F part ;-)
 * picture dia show ( doing something like this in my 20% project at the company I am working at the moment )
 * Twiter Wall
 * website display

ideas for other NOI apps:
 * YouTube / vimeo player
 * G+ wall
 * hdmi playout
 * ...

notes on the service:
---------------------

 * if there are no votes => all registered NOIF's get time equally 
 * there can be auto-votes configured ( e.g. Thursdays at +c-base spacestation a vote for +gobandroid in TV mode as there is go lounge )
 * in a later phase ( will not fit in the 48h implementation frame ) add algorithms to detect voting patterns and replay them 
