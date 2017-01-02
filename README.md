Quake Report App
===================================

This app displays a list of recent earthquakes in the world
from the U.S. Geological Survey (USGS) organization.

###v.0.01 Initial Commit
* List View of fake locations

###v.0.02 Update EarthQuake List
* show Magnitude, Location, Date
* still fake data
* update UI with Custom View for List
* create Earthquake class with fields + getters + setter for Magnitude, Location, Date
* update DataArray to ArrayList<Earthquake>
* create custom adapter to populate data in ListView
    * override getView Method and recycle Views

###v.0.03 Providing earthquake data via hardcoded JSON
* new Query Utils class -> creates ArrayList of EarthQuakes
* app shows data parsed from JSON String

###v.0.04 - Polish UI
* visual styling
* magnitude circle has color of strength

###v.0.05 - show EarthQuake Detail in Browser
* implicit Intent to open web browser with specific URL
* update Earthquake class to hold url field
* fetch url field from JSON
* fixed some UI Bugs from v.0.04

### v1.01 AsyncTask implementation
* fetch fake data in background via Async Task

###v1.02. HttpRequest via AsyncTask
* fetch online data in background via AsyncTask and show it on screen

###v1.03 using Loaders instead of AsyncTask