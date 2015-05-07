Development Notes
=================

Some thoughts on the desired development and deployment lifecycle for this
style of application.

The sources are [Clojurescript](https://github.com/clojure/clojurescript) files
which are compiled down to a single optimized library file.

The application is published by adding a tree to IPFS containing the compiled
script, bootstrapped HTML scaffolding, and any other static resources.

The user then visits an IPFS gateway to get to the published hash, which serves
the application to the user's browser. The user then needs to either enter a
gateway endpoint for IPFS's API (if it supports CORS), or must be visiting a
gateway they control. Obvious auth questions here.

Longer term, it would be really cool to pull in dependencies by specifying
artifact hashes in IPFS.
