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
